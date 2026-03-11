class Robot {

    private GridPosition gridPosition;
    private Orientation orientation;

    Robot(final GridPosition gridPosition, final Orientation orientation) {
        this.gridPosition = gridPosition;
        this.orientation = orientation;
    }

    GridPosition getGridPosition() {
        return gridPosition;
    }

    Orientation getOrientation() {
        return orientation;
    }

    void turnRight() {
        final Orientation[] orientations = Orientation.values();
        orientation = orientations[(orientation.ordinal() + 1) % orientations.length];
    }

    void turnLeft() {
        final Orientation[] orientations = Orientation.values();
        orientation = orientations[(orientation.ordinal() + 3) % orientations.length];
    }

    void advance() {
        switch (orientation) {
            case NORTH:
                gridPosition = new GridPosition(gridPosition.x, gridPosition.y + 1);
                break;
            case SOUTH:
                gridPosition = new GridPosition(gridPosition.x, gridPosition.y - 1);
                break;
            case EAST:
                gridPosition = new GridPosition(gridPosition.x + 1, gridPosition.y);
                break;
            case WEST:
                gridPosition = new GridPosition(gridPosition.x - 1, gridPosition.y);
        }
    }

    void simulate(final String path) {
        for (final char ch : path.toCharArray()) {
            switch (ch) {
                case 'R':
                    this.turnRight();
                    break;
                case 'L':
                    this.turnLeft();
                    break;
                case 'A':
                    this.advance();
            }
        }
    }

}
