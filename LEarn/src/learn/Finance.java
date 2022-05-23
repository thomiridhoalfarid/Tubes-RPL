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
public class Finance {
    private String idPayment;
    private String Type;
    private long Nominal;
    private String UserEmail;

    public String getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(String idPayment) {
        this.idPayment = idPayment;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public long getNominal() {
        return Nominal;
    }

    public void setNominal(long Nominal) {
        this.Nominal = Nominal;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String UserEmail) {
        this.UserEmail = UserEmail;
    }

    public Finance(String idPayment, String Type, long Nominal, String UserEmail) {
        this.idPayment = idPayment;
        this.Type = Type;
        this.Nominal = Nominal;
        this.UserEmail = UserEmail;
    }
    
    
}
