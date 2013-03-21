/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlingredientnormalizer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gardneli
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class NewImportedRecipe implements Serializable {

    @XmlElement
    String title;
    @XmlElement
    List<NewIngredient> ingredients;
    @XmlElement
    List<String> ingredientStrings;
    @XmlElement
    List<String> directions;
    @XmlElement
    List<String> rho;
    @XmlElement
    List<String> alpha;
    @XmlElement
    List<String> beta;
    @XmlElement
    List<String> gamma;
    @XmlElement
    String sourceURL;
    @XmlElement
    boolean checkFlag;
    @XmlElement
    String parserVersion;
    @XmlElement
    List<String> times;
    @XmlElement
    String createDate;
    @XmlElement
    String servings;
    @XmlElement
    String errorString;
    @XmlElement
    boolean subtitles;
    @XmlElement
    int userRating;

    public NewImportedRecipe() {
        ingredients = new ArrayList<NewIngredient>();
        ingredientStrings = new ArrayList<String>();
        directions = new ArrayList<String>();
        times = new ArrayList<String>();
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<NewIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<NewIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getIngredientStrings() {
        return ingredientStrings;
    }

    public void setIngredientStrings(List<String> ingredientStrings) {
        this.ingredientStrings = ingredientStrings;
    } 
    
    public List<String> getDirections() {
        return directions;
    }

    public void setDirections(List<String> directions) {
        this.directions = directions;
    }
        
    public List<String> getRho() {
        return rho;
    }

    public void setRho(List<String> rho) {
        this.rho = rho;
    }
    public List<String> getAlpha() {
        return alpha;
    }

    public void setAlpha(List<String> alpha) {
        this.alpha = alpha;
    }
    public List<String> getBeta() {
        return beta;
    }

    public void setBeta(List<String> beta) {
        this.beta = beta;
    }
    public List<String> getGamma() {
        return gamma;
    }

    public void setGamma(List<String> gamma) {
        this.gamma = gamma;
    }
    public String getSourceURL() {
        return sourceURL;
    }

    public void setSourceURL(String sourceURL) {
        this.sourceURL = sourceURL;
    }

    public boolean isCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(boolean checkFlag) {
        this.checkFlag = checkFlag;
    }

    public String getParserVersion() {
        return parserVersion;
    }

    public void setParserVersion(String parserVersion) {
        this.parserVersion = parserVersion;
    }

    public List<String> getTimes() {
        return times;
    }

    public void setTimes(List<String> times) {
        this.times = times;
    } 

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }        

    public String getServings() {
        return servings;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }

    public String getErrorString() {
        return errorString;
    }

    public void setErrorString(String errorString) {
        this.errorString = errorString;
    }

    public boolean isSubtitles() {
        return subtitles;
    }

    public void setSubtitles(boolean subtitles) {
        this.subtitles = subtitles;
    }
    public int GetUserRating(){
        return userRating;
    }
    
    public void setUserRating(int userRating){
        this.userRating = userRating;
    }
    
    @Override
    public String toString() {
        StringBuilder tmpString = null;
        StringBuilder tmpStringDirections = null;
        for (NewIngredient ingredient : getIngredients()) {
            tmpString.append(ingredient.toString()).append("\n");
        }

        for (String direction : getDirections()) {
            tmpStringDirections.append(direction.toString()).append("\n");
        }
        
        return "Title is: \n" + getTitle()
                + "Ingredients are: " + tmpString.toString() +
                "Directions are:" + tmpStringDirections.toString();

    }
}
