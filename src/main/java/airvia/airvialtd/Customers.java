package airvia.airvialtd;
/**
 * The Customers class represents a collection of customers.
 * It provides methods for adding, retrieving customer information.
 */
public class Customers {
    public Customers(Integer customerID, String firstName, String surname, String email, String number, String valuedCustomer, Integer ticketID) {
        this.customerID = customerID;
        this.ticketID = ticketID;
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
        this.number = number;
        this.valuedCustomer = valuedCustomer;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public Integer getTicketID() {
        return ticketID;
    }

    public void setTicketID(Integer ticketID) {
        this.ticketID = ticketID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getValuedCustomer() {
        return valuedCustomer;
    }

    public void setValuedCustomer(String valuedCustomer) {
        this.valuedCustomer = valuedCustomer;
    }

    private Integer customerID, ticketID;
    private String firstName, surname, email, number, valuedCustomer;
}
