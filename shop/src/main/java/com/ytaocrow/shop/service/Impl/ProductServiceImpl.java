package com.ytaocrow.shop.service.Impl;


import com.ytaocrow.shop.dao.ProductDao;
import com.ytaocrow.shop.dto.ProductRequest;
import com.ytaocrow.shop.dto.ProductsQueryParams;
import com.ytaocrow.shop.model.Product;
import com.ytaocrow.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl  implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> getProducts(ProductsQueryParams productsQueryParams) {
        return productDao.getProducts(productsQueryParams);
    }

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return productDao.createProduct(productRequest);
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        productDao.updateProduct(productId, productRequest);
    }

    @Override
    public void deleteProductById(Integer productId) {
        productDao.deleteProductById(productId);
    }
}
