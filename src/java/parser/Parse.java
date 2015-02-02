/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author xnikos
 */
public class Parse extends Config {

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
        String toPrice = "<";
        toPrice = toPrice.concat(request.getParameter("toPrice"));

        CarModel carMod = new CarModel(this.getFormManuf(), this.getFormModel());
        CarChart Charts = new CarChart();
        ArrayList<String> str = new ArrayList();
        ArrayList<Car> list = Charts.getList(carMod, "/used-cars/" + this.getFormManuf() + "/" + this.getFormModel() + ".html?engine_size=" + fromCC + "&engine_size=" + toCC + "&mileage=" + fromKlm + "&mileage=" + toKlm + "&engine_power=&engine_power=&price=" + fromPrice + "&price=" + toPrice + "&registration=" + fromYear + "&registration=" + toYear + "&variant=&doors=&doors=&seats=&seats=&offer_type=sale");
        if (list.isEmpty()) {
            str.add("nores");
        } else {
            ChartStrings input = new ChartStrings();
            str = input.createInput(list);
            str.add(carMod.getManuf() + " " + carMod.getModel());
            request.setAttribute("yr", str.get(1));
            request.setAttribute("geo", str.get(2));
            request.setAttribute("cc", str.get(3));
            request.setAttribute("car", str.get(4));
            request.setAttribute("minyr", input.getMinYr());
            request.setAttribute("maxyr", input.getMaxYr());
            request.setAttribute("minprice", input.getMinPrice());
            request.setAttribute("maxprice", input.getMaxPrice());
            request.setAttribute("minklm", input.getMinKlm());
            request.setAttribute("maxklm", input.getMaxKlm());
        }
        request.setAttribute("klm", str.get(0));
        RequestDispatcher view = request.getRequestDispatcher("/results.jsp");
        view.forward(request, response);
    }

}
