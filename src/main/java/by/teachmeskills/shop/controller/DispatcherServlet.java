package by.teachmeskills.shop.controller;

import by.teachmeskills.shop.api.Good.GoodRequest;
import by.teachmeskills.shop.api.User.UserRequest;
import by.teachmeskills.shop.entity.User;
import by.teachmeskills.shop.enums.GoodSubtupe;
import by.teachmeskills.shop.enums.UserRole;
import by.teachmeskills.shop.exeption.UniversalExseption;
import by.teachmeskills.shop.repository.GoodRepository;
import by.teachmeskills.shop.repository.UserRepository;
import by.teachmeskills.shop.service.impl.GoodService;
import by.teachmeskills.shop.service.impl.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DispatcherServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (req.getParameter("authentication") != null) {
                String login = req.getParameter("login");
                String password = req.getParameter("password");
                UserService userService = new UserService();
                userService.authorize(login,password);
            } else if (req.getParameter("register") != null) {
                UserRequest userRequest = new UserRequest();
                userRequest.setName(req.getParameter("name"));
                userRequest.setSurname(req.getParameter("surname"));
                userRequest.setLogin(req.getParameter("login"));
                userRequest.setPassword(req.getParameter("password"));
                userRequest.setRole(UserRole.Role.CLIENT);
                UserService userService = new UserService();
                userService.register(userRequest);
            }else if (req.getParameter("allUsers") != null){
                UserRepository userRepository = new UserRepository();
                req.setAttribute("users", userRepository.all());
                req.getRequestDispatcher("/jsp/allUsers.jsp").forward(req,resp);
            } else if (req.getParameter("addGood") != null) {
                GoodRequest goodRequest = new GoodRequest();
                goodRequest.setName(req.getParameter("name"));
                goodRequest.setCode(Integer.parseInt(req.getParameter("code")));
                String subtype = req.getParameter("subtype");
                switch(subtype){
                    case "PHONE" -> goodRequest.setSubtype(GoodSubtupe.subtype.PHONE);
                    case "AUDIO" -> goodRequest.setSubtype(GoodSubtupe.subtype.AUDIO);
                    case "COMPUTER" -> goodRequest.setSubtype(GoodSubtupe.subtype.COMPUTER);
                    case "WATCH" -> goodRequest.setSubtype(GoodSubtupe.subtype.WATCH);
                    case "APPLIANCES" -> goodRequest.setSubtype(GoodSubtupe.subtype.APPLIANCES);
                    case "TV" -> goodRequest.setSubtype(GoodSubtupe.subtype.TV);
                }
                goodRequest.setPrice(Double.parseDouble(req.getParameter("price")));
                goodRequest.setQuantity(Integer.parseInt(req.getParameter("quantity")));
                GoodService goodService = new GoodService();
                goodService.goodAdd(goodRequest);
            } else if (req.getParameter("allGoods") != null) {
                GoodRepository goodRepository = new GoodRepository();
                req.setAttribute("goods",goodRepository.all());
                req.getRequestDispatcher("/jsp/allGoods.jsp").forward(req,resp);
            }
        }catch (UniversalExseption e){
            req.setAttribute("exp",e.getText());
            req.getRequestDispatcher("/html/Error/error.jsp").forward(req,resp);
        }
    }
}
