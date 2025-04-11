package controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Controller {


    default String handle(HttpServletRequest req, HttpServletResponse resp){

        String url = null;

        if(req.getMethod().equals("GET")){
            url = doGet(req, resp);
        }
        if(req.getMethod().equals("POST")){
            url = doPost(req, resp);
        }
        return url;
    };


    String doGet(HttpServletRequest req, HttpServletResponse resp);
    String doPost(HttpServletRequest req, HttpServletResponse resp);
}
