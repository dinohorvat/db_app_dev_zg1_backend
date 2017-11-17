package database.application.development.service;

import database.application.development.model.domain.Product;
import database.application.development.model.messages.ApplicationInputs;
import database.application.development.model.messages.Request;
import database.application.development.model.messages.Response;

import java.util.List;

public interface ProductService {

    Response<Product> getProductById(Request<ApplicationInputs> request);

    Response<List<Product>> getAllProducts();

    Response<Product> createProduct(Request<ApplicationInputs> request);

    Response<Product> updateProduct(Request<ApplicationInputs> request);

    void deleteProduct(Request<ApplicationInputs> request);
}
