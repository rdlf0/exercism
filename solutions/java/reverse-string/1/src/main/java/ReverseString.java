class ReverseString {

    String reverse(String inputString) {
        int l = 0;
        int r = inputString.length() - 1;
        char[] result = new char[inputString.length()];

        while (l <= r) {
            result[l] = inputString.charAt(r);
            result[r] = inputString.charAt(l);

            l++;
            r--;
        }

        return new String(result);
    }
  
}