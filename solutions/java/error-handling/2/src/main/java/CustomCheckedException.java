class CustomCheckedException extends Exception {
    CustomCheckedException() {
        super();
    }

    CustomCheckedException(final String message) {
        super(message);
    }
}
