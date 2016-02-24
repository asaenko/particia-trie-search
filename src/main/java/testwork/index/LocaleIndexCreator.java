package testwork.index;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;

import testwork.exception.ResourceNotFoundException;

public interface LocaleIndexCreator extends IndexCreator {

    void create(String resourceName, Locale locale) throws ResourceNotFoundException;
}
