/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extension;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author glowo
 */
public class CsvReader {
    public void csvRead(String path){
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while((line = br.readLine()) != null){
                String[] data = line.split(",",0);
                
                for(String elem : data){
                    System.out.println(elem);
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
