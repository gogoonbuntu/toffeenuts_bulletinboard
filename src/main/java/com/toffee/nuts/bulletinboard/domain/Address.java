package com.toffee.nuts.bulletinboard.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Address {
    private String city;
    private String zipcode;

    public Address (String city, String zipcode) {
    }
}
