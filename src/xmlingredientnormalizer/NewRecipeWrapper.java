/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlingredientnormalizer;

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
public class NewRecipeWrapper implements Serializable {

    @XmlElement(name = "recipe")
    List<NewImportedRecipe> list;
    @XmlElement(name = "unstoredRecipes")
    List<String> unstoredRecipeList;

    public NewRecipeWrapper() {
        this.list = new ArrayList<NewImportedRecipe>();
        this.unstoredRecipeList = new ArrayList<String>();
    }

    public void add(NewImportedRecipe s) {
        list.add(s);
    }

    public List<NewImportedRecipe> getList() {
        return list;
    }

    public void setList(List<NewImportedRecipe> list) {
        this.list = list;
    }

    public List<String> getUnstoredRecipeList() {
        return unstoredRecipeList;
    }

    public void setUnstoredRecipeList(List<String> unstoredRecipeList) {
        this.unstoredRecipeList = unstoredRecipeList;
    }
}
