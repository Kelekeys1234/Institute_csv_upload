package com.example.demo;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

	@JsonProperty("role_id")
	private String roleId;

	@JsonProperty("role_name")
	private String roleName;
	
	@JsonProperty("description")
	private String description;

}
