package day8;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class StudentDriver {

    public static void main(String[] args) {
      //Student student=new Student(1,"student1","indore",122);
      Student student=new Student("student2","indore",12);
        Configuration configuration = new Configuration();
        configuration.configure();

        configuration.addResource("Student.hbm.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(student);
        transaction.commit();
        session.close();


    }
}