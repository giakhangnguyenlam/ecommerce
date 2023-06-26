package com.khangnlg.controller;

import com.khangnlg.message.ResponseMessage;
import com.khangnlg.model.ProductModel;
import com.khangnlg.model.ProductRegistrationModel;
import com.khangnlg.models.Token;
import com.khangnlg.security.JWTService;
import com.khangnlg.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final WebClient webClient;

    private final JWTService jwtService;

    @Value("${user.host.url}")
    private String userHost;

    @PostMapping
    public ResponseEntity createProduct(@RequestBody ProductRegistrationModel productModel){
        ProductModel product = productService.createProduct(productModel);
        return new ResponseEntity(ResponseMessage.builder()
                .status(HttpStatus.CREATED.value())
                .message("Product created successfully")
                .data(product)
                .build(),
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity getProduct(@PathVariable("id") long id){
        ProductModel productModel = productService.getProductById(id);
        return new ResponseEntity(ResponseMessage
                .builder()
                .status(HttpStatus.CREATED.value())
                .data(productModel)
                .message("Get product successfully").build(),
                HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseMessage getUserById(@PathVariable("username") String username){

        URI uri = UriComponentsBuilder
                .fromHttpUrl(userHost)
                .path("/api/v1/users/username/"+username)
                .build()
                .toUri();

        String authenUserName = SecurityContextHolder.getContext().getAuthentication().getName();

        Token token =  jwtService.generateToken(authenUserName);

        ResponseMessage responseMessage = webClient
                .get()
                .uri(uri)
                .headers(h -> h.setBearerAuth(token.getToken()))
                .retrieve()
                .bodyToMono(ResponseMessage.class)
                .block();

        if (responseMessage != null) {
            return responseMessage;
        } else {
            return ResponseMessage
                    .builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message("Can't find user")
                    .build();
        }
    }

}
