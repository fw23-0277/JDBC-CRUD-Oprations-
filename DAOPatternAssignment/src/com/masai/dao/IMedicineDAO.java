package com.masai.dao;

import java.util.List;

import com.masai.dto.Medicine;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;


public interface IMedicineDAO {
	void addMedicine(Medicine medicine) throws SomethingWentWrongException;
	void deleteMedicine(String mId) throws SomethingWentWrongException , NoRecordFoundException;
	void updateMedicine(Medicine medicine) throws SomethingWentWrongException , NoRecordFoundException;
	List<Medicine> getMedicineList() throws SomethingWentWrongException , NoRecordFoundException;
}
