package com.example.ps14498_luongchihao_asm.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Responegameresult {
    public int result;
    public String mes;
    public Game_Models game_models;
    @SerializedName("games")
    public ArrayList<Game_Models> listgame_models;


    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public Game_Models getGame_models() {
        return game_models;
    }

    public void setGame_models(Game_Models game_models) {
        this.game_models = game_models;
    }

    public Responegameresult(int result, String mes, Game_Models game_models) {
        this.result = result;
        this.mes = mes;
        this.game_models = game_models;
    }

    public Responegameresult() {
    }


    public ArrayList<Game_Models> getListgame_models() {
        return listgame_models;
    }

    public void setListgame_models(ArrayList<Game_Models> listgame_models) {
        this.listgame_models = listgame_models;
    }

    @Override
    public String toString() {
        return "Responegameresult{" +
                "result=" + result +
                ", mes='" + mes + '\'' +
                ", game_models=" + game_models +
                ", listgame_models=" + listgame_models +
                '}';
    }
}


