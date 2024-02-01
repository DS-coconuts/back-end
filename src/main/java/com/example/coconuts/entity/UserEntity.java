package com.example.coconuts.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Integer id; // user id

    @Column
    private String loginId;

    @Column
    private String password;

    @Column
    private String introduction;

    @Column
    private String image;

    @Column
    private Integer goalCpm;
}
