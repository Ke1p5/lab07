package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    public enum Month {
        JANUARY("january", 31), FEBRUARY("february", 28), MARCH("march", 31), APRIL("april", 30),
        MAY("may", 31), JUNE("june", 30), JULY("july", 31), AUGUST("august", 31), 
        SEPTEMBER("september", 30), OCTOBER("october", 31), NOVEMBER("november", 30), DECEMBER("december", 31);

        public int getDaysOfTheMonth() {
            return this.daysOfTheMonth;
        }

        public String getActualName() {
            return this.actualName;
        }

        private int daysOfTheMonth;
        private String actualName;

        private Month(String actualName, int days) {
            this.actualName = actualName;
            this.daysOfTheMonth = days;
        }

        public static Month fromString(final String phrase) {
            if (phrase.toLowerCase().equals("j")) {
                    throw new IllegalArgumentException();
            }
            for (Month m: Month.values()) {
                if (phrase.length() > 0 && (m.actualName.contains(phrase.toLowerCase()) || m.actualName.compareTo(phrase.toLowerCase()) == 0)) {
                    System.out.println(m);
                    return m;
                }
            }
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Comparator<String> sortByDays() {
        Comparator<String> comp = new Comparator<>() {
            public int compare(final String s1, final String s2) {
                Month m1 = Month.fromString(s1);
                Month m2 = Month.fromString(s2);
                return Integer.compare(m1.daysOfTheMonth, m2.daysOfTheMonth); 
            }
        };
        return comp;
    }

    @Override
    public Comparator<String> sortByOrder() {
        Comparator<String> comp = new Comparator<>() {
            public int compare(final String s1, final String s2) {
                Month m1 = Month.fromString(s1);
                Month m2 = Month.fromString(s2);
                return Integer.compare(m1.ordinal(), m2.ordinal());
            }
        };
        return comp;
    }
}
