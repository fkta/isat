package bean;

import ejb.GuranFacade;
import entity.Guran;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.http.Part;
import javax.validation.constraints.NotNull;

/**
 *
 * @author glowo
 */
@Named(value = "ngb")
@SessionScoped
public class NewGuranBean implements Serializable {
@NotNull
    private int id;
    private String name;
    private Part file;
    private String fileName;
    private Path path;
    private List<Guran> list;
    @EJB
    GuranFacade gf;
    
    public String getNext(){
        list = get();
        return "output.xhtml";
    }
    
    public String userAdd(){
        submit();
        csvRead();
        return "useradd.xhtml";
    }
    
    public String comit(){
        gf.create(list);
        return "successful.xhtml";
    }
    
    /* ファイルアップロード */
    public void submit(){
        if(file != null){
            /*this.fileName = "[fileName = " + fileName + "]";
            this.stdoutFile(this.file);*/
            try{
                fileName = file.getSubmittedFileName();
                /* ファイルパス指定 */
                path = Paths.get(System.getProperty("user.home"), "sotsuken","NetBeans","jpql","src","java","extension",fileName);
                /* ファイル取得 */
                InputStream in = file.getInputStream();
                /* ファイル保存 option = 上書き保存 */
                Files.copy(in, path, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("file_copy_successful");
                
                
                
                /* csvに記載された情報をdbに反映 */
                
            }catch(IOException e){
                System.out.println(e);
            }
        }
    }
    
    /* CSVを読み込む */
    public void csvRead(){
        try{
            System.out.println("csvRead Start");
            BufferedReader br = new BufferedReader(new FileReader(path.toString()));
            String line;
            while((line = br.readLine()) != null){
                String[] data = line.split(",",0);
                /*for(String elem : data){
                    System.out.println(elem);
                }*/
                int i = 0;
                
                System.out.println("csvRead loop");
                while(i < data.length){
                    System.out.println("csvRead loop start" + i + "回目");
                    Guran guran = new Guran();
                    guran.setId(Integer.parseInt(data[i]));
                    guran.setName(data[i+1]);
                    System.out.println("ID: "+guran.getId() + "NAME: " +guran.getName());
                    list.add(guran);
                    i = i + 2;
                }
            }
        }catch(IOException e){
            System.out.println(e);
            
        }
        System.out.println("csvRead Finish");
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Part getFile() {
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
