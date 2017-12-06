/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Guran;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author glowo
 */
@Stateless
public class GuranFacade extends AbstractFacade<Guran> {

    @PersistenceContext(unitName = "jpqlPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<Guran> get(){
        TypedQuery<Guran> query = em.createNamedQuery("Guran.findAll",Guran.class);
        query.setFirstResult(0);
        query.setMaxResults(10);
        return query.getResultList();
    }

    public GuranFacade() {
        super(Guran.class);
    }
    
}
