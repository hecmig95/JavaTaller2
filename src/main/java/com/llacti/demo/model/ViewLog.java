package com.llacti.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "vw_logs")
public class ViewLog {
    @Id
    Long logId;
    String tableName;
    String logValue;
    String operation;
    LocalDate logDate;

    public ViewLog(long l, String s, String error) {
    }


}
