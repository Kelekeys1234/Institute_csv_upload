package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResourceDto {

	@JsonProperty("resource_id")
	private String id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("path")
	private String path;

	@JsonProperty("module")
	private Module module;

	@JsonProperty("http_method")
	private String httpMethod;
	
	public ResourceDto(String id, String name, String path, String httpMethod) {
		super();
		this.id = id;
		this.name = name;
		this.path = path;
		this.httpMethod = httpMethod;
	}
	
	

}
