package ru.ddc.webstrtask12.todoapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import ru.ddc.webstrtask12.todoapp.model.Customer;
import ru.ddc.webstrtask12.todoapp.model.ModelFactory;
import ru.ddc.webstrtask12.todoapp.controller.payload.request.LoginRequest;
import ru.ddc.webstrtask12.todoapp.controller.payload.request.SignupRequest;
import ru.ddc.webstrtask12.todoapp.repository.CustomerRepository;
import ru.ddc.webstrtask12.todoapp.utils.SignupRequestValidator;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
//    private final AuthenticationManager authenticationManager;
    private final SignupRequestValidator signupRequestValidator;

    public Customer findById(Long id) {
        return customerRepository.findById(id);
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer register(SignupRequest request, BindingResult bindingResult) throws BindException {
        signupRequestValidator.validate(request, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        } else {
            Customer customer = ModelFactory.getNewCustomer(
                    request.getUsername(),
                    passwordEncoder.encode(request.getPassword()),
                    request.getEmail());
            return customerRepository.save(customer);
        }
    }

//    public void login(LoginRequest request) {
//        System.out.println("LOGIN");
//        UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken.unauthenticated(
//                request.getUsername(), request.getPassword()
//        );
//        Authentication authentication = authenticationManager.authenticate(token);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//    }
}
