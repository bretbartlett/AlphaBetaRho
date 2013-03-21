package XMLMainFile;
 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import xml_handler.JAXBXMLHandler;
import xmlingredientnormalizer.ImportedRecipe;
import xmlingredientnormalizer.Ingredient;
import xmlingredientnormalizer.RecipeWrapper;
import xmlingredientnormalizer.NewImportedRecipe;
import xmlingredientnormalizer.NewIngredient;
import xmlingredientnormalizer.NewRecipeWrapper;
import xmlingredientnormalizer.SemanticNormalization;
 
public class AddingRating {
       
   
    
    public static void main(String[] args) throws JAXBException, FileNotFoundException, IOException {
        if (args.length != 2) {
            System.out.println("Needed parameters: "); 
            System.out.println("\t input file");
            System.out.println("\t output file");            
            return;
        }
        
        String inputFile = args[0];
        String outputFile = args[1];                
     
        
        
        try {
            
          JAXBXMLHandler xmlHandler = new JAXBXMLHandler();
            
          xmlHandler.unmarshalremarshal(new File(inputFile),new File(outputFile));
            
        } catch (JAXBException ex) {
            Logger.getLogger(AddingRating.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
