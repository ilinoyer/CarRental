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
import javafx.stage.WindowEvent;
import javafx.util.converter.NumberStringConverter;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.query.Query;
import sample.Address;
import sample.Client;
import sample.HibernateUtilities;


import java.net.URL;
import java.util.ResourceBundle;

public class ClientConfigurationController implements Initializable{

    private ObservableList<Client> tableData = FXCollections.observableArrayList();

    @FXML
    Button addClientButton;

    @FXML
    Button deleteButton;

    @FXML
    private TableView<Client> clientsView;

    private TableColumn<Client, Integer> idCol =
            new TableColumn<>("Id");

    private TableColumn<Client, String> firstNameCol =
            new TableColumn<>("First Name");

    private TableColumn<Client, String> lastNameCol =
            new TableColumn<>("Last Name");

    private TableColumn<Client, Number> idCardNumCol =
            new TableColumn<>("ID Card Number");

    private TableColumn<Client, Number> drivingLicenseNumCol =
            new TableColumn<>("DL Number");

    private TableColumn addressCol =
            new TableColumn<>("Address");

    SimpleBooleanProperty isNewClientAdded = new SimpleBooleanProperty(false);
    private AddClientController addClientController = null;


    private void openAddWindow(Client newClient)
    {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/views/AddClient.fxml"));
            fxmlLoader.setController(new AddClientController(isNewClientAdded));
            Scene scene = new Scene((Parent) fxmlLoader.load(), 400, 400);
            Stage stage = new Stage();
            addClientController = fxmlLoader.getController();
            stage.setResizable(false);
            stage.setTitle("Add Client");
            stage.setScene(scene);
            stage.showAndWait();

            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    System.out.println("asadasdadasd");
                }
            });
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
        idCol.setMinWidth(stage.getMinWidth()/6 - 5);
        idCol.setResizable(false);

        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        firstNameCol.setCellFactory(TextFieldTableCell.<Client> forTableColumn());
        firstNameCol.setResizable(false);
        firstNameCol.setMinWidth(stage.getMinWidth()/6);

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
        lastNameCol.setMinWidth(stage.getMinWidth()/6);

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
        idCardNumCol.setMinWidth(stage.getMinWidth()/6);

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
        drivingLicenseNumCol.setMinWidth(stage.getMinWidth()/6);


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

        addressCol.setCellValueFactory(c-> new SimpleBooleanProperty(true));
        addressCol.setCellFactory(c->new CellButton());
        addressCol.setResizable(false);
        addressCol.setMinWidth(stage.getMinWidth()/6);


        clientsView.getColumns().addAll(idCol, firstNameCol, lastNameCol, idCardNumCol, drivingLicenseNumCol, addressCol);

        initTable();

        addClientButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Client newClient = new Client();
                openAddWindow(newClient);

            }
        });

        isNewClientAdded.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

                if(newValue && addClientController != null)
                {
                    Client newClient = addClientController.getNewClient();
                    Session session = HibernateUtilities.getSessionFactory().openSession();
                    session.beginTransaction();
                    session.save(newClient);
                    session.getTransaction().commit();
                    session.close();
                    tableData.add(newClient);
                    clientsView.getItems().add(newClient);
                    clientsView.refresh();
                    isNewClientAdded.setValue(false);
                }
            }
        });

        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int id = clientsView.getSelectionModel().getFocusedIndex();
                clientsView.getItems().remove(id);
                tableData.remove(id);
                clientsView.refresh();

                Session session = HibernateUtilities.getSessionFactory().openSession();
                session.beginTransaction();

                String hql = "delete from Client where id =:clientId";
                Query query = session.createQuery(hql);
                query.setParameter("clientId", id+1);
                query.executeUpdate();

                session.getTransaction().commit();
                session.close();

            }
        });

    }

    private class CellButton extends TableCell<Client, Boolean>{

        private final Button moreButton = new Button("More");
        private SimpleBooleanProperty isChanged = new SimpleBooleanProperty(false);

        public CellButton()
        {
            moreButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    Session session = HibernateUtilities.getSessionFactory().openSession();
                    session.beginTransaction();

                    int clientId = CellButton.super.getIndex() + 1;
                    String hql = "select c.address from Client c where c.id = :clientId";
                    Query query = session.createQuery(hql);
                    query.setParameter("clientId",clientId );
                    Address addressToModify = (Address)query.getSingleResult();

                    session.getTransaction().commit();
                    session.close();
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("/views/ModifyAddress.fxml"));
                        fxmlLoader.setController(new ModifyAddressController(addressToModify, isChanged));
                        Scene scene = new Scene((Parent) fxmlLoader.load(), 400, 400);
                        Stage stage = new Stage();
                        stage.setResizable(false);
                        stage.setTitle("Modify Address");
                        stage.setScene(scene);
                        stage.show();
                    }catch(Exception e)
                    {
                        e.printStackTrace();
                    }

                    isChanged.addListener(new ChangeListener<Boolean>() {
                        @Override
                        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                            if(isChanged.getValue())
                            {
                                Session session = HibernateUtilities.getSessionFactory().openSession();
                                session.beginTransaction();

                                String hql = "update Address set townName =:townName," +
                                        "postCode =:postCode," +
                                        "country =:country," +
                                        "streetAddress =:streetAddress " +
                                        "WHERE id =:id";
                                Query query = session.createQuery(hql);
                                query.setParameter("townName", addressToModify.getTownName());
                                query.setParameter("postCode", addressToModify.getPostCode());
                                query.setParameter("country", addressToModify.getCountry());
                                query.setParameter("streetAddress", addressToModify.getStreetAddress());
                                query.setParameter("id", addressToModify.getId());
                                query.executeUpdate();

                                session.getTransaction().commit();
                                session.close();
                                isChanged.setValue(false);
                            }
                        }
                    });
                }
            });

        }

        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
                setGraphic(moreButton);
            }
        }
    }

}
