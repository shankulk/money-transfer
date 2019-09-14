package com.shankulk.resource;

import com.shankulk.dto.MovementRequest;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/money")
public class MoneyTransferRoute {

    @Path("/movements/{sourceAccountId}")
    public Response moveMoney(@PathParam("sourceAccountId") final long sourceAccountId, final MovementRequest request) {
        //TODO code goes here
        return Response.ok().build();
    }
}
