package com.example.demo;

import java.util.UUID;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModuleDto {

	@JsonProperty("module_id")
	private UUID moduleId;
	
	@JsonProperty("module_name")
	private String moduleName;
	
	@JsonProperty("sub_module_name")
	private String subModuleName;
	
	@JsonProperty("module_description")
	private String moduleDescription;
}
