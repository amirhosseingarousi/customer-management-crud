package net.javaguides.usermanagement.model;

import java.util.Date;

public class PrivateCustomer {
    private int id;
    private String firstName;
    private String lastName;
    private String fatherName;
    private Date dob;
    private String nationalCode;
    private String customerNumber;

    public PrivateCustomer(String firstName, String lastName, String fatherName, Date dob, String nationalCode, String customerNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.dob = dob;
        this.nationalCode = nationalCode;
        this.customerNumber = customerNumber;
    }

    public PrivateCustomer(int id, String firstName, String lastName, String fatherName, Date dob, String nationalCode, String customerNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.dob = dob;
        this.nationalCode = nationalCode;
        this.customerNumber = customerNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }
}
