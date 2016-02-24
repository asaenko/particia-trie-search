package testwork.service;

import static java.util.Arrays.asList;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.collections4.Trie;
import org.apache.commons.collections4.trie.PatriciaTrie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import testwork.index.IndexCreator;
import testwork.index.LocaleIndexCreator;

public class FileIndexCreator implements IndexCreator, LocaleIndexCreator {

    public static final Locale DEFAULT_LOCALE = Locale.ENGLISH;

    private static final Logger LOGGER = LoggerFactory.getLogger(FileIndexCreator.class);

    Trie<String, List<Long>> trie = new PatriciaTrie<List<Long>>();

    public void create(String fileName) {
        create(fileName, DEFAULT_LOCALE);
    }

    public void create(String fileName, Locale locale) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            createFromFile(reader, locale);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void createFromFile(BufferedReader reader, Locale locale) throws IOException {
        BreakIterator breakIterator = BreakIterator.getWordInstance(locale);
        String currentLine;
        Long pos = 0L;
        int lastIndex;
        int firstIndex;
        while ((currentLine = reader.readLine()) != null) {
            breakIterator.setText(currentLine);
            lastIndex = breakIterator.first();
            while (lastIndex != BreakIterator.DONE) {
                firstIndex = lastIndex;
                lastIndex = breakIterator.next();
                if (lastIndex != BreakIterator.DONE && Character.isLetterOrDigit(currentLine.charAt(firstIndex))) {
                    String currentWord = currentLine.substring(firstIndex, lastIndex);
                    putWordtoTrie(currentWord, pos+firstIndex);
                }
            }
            pos += currentLine.getBytes().length;
        }
    }

    public Trie<String, List<Long>> getTrie() {
        return trie;
    }

    private void putWordtoTrie(String key, Long pos) {
        if (isNotBlank(key)) {
            List<Long> index = trie.get(key);
            if (index == null) {
                trie.put(key,  new ArrayList<>(asList(pos)));
            } else {
                index.add(pos);
                trie.put(key, index);
            }
        }
    }
}
