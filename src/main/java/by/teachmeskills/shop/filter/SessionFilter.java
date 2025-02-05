package by.teachmeskills.shop.filter;

import by.teachmeskills.shop.entity.User;
import by.teachmeskills.shop.enums.UserRole;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class SessionFilter implements jakarta.servlet.Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest= (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession(false);
        if(session == null){
           session = httpServletRequest.getSession(true);
        }
        else {
            if (session.getAttribute("user") == null && httpServletRequest.getParameter("register") == null && httpServletRequest.getParameter("authentication") == null && httpServletRequest.getParameter("allGoods") == null) {
                httpServletRequest.setAttribute("errorMessage", "пройдите авторизацию");
                httpServletRequest.getRequestDispatcher("/html/authorization/authorization.jsp").forward(request, response);
                return;
            }

        }
        chain.doFilter(request,response);
    }
}
