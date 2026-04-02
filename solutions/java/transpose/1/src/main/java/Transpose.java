import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Transpose {
    public String transpose(final String toTranspose) {
        final String[] inputs = toTranspose.split("\n");
        final int longest = Arrays.stream(inputs).mapToInt(String::length).max().orElse(0);

        final List<String> result = new ArrayList<>();
        for (int i = 0; i < longest; i++) {
            int lastIndex = -1;
            for (int j = inputs.length - 1; j >= 0; j--) {
                if (i < inputs[j].length()) {
                    lastIndex = j;
                    break;
                }
            }

            final StringBuilder row = new StringBuilder();
            for (int j = 0; j <= lastIndex; j++) {
                final String s = inputs[j];
                row.append(i < s.length() ? s.charAt(i) : " ");
            }
            result.add(row.toString());
        }

        return String.join("\n", result);
    }
}
