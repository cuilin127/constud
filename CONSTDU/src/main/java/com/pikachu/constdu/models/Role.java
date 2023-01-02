package com.pikachu.constdu.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String roleName;

    public Role(String roleName) {
        this.roleName = roleName;
    }
    @OneToMany
    List<User> users= new ArrayList<User>();
}
