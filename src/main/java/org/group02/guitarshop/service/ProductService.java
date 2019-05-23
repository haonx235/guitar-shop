package org.group02.guitarshop.service;

import org.group02.guitarshop.entity.Product;
import org.group02.guitarshop.entity.ProductImage;

import java.util.List;

public interface ProductService {

    Product getProductById(Integer id);
    List<Product> searchProducts(String searchString);
    List<Product> getMostDiscountProducts();
    List<Product> getNewestProducts();
    void GetProductExtraInfo(Integer id);
    int getTotalRate();
    int[] getListCountRate();
    List<Product> getListRelatedProducts();
    double getAverageRate();
    List<ProductImage> getProductImage();
}