package sample.controllers;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Session;
import sample.Brand;
import sample.HibernateUtilities;

import java.net.URL;
import java.util.ResourceBundle;

public class AddBrandController implements Initializable{

    private SimpleBooleanProperty isNewBrandAdded;
    private Brand newBrand;

    @FXML
    Button cancelButton;

    @FXML
    Button addButton;

    @FXML
    TextField brandNameField;

    public AddBrandController(SimpleBooleanProperty isNewBrandAdded)
    {
        this.isNewBrandAdded = isNewBrandAdded;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                newBrand = new Brand(brandNameField.getText());
                isNewBrandAdded.setValue(true);
                //Stage stage = (Stage)addButton.getScene().getWindow();
                //stage.close();
            }
        });

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage)cancelButton.getScene().getWindow();
                stage.close();
            }
        });
    }

    public Brand getNewBrand() {
        return newBrand;
    }
}
