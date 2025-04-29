/******************************************************************************
 * File: MemBookDao.java
 * Author: Deb Meyer-Gardner
 * Created: 2025-03-26
 * Description: This class implements the BookDao interface using in-memory
 *              storage. It provides access to a static list of book data for
 *              display and detail view within the Bookclub application.
 ******************************************************************************/

// File: src/main/java/com/bookclub/dao/impl/RestBookDao.java

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
 * This DAO interacts with the OpenLibrary API to fetch book information based
 * on ISBN numbers.
 * </p>
 * <p>
 * It uses Spring's RestTemplate to perform HTTP requests and JsonPath for
 * parsing JSON responses.
 * </p>
 * 
 * Example OpenLibrary API call: https://openlibrary.org/api/books
 *
 * @author
 */
@Repository
public class RestBookDao implements BookDao {

    /**
     * Calls the OpenLibrary API with a list of ISBNs and returns the parsed
     * document.
     *
     * @param isbnString Comma-separated string of ISBNs to query.
     * @return Parsed JSON document containing the book information.
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
     * Retrieves a list of Book objects by querying the OpenLibrary API.
     *
     * @return List of Book objects.
     */
    @Override
    public List<Book> list() {
        Object doc = getBooksDoc("ISBN:0451526538,ISBN:9780140328721,ISBN:9780316769488,ISBN:9780061120084");

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
            book.setDescription("N/A"); // OpenLibrary API doesn't provide description directly here.

            books.add(book);
        }
        return books;
    }

    /**
     * Retrieves a single Book object based on the given ISBN.
     *
     * @param isbn The ISBN of the book to retrieve.
     * @return Book object matching the ISBN or null if not found.
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
