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

@WebServlet("/private/update")
public class UpdateCustomerServlet extends HttpServlet {

    private PrivateCustomerDAO privateCustomerDAO;

    @Override
    public void init() {
        privateCustomerDAO = new PrivateCustomerDAO();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String fatherName = req.getParameter("fatherName");
        Date dob = null;
        try {
            dob = new SimpleDateFormat("yyyy-mm-dd").parse(req.getParameter("dob"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String nationalCode = req.getParameter("nationalCode");

        PrivateCustomer customer = new PrivateCustomer(id, firstName, lastName, fatherName, dob, nationalCode, null);
        try {
            privateCustomerDAO.updateCustomer(customer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        res.sendRedirect("list");
    }
}
