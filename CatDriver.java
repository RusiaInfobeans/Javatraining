package day11.Task;


import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import utils.Utility;

import java.util.List;

public class CatDriver {
    public static void main(String[] args) {
        Session session = Utility.getSession();
        insert();
        CriteriaQuery<Object[]> critertiaQuery2 =searchCat(session);
       List<Object[]> resultList1 = session.createQuery(critertiaQuery2).getResultList();
        resultList1.forEach(item-> System.out.println("name : "+item[0]));

    }
    public static void insert(){
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(Cat.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
            Cat cat=new Cat();
            cat.setName("mat1");
            cat.setAge((int) (Math.random() * 20));
            cat.setWeight((int) (Math.random() * 10));
            session.persist(cat);
        transaction.commit();
        session.close();
    }
    private static CriteriaQuery<Object[]> searchCat(Session session) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> critertiaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Cat> root = critertiaQuery.from(Cat.class);
        critertiaQuery.select(root.get("name")).where(criteriaBuilder.gt(root.get("weight"),2));
        critertiaQuery.groupBy(root.get("age"));
        critertiaQuery.having(criteriaBuilder.like(root.get("name"),"m"));
        return critertiaQuery;
    }
}
