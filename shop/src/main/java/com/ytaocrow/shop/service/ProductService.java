package com.ytaocrow.shop.service;


import com.ytaocrow.shop.dto.ProductRequest;
import com.ytaocrow.shop.dto.ProductsQueryParams;
import com.ytaocrow.shop.model.Product;

import java.util.List;

public interface ProductService {

    Integer countProduct(ProductsQueryParams productsQueryParams);

    List<Product> getProducts(ProductsQueryParams productsQueryParams);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);

}
