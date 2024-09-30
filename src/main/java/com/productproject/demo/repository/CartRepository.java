package com.productproject.demo.repository;

import java.util.List;
import java.util.Map;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.productproject.demo.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer>  {

    List<Cart> findByuid(int uid);

    // @Query
    // ("SELECT new map(c.pid as pid, p.price as price, c.uid as uid) " + "FROM Cart c " + "JOIN Products p ON c.pid = p.pid " + "JOIN Users u ON c.uid = u.uid " +"WHERE c.uid =:uid")
    // List<Map<String, Object>> findProductpriceByUserId(int uid);

    @Query
    ("SELECT new map(c.uid as uid,p.price as price, c.pid as pid,c.cid as cid)" + "FROM Cart c"+" JOIN Products p ON c.pid=p.pid" + " JOIN Users u ON c.uid=u.uid "+"WHERE c.uid= :uid")
    List<Map<String, Object>> findProductpriceByUserId(int uid);

    // Cart findByuid(int uid);

    // Optional<Cart> findByUidAndPid(int uid, int pid);


    // @Query
    // ("SELECT new map(c.uid as uid,p.price as price, c.pid as pid,c.cid as cid)" + "FROM Cart c"+" JOIN Products p ON c.pid=p.pid" + " JOIN Users u ON c.uid=u.uid "+"WHERE c.uid= :uid")
    // List<Map<String, Object>> findProductpricebycid(int cid);
}


/* SELECT u.user_id,c.cart_id FROM cart_table c JOIN users_table u  ON c.user_id = u.user_id ; 
SELECT u.user_id,c.cart_id FROM cart_table c FULL JOIN users_table u  ON c.user_id = u.user_id ;
SELECT u.user_id,c.cart_id FROM cart_table c LEFT JOIN users_table u  ON c.user_id = u.user_id WHERE u.user_id=1;

*/ 