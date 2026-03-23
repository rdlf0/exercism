import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/** THIS IS NOT A REST API AT ALL! A REALLY LOW QUALITY EXERCISM PROBLEM! */
class RestApi {
    final SortedMap<String, User> usersByName;

    RestApi(final User... users) {
        usersByName =
                Arrays.stream(users)
                        .collect(
                                Collectors.toMap(
                                        User::name,
                                        Function.identity(),
                                        (a, _) -> a,
                                        TreeMap::new));
    }

    String get(final String url) {
        return this.get(url, null);
    }

    String get(final String url, final JSONObject payload) {
        return switch (url) {
            case "/users" -> this.getUsers(payload);
            default -> throw new IllegalArgumentException("Unsupported URL");
        };
    }

    private String getUsers(final JSONObject payload) {
        final JSONObject response = new JSONObject();
        final JSONArray array = new JSONArray();
        response.put("users", array);
        if (payload == null) {
            for (final String name : usersByName.keySet()) {
                array.put(usersByName.get(name).toJsonObject());
            }
            return response.toString();
        }

        final JSONArray requestedUsers = payload.getJSONArray("users");
        final List<String> requestedUserNames = new ArrayList<>();
        for (final Object r : requestedUsers) {
            requestedUserNames.add(String.valueOf(r));
        }

        for (final String name : usersByName.keySet()) {
            if (requestedUserNames.contains(name)) {
                array.put(usersByName.get(name).toJsonObject());
            }
        }

        return response.toString();
    }

    String post(final String url, final JSONObject payload) {
        return switch (url) {
            case "/add" -> this.addUser(payload);
            case "/iou" -> this.addIou(payload);
            default -> throw new IllegalArgumentException("Unsupported URL");
        };
    }

    private String addUser(final JSONObject payload) {
        final String name = payload.getString("user");
        final User user = User.builder().setName(name).build();
        return user.toJsonObject().toString();
    }

    private String addIou(final JSONObject payload) {
        final String lenderName = payload.getString("lender");
        final String borrowerName = payload.getString("borrower");
        final double amount = payload.getDouble("amount");

        final User.Builder lenderBuilder = User.builder().setName(lenderName);
        if (usersByName.containsKey(lenderName)) {
            final User existingLender = usersByName.get(lenderName);
            double owedBy = existingLender.owedBy(borrowerName);
            double owes = existingLender.owes(borrowerName);
            if (amount >= owes) {
                owedBy = amount - owes;
                owes = 0;
            } else {
                owes -= amount;
            }

            if (owes > 0) {
                lenderBuilder.owes(borrowerName, owes);
            }

            for (final Iou existingOwes : existingLender.owes()) {
                if (!existingOwes.name.equals(borrowerName)) {
                    lenderBuilder.owes(existingOwes.name, existingOwes.amount);
                }
            }

            if (owedBy > 0) {
                lenderBuilder.owedBy(borrowerName, owedBy);
            }

            for (final Iou existingOwedBy : existingLender.owedBy()) {
                if (!existingOwedBy.name.equals(borrowerName)) {
                    lenderBuilder.owedBy(existingOwedBy.name, existingOwedBy.amount);
                }
            }
        } else {
            lenderBuilder.owedBy(borrowerName, amount);
        }
        usersByName.put(lenderName, lenderBuilder.build());

        final User.Builder borrowerBuilder = User.builder().setName(borrowerName);
        if (usersByName.containsKey(borrowerName)) {
            final User existingBorrower = usersByName.get(borrowerName);
            double owedBy = existingBorrower.owedBy(lenderName);
            double owes = existingBorrower.owes(lenderName);
            if (amount >= owedBy) {
                owes = amount - owedBy;
                owedBy = 0;
            } else {
                owedBy -= amount;
            }

            if (owes > 0) {
                borrowerBuilder.owes(lenderName, owes);
            }

            for (final Iou existingOwes : existingBorrower.owes()) {
                if (!existingOwes.name.equals(lenderName)) {
                    borrowerBuilder.owes(existingOwes.name, existingOwes.amount);
                }
            }

            if (owedBy > 0) {
                borrowerBuilder.owedBy(lenderName, owedBy);
            }

            for (final Iou existingOwedBy : existingBorrower.owedBy()) {
                if (!existingOwedBy.name.equals(lenderName)) {
                    borrowerBuilder.owedBy(existingOwedBy.name, existingOwedBy.amount);
                }
            }
        } else {
            borrowerBuilder.owes(lenderName, amount);
        }
        usersByName.put(borrowerName, borrowerBuilder.build());

        return getUsers(
                new JSONObject().put("users", new JSONArray().put(lenderName).put(borrowerName)));
    }
}
