import static java.util.Collections.unmodifiableList;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/** POJO representing a User in the database. */
public class User {
    private final String name;
    private final List<Iou> owes;
    private final List<Iou> owedBy;

    private User(final String name, final List<Iou> owes, final List<Iou> owedBy) {
        this.name = name;
        this.owes = new ArrayList<>(owes);
        this.owedBy = new ArrayList<>(owedBy);
    }

    public static Builder builder() {
        return new Builder();
    }

    public String name() {
        return name;
    }

    /** IOUs this user owes to other users. */
    public List<Iou> owes() {
        return unmodifiableList(owes);
    }

    public double owes(final String name) {
        for (final Iou iou : owes()) {
            if (iou.name.equals(name)) {
                return iou.amount;
            }
        }

        return 0;
    }

    /** IOUs other users owe to this user. */
    public List<Iou> owedBy() {
        return unmodifiableList(owedBy);
    }

    public double owedBy(final String name) {
        for (final Iou iou : owedBy()) {
            if (iou.name.equals(name)) {
                return iou.amount;
            }
        }

        return 0;
    }

    public JSONObject toJsonObject() {
        final double owesSum = owes.stream().mapToDouble(iou -> iou.amount).sum();
        final double owedBySum = owedBy.stream().mapToDouble(iou -> iou.amount).sum();

        final JSONObject owesObj = new JSONObject();
        for (final Iou o : owes) {
            owesObj.put(o.name, o.amount);
        }

        final JSONObject owedByObj = new JSONObject();
        for (final Iou o : owedBy) {
            owedByObj.put(o.name, o.amount);
        }

        return new JSONObject()
                .put("name", name)
                .put("owes", owesObj)
                .put("owedBy", owedByObj)
                .put("balance", owedBySum - owesSum);
    }

    public static class Builder {
        private final List<Iou> owes = new ArrayList<>();
        private final List<Iou> owedBy = new ArrayList<>();
        private String name;

        public Builder setName(final String name) {
            this.name = name;
            return this;
        }

        public Builder owes(final String name, final double amount) {
            owes.add(new Iou(name, amount));
            return this;
        }

        public Builder owedBy(final String name, final double amount) {
            owedBy.add(new Iou(name, amount));
            return this;
        }

        public User build() {
            return new User(name, owes, owedBy);
        }
    }
}
