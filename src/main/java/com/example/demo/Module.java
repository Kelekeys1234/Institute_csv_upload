package com.example.demo;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Module implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UUID id;

	private String moduleName;

	private String subModuleName;

	private String description;

	private Date createdOn;

	private Date updatedOn;

	private UUID createdBy;

	private UUID updatedBy;

}
