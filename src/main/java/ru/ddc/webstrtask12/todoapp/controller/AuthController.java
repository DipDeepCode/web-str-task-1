package ru.ddc.webstrtask12.todoapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ddc.webstrtask12.todoapp.controller.modelassembler.CustomerDtoModelAssembler;
import ru.ddc.webstrtask12.todoapp.controller.payload.request.SignupRequest;
import ru.ddc.webstrtask12.todoapp.service.CustomerService;
import org.springframework.security.core.userdetails.User;

import java.util.function.Function;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
    private final JdbcUserDetailsManager jdbcUserDetailsManager;
//    private final CustomerService customerService;
    private final ModelMapper modelMapper;
    private final CustomerDtoModelAssembler assembler;
    private final PasswordEncoder passwordEncoder;

    @PostMapping(value = "/register", produces = {"application/hal+json"})
    public ResponseEntity<?> register(@Valid @RequestBody SignupRequest request,
                                      BindingResult bindingResult) throws BindException {

        UserDetails user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .authorities("ROLE_USER")
                .passwordEncoder(passwordEncoder::encode)
                .build();

        System.out.println(user);
        jdbcUserDetailsManager.createUser(user);
//        Customer customer = customerService.createCustomer(request, bindingResult);
//        CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class);
        return null;//ResponseEntity.status(HttpStatus.CREATED).body(assembler.toModel(customerDto));
    }
}
