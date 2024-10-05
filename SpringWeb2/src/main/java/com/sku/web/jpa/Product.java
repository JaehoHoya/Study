package com.sku.web.jpa;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Primary;

@Entity
@Table(name = "Product") // 클래스 명과 동일하지 않게 만들려면 Table
@Data // 파라미터 있는 생성자
@NoArgsConstructor //파라미터 없는 생성자
public class Product //엔터티가 된다는것은 Primary key를 가져야함 그래야 오류 x
{
    @Id
    @SequenceGenerator(sequenceName = "prod_id_seq"
            ,allocationSize =1,name = "prod_id_gen")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator ="prod_id_gen")
    private int id;
    //@Column(name="pname") ->실제 속성은 name인데 Pname으로 하고 싶다
    private String name;
    private int price;
    private String description;
}