class RomanNumeral {

    private final int number;

    RomanNumeral(final int number) {
        this.number = number;
    }

    public String getRomanNumeral() {
        return getRoman(number);
    }

    private String getRoman(final int n) {
        if (n >= 1000) return "M" + getRoman(n - 1000);
        if (n >= 900) return "CM" + getRoman(n - 900);
        if (n >= 500) return "D" + getRoman(n - 500);
        if (n >= 400) return "CD" + getRoman(n - 400);
        if (n >= 100) return "C" + getRoman(n - 100);
        if (n >= 90) return "XC" + getRoman(n - 90);
        if (n >= 50) return "L" + getRoman(n - 50);
        if (n >= 40) return "XL" + getRoman(n - 40);
        if (n >= 10) return "X" + getRoman(n - 10);
        if (n >= 9) return "IX" + getRoman(n - 9);
        if (n >= 5) return "V" + getRoman(n - 5);
        if (n >= 4) return "IV" + getRoman(n - 4);
        if (n >= 1) return "I" + getRoman(n - 1);
        
        return "";
    }
}
