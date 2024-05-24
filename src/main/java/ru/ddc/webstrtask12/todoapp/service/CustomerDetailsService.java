package ru.ddc.webstrtask12.todoapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.ddc.webstrtask12.todoapp.model.Customer;
import ru.ddc.webstrtask12.todoapp.model.CustomerDetails;
import ru.ddc.webstrtask12.todoapp.repository.CustomerRepository;

@Service
@RequiredArgsConstructor
public class CustomerDetailsService implements UserDetailsService {
    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        System.out.println(customer);
        CustomerDetails customerDetails = new CustomerDetails(customer);
        System.out.println(customerDetails);
        return customerDetails;
    }
}
