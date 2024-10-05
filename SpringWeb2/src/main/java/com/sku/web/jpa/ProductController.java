package com.sku.web.jpa;


import com.sku.web.mb.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RestController // 메서드 전체가 리스폰스 바디로 설정됨
@RequestMapping("prod")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("")
    public String index()
    {
        return "JPA CRUD Product Controller";
    }
    @GetMapping("/add")
    public Product add() {
        Product product = new Product(); //엔터티의 새로운 인스턴스
        product.setName("Sonata2");
        product.setPrice(5000);
        product.setDescription("Made in korea");
        Product savedProduct = productRepository.save(product);

        return savedProduct;
    }

    @GetMapping("/list")
    public List<Product> list()
    {
        return productRepository.findAll();

    }
    @GetMapping("byId/{id}")
    public Product getById(@PathVariable int id)
    {   //Optional 조건
        Optional<Product> op = productRepository.findById(id);
       if(op.isPresent())
       {
           return op.get();
       }
       else return null;
    }
    @GetMapping("update/{id}/{price}")
    public Product update(@PathVariable int id, @PathVariable int price)
    {
        Product p=new Product();
        p.setId(id);
        p.setPrice(price);
        //{"id":1,"name":null,"price":5000000,"description":null}
       return productRepository.save(p);

    }
    @GetMapping("delete/{id}")
    public String delete(@PathVariable int id)
    {
        productRepository.deleteById(id);
      return  "delete success";
    }

    @GetMapping("/search/{startPrice}/{endPrice}")
    @ResponseBody
    public  List<Product> searchByPriceBetweem(@PathVariable int startPrice, @PathVariable int endPrice)
    {
        List<Product> list=productRepository.findByPriceBetweenOrderByPrice(startPrice,endPrice);


        return list;

    }
    @GetMapping("/gt/{base}")
    public List<Product> gt(@PathVariable int base)
    {
        return productRepository.findByPriceGreaterThanOrderByPrice(base);
    }
}
