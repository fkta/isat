/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import ejb.GuranFacade;
import entity.Guran;
import extension.FileUpload;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.servlet.http.Part;
import javax.validation.constraints.*;

/**
 *
 * @author glowo
 */
@Named(value = "gb")
@RequestScoped
public class GuranBean {
    @NotNull
    private int id;
    private String name;
    private Part file;
    private List<Guran> list;
    @EJB
    GuranFacade gf;
    
    public String getNext(){
        list = get();
        return "output.xhtml";
    }
    
    /* ファイルアップロード */
    public void submit(){
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
    
    public List<Guran> get(){
        return gf.get();
    }
    
    public void clear(){
            this.id = 0;
            this.name = null;
    }

    /* ゲッター,セッター */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Part getFile() {
        System.out.println("入りーの");
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }
    
    public List<Guran> getList() {
        return list;
    }

    public void setList(List<Guran> list) {
        this.list = list;
    }
    
}
