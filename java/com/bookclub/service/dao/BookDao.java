// File: src/main/java/com/bookclub/service/dao/BookDao.java
package com.bookclub.service.dao;

import java.com.bookclub.model.Book;
import java.com.bookclub.service.GenericDao;

public interface BookDao extends GenericDao<Book, String> {
    // inherits list() and find(String key)
}