package com.productproject.demo.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.productproject.demo.entity.Products;


@Repository
public interface ProductsRepository extends JpaRepository<Products,Integer> {

    // List<Cart> findByuid(int uid);

    // List<Map<String, Long>> findByuid(int uid);

   

}
