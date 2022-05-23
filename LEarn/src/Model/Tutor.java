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

public class Tutor {
    
    public int tutor_id;
    public InputStream eligibility_proof;
    public double rating;
    public String validation_status;
    public String bank;
    public String rekening;
    public String namaRekening;
    
    public static String[] status = new String[]{"Valid", "non-Valid"};

    public Tutor() {
    }
    
    public Tutor(String bank, String rekening, String namaRekening) {
        this.bank = bank;
        this.rekening = rekening;
        this.namaRekening = namaRekening;
        this.validation_status = status[1];
    }
    
    public void DownloadFile(String path) throws IOException{
        byte[] buffer = new byte[eligibility_proof.available()];
        eligibility_proof.read(buffer);
            
        File fileDownload = new File(path);
        OutputStream outStream = new FileOutputStream(fileDownload);
        outStream.write(buffer);
    }
}
