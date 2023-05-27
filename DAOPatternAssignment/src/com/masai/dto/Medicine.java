package com.masai.dto;

import java.time.LocalDate;

public class Medicine {
	private String medId;
	private String name;
	private String company;
	private Double price;
	private LocalDate MFGDate;
	private LocalDate ExpDate;
	
	
	
	public Medicine() {
		super();
	}

	public Medicine(String medId, String name, String company, Double price, LocalDate mFGDate, LocalDate expDate) {
		super();
		this.medId = medId;
		this.name = name;
		this.company = company;
		this.price = price;
		MFGDate = mFGDate;
		ExpDate = expDate;
	}

	public String getMedId() {
		return medId;
	}

	public void setMedId(String medId) {
		this.medId = medId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public LocalDate getMFGDate() {
		return MFGDate;
	}

	public void setMFGDate(LocalDate mFGDate) {
		MFGDate = mFGDate;
	}

	public LocalDate getExpDate() {
		return ExpDate;
	}

	public void setExpDate(LocalDate expDate) {
		ExpDate = expDate;
	}

	@Override
	public String toString() {
		return "Medicine [medId=" + medId + ", name=" + name + ", company=" + company + ", price=" + price
				+ ", MFGDate=" + MFGDate + ", ExpDate=" + ExpDate + "]";
	}
	
}
