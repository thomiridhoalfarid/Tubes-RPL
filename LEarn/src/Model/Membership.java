/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;

/**
 *
 * @author ASUS
 */
public class Membership {
    private String validation_status;
    private Date start_date;
    private Date expired_date;
    public InputStream payment_proof;
    
    public int subs_id;
    
    public static String[] validation = new String[]{"Valid", "Non-Valid"};

    public String getValidation_status() {
        return validation_status;
    }

    public void setValidation_status(String validation_status) {
        this.validation_status = validation_status;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getExpired_date() {
        return expired_date;
    }

    public void setExpired_date(Date expired_date) {
        this.expired_date = expired_date;
    }

    public Membership() {
        validation_status = validation[1];
        java.time.LocalDate local = java.time.LocalDate.now();
        start_date = new Date(local.getYear(), local.getMonthValue(), local.getDayOfMonth());
    }
    
    public void DownloadFile(String path) throws IOException{
        byte[] buffer = new byte[payment_proof.available()];
        payment_proof.read(buffer);
            
        File fileDownload = new File(path);
        OutputStream outStream = new FileOutputStream(fileDownload);
        outStream.write(buffer);
    }
    
}
