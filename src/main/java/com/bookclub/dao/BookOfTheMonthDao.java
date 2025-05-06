// File: src/main/java/com/bookclub/dao/BookOfTheMonthDao.java

package com.bookclub.dao;

import com.bookclub.model.BookOfTheMonth;
import com.bookclub.service.GenericCrudDao;

/**
 * DAO interface for BookOfTheMonth records.
 * 
 * Extends the generic CRUD interface to support basic operations
 * such as list, add, and remove, using String as the key type.
 */
public interface BookOfTheMonthDao extends GenericCrudDao<BookOfTheMonth, String> {
    // Inherits:
    // List<BookOfTheMonth> list(String key);
    // void add(BookOfTheMonth item);
    // boolean remove(String key);
}
