import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

class School {
    private final SortedMap<Integer, SortedSet<String>> studentsByGrades = new TreeMap<>();
    private final Set<String> allStudents = new HashSet<>();

    boolean add(final String student, final int grade) {
        return allStudents.add(student)
                && studentsByGrades.computeIfAbsent(grade, _ -> new TreeSet<>()).add(student);
    }

    List<String> roster() {
        return studentsByGrades.entrySet().stream().flatMap(e -> e.getValue().stream()).toList();
    }

    List<String> grade(final int grade) {
        return new ArrayList<>(studentsByGrades.getOrDefault(grade, new TreeSet<>()));
    }
}
