class DnDCharacter {

    private int strength;
    private int constitution;

    private int throwDice() {
        int dice1 = (int) Math.floor(Math.random() * 6) + 1;
        int dice2 = (int) Math.floor(Math.random() * 6) + 1;
        int dice3 = (int) Math.floor(Math.random() * 6) + 1;

        return dice1 + dice2 + dice3;
    }

    int ability() {
        return throwDice();
    }

    int modifier(int input) {
        return (int) Math.floor((input - 10) / 2.0);
    }

    int getStrength() {
        if (this.strength == 0) {
            this.strength = throwDice();
        }

        return this.strength;
    }

    int getDexterity() {
        return throwDice();
    }

    int getConstitution() {
        if (this.constitution == 0) {
            this.constitution = throwDice();
        }

        return this.constitution;
    }

    int getIntelligence() {
        return throwDice();
    }

    int getWisdom() {
        return throwDice();
    }

    int getCharisma() {
        return throwDice();
    }

    int getHitpoints() {
        return this.modifier(this.constitution) + 10;
    }

}
