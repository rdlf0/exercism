import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

class Flattener {
    List<Object> flatten(final List<?> list) {
        return list.stream()
                .flatMap(
                        i ->
                                i instanceof final List<?> sublist
                                        ? this.flatten(sublist).stream()
                                        : Stream.of(i))
                .filter(Objects::nonNull)
                .toList();
    }
}
