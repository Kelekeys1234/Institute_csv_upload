package com.example.demo;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.type.TypeReference;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CallHandler {
	public static final String ACCESSIBILITY_CONNECTION_URL = "https://auth.yuzee.click/accessibility-service/api/v1/";

	@Autowired
	private RestTemplate restTemplate;

	public ResourceDto saveResource(String moduleId, String grantUserId, String accessToken, ResourceDto resourceDto) {
		ResponseEntity<ResourceDto> responseEntity = null;
		UriComponentsBuilder uriBuilder = null;
		try {
			StringBuilder path = new StringBuilder();
			path.append(ACCESSIBILITY_CONNECTION_URL).append("admin/resource").append("/").append("module/")
					.append(moduleId);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("userId", grantUserId);
			headers.set("Authorization", "Bearer " + accessToken);
			HttpEntity<ResourceDto> entity = new HttpEntity<>(resourceDto, headers);
			entity = new HttpEntity<>(resourceDto, headers);
			uriBuilder = UriComponentsBuilder.fromHttpUrl(path.toString());
			responseEntity = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.POST, entity,
					ResourceDto.class);
		} catch (Exception e) {
			log.error("Exception while uploading saveResource having exception " + e.getMessage());
		}
		return responseEntity.getBody();
	}

	public ClaimGroupDto saveClaimGroup(ClaimGroupDto claimGroupDto, String grantUserId, String accessToken) {
		ResponseEntity<ClaimGroupDto> responseEntity = null;
		try {
			StringBuilder path = new StringBuilder();
			path.append(ACCESSIBILITY_CONNECTION_URL).append("claim");
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("userId", grantUserId);
			headers.set("Authorization", "Bearer " + accessToken);
			HttpEntity<ClaimGroupDto> entity = new HttpEntity<>(claimGroupDto, headers);
			UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(path.toString());
			responseEntity = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.POST, entity,
					ClaimGroupDto.class);
		} catch (Exception e) {
			log.error("Exception while uploading saveClaimGroup having exception " + e);
			e.getMessage();
		}
		return responseEntity.getBody();
	}

	public List<ClaimGroupDto> getAllClaimGroup(String grantUserId, String accessToken) {
		GenericWrapperDto<List<ClaimGroupDto>> genericResponse = null;
		try {
			StringBuilder path = new StringBuilder();
			path.append(ACCESSIBILITY_CONNECTION_URL).append("claim");
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("userId", grantUserId);
			headers.set("Authorization", "Bearer " + accessToken);
			HttpEntity<String> entity = new HttpEntity<>(null, headers);
			UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(path.toString());
			ResponseEntity<String> responseEntity = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET,
					entity, String.class);
			genericResponse = ObjectMapperHelper.readValueFromJSON(responseEntity.getBody(),
					new TypeReference<GenericWrapperDto<List<ClaimGroupDto>>>() {
					});
		} catch (Exception e) {
			log.error("Exception while uploading saveClaimGroup having exception " + e);
			e.getMessage();
		}
		return genericResponse.getData();
	}

	public List<ResourceDto> getResource(String grantUserId, String accessToken) {
		GenericWrapperDto<List<ResourceDto>> genericResponse = null;
		UriComponentsBuilder uriBuilder = null;
		try {
			StringBuilder path = new StringBuilder();
			path.append(ACCESSIBILITY_CONNECTION_URL).append("admin/resource");
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("userId", grantUserId);
			headers.set("Authorization", "Bearer " + accessToken);
			HttpEntity<String> entity = new HttpEntity<String>(headers);
			uriBuilder = UriComponentsBuilder.fromHttpUrl(path.toString());
			ResponseEntity<String> responseEntity = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET,
					entity, String.class);
			genericResponse = ObjectMapperHelper.readValueFromJSON(responseEntity.getBody(),
					new TypeReference<GenericWrapperDto<List<ResourceDto>>>() {
					});
		} catch (Exception e) {
			log.error("Exception while getResource having exception " + e.getMessage());
		}
		return genericResponse.getData();
	}

	public ModuleDto saveModule(ModuleDto moduleDto, String grantUserId, String accessToken) {
		ResponseEntity<ModuleDto> responseEntity = null;
		try {
			StringBuilder path = new StringBuilder();
			path.append(ACCESSIBILITY_CONNECTION_URL).append("admin/module");
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("userId", grantUserId);
			headers.set("Authorization", "Bearer " + accessToken);
			HttpEntity<ModuleDto> entity = new HttpEntity<>(moduleDto, headers);
			UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(path.toString());
			responseEntity = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.POST, entity, ModuleDto.class);
		} catch (Exception e) {
			log.error("Exception while saveModule Careers having exception " + e);
			e.getMessage();
		}
		return responseEntity.getBody();
	}

	public List<ModuleDto> getAllModule(String grantUserId, String accessToken) {
		GenericWrapperDto<List<ModuleDto>> genericResponse = null;
		try {
			StringBuilder path = new StringBuilder();
			path.append(ACCESSIBILITY_CONNECTION_URL).append("admin/module");
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("userId", grantUserId);
			headers.set("Authorization", "Bearer " + accessToken);
			UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(path.toString());
			HttpEntity<String> entity = new HttpEntity<String>(headers);
			ResponseEntity<String> responseEntity = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET,
					entity, String.class);
			genericResponse = ObjectMapperHelper.readValueFromJSON(responseEntity.getBody(),
					new TypeReference<GenericWrapperDto<List<ModuleDto>>>() {
					});
		} catch (Exception e) {
			log.error("Exception while getAllModule having exception " + e);
			e.getMessage();
		}
		return genericResponse.getData();
	}

	public String saveRoleClaim(String roleId, String grantUserId, String accessToken, List<String> claimGroupId) {
		ResponseEntity<String> responseEntity = null;
		try {
			StringBuilder path = new StringBuilder();
			path.append(ACCESSIBILITY_CONNECTION_URL).append("roleClaim/role").append("/").append(roleId);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("userId", grantUserId);
			headers.set("Authorization", "Bearer " + accessToken);
			HttpEntity<List<String>> entity = new HttpEntity<>(claimGroupId, headers);
			UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(path.toString());
			responseEntity = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.POST, entity, String.class);
		} catch (Exception e) {
			e.getMessage();
		}
		return responseEntity.getBody();
	}

	public RoleDto saveRole(String grantUserId, String accessToken, RoleDto role) {
		ResponseEntity<RoleDto> responseEntity = null;
		try {
			StringBuilder path = new StringBuilder();
			path.append(ACCESSIBILITY_CONNECTION_URL).append("admin/role");
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("userId", grantUserId);
			headers.set("Authorization", "Bearer " + accessToken);
			HttpEntity<RoleDto> entity = new HttpEntity<>(role, headers);
			UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(path.toString());
			responseEntity = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.POST, entity, RoleDto.class);

		} catch (Exception e) {
			e.getMessage();
		}
		return responseEntity.getBody();
	}

	public List<RoleDto> getRole(String grantUserId, String accessToken) {
		GenericWrapperDto<List<RoleDto>> genericResponse = null;
		try {
			StringBuilder path = new StringBuilder();
			path.append(ACCESSIBILITY_CONNECTION_URL).append("admin/role");
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("userId", grantUserId);
			headers.set("Authorization", "Bearer " + accessToken);
			UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(path.toString());
			HttpEntity<String> entity = new HttpEntity<>(headers);
			ResponseEntity<String> responseEntity = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET,
					entity, String.class);
			genericResponse = ObjectMapperHelper.readValueFromJSON(responseEntity.getBody(),
					new TypeReference<GenericWrapperDto<List<RoleDto>>>() {
					});

		} catch (Exception e) {
			e.getMessage();

		}
		return genericResponse.getData();
	}
}
