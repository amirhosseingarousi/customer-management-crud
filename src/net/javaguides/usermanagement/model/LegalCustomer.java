package net.javaguides.usermanagement.model;

import java.util.Date;

public class LegalCustomer {

    private int id;
    private String name;
    private Date registerDate;
    private String economicCode;
    private String customerNumber;

    public LegalCustomer(String name, Date registerDate, String economicCode, String customerNumber) {
        this.name = name;
        this.registerDate = registerDate;
        this.economicCode = economicCode;
        this.customerNumber = customerNumber;
    }

    public LegalCustomer(int id, String name, Date registerDate, String economicCode, String customerNumber) {
        this.id = id;
        this.name = name;
        this.registerDate = registerDate;
        this.economicCode = economicCode;
        this.customerNumber = customerNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getEconomicCode() {
        return economicCode;
    }

    public void setEconomicCode(String economicCode) {
        this.economicCode = economicCode;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }
}
