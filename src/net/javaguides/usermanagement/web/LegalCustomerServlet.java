package net.javaguides.usermanagement.web;

import net.javaguides.usermanagement.dao.LegalCustomerDAO;
import net.javaguides.usermanagement.model.LegalCustomer;

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

//@WebServlet("/legal")
public class LegalCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LegalCustomerDAO legalCustomerDAO;

    @Override
    public void init()  {
        legalCustomerDAO = new LegalCustomerDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
        System.out.println("do post");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getServletPath();
//        System.out.println(action + "here");

        try {
            switch (action) {
                case "/new":
                    showNewForm(req, res);
                    break;
                case "/insert":
                    insertCustomer(req, res);
                    break;
                case "/edit":
                    showEditForm(req, res);
                    break;
                case "/delete":
                    deleteCustomer(req, res);
                    break;
                case "/update":
                    updateCustomer(req, res);
                    break;
                default:
                    listCustomer(req, res);
                    System.out.println(req.getServletPath());
                    break;
            }
        } catch (SQLException | ParseException e) {

        }
    }

    private void listCustomer(HttpServletRequest req, HttpServletResponse res) throws SQLException, ServletException, IOException {
        List<LegalCustomer> customers = legalCustomerDAO.getAllCustomers();
        req.setAttribute("listCustomer", customers);
        RequestDispatcher dispatcher = req.getRequestDispatcher("legal/legal-customer-list.jsp");
        dispatcher.forward(req, res);
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("legal/legal-customer-form.jsp");
        dispatcher.forward(req, res);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse res) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        LegalCustomer existingCustomer = legalCustomerDAO.selectCustomerById(id);
        req.setAttribute("customer", existingCustomer);
        RequestDispatcher dispatcher = req.getRequestDispatcher("legal-customer-form.jsp");
        dispatcher.forward(req, res);
    }

    private void insertCustomer(HttpServletRequest req, HttpServletResponse res) throws ParseException, SQLException, IOException {
        String name = req.getParameter("name");
        Date registerDate = new SimpleDateFormat("yyyy-mm-dd").parse(req.getParameter("registerDate"));
        String economicCode = req.getParameter("economicCode");

        LegalCustomer customer = new LegalCustomer(name, registerDate, economicCode, null);
        customer.setCustomerNumber(UUID.randomUUID().toString());
        legalCustomerDAO.insertCustomer(customer);
        res.sendRedirect("list");
    }

    private void updateCustomer(HttpServletRequest req, HttpServletResponse res) throws ParseException, SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        System.out.println("id: " + id);
        String name = req.getParameter("name");
        System.out.println("name: " + name);
        Date registerDate = new SimpleDateFormat("yyyy-mm-dd").parse(req.getParameter("registerDate"));
        System.out.println("date: " + registerDate);
        String economicCode = req.getParameter("economicCode");
        System.out.println("economic code: " + economicCode);

        LegalCustomer customer = new LegalCustomer(id, name, registerDate, economicCode, null);
        legalCustomerDAO.updateCustomer(customer);
        res.sendRedirect("list");
    }

    private void deleteCustomer(HttpServletRequest req, HttpServletResponse res) throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        legalCustomerDAO.deleteCustomer(id);
        res.sendRedirect("list");
    }
}
