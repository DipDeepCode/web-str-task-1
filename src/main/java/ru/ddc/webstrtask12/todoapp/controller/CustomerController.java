package ru.ddc.webstrtask12.todoapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ddc.webstrtask12.todoapp.controller.modelassembler.CustomerDtoModelAssembler;
import ru.ddc.webstrtask12.todoapp.dto.CustomerDto;
import ru.ddc.webstrtask12.todoapp.model.Customer;
import ru.ddc.webstrtask12.todoapp.controller.payload.request.LoginRequest;
import ru.ddc.webstrtask12.todoapp.controller.payload.request.SignupRequest;
import ru.ddc.webstrtask12.todoapp.repository.CustomerRepository;
import ru.ddc.webstrtask12.todoapp.service.CustomerService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerDtoModelAssembler assembler;
    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;

    @PostMapping(value = "/customers/register", produces = {"application/hal+json"})
    public ResponseEntity<?> register(@Valid @RequestBody SignupRequest request,
                                      BindingResult bindingResult) throws BindException {
        Customer customer = customerService.register(request, bindingResult);
        CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(assembler.toModel(customerDto));
    }

//    @PostMapping("/customers/login")
//    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
//        customerService.login(loginRequest);
//        return ResponseEntity.ok().build();
//    }

    @GetMapping("/customers")
    public ResponseEntity<?> getCustomers() {
        List<Customer> customers = customerService.findAll();
        List<CustomerDto> customerDtos = customers.stream()
                .map(customer -> modelMapper.map(customer, CustomerDto.class))
                .toList();
        return ResponseEntity.ok().body(assembler.toCollectionModel(customerDtos));
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable("id") Long id) {
        Customer customer = customerRepository.findById(id);
        CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class);
        return ResponseEntity.ok().body(assembler.toModel(customerDto));
    }

}
