package net.javaguides.usermanagement.servlets.legalcustomer;

import net.javaguides.usermanagement.dao.LegalCustomerDAO;
import net.javaguides.usermanagement.model.LegalCustomer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/legal/update")
public class UpdateLegalCustomerServlet extends HttpServlet {

    private LegalCustomerDAO legalCustomerDAO;

    @Override
    public void init() throws ServletException {
        legalCustomerDAO = new LegalCustomerDAO();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        Date registerDate = null;
        try {
            registerDate = new SimpleDateFormat("yyyy-mm-dd").parse(req.getParameter("registerDate"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String economicCode = req.getParameter("economicCode");

        LegalCustomer customer = new LegalCustomer(id, name, registerDate, economicCode, null);
        try {
            legalCustomerDAO.updateCustomer(customer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        res.sendRedirect("list");
    }
}
