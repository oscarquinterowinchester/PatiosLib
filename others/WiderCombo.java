/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package others;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import basic.Config;
import basic.SendEmail;
//import basic.WiderCombo;
import basic.global;
import basic.utils;
import basic.ComboBoxCellEditor;
import basic.FillCombo;

/**
 *
 * @author Alex
 */
public class WiderCombo extends JComboBox{ 
 
    public WiderCombo() { 
    } 
 
    public WiderCombo(final Object items[]){ 
        super(items); 
    } 
 
    public WiderCombo(ComboBoxModel aModel) { 
        super(aModel); 
    } 
 
    private boolean layingOut = false; 
 
    public void doLayout(){ 
        try{ 
            layingOut = true; 
            super.doLayout(); 
        }finally{ 
            layingOut = false; 
        } 
    } 
 
    public Dimension getSize(){ 
        Dimension dim = super.getSize(); 
        
        if(!layingOut) 
            dim.width = Math.max(dim.width, getPreferredSize().width); 
        return dim; 
    } 
}