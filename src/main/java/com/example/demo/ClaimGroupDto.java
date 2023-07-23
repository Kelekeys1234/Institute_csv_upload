package com.example.demo;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ClaimGroupDto {
	@JsonProperty("id")
    private String id;
	@JsonProperty("claim_name")
	private String claimName;
	@JsonProperty("description")
	private String description;
	@JsonProperty("claim_action")
	private String claimAction;
	@JsonProperty("resources")
	private Set<ResourceDto> resources;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClaimName() {
		return claimName;
	}
	public void setClaimName(String claimName) {
		this.claimName = claimName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getClaimAction() {
		return claimAction;
	}
	public void setClaimAction(String claimAction) {
		this.claimAction = claimAction;
	}
	public Set<ResourceDto> getResources() {
		return resources;
	}
	public void setResources(Set<ResourceDto> resources) {
		this.resources = resources;
	}
	
	

}
