package ru.ddc.webstrtask12.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.ddc.webstrtask12.model.ToDoItem;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ToDoItemRepository implements CrudRepository<ToDoItem, Long> {
    private final JdbcClient jdbcClient;

    @Transactional
    @Override
    public ToDoItem save(ToDoItem model) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("insert into to_do_item(workspace_id, name, description, is_done, start_date_time, duration, " +
                        "priority, is_postponed, created_at)" +
                        "values (:workspaceId, :name, :description, :isDone, :startDateTime, :duration, " +
                        ":priority, :isPostponed, :createdAt)")
                .paramSource(model)
                .update(keyHolder);
        model.setId(keyHolder.getKeyAs(Long.class));
        return model;
    }

    @Override
    public List<ToDoItem> findAll() {
        return jdbcClient.sql("select * from to_do_item").query(ToDoItem.class).list();
    }

    @Override
    public ToDoItem findById(Long id) {
        return jdbcClient
                .sql("select * from to_do_item where id = :id")
                .param("id", id)
                .query(ToDoItem.class)
                .single();
    }

    @Transactional
    @Override
    public int update(ToDoItem toDoItem) {
        return jdbcClient
                .sql("update to_do_item set workspace_id = :workspaceId, name = :name, description = :description, " +
                        "is_done = :isDone, start_date_time = :startDateTime, duration = :duration, " +
                        "priority = :priority, is_postponed = :isPostponed where id = :id")
                .paramSource(toDoItem)
                .update();
    }

    @Transactional
    @Override
    public int deleteById(Long id) {
        return jdbcClient.sql("delete from to_do_item where id = :id").param("id", id).update();
    }
}
