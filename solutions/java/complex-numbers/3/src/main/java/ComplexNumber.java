class ComplexNumber {
    private final double real;
    private final double imaginary;

    ComplexNumber(final double real, final double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    double getReal() {
        return real;
    }

    double getImaginary() {
        return imaginary;
    }

    double abs() {
        return Math.sqrt(Math.pow(real, 2) + Math.pow(imaginary, 2));
    }

    ComplexNumber add(final ComplexNumber other) {
        if (other == null) {
            throw new IllegalArgumentException();
        }
        return new ComplexNumber(real + other.getReal(), imaginary + other.getImaginary());
    }

    ComplexNumber subtract(final ComplexNumber other) {
        if (other == null) {
            throw new IllegalArgumentException();
        }
        return new ComplexNumber(real - other.getReal(), imaginary - other.getImaginary());
    }

    ComplexNumber multiply(final ComplexNumber other) {
        if (other == null) {
            throw new IllegalArgumentException();
        }
        return new ComplexNumber(
                (real * other.getReal()) - (imaginary * other.getImaginary()),
                (imaginary * other.getReal()) + (real * other.getImaginary()));
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
        return new ComplexNumber(
                conjugate.getReal() / magnitude, conjugate.getImaginary() / magnitude);
    }

    ComplexNumber exponentialOf() {
        final double ePowered = Math.pow(Math.E, real);
        return new ComplexNumber(ePowered * Math.cos(imaginary), ePowered * Math.sin(imaginary));
    }
}
