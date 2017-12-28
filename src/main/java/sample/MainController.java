package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController  implements Initializable{

    @FXML
    private ImageView logo;

    @FXML
    private TreeView<String> tree;




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

                switch (tree.getSelectionModel().getSelectedItem().getValue())
                {
                    case "Rental Overview":
                        break;
                    case "Add Rental":
                        isCorrectlyClicked = true;
                        loader.setLocation(getClass().getResource("/AddRental.fxml"));
                        loader.setController(new AddRentalController());
                        primaryStage.setTitle("Add Rental");
                        break;
                    case "Client":
                        break;
                    case "Car":
                        break;
                    case "Model":
                        break;
                    case "Brand":
                        break;
                }

                if(isCorrectlyClicked)
                {
                    try {
                        Parent root = loader.load();
                        Scene scene = new Scene(root, 800, 600);
                        scene.getStylesheets().add("/WindowStyle.css");
                        primaryStage.setScene(scene);
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });

    }
}
