/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Loading;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import opentourtennis.template.FoncResponsable;

/**
 *
 * @author dupor
 */
public class LoadingDialog extends JDialog{
    
    private static LoadingDialog loadingDialog;
    private JProgressBar progressBar;

    public LoadingDialog(FoncResponsable fr) {
        super(fr,"Planning",true);
        
        loadingDialog = this;
        
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setSize(30,75);
        setResizable(false);
        setLocationRelativeTo(fr);
        
        add(BorderLayout.NORTH,new JLabel("Veuillez patienter ..."));
        
        progressBar = new JProgressBar(0,100);
        
        add(BorderLayout.CENTER, progressBar);
    }
    
    public static void setProgressValue(int value){
        loadingDialog.progressBar.setValue(value);
    }
    
    public static void addProgressValue(int value){
        loadingDialog.progressBar.setValue(loadingDialog.progressBar.getValue()+value);
    }
    
}
