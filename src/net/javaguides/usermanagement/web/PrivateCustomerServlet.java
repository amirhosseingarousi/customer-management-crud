package net.javaguides.usermanagement.web;

import net.javaguides.usermanagement.dao.PrivateCustomerDAO;
import net.javaguides.usermanagement.model.PrivateCustomer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

//@WebServlet("/private")
public class PrivateCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PrivateCustomerDAO privateCustomerDAO;

    @Override
    public void init() {
        privateCustomerDAO = new PrivateCustomerDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(req, res);
                    break;
                case "/insert":
                    insertCustomer(req, res);
                    break;
                case "/delete":
                    deleteCustomer(req, res);
                    break;
                case "/edit":
                    showEditForm(req, res);
                    break;
                case "/update":
                    updateCustomer(req, res);
                    break;
                default:
                    listCustomer(req, res);
                    break;
            }
        } catch (SQLException | ParseException e) {

        }
    }

    private void listCustomer(HttpServletRequest req, HttpServletResponse res) throws SQLException, ServletException, IOException {
        List<PrivateCustomer> customers = privateCustomerDAO.getAllCustomers();
        req.setAttribute("listCustomer", customers);
//        RequestDispatcher dispatcher = req.getRequestDispatcher("private/private-customer-list.jsp");
//        dispatcher.forward(req, res);
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("private/private-customer-form.jsp");
        dispatcher.forward(req, res);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse res) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        PrivateCustomer existingCustomer = privateCustomerDAO.selectCustomerById(id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("private/private-customer-form.jsp");
        req.setAttribute("customer", existingCustomer);
        dispatcher.forward(req, res);
    }

    private void insertCustomer(HttpServletRequest req, HttpServletResponse res) throws ParseException, SQLException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String fatherName = req.getParameter("fatherName");
        String nationalCode = req.getParameter("nationalCode");
        Date dob = new SimpleDateFormat("yyyy-mm-dd").parse(req.getParameter("dob"));

        PrivateCustomer customer = new PrivateCustomer(firstName, lastName, fatherName, dob, nationalCode, null);
        customer.setCustomerNumber(UUID.randomUUID().toString());
        privateCustomerDAO.insertCustomer(customer);
        res.sendRedirect("list");
    }

    private void updateCustomer(HttpServletRequest req, HttpServletResponse res) throws ParseException, SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String fatherName = req.getParameter("fatherName");
        Date dob = new SimpleDateFormat("yyyy-mm-dd").parse(req.getParameter("dob"));
        String nationalCode = req.getParameter("nationalCode");

        PrivateCustomer customer = new PrivateCustomer(id, firstName, lastName, fatherName, dob, nationalCode, null);
        privateCustomerDAO.updateCustomer(customer);
        res.sendRedirect("list");
    }

    private void deleteCustomer(HttpServletRequest req, HttpServletResponse res) throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        privateCustomerDAO.deleteCustomer(id);
        res.sendRedirect("list");
    }
}
