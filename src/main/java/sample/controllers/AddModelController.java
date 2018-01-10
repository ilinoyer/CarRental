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
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.query.Query;
import sample.*;

import java.net.URL;
import java.util.ResourceBundle;

public class AddModelController implements Initializable{

    private Model newModel = null;
    private SimpleBooleanProperty isNewModelAdded;

    @FXML
    private Button cancelButton;

    @FXML
    private Button addButton;

    @FXML
    private TextField modelNameField;

    @FXML
    private ComboBox<Brand> brandComboBox;

    public AddModelController(SimpleBooleanProperty isNewModelAdded) {
        this.isNewModelAdded = isNewModelAdded;
    }

    private void initComboBox()
    {
        Session session = HibernateUtilities.getSessionFactory().openSession();
        session.beginTransaction();

        ScrollableResults scrollableResults = session.createQuery("from Brand ").scroll(ScrollMode.FORWARD_ONLY);

        Brand addBrand;
        while(scrollableResults.next())
        {
            addBrand = (Brand) scrollableResults.get(0);
            brandComboBox.getItems().add(addBrand);
        }

        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initComboBox();

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                newModel = new Model(modelNameField.getText());
                Brand brand = brandComboBox.getSelectionModel().getSelectedItem();
                brand.addModel(newModel);

                Session session = HibernateUtilities.getSessionFactory().openSession();
                session.beginTransaction();


                session.update(brand);

                session.getTransaction().commit();
                session.close();

                isNewModelAdded.setValue(true);

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

    public Model getNewModel()
    {
        return newModel;
    }
}
