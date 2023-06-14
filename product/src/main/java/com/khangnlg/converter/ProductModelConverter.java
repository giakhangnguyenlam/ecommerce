package com.khangnlg.converter;

import com.khangnlg.entities.ProductEntity;
import com.khangnlg.model.ProductModel;
import com.khangnlg.objectmapper.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductModelConverter implements Converter<ProductModel, ProductEntity> {

    @Override
    public ProductEntity convertModelToEntity(ProductModel productModel) {
        return ProductEntity.builder()
                .id(productModel.getId())
                .storeId(productModel.getStoreId())
                .category(productModel.getCategory())
                .name(productModel.getName())
                .quantity(productModel.getQuantity())
                .price(productModel.getPrice())
                .description(productModel.getDescription())
                .image(productModel.getImage())
                .build();
    }

    @Override
    public ProductModel convertEntityToModel(ProductEntity productEntity) {
        return ProductModel.builder()
                .id(productEntity.getId())
                .storeId(productEntity.getStoreId())
                .category(productEntity.getCategory())
                .name(productEntity.getName())
                .quantity(productEntity.getQuantity())
                .price(productEntity.getPrice())
                .description(productEntity.getDescription())
                .image(productEntity.getImage())
                .build();
    }
}
