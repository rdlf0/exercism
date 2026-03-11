import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Allergies {

    private final int score;

    Allergies(final int score) {
        this.score = score;
    }

    boolean isAllergicTo(Allergen allergen) {
        return (allergen.getScore() & score) > 0;
    }

    List<Allergen> getList() {
        return Arrays.stream(Allergen.values())
                .filter(this::isAllergicTo)
                .collect(Collectors.toList());
    }

}
