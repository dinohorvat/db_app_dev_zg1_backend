package database.application.development.repository.configuration;

import database.application.development.model.domain.*;
import database.application.development.model.history.*;
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
            configuration.addAnnotatedClass(Branch.class);
            configuration.addAnnotatedClass(Customer.class);
            configuration.addAnnotatedClass(DcsDate.class);
            configuration.addAnnotatedClass(Employee.class);
            configuration.addAnnotatedClass(RewardPoints.class);
            configuration.addAnnotatedClass(Transactions.class);
            configuration.addAnnotatedClass(Location.class);
            configuration.addAnnotatedClass(Product.class);
            configuration.addAnnotatedClass(RewardPolicy.class);
            configuration.addAnnotatedClass(Currency.class);

            configuration.addAnnotatedClass(RelCustomerProductTransaction.class);

            configuration.addAnnotatedClass(HstCompany.class);
            configuration.addAnnotatedClass(HstEmployee.class);
            configuration.addAnnotatedClass(HstBranch.class);
            configuration.addAnnotatedClass(HstCustomer.class);
            configuration.addAnnotatedClass(HstProduct.class);
            configuration.addAnnotatedClass(HstTransaction.class);


            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            ourSessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    protected Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    //TODO: delete after implementing jUnit tests
    public static void main(final String[] args) throws Exception {
        final Session session = new ORMConfig().getSession();
        try {
            System.out.println("querying all the managed entities...");
            Company company = null;
            Transactions transaction1 = null;
            Employee employee = null;
            Branch branch = null;
            Customer customer = null;
            Product product = null;

            HstTransaction hstTransaction = null;

            Transaction transaction = session.beginTransaction();
            transaction1 = session.get(Transactions.class,1);
            employee = session.get(Employee.class, 1);
            branch = session.get(Branch.class, 1);
            company = session.get(Company.class, 1);
            customer = session.get(Customer.class, 1);
            hstTransaction = session.get(HstTransaction.class, 1);
            product = session.get(Product.class, 2);


            product.setPrice(15);
            session.update(product);
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