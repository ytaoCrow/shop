package com.ytaocrow.shop.controller;

import com.ytaocrow.shop.constant.ProductCategory;
import com.ytaocrow.shop.dto.ProductRequest;
import com.ytaocrow.shop.dto.ProductsQueryParams;
import com.ytaocrow.shop.model.Product;
import com.ytaocrow.shop.service.ProductService;
import com.ytaocrow.shop.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<Page<Product>> getProducts(

            //查詢條件
            @RequestParam(required = false) ProductCategory category,
            @RequestParam(required = false) String search,

            //排序 sorting
            @RequestParam(defaultValue = "created_date") String orderBy,
            @RequestParam(defaultValue = "desc") String sort,

            //分頁 Pagination
            @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,
            @RequestParam(defaultValue = "0") @Min(0) Integer offset
    ){

        ProductsQueryParams productsQueryParams = new ProductsQueryParams();
        productsQueryParams.setCategory(category);
        productsQueryParams.setSearch(search);
        productsQueryParams.setOrderBy(orderBy);
        productsQueryParams.setSort(sort);
        productsQueryParams.setLimit(limit);
        productsQueryParams.setOffset(offset);

        //取得Product list
        List<Product> productList = productService.getProducts(productsQueryParams);

       //取得product 總數
       Integer total = productService.countProduct(productsQueryParams);

       //分頁
       Page<Product> page = new Page<>();
       page.setLimit(limit);
       page.setOffset(offset);
       page.setTotal(total);
       page.setResults(productList);

       return  ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId){
        Product product = productService.getProductById(productId);

        if (product != null){
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest){
       Integer productId = productService.createProduct(productRequest);

       Product product = productService.getProductById(productId);
       return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,
                                                 @RequestBody @Valid ProductRequest productRequest){

        //check product
        Product product = productService.getProductById(productId);

        if (product == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        //change product data
        productService.updateProduct(productId, productRequest);

        Product updateProduct = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.OK).body(updateProduct);
    }
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId){

        productService.deleteProductById(productId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
