/******************************************************************************
 * File: RestBookDao.java
 * Author: Deb Meyer-Gardner
 * Created: 2025-03-26
 * Description: REST-based implementation of the BookDao interface using
 *              OpenLibrary API. Fetches book data by ISBNs for display
 *              in the Bookclub application.
 ******************************************************************************/

package com.bookclub.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.bookclub.model.Book;
import com.bookclub.service.dao.BookDao;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

/**
 * REST-based implementation of the BookDao interface.
 * <p>
 * Communicates with the OpenLibrary API to fetch book data
 * based on ISBN numbers. Uses Spring's RestTemplate for HTTP
 * calls and JsonPath to parse responses.
 * </p>
 */
@Repository
public class RestBookDao implements BookDao {

    /**
     * Makes an HTTP GET request to the OpenLibrary API for a set of ISBNs.
     *
     * @param isbnString Comma-separated list of ISBNs prefixed with "ISBN:"
     *                   (e.g., "ISBN:0451526538,ISBN:9780140328721").
     * @return Parsed JSON object containing book data.
     */
    public Object getBooksDoc(String isbnString) {
        String openLibraryUrl = "https://openlibrary.org/api/books";

        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(openLibraryUrl)
                .queryParam("bibkeys", isbnString)
                .queryParam("format", "json")
                .queryParam("jscmd", "data");

        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = rest.exchange(
                builder.toUriString(), HttpMethod.GET, entity, String.class);

        String jsonBookList = response.getBody();
        return Configuration.defaultConfiguration().jsonProvider().parse(jsonBookList);
    }

    /**
     * Returns a list of Book objects from the OpenLibrary API
     * based on a provided string of ISBNs.
     *
     * @param isbnString Comma-separated ISBNs prefixed with "ISBN:"
     * @return list of Book objects
     */
    @Override
    public List<Book> list(String isbnString) {
        Object doc = getBooksDoc(isbnString);
        List<String> isbns = JsonPath.read(doc, "$..key");

        List<Book> books = new ArrayList<>();
        for (String key : isbns) {
            Book book = new Book();

            String titlePath = "$." + key + ".title";
            String numOfPagesPath = "$." + key + ".number_of_pages";
            String infoUrlPath = "$." + key + ".url";

            List<String> titleList = JsonPath.read(doc, titlePath);
            List<Integer> numOfPagesList = JsonPath.read(doc, numOfPagesPath);
            List<String> infoUrlList = JsonPath.read(doc, infoUrlPath);

            book.setIsbn(key.replace("ISBN:", ""));
            book.setTitle(titleList.size() > 0 ? titleList.get(0) : "N/A");
            book.setNumOfPages(numOfPagesList.size() > 0 ? numOfPagesList.get(0) : 0);
            book.setInfoUrl(infoUrlList.size() > 0 ? "https://openlibrary.org" + infoUrlList.get(0) : "N/A");
            book.setDescription("N/A");

            books.add(book);
        }
        return books;
    }

    /**
     * Finds and returns a single Book object based on ISBN.
     *
     * @param isbn The ISBN of the book to look up (digits only, no "ISBN:" prefix)
     * @return Book object with available metadata
     */
    @Override
    public Book find(String isbn) {
        Object doc = getBooksDoc("ISBN:" + isbn);

        Book book = new Book();

        String titlePath = "$.ISBN:" + isbn + ".title";
        String numOfPagesPath = "$.ISBN:" + isbn + ".number_of_pages";
        String infoUrlPath = "$.ISBN:" + isbn + ".url";
        String descriptionPath = "$.ISBN:" + isbn + ".description.value";

        List<String> titleList = JsonPath.read(doc, titlePath);
        List<Integer> numOfPagesList = JsonPath.read(doc, numOfPagesPath);
        List<String> infoUrlList = JsonPath.read(doc, infoUrlPath);
        List<String> descriptionList = JsonPath.read(doc, descriptionPath);

        book.setIsbn(isbn);
        book.setTitle(titleList.size() > 0 ? titleList.get(0) : "N/A");
        book.setNumOfPages(numOfPagesList.size() > 0 ? numOfPagesList.get(0) : 0);
        book.setInfoUrl(infoUrlList.size() > 0 ? "https://openlibrary.org" + infoUrlList.get(0) : "N/A");
        book.setDescription(descriptionList.size() > 0 ? descriptionList.get(0) : "No description available.");

        return book;
    }
}
