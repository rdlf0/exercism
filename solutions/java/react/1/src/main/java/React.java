import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

public final class React {

    private React() {}

    public static <T> InputCell<T> inputCell(final T initialValue) {
        return new InputCell<>(initialValue);
    }

    public static <T> ComputeCell<T> computeCell(
            final Function<List<T>, T> function, final List<Cell<T>> cells) {
        return new ComputeCell<>(function, cells);
    }

    public static class Cell<T> {
        final Set<ComputeCell<T>> observers = new HashSet<>();
        protected T value;

        public T getValue() {
            return value;
        }

        public void addObserver(final ComputeCell<T> observer) {
            observers.add(observer);
            observer.updateValue();
        }
    }

    public static class InputCell<T> extends Cell<T> {
        public InputCell(final T value) {
            this.value = value;
        }

        public void setValue(final T newValue) {
            if (newValue.equals(this.getValue())) {
                return;
            }
            this.value = newValue;
            observers.forEach(ComputeCell::updateValue);
        }
    }

    public static class ComputeCell<T> extends Cell<T> {
        private final Function<List<T>, T> reducer;
        private final List<Cell<T>> observables;
        private final Set<Consumer<T>> callbacks = new HashSet<>();

        public ComputeCell(final Function<List<T>, T> reducer, final List<Cell<T>> observables) {
            this.reducer = reducer;
            this.observables = observables;
            observables.forEach(o -> o.addObserver(this));
        }

        @Override
        public T getValue() {
            final List<T> observableValues = observables.stream().map(Cell::getValue).toList();
            return reducer.apply(observableValues);
        }

        public void updateValue() {
            final T newValue = this.getValue();
            if (newValue.equals(this.value)) {
                return;
            }

            this.value = newValue;
            observers.forEach(ComputeCell::updateValue);
            callbacks.forEach(c -> c.accept(this.value));
        }

        public void addCallback(final Consumer<T> callback) {
            this.callbacks.add(callback);
        }

        public void removeCallback(final Consumer<T> callback) {
            this.callbacks.remove(callback);
        }
    }
}
