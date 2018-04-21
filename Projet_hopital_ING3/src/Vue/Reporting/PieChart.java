/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue.Reporting;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

/**
 *
 * @author marin_000
 */
public class PieChart{
    
    private String nom;
    private DefaultPieDataset dataset;
    private JFreeChart pieChart;
    public ChartPanel piePanel;
    
    public PieChart(String n,String[] name, double[] values){
        nom=n;
        dataset = new DefaultPieDataset();
        this.upDateDataset(name, values);
        pieChart=createChart(dataset, nom);
        piePanel= new ChartPanel(pieChart);
        piePanel.setPreferredSize(new Dimension(500, 270));
        piePanel.setVisible(true);
    }
    
    public void upDateDataset(String[] name, double[] values){
        dataset.clear();
        for(int i=0; i<name.length; i++){
            dataset.setValue(name[i],values[i]);
        }
    }

    private JFreeChart createChart(DefaultPieDataset dataset, String nom) {
        JFreeChart chart = ChartFactory.createPieChart3D(
            nom,                  // chart title
            dataset,                // data
            true,                   // include legend
            true,
            false
        );

        chart.setBackgroundPaint(new Color(0,0,0,0));
        

        PiePlot3D plot;
        plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        plot.setOutlineVisible(false);
        plot.setBackgroundPaint( new Color(0,0,0,0));
        plot.setLabelFont(new Font("New Times Roman",  Font.BOLD, 20));
        
        return chart;
    }
    
}
