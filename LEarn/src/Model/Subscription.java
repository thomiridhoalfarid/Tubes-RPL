/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author ASUS
 */
public class Subscription {
    private String name;
    private double price;
    private double validity_periode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getValidity_periode() {
        return validity_periode;
    }

    public void setValidity_periode(double validity_periode) {
        this.validity_periode = validity_periode;
    }

    public Subscription() {
    }
    
    
}
