/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import javax.servlet.http.HttpServlet;


/**
 *
 * @author xnikos
 */
public class Config extends HttpServlet {

    // starting url to parse
    private final String url = "http://www.car.gr";
    // doc.select input for number of adds text
    private final String numOfAdsSelect = ".breadcrumb .active";
    // doc.select input for ads
    private final String adsSelect = ".clsfd_list_row_group";
    // page number variable (followed by =) for domain
    private final String pgVar = "pg=";
    // cc select
    private final String ccSelect = "span[class=engine]";
    // ad klm select
    private final String klmSelect = "div[class=visible-xs hidden-print mil]";
    // region relect
    private final String regionSelect = "span[itemprop=address]";
    // release date select
    private final String dateSelect = "div[itemprop=releaseDate]";
    // price select
    private final String priceSelect = "span[itemprop=price]";
    private String formManuf;
    private String formModel;
    
    public String getUrl() {
        return url;
    }

    public String getNumOfAdsSelect() {
        return numOfAdsSelect;
    }

    public String getAdsSelect() {
        return adsSelect;
    }

    public String getPgVar() {
        return pgVar;
    }

    public String getKlmSelect() {
        return klmSelect;
    }

    public String getRegionSelect() {
        return regionSelect;
    }

    public String getDateSelect() {
        return dateSelect;
    }

    public String getPriceSelect() {
        return priceSelect;
    }

    public String getCcSelect() {
        return ccSelect;
    }

    public String getFormManuf() {
        return formManuf;
    }

    public String getFormModel() {
        return formModel;
    }

    public void setFormManuf(String formManuf) {
        this.formManuf = formManuf;
    }

    public void setFormModel(String formModel) {
        this.formModel = formModel;
    }

}
