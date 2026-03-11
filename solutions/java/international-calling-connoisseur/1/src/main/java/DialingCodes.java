import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DialingCodes {
    private final Map<Integer, String> dialingCodes = new HashMap<>();

    public Map<Integer, String> getCodes() {
        return dialingCodes;
    }

    public void setDialingCode(final Integer code, final String country) {
        dialingCodes.put(code, country);
    }

    public String getCountry(final Integer code) {
        return dialingCodes.get(code);
    }

    public void addNewDialingCode(final Integer code, final String country) {
        if (!dialingCodes.containsKey(code) && this.findDialingCode(country) == null) {
            this.setDialingCode(code, country);
        }
    }

    public Integer findDialingCode(final String country) {
        return dialingCodes.keySet().stream()
                .filter(key -> dialingCodes.get(key).equals(country))
                .findFirst()
                .orElse(null);
    }

    public void updateCountryDialingCode(final Integer code, final String country) {
        Optional.ofNullable(this.findDialingCode(country))
                .map(key -> dialingCodes.put(code, dialingCodes.remove(key)));
    }
}
