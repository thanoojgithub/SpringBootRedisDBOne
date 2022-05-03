package com.selflearnings.springbootredisdb.beans;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.redis.core.RedisHash;


@RedisHash("Customer")
@Data
@ToString
public class Customer {

    public Customer (String id, String name, Gender gender, String location) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.location = location;

    }

    public enum Gender {
        MALE, FEMALE
    }

    private String id;
    private String name;
    private Gender gender;
    private String location;
}
