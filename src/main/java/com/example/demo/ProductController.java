package com.example.demo;

import com.example.demo.database.service.AppService;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://project-2-team-1-frontend-ucllteam16.ucll-ocp-40cb0df2b03969eabb3fac6e80373775-0000.eu-de.containers.appdomain.cloud",  allowCredentials="true", allowedHeaders = "*")
@RestController
public class ProductController {

    private AppService service = new AppService();

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return service.getProductService().getAll();
    }

    @GetMapping("/check")
    public boolean greeting(@RequestParam(value = "name", defaultValue = "World") String name,
            @AuthenticationPrincipal Jwt accessToken) {
        System.out.println("In GET Request");
        String scope = accessToken.getClaims().get("scope").toString();
        Boolean partnerRole = scope.contains("partner");
        System.out.println("Contains sequence 'partner': " + accessToken.getClaims().get("scope").toString());
        System.out.println(
                "Contains sequence 'partner': " + accessToken.getClaims().get("scope").toString().contains("partner"));
        if (partnerRole) {
            return true;
        } else {
            return false;
        }
    }

    @PostMapping("/products")
    public String addProduct(@RequestBody Product product, @AuthenticationPrincipal Jwt accessToken) {
        System.out.println("In POST Request");
        String scope = accessToken.getClaims().get("scope").toString();
        Boolean partnerRole = scope.contains("partner");

        if (partnerRole) {
            System.out.println("Contains sequence 'partner': " + accessToken.getClaims().get("scope").toString());
            System.out.println("Contains sequence 'partner': "
                    + accessToken.getClaims().get("scope").toString().contains("partner"));
            service.getProductService().add(product);
            return "Product added";
        } else {
            return "Not Authorized to add product";
        }
    }

    @GetMapping("/suppliers")
    public List<Supplier> getAllSuppliers(){
        return service.getSupplierService().getAll();
    }

    @PostMapping("/suppliers")
    public String addProduct(@RequestBody Supplier supplier, @AuthenticationPrincipal Jwt accessToken) {
        System.out.println("In POST Request");
        String scope = accessToken.getClaims().get("scope").toString();
        Boolean partnerRole = scope.contains("partner");

        if (partnerRole) {
            System.out.println("Contains sequence 'partner': " + accessToken.getClaims().get("scope").toString());
            System.out.println("Contains sequence 'partner': "
                    + accessToken.getClaims().get("scope").toString().contains("partner"));
            service.getSupplierService().add(supplier);
            return "Product added";
        } else {
            return "Not Authorized to add product";
        }
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders(){
        return service.getOrderService().getAll();
    }

    @PostMapping("/orders")
    public String addOrder(@RequestBody Order order, @AuthenticationPrincipal Jwt accessToken) {
        System.out.println("In POST Request");
        String scope = accessToken.getClaims().get("scope").toString();
        Boolean partnerRole = scope.contains("partner");

        if (partnerRole) {
            System.out.println("Contains sequence 'partner': " + accessToken.getClaims().get("scope").toString());
            System.out.println("Contains sequence 'partner': "
                    + accessToken.getClaims().get("scope").toString().contains("partner"));
            service.getOrderService().add(order);
            return "Product added";
        } else {
            return "Not Authorized to add product";
        }
    }
}
