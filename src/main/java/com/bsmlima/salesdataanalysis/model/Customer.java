package com.bsmlima.salesdataanalysis.model;

public class Customer {

    private String cnpj;
    private String name;
    private String area;

    public Customer() {
    }

    public Customer(String cnpj, String name, String area) {
        this.cnpj = cnpj;
        this.name = name;
        this.area = area;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
