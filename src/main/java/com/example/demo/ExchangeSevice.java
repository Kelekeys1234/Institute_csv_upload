package com.example.demo;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ExchangeSevice {
	@Autowired
	private CallHandler CallHandler;
	private String userId = "6f6d733c-58ae-4e08-81dd-e32db944770a";
	private String token = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJPUE00U0ozZWtIRzNiWjZlN0I0VXd3R2VVVTVwUngtQy1RNlJ2MVY1emlrIn0.eyJleHAiOjE2OTAwNzM5MzksImlhdCI6MTY5MDA3MzMzOSwianRpIjoiNDE3OTg3NjktNTk1Yy00N2Q1LTg2MzktYjhhNzMzMzA3NGRlIiwiaXNzIjoiaHR0cHM6Ly9hdXRoLnl1emVlLmNsaWNrL2F1dGgvcmVhbG1zL3l1emVlIiwiYXVkIjoiYWNjb3VudCIsInN1YiI6IjZmNmQ3MzNjLTU4YWUtNGUwOC04MWRkLWUzMmRiOTQ0NzcwYSIsInR5cCI6IkJlYXJlciIsImF6cCI6Inl1emVlX2NsaWVudCIsInNlc3Npb25fc3RhdGUiOiJjM2U2MWMyZC1jMzU5LTQ0MTktOGU2MS0yZjlhNTIyNjU4OTkiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbIioiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIlN0dWRlbnQiXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6Im9wZW5pZCBlbWFpbCBwcm9maWxlIiwicmVhbG1fYWNjZXNzeSI6eyJyb2xlcyI6WyJTdHVkZW50Il19LCJ1c2VyU2lnbmVkVXAiOnRydWUsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwibmFtZSI6Ik1ZTkFNRUFTRlVMTFlDQVBJVEFMIFRvZGF5IiwicHJlZmVycmVkX3VzZXJuYW1lIjoiZ2F1cmFuZ3BhaG1AeW9wbWFpbC5jb20iLCJnaXZlbl9uYW1lIjoiTVlOQU1FQVNGVUxMWUNBUElUQUwiLCJmYW1pbHlfbmFtZSI6IlRvZGF5IiwiZW1haWwiOiJnYXVyYW5ncGFobUB5b3BtYWlsLmNvbSJ9.PEtw4dvjIOqGqH6I9WJjOpovjB8dbPXKSX8oRS1Rirjf27XeoZrtIkcDdp81FYMxWDHJhHCSzdH9m0moBc44nEO64hot3eSK2aldDmDR_n9YB8b41Iie6gQUv3szykvqCScAjlpzE8qDDD4qZNHAH5afrok6KSjAzbQZCWJ3NG6YVehrx4iRrrFHMviqj3nzlap6xXrfWytCRG6mj2F7Sy0Vq3TAxbDlpf-3_6GvBPu173mUMZ60SMpeV0MTph3scENJslBwJ1GrvA9pEqRKT01tGqyfd4xwjLXqcG5Wi9oNFGrZ7YhEWZqO7L2wOb3ltb8l69j5A-Zq-YuyPCxCUg";
	List<ExchangeDto> exchange = new ArrayList<>();

	@jakarta.annotation.PostConstruct
	public void init() throws IOException {
		CSVReader in = new CSVReader(new FileReader("Institute_Service.csv"), ',');
		HeaderColumnNameMappingStrategy<ExchangeDto> beanStrategy = new HeaderColumnNameMappingStrategy<ExchangeDto>();
		beanStrategy.setType(ExchangeDto.class);
		CsvToBean<ExchangeDto> csvToBean = new CsvToBean<ExchangeDto>();
		exchange = csvToBean.parse(beanStrategy, in);
		try {
			exchange.forEach(e -> {
				ModuleDto saveModule = null;
				RoleDto saveRole = null;
				ResourceDto saveResource = null;
				ClaimGroupDto saveClaim = null;

				List<ModuleDto> moduleCall = CallHandler.getAllModule(userId, token);
				if (moduleCall.stream().noneMatch(a -> a.getModuleName().equalsIgnoreCase(e.getMODULE_NAME()))) {
					ModuleDto module = new ModuleDto(UUID.randomUUID(), e.getMODULE_NAME(), e.getSUB_MODULE_NAME(),
							"description");
					saveModule = CallHandler.saveModule(module, userId, token);
				}

				List<RoleDto> roleCall = CallHandler.getRole(userId, token);
				if (roleCall.stream().noneMatch(a -> a.getRoleName().equalsIgnoreCase(e.getRole_name()))) {
					RoleDto role = new RoleDto(UUID.randomUUID().toString(), e.getRole_name(), "desc");
					if (!StringUtils.isEmpty(role.getRoleName())) {
						saveRole = CallHandler.saveRole(userId, token, role);
					}
				}

				List<ResourceDto> resourceCall = CallHandler.getResource(userId, token);
				if (resourceCall.stream().noneMatch(a -> a.getName().equalsIgnoreCase(e.getAPI_name()))) {
					ResourceDto resource = new ResourceDto(UUID.randomUUID().toString(), e.getAPI_name(),
							e.getAPI_path(), e.getHTTP_Method());
					if (!ObjectUtils.isEmpty(saveModule)) {
						saveResource = CallHandler.saveResource(saveModule.getModuleId().toString(), userId, token,
								resource);
					} else if (ObjectUtils.isEmpty(saveModule)) {
						String existingModuleIdFromDb = moduleCall.stream()
								.filter(q -> q.getModuleName().equalsIgnoreCase(e.getMODULE_NAME()))
								.map(b -> b.getModuleId().toString()).collect(Collectors.joining(","));
						saveResource = CallHandler.saveResource(existingModuleIdFromDb, userId, token, resource);
					}

				}
				// Action not yet implemented in excel
				// yet to implement claim ..

				List<ClaimGroupDto> groupCall = CallHandler.getAllClaimGroup(userId, token);
				if (groupCall.stream().noneMatch(a -> !a.getClaimName().equalsIgnoreCase(e.getClaim_name()))) {
					Set<ResourceDto> set = new HashSet<>(Arrays.asList(saveResource));
					ClaimGroupDto group = new ClaimGroupDto(UUID.randomUUID().toString(), e.getClaim_name(),
							"description", e.getACTION(), set);
					saveClaim = CallHandler.saveClaimGroup(group, userId, token);
				}
				// Action not yet implemented because claim is yet to be saved..
				
				// yet to implement claim ..

				CallHandler.saveRoleClaim(saveRole.getRoleId(), userId, token, Arrays.asList(saveClaim.getId()));

			});
		} catch (Exception e) {
			log.error("Exception while uploading institute_service having exception " + e);
		}
		if (in != null) {
			in.close();
		}
	}
}
