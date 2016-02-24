package testwork.service;

import static java.util.Arrays.asList;
import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.collections4.Trie;
import org.junit.Before;
import org.junit.Test;

public class IndexTestRussian {

    private static final String TEST_STRING = "!ав [ывапыва] ф-шг,   яавпвапвй. \n  Ёёявыапы ззапрап. ав";

    private static final Locale RUSSIAN_LOCALE = new Locale("ru", "RU");;

    private Trie<String, List<Long>> trie;

    @Before
    public void setup() throws IOException {
        BufferedReader reader = new BufferedReader(new StringReader(TEST_STRING));
        FileIndexCreator indexCreator = new FileIndexCreator();
        indexCreator.createFromFile(reader, RUSSIAN_LOCALE);
        trie = indexCreator.trie;
    }

    @Test
    public void testCreate() {
        assertNotNull(trie);
        assertEquals(6, trie.size());
    }

    @Test
    public void testGetIndexForWord() {
        assertEquals(new ArrayList<>(asList(44L)), trie.get("ззапрап"));
        assertEquals(new ArrayList<>(asList(1L, 53L)), trie.get("ав"));
    }
}
