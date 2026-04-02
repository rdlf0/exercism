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
        return Math.sqrt(Math.pow(this.real, 2) + Math.pow(this.imaginary, 2));
    }

    ComplexNumber add(final ComplexNumber other) {
        if (other == null) {
            throw new IllegalArgumentException();
        }
        return new ComplexNumber(
                this.real + other.getReal(), this.imaginary + other.getImaginary());
    }

    ComplexNumber subtract(final ComplexNumber other) {
        if (other == null) {
            throw new IllegalArgumentException();
        }
        return new ComplexNumber(
                this.real - other.getReal(), this.imaginary - other.getImaginary());
    }

    ComplexNumber multiply(final ComplexNumber other) {
        if (other == null) {
            throw new IllegalArgumentException();
        }
        return new ComplexNumber(
                (this.real * other.getReal()) - (this.imaginary * other.getImaginary()),
                (this.imaginary * other.getReal()) + (this.real * other.getImaginary()));
    }

    ComplexNumber divide(final ComplexNumber other) {
        if (other == null) {
            throw new IllegalArgumentException();
        }
        return this.multiply(other.reciprocal());
    }

    ComplexNumber conjugate() {
        return new ComplexNumber(this.real, this.imaginary * -1);
    }

    private ComplexNumber reciprocal() {
        final double magnitude = Math.pow(this.real, 2) + Math.pow(this.imaginary, 2);
        final ComplexNumber conjugate = this.conjugate();
        return new ComplexNumber(
                conjugate.getReal() / magnitude, conjugate.getImaginary() / magnitude);
    }

    ComplexNumber exponentialOf() {
        final ComplexNumber euler =
                new ComplexNumber(Math.cos(this.imaginary), Math.sin(this.imaginary));
        final double ePowered = Math.pow(Math.E, this.real);
        return new ComplexNumber(ePowered * euler.getReal(), ePowered * euler.getImaginary());
    }
}
