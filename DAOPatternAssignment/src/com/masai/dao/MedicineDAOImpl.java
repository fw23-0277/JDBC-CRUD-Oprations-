package com.masai.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.masai.dto.Medicine;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;
import com.masai.utility.DBUtils;


public class MedicineDAOImpl implements IMedicineDAO {

	@Override
	public void addMedicine(Medicine medicine) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		
		try (Connection connection = DBUtils.createConnection()){
			String insertQuery  = "INSERT INTO MEDICINE VALUES (?,?,?,?,?,?)";
			
			PreparedStatement prepareStatement = connection.prepareStatement(insertQuery);
			
			prepareStatement.setString(1,medicine.getMedId());
			prepareStatement.setString(2,medicine.getName());
			prepareStatement.setString(3,medicine.getCompany());
			prepareStatement.setDouble(4,medicine.getPrice());
			prepareStatement.setDate(5,Date.valueOf(medicine.getMFGDate()));
			prepareStatement.setDate(6,Date.valueOf(medicine.getExpDate()));
			
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			throw new SomethingWentWrongException("Unable to add Medicine...!");
		}
		
		
		
	}

	@Override
	public void deleteMedicine(String mId) throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		String deleteQuery = "DELETE FROM MEDICINE WHERE med_id = ? ";
		
		try (Connection connection  = DBUtils.createConnection()){
			
			PreparedStatement ps = connection.prepareStatement(deleteQuery);
			
			ps.setString(1, mId);
			
			int deleteSucsess =  ps.executeUpdate();
			
			if(deleteSucsess == 0) {
				throw new NoRecordFoundException("Medicine Not Found...!\n");
				
			}else {
				System.out.println("Midicine Delete Successfully...!");
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Unable to Delete Employee...!");
		}
		
	}

	@Override
	public void updateMedicine(Medicine medicine) throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		String updateQuery  = "UPDATE MEDICINE SET name = ? , company = ? ,  price_per_unit = ? , MfgDate = ? ,  ExpDate = ? WHERE med_id = ?";
		try (Connection connection = DBUtils.createConnection()){
			
			PreparedStatement prepareStatement = connection.prepareStatement(updateQuery);
			prepareStatement.setString(1, medicine.getName());
			prepareStatement.setString(2, medicine.getCompany());
			prepareStatement.setDouble(3, medicine.getPrice());
			prepareStatement.setDate(4, Date.valueOf(medicine.getMFGDate()));
			prepareStatement.setDate(5, Date.valueOf(medicine.getExpDate()));
			prepareStatement.setString(6,medicine.getMedId());
			
			prepareStatement.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Unable to Update Medicine...!");
		}
		
	}

	@Override
	public List<Medicine> getMedicineList() throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		String selectQuery = "SELECT * FROM MEDICINE ORDER BY name ASC";
		List<Medicine> medicine = new ArrayList<>();
		try (Connection connection = DBUtils.createConnection()){
			
			PreparedStatement prepareStatement = connection.prepareStatement(selectQuery);
			
			ResultSet rs = prepareStatement.executeQuery();
			if(DBUtils.isResultSetEmpty(rs)) throw new NoRecordFoundException("Medicine Not Found...!");
			
			
			
			while(rs.next()) {
				Medicine med = new Medicine(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getDate(5).toLocalDate(),rs.getDate(6).toLocalDate());
				medicine.add(med);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.print("Unable to Get Medicine...!");
		}
		return medicine;
	}

	
	

}
