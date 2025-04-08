// File: src/main/java/com/bookclub/service/dao/WishlistDao.java

package com.bookclub.service.dao;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.GenericCrudDao;

/**
 * DAO interface for WishlistItem, extending generic CRUD operations.
 */
public interface WishlistDao extends GenericCrudDao<WishlistItem, String> {
    // All methods inherited from GenericCrudDao
}
