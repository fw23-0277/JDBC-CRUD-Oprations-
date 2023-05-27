package com.masai.ui;


import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.masai.dto.Medicine;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;
import com.masai.service.IMedicineService;
import com.masai.service.MedicineServiceImpl;

public class MainUi {
	

	static IMedicineService medicineService = new MedicineServiceImpl();
	
	static void addMedicine(Scanner sc)  {
		System.out.print("Enter Medicine Id : ");
		String mId = sc.next();
		System.out.print("Enter Medicine Name  : ");
		String  name = sc.next();
		System.out.print("Enter Company Name : ");
		String  company = sc.next();
		System.out.print("Enter Medicine Price : ");
		double price = sc.nextDouble();
		System.out.print("Enter Manufacturing Date : ");
		LocalDate mfgDate = LocalDate.parse(sc.next());
		System.out.print("Enter Expiry Date : ");
		LocalDate expDate = LocalDate.parse(sc.next());
		
		Medicine medicine = new Medicine(mId,name,company,price, mfgDate,expDate);
		
		try {
			medicineService.addMedicine(medicine);
			System.out.println("Midicine Added Sucessfully...!");
		} catch (SomethingWentWrongException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}
	
	static void deleteMedicine(Scanner sc) {
		System.out.print("Enter Medicine Id : ");
		String  mId = sc.next();
		try {
			medicineService.deleteMedicine(mId);
		} catch (NoRecordFoundException | SomethingWentWrongException e) {
			// TODO Auto-generated catch block
			System.out.print(e.getMessage());
		}
		
	}
	
	static void viewMedicine() {
		try {
			List<Medicine> medicine = medicineService.getMedicineList();
			medicine.stream().forEach(System.out::println);
		} catch (SomethingWentWrongException | NoRecordFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	static void updateMedicine(Scanner sc) {
		System.out.print("Enter Medicine Id : ");
		String mId = sc.next();
		System.out.print("Enter Medicine Name  : ");
		String  name = sc.next();
		System.out.print("Enter Company Name : ");
		String  company = sc.next();
		System.out.print("Enter Medicine Price : ");
		double price = sc.nextDouble();
		System.out.print("Enter Manufacturing Date : ");
		LocalDate mfgDate = LocalDate.parse(sc.next());
		System.out.print("Enter Expiry Date : ");
		LocalDate expDate = LocalDate.parse(sc.next());
		
		Medicine medicine = new Medicine(mId, name, company, price, mfgDate, expDate);
		
		try {
			medicineService.updateMedicine(medicine);
			System.out.println("Medicine Updated Succesfully...!");
		} catch (SomethingWentWrongException | NoRecordFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	static void searchMedicine(Scanner sc) {
		System.out.print("Enter Medicine Id : ");
		String mId = sc.next();
		try {
			Medicine medicine = medicineService.searchMedicineId(mId);
			
			if(medicine != null) {
				System.out.println(medicine.getMedId() +" "+ medicine.getName() +" "+ medicine.getCompany() +" "+ medicine.getPrice() +" "+ medicine.getMFGDate()+" "+medicine.getExpDate());
			}else {
				throw new NoRecordFoundException("Medicine Not Found...!");
			}
			
		} catch (SomethingWentWrongException | NoRecordFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		int choice;
		do {
			System.out.println("1) Add Medicine");
			System.out.println("2) Update Medicine");
			System.out.println("3) Delete Medicine");
			System.out.println("4) View All Medicine");
			System.out.println("5) Search Medicine");
			System.out.println("0) Close Application");
			System.out.print("Enter Your Choice : ");
			choice = sc.nextInt();
			switch(choice) {
			case 1 -> addMedicine(sc) ;
			case 2 -> updateMedicine(sc);
			case 3 -> deleteMedicine(sc);
			case 4 -> viewMedicine(); 
			case 5 -> searchMedicine(sc);
			default -> System.out.println("Thank You For Use Our Application...!")  ;
			}
			
			
		}while(choice != 0);

	}

}
