package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Region;
import seedu.address.model.report.Proportion;

/**
 * The Report Panel of the App.
 */
public class ReportPanel extends UiPart<Region> {
    private static final String FXML = "ReportPanel.fxml";
    private static final String chartName = "Interns recruitment pipeline";

    private List<Proportion> allProportions;

    @FXML
    private PieChart pieChart;

    public ReportPanel(List<Proportion> allProportions) {
        super(FXML);

        this.allProportions = allProportions;
        pieChart.setTitle(chartName);
        pieChart.setData(tabulateData());
    }

    /**
     * Formats the data into PieChart.Data for display
     */
    private ObservableList<PieChart.Data> tabulateData() {

        ArrayList<PieChart.Data> data = new ArrayList<>();

        for (Proportion p : allProportions) {
            data.add(new PieChart.Data(p.tagName, p.value));
        }
        return FXCollections.observableArrayList(data);
    }
}
