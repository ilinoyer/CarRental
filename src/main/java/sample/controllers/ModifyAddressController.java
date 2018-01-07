package sample.controllers;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Address;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifyAddressController implements Initializable {

    private Address addressToModify;
    private SimpleBooleanProperty isChanged;

    @FXML
    private TextField countryField;

    @FXML
    private TextField townField;

    @FXML
    private TextField postCodeField;

    @FXML
    private TextArea addressField;

    @FXML
    private Button cancelButton;

    @FXML
    private Button modifyButton;

    public ModifyAddressController(Address addressToModify, SimpleBooleanProperty isChanged) {
        this.addressToModify = addressToModify;
        this.isChanged = isChanged;
    }

    private void initView()
    {
        countryField.setText(addressToModify.getCountry());
        townField.setText(addressToModify.getTownName());
        postCodeField.setText(addressToModify.getPostCode());
        addressField.setText(addressToModify.getStreetAddress());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initView();

        modifyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addressToModify.setCountry(countryField.getText());
                addressToModify.setTownName(townField.getText());
                addressToModify.setPostCode(postCodeField.getText());
                addressToModify.setStreetAddress(addressField.getText());
                isChanged.setValue(true);
                Stage stage = (Stage)modifyButton.getScene().getWindow();
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
}
