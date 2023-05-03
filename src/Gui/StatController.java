package Gui
        ;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author spangsberg
 */
public class StatController implements Initializable {

    @FXML
    private BorderPane borderPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        borderPane.setCenter(buildPieChart());
    }

    @FXML
    private void handleShowBarChart() {

        borderPane.setCenter(buildBarChart());
    }

    @FXML
    private void handleShowPieChart() {
        borderPane.setCenter(buildPieChart());
    }

    /*private BarChart buildBarChart() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Most Popular Programming Language");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("# of developers x 1000");

        BarChart barChart = new BarChart(xAxis, yAxis);

        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("Popular programming languages rated by GitHub");

        dataSeries1.getData().add(new XYChart.Data("JavaScript", 2300));
        dataSeries1.getData().add(new XYChart.Data("Python", 1000));
        dataSeries1.getData().add(new XYChart.Data("Java", 986));
        dataSeries1.getData().add(new XYChart.Data("Ruby", 870));
        
        
        barChart.getData().add(dataSeries1);

        return barChart;
    }*/
    private BarChart buildBarChart() {
    CategoryAxis xAxis = new CategoryAxis();
    xAxis.setLabel("Contract Type");

    NumberAxis yAxis = new NumberAxis();
    yAxis.setLabel("Count");

    BarChart barChart = new BarChart(xAxis, yAxis);

    XYChart.Series dataSeries1 = new XYChart.Series();
    dataSeries1.setName("Contract Types");

    try {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
        String query = "SELECT type_de_contrat, COUNT(*) as count FROM Contrat GROUP BY type_de_contrat";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            dataSeries1.getData().add(new XYChart.Data(rs.getString("type_de_contrat"), rs.getInt("count")));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    barChart.getData().add(dataSeries1);
    return barChart;
}

    private PieChart buildPieChart() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    try {
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
    String query = "SELECT PERIOD_DIFF(EXTRACT(YEAR_MONTH FROM date_fin), EXTRACT(YEAR_MONTH FROM date_deb)) AS period, SUM(1) AS count FROM Contrat GROUP BY period";
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery(query);
    while (rs.next()) {
        int periodInMonths = rs.getInt("period");
        int count = rs.getInt("count");
        System.out.println("period: " + periodInMonths + " count: " + count);
            pieChartData.add(new PieChart.Data(Integer.toString(periodInMonths), rs.getInt("count")));
    }
} catch (SQLException e) {
    e.printStackTrace();
}

        //Preparing ObservbleList object         

        PieChart pieChart = new PieChart(pieChartData); //Creating a Pie chart      

        //attach tooltips
        createToolTips(pieChart);

        pieChart.setTitle("Nombre des contrats "); //Setting the title of the Pie chart
        pieChart.setClockwise(true); //setting the direction to arrange the data 
        pieChart.setLabelLineLength(50); //Setting the length of the label line 
        pieChart.setLabelsVisible(true); //Setting the labels of the pie chart visible
        pieChart.setLegendVisible(false);
        pieChart.setStartAngle(180);

        //bind value and label on each pie slice to reflect changes
        pieChartData.forEach(data ->
                data.nameProperty().bind(Bindings.concat(data.getName(), " ", data.pieValueProperty(), " ")
                ));


        ContextMenu contextMenu = new ContextMenu(); //create context menu
        MenuItem miSwitchToBarChart = new MenuItem("Switch to Bar Chart");
        contextMenu.getItems().add(miSwitchToBarChart);


        //Add event handler to display context menu
        pieChart.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getButton() == MouseButton.SECONDARY) {
                            contextMenu.show(pieChart, event.getScreenX(), event.getScreenY());
                        }                        
                    }
                });
               
        
        //Before Java 8
        //Add event handler to change chart type (anonymous inner class)
        miSwitchToBarChart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                borderPane.setCenter(buildBarChart());
            }
        });
        

        //Java 8 and newer (lambda expression)
        miSwitchToBarChart.setOnAction(event -> { borderPane.setCenter(buildBarChart()); });

        
        return pieChart;
    }

    /**
     *
     */
    @FXML
    private void handleClose() {
        System.exit(0);
    }
    
    
    /**
     * 
     */
    @FXML
    private void handleUpdatePieData() {
        Node node = borderPane.getCenter();
        
        if (node instanceof PieChart)
        {
            PieChart pc = (PieChart) node;
            double value = pc.getData().get(2).getPieValue();
            pc.getData().get(2).setPieValue(value * 1.10);
            createToolTips(pc);
        }
    }


    /**
     * Creates tooltips for all data entries
     * @param pc
     */
    private void createToolTips(PieChart pc) {

        for (PieChart.Data data: pc.getData()) {
            String msg = Double.toString(data.getPieValue());

            Tooltip tp = new Tooltip(msg);
         //   tp.setShowDelay(Duration.seconds(0));

            Tooltip.install(data.getNode(), tp);

            //update tooltip data when changed
            data.pieValueProperty().addListener((observable, oldValue, newValue) ->
            {
                tp.setText(newValue.toString());
            });
        }
    }
}