package net.javaguides.usermanagement.dao;

import net.javaguides.usermanagement.model.LegalCustomer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LegalCustomerDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/customer_management";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Amir92ali";

    private static final String INSERT_CUSTOMER_SQL = "insert into legal_customer " +
            "(name, register_date, economic_code, customer_number) " +
            "values (?, ?, ?, ?);";
    private static final String SELECT_CUSTOMER_BY_ID = "select * from legal_customer where id = ?";
    private static final String SELECT_ALL_CUSTOMERS = "select * from legal_customer";
    private static final String UPDATE_CUSTOMER_SQL = "update legal_customer " +
            "set name = ?, register_date = ?, economic_code = ? " +
            "where id = ?;";
    private static final String DELETE_CUSTOMER_SQL = "delete from legal_customer where id = ?";

    private Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public void insertCustomer(LegalCustomer customer) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_CUSTOMER_SQL)) {

            if (!isCustomerEconomicCodeExist(customer)) {
                statement.setString(1, customer.getName());
                statement.setDate(2, new Date(customer.getRegisterDate().getTime()));
                statement.setString(3, customer.getEconomicCode());
                statement.setString(4, customer.getCustomerNumber());

                statement.executeUpdate();
            } else {
                System.out.println("Economic code already exists!");
            }

        }
    }

    private boolean isCustomerEconomicCodeExist(LegalCustomer legalCustomer) throws SQLException {
        List<LegalCustomer> customers = getAllCustomers();
        String economicCode = legalCustomer.getEconomicCode();
        boolean flag = false;

        for (LegalCustomer customer : customers) {
            if (customer.getEconomicCode().equals(economicCode)) {
                flag = true;
            }
        }
        return flag;
    }

    public LegalCustomer selectCustomerById(int id) throws SQLException {
        LegalCustomer customer = null;
        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_CUSTOMER_BY_ID)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                Date registerDate = resultSet.getDate("register_date");
                String economicCode = resultSet.getString("economic_code");
                String customerNumber = resultSet.getString("customer_number");

                customer = new LegalCustomer(id, name, registerDate, economicCode, customerNumber);
            }
        }
        return customer;
    }

    public List<LegalCustomer> getAllCustomers() throws SQLException {
        List<LegalCustomer> customers = new ArrayList<>();
        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_CUSTOMERS)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = Integer.parseInt(resultSet.getString("id"));
                String name = resultSet.getString("name");
                java.util.Date registerDate = resultSet.getDate("register_date");
                String economicCode = resultSet.getString("economic_code");
                String customerNumber = resultSet.getString("customer_number");

                customers.add(new LegalCustomer(id, name, registerDate, economicCode, customerNumber));
            }
        }
        return customers;
    }

    public void updateCustomer(LegalCustomer customer) throws SQLException {
        System.out.println("inside updateCustomer() ... dao");
        boolean rowUpdated;
        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_CUSTOMER_SQL)) {

            statement.setString(1, customer.getName());
            statement.setDate(2, new Date(customer.getRegisterDate().getTime()));
            statement.setString(3, customer.getEconomicCode());
            statement.setInt(4, customer.getId());

            statement.executeUpdate();
        }
    }

    public boolean deleteCustomer(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_CUSTOMER_SQL)) {

            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

}
