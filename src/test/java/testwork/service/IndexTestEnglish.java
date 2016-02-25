package testwork.service;


import static java.util.Arrays.asList;
import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.Trie;
import org.junit.Before;
import org.junit.Test;

public class IndexTestEnglish {

    private static final String TEST_STRING = "!fd [addfgd] s-b,   fdgfd. \n  zzzD ojgdfgsd. fd";

    private Trie<String, List<Long>> trie;

    @Before
    public void setup() throws IOException {
        BufferedReader reader = new BufferedReader(new StringReader(TEST_STRING));
        FileIndex index = new FileIndex();
        index.createFromFile(reader, FileIndex.DEFAULT_LOCALE);
        trie = index.trie;
    }

    @Test
    public void testCreate() {
        assertNotNull(trie);
        assertEquals(6, trie.size());
    }

    @Test
    public void testGetIndexForWord() {
        assertEquals(new ArrayList<>(asList(20L)), trie.get("fdgfd"));
        assertEquals(new ArrayList<>(asList(1L, 44L)), trie.get("fd"));
    }
}
