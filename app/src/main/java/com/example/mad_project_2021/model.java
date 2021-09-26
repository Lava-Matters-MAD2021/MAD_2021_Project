package com.example.mad_project_2021;

public class model {

    String author, burl, category, description, price, qt, title;

    model()
    {

    }

    public model(String author, String burl, String category, String description, String price, String qt, String title) {
        this.author = author;
        this.burl = burl;
        this.category = category;
        this.description = description;
        this.price = price;
        this.qt = qt;
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBurl() {
        return burl;
    }

    public void setBurl(String burl) {
        this.burl = burl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQt() {
        return qt;
    }

    public void setQt(String qt) {
        this.qt = qt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}