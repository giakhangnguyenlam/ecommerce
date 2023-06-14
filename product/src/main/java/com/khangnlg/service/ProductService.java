package com.khangnlg.service;

import com.khangnlg.exception.ObjectNotFoundException;
import com.khangnlg.model.ProductModel;
import com.khangnlg.model.ProductRegistrationModel;

public interface ProductService {

    ProductModel createProduct(ProductRegistrationModel product);

    ProductModel getProductById(long id) throws ObjectNotFoundException;

    void updateProduct(ProductModel product) throws ObjectNotFoundException;

    void deleteProduct(long id) throws ObjectNotFoundException;

}
