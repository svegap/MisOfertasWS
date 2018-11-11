package com.misofertas.rest.jersey.client;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RestClient {
	Client client = Client.create();
	String getUrl = "https://misofertas.azurewebsites.net/GeneraMail/rest/usuario/data/get";
	String postUrl = "https://misofertas.azurewebsites.net/GeneraMail/rest/usuario/data/post";

	public void getRequest() {
		WebResource webResource = client.resource(getUrl);
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		if (response.getStatus() != 200) {
			throw new RuntimeException("HTTP Error: " + response.getStatus());
		}
		String resultado = response.getEntity(String.class);
		System.out.println("Respuesta desde el servidor: ");
		System.out.println(resultado);
	}

	public void postRequest() throws JSONException {
		WebResource webResource = client.resource(postUrl);

		JSONObject obj = new JSONObject(); 
		obj.put("nombre","Sergio Vega" ); 
		obj.put("email", "svegap@gmail.com"); 

				String datosEntrada = obj.toString();
		ClientResponse response = webResource.type("application/json").post(ClientResponse.class, datosEntrada);
				if (response.getStatus() != 201) {
					throw new RuntimeException("HTTP Error: " + response.getStatus());
				}
				String resultado = response.getEntity(String.class);
				System.out.println("Respuesta desde el servidor: ");
				System.out.println(resultado);
	}

	public static void main(String[] args) throws JSONException {
		RestClient restClient = new RestClient();
		restClient.getRequest();
		restClient.postRequest();
	}
}
