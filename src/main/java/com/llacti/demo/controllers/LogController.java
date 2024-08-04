package com.llacti.demo.controllers;

import com.llacti.demo.model.ViewLog;
import com.llacti.demo.repository.ViewLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class LogController {
    @Autowired
    ViewLogRepository viewLogRepository;

    @GetMapping("/logs")
    public List<ViewLog> getLogs() {
        return viewLogRepository.findAll();
    }
}
