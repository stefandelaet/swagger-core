package org.jboss.resteasy.examples.service;

import com.google.common.collect.ImmutableMap;
import org.jboss.resteasy.examples.data.Book;
import org.jboss.resteasy.examples.data.BookListing;

import java.util.ArrayList;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;

public class Library implements LibraryResource {
    private static final Map<String, Book> BOOKS = ImmutableMap.of(
            "596529260", new Book("Leonard Richardson", "596529260", "RESTful Web Services"),
            "333333333", new Book("Bill Burke", "596529260", "EJB 3.0"));

    @Override
    public BookListing getBooks() {
        return getListing();
    }

    private BookListing getListing() {
        ArrayList<Book> list = newArrayList();
        list.addAll(BOOKS.values());
        return new BookListing(list);
    }
}
