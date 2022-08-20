package com.project.test.Test_Project.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "user name is mandatory")
    @NotEmpty(message = "user name is empty")
    private String name;
    @NotBlank(message = "email user's is mandatory")
    @NotEmpty(message = "email user's is empty")
    @Email(message = "email user's invalid")
    private String email;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Gift>gifts;

}