package com.khangnlg.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRegistrationModel {

    public int storeId;
    public int category;
    public String name;
    public int quantity;
    public double price;
    public String description;
    public String image;
    public boolean isDiscount;
    public double discount;

}
