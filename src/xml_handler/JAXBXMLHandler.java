package xml_handler;

import XMLMainFile.AddingRating;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import xmlingredientnormalizer.ImportedRecipe;
import xmlingredientnormalizer.Ingredient;
import xmlingredientnormalizer.RecipeWrapper;
import xmlingredientnormalizer.NewImportedRecipe;
import xmlingredientnormalizer.NewIngredient;
import xmlingredientnormalizer.NewRecipeWrapper;
import xmlingredientnormalizer.SemanticNormalization;
import xmlingredientnormalizer.FindRho;

public class JAXBXMLHandler {

    
    public JAXBXMLHandler() {
    }

    // Import

    
    public void unmarshalremarshal(File importFile, File exportFile) throws IOException, JAXBException, FileNotFoundException {
        NewImportedRecipe tmpRecipe;
        NewRecipeWrapper NewRecWrapper = new NewRecipeWrapper();
        NewIngredient tIngredient;
        JAXBContext jc = JAXBContext.newInstance(RecipeWrapper.class);
        RecipeWrapper storageWrapper = new RecipeWrapper();
        Unmarshaller u = jc.createUnmarshaller();
        RecipeWrapper tmpWrapper = null;
        
        List <String> ALPHASEARCH = excelread.Excelread.main();
        
        
        try {
            tmpWrapper = (RecipeWrapper) u.unmarshal(importFile);
        } catch (Exception fileNotFound) {
            System.err.print("Error: " + fileNotFound.getMessage());            
        }        

        
        for (ImportedRecipe element : tmpWrapper.getList()) {
            tmpRecipe = new NewImportedRecipe();
            
            tmpRecipe.setCheckFlag(element.isCheckFlag());
            tmpRecipe.setCreateDate(element.getCreateDate());
            tmpRecipe.setDirections(element.getDirections());
            tmpRecipe.setErrorString(element.getErrorString());
            tmpRecipe.setIngredientStrings(element.getIngredientStrings());
            tmpRecipe.setParserVersion(element.getParserVersion());
            tmpRecipe.setServings(element.getServings());
            tmpRecipe.setSourceURL(element.getSourceURL());
            tmpRecipe.setSubtitles(element.isSubtitles());
            tmpRecipe.setTimes(element.getTimes());
            tmpRecipe.setUserRating(element.GetUserRating());
            tmpRecipe.setTitle(element.getTitle());

            for (Ingredient tmpIngredient : element.getIngredients()) {
                tIngredient = new NewIngredient();
                tIngredient.setAddedInformation(tmpIngredient.getAddedInformation());
                tIngredient.setAmount(tmpIngredient.getAmount());
                tIngredient.setDescription(tmpIngredient.getDescription());
                tIngredient.setFullIngredientString(tmpIngredient.getFullIngredientString());
                tIngredient.setStatus(tmpIngredient.getStatus());
                tIngredient.setUom(tmpIngredient.getUom());
                tIngredient.setIdentifier(SemanticNormalization.main(tmpIngredient.getAmount(), tmpIngredient.getUom(), tmpIngredient.getDescription(), element.getTitle()));

                tmpRecipe.getIngredients().add(tIngredient);
            }            
            
            tmpRecipe.setRho(FindRho.main(tmpRecipe.getDirections()));
            tmpRecipe.setAlpha(FindRho.GetAlpha(tmpRecipe.getIngredients(), ALPHASEARCH));
            tmpRecipe.setBeta(FindRho.GetBeta(tmpRecipe.getIngredients(), ALPHASEARCH));
            tmpRecipe.setGamma(FindRho.GetGamma(tmpRecipe.getIngredients()));
            
            NewRecWrapper.getList().add(tmpRecipe);
        }

        NewRecWrapper.getUnstoredRecipeList().addAll(tmpWrapper.getUnstoredRecipeList());

        JAXBContext njc = JAXBContext.newInstance(NewRecipeWrapper.class);
        Marshaller m = njc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        m.marshal(NewRecWrapper, new FileOutputStream(exportFile));
    }
    private static final Logger LOG = Logger.getLogger(JAXBXMLHandler.class.getName());
}
