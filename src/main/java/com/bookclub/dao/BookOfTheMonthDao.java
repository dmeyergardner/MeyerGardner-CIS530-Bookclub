package com.bookclub.dao;

import com.bookclub.model.BookOfTheMonth;
import com.bookclub.service.GenericCrudDao;

/**
 * BookOfTheMonthDao defines the contract for CRUD operations
 * on BookOfTheMonth entities. This interface extends the
 * GenericCrudDao interface with type parameters BookOfTheMonth and String.
 *
 * Implementing classes should provide MongoDB-based or other storage logic
 * to handle operations such as listing, adding, finding, updating, and removing
 * book entries designated for a particular month.
 */
public interface BookOfTheMonthDao extends GenericCrudDao<BookOfTheMonth, String> {
    // All required methods are inherited from GenericCrudDao
}
