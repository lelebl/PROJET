/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue.Reporting;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author marin_000
 */
public class BarChart {
    private String nom;
    private DefaultCategoryDataset dataset;
    private JFreeChart barChart;
    public ChartPanel barPanel;
    
    
    public BarChart(String n, ArrayList<String> names, double[] values_m,double[] values_d, String abs, String ord, String a, String b){
        nom=n;
        dataset = new DefaultCategoryDataset();
        this.upDateDataset(names, values_m, values_d,a,b);
        barChart = ChartFactory.createBarChart(
                    nom,           
                    abs,            
                    ord,            
                    dataset,          
                    PlotOrientation.VERTICAL,           
                    true, true, false);
        barPanel= new ChartPanel(barChart,false);
        barPanel.setSize(new Dimension(500, 270));
        barPanel.setBackground(new Color(0,0,0,0));
        barChart.setBorderVisible(false);
        CategoryPlot plot = barChart.getCategoryPlot();
        plot.setBackgroundPaint( new Color(0,0,0,0));
        barChart.setBackgroundPaint(new Color(0,0,0,0));
        
        barPanel.setVisible(true);
    }
    
    public void upDateDataset(ArrayList<String> names, double[] values_m,double[] values_d, String a, String b){
        dataset.clear();
        for(int i=0; i<names.size(); i++){
            dataset.addValue(values_m[i],a,names.get(i));
            dataset.addValue(values_d[i],b,names.get(i));
        }
    }
   
}
