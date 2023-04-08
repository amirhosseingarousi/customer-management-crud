package net.javaguides.usermanagement.servlets.legalcustomer;

import net.javaguides.usermanagement.dao.LegalCustomerDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/legal/delete")
public class DeleteLegalCustomerServlet extends HttpServlet {

    private LegalCustomerDAO legalCustomerDAO;

    @Override
    public void init() {
        legalCustomerDAO = new LegalCustomerDAO();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            legalCustomerDAO.deleteCustomer(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        res.sendRedirect("list");
    }
}
