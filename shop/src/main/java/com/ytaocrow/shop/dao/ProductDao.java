package com.ytaocrow.shop.dao;

import com.ytaocrow.shop.dto.ProductRequest;
import com.ytaocrow.shop.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);

}
