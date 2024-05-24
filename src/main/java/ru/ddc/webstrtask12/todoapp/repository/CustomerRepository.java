package ru.ddc.webstrtask12.todoapp.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.ddc.webstrtask12.todoapp.model.Customer;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomerRepository implements CrudRepository<Customer, Long> {
    private final JdbcClient jdbcClient;

    @Transactional
    @Override
    public Customer save(Customer model) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("insert into customer (username, email, password, role, created_at, is_non_locked) " +
                "values (:username, :email, :password, :role, :createdAt, :isNonLocked)")
                .paramSource(model)
                .update(keyHolder);
        model.setId(keyHolder.getKeyAs(Long.class));
        return model;
    }

    @Override
    public List<Customer> findAll() {
        return jdbcClient.sql("select * from customer").query(Customer.class).list();
    }

    @Override
    public Customer findById(Long id) {
        return jdbcClient.sql("select * from customer where id = :id")
                .param("id", id)
                .query(Customer.class)
                .single();
    }

    @Transactional
    @Override
    public int update(Customer customer) {
        return jdbcClient.sql("update customer set username = :username, email = :email, password = :password," +
                "role = :role, is_non_locked = :isNonLocked where id = :id")
                .paramSource(customer)
                .update();
    }

    @Transactional
    @Override
    public int deleteById(Long id) {
        return jdbcClient.sql("delete from customer where id = :id").param("id", id).update();
    }

    public Optional<Customer> findByUsername(String username) {
        return jdbcClient.sql("select * from customer where username = :username")
                .param("username", username)
                .query(Customer.class)
                .optional();
    }

    public boolean existsByUsername(String username) {
        return jdbcClient.sql("select exists(select * from customer where username = :username)")
                .param("username", username)
                .query(Boolean.class)
                .single();
    }

    public boolean existsByEmail(String email) {
        return jdbcClient.sql("select exists(select * from customer where email = :email)")
                .param("email", email)
                .query(Boolean.class)
                .single();
    }

    @Transactional
    public int updateNonLocked(Long id, Boolean isNonLocked) {
        return jdbcClient.sql("update customer set is_non_locked = :isNonLocked where id = :id")
                .param("id", id)
                .param("isNonLocked", isNonLocked)
                .update();
    }
}
