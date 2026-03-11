public enum State {
    READY("ready"),
    RUNNING("running"),
    STOPPED("stopped"),
    ;

    private final String nameString;

    State(final String nameString) {
        this.nameString = nameString;
    }

    @Override
    public String toString() {
        return nameString;
    }
}
