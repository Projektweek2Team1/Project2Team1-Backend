package com.example.demo;

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

@CrossOrigin(origins = "0.0.0.0/0",  allowCredentials="true", allowedHeaders = "*")
@RestController
public class ProductController {

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return Arrays.asList(
                new Product(
                        "1",
                        "Intel® Core™ i7-1265UE Processor",
                        "10 cores, 12MB cache, up to 4.70 GHz",
                        "https://www.intel.com/content/dam/www/global/badges/core-i7-processors-framed-badge-rwd.jpg.rendition.intel.web.480.270.jpg",
                        10,
                        499.99F),
                new Product(
                        "2",
                        "XLR8 Gaming",
                        "3200MHz 32GB (2x16GB) DDR4 DRAM",
                        "https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6401/6401196_sd.jpg;maxHeight=640;maxWidth=550",
                        15,
                        116.99F),
                new Product(
                        "3",
                        "NVIDIA GeForce RTX 3090 Ti",
                        "The GeForce RTX 3090 Ti is a big ferocious GPU with TITAN class performance. Powered by Ampere—NVIDIA's 2nd gen RTX architecture—it doubles down on ray tracing and AI performance with enhanced Ray Tracing Cores, Tensor Cores and new streaming multiprocessors.",
                        "https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6502/6502626_sd.jpg;maxHeight=640;maxWidth=550",
                        2,
                        1999.99F),
                new Product(
                        "4",
                        "Intel® Core™ i7-8700K Processor",
                        "8 cores, 16MB cache, up to 4.2 GHz",
                        "https://www.intel.com/content/dam/www/global/badges/core-i7-processors-framed-badge-rwd.jpg.rendition.intel.web.480.270.jpg",
                        5,
                        599.99F));
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
            return "Product added";
        } else {
            return "Not Authorized to add product";
        }
    }

    @GetMapping("/suppliers")
    public List<Supplier> getAllSuppliers(){
        return Arrays.asList(
            new Supplier(1,"Intel","https://www.intel.com/content/dam/www/public/us/en/images/logos/logo-rwd.png.rendition.intel.web.1280.720.png" ,"https://www.intel.com/content/www/us/en/homepage.html"),
            new Supplier(2, "AMD", "https://d3cy9zhslanhfa.cloudfront.net/media/6D972F55-8581-42E9-B19004B4B9C6882E/6402D423-A4D4-4F71-B4426826DF535016/webimage-0D0EA698-37CE-45FE-ABD49CBE61F82A7A.jpg", "https://www.amd.com/en"),
            new Supplier(3, "NVIDIA", "https://www.nvidia.com/content/dam/en-zz/Solutions/about-nvidia/logo-and-brand/01-nvidia-logo-vert-500x200-2c50-d.png", "https://www.nvidia.com/en-us/")
        )   ;
    }
}
