package com.misofertas.rest.jersey.server;

import java.net.MalformedURLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.misofertas.data.model.EnvioMail;
import com.misofertas.data.model.Usuario;

@Path("/usuario/data")
public class RestServer {
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario getUsuarioRecord() {
		Usuario usuario = new Usuario();
		usuario.setNombre("Sergio");
		usuario.setApellido("Vega");
		usuario.setEmail("ser.vegap@alumnos.duoc.cl");
		
		return usuario;
	}
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postUsuarioRecord(Usuario usuario) throws MalformedURLException {
		String result = "Registro ingresado: " + usuario;
		EnvioMail em = new EnvioMail();
		em.getMail(usuario);
		return Response.status(201).entity(result).build();
	}
}
