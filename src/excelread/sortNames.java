/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package excelread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author bretbartlett
 */

public class sortNames {
    
        
   public static List<String> SortIngs (List<String> Ingredients){
       
       List<String> SortedIngs = new ArrayList<String>();       
       boolean finished = false;
       
       for( String input : Ingredients){
          SortedIngs.add(input.toLowerCase());
       }
       Collections.sort(SortedIngs);
       Collections.reverse(SortedIngs);
       
       do{
           finished = true;
           for (String outside : SortedIngs){
               for (String inside : SortedIngs){
                   if (inside.contains(outside) && (SortedIngs.indexOf(inside) > SortedIngs.indexOf(outside))){
                     Collections.swap(SortedIngs, SortedIngs.indexOf(outside), SortedIngs.indexOf(inside));
                     finished = false;
                   }   
            }
        }
       }while(finished);
       
       
       return SortedIngs;
   }
    
    
    
}
