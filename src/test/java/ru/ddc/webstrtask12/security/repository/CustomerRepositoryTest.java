package ru.ddc.webstrtask12.security.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ddc.webstrtask12.todoapp.repository.CustomerRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void test1() {
        assertFalse(customerRepository.existsByUsername("usr"));
    }

    @Test
    public void test2() {
        assertTrue(customerRepository.existsByUsername("usr2"));
    }
}