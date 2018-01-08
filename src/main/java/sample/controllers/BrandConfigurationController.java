package sample.controllers;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.query.Query;
import sample.Brand;
import sample.Client;
import sample.HibernateUtilities;


import java.net.URL;
import java.util.ResourceBundle;

public class BrandConfigurationController implements Initializable{

    private ObservableList<Brand> tableData = FXCollections.observableArrayList();
    private SimpleBooleanProperty isNewBrandAdded = new SimpleBooleanProperty(false);
    private AddBrandController addBrandController = null;

    @FXML
    Button addBrandButton;

    @FXML
    Button deleteButton;

    @FXML
    private TableView<Brand> brandsView;

    private TableColumn<Brand, Integer> idCol =
            new TableColumn<>("Id");

    private TableColumn<Brand, String> brandCol =
            new TableColumn<>("Brand");


    private void initTable()
    {
        Session session = HibernateUtilities.getSessionFactory().openSession();
        session.beginTransaction();

        ScrollableResults scrollableResults = session.createQuery("from Brand ").scroll(ScrollMode.FORWARD_ONLY);

        Brand addBrand;
        while(scrollableResults.next())
        {
            addBrand = (Brand) scrollableResults.get(0);
            tableData.add(addBrand);
        }

        session.getTransaction().commit();
        session.close();

        brandsView.getItems().addAll(tableData);
    }

    private void openAddWindow()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/views/AddBrand.fxml"));
            fxmlLoader.setController(new AddBrandController(isNewBrandAdded));
            Scene scene = new Scene((Parent) fxmlLoader.load(), 400, 200);
            Stage stage = new Stage();
            addBrandController = fxmlLoader.getController();
            stage.setResizable(false);
            stage.setTitle("Add Brand");
            stage.setScene(scene);
            stage.show();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        brandsView.setEditable(true);

        GridPane stage = (GridPane)brandsView.getParent();

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setMinWidth(stage.getMinWidth()/2 - 5);
        idCol.setResizable(false);

        brandCol.setCellValueFactory(new PropertyValueFactory<>("brandName"));
        brandCol.setCellFactory(TextFieldTableCell.<Brand> forTableColumn());
        brandCol.setResizable(false);
        brandCol.setMinWidth(stage.getMinWidth()/2);

        brandCol.setOnEditCommit((TableColumn.CellEditEvent<Brand,String> event) ->{
            TablePosition<Brand, String> pos = event.getTablePosition();

            String brandName = event.getNewValue();
            int row = pos.getRow();

            Brand brand = tableData.get(row);
            brand.setBrandName(brandName);
            brandsView.refresh();

            Session session = HibernateUtilities.getSessionFactory().openSession();
            session.beginTransaction();

            String hql = "UPDATE Brand set brandName = :brandName "  +
                    "WHERE id = :brand_id";
            Query query = session.createQuery(hql);
            query.setParameter("brandName", brandName);
            query.setParameter("brand_id", row + 1);
            query.executeUpdate();

            session.getTransaction().commit();
            session.close();
        });

        brandsView.getColumns().addAll(idCol,brandCol);

        initTable();

        addBrandButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                openAddWindow();
            }
        });

        isNewBrandAdded.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue && addBrandController.getNewBrand() != null)
                {
                    Brand newBramd = addBrandController.getNewBrand();
                    Session session = HibernateUtilities.getSessionFactory().openSession();
                    session.beginTransaction();
                    session.save(newBramd);
                    session.getTransaction().commit();
                    session.close();
                    tableData.add(newBramd);
                    brandsView.getItems().add(newBramd);
                    brandsView.refresh();
                    isNewBrandAdded.setValue(false);
                }
            }
        });

        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int id = brandsView.getSelectionModel().getFocusedIndex();
                brandsView.getItems().remove(id);
                tableData.remove(id);
                brandsView.refresh();

                Session session = HibernateUtilities.getSessionFactory().openSession();
                session.beginTransaction();

                String hql = "delete from Brand where id =:brandId";
                Query query = session.createQuery(hql);
                query.setParameter("brandId", id+1);
                query.executeUpdate();

                session.getTransaction().commit();
                session.close();

            }
        });
    }
}
