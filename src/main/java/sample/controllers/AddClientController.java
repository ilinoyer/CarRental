package sample.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Session session = HibernateUtilities.getSessionFactory().openSession();
                session.beginTransaction();
                Client newClient = new Client(firstNameField.getText(),lastNameField.getText(), Integer.parseInt(drivingLicenseField.getText()),Integer.parseInt(IDNumberField.getText()), new Address(townField.getText(), postCodeField.getText(), countryField.getText(), addressField.getText()));
                session.save(newClient);
                session.getTransaction().commit();
                session.close();
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
