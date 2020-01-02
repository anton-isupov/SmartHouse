package com.technologies.smart.house.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table
@Entity
public class User {
    @Id
    private String id;
    private String login;
    private String password;
    private String email;
}
