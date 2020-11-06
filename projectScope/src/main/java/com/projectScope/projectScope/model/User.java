package com.projectScope.projectScope.model;

import com.projectScope.projectScope.model.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue()
    private long id;
    private String name;
    private String surname;
    private String password;
    private String email;
    private String profilePicture;
    @Enumerated(value = EnumType.STRING)
    private Type type;
    private String otp;
    private boolean active;
}
