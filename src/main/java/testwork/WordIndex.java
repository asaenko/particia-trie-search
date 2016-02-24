package testwork;

import java.util.List;
import java.util.Locale;

import testwork.exception.ResourceNotFoundException;
import testwork.index.LocaleIndexCreator;
import testwork.service.FileIndexCreator;

public class WordIndex {

    private LocaleIndexCreator fileLocaleIndexCreator = new FileIndexCreator();

    public List<Long> getIndexesForWord(String word) {
        return fileLocaleIndexCreator.findWordPositions(word);
    }

    public void readIndex(String filename, Locale locale) throws ResourceNotFoundException {
        fileLocaleIndexCreator.create(filename, locale);
    }

    public void readIndex(String filename) throws ResourceNotFoundException {
        fileLocaleIndexCreator.create(filename);
    }

}
