record ComplexNumber(double real, double imaginary) {
    double abs() {
        return Math.sqrt(Math.pow(real, 2) + Math.pow(imaginary, 2));
    }

    ComplexNumber add(final ComplexNumber other) {
        if (other == null) {
            throw new IllegalArgumentException();
        }
        return new ComplexNumber(real + other.real(), imaginary + other.imaginary());
    }

    ComplexNumber subtract(final ComplexNumber other) {
        if (other == null) {
            throw new IllegalArgumentException();
        }
        return new ComplexNumber(real - other.real(), imaginary - other.imaginary());
    }

    ComplexNumber multiply(final ComplexNumber other) {
        if (other == null) {
            throw new IllegalArgumentException();
        }
        return new ComplexNumber(
                (real * other.real()) - (imaginary * other.imaginary()),
                (imaginary * other.real()) + (real * other.imaginary()));
    }

    ComplexNumber divide(final ComplexNumber other) {
        if (other == null) {
            throw new IllegalArgumentException();
        }
        return this.multiply(other.reciprocal());
    }

    ComplexNumber conjugate() {
        return new ComplexNumber(real, imaginary * -1);
    }

    private ComplexNumber reciprocal() {
        final double magnitude = Math.pow(real, 2) + Math.pow(imaginary, 2);
        final ComplexNumber conjugate = this.conjugate();
        return new ComplexNumber(conjugate.real() / magnitude, conjugate.imaginary() / magnitude);
    }

    ComplexNumber exponentialOf() {
        final ComplexNumber euler = new ComplexNumber(Math.cos(imaginary), Math.sin(imaginary));
        final double ePowered = Math.pow(Math.E, real);
        return new ComplexNumber(ePowered * euler.real(), ePowered * euler.imaginary());
    }
}
