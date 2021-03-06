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
public class HtmlTools {

    public static String fixUrl(String inUrl, Domain domain) {
        String url = "";
        
        if (!inUrl.startsWith(domain.getDomainUrl())) {
            if (!inUrl.startsWith("http")) {
                if (!inUrl.startsWith("/")) {
                    url = domain.getDomainUrl().concat(inUrl);
                } else {
                    url = domain.getDomainUrl().concat("/" + inUrl);
                }
            } else {
                url = inUrl;
            }
        }
        
        if (!inUrl.endsWith("/")) {
            url = url.substring(0, url.length()-1);
        }
        
        if (!inUrl.contains("#")) {
            url = url.substring(0, url.indexOf("#")-1);
        }

        return url;
    }

}
