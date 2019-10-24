package mediadorchat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class MainCliente extends Application  {
    public static Stage primaryStage;
    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("views/Login.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Chat");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    public static void changeToMainView() throws IOException {
        Parent root = FXMLLoader.load(MainCliente.class.getResource("views/Main.fxml"));
        primaryStage.getScene().setRoot(root);
    }
    public static void newWindowForChat(String topic) throws IOException {
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                ChatDirecto cd = new ChatDirecto(topic);
                Stage s = new Stage();
                try {
                    cd.start(s);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        hilo.start();
    }
    public static void error(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText("No se pudo iniciar sesión.");
        alert.setContentText("Contraseña inválida o usuario no existente.");
        alert.showAndWait();
    }


    private static class ChatDirecto extends Application{
        private String topic;
        public ChatDirecto(String topic){
            this.topic = topic;
        }

        @Override
        public void start(Stage primaryStage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("views/Chat.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle(topic);
            primaryStage.show();
        }
    }
}
