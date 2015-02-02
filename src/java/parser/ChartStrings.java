/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.util.ArrayList;

/**
 *
 * @author xnikos
 */
public class ChartStrings {

    private Integer minKlm = 99999999, maxKlm = -1, minYr = 3000, maxYr = -1, minPrice = 9999999, maxPrice = -1;
    
    public ArrayList<String> createInput(ArrayList<Car> cars) {
        ArrayList<String> str = new ArrayList();
        // 0: klm/price, 1: yr/price, 2: geochart, 3: cc/price
        for (int i = 0; i < 4; i++) {
            str.add("");
        }

        ArrayList<String> geoCity = new ArrayList();
        ArrayList<Integer> geoPrice = new ArrayList();
        ArrayList<Integer> geoNumofCars = new ArrayList();
        ArrayList<Integer> ccFound = new ArrayList();
        ArrayList<Integer> ccNumofCars = new ArrayList();
        ArrayList<Integer> ccPrice = new ArrayList();
        ArrayList<Integer> ccMinPrice = new ArrayList();
        Integer temp;
        String tempStr;
        Boolean flag;
        Integer year;

        for (Car car : cars) {
            // adjust year of first registration date to 4 digits
            if (car.getFirstReg().getYear() < 20) {
                year = car.getFirstReg().getYear() + 2000;
            } else {
                year = car.getFirstReg().getYear() + 1900;
            }
            // run through list of cities we found so far
            flag = false;
            for (int j = 0; j < geoCity.size() && flag == false; j++) {
                // if city is already found add car price to the co-responding variable and +1 number of cars found in city
                if (car.getRegion().equals(geoCity.get(j))) {
                    geoPrice.set(j, geoPrice.get(j) + car.getPrice());
                    geoNumofCars.set(j, geoNumofCars.get(j) + 1);
                    flag = true;
                }
            }
            // if city is seen first time on the list
            if (!flag) {
                // add city to the list and create/adjust co-responding variables
                geoCity.add(car.getRegion());
                geoPrice.add(car.getPrice());
                geoNumofCars.add(1);
            }
            // same procedure as cities for cc of cars
            flag = false;
            for (int j = 0; j < ccFound.size() && flag == false; j++) {
                if (car.getCc() == ccFound.get(j)) {
                    ccPrice.set(j, ccPrice.get(j) + car.getPrice());
                    ccNumofCars.set(j, ccNumofCars.get(j) + 1);
                    if (car.getPrice() < ccMinPrice.get(j)) {
                        ccMinPrice.set(j, car.getPrice());
                    }
                    flag = true;
                }
            }
            if (!flag) {
                ccFound.add(car.getCc());
                ccPrice.add(car.getPrice());
                ccMinPrice.add(car.getPrice());
                ccNumofCars.add(1);
            }
            // add a line with the car to the klm/price scatter graph input
            str.set(0, str.get(0) + ",\n[ " + car.getKlm() + ",   " + car.getPrice() + "]");
            // add a line with the car to the first reg/price scatter graph input
            str.set(1, str.get(1) + ",\n[ " + year + ",   " + car.getPrice() + "]");
            // if car first reg is min or max seen this far, set the variable to it's first reg
            if (year < minYr) {
                minYr = year;
            }
            if (year > maxYr) {
                maxYr = year;
            }
            // same with min/max klm
            if (car.getKlm() < minKlm) {
                minKlm = car.getKlm();
            }
            if (car.getKlm() > maxKlm) {
                maxKlm = car.getKlm();
            }
            // same with min/max price
            if (car.getPrice() < minPrice) {
                minPrice = car.getPrice();
            }
            if (car.getPrice() > maxPrice) {
                maxPrice = car.getPrice();
            }
        }

        // bubble sort cities
        // sorting is done because when very large inputs on google charts, it shows cities few at a time until chart is filled completely
        flag = true;
        while (flag) {
            flag = false;
            for (int j = 0; j < geoNumofCars.size() - 1; j++) {
                if (geoNumofCars.get(j) < geoNumofCars.get(j + 1)) {
                    temp = geoNumofCars.get(j);
                    geoNumofCars.set(j, geoNumofCars.get(j + 1));
                    geoNumofCars.set(j + 1, temp);

                    temp = geoPrice.get(j);
                    geoPrice.set(j, geoPrice.get(j + 1));
                    geoPrice.set(j + 1, temp);

                    tempStr = geoCity.get(j);
                    geoCity.set(j, geoCity.get(j + 1));
                    geoCity.set(j + 1, tempStr);
                    flag = true;
                }
            }
        }

        // bubble sort cc
        flag = true;
        while (flag) {
            flag = false;
            for (int j = 0; j < ccFound.size() - 1; j++) {
                if (ccFound.get(j) < ccFound.get(j + 1)) {
                    temp = ccFound.get(j);
                    ccFound.set(j, ccFound.get(j + 1));
                    ccFound.set(j + 1, temp);

                    temp = ccPrice.get(j);
                    ccPrice.set(j, ccPrice.get(j + 1));
                    ccPrice.set(j + 1, temp);

                    temp = ccMinPrice.get(j);
                    ccMinPrice.set(j, ccMinPrice.get(j + 1));
                    ccMinPrice.set(j + 1, temp);

                    temp = ccNumofCars.get(j);
                    ccNumofCars.set(j, ccNumofCars.get(j + 1));
                    ccNumofCars.set(j + 1, temp);
                    flag = true;
                }
            }
        }

        for (int i = 0; i < geoCity.size(); i++) {

            // add a line to geochart input with the city's cars
            temp = geoPrice.get(i) / geoNumofCars.get(i);
            str.set(2, str.get(2) + ",\n [ \'" + geoCity.get(i) + "\',    " + temp.toString() + ",    " + geoNumofCars.get(i).toString() + "]");
        }

        for (int i = 0; i < ccFound.size(); i++) {
            temp = ccPrice.get(i) / ccNumofCars.get(i);
            str.set(3, str.get(3) + ",\n [ \'" + ccFound.get(i) + "\',    " + temp.toString() + ",    " + ccMinPrice.get(i).toString() + "]");
        }

        return str;
    }

    public Integer getMinKlm() {
        return minKlm;
    }

    public Integer getMaxKlm() {
        return maxKlm;
    }

    public Integer getMinYr() {
        return minYr;
    }

    public Integer getMaxYr() {
        return maxYr;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

}
