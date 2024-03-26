package by.teachmeskills.shop.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class BasketFilter implements jakarta.servlet.Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest= (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession(false);
        if(httpServletRequest.getParameter("basket") != null && session.getAttribute("user") == null){
            httpServletRequest.setAttribute("errorMessage", "пройдите авторизацию");
            httpServletRequest.getRequestDispatcher("/html/authorization/authorization.jsp").forward(request, response);
        }
        chain.doFilter(request,response);
    }
}
