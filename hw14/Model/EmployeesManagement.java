package com.example.hw14.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeesManagement {

    @NotNull(message = "The Id should be not null")
    @Size(min = 2, message = "Length of ID should be more than 2")
    private String ID;

    @NotNull(message = "The name should be not null")
    @Size(min = 4, message = "Length of name should be more than 4")
    private String name;

    @NotNull(message = "The age should be not null")
    @Min(value = 25, message = "Age should be more than 25")
    @Positive(message = "Age should be positive number and more than 25")
    private int age;

    @NotNull(message = "The position should be not null")
    @Pattern(regexp = "\\b(supervisor|coordinator)\\b" , message = "Position should be (supervisor) or (coordinator) only")
    private String position;

    @AssertFalse(message = "onLeave should be false")
    private boolean onLeave;

    @NotNull(message = "The employment year should be not null")
    @Positive(message = "Employment year has to be a number")
//  @Pattern(regexp = "^((19|2[0-9])[0-9]{2})$", message = "Employment year has to be a valid year")
    @Min(value = 1970, message = "Employment year has to be a valid year")
    @Max(value = 2999, message = "Annual year has to be a valid year")
    private int employmentYear;

    @NotNull(message = "The annual Leave should be not null")
    @Positive(message = "Annual year has to be a number")
    @Max(value = 30, message = "Annual year should be less than or equal 30 day")
    private int annualLeave;
}
