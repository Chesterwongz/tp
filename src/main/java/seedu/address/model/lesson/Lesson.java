package seedu.address.model.lesson;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public abstract class Lesson {

    // Time fields
    private final Date date;
    private final Time startTime;
    private final Time endTime;

    // Data fields
    private final Subject subject;
    private final Set<Homework> homework = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Lesson(Date date, Time startTime, Time endTime, Subject subject, Set<Homework> homework) {
        requireAllNonNull(date, startTime, endTime, homework);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.subject = subject;
        this.homework.addAll(homework);
    }

    public Date getDate() {
        return date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public Subject getSubject() {
        return subject;
    }

    /**
     * Returns an immutable homework set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Homework> getHomework() {
        return Collections.unmodifiableSet(homework);
    }

    public Date updateDateWithWeek() {
        Date newDate = new Date(getDate().getLocalDate().plusDays(7).toString());
        return newDate;
    }

    public abstract Lesson updateDate(String newDateString);

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof seedu.address.model.lesson.Lesson)) {
            return false;
        }

        seedu.address.model.lesson.Lesson otherLesson = (seedu.address.model.lesson.Lesson) other;
        return otherLesson.getDate().equals(getDate())
                && otherLesson.getStartTime().equals(getStartTime())
                && otherLesson.getEndTime().equals(getEndTime())
                && otherLesson.getSubject().equals(getSubject())
                && otherLesson.getHomework().equals(getHomework());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(date, startTime, endTime, subject, homework);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDate())
                .append("; Start: ")
                .append(getStartTime())
                .append("; End: ")
                .append(getEndTime())
                .append("; Subject: ")
                .append(getSubject());

        Set<Homework> homework = getHomework();
        if (!homework.isEmpty()) {
            builder.append("; Homework: ");
            homework.forEach(builder::append);
        }
        return builder.toString();
    }

}

