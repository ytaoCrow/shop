package com.ytaocrow.shop.service;

import com.ytaocrow.shop.constant.ProductCategory;
import com.ytaocrow.shop.dto.ProductRequest;
import com.ytaocrow.shop.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts(ProductCategory category, String search);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);

}
