package ru.ddc.webstrtask12.todoapp.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ddc.webstrtask12.todoapp.controller.modelassembler.CustomerDtoModelAssembler;
import ru.ddc.webstrtask12.todoapp.dto.CustomerDto;
import ru.ddc.webstrtask12.todoapp.model.Customer;
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
