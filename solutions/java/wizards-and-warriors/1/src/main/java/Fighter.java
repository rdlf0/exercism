class Fighter {
    protected static final String DESCRIPTION = "Fighter is a %s";

    boolean isVulnerable() {
        return true;
    }

    int getDamagePoints(final Fighter fighter) {
        if (fighter == null) {
            throw new IllegalArgumentException("Fighter can't be null");
        }
        return 1;
    }

    @Override
    public String toString() {
        return DESCRIPTION.formatted(this.getClass().getSimpleName());
    }
}

class Warrior extends Fighter {
    private static final int POINTS_ATTACKING_VULNERABLE = 10;
    private static final int POINTS_ATTACKING_INVULNERABLE = 6;

    @Override
    boolean isVulnerable() {
        return false;
    }

    @Override
    int getDamagePoints(final Fighter fighter) {
        super.getDamagePoints(fighter);
        return fighter.isVulnerable() ? POINTS_ATTACKING_VULNERABLE : POINTS_ATTACKING_INVULNERABLE;
    }

    @Override
    public String toString() {
        // Tests are stupid
        return super.toString();
    }
}

class Wizard extends Fighter {
    private static final int POINTS_WHEN_VULNERABLE = 3;
    private static final int POINTS_WHEN_INVULNERABLE = 12;

    private boolean spellPrepared = false;

    public void prepareSpell() {
        spellPrepared = true;
    }

    @Override
    boolean isVulnerable() {
        return !spellPrepared;
    }

    @Override
    int getDamagePoints(final Fighter fighter) {
        super.getDamagePoints(fighter);
        return this.isVulnerable() ? POINTS_WHEN_VULNERABLE : POINTS_WHEN_INVULNERABLE;
    }

    @Override
    public String toString() {
        // Tests are stupid
        return super.toString();
    }
}
