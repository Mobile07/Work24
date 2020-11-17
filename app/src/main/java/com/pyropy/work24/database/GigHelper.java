package com.pyropy.work24.database;

public class GigHelper {

    public String gigTitle, description, deliveryDate,price,img1Uri,img2Uri,img3Uri,category;

    public GigHelper(String gigTitle, String description, String deliveryDate, String price, String img1Uri, String img2Uri, String img3Uri, String category) {
        this.gigTitle = gigTitle;
        this.description = description;
        this.deliveryDate = deliveryDate;
        this.price = price;
        this.img1Uri = img1Uri;
        this.img2Uri = img2Uri;
        this.img3Uri = img3Uri;
        this.category = category;
    }

    public GigHelper(){}

    public String getGigTitle() {
        return gigTitle;
    }

    public void setGigTitle(String gigTitle) {
        this.gigTitle = gigTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImg1Uri() {
        return img1Uri;
    }

    public void setImg1Uri(String img1Uri) {
        this.img1Uri = img1Uri;
    }

    public String getImg2Uri() {
        return img2Uri;
    }

    public void setImg2Uri(String img2Uri) {
        this.img2Uri = img2Uri;
    }

    public String getImg3Uri() {
        return img3Uri;
    }

    public void setImg3Uri(String img3Uri) {
        this.img3Uri = img3Uri;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
