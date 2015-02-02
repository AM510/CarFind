<%-- 
    Document   : results
    Created on : Dec 4, 2014, 10:45:20 AM
    Author     : xnikos
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="parser.Car"%>
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" media="all" href="css/results.css">

        <%
            String klmPriceTableString = (String) request.getAttribute("klm");
            if (klmPriceTableString.equals("nores")) {
                response.sendRedirect("CarNotFound.html");
                return;
            }
            String car_model = (String) request.getAttribute("car");
            car_model = car_model.toUpperCase();
            String yrPriceTableString = (String) request.getAttribute("yr");
            String geoTableString = (String) request.getAttribute("geo");
            String ccTableString = (String) request.getAttribute("cc");
            Integer minYr = (Integer) request.getAttribute("minyr");
            Integer maxYr = (Integer) request.getAttribute("maxyr");
            Integer minPrice = (Integer) request.getAttribute("minprice");
            Integer maxPrice = (Integer) request.getAttribute("maxprice");
            Integer minKlm = (Integer) request.getAttribute("minklm");
            Integer maxKlm = (Integer) request.getAttribute("maxklm");
            car_model = car_model.replaceAll("_", "");
        %>

        <script type='text/javascript' src='https://www.google.com/jsapi'></script>
        <script type='text/javascript'>
                    google.load('visualization', '1', {'packages': ['geochart']});
                    google.setOnLoadCallback(drawMarkersMap);
                    function drawMarkersMap() {
                    var data = google.visualization.arrayToDataTable([
                            ['City', 'Average Price', '# of Ads']
            <% out.print(geoTableString);%>
                    ]);
                            var options = {
                            region: 'GR',
                                    displayMode: 'markers',
                                    colorAxis: {colors: ['green', 'blue']}
                            };
                            var chart = new google.visualization.GeoChart(document.getElementById('chart_div2'));
                            chart.draw(data, options);
                    }
            ;</script>
        <script type="text/javascript" src="https://www.google.com/jsapi"></script>
        <script type="text/javascript">
                    google.load("visualization", "1", {packages: ["corechart"]});
                    google.setOnLoadCallback(drawChart);
                    function drawChart() {
                    var data = google.visualization.arrayToDataTable([
                            ['First Registration', 'Price']
            <% out.print(yrPriceTableString);%>
                    ]);
                            var options = {
                            title: '(First Registration / Price) Scatter Graph',
                                    hAxis: {title: 'First Registration', minValue: <%out.print(minYr);%>, maxValue: <%out.print(maxYr);%>},
                                    vAxis: {title: 'Price', minValue: <%out.print(minPrice);%>, maxValue: <%out.print(maxPrice);%>},
                                    legend: 'none',
                                    width: 800,
                                    height: 440
                            };
                            var chart = new google.visualization.ScatterChart(document.getElementById('chart_div'));
                            chart.draw(data, options);
                    }
        </script>
        <script type="text/javascript" src="https://www.google.com/jsapi"></script>
        <script type="text/javascript">
                    google.load("visualization", "1", {packages: ["corechart"]});
                    google.setOnLoadCallback(drawChart);
                    function drawChart() {
                    var data = google.visualization.arrayToDataTable([
                            ['Kilometers', 'Price']
            <% out.print(klmPriceTableString);%>
                    ]);
                            var options = {
                            title: '(Kilometers / Price) Scatter Graph',
                                    hAxis: {title: 'Kilometers', minValue: <%out.print(minKlm);%>, maxValue: <%out.print(maxKlm);%>},
                                    vAxis: {title: 'Price', minValue: <%out.print(minPrice);%>, maxValue: <%out.print(maxPrice);%>},
                                    legend: 'none',
                                    width: 800,
                                    height: 440
                            };
                            var chart = new google.visualization.ScatterChart(document.getElementById('chart_div1'));
                            chart.draw(data, options);
                    }
        </script>
        <script type="text/javascript"
                src="https://www.google.com/jsapi?autoload={
                'modules':[{
                'name':'visualization',
                'version':'1',
                'packages':['corechart']
                }]
        }"></script>

        <script type="text/javascript">
                    google.setOnLoadCallback(drawChart);
                    function drawChart() {
                    var data = google.visualization.arrayToDataTable([
                            ['Engine Size', 'Avg. Price', 'Min. Price']
            <% out.print(ccTableString); %>
                    ]);
                            var options = {
                            title: '(CC / Price) Line Graph',
                                    curveType: 'function',
                                    legend: { position: 'bottom' },
                                    width: 800,
                                    height: 440
                            };
                            var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));
                            chart.draw(data, options);
                    }
        </script>
        <script type="text/javascript">
            window.onload = function(){
            var menu_elements = document.querySelectorAll('.menu>li'),
                    menu_length = menu_elements.length;
                    for (var i = 0; i < menu_length; i++) {
            menu_elements[i].addEventListener('click', function (e) {
            var target = document.querySelector('.container>.' + e.target.classList[0]); // clicked element
                    Array.prototype.filter.call(target.parentNode.children, function (siblings) {
                    siblings.style.display = 'none'; // hide sibling elements
                    });
                    target.style.display = 'block'; // show clicked element
            });
                    }
            }
        </script>

        <title>Price Charts for used <% out.print(car_model);%> cars</title>
    </head>
    <body>
        

        <div id="wrapper">
            <h1>Price Charts for used <br><% out.print(car_model);%></h1>
            <h3>Select the Chart you would like to review</h3>
            <ul class="menu">
                <li class="toggle1">GeoChart</li>
                <li class="toggle2">First Reg.</li>
                <li class="toggle3">Kilometers</li>
                <li class="toggle4">Engine Size</li>
            </ul>
            <div class="container">
                    <div class="toggle1" id="chart_div2" style="width: 800px; height: 440px;"></div>
                    <div class="toggle2" id="chart_div" style="width: 800px; height: 440px;"></div>
                    <div class="toggle3" id="chart_div1" style="width: 800px; height: 440px;"></div>
                    <div class="toggle4" id="curve_chart" style="width: 800px; height: 440px;"></div>
            </div>
            <br>
            <h2>
                <a href="../CarFind">New Search</a>
            </h2>
            <br>
            <h4>Disclaimer: All data used in the charts shown are property of http://car.gr and CarFind does not carry any responsibility regarding the integrity of the Ads. 
                CarFind should only be used as a complimentary tool for more productive searching of car Ads. 
                <br>Vehicles declared as damaged are not included in results of CarFind.</h4>
            <br><br>
        </div>
    </body>
</html>
