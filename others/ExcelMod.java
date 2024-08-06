/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package others;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import basic.Config;
import basic.SendEmail;
import basic.WiderCombo;
import basic.global;
import basic.utils;
import basic.ComboBoxCellEditor;
import basic.FillCombo;

/**
 *
 * @author RUBEN
 */
public class ExcelMod {

    public static void crearExcel(ArrayList<String[]> data, File file, int inirow) throws IOException, SQLException {

        FileInputStream instream = new FileInputStream(file);
        //Creamos una instancia de la clase HSSFWorkbook llamada libro
        HSSFWorkbook libro = new HSSFWorkbook(instream);

        //Creamos una instancia de la clase HSSFSheet llamada hoja y la creamos
        HSSFSheet hoja = libro.getSheetAt(0);

        //Creamos una instancia de la clase HSSFRow llamada fila y creamos la fila con el indice 0
        for (int i = 0; i < data.size(); i++) {
            HSSFRow fila = hoja.getRow(inirow);
            if (fila == null) {
                fila = hoja.createRow(inirow);
            }
            for (int j = 0; j < data.get(i).length; j++) {
                HSSFCell celda = fila.getCell(j);
                if (celda == null) {
                    celda = fila.createCell(j);
                }
                celda.setCellValue(new HSSFRichTextString(data.get(i)[j]));

            }
            inirow++;
        }

        instream.close();
        FileOutputStream out = new FileOutputStream(file.getAbsolutePath());
        libro.write(out);
        out.close();

        try {
            //definiendo la ruta en la propiedad file

            Runtime.getRuntime().exec("cmd /c start " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void modificarExcelFourKite(ArrayList<String[]> data, File file, int inirow) throws IOException, SQLException 
    {
        FileInputStream instream = new FileInputStream(file);
        //Creamos una instancia de la clase HSSFWorkbook llamada libro
        HSSFWorkbook libro = new HSSFWorkbook(instream);
        //Creamos una instancia de la clase HSSFSheet llamada hoja y la creamos
        HSSFSheet hoja = libro.getSheetAt(0);
        //Creamos una instancia de la clase HSSFRow llamada fila y creamos la fila con el indice 0
        for (int i = 0; i < data.size(); i++) {
            HSSFRow fila = hoja.getRow(inirow);
            if (fila == null) {
                fila = hoja.createRow(inirow);
            }
            for (int j = 0; j < data.get(i).length; j++) {
                HSSFCell celda = fila.getCell(j);
                if (celda == null) {
                    celda = fila.createCell(j);
                }
                if (data.get(i)[j] != null) {
                    celda.setCellValue(new HSSFRichTextString(data.get(i)[j]));
                }
            }
            inirow++;
        }
        instream.close();
        FileOutputStream out = new FileOutputStream(file.getAbsolutePath());
        libro.write(out);
        out.close();
        try {
            //definiendo la ruta en la propiedad file
            Runtime.getRuntime().exec("cmd /c start " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void modificarExcel(ArrayList<String[]> data, File file, int inirow) throws IOException, SQLException {

        File file2 = new File(global.path+"POLIZAS\\PolizaEmpty2.xls");
        FileInputStream instream = new FileInputStream(file);
        //Creamos una instancia de la clase HSSFWorkbook llamada libro
        HSSFWorkbook libro = new HSSFWorkbook(instream);

        //Creamos una instancia de la clase HSSFSheet llamada hoja y la creamos
        HSSFSheet hoja = libro.getSheetAt(0);

        //Creamos una instancia de la clase HSSFRow llamada fila y creamos la fila con el indice 0
        for (int i = 0; i < data.size(); i++) {
            HSSFRow fila = hoja.getRow(inirow);
            if (fila == null) {
                fila = hoja.createRow(inirow);
            }
            for (int j = 0; j < data.get(i).length; j++) {
                HSSFCell celda = fila.getCell(j);
                if (celda == null) {
                    celda = fila.createCell(j);
                }
                if (data.get(i)[j] != null) {
                    celda.setCellValue(new HSSFRichTextString(data.get(i)[j]));
                }

            }
            inirow++;
        }

        instream.close();
        FileOutputStream out = new FileOutputStream(file.getAbsolutePath());
        libro.write(out);
        out.close();

        try {
            //definiendo la ruta en la propiedad file

            Runtime.getRuntime().exec("cmd /c start " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
