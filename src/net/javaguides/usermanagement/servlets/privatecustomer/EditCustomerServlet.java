package net.javaguides.usermanagement.servlets.privatecustomer;

import net.javaguides.usermanagement.dao.PrivateCustomerDAO;
import net.javaguides.usermanagement.model.PrivateCustomer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/private/edit")
public class EditCustomerServlet extends HttpServlet {

    private PrivateCustomerDAO privateCustomerDAO;

    @Override
    public void init() {
        privateCustomerDAO = new PrivateCustomerDAO();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        PrivateCustomer existingCustomer = null;
        try {
            existingCustomer = privateCustomerDAO.selectCustomerById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("private-customer-form.jsp");
        req.setAttribute("customer", existingCustomer);
        dispatcher.forward(req, res);
    }
}
