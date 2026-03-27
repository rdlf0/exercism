import java.util.ArrayList;

class BuildTree {
    private static void validateRecord(final Record record, final Record[] ordered)
            throws InvalidRecordsException {
        final int recordId = record.getRecordId();
        final int parentId = record.getParentId();
        if (recordId < 0 || recordId >= ordered.length || ordered[recordId] != null) {
            throw new InvalidRecordsException("Invalid Records");
        }
        if (recordId == 0) {
            if (parentId != 0) {
                throw new InvalidRecordsException("Invalid Records");
            }
        } else if (parentId >= recordId) {
            throw new InvalidRecordsException("Invalid Records");
        }
    }

    TreeNode buildTree(final ArrayList<Record> records) throws InvalidRecordsException {
        if (records.isEmpty()) {
            return null;
        }

        final int len = records.size();
        final Record[] orderedByRecordId = new Record[len];
        for (final Record record : records) {
            validateRecord(record, orderedByRecordId);
            orderedByRecordId[record.getRecordId()] = record;
        }

        final TreeNode[] tree = new TreeNode[len];
        tree[0] = new TreeNode(0);
        for (int i = 1; i < len; i++) {
            final TreeNode child = new TreeNode(i);
            tree[i] = child;
            tree[orderedByRecordId[i].getParentId()].getChildren().add(child);
        }

        return tree[0];
    }
}
