package sample.controllers;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.query.Query;
import sample.Brand;
import sample.HibernateUtilities;
import sample.Model;

import java.net.URL;
import java.util.ResourceBundle;

public class ModelConfigurationController implements Initializable{

    private ObservableList<Model> tableData = FXCollections.observableArrayList();
    private ObservableList<String> brandsList = FXCollections.observableArrayList();
    private SimpleBooleanProperty isNewModelAdded = new SimpleBooleanProperty(false);
    private AddModelController addModelController = null;


    @FXML
    Button addModelButton;

    @FXML
    Button deleteButton;

    @FXML
    private TableView<Model> modelsView;

    private TableColumn<Model, Integer> idCol =
            new TableColumn<>("Id");

    private TableColumn<Model, String> modelCol =
            new TableColumn<>("Model");

    private TableColumn<Model, String> brandCol =
            new TableColumn<>("Brand");

    private void initTable()
    {
        Session session = HibernateUtilities.getSessionFactory().openSession();
        session.beginTransaction();

        ScrollableResults scrollableResults = session.createQuery("from Model").scroll(ScrollMode.FORWARD_ONLY);

        Model addModel;
        while(scrollableResults.next())
        {
            addModel = (Model) scrollableResults.get(0);
            tableData.add(addModel);
        }

        session.getTransaction().commit();
        session.close();

        modelsView.getItems().addAll(tableData);
    }

    private void initBrandsList()
    {
        Session session = HibernateUtilities.getSessionFactory().openSession();
        session.beginTransaction();

        ScrollableResults scrollableResults = session.createQuery("from Brand ").scroll(ScrollMode.FORWARD_ONLY);

        Brand addBrand;
        while(scrollableResults.next())
        {
            addBrand = (Brand) scrollableResults.get(0);
            brandsList.add(addBrand.getBrandName());
        }

        session.getTransaction().commit();
        session.close();
    }


    private void openAddWindow()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/views/AddModel.fxml"));
            fxmlLoader.setController(new AddModelController(isNewModelAdded));
            addModelController = fxmlLoader.getController();
            Scene scene = new Scene((Parent) fxmlLoader.load(), 400, 400);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Add Model");
            stage.setScene(scene);
            stage.show();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        modelsView.setEditable(true);

        GridPane gridPane = (GridPane) modelsView.getParent();

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setMinWidth(gridPane.getMinWidth()/3 - 5);
        idCol.setResizable(false);

        modelCol.setCellValueFactory(new PropertyValueFactory<>("modelName"));
        modelCol.setCellFactory(TextFieldTableCell.<Model> forTableColumn());
        modelCol.setResizable(false);
        modelCol.setMinWidth(gridPane.getMinWidth()/3);

        modelCol.setOnEditCommit((TableColumn.CellEditEvent<Model,String> event) ->{
            TablePosition<Model, String> pos = event.getTablePosition();

            String modelName = event.getNewValue();
            int row = pos.getRow();

            Model model = tableData.get(row);
            model.setModelName(modelName);
            modelsView.refresh();

            Session session = HibernateUtilities.getSessionFactory().openSession();
            session.beginTransaction();

            String hql = "UPDATE Model set modelName = :modelName "  +
                    "WHERE id = :model_id";
            Query query = session.createQuery(hql);
            query.setParameter("modelName", modelName);
            query.setParameter("model_id", row + 1);
            query.executeUpdate();

            session.getTransaction().commit();
            session.close();
        });

        initBrandsList();

        brandCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Model, String> param) {
                Model model = param.getValue();

                Session session = HibernateUtilities.getSessionFactory().openSession();
                session.beginTransaction();

                String hql ="from Brand where id=:brandId";
                Query query = session.createQuery(hql);
                query.setParameter("brandId", model.getBrand().getId());
                Brand brand = (Brand)query.getSingleResult();

                session.getTransaction().commit();
                session.close();

                return new SimpleStringProperty(brand.getBrandName());
            }
        });

        brandCol.setCellFactory(ComboBoxTableCell.forTableColumn(brandsList));
        brandCol.setResizable(false);
        brandCol.setMinWidth(gridPane.getMinWidth()/3);

        brandCol.setOnEditCommit((TableColumn.CellEditEvent<Model, String> event) -> {
            TablePosition<Model, String> pos = event.getTablePosition();

            String brandName = event.getNewValue();
            int row = pos.getRow();

            Model modelToUpdate = tableData.get(row);

            Session session = HibernateUtilities.getSessionFactory().openSession();
            session.beginTransaction();

            String hql = "from Brand where brandName=:brandName";
            Query query = session.createQuery(hql);
            query.setParameter("brandName", brandName);

            Brand newBrand = (Brand) query.getSingleResult();
            newBrand.addModel(modelToUpdate);
            //session.update(newBrand);
            /*hql = "update Brand set modelsList =:modelList " +
                    "where id =:brandId";

            query = session.createQuery(hql);
            query.setParameter("modelList", newBrand.getModelsList());
            query.setParameter("brandId", newBrand.getId());
           // query.executeUpdate();*/

            session.getTransaction().commit();
            session.close();

            modelsView.refresh();
        });

        modelsView.getColumns().addAll(idCol,modelCol,brandCol);

        initTable();

        addModelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                openAddWindow();
            }
        });

        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                int id = modelsView.getSelectionModel().getFocusedIndex();
                modelsView.getItems().remove(id);
                tableData.remove(id);
                modelsView.refresh();

                Session session = HibernateUtilities.getSessionFactory().openSession();
                session.beginTransaction();

                String hql = "delete from Model where id =:modelId";
                Query query = session.createQuery(hql);
                query.setParameter("modelId", id+1);
                query.executeUpdate();

                session.getTransaction().commit();
                session.close();
            }
        });

        isNewModelAdded.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue && addModelController.getNewModel() != null)
                {
                    Model newModel = addModelController.getNewModel();
                    tableData.add(newModel);
                    modelsView.getItems().add(newModel);
                    modelsView.refresh();
                    isNewModelAdded.setValue(false);
                }
            }
        });
    }
}
