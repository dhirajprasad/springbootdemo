package com.codelab.springBootDemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="UserInfo")
public class User {
	    @Id@GeneratedValue(strategy=GenerationType.AUTO)
	    private int id;

	    @Column(name="country")
	    private String country;
	    @Column(name="name")
	    @Size(min = 3, max = 50)
	    @NotBlank(message = "username can't empty!")
	    private String name;


	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }
	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getCountry() {
	        return country;
	    }

	    public void setCountry(String country) {
	        this.country = country;
	    }

}
