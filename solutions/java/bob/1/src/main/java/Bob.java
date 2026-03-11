import java.util.List;
import java.util.stream.Collectors;

public class Bob {

    private static final String RESPONSE_QUESTION = "Sure.";
    private static final String RESPONSE_YELLING = "Whoa, chill out!";
    private static final String RESPONSE_YELLING_QUESTION = "Calm down, I know what I'm doing!";
    private static final String RESPONSE_NOT_SAYING_ANYTHING = "Fine. Be that way!";
    private static final String RESPONSE_DEFAULT = "Whatever.";

    private String call;

    public String hey(String call) {
        this.call = call.trim();

        if (this.isNotSayingAnything()) return RESPONSE_NOT_SAYING_ANYTHING;
        if (this.isYellingAQuestion()) return RESPONSE_YELLING_QUESTION;
        if (this.isQuestion()) return RESPONSE_QUESTION;
        if (this.isYelling()) return RESPONSE_YELLING;

        return RESPONSE_DEFAULT;
    }

    private boolean isQuestion() {
        return this.call.charAt(this.call.length() - 1) == '?';
    }

    private boolean isYelling() {
        List<Integer> s = this.call.chars()
                .boxed()
                .filter(Character::isLetter)
                .collect(Collectors.toList());

        return s.size() > 0 && s.stream().allMatch(Character::isUpperCase);
    }

    private boolean isYellingAQuestion() {
        return this.isQuestion() && this.isYelling();
    }

    private boolean isNotSayingAnything() {
        return this.call.matches("^(\\s)*|(\\n\\r)*|(\\t)*$");
    }

}
