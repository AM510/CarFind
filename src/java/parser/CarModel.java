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
public class CarModel {
    private final String manuf;
    private final String model;

    public CarModel(String manuf, String model) {
        this.manuf = manuf;
        this.model = model;
    }

    public String getManuf() {
        return manuf;
    }

    public String getModel() {
        return model;
    }
    
}
