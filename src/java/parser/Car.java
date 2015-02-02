/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

/**
 *
 * @author xnikos
 */
public class Car {
    private final int id;
    private final String manuf;
    private final String model;
    private final String link;
    private final int cc;
    private final int klm;
    private final String region;
    private final int postNum;
    private final CarDate firstReg;
    private final int price;

    public Car(int id, String link, String manuf, String model, int cc, int klm, String region, int postNum, CarDate firstReg, int price) {
        this.id = id;
        this.manuf = manuf;
        this.model = model;
        this.link = link;
        this.cc = cc;
        this.klm = klm;
        this.postNum = postNum;
        this.region = region;
        this.firstReg = firstReg;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getLink() {
        return link;
    }

    public String getManuf() {
        return manuf;
    }

    public String getModel() {
        return model;
    }

    public int getKlm() {
        return klm;
    }

    public String getRegion() {
        return region;
    }

    public CarDate getFirstReg() {
        return firstReg;
    }

    public int getPrice() {
        return price;
    }

    public int getPostNum() {
        return postNum;
    }

    public int getCc() {
        return cc;
    }
    
}
