import java.util.Collections;
import java.util.List;

class RelationshipComputer<T> {
    Relationship computeRelationship(final List<T> firstList, final List<T> secondList) {
        final boolean firstContainsSecond = Collections.indexOfSubList(firstList, secondList) >= 0;
        final boolean secondContainsFirst = Collections.indexOfSubList(secondList, firstList) >= 0;

        if (firstContainsSecond) {
            if (secondContainsFirst) {
                return Relationship.EQUAL;
            }
            return Relationship.SUPERLIST;
        } else if (secondContainsFirst) {
            return Relationship.SUBLIST;
        } else {
            return Relationship.UNEQUAL;
        }
    }
}
