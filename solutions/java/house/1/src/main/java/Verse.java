class Verse {

    private String predicate;
    private String subject;

    Verse(String predicate, String subject) {
        this.predicate = predicate;
        this.subject = subject;
    }

    String getPredicate() {
        return predicate;
    }

    String getSubject() {
        return subject;
    }
}
