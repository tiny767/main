package seedu.address.ui;

import java.awt.*;
import java.util.ArrayList;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Region;
import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.report.Proportion;
import seedu.address.model.report.Report;

import javax.xml.soap.Text;

/**
 * The Report Panel of the App.
 */
public class ReportPanel extends UiPart<Region> {
    private static final String FXML = "ReportPanel.fxml";
    private static final Logger logger = LogsCenter.getLogger(MainApp.class);
    private static final Double CHART_BAR_GAP = 0.1;

    private Report pReport;

    @FXML
    private PieChart pieChart;

    @FXML
    private BarChart barChart;

    public ReportPanel(Report report) {
        super(FXML);
        pReport = report;

        pieChart.setTitle("In " + report.getTotalTags() + " Tags");
        pieChart.setData(tabulateData());
        pieChart.setLabelsVisible(false);

        String barChartTitle = "STATISTICS OF #" + report.getPopulation().tagName + " CANDIDATES\n"
                + "\n"
                + "In " + report.getTotalPersons() + " candidates";
        barChart.setTitle(barChartTitle);
        barChart.setData(tabulateBarChartData());
        barChart.setBarGap(CHART_BAR_GAP);
    }

    /**
     * Formats the data into PieChart.Data for display
     */
    private ObservableList<PieChart.Data> tabulateData() {
        ArrayList<PieChart.Data> data = new ArrayList<>();
        int count = 0;

        for (Proportion p : pReport.getAllProportions()) {
            count++;
            int percent = (int) ((p.value * 100.0f) / pReport.getTotalTags());
            data.add(new PieChart.Data(count + ". " + p.tagName + " " + percent + "%", p.value));
        }
        return FXCollections.observableArrayList(data);
    }

    /**
     * Formats the data into BarChart.Data for display
     */
    private ObservableList<XYChart.Series<String, Integer>> tabulateBarChartData() {
        ObservableList<XYChart.Series<String, Integer>> data = FXCollections.observableArrayList();
        XYChart.Series<String, Integer> series = new XYChart.Series<String, Integer>();
        series.setName("Number of Candidates Having The Tag");

        for (Proportion p : pReport.getAllProportions()) {
            series.getData().add(new XYChart.Data(p.tagName, new Integer(p.totalPersons)));
        }
        data.addAll(series);

        return data;
    }
}
