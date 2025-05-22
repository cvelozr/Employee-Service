package com.babel.domain.dto;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonDeserialize(builder = EmployeeDTO.Builder.class)
public class EmployeeDTO implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public interface OnCreate {}

	public interface OnUpdate {}

	private Long id;
	@NotNull(message = "First Name is required", groups = OnCreate.class)
	@Size(max = 50, message = "First name must be less than 50 characters",groups = {OnCreate.class, OnUpdate.class})
	private String firstName;
	@Size(max = 50, message = "Second name must be less than 50 characters",groups = {OnCreate.class, OnUpdate.class})
	private String secondName;
	@NotNull(message = "Father LastName is required", groups = OnCreate.class)
	@Size(max = 50, message = "Father Lastname must be less than 50 characters",groups = {OnCreate.class, OnUpdate.class})
	private String fatherLastName;
	@Size(max = 50, message = "Mother Lastname must be less than 50 characters",groups = {OnCreate.class, OnUpdate.class})
	private String mothersLastName;
	@NotNull(message = "age is required", groups = OnCreate.class)
	@Min(value = 0, message = "Age must be one at least",groups = {OnCreate.class, OnUpdate.class})
	@Max(value = 125, message = "Age must be less than 125",groups = {OnCreate.class, OnUpdate.class})
	private Integer age;
	@NotNull(message = "Gender is required", groups = OnCreate.class)
	@Size(max = 50, message = "Gender must be less than 50 characters",groups = {OnCreate.class, OnUpdate.class})
	private String gender;
	@NotNull(message = "Date can't be null", groups = OnCreate.class)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private String birdDate;
	@Size(max = 100, message = "Workstation must be less than 100 characters",groups = {OnCreate.class, OnUpdate.class})
	private String workStation;

	private EmployeeDTO(Builder builder) {
		super();
		this.id = builder.id;
		this.firstName = builder.firstName;
		this.secondName = builder.secondName;
		this.fatherLastName = builder.fatherLastName;
		this.mothersLastName = builder.mothersLastName;
		this.age = builder.age;
		this.gender = builder.gender;
		this.birdDate = builder.birdDate;
		this.workStation = builder.workStation;
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public String getFatherLastName() {
		return fatherLastName;
	}

	public String getMothersLastName() {
		return mothersLastName;
	}

	public Integer getAge() {
		return age;
	}

	public String getGender() {
		return gender;
	}

	public String getBirdDate() {
		return birdDate;
	}

	public String getWorkStation() {
		return workStation;
	}

	@JsonPOJOBuilder(withPrefix = "set")
	public static class Builder {
		private Long id;
		private String firstName;
		private String secondName;
		private String fatherLastName;
		private String mothersLastName;
		private Integer age;
		private String gender;
		private String birdDate;
		private String workStation;

		public Builder setId(Long id) {
			this.id = id;
			return this;
		}

		public Builder setFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder setSecondName(String secondName) {
			this.secondName = secondName;
			return this;
		}

		public Builder setFatherLastName(String fatherLastName) {
			this.fatherLastName = fatherLastName;
			return this;
		}

		public Builder setMothersLastName(String mothersLastName) {
			this.mothersLastName = mothersLastName;
			return this;
		}

		public Builder setAge(Integer age) {
			this.age = age;
			return this;
		}

		public Builder setGender(String gender) {
			this.gender = gender;
			return this;
		}

		public Builder setBirdDate(String birdDate) {
			this.birdDate = birdDate;
			return this;
		}

		public Builder setWorkStation(String workStation) {
			this.workStation = workStation;
			return this;
		}

		public EmployeeDTO build() {
			return new EmployeeDTO(this);
		}
	}

}
