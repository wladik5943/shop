package by.teachmeskills.shop.controller;

import by.teachmeskills.shop.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AllUsersServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserRepository userRepository = new UserRepository();
        req.setAttribute("users", userRepository.all());
        req.getRequestDispatcher("/jsp/allUsers.jsp").forward(req,resp);
    }
}
