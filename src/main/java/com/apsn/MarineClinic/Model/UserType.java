package com.apsn.MarineClinic.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "usertype")
public class UserType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long userType_Id;

    @Column(length = 50)
    private String userTypeName;

    @OneToMany(mappedBy = "userType")
    private List<User> users;
}
