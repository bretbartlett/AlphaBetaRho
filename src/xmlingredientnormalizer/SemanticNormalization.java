package xmlingredientnormalizer;



import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import xmlingredientnormalizer.NewImportedRecipe;
import xmlingredientnormalizer.NewIngredient;
import xmlingredientnormalizer.NewRecipeWrapper;

/**
 *
 * @author bretbartlett
 */
public class SemanticNormalization {

    //enter the string you want into this one, run program and it takes care of the rest
    private static final String[] OriginalUOM = {
        "teaspoon",
        "tablespoon",
        "pound",
        "ounce",
        "fluid-ounce",
        "grams",
        "liter",
        "milliliter",
        "cup",
        "pint",
        "quart",
        "slices",
        "bottle",
        "pan",
        "inch",
        "gallon"};
    private static final float[] ConversionVolumeUOM = new float[]{
        2f,
        3f,
        97f,
        6f,
        6f,
        1f,
        202f,
        2f,
        48f,
        96f,
        192f,
        6f,
        100f,
        20f,
        768f};
    private static final String[] DistinctSpices = {
        "allspice",
        "anise",
        "basil",
        "bay leaf",
        "caraway",
        "cardamom",
        "chive",
        "cilantro",
        "cinnamon",
        "cloves",
        "coriander",
        "cumin",
        "dill",
        "fennel",
        "cocoa",
        "garlic",
        "ginger",
        "mace",
        "mustard",
        "nutmeg",
        "oregano",
        "paprika",
        "parsley",
        "black pepper",
        "rosemary",
        "lemon bay leaves",
        "sage",
        "salt",
        "thyme",
        "cayenne pepper",
        "curry",
        "picante sauce"};
    private static final String[] SmallIngredients = {
        "grape tomatoes",
        "cherry tomatoes",
        "tomatoes",
        "strawberries",
        "nectarines",
        "grapes",
        "blueberries",
        "baby carrots",
        "raspberries"
    };
    private static final String[] MediumIngredients = {
        "green onions",
        "anchovy fillet",
        "eggs",
        "vanilla extract",
        "romaine lettuce",
        "red onion cloves",
        "garlic boneless",
        "pork chops",
        "basil leaves mango",
        "red bell pepper",
        "slices bacon",
        "potatoes",
        "stalks celery",
        "jalapeño peppers",
        "apples",
        "pears",
        "cardamom pod",
        "avocado",
        "tilapia fillet",
        "jumbo pasta shell",
        "jumbo shrimp",
        "cinnamon stick",
        "carrot",
        "taco shell",
        "french roll",
        "leek",
        "pineapple",
        "banana",
        "celery",
        "sweet bell peppers",
        "peaches",
        "spinach",
        "lettuce",
        "cucumbers",
        "potatoe",
        "kale",
        "cherries",
        "hot pepper",
        "green pepper",
        "red pepper",
        "yellow pepper",
        "pear",
        "nectarines",
        "plums",
        "summer squash",
        "oranges",
        "broccoli",
        "green onions",
        "bananas",
        "honeydew melon",
        "tomatoes",
        "cantaloupe",
        "cauliflower",
        "papaya",
        "ham",
        "plums",
        "winter squash",
        "mushrooms",
        "watermelon",
        "grapefruit",
        "sweet potatoes",
        "cantaloupe",
        "kiwi",
        "eggplant",
        "mangoes",
        "asparagus",
        "sweet peas",
        "cabbage",
        "avocado",
        "pineapples",
        "sweet corn",
        "onion",
        "chicken",
        "zucchini"
    };

   private static final String[] ReplaceTitle = {
    "and","with","in","on","baked","barbecued","-"
};
    
    public static float main(float AMT, String UOM, String ING, String RecipeTitle) {

        String NewTitle = RecipeTitle.toLowerCase();
        for ( String Word : ReplaceTitle){
        NewTitle = NewTitle.replaceAll(Word, " ");
        }
        NewTitle = NewTitle.replaceAll("\\s+", " ");
        String[] TitleKeys = NewTitle.split("\\s");
        
        float UOMmultiplier = 1f;
        float BASE = 1f;
        int x = 0;

        if (AMT == -1.0) {
            AMT = 1f;
        }

        String UOMtmp = UOM;
        if (ING.equals("")) {
            return (float) 0.0;
        }


//Taking the unit of measurement and splitting it up so know what type dealing with


            String NUOM = UOMtmp.replaceAll("-", " ");
            NUOM = NUOM.replaceAll("9x9", "12");
            String[] result = NUOM.split("\\s");
            
            AMT = Math.abs(AMT);

// if the result is longer than 1 then it is something like AMT UOM UOM or just AMT UOM
// this section takes the firt number if it is a number and multiplies it to the multiplier

            if (result.length > 1) {
                if (isNUM(result[0].charAt(0))) {
                    float s = Float.valueOf(result[0]);
                    UOMmultiplier = (UOMmultiplier * s);
                    x = 1;
                }
            }
            // finds the conversion of the UOM in it and multiplies the multiplier by the conversion

            int f = 0;
            while (f < OriginalUOM.length) {
                if (UOM.contains(OriginalUOM[f])) {
                    if (ConversionVolumeUOM.length > f) {
                        UOMmultiplier = (UOMmultiplier * ConversionVolumeUOM[f]);
                    }
                    break;
                }
                f++;
            }

            // if there is a distinct spice then the amount is going to be much less. 
            // to counter this the mulitiplier recieves a boost

      for (String Spice : DistinctSpices){
            if(ING.toLowerCase().contains(Spice)){
                UOMmultiplier = (UOMmultiplier * 10f);
            }
        }
  
        if(UOM.equals("")){
        // multiplies by 10 for small ingredients like grapes
        // by 30 for larger like apples and onions

            
        for (String SmallING : SmallIngredients){
            if(ING.toLowerCase().contains(SmallING)){
                UOMmultiplier = (UOMmultiplier * 10f);
            }
        }
  
           
        for (String MedING : MediumIngredients){
            if(ING.toLowerCase().contains(MedING)){
                UOMmultiplier = (UOMmultiplier * 30f);
            }
        }
        
        }//end of uom if statement
        
        for (String KeyING : TitleKeys){
            if(ING.toLowerCase().contains(KeyING)){
                UOMmultiplier = (UOMmultiplier * 100f);
            }
        }
        
        
        // 100/100 is going to be a 4 out of 5
        // so this takes that base and divides the amount*multiplier
        // and divides by 4

        BASE = Math.round(((AMT * UOMmultiplier) / 100) * 4);
        if (BASE > 5) {
            BASE = 5;
        }



        return BASE;

    }//end of main
    

    public static boolean isNUM(char x) {
        if (x == '1'
                || x == '2'
                || x == '3'
                || x == '4'
                || x == '5'
                || x == '6'
                || x == '7'
                || x == '8'
                || x == '9'
                || x == '0'
                || x == '.') {
            return true;
        } else {
            return false;
        }

    }
}
