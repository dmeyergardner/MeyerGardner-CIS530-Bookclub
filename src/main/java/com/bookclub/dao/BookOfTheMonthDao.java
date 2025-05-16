// File: src/main/java/com/bookclub/dao/BookOfTheMonthDao.java

package com.bookclub.dao;

import com.bookclub.model.BookOfTheMonth;
import com.bookclub.service.GenericCrudDao;

/**
 * BookOfTheMonthDao defines the contract for performing CRUD operations
 * on BookOfTheMonth entities. It extends the GenericCrudDao interface
 * with type parameters BookOfTheMonth and String.
 *
 * This DAO interface supports operations such as:
 * - Adding a new book of the month entry
 * - Updating an existing entry
 * - Removing an entry by ID
 * - Listing entries filtered by a key (such as month number or static token)
 * - Finding a single entry by its ID
 *
 * Implementing classes, such as MongoBookOfTheMonthDao, should define
 * the data source and logic for these operations, typically using MongoDB or
 * another persistence mechanism.
 *
 * Example usage in a controller:
 * 
 * <pre>
 * List&lt;BookOfTheMonth&gt; list = bookOfTheMonthDao.list("999");
 * </pre>
 *
 * @author
 */
public interface BookOfTheMonthDao extends GenericCrudDao<BookOfTheMonth, String> {
    // No additional methods; uses inherited GenericCrudDao methods
}
