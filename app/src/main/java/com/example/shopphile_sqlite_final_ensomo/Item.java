package com.example.shopphile_sqlite_final_ensomo;

public class Item {
    private int id;
    private String brandName;
    private String productName;
    private String productPrice;
    private int productImage;  // Holds the image resource ID

    public Item(String brandName, String productName, String productPrice, int productImage) {
        this.id = id;
        this.brandName = brandName;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImage = productImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public int getProductImage() {
        return productImage;
    }
}

