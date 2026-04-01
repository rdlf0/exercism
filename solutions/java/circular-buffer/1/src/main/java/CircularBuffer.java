import java.util.LinkedHashSet;

class CircularBuffer<T> {
    private final int size;
    private final LinkedHashSet<T> buffer;

    CircularBuffer(final int size) {
        this.size = size;
        this.buffer = LinkedHashSet.newLinkedHashSet(size);
    }

    T read() throws BufferIOException {
        if (buffer.isEmpty()) {
            throw new BufferIOException("Tried to read from empty buffer");
        }
        return buffer.removeFirst();
    }

    void write(final T data) throws BufferIOException {
        if (buffer.size() == size) {
            throw new BufferIOException("Tried to write to full buffer");
        }
        buffer.add(data);
    }

    void overwrite(final T data) {
        if (buffer.size() == size) {
            buffer.removeFirst();
        }
        buffer.add(data);
    }

    void clear() {
        buffer.clear();
    }
}
