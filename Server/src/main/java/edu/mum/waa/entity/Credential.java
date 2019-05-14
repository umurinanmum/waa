package edu.mum.waa.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(indexes = {@Index(columnList = "email,password")})
public class Credential {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @Column(unique = true)
    private String email;

    private String password;

    @OneToOne
    private Student student;

    @OneToOne
    private Faculty faculty;

    @ManyToMany
    private List<Role> roles;

}
