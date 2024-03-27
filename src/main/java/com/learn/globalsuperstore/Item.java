package com.learn.globalsuperstore;

import java.util.Date;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

public class Item {

    @NotBlank(message = "Please choose a category")
    private String category;
    @NotBlank(message = "name cannot be blank")
    private String name;
    @Min(value = 0,message = "Price cannot be negative")
    private long price;
    @Min(value = 0,message = "Discount cannot be negative")
    private long discount;

    @Past(message = "Date must be of the past")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
 
    private String id;



    // public Item(String category, String name, String price, String discount, Date date) {
    //     this.category = category;
    //     this.name = name;
    //     this.price = price;
    //     this.discount = discount;
    //     this.date = date;
    // }


    public Item() {
        this.id = UUID.randomUUID().toString();
    }


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return this.price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getDiscount() {
        return this.discount;
    }

    public void setDiscount(Long discount) {
        this.discount = discount;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // public String getFormatDate(){
    //     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    //     return formatter.format(date);
    // }

    
}
