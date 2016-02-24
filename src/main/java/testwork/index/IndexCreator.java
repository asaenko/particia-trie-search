package testwork.index;

import java.util.List;

import org.apache.commons.collections4.Trie;

import testwork.exception.ResourceNotFoundException;

/**
 *
 */
public interface IndexCreator {

    /**
     *
     * @param resourceName
     */
    void create(String resourceName) throws ResourceNotFoundException;

    /**
     *
     * @return
     */
    List<Long> findWordPositions(String word);
}
