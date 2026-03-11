public class GameMaster {
    private static final String DESCRIPTION_CHARACTER = "You're a level %d %s with %d hit points.";
    private static final String DESCRIPTION_DESTINATION =
            "You've arrived at %s, which has %d inhabitants.";
    private static final String DESCRIPTION_TRAVEL_METHOD =
            "You're traveling to your destination %s.";

    public String describe(final Character character) {
        return DESCRIPTION_CHARACTER.formatted(
                character.getLevel(), character.getCharacterClass(), character.getHitPoints());
    }

    public String describe(final Destination destination) {
        return DESCRIPTION_DESTINATION.formatted(
                destination.getName(), destination.getInhabitants());
    }

    public String describe(final TravelMethod travelMethod) {
        return DESCRIPTION_TRAVEL_METHOD.formatted(travelMethod.getAsPartOfSentence());
    }

    public String describe(
            final Character character,
            final Destination destination,
            final TravelMethod travelMethod) {
        return "%s %s %s"
                .formatted(
                        this.describe(character),
                        this.describe(travelMethod),
                        this.describe(destination));
    }

    public String describe(final Character character, final Destination destination) {
        return this.describe(character, destination, TravelMethod.WALKING);
    }
}
