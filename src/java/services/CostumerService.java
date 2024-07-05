/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import models.Costumer;
import models.NewHibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author The user
 */
public class CostumerService {
    Transaction trx=null;
    public void addClient(Costumer c){
        try{
            Session session=NewHibernateUtil.getSessionFactory().openSession();
            trx=session.beginTransaction();
            session.save(c);
            trx.commit();
            
        }
        catch(Exception e){
            trx.rollback();
        }
    }
    public Costumer findCostumertById(int id){
        Costumer c1=null;
        try{
            Session session=NewHibernateUtil.getSessionFactory().openSession();
            c1 = (Costumer)session.get(Costumer.class, id);
        
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return c1; 
    }
    private Costumer getCostumerByName(String name){
        Costumer c1=null;
        try{
            Session session=NewHibernateUtil.getSessionFactory().openSession();
            String hql = "FROM Costumer WHERE name = :costumerName";
            Query query = session.createQuery(hql);
            query.setParameter("costumerName", name);
            c1= (Costumer) query.uniqueResult();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return c1;
    }
    public boolean isNameExist(String name){
        Costumer c1=this.getCostumerByName(name);
        
        if(c1!=null){
            return true;
        }
        return false;
    }
    public boolean isPasswordCorrect(String name,String password){
        Costumer c1=this.getCostumerByName(name);
        if (c1.getPassword().equals(password)){
            return true;
        }
        return false;
    }
    
}
