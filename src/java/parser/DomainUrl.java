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
class DomainUrl {
    private final String domainUrl;
    private final Domain domain;

    public DomainUrl(String domainUrl, Domain domain) {
        this.domainUrl = domainUrl;
        this.domain = domain;
    }

    public String getDomainUrl() {
        return domainUrl;
    }

    public Domain getDomain() {
        return domain;
    }
    
    
}
