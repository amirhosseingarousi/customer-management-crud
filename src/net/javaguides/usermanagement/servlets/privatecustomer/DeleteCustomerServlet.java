package net.javaguides.usermanagement.servlets.privatecustomer;

import net.javaguides.usermanagement.dao.PrivateCustomerDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/private/delete")
public class DeleteCustomerServlet extends HttpServlet {

    private PrivateCustomerDAO privateCustomerDAO;

    @Override
    public void init() {
        privateCustomerDAO = new PrivateCustomerDAO();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            privateCustomerDAO.deleteCustomer(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        res.sendRedirect("list");
    }
}
