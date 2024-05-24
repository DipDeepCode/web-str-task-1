package ru.ddc.webstrtask12.todoapp.model;

public class ModelFactory {

    public static Customer getNewCustomer(String username, String password, String email) {
        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setPassword(password);
        customer.setEmail(email);
        customer.setRole(Role.ROLE_USER);
        return customer;
    }
}
