package testwork.index;

import java.util.Set;

import testwork.exception.SourceNotFoundException;

/**
 * Working with resource, can create index from source and search by it
 */
public interface Index {

    /**
     * Create index from source
     * @param sourceName
     */
    void create(String sourceName) throws SourceNotFoundException;

    /**
     * Find all word positions
     * @param word
     * @return
     */
    Set<Integer> findWordPositions(String word);
}
