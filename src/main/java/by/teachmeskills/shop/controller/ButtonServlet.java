package by.teachmeskills.shop.controller;

import by.teachmeskills.shop.api.User.UserResponse;
import by.teachmeskills.shop.entity.User;
import by.teachmeskills.shop.enums.UserRole;
import by.teachmeskills.shop.service.BasketService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


public class ButtonServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(((UserResponse)(req.getSession().getAttribute("user"))).getRole() == UserRole.CLIENT && (req.getParameter("allUsers") != null ||
                req.getParameter("packedOrder") != null ||  req.getParameter("admin") != null)){
            req.setAttribute("exp","не достаточно прав");
            req.getRequestDispatcher("/html/Error/error.jsp").forward(req,resp);
        }

        if(req.getParameter("allGoods") != null){
            req.getRequestDispatcher("/jsp/allGoods.jsp").forward(req,resp);
        }else if(req.getParameter("allUsers") != null){
            req.getRequestDispatcher("/jsp/allUsers.jsp").forward(req,resp);
        }else if(req.getParameter("addGood") != null){
            req.getRequestDispatcher("/html/addGood/addGood.html").forward(req,resp);
        }else if(req.getParameter("admin") != null){
            req.getRequestDispatcher("/jsp/admin.jsp").forward(req,resp);
        } else if (req.getParameter("myOrders") != null) {
            req.getRequestDispatcher("/jsp/myOrders.jsp").forward(req,resp);
        } else if (req.getParameter("packedOrder") != null) {
            req.getRequestDispatcher("/jsp/packedOrder.jsp").forward(req,resp);
        } else if (req.getParameter("master") != null) {
            User user = (User) req.getSession().getAttribute("user");

            if(user == null)
                req.getRequestDispatcher("jsp/welcome.jsp").forward(req,resp);
            BasketService basketService = new BasketService();
            req.getSession().setAttribute("basket",basketService.userBasket(user.getId()));
            if(user.getRole() == UserRole.CLIENT)
                req.getRequestDispatcher("jsp/welcome.jsp").forward(req,resp);
            else
            req.getRequestDispatcher("jsp/admin.jsp").forward(req,resp);
        }

    }
}
