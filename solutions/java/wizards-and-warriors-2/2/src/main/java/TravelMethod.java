public enum TravelMethod {
    WALKING("by walking"),
    HORSEBACK("on horseback"),
    ;

    private final String asPartOfSentence;

    TravelMethod(final String asPartOfSentence) {
        this.asPartOfSentence = asPartOfSentence;
    }

    public String getAsPartOfSentence() {
        return asPartOfSentence;
    }
}
