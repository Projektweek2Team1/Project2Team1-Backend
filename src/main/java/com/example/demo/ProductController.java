package com.example.demo;

import com.example.demo.database.service.AppService;
import org.springframework.security.oauth2.jwt.Jwt;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

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
        System.out.println(LocalDateTime.now().toString() + "Products add");
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
        System.out.println(LocalDateTime.now().toString() + "Suppliers add");
        String scope = accessToken.getClaims().get("scope").toString();
        Boolean partnerRole = scope.contains("partner");

        if (partnerRole) {
            System.out.println("Contains sequence 'partner': " + accessToken.getClaims().get("scope").toString());
            System.out.println("Contains sequence 'partner': "
                    + accessToken.getClaims().get("scope").toString().contains("partner"));
            service.getSupplierService().add(supplier);
            return "Supplier added";
        } else {
            return "Not Authorized to add supplier";
        }
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders(){
        return service.getOrderService().getAll();
    }

    @GetMapping("/orders/{id}")
    public Order getAllOrders(@PathParam("id") int id){
        return service.getOrderService().get(id);
    }

    @PostMapping("/orders")
    public String addOrder(@RequestBody Order order, @AuthenticationPrincipal Jwt accessToken) {
        System.out.println(LocalDateTime.now().toString() + "Orders add");
        String scope = accessToken.getClaims().get("scope").toString();
        Boolean partnerRole = scope.contains("partner");

        if (order.getItemIds().length < 0) {
            return "No items";
        }

        if (partnerRole) {
            System.out.println("Contains sequence 'partner': " + accessToken.getClaims().get("scope").toString());
            System.out.println("Contains sequence 'partner': "
                    + accessToken.getClaims().get("scope").toString().contains("partner"));
            service.getOrderService().add(order);
            order.decreaseQuantities(service);
            return "Order added";
        } else {
            return "Not Authorized to add order";
        }
    }
}
