package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class AddRentalController implements Initializable {

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
    }
}
