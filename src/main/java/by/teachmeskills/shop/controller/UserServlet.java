package by.teachmeskills.shop.controller;

import by.teachmeskills.shop.entity.User;
import by.teachmeskills.shop.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class UserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("authentication") != null){
            doAutorise(req, resp);
        }
        else{
            doRegister(req, resp);
        }
    }

    protected void doRegister(HttpServletRequest req, HttpServletResponse resp){
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        UserRepository userRepository = new UserRepository();
        User user = new User(name,surname,login,password);
        userRepository.add(user);
    }

    protected void doAutorise(HttpServletRequest req, HttpServletResponse resp){
        try {
            UserRepository userRepository = new UserRepository();
            User user = userRepository.loginSearch(req.getParameter("login"));
            if (user == null) {
                resp.sendRedirect("html/Error/error.jsp");
            }
            else if(!user.getPassword().equals(req.getParameter("password"))){
                resp.sendRedirect("html/Error/error.jsp");
            }
            else{

            }

        }catch (IOException e){

        }
    }
}
