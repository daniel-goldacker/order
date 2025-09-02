package org.acme.client;

import org.acme.dto.CustomerDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/products")
@RegisterRestClient
@ApplicationScoped
public interface ProductClient {
    @GET
    @Path("/{id}/")
    CustomerDTO getProductById(@PathParam("id") Long id);
}
