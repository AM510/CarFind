/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author xnikos
 */
public class FakeServlet extends Config{
    
    /**
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        this.setFormManuf(request.getParameter("manuf").toLowerCase());
        this.setFormModel(request.getParameter("model").toLowerCase());
        String fromYear = ">";
        fromYear = fromYear.concat(request.getParameter("fromYear"));
        String toYear = "<";
        toYear = toYear.concat(request.getParameter("toYear"));
        String fromCC = ">";
        fromCC = fromCC.concat(request.getParameter("fromCC"));
        String toCC = "<";
        toCC = toCC.concat(request.getParameter("toCC"));
        String fromKlm = ">";
        fromKlm = fromKlm.concat(request.getParameter("fromKlm"));
        String toKlm = "<";
        toKlm = toKlm.concat(request.getParameter("toKlm"));
        String fromPrice = ">";
        fromPrice = fromPrice.concat(request.getParameter("fromPrice"));
        String toPrice = request.getParameter("toPrice");

        request.setAttribute("manuf",this.getFormManuf());
        request.setAttribute("model", this.getFormModel());
        request.setAttribute("fromYear", fromYear);
        request.setAttribute("toYear", toYear);
        request.setAttribute("fromCC", fromCC);
        request.setAttribute("toCC", toCC);
        request.setAttribute("fromKlm", fromKlm);
        request.setAttribute("toKlm", toKlm);
        request.setAttribute("fromPrice", fromPrice);
        request.setAttribute("toPrice", toPrice);
        
        RequestDispatcher view = request.getRequestDispatcher("/pleaseWait.jsp");
        view.forward(request, response);
        
    }

}
