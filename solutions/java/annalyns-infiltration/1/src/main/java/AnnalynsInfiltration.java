import java.math.BigInteger;
import java.util.BitSet;

class AnnalynsInfiltration {
    public static boolean canFastAttack(boolean knightIsAwake) {
        return !knightIsAwake;
    }

    public static boolean canSpy(
            boolean knightIsAwake, boolean archerIsAwake, boolean prisonerIsAwake) {
        return knightIsAwake || archerIsAwake || prisonerIsAwake;
    }

    public static boolean canSignalPrisoner(boolean archerIsAwake, boolean prisonerIsAwake) {
        return !archerIsAwake && prisonerIsAwake;
    }

    public static boolean canFreePrisoner(
            boolean knightIsAwake,
            boolean archerIsAwake,
            boolean prisonerIsAwake,
            boolean petDogIsPresent) {
        final BitSet bitSet = new BitSet(4);
        bitSet.set(0, petDogIsPresent);
        bitSet.set(1, prisonerIsAwake);
        bitSet.set(2, knightIsAwake);
        bitSet.set(3, archerIsAwake);

        if (bitSet.isEmpty()) {
            return false;
        }

        final BigInteger bigInt = new BigInteger(bitSet.toByteArray());

        return bigInt.intValue() < 8 && bigInt.isProbablePrime(100) || bigInt.intValue() == 1;
    }
}
