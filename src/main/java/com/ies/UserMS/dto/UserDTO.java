package com.ies.UserMS.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDTO {
    private String userId;
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "{name.pattern.invalid}")
    @Size(min = 3, max = 50, message = "{name.size.invalid}")
    private String name;

    @Email(message = "{email.pattern.invalid}")
    private String email;
    private String password;
    @Pattern(regexp = "ADMIN|CASEWORKER|USER", message = "{role.pattern.invalid}")
    private String role;
    @Pattern(regexp = "^[0-9]{10}$", message = "{phone.pattern.invalid}")
    private String phone;
    @Pattern(regexp = "Male|Female|Other", message = "{gender.pattern.invalid}")
    private String gender;
    @Past(message = "{dob.past.invalid}")
    private LocalDate dob;
    @Pattern(regexp = "^[0-9]{12}$", message = "{aadharNo.pattern.invalid}")
    private String aadharNo;
    private boolean passwordChanged;

}
