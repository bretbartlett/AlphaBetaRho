/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlingredientnormalizer;

import xmlingredientnormalizer.ImportedRecipe;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lee
 */
@XmlRootElement(name = "recipes")
@XmlAccessorType(XmlAccessType.FIELD)
public class RecipeWrapper implements Serializable {

    @XmlElement(name = "recipe")
    List<ImportedRecipe> list;
    @XmlElement(name = "unstoredRecipes")
    List<String> unstoredRecipeList;

    public RecipeWrapper() {
        this.list = new ArrayList<ImportedRecipe>();
        this.unstoredRecipeList = new ArrayList<String>();
    }

    public void add(ImportedRecipe s) {
        list.add(s);
    }

    public List<ImportedRecipe> getList() {
        return list;
    }

    public void setList(List<ImportedRecipe> list) {
        this.list = list;
    }

    public List<String> getUnstoredRecipeList() {
        return unstoredRecipeList;
    }

    public void setUnstoredRecipeList(List<String> unstoredRecipeList) {
        this.unstoredRecipeList = unstoredRecipeList;
    }
}
