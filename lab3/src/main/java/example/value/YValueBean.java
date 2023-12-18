package example.value;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.validator.ValidatorException;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Managed bean for handling X coordinate value in JSF application.
 * Provides functionality to get and set X value, and to validate it.
 */
@Data
@NoArgsConstructor
@ViewScoped
public class YValueBean implements Serializable {
    private Double y;

    public void validateYBeanValue(Object o){
        if (o == null){
            FacesMessage message = new FacesMessage("Input Y!");
            throw new ValidatorException(message);
        }
    }
}
