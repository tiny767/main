package seedu.address.testutil;

import seedu.address.model.report.Proportion;
import seedu.address.model.report.Report;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A utility class to help with building Report objects.
 */
public class ReportBuilder {

    public static final String DEFAULT_POPULATION = "SEIntern";
    public static final int DEFAULT_TOTAL_PERSONS = 5;
    public static final int DEFAULT_TOTAL_TAGS = 5;

    /** DEFAULT PORTION 1 */
    public static final int DEFAULT_VALUE_1 = 5;
    public static final String DEFAULT_TAG_NAME_1 = "Screening";
    public static final int DEFAULT_TOTAL_PERSONS_1 = 5;

    private Tag population;
    private  List<Proportion> allProportions;
    private  int totalTags;
    private  int totalPersons;

    public ReportBuilder() {
       population = new Tag(DEFAULT_POPULATION);
       allProportions = new ArrayList<>();
       allProportions.add(new Proportion(DEFAULT_TAG_NAME_1, DEFAULT_VALUE_1, DEFAULT_TOTAL_PERSONS_1));
       totalTags = DEFAULT_TOTAL_TAGS;
       totalPersons = DEFAULT_TOTAL_PERSONS;
    }

//    /**
//     * Initializes the PersonBuilder with the data of {@code personToCopy}.
//     */
//    public ReportBuilder(Report personToCopy) {
//        name = personToCopy.getName();
//        phone = personToCopy.getPhone();
//        email = personToCopy.getEmail();
//        address = personToCopy.getAddress();
//        remark = personToCopy.getRemark();
//        link = personToCopy.getLink();
//        skill = personToCopy.getSkills();
//        tags = new HashSet<>(personToCopy.getTags());
//    }
//
    /**
     * Sets the {@code Name} of the {@code Report} that we are building.
     */
    public ReportBuilder withPopulation(String tagName) {
        this.population = new Tag(tagName);
        return this;
    }
//
//    /**
//     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Report} that we are building.
//     */
//    public ReportBuilder withTags(String ... tags) {
//        this.tags = SampleDataUtil.getTagSet(tags);
//        return this;
//    }
//
//    /**
//     * Sets the {@code Address} of the {@code Report} that we are building.
//     */
//    public ReportBuilder withAddress(String address) {
//        this.address = new Address(address);
//        return this;
//    }
//
//    /**
//     * Sets the {@code Phone} of the {@code Report} that
//     * we are building.
//     */
//    public ReportBuilder withPhone(String phone) {
//        this.phone = new Phone(phone);
//        return this;
//    }
//
//    /**
//     * Sets the {@code Email} of the {@code Report} that we are building.
//     */
//    public ReportBuilder withEmail(String email) {
//        this.email = new Email(email);
//        return this;
//    }
//
//    /**
//     * Sets the {@code Remark} of the {@code Report} that we are building.
//     */
//    public ReportBuilder withRemark(String remark) {
//        this.remark = new Remark(remark);
//        return this;
//    }
//
//    /**
//     * Sets the {@code Link} of the {@code Report} that we are building.
//     */
//    public ReportBuilder withLink(String link) {
//        this.link = new Link(link);
//        return this;
//    }
//
//    /**
//     * Sets the {@code Skill} of the {@code Report} that we are building.
//     */
//    public ReportBuilder withSkills(String skills) {
//        this.skill = new Skill(skills);
//        return this;
//    }

    public Report build() {
        return new Report(population, allProportions, totalPersons);
    }

}
