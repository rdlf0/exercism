import java.util.Optional;

class Badge {
    public String print(final Integer id, final String name, final String department) {
        final String formattedId = Optional.ofNullable(id).map("[%d] - "::formatted).orElse("");
        final String formattedDept = Optional.ofNullable(department).orElse("owner").toUpperCase();
        return "%s%s - %s".formatted(formattedId, name, formattedDept);
    }
}
