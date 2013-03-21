
package xmlingredientnormalizer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import xmlingredientnormalizer.NewIngredient;

/**
 *
 * @author bretbartlett
 */
public class FindRho {
  
      private static final String[] RhoKeyWords = {
        "baked", "bakes", "bake","baking",
        "basted", "baster", "bastes", "basting", "baste",
        "blanched", "blanches", "blanching", "blanche",
        "blended", "blender", "blends", "blending", "blend",
        "boiled", "boil", "boils", "boiler", "boiling",
        "braised", "braises", "baising", "braise",
        "brines", "brined", "brining", "brine",
        "broils", "broiler", "broiled", "broiling", "broil",
        "ceviche",
        "finely chopped", "roughly chopped", "chopping","chopped",
        "crushed", "crushing",
        "cool", "cools", "cooled", "cooling",
        "crumbled", "crumbles", "crumble", "crumbling",
        "deep-fries", "deep-fried", "deep-frier", "deep fries", "deep fried", "deep frier", "deep-frying",
        "diced", "dices", "dicer", "dicing", "dice",
        "dry", "dries", "dried", "drying",
        "ferment", "ferments", "fermented", "fermenter", "fermenting",
        "flaked",
        "flash-freeze", "flash-frozen", "flash freeze", "flash frozen", "flash freezing",
        "freeze", "freezes", "frozen", "freezer", "freezing",
        "grated", "grates", "grater", "grate", "grating",
        "grinding", "grind", "grinds", "grinder",
        "hardening", "harden", "hardens", "hardened",
        "julienning", "julienne", "juliennes", "julienned",
        "kneading", "knead", "kneads", "kneaded",
        "marinating", "marinate", "marinates", "marinated",
        "melted",
        "microwaving", "microwave", "microwaved", "microwaves", "nuke", "nuked", "nukes", "zap", "zaps", "zapped",
        "milling", "mill", "mills", "milled", "mill",
        "mincing", "mince", "minces", "minced", "mincer",
        "mixing", "mixes", "mixer", "mixed",
        "pan-frying", "pan-fry", "pan-fried", "pan-fries", "frying pan", "pan frying", "pan fry", "pan fried", "pan fries",
        "peeling", "peel", "peels", "peeled", "peeler",
        "pickling", "pickles", "pickled",
        "refrigeration", "refrigerate", "refrigerated", "refrigerates", "refrigerator", "fridge",
        "roasting", "roasted", "roasts", "roaster", "roast",
        "rolling", "rolled", "roller", "flattened","flatten",
        "salting", "salts", "salter", 
        "sautéing", "sauté", "sautés", "sautéd",
        "shaving", "shave", "shaves", "shaved",
        "finely shredded", "shredded",
        "slow cooking", "slow cook", "slow-cook", "slow cooked",
        "sliced",
        "smoking", "smoked", "smokes", "smoker", "smoke",
        "softened",
        "souring", "soured", "sours",
        "steaming", "steam", "steams", "steamed", "steamer",
        "stewing", "stewed",
        "stir-frying", "stir-fry", "stir-fries", "stir-fried", "stir frying", "stir fry", "stir fries", "stir fried",
        "stirring", "stir", "stirs", "stirred", "stirrer",
        "fries", "fried", "frier", "frying",
        "sugaring", "sugars", "sugared",
        "wood-firing", "wood-fire", "wood-fires", "wood-fired",
        "uncooked"};
    private static final String[] PrintRhoKey = {
        "bake", "bake", "bake","bake",
        "baste", "baste", "baste", "baste", "baste",
        "blanche", "blanche", "blanche", "blanche",
        "blend", "blend", "blend", "blend", "blend",
        "boil", "boil", "boil", "boil", "boil",
        "braised", "braised", "braised", "braised",
        "brine", "brine", "brine", "brine",
        "broil", "broil", "broil", "broil", "broil",
        "ceviche",
        "finely chopped", "roughly chopped", "chopping","chopped",
        "crushed", "crushed",
        "cooled", "cooled", "cooled", "cooled",
        "crumble", "crumbled", "crumbled", "crumbled",
        "deep-fried", "deep-fried", "deep-fried", "deep-fried", "deep-fried", "deep-fried", "deep-fried",
        "dice", "dice", "dice", "dice", "dice",
        "dried", "dried", "dried", "dried",
        "fermented", "fermented", "fermented", "fermented", "fermented",
        "flaked",
        "flash-frozen", "flash-frozen", "flash-frozen", "flash-frozen", "flash-frozen",
        "frozen", "frozen", "frozen", "frozen", "frozen",
        "grated", "grated", "grated", "grate", "grated",
        "ground", "ground", "ground", "ground", 
        "hardened", "hardened", "hardened", "hardened",
        "julienned", "julienned", "julienned", "julienned",
        "kneaded", "kneaded", "kneaded", "kneaded",
        "marinated", "marinated", "marinated", "marinated",
        "melted",
        "microwaved", "microwaved", "microwaved", "microwaved", "microwaved", "microwaved", "microwaved", "microwaved", "microwaved", "microwaved",
        "milled", "milled", "milled", "milled", "milled",
        "mince", "mince", "mince", "mince", "mince",
        "mixed", "mixed", "mixed", "mixed",
        "pan-fried", "pan-fried", "pan-fried", "pan-fried", "pan-fried", "pan-fried", "pan-fried", "pan-fried", "pan-fried",
        "peeled", "peeled", "peeled", "peeled", "peeled",
        "pickled", "pickled", "pickled",
        "refrigerate", "refrigerate", "refrigerate", "refrigerate", "refrigerate", "refrigerate",
        "roast", "roast", "roast", "roast", "roasted",
        "rolled", "rolled", "rolled", "flattened","flatten",
        "salted", "salted", "salted",
        "sautéd", "sautéd", "sautéd", "sautéd",
        "shaved", "shaved", "shaved", "shaved",
        "fine shredded", "shredded",
        "slow cooked", "slow cooked", "slow cooked", "slow cooked",
        "sliced",
        "smoke", "smoke", "smoke", "smoke", "smoked",
        "softened",
        "soured", "soured", "soured",
        "steamed", "steamed", "steamed", "steamed", "steamed",
        "stewed", "stewed",
        "stir-fried", "stir-fried", "stir-fried", "stir-fried", "stir-fried", "stir-fried", "stir-fried", "stir-fried",
        "stirred", "stirred", "stirred", "stirred", "stirred",
        "fried", "fried", "fried", "fried",
        "sugared", "sugared", "sugared",
        "wood-fired", "wood-fired", "wood-fired", "wood-fired",
        "uncooked"};
    
     
    
    
    public static List <String> main( List <String> directions ){
     
        List<String> RHO = new ArrayList<String>();

        if(! directions.isEmpty()){
        for(String DirString : directions){  
            for (int i = 0 ; i <  RhoKeyWords.length ; i++){
                if(DirString.contains(RhoKeyWords[i])){
                   if(!RHO.contains(PrintRhoKey[i])){
                        RHO.add(PrintRhoKey[i]);
                   }
                }
            }
          }
        }
        
        if (RHO.isEmpty()){
            RHO.add("Not Found");
        }
        return RHO;
    }
    
    
    public static List<String> GetAlpha(List<NewIngredient> Ingredient, List<String> ALPHASEARCH) throws FileNotFoundException, IOException{
      
     List<String> ALPHA = new ArrayList<String>();

        if(! Ingredient.isEmpty()){
        for(NewIngredient ThisIngredient : Ingredient){  
            for (String alphasearch : ALPHASEARCH){
                if(ThisIngredient.fullIngredientString.contains(alphasearch)){
                   if(!ALPHA.contains(alphasearch)){
                        ALPHA.add(alphasearch);
                   }
                }
            }
          }
        }
        
        if (ALPHA.isEmpty()){
            ALPHA.add("Not Found");
        }
        return ALPHA;
    }   
    
    public static List<String> GetBeta(List<NewIngredient> Ingredient, List<String> BETASEARCH) throws FileNotFoundException, IOException{
                 
     List<String> BETA = new ArrayList<String>();

        if(! Ingredient.isEmpty()){
        for(NewIngredient ThisIngredient : Ingredient){  
            for (String betasearch : BETASEARCH){
                if(ThisIngredient.fullIngredientString.contains(betasearch)){
                   if(!BETA.contains(betasearch)){
                        BETA.add(betasearch);
                   }
                }
            }
          }
        }
        
        if (BETA.isEmpty()){
            BETA.add("Not Found");
        }
        return BETA;
    }
    
    public static List<String> GetGamma(List<NewIngredient> Ingredient){
     List<String> GAMMA = new ArrayList<String>();
     
     float highnum = 1;
     for(NewIngredient ing : Ingredient){
         if (ing.getIdentifier() > highnum){
             highnum = ing.getIdentifier();
         }
     }
     
     for (NewIngredient ing : Ingredient){
         if(ing.getIdentifier() <= (highnum-2) && ing.getIdentifier() >= 1f ){
             GAMMA.add(ing.getDescription());
         }
    }
     
     if (GAMMA.isEmpty()){
            GAMMA.add("Not Found");
        }
     return GAMMA;
    }
    
    
    
    
}
