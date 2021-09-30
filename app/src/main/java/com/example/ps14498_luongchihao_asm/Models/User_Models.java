package com.example.ps14498_luongchihao_asm.Models;

import com.google.gson.annotations.SerializedName;

public class User_Models {
    private int userId;
    private String username;
    private String name;
    private String password;
    private int money;
    private String result;

    public User_Models(int userId, String username, String name, String password, int money) {
        this.userId = userId;
        this.username = username;
        this.name = name;
        this.password = password;
        this.money = money;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "User_Models{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", money=" + money +
                ", result" + result +
                '}';
    }
}
