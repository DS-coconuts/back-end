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
public class FriendEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // friend id

    @ManyToOne(cascade = CascadeType.DETACH)
    private UserEntity fromUser;  // 요청을 보낸 사용자

    @ManyToOne(cascade = CascadeType.DETACH)
    private UserEntity toUser;   // 요청을 받은 사용자
}
