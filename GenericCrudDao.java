// File: src/main/java/com/bookclub/service/GenericCrudDao.java

package com.bookclub.service;

import java.util.List;

/**
 * Generic CRUD DAO interface defining common operations for data access.
 *
 * @param <E> the entity type
 * @param <K> the key type
 */
public interface GenericCrudDao<E, K> {

    /**
     * Adds a new entity to the data store.
     *
     * @param entity the entity to add
     */
    void add(E entity);

    /**
     * Updates an existing entity in the data store.
     *
     * @param entity the entity to update
     */
    void update(E entity);

    /**
     * Removes an entity from the data store.
     *
     * @param entity the entity to remove
     * @return true if removal was successful, false otherwise
     */
    boolean remove(E entity);

    /**
     * Retrieves a list of all entities in the data store.
     *
     * @return a list of all entities
     */
    List<E> list();

    /**
     * Finds an entity by its key.
     *
     * @param key the identifier for the entity
     * @return the found entity or null if not found
     */
    E find(K key);
}
