package database.application.development.repository;

import database.application.development.model.domain.Product;

/**
 * Created by dinohorvat on 30/10/2017.
 */
public interface ProductDao {
    Product getProductById(int entityId);

    Product updateProduct(Product product);

    Product createProduct(Product product);

    void deleteProduct(Product product);
}
