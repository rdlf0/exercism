import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

class Tournament {
    private static final String FORMAT_HEADER = "%-30s | %2s | %2s | %2s | %2s | %2s\n";
    private static final String FORMAT_TEAM = "%-30s | %2d | %2d | %2d | %2d | %2d\n";
    private static final String RESULT_LINE_SEPARATOR = "\n";
    private static final String RESULT_PART_DELIMITER = ";";
    private static final Comparator<Team> TEAM_COMPARATOR =
            Comparator.comparingInt(Team::getPoints).reversed().thenComparing(Team::getName);

    private final Map<String, Team> teamsByName = new HashMap<>();

    String printTable() {
        final StringBuilder result = new StringBuilder();
        result.append(FORMAT_HEADER.formatted("Team", "MP", "W", "D", "L", "P"));

        final SortedSet<Team> teams = new TreeSet<>(TEAM_COMPARATOR);
        teams.addAll(teamsByName.values());

        for (final Team team : teams) {
            result.append(
                    FORMAT_TEAM.formatted(
                            team.getName(),
                            team.getMatchesPlayed(),
                            team.getMatchesWon(),
                            team.getMatchesDraw(),
                            team.getMatchesLost(),
                            team.getPoints()));
        }

        return result.toString();
    }

    void applyResults(final String resultString) {
        final String[] results = resultString.split(RESULT_LINE_SEPARATOR);
        for (final String result : results) {
            final String[] parts = result.split(RESULT_PART_DELIMITER);

            final String hostName = parts[0];
            final Team hostTeam = teamsByName.getOrDefault(hostName, new Team(hostName));
            final ResultType hostResult = ResultType.TYPES_BY_NAME.get(parts[2]);
            hostTeam.addResult(hostResult);
            teamsByName.put(hostName, hostTeam);

            final String guestName = parts[1];
            final ResultType guestResult =
                    switch (hostResult) {
                        case ResultType.WIN -> ResultType.LOSS;
                        case ResultType.LOSS -> ResultType.WIN;
                        default -> ResultType.DRAW;
                    };
            final Team guestTeam = teamsByName.getOrDefault(guestName, new Team(guestName));
            guestTeam.addResult(guestResult);
            teamsByName.put(guestName, guestTeam);
        }
    }
}
