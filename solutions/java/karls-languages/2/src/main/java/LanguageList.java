import java.util.ArrayList;
import java.util.List;

public class LanguageList {
    private static final String EXCITING_JAVA = "Java";
    private static final String EXCITING_KOTLIN = "Kotlin";

    private final List<String> languages = new ArrayList<>();

    public boolean isEmpty() {
        return languages.isEmpty();
    }

    public void addLanguage(final String language) {
        if (language == null || language.isBlank()) {
            return;
        }
        languages.add(language);
    }

    public void removeLanguage(final String language) {
        if (language == null) {
            return;
        }
        languages.remove(language);
    }

    public String firstLanguage() {
        return languages.getFirst();
    }

    public int count() {
        return languages.size();
    }

    public boolean containsLanguage(final String language) {
        return languages.stream().anyMatch(l -> l.equals(language));
    }

    public boolean isExciting() {
        return languages.stream()
                .anyMatch(l -> l.equals(EXCITING_JAVA) || l.equals(EXCITING_KOTLIN));
    }
}
