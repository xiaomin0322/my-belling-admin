package test;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import ChartDirector.Chart;
import ChartDirector.ChartViewer;
import ChartDirector.PieChart;

public class Anglepie implements DemoModule
{
    //Name of demo program
    public String toString() { return "Start Angle and Direction"; }

    //Number of charts produced in this demo
    public int getNoOfCharts() { return 2; }

    //Main code for creating charts
    public void createChart(ChartViewer viewer, int chartIndex)
    {
        // The data for the pie chart
        double[] data = {25, 18, 15, 12, 8, 30, 35};

        // The labels for the pie chart
        String[] labels = {"Labor", "Licenses", "Taxes", "Legal", "Insurance", "Facilities",
            "Production"};

        // Create a PieChart object of size 280 x 240 pixels
        PieChart c = new PieChart(280, 240);

        // Set the center of the pie at (140, 130) and the radius to 80 pixels
        c.setPieSize(140, 130, 80);

        // Add a title to the pie to show the start angle and direction
        if (chartIndex == 0) {
            c.addTitle("Start Angle = 0 degrees\nDirection = Clockwise");
        } else {
            c.addTitle("Start Angle = 90 degrees\nDirection = AntiClockwise");
            c.setStartAngle(90, false);
        }

        // Draw the pie in 3D
        c.set3D();

        // Set the pie data and the pie labels
        c.setData(data, labels);

        // Explode the 1st sector (index = 0)
        c.setExplode(0);
        
        byte[] bytes = c.makeChart(Chart.PNG);
        
        try {
			FileUtils.writeByteArrayToFile(new File("d:\\test.png"), bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}

        // Output the chart
        viewer.setChart(c);

        //include tool tip for the chart
        viewer.setImageMap(c.getHTMLImageMap("clickable", "",
            "title='{label}: US${value}K ({percent}%)'"));
    }

    //Allow this module to run as standalone program for easy testing
    public static void main(String[] args)
    {
        //Instantiate an instance of this demo module
        DemoModule demo = new Anglepie();

        //Create and set up the main window
        JFrame frame = new JFrame(demo.toString());
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);} });
        frame.getContentPane().setBackground(Color.white);
        frame.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));
        frame.setSize(800, 450);

        // Create the charts and put them in the content pane
        for (int i = 0; i < demo.getNoOfCharts(); ++i)
        {
            ChartViewer viewer = new ChartViewer();
            demo.createChart(viewer, i);
            frame.getContentPane().add(viewer);
        }

        // Display the window
        frame.setVisible(true);
    }
}

