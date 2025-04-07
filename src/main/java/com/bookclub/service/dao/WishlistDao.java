// File: src/main/java/com/bookclub/service/dao/WishlistDao.java

package com.bookclub.service.dao;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.GenericDao;

/**
 * DAO interface for managing WishlistItem entities.
 * Extends GenericDao with WishlistItem as the entity type and String as the key type (ISBN).
 */
public interface WishlistDao extends GenericDao<WishlistItem, String> {
    // Inherits list() and find(String key) methods
}
