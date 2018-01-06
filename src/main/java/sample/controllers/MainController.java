package sample.controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.HibernateUtilities;
import sample.controllers.AddRentalController;
import sample.controllers.ClientConfigurationController;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController  implements Initializable{

    @FXML
    private ImageView logo;

    @FXML
    private TreeView<String> tree;

    @FXML
    AnchorPane rootPane;


    private void initTreeView()
    {
        TreeItem<String> rentalOverview = new TreeItem<>("Rental Overview");

        TreeItem<String> addRental  = new TreeItem<>("Add Rental");
        TreeItem<String> rootTreeItem = new TreeItem<>();
        addRental.setExpanded(false);

        TreeItem<String> configuration  = new TreeItem<>("Configuration");
        configuration.getChildren().add(new TreeItem<>("Client"));
        configuration.getChildren().add(new TreeItem<>("Car"));
        configuration.getChildren().add(new TreeItem<>("Model"));
        configuration.getChildren().add(new TreeItem<>("Brand"));

        rootTreeItem.getChildren().add(rentalOverview);
        rootTreeItem.getChildren().add(addRental);
        rootTreeItem.getChildren().add(configuration);

        tree.setRoot(rootTreeItem);
        tree.setShowRoot(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image logoImg = new Image("Logo.PNG");
        logo.setImage(logoImg);
        initTreeView();

        tree.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                boolean isCorrectlyClicked = false;
                Stage primaryStage = (Stage)tree.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader();

                if(!tree.getSelectionModel().getSelectedItems().isEmpty()) {
                    switch (tree.getSelectionModel().getSelectedItem().getValue()) {
                        case "Rental Overview":
                            break;
                        case "Add Rental":
                            isCorrectlyClicked = true;
                            loader.setLocation(getClass().getResource("/views/AddRental.fxml"));
                            loader.setController(new AddRentalController());
                            primaryStage.setTitle("Add Rental");
                            break;
                        case "Client":
                            isCorrectlyClicked = true;
                            loader.setLocation(getClass().getResource("/views/ClientConfiguration.fxml"));
                            loader.setController(new ClientConfigurationController());
                            primaryStage.setTitle("Client Configuration");
                            break;
                        case "Car":
                            isCorrectlyClicked = true;
                            loader.setLocation(getClass().getResource("/views/CarConfiguration.fxml"));
                            loader.setController(new CarConfigurationController());
                            primaryStage.setTitle("Car Configuration");
                            break;
                        case "Model":
                            isCorrectlyClicked = true;
                            loader.setLocation(getClass().getResource("/views/ModelConfiguration.fxml"));
                            loader.setController(new ModelConfigurationController());
                            primaryStage.setTitle("Model Configuration");
                            break;
                        case "Brand":
                            isCorrectlyClicked = true;
                            loader.setLocation(getClass().getResource("/views/BrandConfiguration.fxml"));
                            loader.setController(new BrandConfigurationController());
                            primaryStage.setTitle("Brand Configuration");
                            break;
                    }

                    if (isCorrectlyClicked) {
                        try {
                            rootPane.getChildren().clear();
                            GridPane newPane = loader.load();
                            rootPane.getChildren().add(newPane);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }
}
