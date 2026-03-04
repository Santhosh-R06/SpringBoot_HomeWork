package com.module2.DepartmentManagement.dto;


import com.module2.DepartmentManagement.annotations.DepartmentLocationValidation;
import com.module2.DepartmentManagement.annotations.DepartmentPasswordValidation;
import com.module2.DepartmentManagement.annotations.DepartmentPrimeNumberValidation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {

    @Null(message = "Department ID must be null during creation. It will be auto-generated.")
    private Long id;

    @NotBlank(message = "Department name cannot be blank.")
    @Size(min = 3, max = 20, message = "Department name must be between 3 and 20 characters.")
    private String deptName;

    @NotBlank(message = "Department email cannot be blank.")
    @Email(message = "Please provide a valid department email address.")
    private String deptEmail;

    @NotNull(message = "Department contact number is required.")
    @Pattern(regexp = "^[0-9]{10}$", message = "Contact number must be exactly 10 digits.")
    private String deptContactNumber;

    @NotNull(message = "Department employee count is required.")
    @Min(value = 5, message = "Department must have at least 5 employees.")
    @Max(value = 100, message = "Department cannot have more than 100 employees.")
    private Integer deptEmpCount;

    @NotNull(message = "Department active status must be provided.")
    @AssertTrue(message = "Department must be active to proceed.")
    private Boolean isDeptActive;

    @DepartmentLocationValidation
    private String location;

    @NotNull(message = "Department revenue is required.")
    @DecimalMin(value = "10000.00", message = "Revenue must be at least 10,000.")
    @DecimalMax(value = "1000000.00", message = "Revenue cannot exceed 1,000,000.")
    private Double deptRevenue;

    @PositiveOrZero(message = "Savings balance cannot be negative.")
    private Double deptSavingsBalance;

    @Digits(integer = 12, fraction = 2, message = "Account balance must have up to 12 digits and 2 decimal places.")
    private Double deptAccountBalance;

    @NotEmpty(message = "Access department list cannot be empty.")
    private List<@NotBlank(message = "Access department value cannot be blank.") String> haveAccessDept;

    @CreditCardNumber(message = "Invalid credit card number format.")
    private String deptCreditCardNumber;

    @PositiveOrZero(message = "Credit card balance cannot be negative.")
    private Double deptCreditCardBalance;

    @URL(message = "Please provide a valid website URL (e.g., https://example.com).")
    private String deptURL;

    @NotNull(message = "Creation date is required.")
    @PastOrPresent(message = "Creation date cannot be in the future.")
    private LocalDate createdAt;

    @NotNull(message = "Next audit date is required.")
    @Future(message = "Next audit date must be in the future.")
    private LocalDate nextAuditDate;

    @NotNull(message = "Department employee is required.")
    //@Null(message = "lastModified must be null during creation. It will be auto-generated.")
    @PastOrPresent(message = "Last modified date cannot be in the future.")
    private LocalDateTime lastModified;


    @DepartmentPrimeNumberValidation
    private Integer deptPrimeNumber;

    @DepartmentPasswordValidation
    private String deptPassword;
}
