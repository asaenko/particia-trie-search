package testwork.index;

import java.util.Locale;

import testwork.exception.SourceNotFoundException;

public interface LocaleIndex extends Index {

    /**
     * Create index from source, use Locale for correct words dividing
     * @param sourceName
     * @param locale
     * @throws SourceNotFoundException
     */
    void create(String sourceName, Locale locale) throws SourceNotFoundException;
}
