package testwork;

import java.util.Set;
import java.util.logging.Logger;

import org.apache.commons.lang3.LocaleUtils;

import testwork.exception.ResourceNotFoundException;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getSimpleName());

    public static void main(String[] args) {
        System.out.println("Arguments: \n 1) argument filename, 2) - word for position search (optional), 3) Locale (optional), default Locale.ENGLISH");
        String filename;
        if (args.length > 0 && !args[0].isEmpty()) {
            filename = args[0];
        } else {
            filename = "src/main/resources/example.txt";
        }

        WordIndex index = new WordIndex();

        try {
            if (args.length > 2 && !args[2].isEmpty()) {
                index.readIndex(filename, LocaleUtils.toLocale(args[2]));
            } else {
                index.readIndex(filename);
            }
        } catch (ResourceNotFoundException e) {
            LOGGER.severe(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        String word;
        if (args.length > 1 && !args[1].isEmpty()) {
            word = args[1];
        } else {
            word = "Присоединяется";
        }
        Set<Integer> positions = index.getIndexesForWord(word);
        if (positions != null) {
            System.out.println("Word " + word + "  position: " + positions);
        } else {
            System.out.println("Word " + word + "  not found");
        }
    }
}
