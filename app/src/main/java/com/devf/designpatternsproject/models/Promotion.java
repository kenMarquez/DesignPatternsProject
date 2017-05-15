package com.devf.designpatternsproject.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ken on 02/05/17.
 */

public class Promotion implements Parcelable {

    private String title;
    private String urlIcon;
    private String description;
    private double price;
    private String discount;
    private String urlImage;
    private String storeName;

    public Promotion(String title, String urlIcon, String description, double price, String discount, String urlImage, String storeName) {
        this.title = title;
        this.urlIcon = urlIcon;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.urlImage = urlImage;
        this.storeName = storeName;
    }

    protected Promotion(Parcel in) {
        title = in.readString();
        urlIcon = in.readString();
        description = in.readString();
        price = in.readDouble();
        discount = in.readString();
        urlImage = in.readString();
        storeName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(urlIcon);
        dest.writeString(description);
        dest.writeDouble(price);
        dest.writeString(discount);
        dest.writeString(urlImage);
        dest.writeString(storeName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Promotion> CREATOR = new Creator<Promotion>() {
        @Override
        public Promotion createFromParcel(Parcel in) {
            return new Promotion(in);
        }

        @Override
        public Promotion[] newArray(int size) {
            return new Promotion[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlIcon() {
        return urlIcon;
    }

    public void setUrlIcon(String urlIcon) {
        this.urlIcon = urlIcon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
