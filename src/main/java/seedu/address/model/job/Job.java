package seedu.address.model.job;

import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueTagList;

/***
 * TODO: Write javadoc commenet
 */
public class Job {
    private final String jobTitle;
    private final String location;
    private final String skills;
    private final UniqueTagList tags;

    public Job(String jobTitle, String location, String skills, Set<Tag> tags) {
        this.jobTitle = jobTitle;
        this.location = location;
        this.skills = skills;
        this.tags = new UniqueTagList(tags);
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getLocation() {
        return location;
    }

    public String getSkills() {
        return skills;
    }

    public UniqueTagList getTags() {
        return tags;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Job)) {
            return false;
        }

        Job otherJob = (Job) other;
        return otherJob.getJobTitle().equals(this.getJobTitle())
                && otherJob.getLocation().equals(this.getLocation())
                && otherJob.getSkills().equals(this.getSkills())
                && otherJob.getTags().equals(this.getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(jobTitle, skills, location, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getJobTitle())
                .append(" Location: ")
                .append(getLocation())
                .append(" Skills: ")
                .append(getSkills())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }


}
