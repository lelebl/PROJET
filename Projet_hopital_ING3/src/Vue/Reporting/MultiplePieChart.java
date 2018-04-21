/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue.Reporting;

import java.awt.Color;
import java.awt.Font;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.MultiplePiePlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.TableOrder;

/**
 *
 * @author marin_000
 */
public class MultiplePieChart {
    private String nom;
    private CategoryDataset dataset;
    private JFreeChart pieChart;
    public ChartPanel piePanel;
    
    public MultiplePieChart(String[] lits, double[] lit_libre, double[] lit_occupe){
        dataset = createDataset(lits,lit_libre,lit_occupe);
        pieChart = createChart(dataset);
        piePanel = new ChartPanel(pieChart, false);
        piePanel.setPreferredSize(new java.awt.Dimension(600, 380));
        piePanel.setBackground(new Color(0,0,0,0));
    }
    
    private CategoryDataset createDataset(String[] no_lits, double[] lit_libre, double[] lit_occupe) {
        double[][] data=new double[no_lits.length][2];
        Comparable[] type= new String[2];
        type[0]="lit occupé";
        type[1]="lit libre";
        for(int i=0; i<no_lits.length;i++){
            data[i][1]=lit_libre[i];
            data[i][0]=lit_occupe[i];
        }
        
        final CategoryDataset d = DatasetUtilities.createCategoryDataset(
            no_lits,
            type,
            data
        );
        return d;
    }
    
    private JFreeChart createChart(final CategoryDataset dataset) {
        final JFreeChart chart = ChartFactory.createMultiplePieChart(
            "Multiple Pie Chart",  // chart title
            dataset,               // dataset
            TableOrder.BY_ROW,
            true,                  // include legend
            true,
            false
        );
        chart.setBackgroundPaint(new Color(0,0,0,0));
        MultiplePiePlot plot = (MultiplePiePlot) chart.getPlot();
        JFreeChart subchart = plot.getPieChart();
        subchart.setBackgroundPaint(new Color(0,0,0,0));
        PiePlot p = (PiePlot) subchart.getPlot();
        p.setBackgroundPaint( new Color(0,0,0,0));
        //p.setLabelGenerator(new StandardPieItemLabelGenerator("{0}"));
        p.setLabelFont(new Font("New Times Roman", Font.BOLD, 8));
        p.setInteriorGap(0.30);
        
        return chart;
    }
}
