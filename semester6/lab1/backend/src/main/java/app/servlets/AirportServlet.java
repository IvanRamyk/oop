package app.servlets;


import app.entities.Airport;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AddServlet", urlPatterns = "/airports")

public class AirportServlet extends HttpServlet {
    List<Airport> airports = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        airports.add(new Airport(1, "A", "A city", "A country"));
        airports.add(new Airport(2, "B", "B city", "B country"));
        airports.add(new Airport(3, "C", "C city", "C country"));
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.setCharacterEncoding("UTF-8");
        String airportsJson = new Gson().toJson(airports);
        out.print(airportsJson);
        out.flush();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) {
            /*report an error*/
        }

        Gson gson = new Gson();
        Airport airport = gson.fromJson(jb.toString(), Airport.class);
        System.out.println(airport);
    }

}