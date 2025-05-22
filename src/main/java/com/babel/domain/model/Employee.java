package com.babel.domain.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String secondName;
	private String fatherLastName;
	private String mothersLastName;
	private Integer age;
	private String gender;
	private LocalDate birdDate;
	private String workStation;
	
    public Employee() {

    }
	private Employee(Builder builder) {
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

	public LocalDate getBirdDate() {
		return birdDate;
	}

	public String getWorkStation() {
		return workStation;
	}

	public static class Builder {
		private Long id;
		private String firstName;
		private String secondName;
		private String fatherLastName;
		private String mothersLastName;
		private Integer age;
		private String gender;
		private LocalDate birdDate;
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
		
		public Builder setBirdDate(LocalDate birdDate) {
			this.birdDate = birdDate;
			return this;
		}
		
		public Builder setWorkStation(String workStation) {
			this.workStation = workStation;
			return this;
		}
		
        public Employee build() {
            return new Employee(this);
        }
	}
	
}
