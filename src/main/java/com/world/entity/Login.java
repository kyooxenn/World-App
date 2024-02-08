package com.world.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="login")
public class Login {

    @Id
    @Column(name="id")
    private long id;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;

}
