package sample.controllers;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Session;
import sample.*;

import java.net.URL;
import java.util.ResourceBundle;

public class AddModelController implements Initializable{

    private Model model;
    private SimpleBooleanProperty isNewModelAdded;

    @FXML
    private Button cancelButton;

    @FXML
    private Button addButton;

    @FXML
    private TextField modelNameField;

    @FXML
    private ComboBox<String> brandComboBox;

    public AddModelController(SimpleBooleanProperty isNewModelAdded) {
        this.isNewModelAdded = isNewModelAdded;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Session session = HibernateUtilities.getSessionFactory().openSession();
                session.beginTransaction();
                Model newModel = new Model(modelNameField.getText());
                session.save(newModel);
                session.getTransaction().commit();
                session.close();
                //Stage stage = (Stage)addButton.getScene().getWindow();
                //stage.close();
                // loading brands from database needed
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
}
