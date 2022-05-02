package com.ytaocrow.shop.service;

import com.ytaocrow.shop.dto.ProductRequest;
import com.ytaocrow.shop.model.Product;

public interface ProductService {
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

}
