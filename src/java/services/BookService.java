/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import models.Book;
import models.NewHibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author The user
 */
public class BookService {

    Transaction trx = null;

    public void addBook(Book b) {
        try {
            Session session = NewHibernateUtil.getSessionFactory().openSession();
            trx = session.beginTransaction();
            session.save(b);
            trx.commit();

        } catch (Exception e) {
            trx.rollback();
        }
    }

    public Book findBookById(int id) {
        Book b1 = null;
        try {
            Session session = NewHibernateUtil.getSessionFactory().openSession();
            b1 = (Book) session.get(Book.class, id);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return b1;
    }

}
