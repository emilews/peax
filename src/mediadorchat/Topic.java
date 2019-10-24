/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediadorchat;

import java.util.Vector;

import static mediadorchat.Conexion.clientesConectados;

/**
 * @author pedro
 */
public class Topic {

    public Vector<Conexion> usuarios = new Vector();
    public String topicTitle;
    public String admin;


    public Vector<Conexion> getUserList() {
        return usuarios;
    }

    public void setUserList(Vector<Conexion> conexion) {
        this.usuarios = usuarios;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public void setAdministrador(String admin) {
        this.admin = admin;
    }

    public String getAdministrador() {
        return this.admin;
    }

    public void Publish(String msg, String user, Conexion con){
        for (int i = 0; i < usuarios.size(); i++) {
            if(con != usuarios.get(i)){
                usuarios.get(i).EnviarMensaje("["+ getTopicTitle() +"]<" + user + "> " + msg);
            }
        }
    }
}
