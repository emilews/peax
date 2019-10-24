package mediadorchat.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import mediadorchat.Cliente;

import java.util.Timer;
import java.util.TimerTask;


public class MainControllter {
    static Cliente cliente = null;
    @FXML
    public static TextField globalChat;
    @FXML
    public static ListView<String> topicsList;
    @FXML
    public TextField textoEnviar;
    @FXML
    public Button enviarButton;


    @FXML
    public void enviarTexto(){
        if(!textoEnviar.getText().trim().isEmpty()){
            cliente.EnviarDatos("enviar -m '"+textoEnviar.getText()+"' -t 'Broadcast'");
            textoEnviar.clear();
        }
    }

    public static void updateTopicList(String newTopics){
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                String[] tops = newTopics.split(",");
                String[] f = tops[0].split(">");
                for(int i = 1; i<tops.length; i++){
                    topicsList.getItems().removeAll(topicsList.getItems());
                    topicsList.getItems().addAll(tops[i]);
                }
                topicsList.getItems().add(f[1]);
            }
        });
        hilo.start();
    }
    static class SayHello extends TimerTask {
        public void run() {
            cliente.EnviarDatos("topics -l");
        }
    }

    public static void setCliente(Cliente c){
        Timer timer = new Timer();
        timer.schedule(new SayHello(), 0, 5000);
        cliente = c;
    }


    public static void newMsg(String msg){
        globalChat.setText();
    }
}
