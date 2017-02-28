package control;
import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class Graph extends JFrame {
	ArrayList<Integer> ds;
	
	public Graph(ArrayList<Integer> ds) {
        super("XY Line Chart Example with JFreechart");
        this.ds = ds;
 
        JPanel chartPanel = createChartPanel();
        add(chartPanel, BorderLayout.CENTER);
 
        setSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
 
    private JPanel createChartPanel() {
        // creates a line chart object
        // returns the chart panel
        String chartTitle = "Objects Movement Chart";
        String xAxisLabel = "X";
        String yAxisLabel = "Y";
     
        XYDataset dataset = createDataset(ds);
     
        JFreeChart chart = ChartFactory.createXYLineChart(chartTitle,
                xAxisLabel, yAxisLabel, dataset);
     
        return new ChartPanel(chart);
    }
 
    private XYDataset createDataset(ArrayList<Integer> ds) {
        // creates an XY dataset...
        // returns the dataset
    	XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series1 = new XYSeries("Object 1");
        for(int i = 1; i < ds.size(); i++) {
        	series1.add(i,ds.get(i));
        }
        dataset.addSeries(series1);
        return dataset;
    }
}
