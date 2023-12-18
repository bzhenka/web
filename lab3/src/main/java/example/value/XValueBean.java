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
public class XValueBean implements Serializable {
    private String selectedItem = "0";
    @Getter
    private List<String> availableItems = Arrays.asList("-4", "-3", "-2", "-1", "0", "1", "2", "3", "4");
}
