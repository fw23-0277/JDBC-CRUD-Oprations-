package com.masai.service;

import java.util.List;
import java.util.Optional;

import com.masai.dao.IMedicineDAO;
import com.masai.dao.MedicineDAOImpl;
import com.masai.dto.Medicine;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;

public class MedicineServiceImpl implements IMedicineService {
	
	IMedicineDAO medicineDAO = new MedicineDAOImpl();

	@Override
	public void addMedicine(Medicine medicine) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		medicineDAO.addMedicine(medicine);
		
	}

	@Override
	public void deleteMedicine(String mId) throws SomethingWentWrongException , NoRecordFoundException{
		// TODO Auto-generated method stub
		medicineDAO.deleteMedicine(mId);
	}

	@Override
	public void updateMedicine(Medicine medicine) throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		medicineDAO.updateMedicine(medicine);
		
	}

	@Override
	public List<Medicine> getMedicineList() throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		return medicineDAO.getMedicineList();
	}

	@Override
	public Medicine searchMedicineId(String searchMedId) throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		Medicine searchMedicine = null;
		List<Medicine> medicine = getMedicineList();
		Optional<Medicine> searchedMedicine = medicine.stream()
                .filter(medicin -> medicin.getMedId() == searchMedId)
                .findFirst();
		
		 if (searchedMedicine.isPresent()) {
	             searchMedicine= searchedMedicine.get();
		 }
				
		 return searchMedicine;
		
	}
	
	
}
