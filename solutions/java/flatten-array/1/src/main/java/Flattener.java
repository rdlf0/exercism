import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Flattener {

    private static void flatten(final List<?> list, final List<Object> result) {
        list.stream()
                .filter(Objects::nonNull)
                .forEach(
                        o -> {
                            if (o instanceof final List<?> sublist) {
                                flatten(sublist, result);
                                return;
                            }
                            result.add(o);
                        });
    }

    List<Object> flatten(final List<?> list) {
        final List<Object> result = new ArrayList<>();
        flatten(list, result);
        return result;
    }
}
