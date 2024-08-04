package com.llacti.demo.repository;

import com.llacti.demo.model.ViewLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewLogRepository extends JpaRepository<ViewLog, Long> {
}
