/******************************************************************************
 * File: BookDao.java
 * Author: Deb Meyer-Gardner
 * Created: 2025-03-26
 * Description: This interface extends GenericDao for managing Book entities.
 *              It provides a contract for listing and retrieving books by ISBN
 *              within the Bookclub application.
 ******************************************************************************/

 package com.bookclub.service.dao;

import com.bookclub.model.Book;
import com.bookclub.service.GenericDao;
 
 /**
  * BookDao defines the data access contract for Book entities.
  * It extends the GenericDao interface using Book as the entity type
  * and String as the key type (ISBN).
  *
  * Implementing classes (e.g., MemBookDao) will provide logic for
  * listing all books and finding books by their ISBN.
  */
 public interface BookDao extends GenericDao<Book, String> {
     // Inherits:
     // List<Book> list();
     // Book find(String key);
 }
 