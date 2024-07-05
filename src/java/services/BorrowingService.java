/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import models.Book;
import models.Borrowing;
import models.Costumer;
import models.Item;
import models.NewHibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author The user
 */
public class BorrowingService {
    Transaction trx=null;
    public void addBorrowing(Borrowing b){
        try{
            Session session=NewHibernateUtil.getSessionFactory().openSession();
            trx=session.beginTransaction();
            session.save(b);
            trx.commit();
            
        }
        catch(Exception e){
            trx.rollback();
        }
    }
    public boolean returnBook(Item item,Costumer costumer){
        try{
            Session session=NewHibernateUtil.getSessionFactory().openSession();
            trx=session.beginTransaction();
            Borrowing borrowingToUpdate=null;
            String hql = "FROM Borrowing WHERE ITEM_ID=:item_id and CUSTOMER_ID=:cus_id and ISRETURNED=false";
            Query query = session.createQuery(hql);
            query.setParameter("item_id", item.getId());
            query.setParameter("cos_id", costumer.getId());
            borrowingToUpdate= (Borrowing) query.uniqueResult();
            if(borrowingToUpdate==null){
                trx.commit();
                return false;
            }
                
            borrowingToUpdate.setReturned(true);
            session.update(borrowingToUpdate);
            trx.commit();
            
        }
        catch(Exception e){
            e.printStackTrace();
            trx.rollback();
        }
        return true;
    }
}

