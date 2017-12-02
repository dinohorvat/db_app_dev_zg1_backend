package database.application.development.service.impl;

import database.application.development.model.domain.Product;
import database.application.development.model.history.HstProduct;
import database.application.development.model.messages.ApplicationInputs;
import database.application.development.model.messages.OutputHeader;
import database.application.development.model.messages.Request;
import database.application.development.model.messages.Response;
import database.application.development.model.util.ProductStatus;
import database.application.development.repository.ProductDao;
import database.application.development.repository.configuration.ORMConfig;
import database.application.development.repository.hst.HstProductDao;
import database.application.development.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl extends ORMConfig implements ProductService {

    private ProductDao productDao;
    private HstProductDao hstProductDao;

//    @Autowired
//    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductDao productDao, HstProductDao hstProductDao) {
        super();
        this.productDao = productDao;
        this.hstProductDao = hstProductDao;
    }

    @Override
    public Response<Product> getProductById(Request<ApplicationInputs> request) {
        Session session = this.getSession();
        Product product = productDao.getProductById(request.getBody().getEntityId(), session);
        return new Response<>(new OutputHeader(), product);
    }

    @Override
    public Response<List<Product>> getAllProducts() {
        Session session = this.getSession();
        List<Product> products = productDao.getAllProducts(session);
        return new Response<>(new OutputHeader(), products);
    }

    @Override
    public Response<Product> createProduct(Request<ApplicationInputs> request) {
        Session session = this.getSession();
        session.beginTransaction();
        Product product = productDao.createProduct(request.getBody().getProduct(), session);

        addToProductHistory("INSERT", product, session);

        session.getTransaction().commit();
        session.close();
        return new Response<>(new OutputHeader(), product);
    }

    @Override
    public Response<Product> updateProduct(Request<ApplicationInputs> request) {
        Session session = this.getSession();
        session.beginTransaction();
        Product product = productDao.updateProduct(request.getBody().getProduct(), session);

        addToProductHistory("UPDATE", product, session);

        session.getTransaction().commit();
        session.close();
        return new Response<>(new OutputHeader(), product);
    }

    @Override
    public void deleteProduct(Request<ApplicationInputs> request) {
        Session session = this.getSession();
        session.beginTransaction();
        Product product = productDao.getProductById(request.getBody().getEntityId(), session);

        product.setProductStatus(ProductStatus.DELETED);
        addToProductHistory("DELETE", product, session);

        productDao.deleteProduct(product, session);

        session.getTransaction().commit();
        session.close();
    }

    /**
     * Adds a new row to the HST_PRODUCT table for this product object.
     *
     * @param changeDesc The description of the change (INSERT, UPDATE, or DELETE)
     * @param product The {@link Product} object which has been changed
     */
    private void addToProductHistory(String changeDesc, Product product, Session session) {
        HstProduct hstProduct = new HstProduct(changeDesc, product);
        hstProduct = hstProductDao.createHstProduct(hstProduct, session);
        if(product.getHstProducts() == null) product.setHstProducts(new HashSet<HstProduct>());
        product.getHstProducts().add(hstProduct);
    }
}
