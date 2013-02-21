package org.jboss.resteasy.examples.service;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiError;
import com.wordnik.swagger.annotations.ApiErrors;
import com.wordnik.swagger.annotations.ApiOperation;
import org.jboss.resteasy.examples.data.BookListing;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Api(value = "/library", description = "the Library api")
@Path("/library")
public interface LibraryResource {
    @GET
    @Path("/books")
    @Produces("application/json")
    @ApiOperation(value = "gets books ", notes = "gets books", responseClass = "org.jboss.resteasy.examples.data.BookListing")
    @ApiErrors(value = {@ApiError(code = 400, reason = "Not sure"), @ApiError(code = 404, reason = "bad")})
    BookListing getBooks();
}
