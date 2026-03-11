class CustomCheckedException extends Exception {
    private final String message;

    CustomCheckedException() {
        this.message = super.getMessage();
    }

    CustomCheckedException(final String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
