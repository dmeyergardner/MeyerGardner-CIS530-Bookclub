/******************************************************************************
 * File: GenericDao.java
 * Author: Deb Meyer-Gardner
 * Created: 2025-03-26
 * Description: Generic data access interface for listing and finding objects.
 *              This interface supports reusable DAO patterns using generics.
 ******************************************************************************/

package com.bookclub.service;

import java.util.List;

/**
 * GenericDao is a reusable interface for basic data access operations.
 * It supports listing all entities and finding a specific entity by key.
 *
 * @param <E> the entity type
 * @param <K> the key type used to locate an entity
 */
public interface GenericDao<E, K> {

    /**
     * Returns a list of all entities.
     *
     * @return a list of objects of type E
     */
    List<E> list(K key);

    /**
     * Finds a single entity using the provided key.
     *
     * @param key the unique identifier used to locate the entity
     * @return the entity of type E if found, or null otherwise
     */
    E find(K key);
}
