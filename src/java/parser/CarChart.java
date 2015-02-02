/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author xnikos
 */
// xreiazetai diorthwsh h diaxeirish to url
public class CarChart extends Config {

    public ArrayList<Car> getList(CarModel carMod, String webaddress) {
        Domain domain = new Domain(this.getUrl() + webaddress);
        
        ArrayList<Car> list = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(domain.getDomainUrl()).get();

            // Number of Ads
            String numberOfAdsString = doc.body().select(this.getNumOfAdsSelect()).text();
            numberOfAdsString = numberOfAdsString.substring(0, numberOfAdsString.indexOf(" "));
            int numberOfAds = Integer.parseInt(numberOfAdsString);

            // Number of Adds per page, each add is in a class=clsfd_list_row_group
            Elements Ads = doc.body().select(this.getAdsSelect());
            int adsPerPage = Ads.size();
            // Parse cars and add them to list
            int klm;
            String region;
            String link;
            Element linkEl;
            int postNum;
            CarDate firstReg;
            int price;
            int cc;
            for (int i = 0; i < numberOfAds; i++) {
                if ((i % adsPerPage == 0) && (i != 0)) {
                    doc = Jsoup.connect(domain.getDomainUrl() + "&" + this.getPgVar() + Integer.toString(i / Ads.size() + 1)).get();
                    Ads = doc.body().select(this.getAdsSelect());
                }
                // Run through elements, create Car objects and add them to the ArrayList

                // link is the href target of the first <a> in div
                linkEl = Ads.get(i%adsPerPage).select("a").first();
                link = "http://www.car.gr";
                link = link.concat(linkEl.attr("href"));
                
                // klm is passed in format "x&nbsp;..." we only need x so, use region variable as temp to get the number
                region = Ads.get(i % adsPerPage).select(this.getKlmSelect()).text();
                region = region.substring(0, region.lastIndexOf("k") - 1);
                klm = Integer.parseInt(region.replace(".", ""));

                // release date, using region variable ase temp again. format passed "MM.....YY"
                region = Ads.get(i % adsPerPage).select(this.getDateSelect()).text();
                firstReg = new CarDate(Integer.parseInt(region.substring(0, region.indexOf("/") - 1)), Integer.parseInt(region.substring(region.indexOf("/") + 2, region.length())));

                // price, format passed "....;x.y" (we want it as one integer xy)
                region = Ads.get(i % adsPerPage).select(this.getPriceSelect()).text();
                // case: ask price
                // form passes minimum value as 1eu if user doesn't choose or chooses 0
                // since 0 price is used only on vehicles that have the "ask price" option
                region = region.substring(2, region.length());
                price = Integer.parseInt(region.replace(".", ""));

                // cc, format xcc
                region = Ads.get(i % adsPerPage).select(this.getCcSelect()).text();
                cc = Integer.parseInt(region.substring(0, region.indexOf("c")));

                // region, format passed city, xxxxx
                region = Ads.get(i % adsPerPage).select(this.getRegionSelect()).text();
                postNum = Integer.parseInt(region.substring(region.length() - 5, region.length()));
                region = region.substring(0, region.indexOf(","));

                // add object to array
                list.add(i, new Car(i, link, carMod.getManuf(), carMod.getModel(), cc, klm, region, postNum, firstReg, price));
                
            }

        } catch (IOException ex) {
            Logger.getLogger(CarChart.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;

    }

}
