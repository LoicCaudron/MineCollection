package com.example.einore.minecollection.Model;

public class Mineral {
    private int mineral_id;
    private String mineral_name;
    private String mineral_synonym;
    private String mineral_minAss;
    private String mineral_systCrist;
    private String mineral_color;
    private String mineral_glow;
    private String mineral_aspect;
    private String mineral_clivage;
    private float mineral_hardness;
    private float mineral_density;
    private float mineral_price;
    private String mineral_location;
    private int foreignKey_user;
    private int foreignKey_location;
    private int foreignKey_chemical;

    public Mineral(){}

    public int getMineral_id() {
        return mineral_id;
    }

    public void setMineral_id(int mineral_id) {
        this.mineral_id = mineral_id;
    }

    public String getMineral_name() {
        return mineral_name;
    }

    public void setMineral_name(String mineral_name) {
        this.mineral_name = mineral_name;
    }

    public String getMineral_synonyme() {
        return mineral_synonym;
    }

    public void setMineral_synonyme(String mineral_synonyme) {
        this.mineral_synonym = mineral_synonyme;
    }

    public String getMineral_minAss() {
        return mineral_minAss;
    }

    public void setMineral_minAss(String mineral_minAss) {
        this.mineral_minAss = mineral_minAss;
    }

    public String getMineral_systCrist() {
        return mineral_systCrist;
    }

    public void setMineral_systCrist(String mineral_systCrist) {
        this.mineral_systCrist = mineral_systCrist;
    }

    public String getMineral_color() {
        return mineral_color;
    }

    public void setMineral_color(String mineral_color) {
        this.mineral_color = mineral_color;
    }

    public String getMineral_glow() {
        return mineral_glow;
    }

    public void setMineral_glow(String mineral_glow) {
        this.mineral_glow = mineral_glow;
    }

    public String getMineral_aspect() {
        return mineral_aspect;
    }

    public void setMineral_aspect(String mineral_aspect) {
        this.mineral_aspect = mineral_aspect;
    }

    public String getMineral_clivage() {
        return mineral_clivage;
    }

    public void setMineral_clivage(String mineral_clivage) {
        this.mineral_clivage = mineral_clivage;
    }

    public float getMineral_hardness() {
        return mineral_hardness;
    }

    public void setMineral_hardness(float mineral_hardness) {
        this.mineral_hardness = mineral_hardness;
    }

    public float getMineral_density() {
        return mineral_density;
    }

    public void setMineral_density(float mineral_density) {
        this.mineral_density = mineral_density;
    }


    public float getMineral_price() {
        return mineral_price;
    }

    public void setMineral_price(float mineral_price) {
        this.mineral_price = mineral_price;
    }

    public String getMineral_location() {
        return mineral_location;
    }

    public void setMineral_location(String mineral_location) {
        this.mineral_location = mineral_location;
    }

    public int getForeignKey_user() {
        return foreignKey_user;
    }

    public void setForeignKey_user(int foreignKey_user) {
        this.foreignKey_user = foreignKey_user;
    }

    public int getForeignKey_location() {
        return foreignKey_location;
    }

    public void setForeignKey_location(int foreignKey_location) {
        this.foreignKey_location = foreignKey_location;
    }

    public int getForeignKey_chemical() {
        return foreignKey_chemical;
    }

    public void setForeignKey_chemical(int foreignKey_chemical) {
        this.foreignKey_chemical = foreignKey_chemical;
    }
}
