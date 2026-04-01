import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class CircularBuffer<T> {
    private final BlockingQueue<T> buffer;

    CircularBuffer(final int size) {
        this.buffer = new ArrayBlockingQueue<>(size);
    }

    T read() throws BufferIOException {
        if (buffer.isEmpty()) {
            throw new BufferIOException("Tried to read from empty buffer");
        }
        return buffer.poll();
    }

    void write(final T data) throws BufferIOException {
        if (!buffer.offer(data)) {
            throw new BufferIOException("Tried to write to full buffer");
        }
    }

    void overwrite(final T data) {
        while (!buffer.offer(data)) {
            buffer.poll();
        }
    }

    void clear() {
        buffer.clear();
    }
}
