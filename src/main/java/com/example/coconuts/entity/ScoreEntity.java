package com.example.coconuts.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ScoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SCORE_ID")
    private Integer id; // score id

    @CreatedDate
    private LocalDate createdAt;

    @Column
    private Integer cpm;

    @Column
    private Integer wpm;

    @Column
    private Integer acc;

    @Column
    private LocalTime countTime;

    @Column
    private String language;

    @Column(name = "DATA_ID")
    private Integer dataId;    // FK

    @Column(name = "USER_ID")
    private Integer userId;    // FK

}
