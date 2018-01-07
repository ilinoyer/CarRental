package sample.controllers;

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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.query.Query;
import sample.Client;
import sample.HibernateUtilities;


import java.net.URL;
import java.util.ResourceBundle;

public class ClientConfigurationController implements Initializable{

    private ObservableList<Client> tableData = FXCollections.observableArrayList();

    @FXML
    Button addClientButton;

    @FXML
    TableView<Client> clientsView;

    TableColumn<Client, Integer> idCol =
            new TableColumn<>("Id");

    TableColumn<Client, String> firstNameCol =
            new TableColumn<>("First Name");

    TableColumn<Client, String> lastNameCol =
            new TableColumn<>("Last Name");

    TableColumn<Client, Number> idCardNumCol =
            new TableColumn<>("ID Card Number");

    TableColumn<Client, Number> drivingLicenseNumCol =
            new TableColumn<>("DL Number");


    private void openAddWindow()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/views/AddClient.fxml"));
            fxmlLoader.setController(new AddClientController());
            Scene scene = new Scene((Parent) fxmlLoader.load(), 400, 400);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Add Client");
            stage.setScene(scene);
            stage.show();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void initTable()
    {
        Session session = HibernateUtilities.getSessionFactory().openSession();
        session.beginTransaction();

        ScrollableResults scrollableResults = session.createQuery("from Client").scroll(ScrollMode.FORWARD_ONLY);

        Client addClient;
        while(scrollableResults.next())
        {
            addClient = (Client)scrollableResults.get(0);
            tableData.add(addClient);
        }

        session.getTransaction().commit();
        session.close();

        clientsView.getItems().addAll(tableData);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        clientsView.setEditable(true);

        GridPane stage = (GridPane)clientsView.getParent();

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setMinWidth(stage.getMinWidth()/5);
        idCol.setResizable(false);

        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        firstNameCol.setCellFactory(TextFieldTableCell.<Client> forTableColumn());
        firstNameCol.setResizable(false);
        firstNameCol.setMinWidth(stage.getMinWidth()/5);

        firstNameCol.setOnEditCommit((TableColumn.CellEditEvent<Client,String> event) ->{
            TablePosition<Client, String> pos = event.getTablePosition();

            String firstName = event.getNewValue();
            int row = pos.getRow();

            Client person = tableData.get(row);
            person.setFirstName(firstName);
            clientsView.refresh();

            Session session = HibernateUtilities.getSessionFactory().openSession();
            session.beginTransaction();

            String hql = "UPDATE Client set firstName = :firstName "  +
                    "WHERE id = :client_id";
            Query query = session.createQuery(hql);
            query.setParameter("firstName", firstName);
            query.setParameter("client_id", row + 1);
            query.executeUpdate();

            session.getTransaction().commit();
            session.close();
        });

        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        lastNameCol.setCellFactory(TextFieldTableCell.<Client> forTableColumn());
        lastNameCol.setResizable(false);
        lastNameCol.setMinWidth(stage.getMinWidth()/5);

        lastNameCol.setOnEditCommit((TableColumn.CellEditEvent<Client,String> event) ->{
            TablePosition<Client, String> pos = event.getTablePosition();

            String lastName = event.getNewValue();
            int row = pos.getRow();

            Client person = tableData.get(row);
            person.setLastName(lastName);
            clientsView.refresh();

            Session session = HibernateUtilities.getSessionFactory().openSession();
            session.beginTransaction();

            String hql = "UPDATE Client set firstName = :lastName "  +
                    "WHERE id = :client_id";
            Query query = session.createQuery(hql);
            query.setParameter("lastName", lastName);
            query.setParameter("client_id", row + 1);
            query.executeUpdate();

            session.getTransaction().commit();
            session.close();
        });


        idCardNumCol.setCellValueFactory(new PropertyValueFactory<>("identityCardNumber"));
        idCardNumCol.setCellFactory(TextFieldTableCell.<Client, Number>forTableColumn(new NumberStringConverter()));
        idCardNumCol.setResizable(false);
        idCardNumCol.setMinWidth(stage.getMinWidth()/5);

        idCardNumCol.setOnEditCommit((TableColumn.CellEditEvent<Client,Number> event) ->{
            TablePosition<Client, Number> pos = event.getTablePosition();

            Number idCardNumber = event.getNewValue();
            int row = pos.getRow();

            Client person = tableData.get(row);
            person.setIdentityCardNumber(idCardNumber.intValue());
            clientsView.refresh();

            Session session = HibernateUtilities.getSessionFactory().openSession();
            session.beginTransaction();

            String hql = "UPDATE Client set identityCardNumber = :identityCardNumber "  +
                    "WHERE id = :client_id";
            Query query = session.createQuery(hql);
            query.setParameter("identityCardNumber", idCardNumber.intValue());
            query.setParameter("client_id", row + 1);
            query.executeUpdate();

            session.getTransaction().commit();
            session.close();
        });

        drivingLicenseNumCol.setCellValueFactory(new PropertyValueFactory<>("drivingLicenseNumber"));
        drivingLicenseNumCol.setCellFactory(TextFieldTableCell.<Client, Number>forTableColumn(new NumberStringConverter()));
        drivingLicenseNumCol.setResizable(false);
        drivingLicenseNumCol.setMinWidth(stage.getMinWidth()/5);


        drivingLicenseNumCol.setOnEditCommit((TableColumn.CellEditEvent<Client,Number> event) ->{
            TablePosition<Client, Number> pos = event.getTablePosition();

            Number drivingLicenseNumber = event.getNewValue();
            int row = pos.getRow();

            Client person = tableData.get(row);
            person.setDrivingLicenseNumber(drivingLicenseNumber.intValue());
            clientsView.refresh();

            Session session = HibernateUtilities.getSessionFactory().openSession();
            session.beginTransaction();

            String hql = "UPDATE Client set identityCardNumber = :drivingLicenseNumber "  +
                    "WHERE id = :client_id";
            Query query = session.createQuery(hql);
            query.setParameter("drivingLicenseNumber", drivingLicenseNumber.intValue());
            query.setParameter("client_id", row + 1);
            query.executeUpdate();

            session.getTransaction().commit();
            session.close();
        });

        clientsView.getColumns().addAll(idCol, firstNameCol, lastNameCol, idCardNumCol, drivingLicenseNumCol);

        initTable();

        addClientButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                openAddWindow();
            }
        });
    }
}
