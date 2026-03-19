import java.util.ArrayList;
import java.util.List;

public final class IntergalacticTransmission {
    private static final int CHUNK_SIZE = 7;

    private IntergalacticTransmission() {}

    public static List<Integer> getTransmitSequence(final List<Integer> message) {
        final List<Integer> result = new ArrayList<>();
        int buffer = 0;
        int bitsInBuffer = 0;
        for (final int transmission : message) {
            buffer = (buffer << Byte.SIZE) | (transmission & 0xFF);
            bitsInBuffer += Byte.SIZE;

            while (bitsInBuffer >= CHUNK_SIZE) {
                final int shift = bitsInBuffer - CHUNK_SIZE;
                final int chunk = (buffer >> shift) & 0x7F;
                result.add(applyParity(chunk));

                bitsInBuffer -= CHUNK_SIZE;
                buffer &= (1 << bitsInBuffer) - 1;
            }
        }

        if (bitsInBuffer > 0) {
            final int shift = CHUNK_SIZE - bitsInBuffer;
            final int chunk = (buffer << shift) & 0x7F;
            result.add(applyParity(chunk));
        }

        return result;
    }

    private static int applyParity(final int chunk) {
        final int parityBit = Integer.bitCount(chunk) % 2 == 0 ? 0 : 1;
        return (chunk << 1) | parityBit;
    }

    public static List<Integer> decodeSequence(final List<Integer> sequence) {
        final List<Integer> result = new ArrayList<>();
        int buffer = 0;
        int bitsInBuffer = 0;
        for (final int transmission : sequence) {
            final int chunk = (transmission >> 1) & 0x7F;
            final int parityBit = transmission & 0x01;
            final int expectedParityBit = Integer.bitCount(chunk) % 2 == 0 ? 0 : 1;

            if (parityBit != expectedParityBit) {
                throw new IllegalArgumentException("Corrupted data");
            }

            buffer = (buffer << CHUNK_SIZE) | (chunk & 0x7F);
            bitsInBuffer += CHUNK_SIZE;

            while (bitsInBuffer >= Byte.SIZE) {
                final int shift = bitsInBuffer - Byte.SIZE;
                final int originalByte = (buffer >> shift) & 0xFF;
                result.add(originalByte);

                bitsInBuffer -= Byte.SIZE;
                buffer &= (1 << bitsInBuffer) - 1;
            }
        }

        return result;
    }
}
