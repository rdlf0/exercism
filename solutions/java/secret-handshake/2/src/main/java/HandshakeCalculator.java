import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class HandshakeCalculator {

    List<Signal> calculateHandshake(int number) {
        final List<Signal> result = new ArrayList<>();

        for (final Signal s : Signal.values()) {
            if ((number & 1) == 1) {
                result.add(s);
            }
            number >>= 1;
        }

        if (number > 0) {
            Collections.reverse(result);
        }

        return result;
    }

}
