package com.masai.service;


import java.util.List;

import com.masai.dto.Medicine;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;

public interface IMedicineService {
	void addMedicine(Medicine medicine) throws SomethingWentWrongException;
	void deleteMedicine(String mId) throws SomethingWentWrongException , NoRecordFoundException;
	void updateMedicine(Medicine medicine) throws SomethingWentWrongException, NoRecordFoundException;
	List<Medicine> getMedicineList() throws SomethingWentWrongException , NoRecordFoundException;
	Medicine searchMedicineId(String searchMedId) throws SomethingWentWrongException , NoRecordFoundException;
}
