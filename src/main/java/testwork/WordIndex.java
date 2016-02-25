package testwork;

import java.util.Locale;
import java.util.Set;

import testwork.exception.SourceNotFoundException;
import testwork.index.LocaleIndex;
import testwork.service.FileIndex;

public class WordIndex {

    private LocaleIndex fileLocaleIndex = new FileIndex();

    public Set<Integer> getIndexesForWord(String word) {
        return fileLocaleIndex.findWordPositions(word);
    }

    public void readIndex(String filename, Locale locale) throws SourceNotFoundException {
        fileLocaleIndex.create(filename, locale);
    }

    public void readIndex(String filename) throws SourceNotFoundException {
        fileLocaleIndex.create(filename);
    }

}
