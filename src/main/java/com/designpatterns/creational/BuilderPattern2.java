package com.designpatterns.creational;

import java.util.Arrays;
import java.util.List;

public class BuilderPattern2 {

    public static void main(String ... args) {
        Product.Builder productBuilder = Product.newBuilder("Computer");
        Product dell = productBuilder.addProductPrice(12)
                .addProductDetails(Arrays.asList("intel-core7", "Dell"))
                .build();
        System.out.print("Product Price: " + dell.getPrice());
    }
}

class Product {

    private String name;
    private long price;
    private List<String> details;

    private Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    public List<String> getDetails() {
        return details;
    }

    public static ProductBuilder newBuilder(String name) {
        return new ProductBuilder(name);
    }

    public interface Builder {
        Product build();
        Builder addProductPrice(long productPrice);
        Builder addProductDetails(List<String> productDetails);
    }

    private static class ProductBuilder implements Builder {
        private Product product;

        private ProductBuilder(String name) {
            this.product = new Product(name);
        }

        @Override
        public Product build() {
            return this.product;
        }

        @Override
        public ProductBuilder addProductPrice(long productPrice) {
            this.product.price = productPrice;
            return this;
        }

        @Override
        public ProductBuilder addProductDetails(List<String> productDetails) {
            this.product.details = productDetails;
            return this;
        }
    }
}
