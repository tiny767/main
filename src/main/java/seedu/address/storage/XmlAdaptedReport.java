package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.MainApp;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Link;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.report.Proportion;
import seedu.address.model.report.Report;
import seedu.address.model.tag.Tag;

/**
 * JAXB-friendly version of the Report;
 */
public class XmlAdaptedReport {

    @XmlElement(required = true)
    private XmlAdaptedTag population;
    @XmlElement(required = true, defaultValue = "null")
    private Integer totalTags;
    @XmlElement(required = true, defaultValue = "null")
    private Integer totalPersons;
    @XmlElement
    private List<XmlAdaptedProportion> proportions = new ArrayList<>();
    @XmlElement
    private String test;

    /**
     * Constructs an XmlAdaptedReport.
     * This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedReport() {}

    /**
     * Constructs an {@code XmlAdaptedReport} with the given Report details.
     */
    public XmlAdaptedReport(XmlAdaptedTag population, int totalTags, int totalPersons,
                                List<XmlAdaptedProportion> proportions) {
        this.population = population;
        this.totalPersons = totalPersons;
        this.totalTags = totalTags;
        this.proportions = proportions;
    }

    /**
     * Converts a given Report into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created XmlAdaptedReport
     */
    public XmlAdaptedReport(Report source) {
        population = new XmlAdaptedTag(source.getPopulation());
        totalTags = source.getTotalTags();
        totalPersons = source.getTotalPersons();
        proportions = new ArrayList<>();
        for (Proportion p : source.getAllProportions()) {
            proportions.add(new XmlAdaptedProportion(p));
        }
        test = source.getPopulation().tagName;
    }

    /**
     * Converts this jaxb-friendly adapted Report object into the model's Report object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Proportion
     */
    public Report toModelType() throws IllegalValueException {
        final List<Proportion> proportions = new ArrayList<>();
        for (XmlAdaptedProportion p : this.proportions) {
            proportions.add(p.toModelType());
        }

        final Tag population = this.population.toModelType();
        System.out.println("Created a xml model.");

//        return new Report(population, proportions, totalPersons);
        return new Report(new Tag(test), new ArrayList<>(), 0);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlAdaptedReport)) {
            return false;
        }

        XmlAdaptedReport otherReport = (XmlAdaptedReport) other;
        return false;
//        return Objects.equals(population, otherReport.population)
//                && proportions.equals(otherReport.proportions)
//                && totalPersons == otherReport.totalPersons
//                && totalTags == otherReport.totalTags;
    }
}
