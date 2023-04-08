package net.javaguides.usermanagement.servlets.privatecustomer;

import net.javaguides.usermanagement.dao.PrivateCustomerDAO;
import net.javaguides.usermanagement.model.PrivateCustomer;

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
import java.util.UUID;

@WebServlet("/private/insert")
public class InsertCustomerServlet extends HttpServlet {

    private PrivateCustomerDAO privateCustomerDAO;

    @Override
    public void init() {
        privateCustomerDAO = new PrivateCustomerDAO();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String fatherName = req.getParameter("fatherName");
        String nationalCode = req.getParameter("nationalCode");
        Date dob = null;
        try {
            dob = new SimpleDateFormat("yyyy-mm-dd").parse(req.getParameter("dob"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        PrivateCustomer customer = new PrivateCustomer(firstName, lastName, fatherName, dob, nationalCode, null);
        customer.setCustomerNumber(UUID.randomUUID().toString());
        try {
            privateCustomerDAO.insertCustomer(customer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        res.sendRedirect("list");
    }
}
