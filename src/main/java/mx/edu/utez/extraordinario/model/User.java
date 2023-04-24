package mx.edu.utez.extraordinario.model;

import jakarta.validation.constraints.NotBlank;

public class User {
    @NotBlank(message = "El campo no debe quedar vacío")
    private String name;
    @NotBlank(message = "El campo no debe quedar vacío")
    private String surname;
    @NotBlank(message = "El campo no debe quedar vacío")
    private String lastname;
    @NotBlank(message = "El campo no debe quedar vacío")
    private String rfc;
    @NotBlank(message = "El campo no debe quedar vacío")
    private String phone;
    @NotBlank(message = "El campo no debe quedar vacío")
    private String email;
    @NotBlank(message = "El campo no debe quedar vacío")
    private String password;
}
