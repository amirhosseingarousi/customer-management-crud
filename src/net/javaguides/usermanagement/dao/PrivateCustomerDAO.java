package net.javaguides.usermanagement.dao;

import net.javaguides.usermanagement.model.PrivateCustomer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrivateCustomerDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/customer_management";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Amir92ali";

    private static final String INSERT_CUSTOMER_SQL = "insert into private_customer " +
            "(first_name, last_name, father_name, date_of_birth, national_code, customer_number) " +
            "values (?, ?, ?, ?, ?, ?);";
    private static final String SELECT_CUSTOMER_BY_ID = "select * from private_customer where id = ?";
    private static final String SELECT_ALL_CUSTOMERS = "select * from private_customer";
    private static final String UPDATE_CUSTOMER_SQL = "update private_customer " +
            "set first_name = ?, last_name = ?, father_name = ?, date_of_birth = ?, national_code = ? " +
            "where id = ?;";
    private static final String DELETE_CUSTOMER_SQL = "delete from private_customer where id = ?";

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

    public void insertCustomer(PrivateCustomer customer) throws SQLException {
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_CUSTOMER_SQL)) {

            if (!isCustomerNationalCodeExist(customer)) {
                statement.setString(1, customer.getFirstName());
                statement.setString(2, customer.getLastName());
                statement.setString(3, customer.getFatherName());
                statement.setDate(4, new Date(customer.getDob().getTime()));
                statement.setString(5, customer.getNationalCode());
                statement.setString(6, customer.getCustomerNumber());

                statement.executeUpdate();
            } else {
                System.out.println("Customer national code already exist!");
            }

        }
    }

    private boolean isCustomerNationalCodeExist(PrivateCustomer privateCustomer) throws SQLException {
        List<PrivateCustomer> customers = getAllCustomers();
        String nationalCode = privateCustomer.getNationalCode();
        boolean flag = false;

        for (PrivateCustomer customer : customers) {
            if (customer.getNationalCode().equals(nationalCode)) {
                flag = true;
            }
        }
        return flag;
    }

    public PrivateCustomer selectCustomerById(int id) throws SQLException {
        PrivateCustomer customer = null;
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_CUSTOMER_BY_ID)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String fatherName = resultSet.getString("father_name");
                java.util.Date dob = resultSet.getDate("date_of_birth");
                String nationalCode = resultSet.getString("national_code");
                String customerNumber = resultSet.getString("customer_number");
                customer = new PrivateCustomer(id, firstName, lastName, fatherName, dob, nationalCode, customerNumber);
            }
        }
        return customer;
    }

    public List<PrivateCustomer> getAllCustomers() throws SQLException {
        List<PrivateCustomer> customers = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_CUSTOMERS)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String fatherName = resultSet.getString("father_name");
                java.util.Date dob = resultSet.getDate("date_of_birth");
                String nationalCode = resultSet.getString("national_code");
                String customerNumber = resultSet.getString("customer_number");

                customers.add(new PrivateCustomer(id, firstName, lastName, fatherName, dob, nationalCode, customerNumber));
            }
        }
        return customers;
    }

    public void updateCustomer(PrivateCustomer customer) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_CUSTOMER_SQL)) {

            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getFatherName());
            statement.setDate(4, new Date(customer.getDob().getTime()));
            statement.setString(5, customer.getNationalCode());
            statement.setInt(6, customer.getId());

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
