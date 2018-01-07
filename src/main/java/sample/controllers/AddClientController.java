package sample.controllers;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.hibernate.Session;
import sample.Address;
import sample.Client;
import sample.HibernateUtilities;

import java.net.URL;
import java.util.ResourceBundle;

public class AddClientController implements Initializable {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField drivingLicenseField;

    @FXML
    private TextField IDNumberField;

    @FXML
    private TextField countryField;

    @FXML
    private TextField townField;

    @FXML
    private TextField postCodeField;

    @FXML
    private TextField addressField;

    @FXML
    private Button cancelButton;

    @FXML
    private Button addButton;

    private Client newClient;
    private SimpleBooleanProperty isNewClientAdded;

    public AddClientController( SimpleBooleanProperty isNewClientAdded) {
        this.isNewClientAdded = isNewClientAdded;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                newClient = new Client.ClientBuilder(firstNameField.getText(),lastNameField.getText(),Integer.parseInt(IDNumberField.getText()))
                        .addDrivingLicenseNumber(Integer.parseInt(drivingLicenseField.getText()))
                        .addAddress(new Address(townField.getText(), postCodeField.getText(), countryField.getText(), addressField.getText())).build();

                isNewClientAdded.setValue(true);
                Stage stage = (Stage)addButton.getScene().getWindow();
                stage.close();
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

    public Client getNewClient()
    {
        return newClient;
    }

}
