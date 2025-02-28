package com.ies.UserMS.entity;

import com.ies.UserMS.utils.UserId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @UserId
    private String userId;
    private String name;
    private String email;
    private String password;
    private String role;
    private String phone;
    private String gender;
    private LocalDate dob;
    private String aadharNo;
    private boolean passwordChanged;

}
