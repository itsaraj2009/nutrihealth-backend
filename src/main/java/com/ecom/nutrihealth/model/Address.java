package com.ecom.nutrihealth.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "addresses")
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;

//    @NotBlank
    @Size(min = 2, message = "Building Name must have atleast 6 character")
    private String buildingName;

//    @NotBlank
    @Size(min = 2, message = "Street must have atleast 6 character")
    private String street;

//    @NotBlank
    @Size(min = 2, message = "City Name must have atleast 6 character")
    private String city;

//    @NotBlank
    @Size(min = 2, message = "State Name must have atleast 6 character")
    private String state;

//    @NotBlank
    @Size(min = 2, message = "Landmark Name must have atleast 6 character")
    private String landMark;

//    @NotBlank
    @Size(min = 2, message = "Country Name must have atleast 6 character")
    private String country;

//    @NotBlank
    @Size(min = 2, message = "ZipCode Name must have atleast 6 character")
    private String pincode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Address(String buildingName, String street,
                   String city, String state, String landMark,
                   String country, String pincode) {
        this.buildingName = buildingName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.landMark = landMark;
        this.country = country;
        this.pincode = pincode;
    }
}
