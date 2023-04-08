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
import java.util.List;

@WebServlet("/private/list")
public class ListCustomerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private PrivateCustomerDAO privateCustomerDAO;

    @Override
    public void init() {
        privateCustomerDAO = new PrivateCustomerDAO();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println(req.getServletPath());
        List<PrivateCustomer> customers = null;
        try {
            customers = privateCustomerDAO.getAllCustomers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("listCustomer", customers);
        RequestDispatcher dispatcher = req.getRequestDispatcher("private-customer-list.jsp");
        dispatcher.forward(req, res);
    }
}
