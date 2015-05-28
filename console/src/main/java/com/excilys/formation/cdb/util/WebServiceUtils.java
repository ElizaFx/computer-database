package com.excilys.formation.cdb.util;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;

import com.excilys.formation.cdb.dto.CompanyDTO;
import com.excilys.formation.cdb.dto.ComputerDTO;

public class WebServiceUtils {
	public final static String computerPath = "computer/";
	public final static String companyPath = "company/";
	public final static Client CLIENT;
	static {
		CLIENT = ClientBuilder
				.newBuilder()
				.withConfig(
						new ClientConfig()
								.connectorProvider(new HttpUrlConnectorProvider()))
				.build();
	}

	public static Response getFindComputerResponse(Long id) {
		return getWebTarget(computerPath + "find/" + id).get();
	}

	public static Response getFindCompanyResponse(Long id) {
		return getWebTarget(companyPath + "find/" + id).get();
	}

	public static Response getFindAllComputerResponse() {
		return getWebTarget(computerPath + "findAll").get();
	}

	public static Response getFindAllCompanyResponse() {
		return getWebTarget(companyPath + "findAll").get();
	}

	public static Response deleteComputerResponse(Long id) {
		return getWebTarget(computerPath + "remove/" + id).delete();
	}

	public static Response deleteCompanyResponse(Long id) {
		return getWebTarget(companyPath + "remove/" + id).delete();
	}

	public static Response postComputerResponse(ComputerDTO computer,
			MediaType mediatType) {
		return getWebTarget(computerPath + "create").post(
				Entity.entity(computer, mediatType));
	}

	public static Response putComputerResponse(ComputerDTO computer,
			MediaType mediatType) {
		return getWebTarget(computerPath + "edit").put(
				Entity.entity(computer, mediatType));
	}

	public static Builder getWebTarget(String path) {
		return CLIENT.target("http://localhost:8080/webservice/json")
				.path(path).request().cookie("computerDatabaseLocale", "fr");
	}

	public static ComputerDTO getComputer(Long id) {
		Response response = getFindComputerResponse(id);
		if ((response.getStatus() != 200) || !response.hasEntity()) {
			return null;
		}
		return response.readEntity(ComputerDTO.class);
	}

	public static CompanyDTO getCompany(Long id) {
		Response response = getFindCompanyResponse(id);
		if ((response.getStatus() != 200) || !response.hasEntity()) {
			return null;
		}
		return response.readEntity(CompanyDTO.class);
	}

	public static List<ComputerDTO> findAllComputer() {
		Response response = getFindAllComputerResponse();
		if ((response.getStatus() != 200) || !response.hasEntity()) {
			System.out.println(response);
			return null;
		}
		return response.readEntity(new GenericType<List<ComputerDTO>>() {
		});
	}

	public static List<CompanyDTO> findAllCompany() {
		Response response = getFindAllCompanyResponse();
		if ((response.getStatus() != 200) || !response.hasEntity()) {
			return null;
		}
		return response.readEntity(new GenericType<List<CompanyDTO>>() {
		});
	}
}
