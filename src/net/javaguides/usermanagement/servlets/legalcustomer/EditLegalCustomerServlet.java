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

@WebServlet("/legal/edit")
public class EditLegalCustomerServlet extends HttpServlet {

    private LegalCustomerDAO legalCustomerDAO;

    @Override
    public void init() {
        legalCustomerDAO = new LegalCustomerDAO();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        LegalCustomer existingCustomer = null;
        try {
            existingCustomer = legalCustomerDAO.selectCustomerById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("customer", existingCustomer);
        RequestDispatcher dispatcher = req.getRequestDispatcher("legal-customer-form.jsp");
        dispatcher.forward(req, res);
    }
}
