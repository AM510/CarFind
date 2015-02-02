/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.util.Date;

/**
 *
 * @author xnikos
 */
public class HtmlPage {
    private int id;
    private String Html;
    private DomainUrl domainUrl;
    private Date created;

    public HtmlPage(String Html, DomainUrl domainUrl, Date created) {
        this.Html = Html;
        this.domainUrl = domainUrl;
        this.created = created;
    }
    
    public void save() {

    }

}
