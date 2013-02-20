package org.jboss.resteasy.examples.service;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiError;
import com.wordnik.swagger.annotations.ApiErrors;
import com.wordnik.swagger.annotations.ApiOperation;
import org.jboss.resteasy.examples.data.Book;
import org.jboss.resteasy.examples.data.BookListing;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.HashMap;

@Api(value = "/library", description = "the Library api")
@Path("/library")
public class Library {
    private HashMap<String, Book> books = new HashMap<String, Book>();

    public Library() {
        books.put("596529260", new Book("Leonard Richardson", "596529260", "RESTful Web Services"));
        books.put("333333333", new Book("Bill Burke", "596529260", "EJB 3.0"));
    }

    @GET
    @Path("/books")
    @Produces("application/json")
    @ApiOperation(value = "gets books ", notes = "gets books", responseClass = "org.jboss.resteasy.examples.data.BookListing")
    @ApiErrors(value = {@ApiError(code = 400, reason = "Not sure"), @ApiError(code = 404, reason = "bad")})
    public BookListing getBooks() {
        return getListing();
    }

    private BookListing getListing() {
        ArrayList<Book> list = new ArrayList<Book>();
        list.addAll(books.values());
        return new BookListing(list);
    }
}
