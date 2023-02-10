package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user.getCar());
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public User getByCar(String model, int series) {
        String hqlQuery = "from User user where " +
                "user.car.model=:model and user.car.series=:series";
        return (User) sessionFactory.getCurrentSession().createQuery(hqlQuery)
                .setParameter("model", model)
                .setParameter("series", series)
                .getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }
    @Override
    public void dropTables() {
        sessionFactory.getCurrentSession()
                .createSQLQuery("DROP TABLE users").executeUpdate();
        sessionFactory.getCurrentSession()
                .createSQLQuery("DROP TABLE cars").executeUpdate();
    }

}
