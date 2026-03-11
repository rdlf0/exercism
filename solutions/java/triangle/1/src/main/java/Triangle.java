import java.util.HashMap;
import java.util.Map;

class Triangle {
    private int numEqualSides = 0;

    Triangle(final double side1, final double side2, final double side3) throws TriangleException {
        if (side1 == 0 || side2 == 0 || side3 == 0) {
            throw new TriangleException();
        }

        if (side1 + side2 < side3 || side1 + side3 < side2 || side2 + side3 < side1) {
            throw new TriangleException();
        }

        final Map<Double, Integer> timesPerSide = new HashMap<>();
        timesPerSide.put(side1, timesPerSide.getOrDefault(side1, 0) + 1);
        numEqualSides = Math.max(numEqualSides, timesPerSide.get(side1));

        timesPerSide.put(side2, timesPerSide.getOrDefault(side2, 0) + 1);
        numEqualSides = Math.max(numEqualSides, timesPerSide.get(side2));

        timesPerSide.put(side3, timesPerSide.getOrDefault(side3, 0) + 1);
        numEqualSides = Math.max(numEqualSides, timesPerSide.get(side3));
    }

    boolean isEquilateral() {
        return numEqualSides == 3;
    }

    boolean isIsosceles() {
        return numEqualSides >= 2;
    }

    boolean isScalene() {
        return numEqualSides == 1;
    }
}
