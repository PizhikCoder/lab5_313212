package org.example.interfaces;
/**
 * Contains logic-declaration for saving models.
 */
public interface IDataSaver {
    /**
     *
     * @param data models to save.
     * @return
     * @param <T>
     */
    <T> boolean save(T data);
}
