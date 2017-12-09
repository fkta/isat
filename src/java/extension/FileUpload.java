/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extension;

//import java.io.BufferedInputStream;
//import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
//import javax.enterprise.context.*;
//import javax.inject.Named;
import javax.servlet.http.Part;

//@Named(value = "fu")
//@RequestScoped
public class FileUpload {
//    private Part file;
//    private String fileName;
    
    public void submit(Part file){
        if(file != null){
            /*this.fileName = "[fileName = " + fileName + "]";
            this.stdoutFile(this.file);*/
            String fileName;
            try{
                fileName = file.getSubmittedFileName();
                /* ファイルパス指定 */
                Path path = Paths.get(System.getProperty("user.home"), "sotsuken","NetBeans","jpql","src","java","extension",fileName);
                /* ファイル取得 */
                InputStream in = file.getInputStream();
                /* ファイル保存 option = 上書き保存 */
                Files.copy(in, path, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("file_copy_successful");
            }catch(IOException e){
                System.out.println(e);
            }
        }
    }
    
    /*private void stdoutFile(Part targetFile){
        try(
            InputStream is = targetFile.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            InputStreamReader isr = new InputStreamReader(bis,Charset.forName("UTF-8"));
            BufferedReader br = new BufferedReader(isr)){
            
            String line = null;
            while((line = br.readLine()) != null){
                System.out.println(line);
            }
        }catch(IOException e){
            System.out.println(e);
        }
    }*/
    
}
