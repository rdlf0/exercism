class ArmstrongNumbers {

	boolean isArmstrongNumber(int numberToCheck) {
		if (numberToCheck < 10) return true;
		if (numberToCheck < 153) return false;

		int len = String.valueOf(numberToCheck).length();
		int result = 0;
		int input = numberToCheck;

		while (input > 0) {
			result += Math.pow(input % 10, len);
			input /= 10;
		}

		return numberToCheck == result;
	}

}
