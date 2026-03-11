class CustomUncheckedException extends RuntimeException {
    CustomUncheckedException() {
        super();
    }

    CustomUncheckedException(final String message) {
        super(message);
    }
}
