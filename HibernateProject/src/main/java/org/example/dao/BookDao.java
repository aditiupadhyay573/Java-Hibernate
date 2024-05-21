package org.example.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query; // Import the correct Query class
import org.example.utility.HibernateUtil;
import org.example.entity.Books;

public class BookDao {

    public void saveBook(String title, String author, double price) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Books book = new Books();
            book.setTitle(title);
            book.setAuthor(author);
            book.setPrice(price);

            session.save(book);
            transaction.commit();
            System.out.println("Records inserted successfully");
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<Books> getBooks() {
        Session session = HibernateUtil.getSession();
        Query<Books> query = session.createQuery("from Books", Books.class); // Correct query
        List<Books> books = query.list();
        session.close();
        return books;
    }

    public int updateBook(Books b) {
        if (b.getId() <= 0) return 0;
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        String hql = "update Books set title = :title, author = :author, price = :price where id = :id"; // Correct query
        Query query = session.createQuery(hql);
        query.setParameter("id", b.getId());
        query.setParameter("title", b.getTitle());
        query.setParameter("author", b.getAuthor());
        query.setParameter("price", b.getPrice());
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();
        return rowCount;
    }

    public int deleteBook(int id) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        String hql = "delete from Books where id = :id"; // Correct query
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();
        return rowCount;
    }
}
