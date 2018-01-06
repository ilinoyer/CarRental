package sample.controllers;

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

    @FXML
    Button cancelButton;

    @FXML
    Button addButton;

    @FXML
    TextField brandNameField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Session session = HibernateUtilities.getSessionFactory().openSession();
                session.beginTransaction();
                Brand newBrand = new Brand(brandNameField.getText());
                session.save(newBrand);
                session.getTransaction().commit();
                session.close();
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
}
