package ru.ddc.webstrtask12.todoapp.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.stereotype.Component;
import ru.ddc.webstrtask12.todoapp.controller.payload.request.SignupRequest;
import ru.ddc.webstrtask12.todoapp.repository.CustomerRepository;

@Component
@RequiredArgsConstructor
public class SignupRequestValidator implements Validator {
    private final CustomerRepository customerRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return SignupRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SignupRequest request = (SignupRequest) target;
        if (customerRepository.existsByUsername(request.getUsername())) {
            errors.rejectValue("username", "", "username is already taken");
        }
        if (customerRepository.existsByEmail(request.getEmail())) {
            errors.rejectValue("email", "", "email is already in use");
        }
    }
}
