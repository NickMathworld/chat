/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author NickMadworld
 */

import java.io.Serializable;
import java.net.InetAddress;

public class mensaje implements Serializable{
    private int tipo = -1;

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getMsj() {
        return msj;
    }

    public void setMsj(String msj) {
        this.msj = msj;
    }
    private String origen = null;
    private String destino = null;
    private String msj = null;
    // Constructor para mensajes de tipo 0
    public mensaje(int t, String origen){
        tipo = t;
        this.origen = origen;
    }
   // Constructor para mensajes de tipo 1 y 2
   // Constructor de tipo 1: Para mensajes de respuesta
   // Constructor de tipo 2: Para mensajes para todos
    public mensaje(int t, String origen, String destino){
        tipo =  t;
        this.origen = origen;
        this.destino = destino;
    }
    // Constructor para mensaje de tipo 3
    // Mensajes de tipo privado
    public mensaje(int t, String origen, String destino, String msj){
        tipo = t;
        this.origen = origen;
        this.destino = destino;
        this.msj = msj;
    }
    
}
