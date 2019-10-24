/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediadorchat;

import mediadorchat.controllers.MainControllter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author pedro
 */
public class Cliente extends Colega {
    public Cliente(String nombre, String ip, int puerto, String ps) {

        this.puerto = puerto;
        this.nombre = nombre;
        this.ip = ip;
        this.pass = ps;
    }

    public void init() {
        try {
            Thread hilo = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        cliente = new Socket(ip, puerto);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        buffSalida = new DataOutputStream(cliente.getOutputStream());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        buffEntrada = new DataInputStream(cliente.getInputStream());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        buffSalida.writeUTF(nombre + "," + pass);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    String mesgIn = null;
                    try {
                        mesgIn = buffEntrada.readUTF();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    if (mesgIn.equals("Aceptado")) {
                        try {
                            MainCliente.changeToMainView();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        RecibirDatos();
                        EscribirDatos();
                    } else {
                        try {
                            cliente.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });
            hilo.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void Start(String n, String pass){
        Cliente cliente = new Cliente(n, "localhost", 9000, pass);
        cliente.init();
        MainControllter.setCliente(cliente);
    }

}
