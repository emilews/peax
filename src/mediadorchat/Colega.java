/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediadorchat;

import mediadorchat.controllers.MainControllter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * @author pedro
 */
public abstract class Colega {

    public Socket cliente;
    public DataOutputStream buffSalida;
    public DataInputStream buffEntrada;
    public DataInputStream teclado;
    public String nombre;
    public String ip;
    public int puerto;
    public String pass;

    public void RecibirDatos() {
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        String mesgIn = buffEntrada.readUTF();
                        if(mesgIn.contains("topics:>")){
                            MainControllter.updateTopicList(mesgIn);
                        }
                        if(mesgIn.contains("[Broadcast]")){
                            MainControllter.newMsg(mesgIn);
                        }
                        System.out.println(mesgIn);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        hilo.start();
    }

    public void EnviarDatos(String msg) {
        try {
            buffSalida.writeUTF("<" + nombre + "> " + msg);
            buffSalida.flush();
        } catch (Exception e) {
        }
        ;
    }

    public void EscribirDatos() {
        try {
            String line = "";
            while (!line.equals(".bye")) {
                line = teclado.readLine();
                buffSalida.writeUTF(line);
                buffSalida.flush();
            }
        } catch (Exception e) {
        }
    }
}
