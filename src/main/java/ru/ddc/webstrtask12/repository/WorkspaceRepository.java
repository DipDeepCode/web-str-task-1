package ru.ddc.webstrtask12.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.ddc.webstrtask12.model.Workspace;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class WorkspaceRepository implements CrudRepository<Workspace, Long> {
    private final JdbcClient jdbcClient;

    @Transactional
    @Override
    public Workspace save(Workspace model) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("insert into workspace (name, description, created_at) values (:name, :description, :createdAt)")
                .paramSource(model)
                .update(keyHolder);
        model.setId(keyHolder.getKeyAs(Long.class));
        return model;
    }

    @Override
    public List<Workspace> findAll() {
        return jdbcClient.sql("select * from workspace").query(Workspace.class).list();
    }

    @Override
    public Workspace findById(Long id) {
        return jdbcClient.sql("select * from workspace where id = :id")
                .param("id", id)
                .query(Workspace.class)
                .single();
    }

    @Transactional
    @Override
    public int update(Workspace workspace) {
        return jdbcClient.sql("update WORKSPACE set name = :name, description = :description where id = :id")
                .paramSource(workspace)
                .update();
    }

    @Transactional
    @Override
    public int deleteById(Long id) {
        return jdbcClient.sql("delete from WORKSPACE where id = :id").param("id", id).update();
    }
}
