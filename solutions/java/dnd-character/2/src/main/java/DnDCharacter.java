import java.util.concurrent.ThreadLocalRandom;

class DnDCharacter {

    private int ability;
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;
    private int hitpoints;

    public DnDCharacter() {
        this.ability = throwDice();
        this.strength = throwDice();
        this.dexterity = throwDice();
        this.constitution = throwDice();
        this.intelligence = throwDice();
        this.wisdom = throwDice();
        this.charisma = throwDice();
        this.hitpoints = this.modifier(this.constitution) + 10;
    }

    private int throwDice() {
        return ThreadLocalRandom.current().nextInt(3, 19);
    }

    int ability() {
        return this.ability;
    }

    int modifier(int input) {
        return (int) Math.floor((input - 10) / 2.0);
    }

    int getStrength() {
        return this.strength;
    }

    int getDexterity() {
        return this.dexterity;
    }

    int getConstitution() {
        return this.constitution;
    }

    int getIntelligence() {
        return this.intelligence;
    }

    int getWisdom() {
        return this.wisdom;
    }

    int getCharisma() {
        return this.charisma;
    }

    int getHitpoints() {
        return this.hitpoints;
    }

}
