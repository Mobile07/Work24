package com.pyropy.work24.database;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

public class GigHelper implements Parcelable {

    public String gigTitle, description, deliveryDate,price,img1Uri,img2Uri,img3Uri,category,author, id,salesCount;

    public GigHelper(String gigTitle, String description, String deliveryDate, String price, String img1Uri, String img2Uri, String img3Uri, String category, String author) {
        this.gigTitle = gigTitle;
        this.description = description;
        this.deliveryDate = deliveryDate;
        this.price = price;
        this.img1Uri = img1Uri;
        this.img2Uri = img2Uri;
        this.img3Uri = img3Uri;
        this.category = category;
        this.author = author;
    }

    public GigHelper(){}

    protected GigHelper(Parcel in) {
        gigTitle = in.readString();
        description = in.readString();
        deliveryDate = in.readString();
        price = in.readString();
        img1Uri = in.readString();
        img2Uri = in.readString();
        img3Uri = in.readString();
        category = in.readString();
        author = in.readString();
        id = in.readString();
    }

    public static final Creator<GigHelper> CREATOR = new Creator<GigHelper>() {
        @Override
        public GigHelper createFromParcel(Parcel in) {
            return new GigHelper(in);
        }

        @Override
        public GigHelper[] newArray(int size) {
            return new GigHelper[size];
        }
    };

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSalesCount() {
        return salesCount;
    }

    public void setSalesCount(String salesCount) {
        this.salesCount = salesCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(gigTitle);
        parcel.writeString(description);
        parcel.writeString(deliveryDate);
        parcel.writeString(price);
        parcel.writeString(img1Uri);
        parcel.writeString(img2Uri);
        parcel.writeString(img3Uri);
        parcel.writeString(category);
        parcel.writeString(author);
        parcel.writeString(id);
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("category", category);
        result.put("author", author);
        result.put("gigTitle", gigTitle);
        result.put("description", description);
        result.put("deliveryDate", deliveryDate);
        result.put("price", price);
        result.put("img1Uri", img1Uri);
        result.put("img2Uri", img2Uri);
        result.put("img3Uri", img3Uri);
        result.put("salesCount", salesCount);

        return result;
    }
}
