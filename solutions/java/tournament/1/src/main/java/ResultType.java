import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

enum ResultType {
    WIN,
    LOSS,
    DRAW,
    ;

    static final Map<String, ResultType> TYPES_BY_NAME =
            Arrays.stream(ResultType.values())
                    .collect(Collectors.toMap(e -> e.name().toLowerCase(), Function.identity()));
}
