package com.ytaocrow.shop.dao;


import com.ytaocrow.shop.dto.ProductRequest;
import com.ytaocrow.shop.dto.ProductsQueryParams;
import com.ytaocrow.shop.model.Product;

import java.util.List;

public interface ProductDao {

    Integer countProduct(ProductsQueryParams productsQueryParams);

    List<Product> getProducts(ProductsQueryParams productsQueryParams);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void updateStock(Integer productId, Integer stock);

    void deleteProductById(Integer productId);

}
