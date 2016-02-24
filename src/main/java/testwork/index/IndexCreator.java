package testwork.index;

import java.util.List;
import java.util.Locale;

import org.apache.commons.collections4.Trie;

public interface IndexCreator {

    void create(String resourceName);

    Trie<String, List<Long>> getTrie();
}
