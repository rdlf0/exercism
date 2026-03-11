class CustomUncheckedException extends RuntimeException {
    private final String message;

    CustomUncheckedException() {
        this.message = super.getMessage();
    }

    CustomUncheckedException(final String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
