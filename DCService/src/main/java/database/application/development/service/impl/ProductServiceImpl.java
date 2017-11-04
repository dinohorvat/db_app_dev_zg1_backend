package database.application.development.service.impl;

import database.application.development.model.domain.Product;
import database.application.development.model.history.HstProduct;
import database.application.development.model.messages.ApplicationInputs;
import database.application.development.model.messages.OutputHeader;
import database.application.development.model.messages.Request;
import database.application.development.model.messages.Response;
import database.application.development.repository.ProductDao;
import database.application.development.repository.hst.HstProductDao;
import database.application.development.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;
    private HstProductDao hstProductDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao, HstProductDao hstProductDao) {
        this.productDao = productDao;
        this.hstProductDao = hstProductDao;
    }

    @Override
    public Response<Product> getProductById(Request<ApplicationInputs> request) {
        Product product = productDao.getProductById(request.getBody().getEntityId());
        return new Response<>(new OutputHeader(), product);
    }

    @Override
    public Response<Product> createProduct(Request<ApplicationInputs> request) {
        Product product = productDao.createProduct(request.getBody().getProduct());

        addToProductHistory("INSERT", product);

        return new Response<>(new OutputHeader(), product);
    }

    @Override
    public Response<Product> updateProduct(Request<ApplicationInputs> request) {
        Product product = productDao.updateProduct(request.getBody().getProduct());

        addToProductHistory("UPDATE", product);

        return new Response<>(new OutputHeader(), product);
    }

    @Override
    public void deleteProduct(Request<ApplicationInputs> request) {
        Product product = productDao.getProductById(request.getBody().getEntityId());

        addToProductHistory("DELETE", product);

        productDao.deleteProduct(product);
    }

    /**
     * Adds a new row to the HST_PRODUCT table for this product object.
     *
     * @param changeDesc The description of the change (INSERT, UPDATE, or DELETE)
     * @param product The {@link Product} object which has been changed
     */
    private void addToProductHistory(String changeDesc, Product product) {
        HstProduct hstProduct = new HstProduct(changeDesc, product);
        hstProduct = hstProductDao.createHstProduct(hstProduct);
        product.getHstProducts().add(hstProduct);
    }
}
