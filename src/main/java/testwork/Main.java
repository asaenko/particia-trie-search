package testwork;

import org.apache.commons.lang3.LocaleUtils;

public class Main {

    public static void main(String[] args) {
        System.out.println("Arguments: \n 1) argument filename, 2) - word for position search (optional), 3) Locale (optional), default Locale.ENGLISH");
        if (args.length > 0 && !args[0].isEmpty()) {
            WordIndex index = new WordIndex();

            if (args.length > 2 && !args[2].isEmpty()) {
                index.readIndex(args[0], LocaleUtils.toLocale(args[2]));
            } else {
                index.readIndex(args[0]);
            }

            if (args.length > 1 && !args[1].isEmpty()) {
                index.getIndexesForWord(args[1]);
            }
        } else {
            System.out.println("You must provide filename for dictionary indexation as first filename parameter");
        }
    }
}
