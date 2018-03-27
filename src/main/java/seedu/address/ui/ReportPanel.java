package seedu.address.ui;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Region;

/**
 * The Report Panel of the App.
 */
public class ReportPanel extends UiPart<Region> {
    private static final String FXML = "ReportPanel.fxml";

    //TODO: remove stubs
    private static final String chartName = "Interns recruitment pipeline";

    @FXML
    private PieChart pieChart;

    public ReportPanel() {
        super(FXML);

        pieChart.setTitle(chartName);
        pieChart.setData(tabulateData());
    }

    /**
     * Formats the data into PieChart.Data for display
     */
    private ObservableList<PieChart.Data> tabulateData() {

        ArrayList<PieChart.Data> data = new ArrayList<>();

        //TODO: rmeove stubs
        data.add(new PieChart.Data("Screening", 200));
        data.add(new PieChart.Data("Interviewing", 20));
        data.add(new PieChart.Data("Accepted", 4));

        return FXCollections.observableArrayList(data);
    }
}
