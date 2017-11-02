package database.application.development.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.application.development.model.domain.Product;
import database.application.development.model.messages.ApplicationInputs;
import database.application.development.model.messages.InputHeader;
import database.application.development.model.messages.Request;
import database.application.development.model.messages.Response;
import database.application.development.model.util.Views;
import database.application.development.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("product")
@Api(tags = "Product", description = "Operations about products")
@Slf4j
public class ProductController extends Serializer {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService, ObjectMapper mapper) {
        super(mapper);
        this.productService = productService;
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Find Product by ID", notes = "Implementation for getting a Product by ID")
    public ResponseEntity<Response<Product>> getProductById(@PathVariable int id) throws JsonProcessingException {
        InputHeader header = new InputHeader();
        ApplicationInputs inputs = new ApplicationInputs().setEntityId(id);

        Response<Product> result = productService.getProductById(new Request<>(header, inputs));
        ResponseEntity<Response<Product>> response = new ResponseEntity<Response<Product>>(result, HttpStatus.OK);

        return serializeResponse(result, new Views.RequestToProduct());
    }

    @PostMapping
    @ApiOperation(value = "Create Product", notes = "Implementation for creating a Product")
    public ResponseEntity<Response<Product>> createProduct(@RequestBody Product product) throws JsonProcessingException {
        InputHeader header = new InputHeader();
        ApplicationInputs inputs = new ApplicationInputs().setProduct(product);
        Response<Product> result = productService.createProduct(new Request<>(header, inputs));
        ResponseEntity<Response<Product>> response = new ResponseEntity<Response<Product>>(result, HttpStatus.OK);

        return serializeResponse(result, new Views.RequestToProduct());
    }

    @PutMapping
    @ApiOperation(value = "Update Product", notes = "Implementation for updating a Product")
    public ResponseEntity<Response<Product>> updateProduct(@RequestBody Product product) throws JsonProcessingException {
        InputHeader header = new InputHeader();
        ApplicationInputs inputs = new ApplicationInputs().setProduct(product);
        Response<Product> result = productService.updateProduct(new Request<>(header, inputs));
        ResponseEntity<Response<Product>> response = new ResponseEntity<Response<Product>>(result, HttpStatus.OK);

        return serializeResponse(result, new Views.RequestToProduct());
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Delete Product", notes = "Implementation for deleting a Product")
    public ResponseEntity deleteProduct(@PathVariable int id) {
        InputHeader header = new InputHeader();
        ApplicationInputs inputs = new ApplicationInputs().setEntityId(id);
        productService.deleteProduct(new Request<>(header, inputs));

        return new ResponseEntity(HttpStatus.OK);
    }
}
