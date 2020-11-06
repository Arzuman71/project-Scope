package com.projectScope.projectScope.repository;

import com.projectScope.projectScope.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogRepo extends JpaRepository<Log, Long> {

    List<Log> findAllByUserId(long id);
}
