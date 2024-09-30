package com.productproject.demo.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.productproject.demo.entity.Cart;
import com.productproject.demo.entity.Products;
import com.productproject.demo.entity.Users;
import com.productproject.demo.repository.CartRepository;
import com.productproject.demo.repository.ProductsRepository;
import com.productproject.demo.repository.UsersRepository;

import jakarta.validation.constraints.NotNull;

@Service
public class DemoService {

    @Autowired
    public UsersRepository usersRepository;

    @Autowired
    public ProductsRepository productsRepository;

    @Autowired
    public CartRepository cartRepository;

    // Get all products
    public List<Products> getAllproducts() {
        try {
            return productsRepository.findAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null; 
        }
    }

    // Get all users
    public List<Users> getAllUsers() {
        try {
            return usersRepository.findAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // // Save product
    // public ResponseEntity<Object> save(Products products,MultipartFile image) {
    //     try {
    //         if(!image.isEmpty()){
    //             products.setImage(image.getBytes());
    //         }
    //         productsRepository.save(products);
    //         return ResponseEntity.ok("done");
    //     } catch (Exception e) {
    //         System.out.println(e.getMessage());
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    //     }
    // }

    // Save user
    public Users save(Users users) {
        try {
            return usersRepository.save(users);
        } catch (Exception e) {
            System.out.println( e.getMessage());
            return null;
        }
    }

    // get prdt by id
    public Products getproductbyId(int pid) {
        try {
           Products product =productsRepository.findById(pid).get();
            return product;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // get usr by id
    public Users getuserbyId(int uid) {
        try {
            Users user=usersRepository.findById(uid).get();
            return user;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // Delete product by ID
    public int deleteproduct(int pid) {
        try {
            productsRepository.deleteById(pid);
            return pid;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return pid;  
        }
    }

    // Delete user by ID
    public int deleteuser(int uid) {
        try {
            usersRepository.deleteById(uid);
            return uid;
        } catch (Exception e) {
            System.out.println( e.getMessage());
            return uid;
        }
    }
//save cart item
    public Cart save(Cart cart) {
       return cartRepository.save(cart);
    }


    //get product and qty by userid
    public List<Map<String, Long>> getproductqtywithuid(int uid) {
        try{

        List<Cart> cartpro=cartRepository.findByuid(uid);
        List<Map<String, Long>> result = new ArrayList<>();

        for (Cart oneproduct : cartpro){
           Map<String, Long> newresult = new HashMap<String,Long>();

           newresult.put("product_id", (long) oneproduct.getPid());        
           newresult.put("quantity", oneproduct.getQty());
        result.add(newresult);  
        }
        return result;}
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

// get price of products in the cart
    public List<Map<String, Object>> getProductpriceByUserId(int uid) {
        try{
        return cartRepository.findProductpriceByUserId(uid); }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

// total price
    public Map<String, Object> getTotalprice(int uid) {
       List<Cart> cartItems = cartRepository.findByuid(uid);  
        int totalPrice=0;

        for (Cart item : cartItems) {
            Products product = productsRepository.findById(item.getPid()).get();
            if (product != null) {
                totalPrice += item.getQty() * product.getPrice();
            }}

        Map<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("total_price", totalPrice);
        return result;
    }


    // get the prdt img using id
    // public byte[] getprdtimage(int pid) {
    //     Products product=productsRepository.findById(pid).get();
    //     return product.getImage();
        
    // }

    public String getprdtimage(int pid) {
        Products product=productsRepository.findById(pid).get();
        return product.getImage();
        
    }

    // Save product
    public ResponseEntity<Object> save(Products products,MultipartFile image) {
        try {
            if(image != null){
                String newimage = Base64.getEncoder().encodeToString(image.getBytes());
                products.setImage(newimage);    
            }
            productsRepository.save(products);
            return ResponseEntity.ok("done");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
        
    // public String getLoginMessage() {
    //     return "Logged in successfully!";
    // }

    // public String getLogoutMessage() {
    //     return "Logged out successfully!";
    // }

}
        
    
