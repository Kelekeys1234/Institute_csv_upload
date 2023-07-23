package com.example.demo;

import com.opencsv.bean.CsvBindByName;

import com.opencsv.bean.CsvBindByPosition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeDto {
	@CsvBindByPosition(position =0)
	private String Sr_No;
	@CsvBindByPosition(position =1)
	private String MODULE_NAME;
	@CsvBindByPosition(position =2)
	private String SUB_MODULE_NAME;
	@CsvBindByPosition(position =3)
	private String API_path;
	@CsvBindByPosition(position =4)
	private String HTTP_Method;
	@CsvBindByPosition(position =5)
	private String API_name;
	@CsvBindByPosition(position =6)
	private String Contoller_Name;
	@CsvBindByPosition(position =7)
	private String Claim_name;
	@CsvBindByPosition(position =8)
	private String ACTION;
	@CsvBindByPosition(position =9)
	private String Role_name;
}
