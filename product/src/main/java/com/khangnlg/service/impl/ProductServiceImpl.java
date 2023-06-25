package com.khangnlg.service.impl;

import com.khangnlg.entities.ProductEntity;
import com.khangnlg.exception.ObjectNotFoundException;
import com.khangnlg.model.ProductModel;
import com.khangnlg.model.ProductRegistrationModel;
import com.khangnlg.objectmapper.Converter;
import com.khangnlg.repositories.ProductRepository;
import com.khangnlg.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    @Qualifier("productModelConverter")
    private Converter<ProductModel, ProductEntity> productModelConverter;

    @Autowired
    @Qualifier("productRegistrationConverter")
    private Converter<ProductRegistrationModel, ProductEntity> productRegistrationConverter;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductModel createProduct(ProductRegistrationModel product) {
        ProductEntity productEntity = productRegistrationConverter.convertModelToEntity(product);
        productEntity = productRepository.save(productEntity);
        return productModelConverter.convertEntityToModel(productEntity);
    }

    @Override
    public ProductModel getProductById(long id) {
        Optional<ProductEntity> optionalProductEntity = productRepository.findById(id);
        if(optionalProductEntity.isPresent()){
            return productModelConverter.convertEntityToModel(optionalProductEntity.get());
        }else {
            throw new ObjectNotFoundException("Can't find product with id "+id);
        }
    }

    @Override
    public void updateProduct(ProductModel product) throws ObjectNotFoundException {
        Optional<ProductEntity> optionalProductEntity = productRepository.findById(product.getId());
        if(optionalProductEntity.isPresent()){
            ProductEntity productEntity = productModelConverter.convertModelToEntity(product);
            productRepository.save(productEntity);
        }else {
            throw new ObjectNotFoundException("Can't find product with id "+product.getId());
        }
    }

    @Override
    public void deleteProduct(long id) throws ObjectNotFoundException {
        Optional<ProductEntity> optionalProductEntity = productRepository.findById(id);
        if(optionalProductEntity.isPresent()){
            productRepository.deleteById(id);
        }else {
            throw new ObjectNotFoundException("Can't find product with id "+id);
        }
    }
}
