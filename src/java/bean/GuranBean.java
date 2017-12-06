/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import ejb.GuranFacade;
import entity.Guran;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
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
    private List<Guran> list;
    @EJB
    GuranFacade gf;
    
    public String getNext(){
        list = get();
        return "output.xhtml";
    }
    
    public List<Guran> get(){
        return gf.get();
    }
    
    public void clear(){
            this.id = 0;
            this.name = null;
    }

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

    public List<Guran> getList() {
        return list;
    }

    public void setList(List<Guran> list) {
        this.list = list;
    }   
    
}
