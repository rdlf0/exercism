class IsbnVerifier {

    private static final byte LENGTH = 10;

    boolean isValid(String stringToVerify) {
        String sanitized = stringToVerify.replace("-", "");

        if (!sanitized.matches("^\\d{9}[\\dX]$")) return false;

        int checksum = 0;
        for (int i = 0; i < LENGTH; i++) {
            char ch = sanitized.charAt(i);
            int digit = ch == 'X' ? 10 : Character.getNumericValue(ch);
            checksum += digit * (LENGTH - i);
        }

        return checksum % 11 == 0;
    }

}
