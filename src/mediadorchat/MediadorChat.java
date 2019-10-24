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

/**
 *
 * @author pedro
 */
public class MediadorChat extends Mediator {

    
     public MediadorChat(int puerto){
          
          this.puerto = puerto;
     }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        MediadorChat servidor = new MediadorChat(9000);
        servidor.init();
        
    }
    
}
