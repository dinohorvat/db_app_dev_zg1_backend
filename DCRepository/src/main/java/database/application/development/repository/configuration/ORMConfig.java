package database.application.development.repository.configuration;

import database.application.development.model.domain.*;
import database.application.development.model.history.HstCompany;
import database.application.development.model.relation.RelCustomerProductTransaction;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by HrvojeGrgic on 11/10/2017.
 */
public class ORMConfig {
    private final SessionFactory ourSessionFactory;
    private final ServiceRegistry serviceRegistry;

    @Autowired
    public ORMConfig(){
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            configuration.addAnnotatedClass(Company.class);
            configuration.addAnnotatedClass(HstCompany.class);
            configuration.addAnnotatedClass(Branch.class);
            configuration.addAnnotatedClass(Customer.class);
            configuration.addAnnotatedClass(DcsDate.class);
            configuration.addAnnotatedClass(Employee.class);
            configuration.addAnnotatedClass(RewardPoints.class);
            configuration.addAnnotatedClass(Transactions.class);
            configuration.addAnnotatedClass(Location.class);
            configuration.addAnnotatedClass(Product.class);

            configuration.addAnnotatedClass(RelCustomerProductTransaction.class);




            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            ourSessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    //TODO: delete after implementing jUnit tests
    public static void main(final String[] args) throws Exception {
        final Session session = new ORMConfig().getSession();
        try {
            System.out.println("querying all the managed entities...");
            Transactions transaction1 = null;
            Employee employee = null;
            Branch branch = null;

            Transaction transaction = session.beginTransaction();
            transaction1 = session.get(Transactions.class,1);
            employee = session.get(Employee.class, 1);
            branch = session.get(Branch.class, 1);
            transaction.commit();

            System.out.println("done...");
        }catch (RuntimeException re){
            re.printStackTrace();
        }
        finally {
            session.close();
            System.exit(-1);
        }
    }

}