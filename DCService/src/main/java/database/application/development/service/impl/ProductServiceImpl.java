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

        // TODO: 11/2/2017  insert hst

        return new Response<>(new OutputHeader(), product);
    }

    @Override
    public Response<Product> createProduct(Request<ApplicationInputs> request) {
        Product product = productDao.createProduct(request.getBody().getProduct());

        HstProduct hstProduct = new HstProduct("INSERT", product);
        hstProduct = hstProductDao.createHstProduct(hstProduct);
        product.getHstProducts().add(hstProduct);

        return new Response<>(new OutputHeader(), product);
    }

    @Override
    public Response<Product> updateProduct(Request<ApplicationInputs> request) {
        Product product = productDao.updateProduct(request.getBody().getProduct());

        return new Response<>(new OutputHeader(), product);
    }

    @Override
    public void deleteProduct(Request<ApplicationInputs> request) {
        Product product = productDao.getProductById(request.getBody().getEntityId());

        // TODO: 11/2/2017  insert hst

        productDao.deleteProduct(product);
    }
}