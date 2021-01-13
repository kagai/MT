package com.example.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "user")
public class User {
	@Id
	@Column(name = "id")
	private long id;

	@Column(name = "msisdn")
	private String msisdn;
	
	@Column(name = "sim_type")
	private String sim_type;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "date_of_birth")
	private Date date_of_birth;

	@Column(name = "gender")
	private String gender;

	@Column(name = "address")
	private String address;

	@Column(name = "id_number")
	private String id_number;

	public User() {

	}

	public User(long id, String msisdn, String sim_type, String name,Date date_of_birth, String gender,String address,String id_number ) {
		this.id = id;
		this.msisdn = msisdn;
		this.sim_type = sim_type;
		this.name = name;
		this.date_of_birth = date_of_birth;
		this.gender = gender;
		this.address = address;
		this.id_number = id_number;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getSim_type() {
		return sim_type;
	}

	public void setSim_type(String sim_type) {
		this.sim_type = sim_type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getId_number() {
		return id_number;
	}

	public void setId_number(String id_number) {
		this.id_number = id_number;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", msisdn=" + msisdn + ", sim_type=" + sim_type + ", name=" + name
				+ "]";
	}
	
}
