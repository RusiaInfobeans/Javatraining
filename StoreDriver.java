package day11.Task;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class StoreDriver {
    public static void main(String[] args) {
        insertIntial();
        //insertMultiple();
       // remove();
    }
    private static void remove() {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Store store = session.get(Store.class, 1);
        session.remove(store);
        transaction.commit();
        session.close();
    }
    private static void insertMultiple(){
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Store store=session.get(Store.class,2);
        Product1 product=new Product1();
        product.setName("product3");
        product.setStore(store);
        session.persist(product);
        transaction.commit();
        session.close();
    }
    private static void insertIntial() {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Store store = new Store();
        store.setName("store1");
        store.setAddress("address2");
        Product1 product = new Product1();
        product.setName("product2");
        product.setStore(store);
        session.persist(product);
        transaction.commit();
        session.close();
    }

    public static Session getSession() {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(Store.class);
        configuration.addAnnotatedClass(Product1.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        return session;
    }

}