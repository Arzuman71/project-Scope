package com.projectScope.projectScope.service;


import com.projectScope.projectScope.model.Log;
import com.projectScope.projectScope.repository.LogRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogRepo logRepo;

    public void save(Log log) {
        logRepo.save(log);
    }

    public void deleteById( List<Long> idS) {
        idS.forEach(logRepo::deleteById);

    }

    public List<Log> findAllByUserId(long id) {
        return logRepo.findAllByUserId(id);

    }
}
