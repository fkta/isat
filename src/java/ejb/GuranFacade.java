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
    
    public void create(List<Guran> guran){
        System.out.println("hello");
        int i = 1;
        for (Guran data : guran) {
            em.persist(data);
            System.out.println(i+"件目のデータ登録完了");
        }
    }

    public GuranFacade() {
        super(Guran.class);
    }
    
}
