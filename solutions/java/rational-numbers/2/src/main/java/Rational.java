import java.util.Objects;

class Rational {
    private final int numerator;
    private final int denominator;

    Rational(final int numerator, final int denominator) {
        if (denominator == 0) {
            throw new ArithmeticException("Denominator cannot be zero");
        }

        final int gcd = gcd(numerator, denominator);
        final int reducedNumerator = numerator / gcd;
        final int reducedDenominator = denominator / gcd;
        if (reducedDenominator < 0) {
            this.numerator = -reducedNumerator;
            this.denominator = -reducedDenominator;
        } else {
            this.numerator = reducedNumerator;
            this.denominator = reducedDenominator;
        }
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            final int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    int getNumerator() {
        return numerator;
    }

    int getDenominator() {
        return denominator;
    }

    Rational add(final Rational other) {
        final int newNumerator =
                (this.getNumerator() * other.getDenominator())
                        + (other.getNumerator() * this.getDenominator());
        final int newDenominator = this.getDenominator() * other.getDenominator();
        return new Rational(newNumerator, newDenominator);
    }

    Rational subtract(final Rational other) {
        final int newNumerator =
                (this.getNumerator() * other.getDenominator())
                        - (other.getNumerator() * this.getDenominator());
        final int newDenominator = this.getDenominator() * other.getDenominator();
        return new Rational(newNumerator, newDenominator);
    }

    Rational multiply(final Rational other) {
        final int newNumerator = this.getNumerator() * other.getNumerator();
        final int newDenominator = this.getDenominator() * other.getDenominator();
        return new Rational(newNumerator, newDenominator);
    }

    Rational divide(final Rational other) {
        if (other.getNumerator() == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        final int newNumerator = this.getNumerator() * other.getDenominator();
        final int newDenominator = other.getNumerator() * this.getDenominator();
        return new Rational(newNumerator, newDenominator);
    }

    Rational abs() {
        return new Rational(Math.abs(this.getNumerator()), Math.abs(this.getDenominator()));
    }

    Rational pow(final int power) {
        if (power >= 0) {
            return new Rational(
                    Math.powExact(this.getNumerator(), power),
                    Math.powExact(this.getDenominator(), power));
        } else {
            return new Rational(
                    Math.powExact(this.getDenominator(), -power),
                    Math.powExact(this.getNumerator(), -power));
        }
    }

    double exp(final double exponent) {
        return Math.pow(exponent, (double) this.getNumerator() / this.getDenominator());
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
