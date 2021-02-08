package com.tesda8.region8.quality.model.entities;

import com.tesda8.region8.util.enums.Sex;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@NoArgsConstructor
@Embeddable
public class Customer {

    private String firstName;
    private String middleName;
    private String lastName;
    private String fullName;
    private Integer age;
    @Enumerated(EnumType.STRING)
    private Sex gender;
    private String address;
    private String contactNumber;
    private String emailAddress;

    public String fetchFullName() {
        return firstName + " " + middleName + " " + lastName;
    }
}
