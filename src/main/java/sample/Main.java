package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainWindow.fxml"));
        loader.setController(new MainController());
        Parent root = loader.load();
        primaryStage.setTitle("Car Rental");
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add("/WindowStyle.css");
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(800);
        primaryStage.setResizable(false);
        primaryStage.show();




    }


    public static void main(String[] args) {
        /*
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

        session.beginTransaction();
        Car car1 = new Car(nissan.getModelByIndex(0),"RPZ", 1996, "PL", "adv", 1550);
        Car car2 = new Car(nissan.getModelByIndex(1),"RZ", 1999, "PL", "adv", 1950);
        session.save(car1);
        session.save(car2);
        session.getTransaction().commit();

        session.beginTransaction();
        Rental rent1 = new Rental(car1, client1, new Date(), new Date(), 1000);
        Rental rent2 = new Rental(car2, client2, new Date(), new Date(), 1000);
        session.save(rent1);
        session.save(rent2);
        session.getTransaction().commit();
        session.close();
        HibernateUtilities.shutdown();
        */
        launch(args);
    }
}
