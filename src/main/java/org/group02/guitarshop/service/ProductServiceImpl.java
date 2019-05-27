package org.group02.guitarshop.service;

import java.util.ArrayList;
import java.util.List;

import org.group02.guitarshop.entity.Product;
import org.group02.guitarshop.entity.ProductImage;
import org.group02.guitarshop.entity.Rate;
import org.group02.guitarshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;

@Service
public class ProductServiceImpl implements ProductService {
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private ProductRepository repository;

    public List<ProductImage> ListImage;
    public List<Product> ListRelatedProducts;
    public int TotalRate;
    public double AverageRate=0;
    public int[] ListCountRate;

    public List<Product> getListRelatedProducts() {
        return ListRelatedProducts;
    }

    @Override
    public int getTotalRate() {
        return TotalRate;
    }

    @Override
    public int[] getListCountRate(){
        return ListCountRate;
    }

    public double getAverageRate() {
        return AverageRate;
    }

    @Override
    public List<ProductImage> getProductImage() {
        return ListImage;
    }

    @Override
    public Product getProductById(Integer id) {
        Product product = repository.findById(id).get();
        return product;
    }

    @Override
    public List<Product> getMostDiscountProducts() {
        Query query = entityManager.createNativeQuery("SELECT TOP(8) * FROM PRODUCT as pd ORDER BY pd.Discount_Amount DESC", Product.class);
        return query.getResultList();
    }

    @Override
    public List<Product> getNewestProducts() {
        Query query = entityManager.createNativeQuery("SELECT TOP(8) * FROM PRODUCT as pd ORDER BY pd.Created_Time DESC", Product.class);
        return query.getResultList();
    }

    @Override
    public List<Product> searchProducts(String searchString) {
        List<Product> result = new ArrayList<>();
        for (Product p:repository.findAll()) {
            if(p.getName().toLowerCase().contains(searchString.toLowerCase()))
                result.add(p);
        }
        return result;
    }

    @Override
    public void GetProductExtraInfo(Integer id)
    {
        // Lấy list hình ảnh của sản phẩm
        Query query = entityManager.createNativeQuery("SELECT * FROM PRODUCT_IMAGE as pi where pi.Id="+id, ProductImage.class);
        ListImage = query.getResultList();

        // Lấy list sản phẩm liên quan
        query = entityManager.createNativeQuery("SELECT TOP(8) * FROM PRODUCT as p1, (SELECT * FROM PRODUCT as p where p.Id="+id+") as p2 where p1.Id<>p2.Id and p1.Id_Category=p2.Id_Category", Product.class);
        ListRelatedProducts = query.getResultList();

        // Đếm tổng số lượt xếp hạng sản phẩm
        query = entityManager.createNativeQuery("SELECT * FROM RATE as r where r.Id_Product="+id, Rate.class);
        List<Rate> listRate = query.getResultList();
        TotalRate = query.getResultList().size();

        // Đếm số lượt của mỗi bậc xếp hạng
        ListCountRate = new int[5];
        for (int i = 0; i < 5; i++)
        {
            query = entityManager.createNativeQuery("select * from RATE as r where r.Id_Product="+id + " and r.Number_Of_Stars="+(i+1),Rate.class);
            ListCountRate[i] = query.getResultList().size();
        }
        if (TotalRate > 0)
        {
            int sum=0;
            for (Rate i: listRate) {
                sum += i.getNumberOfStars();
            }
            AverageRate = sum/TotalRate;
        }
        else
        {
            AverageRate = 0;
        }

    }
}
