package com.module2.DepartmentManagement.entities;



import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
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

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "department")
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String deptName;
    private String deptEmail;
    private String deptContactNumber;
    private Integer deptEmpCount;
    @JsonProperty("isDeptActive")
    private Boolean isDeptActive;
    private String location;
    private Double deptRevenue;
    private Double deptSavingsBalance;
    private Double deptAccountBalance;
    private String deptCreditCardNumber;
    private Double deptCreditCardBalance;
    private List<String> haveAccessDept;
    private String deptURL;
    private LocalDate createdAt;
    private LocalDate nextAuditDate;
    private LocalDateTime lastModified;
    private Integer deptPrimeNumber;
    private String deptPassword;
}
