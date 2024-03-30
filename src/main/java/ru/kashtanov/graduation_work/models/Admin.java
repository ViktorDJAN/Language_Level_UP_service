package ru.kashtanov.graduation_work.models;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Admin is an entity that accomplishes the admin role in the project
 */
@Entity
@Table(name = "administrators")
public class Admin extends UserEntity {
    private String phone;

    public Admin() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
