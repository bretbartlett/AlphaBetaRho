
package xmlingredientnormalizer;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Ingredient implements Serializable {
    
    @XmlElement
    float amount;
    @XmlElement
    String uom;
    @XmlElement
    String description;
    @XmlElement
    String fullIngredientString;
    @XmlElement
    String addedInformation;
    @XmlElement
    String status;

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
    
    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getFullIngredientString() {
        return fullIngredientString;
    }

    public void setFullIngredientString(String fullIngredientString) {
        this.fullIngredientString = fullIngredientString;
    }

    public String getAddedInformation() {
        return addedInformation;
    }

    public void setAddedInformation(String addedInformation) {
        this.addedInformation = addedInformation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
