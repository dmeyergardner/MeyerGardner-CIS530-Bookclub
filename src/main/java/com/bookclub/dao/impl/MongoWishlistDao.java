// File: src/main/java/com/bookclub/dao/impl/MongoWishlistDao.java

package com.bookclub.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.bookclub.dao.WishlistDao;
import com.bookclub.model.WishlistItem;
import com.mongodb.client.result.DeleteResult;

/**
 * MongoDB-based implementation of the WishlistDao interface.
 * This class provides CRUD operations on the Wishlist collection
 * using Spring Data MongoTemplate.
 * 
 * Developer Notes:
 * - Added user-based filtering for list().
 * - Added full support for update and delete operations.
 */
@Repository
public class MongoWishlistDao implements WishlistDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * Adds a new wishlist item to the database.
     *
     * @param wishlistItem the wishlist item to add
     */
    @Override
    public void add(WishlistItem wishlistItem) {
        mongoTemplate.insert(wishlistItem);
    }

    /**
     * Lists wishlist items for a specific username.
     *
     * @param username the username to filter wishlist items by
     * @return list of wishlist items for the given user
     */
    @Override
    public List<WishlistItem> list(String username) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));
        return mongoTemplate.find(query, WishlistItem.class);
    }

    /**
     * Finds a wishlist item by its ID.
     *
     * @param id the id of the wishlist item
     * @return the found wishlist item or null if not found
     */
    @Override
    public WishlistItem find(String id) {
        return mongoTemplate.findById(id, WishlistItem.class);
    }

    /**
     * Updates an existing wishlist item.
     * 
     * Developer Note:
     * - Uses MongoDB's save() method, which will perform an insert if no ID exists.
     * - Only works properly when the ID field is populated.
     *
     * @param wishlistItem the updated wishlist item
     */
    @Override
    public void update(WishlistItem wishlistItem) {
        mongoTemplate.save(wishlistItem);
    }

    /**
     * Removes a wishlist item by its ID.
     *
     * Developer Note:
     * - Returns true if the delete operation was acknowledged by MongoDB.
     * - Uses a Query and Criteria to match and delete the correct document.
     *
     * @param id the id of the wishlist item to remove
     * @return true if the delete operation was acknowledged, false otherwise
     */
    @Override
    public boolean remove(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        DeleteResult result = mongoTemplate.remove(query, WishlistItem.class);
        return result.wasAcknowledged();
    }
}
