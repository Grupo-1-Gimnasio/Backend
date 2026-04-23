package com.inditex.gym_lorza.model;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    private String dni;
    @NotNull
    @Column(name = "start_year")
    private Integer startYear;
    @NotNull
    @Column(name = "is_active")
    private Boolean isActive;
    @NotNull
    @Column(name = "annual_fee_paid")
    private Boolean annualFeePaid;

    private String image;
}
