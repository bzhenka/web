package example.value;

import jakarta.annotation.ManagedBean;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
@ManagedBean
@ViewScoped
@Data
public class RValueBean implements Serializable {
    private String selectedItem = "3";
    @Getter
    private List<String> availableItems = Arrays.asList("1", "1.5", "2", "2.5", "3");
}

