package com.sku.web.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//DAO
public interface ProductRepository extends JpaRepository<Product,Integer> // 엔터티, 아이디
{
    // CRUD 지원 JpaRepository가 받아서 메소드 바디를 완성

    List<Product> findByPriceBetweenOrderByPrice(int startPrice, int endPrice);
    List<Product> findByPriceGreaterThanOrderByPrice( int base);
}
