package net.javaguides.usermanagement.servlets.legalcustomer;

import net.javaguides.usermanagement.dao.LegalCustomerDAO;
import net.javaguides.usermanagement.model.LegalCustomer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/legal/list")
public class ListCustomerServlet extends HttpServlet {

    private LegalCustomerDAO legalCustomerDAO;

    @Override
    public void init() {
        legalCustomerDAO = new LegalCustomerDAO();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<LegalCustomer> customers = null;
        try {
            customers = legalCustomerDAO.getAllCustomers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("listCustomer", customers);
        RequestDispatcher dispatcher = req.getRequestDispatcher("legal-customer-list.jsp");
        dispatcher.forward(req, res);
    }
}
