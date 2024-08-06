/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package others;

import basic.ComboBoxCellEditor;
import basic.DatePickerCellEditor;
import basic.Config;
import basic.SendEmail;
import basic.WiderCombo;
import basic.global;
import basic.utils;
import basic.ComboBoxCellEditor;
import basic.FillCombo;
import com.alee.extended.date.WebDateField;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.tableeditors.DateTableEditor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.table.TableColumn;

/**
 *
 * @author admin
 */
public class ConfigLib {
    
    public static void addTimeHHmm(JTable table, int col) {

        JFormattedTextField txtInicio = new JFormattedTextField();
        setFormattedHM(txtInicio, 12);
        DefaultCellEditor hinicio = new DefaultCellEditor(txtInicio);
        hinicio.setClickCountToStart(2);
        table.getColumnModel().getColumn(col).setCellEditor(hinicio);

    }

    public static void addCombo(JTable table, JComboBox combo, int posicion) {
        table.getColumnModel().getColumn(posicion).setCellEditor(new ComboBoxCellEditor(combo));
    }

    public static void addDefaultCombo(JTable table, JComboBox combobox, int posicion) {
        DefaultCellEditor editor = new DefaultCellEditor(combobox);
        editor.setClickCountToStart(2);
        table.getColumnModel().getColumn(posicion).setCellEditor(editor);
    }

    public static void setTable(JTable table) {
        table.putClientProperty("terminateEditOnFocusLost", true);
        table.setFillsViewportHeight(true);
        table.getTableHeader().setReorderingAllowed(false);

    }

    public static void addCheck(JTable table, int col) {
        TableColumn cmodel = table.getColumnModel().getColumn(col);
        cmodel.setCellEditor(table.getDefaultEditor(Boolean.class));
        cmodel.setCellRenderer(table.getDefaultRenderer(Boolean.class));

    }

    public static void addDate(JTable table, int col) {
//        DateTableEditor editor = new DateTableEditor();
//        editor.clickCountToEdit = 2;
//        setDatePicker(editor.getDatePicker(), 12);
//        table.setDefaultEditor(LocalDateTime.class, editor);
//        table.setDefaultRenderer(LocalDateTime.class, editor);
//
//        // Explicitly set the default editor and renderer for column index 0.
//        table.getColumnModel().getColumn(col).setCellEditor(table.getDefaultEditor(LocalDateTime.class));
//        table.getColumnModel().getColumn(col).setCellRenderer(table.getDefaultRenderer(LocalDateTime.class));
        
        
        
        /////////////////////////
        
        DatePickerCellEditor editor = new DatePickerCellEditor();
//        editor.clickCountToEdit = 2;
        table.getColumnModel().getColumn(col).setCellEditor(editor);
        
    }

    public static void addWebDate(JTable table, int col) {
        WebDateField txtFecha = new WebDateField(new Date());
        txtFecha.setDateFormat(new SimpleDateFormat(global.ffecha));
//        txtFecha.setFormatterFactory(new DefaultFormatterFactory(new DateFormatter(new SimpleDateFormat(global.ffecha))));
        WebDate(txtFecha, 12);
        DefaultCellEditor fecha = new DefaultCellEditor(txtFecha);
        fecha.setClickCountToStart(2);
        table.getColumnModel().getColumn(col).setCellEditor(fecha);
    }

    public static void setDatePicker(DatePicker txt, int size) {
        DatePickerSettings settings = new DatePickerSettings();
        settings.setFirstDayOfWeek(DayOfWeek.MONDAY);
        settings.setAllowEmptyDates(true);
        settings.setFormatForDatesCommonEra(global.ffecha);
        settings.setFormatForTodayButton(DateTimeFormatter.ofPattern(global.ffecha));
        settings.setFontValidDate(new Font("Tahoma", 0, size));
        settings.setFontInvalidDate(new Font("Tahoma", 0, size));
//        txt.getComponentDateTextField().setFont(new java.awt.Font("Tahoma", 0, size+4));
        txt.setSettings(settings);
//        txt.setFont(new java.awt.Font("Tahoma", 0, size+4));
        txt.setDateToToday();
    }
    public static void setDatePicker(DatePicker txt, int size, String formatofecha) {
        DatePickerSettings settings = new DatePickerSettings();
        settings.setFirstDayOfWeek(DayOfWeek.MONDAY);
        settings.setAllowEmptyDates(true);
        settings.setFormatForDatesCommonEra(formatofecha);
        settings.setFormatForTodayButton(DateTimeFormatter.ofPattern(formatofecha));
        settings.setFontValidDate(new Font("Tahoma", 0, size));
        settings.setFontInvalidDate(new Font("Tahoma", 0, size));
//        txt.getComponentDateTextField().setFont(new java.awt.Font("Tahoma", 0, size+4));
        txt.setSettings(settings);
//        txt.setFont(new java.awt.Font("Tahoma", 0, size+4));
        txt.setDateToToday();
    }

    public static void tcdia(JTextField txtf) {
        txtf.setText(utils.dbConsult("SELECT IFNULL((SELECT FORMAT(TipoCambio,4) from tipocambio_tbl where DATEDIFF(now(),Fecha) = 0),0)"));
    }

    public static void setFormattedHM(JFormattedTextField txt, int size) {
        try {
            txt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt.setFont(new java.awt.Font("Tahoma", 0, size));
        txt.setCaretPosition(0);
    }

//    public static void txtFormatted_ddmmyyyyHM(JFormattedTextField txt, int size) {
//        try {
//            txt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.##.#### ##:##")));
//        } catch (java.text.ParseException ex) {
//            ex.printStackTrace();
//        }
//        txt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
//        txt.setFont(new java.awt.Font("Tahoma", 0, size));
//        txt.setCaretPosition(0);
//    }

    public static void spinnerHMIni(JSpinner s) {
        Date date = new Date();
        SpinnerDateModel sm = new SpinnerDateModel(date, null, null, Calendar.MINUTE);
        s.setModel(sm);
        JSpinner.DateEditor de = new JSpinner.DateEditor(s, "HH:mm");
        s.setEditor(de);
    }

    public static void spinnerInt(JSpinner s, Font font, float start, int step) {
        s.setFont(font); // NOI18N
        s.setModel(new javax.swing.SpinnerNumberModel(start, null, null, step));
        JSpinner.NumberEditor editor = (JSpinner.NumberEditor) s.getEditor();
        DecimalFormat format = editor.getFormat();
        format.setMinimumFractionDigits(2);
    }

    public static void WebDate(WebDateField field, int size) {
        field.setDateFormat(new java.text.SimpleDateFormat(global.ffecha));
//        field.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat(global.ffecha))));

        field.setInputPromptPosition(SwingConstants.CENTER);
        field.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        field.setFont(new Font("Tahoma", 0, size));
        field.setEditable(true);
    }

    public static void openWindow(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds((int) (screenSize.getWidth() / 2) - 640, (int) (screenSize.getHeight() / 2) - 380, 1280, 720);
        frame.setVisible(true);
        ImageIcon icon = new ImageIcon("images\\icon.png");
        frame.setIconImage(icon.getImage());
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                frame.removeAll();
                frame.dispose();
                System.gc();
            }
        });
    }
    
    public static void openWindowKeepsize(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        ImageIcon icon = new ImageIcon("images\\icon.png");
        frame.setIconImage(icon.getImage());
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                frame.removeAll();
                frame.dispose();
                System.gc();
            }
        });
    }

    public static void openWindow(JFrame frame, boolean fullscreen) {
        if (fullscreen) {
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        } else {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            frame.setBounds((int) (screenSize.getWidth() / 2) - 640, (int) (screenSize.getHeight() / 2) - 380, 1280, 720);
        }
        frame.setVisible(true);
        ImageIcon icon = new ImageIcon("images\\icon.png");
        frame.setIconImage(icon.getImage());
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                frame.removeAll();
                frame.dispose();
                System.gc();
            }
        });
    }

    public static void txtDatePicker(DatePicker txt, int size) {
        DatePickerSettings settings = new DatePickerSettings();
        settings.setFirstDayOfWeek(DayOfWeek.MONDAY);

        settings.setFormatForDatesCommonEra(global.ffecha);
        settings.setFormatForTodayButton(DateTimeFormatter.ofPattern(global.ffecha));

        txt.setSettings(settings);
        txt.setFont(new java.awt.Font("Tahoma", 0, size));
        txt.setDateToToday();
    }
      public static void txtFormattedHM(JFormattedTextField txt, int size) {
        try {
            txt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("**:**")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt.setFont(new java.awt.Font("Tahoma", 0, size));
        txt.setCaretPosition(0);
    }

}
