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
 * MongoBookOfTheMonthDao implements BookOfTheMonthDao
 * using MongoDB as the data store. It provides methods
 * for listing, adding, and removing BookOfTheMonth records.
 *
 * This DAO supports filtering by month (as key),
 * useful for role-based or time-based queries.
 */
@Repository("bookOfTheMonthDao")
public class MongoBookOfTheMonthDao implements BookOfTheMonthDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * Lists BookOfTheMonth entries.
     * If key is "999", returns all entries. Otherwise filters by month.
     *
     * @param key Month number as string, or "999" to return all entries.
     * @return List of BookOfTheMonth records.
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
     * Adds a new BookOfTheMonth entry.
     *
     * @param item BookOfTheMonth to be added.
     */
    @Override
    public void add(BookOfTheMonth item) {
        mongoTemplate.insert(item);
    }

    /**
     * Removes a BookOfTheMonth entry by ID.
     *
     * @param id ID of the book to remove.
     * @return true if removal was acknowledged, false otherwise.
     */
    @Override
    public boolean remove(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.remove(query, BookOfTheMonth.class).wasAcknowledged();
    }

    /**
     * Not implemented: update logic.
     *
     * @param entity BookOfTheMonth entity to update.
     */
    @Override
    public void update(BookOfTheMonth entity) {
        throw new UnsupportedOperationException("Update not implemented.");
    }

    /**
     * Not implemented: find logic.
     *
     * @param key ID to find.
     * @return BookOfTheMonth record (not implemented).
     */
    @Override
    public BookOfTheMonth find(String key) {
        throw new UnsupportedOperationException("Find not implemented.");
    }
}
