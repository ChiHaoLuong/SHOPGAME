package com.example.ps14498_luongchihao_asm.Models;

import java.io.Serializable;

public class Game_Models  {
    public int gameId;
    public String name;
    public int price;
    public String describle;
    public String developer;
    public int categoryId;
    public int rating;
    public String img;

    public Game_Models(int gameId, String name, int price, String img, String describle, String developer, int categoryId, int rating) {
        this.gameId = gameId;
        this.name = name;
        this.price = price;
        this.describle = describle;
        this.developer = developer;
        this.categoryId = categoryId;
        this.rating = rating;
        this.img = img;
    }

    public String getImg(){
        return img;
    }

    public void setImg(){
        this.img = img;
    }



    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescrible() {
        return describle;
    }

    public void setDescrible(String describle) {
        this.describle = describle;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Game_Models{" +
                "gameId=" + gameId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", describle='" + describle + '\'' +
                ", developer='" + developer + '\'' +
                ", categoryId=" + categoryId +
                ", rating=" + rating +
                ", img='" + img + '\'' +
                '}';
    }
}
