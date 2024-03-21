package com.usercrud.usercrud.User.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.usercrud.usercrud.Archive;
import com.usercrud.usercrud.SECURITY.model.entity.UserDetailsENTITY;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean status;
    private Integer age;
    @OneToOne(cascade = CascadeType.ALL)
    private Archive photo;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private UserDetailsENTITY userDetailsENTITY;

}
