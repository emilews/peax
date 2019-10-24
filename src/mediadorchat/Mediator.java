/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediadorchat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static mediadorchat.Conexion.topics;

/**
 * @author pedro
 */
public abstract class Mediator {
    public ServerSocket server;
    public int puerto = 9000;
    public List<Conexion> conexiones = new ArrayList<Conexion>();
    public List<Usuario> users = new ArrayList<>();

    public void init() {
        Socket socket;
        users.add(new Usuario("Aileen","123"));
        users.add(new Usuario("Emilio", "123"));
        try {
            Topic broadcast = new Topic();
            broadcast.setTopicTitle("Broadcast");
            topics.add(broadcast);
            server = new ServerSocket(puerto);
            System.out.println("Esperando peticiones por el puerto " + puerto);
            while (true) {
                socket = server.accept();
                DataInputStream buffEntrada = new DataInputStream(socket.getInputStream());
                DataOutputStream buffSalida = new DataOutputStream(socket.getOutputStream());
                String username = buffEntrada.readUTF();
                for(int i = 0; i < users.size(); i++){
                    if(username.split(",")[0].equals(users.get(i).getNombre())){
                        if(username.split(",")[1].equals(users.get(1).getPass())){
                            Conexion conexion = new Conexion(socket, buffEntrada, buffSalida, username.split(",")[0]);
                            conexion.start();
                            conexiones.add(conexion);
                            buffSalida.writeUTF("Aceptado");
                            buffSalida.flush();
                        }else {
                            socket.close();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class Usuario {
        private String nombre;
        private String pass;
        public Usuario(String n, String p){
            this.nombre = n;
            this.pass = p;
        }
        public String getNombre(){
            return this.nombre;
        }
        public String getPass(){
            return this.pass;
        }
    }
}
