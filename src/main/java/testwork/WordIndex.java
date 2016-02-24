package testwork;

import java.util.List;
import java.util.Locale;

import testwork.index.IndexCreator;
import testwork.index.LocaleIndexCreator;
import testwork.service.FileIndexCreator;

public class WordIndex {

    private IndexCreator fileIndexCreator = new FileIndexCreator();

    private LocaleIndexCreator fileLocaleIndexCreator = new FileIndexCreator();

    public List<Long> getIndexesForWord(String word) {
        return fileIndexCreator.getTrie().get(word);
    }

    public void readIndex(String filename, Locale locale) {
        fileLocaleIndexCreator.create(filename, locale);
    }

    public void readIndex(String filename) {
        fileIndexCreator.create(filename);
    }

}
