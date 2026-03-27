import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class BuildTree {
    private static final Comparator<Record> RECORD_COMPARATOR =
            Comparator.comparingInt(Record::getParentId).thenComparingInt(Record::getRecordId);

    TreeNode buildTree(final ArrayList<Record> records) throws InvalidRecordsException {
        if (records.isEmpty()) {
            return null;
        }

        records.sort(RECORD_COMPARATOR);
        if (records.getFirst().getRecordId() != 0 || records.getFirst().getParentId() != 0) {
            throw new InvalidRecordsException("Invalid Records");
        }

        final List<TreeNode> tree = new ArrayList<>();
        for (final Record record : records) {
            final int recordId = record.getRecordId();
            final int parentId = record.getParentId();
            if (recordId >= records.size()) {
                throw new InvalidRecordsException("Invalid Records");
            }
            if (recordId == parentId && recordId != 0) {
                throw new InvalidRecordsException("Invalid Records");
            }
            final TreeNode node = new TreeNode(recordId);
            if (recordId != 0) {
                if (parentId >= tree.size()) {
                    throw new InvalidRecordsException("Invalid Records");
                }
                tree.get(parentId).getChildren().add(node);
            }
            tree.add(node);
        }

        return tree.getFirst();
    }
}
