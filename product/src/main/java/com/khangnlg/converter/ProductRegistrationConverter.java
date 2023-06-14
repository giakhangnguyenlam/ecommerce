package com.khangnlg.converter;

import com.khangnlg.entities.ProductEntity;
import com.khangnlg.model.ProductRegistrationModel;
import com.khangnlg.objectmapper.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductRegistrationConverter implements Converter<ProductRegistrationModel, ProductEntity> {

    @Override
    public ProductEntity convertModelToEntity(ProductRegistrationModel productRegistrationModel) {
        return ProductEntity.builder()
                .storeId(productRegistrationModel.storeId)
                .category(productRegistrationModel.getCategory())
                .name(productRegistrationModel.getName())
                .quantity(productRegistrationModel.getQuantity())
                .price(productRegistrationModel.getPrice())
                .description(productRegistrationModel.getDescription())
                .image(productRegistrationModel.getImage())
                .isDiscount(productRegistrationModel.isDiscount)
                .discount(productRegistrationModel.getDiscount())
                .build();
    }

    @Override
    public ProductRegistrationModel convertEntityToModel(ProductEntity productEntity) {
        return null;
    }
}
