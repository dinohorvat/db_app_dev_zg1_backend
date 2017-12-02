package database.application.development.repository;

import database.application.development.model.domain.Product;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by dinohorvat on 30/10/2017.
 */
public interface ProductDao {
    Product getProductById(int entityId, Session session);

    Product updateProduct(Product product, Session session);

    Product createProduct(Product product, Session session);

    List<Product> getAllProducts(Session session);

    void deleteProduct(Product product, Session session);
}
