package com.productproject.demo.RestController;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.productproject.demo.Service.DemoService;
import com.productproject.demo.entity.Cart;
import com.productproject.demo.entity.Products;
import com.productproject.demo.entity.Users;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@Validated
public class demoRestController {

    @Autowired
    private DemoService demoService;
// get all prdts
    @GetMapping("/store/products")
    public ResponseEntity<List<Products>> getAllProducts() {
        try {
            List<Products> productAll = demoService.getAllproducts();
            return ResponseEntity.ok(productAll);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
// get all users
    @GetMapping("store/users")
    public ResponseEntity<List<Users>> getAllUsers() {
        try {
            List<Users> userAll = demoService.getAllUsers();
            return ResponseEntity.ok(userAll);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
// add prdt
    @PostMapping("store/addproduct")
        public ResponseEntity<Object> addProducts(@Valid @RequestPart("products") String productsString, @RequestPart("image") MultipartFile image) {
        try {
        
        ObjectMapper om = new ObjectMapper();
        Products products = om.readValue(productsString, Products.class);
            ResponseEntity<Object> newProducts = demoService.save( products,image);
            System.out.println(newProducts);
            return newProducts;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add product");
        }
    }

// add usr
    @PostMapping("/store/addusers")
    public ResponseEntity<Users> addUsers(@Valid @RequestBody Users users) {
        try {
            Users newUsers = demoService.save(users);
            return ResponseEntity.ok(newUsers);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

// add cart
@PostMapping("/store/addcart")
public ResponseEntity<Cart> addcart(@Valid @RequestBody Cart cart) {
    try {
        Cart newcart = demoService.save(cart);
        return ResponseEntity.ok(newcart);
    } catch (Exception e) {
        System.out.println(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}

// get prdt by id
    @GetMapping("/store/products/{pid}")
    public ResponseEntity<Products> getProductById(@PathVariable("pid") int pid) {
        try {
            Products theProduct = demoService.getproductbyId(pid);
            if (theProduct == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(theProduct);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
// get usr by id
    @GetMapping("/store/users/{uid}")
    public ResponseEntity<Users> getUserById(@PathVariable("uid") int uid) {
        try {
            Users theUser = demoService.getuserbyId(uid);
            if (theUser == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(theUser);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
// dlte prdt
    @DeleteMapping("store/deleteproduct/{pid}")
    public ResponseEntity<String> deleteProduct(@PathVariable int pid) {
        try {
            Products productDel = demoService.getproductbyId(pid);
            if (productDel == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No product found");
            }
            demoService.deleteproduct(pid);
            return ResponseEntity.ok("Deleted the product");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete product");
        }
    }
// dlte usr
    @DeleteMapping("/store/deleteUser/{uid}")
    public ResponseEntity<String> deleteUser(@PathVariable int uid) {
        try {
            Users userDel = demoService.getuserbyId(uid);
            if (userDel == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user found");
            }
            demoService.deleteuser(uid);
            return ResponseEntity.ok("Deleted the user");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete user");
        }
    }

    //get prdt and its qty using user id
    @GetMapping("/store/getcartqty/{uid}")
    public ResponseEntity<List<Map<String ,Long>>> getproductqtywithuid(@PathVariable("uid") int uid){
        try{
           List<Map<String ,Long>> userorder=demoService.getproductqtywithuid(uid);
            return new ResponseEntity<>(userorder,HttpStatus.OK);}
        catch(Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
      }

//get product and its price by giving an uid 
    @GetMapping("/store/product/productsprice/{uid}")
    public ResponseEntity<List<Map<String, Object>>> getProductprice(@PathVariable int uid) {
        try{
        List<Map<String, Object>> productDetails = demoService.getProductpriceByUserId(uid);
        return new ResponseEntity<>(productDetails, HttpStatus.OK);}
        catch(Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
// find the total price
    @GetMapping("/store/cart/totalprice/{uid}")
    public ResponseEntity<Map<String, Object>> getTotalprice(@PathVariable int uid){
        try{       
            Map<String,Object> pricelist = demoService.getTotalprice(uid);
            return new ResponseEntity<>(pricelist,HttpStatus.OK);}
            catch(Exception e){
                System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
      }


    // get an image when passing an pid
    // @GetMapping("/store/productimg/{pid}")
    // }

// getting images using pid
    @GetMapping("/store/productimg/{pid}")
    public ResponseEntity<String> getprdtimage(@PathVariable int pid){
        try{
           String theimage=demoService.getprdtimage(pid);
            return new ResponseEntity<>(theimage,HttpStatus.OK);
        }
        catch(Exception e ){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); 
        }
    }

// login
    @GetMapping("/access/login")
    public String login(Authentication authentication){
        try{
        for (GrantedAuthority authority : authentication.getAuthorities()) {
                String theroles = authority.getAuthority();
            if (theroles.equals("ROLE_USER")) {
                return "Login successfully - user";
            } else if (theroles.equals("ROLE_MANAGER")) {
                return "Login successfully - manager";
            } else if (theroles.equals("ROLE_ADMIN")) {
                return "Login successfully - admin";
            }}}
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        return "login sucessfully";
    }
    //logout

    @GetMapping("/access/logout")
    public String logout(){
        try{
        return "logout sucessfully";}
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    }


    
     
  
     
    

      
      
     
     
 