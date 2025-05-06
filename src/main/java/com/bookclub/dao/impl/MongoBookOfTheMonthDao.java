// File: src/main/java/com/bookclub/dao/impl/MongoBookOfTheMonthDao.java

package com.bookclub.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.bookclub.dao.BookOfTheMonthDao;
import com.bookclub.model.BookOfTheMonth;

/**
 * MongoDB implementation of BookOfTheMonthDao.
 * Handles storage and retrieval of monthly book selections using MongoTemplate.
 */
@Repository("BookOfTheMonthDao")
public class MongoBookOfTheMonthDao implements BookOfTheMonthDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * Retrieves a list of BookOfTheMonth entries filtered by the provided month.
     * If the key is "999", all records are returned.
     *
     * @param key the string representation of the month (1–12) or "999" for all
     * @return list of BookOfTheMonth records
     */
    @Override
    public List<BookOfTheMonth> list(String key) {
        int month = Integer.parseInt(key);
        if (month == 999) {
            return mongoTemplate.findAll(BookOfTheMonth.class);
        }
        Query query = new Query(Criteria.where("month").is(month));
        return mongoTemplate.find(query, BookOfTheMonth.class);
    }

    /**
     * Adds a new BookOfTheMonth entry to the collection.
     *
     * @param item the BookOfTheMonth object to insert
     */
    @Override
    public void add(BookOfTheMonth item) {
        mongoTemplate.insert(item);
    }

    /**
     * Removes a BookOfTheMonth entry by ID.
     *
     * @param id the ID of the record to remove
     * @return true if the record was acknowledged and removed
     */
    @Override
    public boolean remove(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.remove(query, BookOfTheMonth.class).wasAcknowledged();
    }

    /**
     * Updates a BookOfTheMonth entry.
     * Currently unimplemented.
     *
     * @param entity the entity to update
     * @throws UnsupportedOperationException if called
     */
    @Override
    public void update(BookOfTheMonth entity) {
        throw new UnsupportedOperationException("Update not yet supported.");
    }

    /**
     * Finds a BookOfTheMonth by ID.
     * Currently unimplemented.
     *
     * @param key the ID to search for
     * @return BookOfTheMonth object
     * @throws UnsupportedOperationException if called
     */
    @Override
    public BookOfTheMonth find(String key) {
        throw new UnsupportedOperationException("Find not yet supported.");
    }
}
