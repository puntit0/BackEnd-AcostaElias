/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Elias.Acosta.arg.Security.Controller;

import org.springframework.http.HttpStatus;


public class Mensaje {
    private String mensaje;
    
    //constructores

    public Mensaje(String el_nombre_es_obligatorio, HttpStatus BAD_REQUEST) {
    }

    public Mensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    //Getter y Stter

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
}
