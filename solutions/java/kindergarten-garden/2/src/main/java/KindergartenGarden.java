import java.util.Arrays;
import java.util.List;

class KindergartenGarden {

    private final String[] rows;

    KindergartenGarden(final String garden) {
        this.rows = garden.split("\n");
    }

    List<Plant> getPlantsOfStudent(final String student) {
        final int position = Student.valueOf(student.toUpperCase()).ordinal();

        return Arrays.asList(
            Plant.getPlant(rows[0].charAt(position * 2)),
            Plant.getPlant(rows[0].charAt(position * 2 + 1)),
            Plant.getPlant(rows[1].charAt(position * 2)),
            Plant.getPlant(rows[1].charAt(position * 2 + 1)));
    }

}
