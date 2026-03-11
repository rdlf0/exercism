import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Ledger {
    private static final String PATTERN_HEADER = "%-10s | %-25s | %-13s";
    private static final String PATTERN_ENTRY = "%-10s | %-25s | %13s";
    private static final Integer DESCRIPTION_MAX_LENGTH = 22;
    private static final Locale LOCALE_NL = Locale.of("nl", "NL");

    private static final Map<Locale, String[]> HEADINGS_BY_LOCALE =
            Map.of(
                    Locale.US,
                    new String[] {"Date", "Description", "Change"},
                    LOCALE_NL,
                    new String[] {"Datum", "Omschrijving", "Verandering"});

    private static final Map<Locale, DateTimeFormatter> DATE_FORMATS_BY_LOCALE =
            Map.of(
                    Locale.US,
                    DateTimeFormatter.ofPattern("MM/dd/yyyy"),
                    LOCALE_NL,
                    DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    private static final Map<Locale, String> DECIMAL_PATTERN_BY_LOCALE =
            Map.of(Locale.US, "¤#,##0.00 ;(¤#,##0.00)", LOCALE_NL, "¤ #,##0.00 ;¤ -#,##0.00 ");

    private static String formatDate(final Locale locale, final LocalDate localDate) {
        return localDate.format(DATE_FORMATS_BY_LOCALE.get(locale));
    }

    private static String truncateDescription(final String description) {
        if (description.length() > DESCRIPTION_MAX_LENGTH) {
            return "%s...".formatted(description.substring(0, DESCRIPTION_MAX_LENGTH));
        }
        return description;
    }

    private static String formatMoney(
            final Locale locale, final Currency currency, final double amount) {
        final NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);

        if (numberFormat instanceof final DecimalFormat decimalFormat) {
            final DecimalFormatSymbols symbols = decimalFormat.getDecimalFormatSymbols();
            symbols.setCurrencySymbol(currency.getSymbol());

            decimalFormat.setDecimalFormatSymbols(symbols);
            decimalFormat.applyPattern(DECIMAL_PATTERN_BY_LOCALE.get(locale));
            return decimalFormat.format(amount);
        }

        return numberFormat.format(amount);
    }

    public LedgerEntry createLedgerEntry(
            final String date, final String description, final int change) {
        return new LedgerEntry(LocalDate.parse(date), description, change / 100.00);
    }

    public String format(
            final String currencyString, final String localeString, final LedgerEntry[] entries) {
        final Locale locale = Locale.forLanguageTag(localeString);
        final Currency currency = Currency.getInstance(currencyString);

        Arrays.sort(
                entries,
                Comparator.comparing(LedgerEntry::localDate)
                        .thenComparing(LedgerEntry::description)
                        .thenComparing(LedgerEntry::change));

        final List<String> result = new ArrayList<>();
        result.add(PATTERN_HEADER.formatted((Object[]) HEADINGS_BY_LOCALE.get(locale)));
        for (final LedgerEntry entry : entries) {
            result.add(
                    PATTERN_ENTRY.formatted(
                            formatDate(locale, entry.localDate()),
                            truncateDescription(entry.description()),
                            formatMoney(locale, currency, entry.change())));
        }

        return String.join("\n", result);
    }

    public record LedgerEntry(LocalDate localDate, String description, double change) {}
}
