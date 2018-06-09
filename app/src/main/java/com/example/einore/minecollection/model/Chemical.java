package com.example.einore.minecollection.model;

public class Chemical {

    private int chemical_id;
    private String chemical_formula;
    private int chemical_class;

    public Chemical(){}

    public Chemical(String chemical_formula, int chemical_class) {
        this.chemical_formula = chemical_formula;
        this.chemical_class = chemical_class;
    }

    public int getChemical_id() {
        return chemical_id;
    }

    public void setChemical_id(int chemical_id) {
        this.chemical_id = chemical_id;
    }

    public String getChemical_formula() {
        return chemical_formula;
    }

    public void setChemical_formula(String chemical_formula) {
        this.chemical_formula = chemical_formula;
    }

    public int getChemical_class() {
        return chemical_class;
    }

    public void setChemical_class(int chemical_class) {
        this.chemical_class = chemical_class;
    }
}
