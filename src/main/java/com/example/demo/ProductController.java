package com.example.demo;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ProductController {
    
    @RequestMapping("/products")
    public List<Product> getAllProducts() {
        return Arrays.asList(
            new Product(
                "1",
                "Intel® Core™ i7-1265UE Processor",
                "10 cores, 12MB cache, up to 4.70 GHz",
                "https://www.intel.com/content/dam/www/global/badges/core-i7-processors-framed-badge-rwd.jpg.rendition.intel.web.480.270.jpg",
                10,
                499.99F
            ),
            new Product(
                "2",
                "XLR8 Gaming",
                "3200MHz 32GB (2x16GB) DDR4 DRAM",
                "https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6401/6401196_sd.jpg;maxHeight=640;maxWidth=550",
                15,
                116.99F
            ),
            new Product(
                "3",
                "NVIDIA GeForce RTX 3090 Ti",
                "The GeForce RTX 3090 Ti is a big ferocious GPU with TITAN class performance. Powered by Ampere—NVIDIA's 2nd gen RTX architecture—it doubles down on ray tracing and AI performance with enhanced Ray Tracing Cores, Tensor Cores and new streaming multiprocessors.",
                "https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6502/6502626_sd.jpg;maxHeight=640;maxWidth=550",
                2,
                1999.99F
            ),
            new Product(
                "4",
                "Intel® Core™ i7-8700K Processor",
                "8 cores, 16MB cache, up to 4.2 GHz",
                "https://www.intel.com/content/dam/www/global/badges/core-i7-processors-framed-badge-rwd.jpg.rendition.intel.web.480.270.jpg",
                5,
                599.99F
            )
        );
    }
}
