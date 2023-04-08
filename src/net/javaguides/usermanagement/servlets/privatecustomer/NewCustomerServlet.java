package net.javaguides.usermanagement.servlets.privatecustomer;

import net.javaguides.usermanagement.dao.PrivateCustomerDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/private/new")
public class NewCustomerServlet extends HttpServlet {

    private PrivateCustomerDAO privateCustomerDAO;

    @Override
    public void init() {
        privateCustomerDAO = new PrivateCustomerDAO();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("private-customer-form.jsp");
        dispatcher.forward(req, res);
    }
}
