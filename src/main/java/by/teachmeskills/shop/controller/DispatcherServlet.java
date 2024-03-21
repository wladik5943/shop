package by.teachmeskills.shop.controller;

import by.teachmeskills.shop.api.Basket.BasketRequest;
import by.teachmeskills.shop.api.Good.GoodRequest;
import by.teachmeskills.shop.api.User.UserRequest;
import by.teachmeskills.shop.api.User.UserResponse;
import by.teachmeskills.shop.entity.User;
import by.teachmeskills.shop.enums.GoodSubtupe;
import by.teachmeskills.shop.enums.UserRole;
import by.teachmeskills.shop.exeption.UniversalExseption;
import by.teachmeskills.shop.repository.GoodRepository;
import by.teachmeskills.shop.repository.UserRepository;
import by.teachmeskills.shop.service.BasketService;
import by.teachmeskills.shop.service.GoodService;
import by.teachmeskills.shop.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;

public class DispatcherServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (req.getParameter("authentication") != null) {
                String login = req.getParameter("login");
                String password = req.getParameter("password");
                UserService userService = new UserService();
                UserResponse authorize = userService.authorize(login, password);
//                req.setAttribute("username", authorize.getName());
                HttpSession session = req.getSession(false);
//                session.setAttribute("userId", authorize.getId());
//                session.setAttribute("userRole",authorize.getRole());
                session.setAttribute("user", authorize);
                if(authorize.getRole() == UserRole.ADMIN)
                    req.getRequestDispatcher("jsp/admin.jsp").forward(req,resp);
                else
                    req.getRequestDispatcher("jsp/welcome.jsp").forward(req,resp);
            } else if (req.getParameter("register") != null) {
                UserRequest userRequest = new UserRequest();
                userRequest.setName(req.getParameter("name"));
                userRequest.setSurname(req.getParameter("surname"));
                userRequest.setLogin(req.getParameter("login"));
                userRequest.setPassword(req.getParameter("password"));
                userRequest.setRole(UserRole.CLIENT);
                UserService userService = new UserService();
                UserResponse register = userService.register(userRequest);
                HttpSession session = req.getSession(true);
                session.setAttribute("userAuthorize",register);
                req.setAttribute("username", req.getParameter("name"));
                req.getRequestDispatcher("jsp/welcome.jsp").forward(req,resp);
            }else if (req.getParameter("allUsers" ) != null ){
                UserService userService = new UserService();
                req.setAttribute("users", userService.all());
                req.getRequestDispatcher("/jsp/allUsers.jsp").forward(req,resp);
            } else if (req.getParameter("addGood") != null) {
                if(((UserResponse)(req.getSession().getAttribute("user"))).getRole() == UserRole.ADMIN) {
                    GoodRequest goodRequest = new GoodRequest();
                    goodRequest.setName(req.getParameter("name"));
                    goodRequest.setCode(Integer.parseInt(req.getParameter("code")));
                    String subtype = req.getParameter("subtype");
                    switch (subtype) {
                        case "PHONE" -> goodRequest.setSubtype(GoodSubtupe.PHONE);
                        case "AUDIO" -> goodRequest.setSubtype(GoodSubtupe.AUDIO);
                        case "COMPUTER" -> goodRequest.setSubtype(GoodSubtupe.COMPUTER);
                        case "WATCH" -> goodRequest.setSubtype(GoodSubtupe.WATCH);
                        case "APPLIANCES" -> goodRequest.setSubtype(GoodSubtupe.APPLIANCES);
                        case "TV" -> goodRequest.setSubtype(GoodSubtupe.TV);
                    }
                    goodRequest.setPrice(Double.parseDouble(req.getParameter("price")));
                    goodRequest.setQuantity(Integer.parseInt(req.getParameter("quantity")));
                    GoodService goodService = new GoodService();
                    goodService.goodAdd(goodRequest);
                }
                else {
                    throw new UniversalExseption("не достаточно прав");
                }
                req.getRequestDispatcher("/html/addGoodSuccessfull.html").forward(req,resp);
            } else if (req.getParameter("allGoods") != null) {
                GoodService goodService = new GoodService();
                req.setAttribute("goods",goodService.all());
                req.getRequestDispatcher("/jsp/allGoods.jsp").forward(req,resp);
            } else if (req.getParameter("basket")!= null) {
                BasketRequest basketRequest = new BasketRequest();
                basketRequest.setGoodId(Integer.parseInt(req.getParameter("basket")));
                basketRequest.setCount(Integer.parseInt(req.getParameter("count")));
                int userId = ((User)req.getSession(false).getAttribute("user")).getId();
                BasketService basketService = new BasketService();
                basketService.addBasket(userId,basketRequest);
            }
        }catch (UniversalExseption e){
            req.setAttribute("exp",e.getText());
            req.getRequestDispatcher("/html/Error/error.jsp").forward(req,resp);
        }
    }
}

//создать buttonservlet для перенаправления сессии между страницами
