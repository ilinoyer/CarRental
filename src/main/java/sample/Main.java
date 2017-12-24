package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.concurrent.ScheduledExecutorService;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainWindow.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Car Rental");
        final MainController maincontroller = loader.getController();
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(600);
        primaryStage.show();




    }


    public static void main(String[] args) {

        Session session = HibernateUtilities.getSessionFactory().openSession();

        session.beginTransaction();
        Brand nissan = new Brand("Nissan");
        nissan.addModel(new Model("Micra"));
        nissan.addModel(new Model("Almera"));
        nissan.addModel(new Model("Primera p11"));
        nissan.addModel(new Model("Primera p12"));
        session.save(nissan);
        session.getTransaction().commit();

        session.beginTransaction();
        Client client1 = new Client("Adam", "Nowak", 12345, 12345, new Address("KRK", "22-456", "PL", "3A"));
        Client client2 = new Client("Marian", "Nowak", 12345, 12345, new Address("PZ", "23-567", "PL", "7b"));
        session.save(client1);
        session.save(client2);
        session.getTransaction().commit();
        session.close();
        HibernateUtilities.shutdown();

        launch(args);
    }
}
