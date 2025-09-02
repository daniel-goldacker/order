package org.acme.client;

import org.acme.dto.CustomerDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/customers")
@RegisterRestClient(configKey="customer-api")
@ApplicationScoped
public interface CustomerClient {
    @GET
    @Path("/{id}/")
    CustomerDTO getCustomerById(@PathParam("id") Long id);
}
