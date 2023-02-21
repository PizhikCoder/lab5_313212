package org.example.interfaces;

import org.example.core.models.MusicBand;

/**
 * Contains logic-declaration for loading models.
 */
public interface IDataLoader {
    /**
     * loads data
     * @param clazz data-class
     * @return
     * @param <T>
     */
    <T> T load(Class<T> clazz);
}
