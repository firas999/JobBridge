/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.mycompany.entities.Entreprise;
import com.mycompany.entities.OffreStage;
import com.mycompany.services.ServiceEntreprise;
import com.mycompany.services.ServiceOffreStage;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class StatistiqueEntreprise extends Form {

    ArrayList<Entreprise> data = new ArrayList<>();

    /**
     * Creates a renderer for the specified colors.
     */
    private DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(15);
        renderer.setLegendTextSize(15);
        renderer.setMargins(new int[]{20, 30, 15, 0});
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
        
    }

    /**
     * Builds a category series using the provided values.
     *
     * @param titles the series titles
     * @param values the values
     * @return the category series
     */
    protected CategorySeries buildCategoryDataset(String title, ArrayList<Entreprise> data) {
        CategorySeries series = new CategorySeries(title);
        data = ServiceEntreprise.getInstance().getAllTasks();
        int pt = 0;
        int pc = 0;
        int pe = 0;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getTaille()>1000) {
                pt = pt + 1;
            }
            if (data.get(i).getTaille()<1000) {
                pc = pc + 1;
            }
           

        }


        series.add(" supérieur à 1000", pt);
        series.add(" inférieur à 1000", pc);
  
        return series;
    }

    public StatistiqueEntreprise(Form previous) {
        // Set up the renderer
        
        
        
        
        
        // Set up the renderer
    int[] colors = new int[]{ColorUtil.BLUE,ColorUtil.MAGENTA};
    DefaultRenderer renderer = buildCategoryRenderer(colors);
    renderer.setChartTitleTextSize(80);
    renderer.setDisplayValues(true);
    renderer.setShowLabels(true);
    SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
    
renderer.setLabelsTextSize(50);
    renderer.setLegendTextSize(60);
    renderer.setChartTitle("taille de l'entreprise");
    renderer.setLabelsColor(12345);
    renderer.setBackgroundColor(12346);
        
        
        
        

        // Create the chart ... pass the values and renderer to the chart object.
        PieChart chart = new PieChart(buildCategoryDataset("taille de l'entreprise", data), renderer);

        // Wrap the chart in a Component so we can add it to a form
        ChartComponent c = new ChartComponent(chart);

        // Create a form and show it.
        add(c);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                e -> previous.showBack()); // Revenir vers l'interface précédente
           
    }
    
    
}
