/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package others;

import java.io.File;
import java.io.PrintWriter;
import basic.Config;
import basic.SendEmail;
import basic.WiderCombo;
import basic.global;
import basic.utils;
import basic.ComboBoxCellEditor;
import basic.FillCombo;

/**
 *
 * @author admin
 */
public class GenTxt {

    public static String Generate(String name, String text) {
        PrintWriter writer = null;
        String msj = "";
        try {
            if (!new File(global.path + "bodys").exists()) {
                new File(global.path + "bodys").mkdir();
            }
            writer = new PrintWriter(name, "UTF-8");
            String splited[] = text.split("\n");
            for (int i = 0; i < splited.length; i++) {
                writer.println(splited[i]);
            }
        } catch (Exception e) {
            msj = e.toString();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
        return msj;
    }
}
