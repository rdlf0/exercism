class Team {
    private final String name;
    private int matchesPlayed = 0;
    private int matchesWon = 0;
    private int matchesLost = 0;
    private int matchesDraw = 0;
    private int points;

    public Team(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public int getMatchesWon() {
        return matchesWon;
    }

    public int getMatchesLost() {
        return matchesLost;
    }

    public int getMatchesDraw() {
        return matchesDraw;
    }

    public int getPoints() {
        return points;
    }

    public void addResult(final ResultType resultType) {
        matchesPlayed++;
        switch (resultType) {
            case ResultType.WIN -> matchesWon++;
            case ResultType.LOSS -> matchesLost++;
            case ResultType.DRAW -> matchesDraw++;
        }

        points = matchesWon * 3 + matchesDraw;
    }
}
