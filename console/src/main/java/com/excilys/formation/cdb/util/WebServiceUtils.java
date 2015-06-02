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
	public final static String computerPath = "computers/";
	public final static String companyPath = "companies/";
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
		return getWebTarget(computerPath + id).get();
	}

	public static Response getFindCompanyResponse(Long id) {
		return getWebTarget(companyPath + id).get();
	}

	public static Response getFindAllComputerResponse() {
		return getWebTarget(computerPath).get();
	}

	public static Response getFindAllCompanyResponse() {
		return getWebTarget(companyPath).get();
	}

	public static Response deleteComputerResponse(Long id) {
		return getWebTarget(computerPath + id).delete();
	}

	public static Response deleteCompanyResponse(Long id) {
		return getWebTarget(companyPath + id).delete();
	}

	public static Response postComputerResponse(ComputerDTO computer,
			MediaType mediatType) {
		return getWebTarget(computerPath).post(
				Entity.entity(computer, mediatType));
	}

	public static Response putComputerResponse(ComputerDTO computer,
			MediaType mediatType) {
		return getWebTarget(computerPath).put(
				Entity.entity(computer, mediatType));
	}

	public static Builder getWebTarget(String path) {
		return CLIENT.target("http://localhost:8080/webservice/").path(path)
				.request().cookie("computerDatabaseLocale", "fr");
	}

	public static ComputerDTO getComputer(Long id) {
		Response response = getFindComputerResponse(id);
		if ((response.getStatus() != 200) || !response.hasEntity()) {
			return null;
		}
		ComputerDTO res = response.readEntity(ComputerDTO.class);
		return res;
	}

	public static CompanyDTO getCompany(Long id) {
		Response response = getFindCompanyResponse(id);
		if ((response.getStatus() != 200) || !response.hasEntity()) {
			return null;
		}
		CompanyDTO res = response.readEntity(CompanyDTO.class);
		response.close();
		return res;
	}

	public static List<ComputerDTO> findAllComputer() {
		Response response = getFindAllComputerResponse();
		if ((response.getStatus() != 200) || !response.hasEntity()) {
			response.close();
			return null;
		}
		List<ComputerDTO> res = response
				.readEntity(new GenericType<List<ComputerDTO>>() {
				});
		response.close();
		return res;
	}

	public static List<CompanyDTO> findAllCompany() {
		Response response = getFindAllCompanyResponse();
		if ((response.getStatus() != 200) || !response.hasEntity()) {
			return null;
		}
		List<CompanyDTO> res = response
				.readEntity(new GenericType<List<CompanyDTO>>() {
				});
		return res;
	}
}
