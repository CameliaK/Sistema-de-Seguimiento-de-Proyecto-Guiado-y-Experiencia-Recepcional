package sspger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;




public class SSPGER extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("vistas/FXMLInicioSesion.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("vistas/FXMLCrearAnteproyecto.fxml"));

        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Inicio Sesión");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
