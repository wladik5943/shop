package by.teachmeskills.shop.controller;

import by.teachmeskills.shop.api.Basket.BasketRequest;
import by.teachmeskills.shop.api.Good.GoodRequest;
import by.teachmeskills.shop.api.Good.GoodResponse;
import by.teachmeskills.shop.api.Order.OrderResponse;
import by.teachmeskills.shop.api.User.UserRequest;
import by.teachmeskills.shop.api.User.UserResponse;
import by.teachmeskills.shop.entity.Good;
import by.teachmeskills.shop.entity.User;
import by.teachmeskills.shop.enums.GoodSubtupe;
import by.teachmeskills.shop.enums.UserRole;
import by.teachmeskills.shop.exeption.UniversalExseption;
import by.teachmeskills.shop.service.BasketService;
import by.teachmeskills.shop.service.GoodService;
import by.teachmeskills.shop.service.OrderService;
import by.teachmeskills.shop.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

public class DispatcherServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();
        GoodService goodService = new GoodService();
        BasketService basketService = new BasketService();
        OrderService orderService = new OrderService();
        try {
            if (req.getParameter("authentication") != null) {
                String login = req.getParameter("login");
                String password = req.getParameter("password");
                UserResponse authorize = userService.authorize(login, password);
//                req.setAttribute("username", authorize.getName());
                HttpSession session = req.getSession(false);
//                session.setAttribute("userId", authorize.getId());
//                session.setAttribute("userRole",authorize.getRole());
                session.setAttribute("user", authorize);
                session.setAttribute("basket",basketService.userBasket(authorize.getId()));
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
                UserResponse register = userService.register(userRequest);
                HttpSession session = req.getSession(true);
                session.setAttribute("userAuthorize",register);
                req.setAttribute("username", req.getParameter("name"));
                req.getRequestDispatcher("jsp/welcome.jsp").forward(req,resp);
            }else if (req.getParameter("allUsers" ) != null ){
                if(((UserResponse)(req.getSession().getAttribute("user"))).getRole() == UserRole.ADMIN) {
                    req.setAttribute("users", userService.all());
                    req.getRequestDispatcher("/jsp/allUsers.jsp").forward(req, resp);
                }else {
                    throw new UniversalExseption("не достаточно прав");
                }
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
                    goodService.goodAdd(goodRequest);
                }
                else {
                    throw new UniversalExseption("не достаточно прав");
                }
                req.getRequestDispatcher("/html/addGoodSuccessfull.html").forward(req,resp);
            } else if (req.getParameter("allGoods") != null) {
                req.setAttribute("goods",goodService.all());
                req.getRequestDispatcher("/jsp/allGoods.jsp").forward(req,resp);
            } else if (req.getParameter("basket")!= null) {
                BasketRequest basketRequest = new BasketRequest();
                basketRequest.setGoodId(Integer.parseInt(req.getParameter("basket")));
                basketRequest.setCount(Integer.parseInt(req.getParameter("count")));
                int userId = ((User)req.getSession(false).getAttribute("user")).getId();
                basketService.addBasket(userId,basketRequest);
                req.getRequestDispatcher("jsp/allGoods.jsp").forward(req,resp);
            }else if(req.getParameter("deleteGoodFromBasket") != null){
                int goodId = Integer.parseInt(req.getParameter("deleteGoodFromBasket"));
                User user = (User) req.getSession().getAttribute("user");
                basketService.deleteGoodFromBasket(user.getId(),goodId);
                Collection<Good> basket = (LinkedList)req.getSession().getAttribute("basket");
                for(Good temp: basket){
                    if(temp.getId() == goodId){
                        basket.remove(temp);
                        break;
                    }
                }
                if(user.getRole() == UserRole.ADMIN)
                    req.getRequestDispatcher("jsp/admin.jsp").forward(req,resp);
                else
                    req.getRequestDispatcher("jsp/welcome.jsp").forward(req,resp);
            }else if(req.getParameter("logout") != null){
                req.getSession().invalidate();
                req.getRequestDispatcher("jsp/welcome.jsp").forward(req,resp);
            } else if (req.getParameter("placeOrder") != null) {
                User user = (User) req.getSession().getAttribute("user");
                Collection<GoodResponse> basket = (LinkedList)req.getSession().getAttribute("basket");
                orderService.placeOrder(user.getId(),basket);
                req.getRequestDispatcher("/html/placeOrderSuccesfull.html").forward(req,resp);
            } else if (req.getParameter("processedOrders") != null) {
                req.setAttribute("processedOrders",orderService.processedOrders());
                req.getRequestDispatcher("/jsp/packedOrder.jsp").forward(req,resp);
            } else if (req.getParameter("showOrder") != null) {
                Collection<GoodResponse> basket = basketService.basketByOrderId(Integer.parseInt(req.getParameter("showOrder")));
                OrderResponse orderResponse = orderService.OrderById(Integer.parseInt(req.getParameter("showOrder")));
                req.setAttribute("basketOrder",basket);
                req.setAttribute("order",orderResponse);
                req.getRequestDispatcher("/jsp/packedOrder.jsp").forward(req,resp);
            }else if(req.getParameter("OrderPack") != null){
                int orderId = Integer.parseInt(req.getParameter("OrderPack"));
                orderService.packedOrder(orderId);
                req.setAttribute("text","Заказ: " + orderId + " собран");
                req.getRequestDispatcher("/jsp/packedOrder.jsp").forward(req,resp);
            }else if(req.getParameter("myOrders") != null){
                User user = (User) req.getSession().getAttribute("user");
                Collection<OrderResponse> orderResponses = orderService.userOrders(user.getId());
                req.setAttribute("userOrders", orderResponses);
                req.getRequestDispatcher("/jsp/myOrders.jsp").forward(req,resp);
            }else if(req.getParameter("cancelOrder") != null){
                orderService.canselOrder(Integer.parseInt(req.getParameter("cancelOrder")));
                User user = (User) req.getSession().getAttribute("user");
                Collection<OrderResponse> orders = orderService.userOrders(user.getId());
                req.setAttribute("userOrders", orders);
                req.getRequestDispatcher("/jsp/myOrders.jsp").forward(req,resp);
            }

        }catch (UniversalExseption e){
            req.setAttribute("exp",e.getText());
            req.getRequestDispatcher("/html/Error/error.jsp").forward(req,resp);
        }
    }

}

