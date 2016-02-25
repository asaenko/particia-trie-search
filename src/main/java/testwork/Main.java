package testwork;

import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.LocaleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import testwork.exception.ResourceNotFoundException;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        System.out.println("Arguments: \n 1) argument filename, 2) - word for position search (optional), 3) Locale (optional), default Locale.ENGLISH");
        String filename;
        Locale locale;
        if (args.length > 0 && !args[0].isEmpty()) {
            filename = args[0];
        } else {
            filename = "src/main/resources/example.txt";
        }

        WordIndex index = new WordIndex();
        if (args.length > 2 && !args[2].isEmpty()) {
            locale =  LocaleUtils.toLocale(args[2]);
        } else {
            locale = new Locale("ru", "RU");
        }


        try {
            index.readIndex(filename,locale);
        } catch (ResourceNotFoundException e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        String word;
        if (args.length > 1 && !args[1].isEmpty()) {
            word = args[1];
        } else {
            word = "Присоединяется";
        }
        List<Long> positions = index.getIndexesForWord(word);
        if (positions != null) {
            System.out.println("Word " + word + "  position: " + positions);
        } else {
            System.out.println("Word " + word + "  not found");
        }
    }
}
