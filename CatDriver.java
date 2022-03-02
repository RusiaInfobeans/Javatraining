package day11.Task;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.Utility;

public class CatDriver {
    public static void main(String[] args) {
         insertCats();
        Session session1 = Utility.getSession();
        CriteriaQuery<Cat> criteriaQuery = fetchData(session1);
        System.out.println(session1.createQuery(criteriaQuery).getResultList());
    }

    private static CriteriaQuery<Cat>  fetchData(Session session) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Cat> criteriaQuery= criteriaBuilder.createQuery(Cat.class);
        Root<Cat> root=criteriaQuery.from(Cat.class);
        criteriaQuery.select(root).where(criteriaBuilder .and(criteriaBuilder.like(root.get("name"),"m%"),criteriaBuilder.gt(root.get("weight"),2)));
         criteriaQuery.orderBy(criteriaBuilder.asc(root.get("age")));
        return criteriaQuery;

    }

    public static void insertCats(){
        Session session = Utility.getSession();
        String[] names = new String[]{"cat1","cat2","cat3","cat4","mat5"};
        Transaction transaction = session.beginTransaction();
        for(int i=0;i<100;i++){
            Cat cat=new Cat();
            cat.setName(names[(int)(Math.random()*5)]);
            cat.getWeight((int)(Math.random()*10));
            cat.setAge((int)(Math.random()*10));
            session.persist(cat);
        }
        transaction.commit();
        session.close();
    }
}
