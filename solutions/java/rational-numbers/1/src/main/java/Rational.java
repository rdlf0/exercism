import java.math.BigInteger;
import java.util.Objects;

class Rational {
    private final int numerator;
    private final int denominator;

    Rational(final int numerator, final int denominator) {
        final int gcd = gcd(numerator, denominator);
        final int reducedNumerator = numerator / gcd;
        final int reducedDenominator = denominator / gcd;
        if (reducedDenominator < 0) {
            this.numerator = reducedNumerator * -1;
            this.denominator = reducedDenominator * -1;
        } else {
            this.numerator = reducedNumerator;
            this.denominator = reducedDenominator;
        }
    }

    private static int gcd(final int a, final int b) {
        final BigInteger bigA = BigInteger.valueOf(a);
        final BigInteger bigB = BigInteger.valueOf(b);
        return bigA.gcd(bigB).intValue();
    }

    private static Rational reduce(final int numerator, final int denominator) {
        final int gcd = gcd(numerator, denominator);
        return new Rational(numerator / gcd, denominator / gcd);
    }

    private static Rational standardize(final Rational rational) {
        if (rational.denominator < 0) {
            return new Rational(rational.numerator * -1, rational.denominator * -1);
        } else {
            return new Rational(rational.numerator, rational.denominator);
        }
    }

    int getNumerator() {
        return numerator;
    }

    int getDenominator() {
        return denominator;
    }

    Rational add(final Rational other) {
        final int newNumerator =
                (this.numerator * other.denominator) + (other.numerator * this.denominator);
        final int newDenominator = this.denominator * other.denominator;
        return standardize(reduce(newNumerator, newDenominator));
    }

    Rational subtract(final Rational other) {
        final int newNumerator =
                (this.numerator * other.denominator) - (other.numerator * this.denominator);
        final int newDenominator = this.denominator * other.denominator;
        return standardize(reduce(newNumerator, newDenominator));
    }

    Rational multiply(final Rational other) {
        final int newNumerator = this.numerator * other.numerator;
        final int newDenominator = this.denominator * other.denominator;
        return standardize(reduce(newNumerator, newDenominator));
    }

    Rational divide(final Rational other) {
        final int newNumerator = this.numerator * other.denominator;
        final int newDenominator = other.numerator * this.denominator;
        return standardize(reduce(newNumerator, newDenominator));
    }

    Rational abs() {
        return reduce(Math.abs(numerator), Math.abs(denominator));
    }

    Rational pow(final int power) {
        if (power > 0) {
            return standardize(
                    reduce(Math.powExact(numerator, power), Math.powExact(denominator, power)));
        } else if (power < 0) {
            final int absPower = Math.abs(power);
            return standardize(
                    reduce(
                            Math.powExact(denominator, absPower),
                            Math.powExact(numerator, absPower)));
        } else {
            return new Rational(1, 1);
        }
    }

    double exp(final double exponent) {
        return Math.pow(exponent, (double) numerator / denominator);
    }

    @Override
    public String toString() {
        return String.format("%d/%d", this.getNumerator(), this.getDenominator());
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof final Rational other) {
            return this.getNumerator() == other.getNumerator()
                    && this.getDenominator() == other.getDenominator();
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getNumerator(), this.getDenominator());
    }
}
