package ru.ddc.webstrtask12.todoapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ddc.webstrtask12.todoapp.controller.modelassembler.CustomerDtoModelAssembler;
import ru.ddc.webstrtask12.todoapp.controller.payload.request.SignupRequest;
import ru.ddc.webstrtask12.todoapp.dto.CustomerDto;
import ru.ddc.webstrtask12.todoapp.model.Customer;
import ru.ddc.webstrtask12.todoapp.service.CustomerService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RegistrationController {
    private final CustomerService customerService;
    private final ModelMapper modelMapper;
    private final CustomerDtoModelAssembler assembler;

    @PostMapping(value = "/register", produces = {"application/hal+json"})
    public ResponseEntity<?> register(@Valid @RequestBody SignupRequest request,
                                      BindingResult bindingResult) throws BindException {
        Customer customer = customerService.createCustomer(request, bindingResult);
        CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(assembler.toModel(customerDto));
    }
}
