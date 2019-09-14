package com.shankulk.resource;

import com.shankulk.domain.Account;
import java.net.URI;
import java.net.URISyntaxException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/accounts")
public class AccountsRoute {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAccounts() {
        //TODO code goes here
        return Response.ok(null, MediaType.APPLICATION_JSON_TYPE).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAccount(Account account) throws URISyntaxException {
        //TODO code goes here
        return Response.created(new URI("/accounts")).entity(null).build();
    }

}
