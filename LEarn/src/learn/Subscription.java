/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learn;

/**
 *
 * @author ASUS
 */
public class Subscription {
    private String SubscriptionID;
    private String Name;
    private long Price;

    public String getSubscriptionID() {
        return SubscriptionID;
    }

    public void setSubscriptionID(String SubscriptionID) {
        this.SubscriptionID = SubscriptionID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public long getPrice() {
        return Price;
    }

    public void setPrice(long Price) {
        this.Price = Price;
    }

    public Subscription(String SubscriptionID, String Name, long Price) {
        this.SubscriptionID = SubscriptionID;
        this.Name = Name;
        this.Price = Price;
    }
    
    
}
