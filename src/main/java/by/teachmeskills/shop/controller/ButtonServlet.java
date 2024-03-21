package by.teachmeskills.shop.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


public class ButtonServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(req.getParameter("allGoods") != null){
            req.getRequestDispatcher("/jsp/allGoods.jsp").forward(req,resp);
        }else if(req.getParameter("allUsers") != null){
            req.getRequestDispatcher("/jsp/allUsers.jsp").forward(req,resp);
        }else if(req.getParameter("addGood") != null){
            req.getRequestDispatcher("/html/addGood/addGood.html").forward(req,resp);
        }else if(req.getParameter("admin") != null){
            req.getRequestDispatcher("/jsp/admin.jsp").forward(req,resp);
        }

    }
}
