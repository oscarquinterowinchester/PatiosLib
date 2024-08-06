/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package others;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JComboBox;
import basic.Config;
import basic.SendEmail;
import basic.WiderCombo;
import basic.global;
import basic.utils;
import basic.ComboBoxCellEditor;

/**
 *
 * @author admin
 */
public class FillCombo {
    public static void cargarContraRecibo(JComboBox combo, ArrayList list, String startwith,String ProveedorID) {
        String query = "SELECT ContraReciboID,ContraReciboID from contrarecibos_tbl where Status = true and ProveedorID="+ProveedorID;
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }

    }
    public static void cargarTiposReferencias(JComboBox combo, ArrayList list, String startwith) {

        String query = "SELECT nombre,id FROM tiporefs_tbl where status = true ";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }
    public static void cargarDirIntermedio(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT Nombre,LocacionID from locaciones_tbl where Status = true and Intermedio = true order by Nombre";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }

    }
    public static void cargarProyectos(JComboBox combo, ArrayList list, String startwith) {
        
        String query = "SELECT nombre, id from proyectoop_tbl where status = true order by nombre";
        combo.removeAllItems();
        list.clear();
        if (startwith == null) {
            list.add("0");
            combo.addItem("Todos");
            list.add("0");
            combo.addItem("No definido");
        } else {
            if (!startwith.isEmpty()) {
                list.add("0");
                combo.addItem(startwith);
            }
        }

        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }

        } catch (SQLException e) {
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
        /*String query = "SELECT nombre, id from proyectoop_tbl where status = true order by nombre";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }

        } catch (SQLException e) {
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }*/

    }
    
    public static void cargarProyectos(JComboBox combo, ArrayList list, String startwith, String clienteid) {
        String filtrocliente = "";
        if (!clienteid.equals("0")) {
            filtrocliente = " and clientefk = '" + clienteid + "' ";
        }
        String query = "SELECT nombre, id from proyectoop_tbl where status = true" + filtrocliente + " order by nombre";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }

        } catch (SQLException e) {
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }

    }
    
    public static void cargarTipoSalario(JComboBox combo, ArrayList list, String startwith) {

        String query = "SELECT tipos,id FROM tipossalarios_tbl where status = true "; //order by NComercial
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {                
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }
    
    public static void cargarFacturaDirecta(JComboBox combo, ArrayList list, String startwith, boolean mexicana) {

        String query = "SELECT NComercial,EmpresaID FROM empresas_tbl where Status = true and Mexicana = " + mexicana + " "; //order by NComercial
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {                
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }
    
    public static void cargarChoferesToken(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT ChoferID, Nombre from choferes_tbl where Status = true and DID != ''";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(1));
                combo.addItem(rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }

    }
    
    public static void cargarEmpresa(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT RazonSocial, EmpresaID from empresas_tbl where Status = true order by NComercial ";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con;
        con = utils.startConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));

            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }
    
    
    public static void cargarTiposUnidad(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT Nombre,TipoUID from tiposunidad_tbl where Status = true ";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }

    }
    
    public static void cargarTiposCamion(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT nombre, id from tiposunidadcamion_tbl where status = true ";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }

    }
    public static void cargarEstadoCarga(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT nombre, estadocr from estadoscarga_tbl where status = true"
                + " ";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }
      
    
    public static void cargarTiposParadas(JComboBox combo, ArrayList list, String startwith) {

        String query = "SELECT nombre,id FROM tipoparada_tbl where status = true ";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }
    
    public static void cargarTipoRentas(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT nombre, idtipor from tipomovrenta_tbl where status = true order by nombre";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }
    
    public static void cargarLocalidadesCP(JComboBox combo, ArrayList list, ArrayList listid, String startwith, String c_estado, String cp) {
        String filtroestado = "";

        if (!c_estado.equals("")) {
            filtroestado = " and c_estado = '" + c_estado + "' ";
        }
        //SELECT * FROM catalogossatdb.c_cp where cestado like 'DIF' and codigo = '06050'
        String query = "SELECT descripcion,codigo, localidadid FROM catalogossatdb.c_localidad where status = true " + filtroestado + " and codigo in (SELECT tcp.clocalidad FROM catalogossatdb.c_cp as tcp where tcp.cestado like '" + c_estado + "' and tcp.codigo = '" + cp + "') order by descripcion ";
        combo.removeAllItems();
        list.clear();
        listid.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("");
            listid.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                listid.add(rs.getString(3));
                combo.addItem(rs.getString(1));

            }
            
        } catch (SQLException e) {
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }
    
    public static void cargarMunicipioCP(JComboBox combo, ArrayList list, ArrayList listid, String startwith, String c_estado, String cp) {
        String filtroestado = "";

        if (!c_estado.equals("")) {
            filtroestado = " and c_estado = '" + c_estado + "' ";
        }

        String query = "SELECT descripcion,codigo, municipioid FROM catalogossatdb.c_municipio where status = true " + filtroestado + "and codigo in (SELECT tcp.cmunicipio FROM catalogossatdb.c_cp as tcp where tcp.cestado like '" + c_estado + "' and tcp.codigo = '" + cp + "') order by descripcion ";
        combo.removeAllItems();
        list.clear();
        listid.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("");
            listid.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                listid.add(rs.getString(3));
                combo.addItem(rs.getString(1));

            }
        } catch (SQLException e) {
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarMotivosCancela(JComboBox combo, ArrayList list, String startwith) {

        String query = "SELECT descripcion,codigo FROM catalogossatdb.c_motivocancela where status = true order by codigo ";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarCajaCamionSamsara(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT NoEconomico,CamionCajaSamID FROM camioncaja_samsara_tbl ORDER BY NoEconomico ";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);
        }
        Connection con;
        con = utils.startConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));

            }
            con.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    public static void cargarTipoTarifas(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT Nombre, TipoID from tipostarifapago_tbl where Status = true order by Nombre ";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }
    
    public static void cargarPerfilRevenue(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT nombre, id from perfilrevenue_tbl where status = true order by nombre ";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarSubtipoRemolque(JComboBox combo, ArrayList list, String startwith) {

        String query = "SELECT descripcion,codigo FROM catalogossatdb.c_subtiporem where status = true order by descripcion ";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));

            }

        } catch (SQLException e) {
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarConfiguracionAuto(JComboBox combo, ArrayList list, String startwith) {

        String query = "SELECT descripcion,codigo FROM catalogossatdb.c_configauto where status = true order by descripcion ";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));

            }
        } catch (SQLException e) {
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarSemanasNuevas(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT CONCAT(Semana,' - ',DATE_FORMAT(Fechaini,'%Y')), SemanaID "
                + "from semanas_tbl "
                + "where SemanaID >= IFNULL((SELECT SemanaID-1 from semanas_tbl where now() between Fechaini and Fechafin),1) ";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarEmpresas(JComboBox combo, ArrayList list, String startwith, boolean mexicana) {

        String query = "SELECT NComercial,EmpresaID FROM empresas_tbl where Status = true and Mexicana = " + mexicana + " order by NComercial ";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarEmpresas(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT NComercial, EmpresaID "
                + "from empresas_tbl where Status = true "
                + "order by NComercial"
                + " ";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarSCT(JComboBox combo, ArrayList list, String startwith) {
        String query = "select permisoid, CONCAT(codigo,' - ',descripcion) from catalogossatdb.c_tipopermiso where status = true";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);

        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(1));
                combo.addItem(rs.getString(2));

            }
        } catch (SQLException e) {
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }
    
    public static void cargarParteTransporte(JComboBox combo, ArrayList list, String startwith) {
        String query = "select codigo, descripcion from catalogossatdb.c_partetransporte where status = true";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("");
            combo.addItem(startwith);

        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(1));
                combo.addItem(rs.getString(2));

            }
        } catch (SQLException e) {
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }
    
    public static void cargarTiposFigura(JComboBox combo, ArrayList list, String startwith) {
        String query = "select codigo, descripcion from catalogossatdb.c_figuratransporte where status = true";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("");
            combo.addItem(startwith);

        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(1));
                combo.addItem(rs.getString(2));

            }
        } catch (SQLException e) {
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarTipoUnidad(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT Nombre,TipoUID FROM tiposunidad_tbl where Status = true order by Nombre ";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));

            }

        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarMunicipio(JComboBox combo, ArrayList list, ArrayList listid, String startwith, String c_estado) {
        String filtroestado = "";

        if (!c_estado.equals("")) {
            filtroestado = " and c_estado = '" + c_estado + "' ";
        }

        String query = "SELECT descripcion,codigo, municipioid FROM catalogossatdb.c_municipio where status = true " + filtroestado + " order by descripcion ";
        combo.removeAllItems();
        list.clear();
        listid.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("");
            listid.add("0");
        }
        Connection con;
        con = utils.startConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                listid.add(rs.getString(3));
                combo.addItem(rs.getString(1));

            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }
    public static void cargarHorario(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT nombre, horarioid FROM horario_tbl WHERE status=true";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con;
        con = utils.startConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                combo.addItem(rs.getString(1));
                list.add(rs.getString(2));
            }
            con.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    public static void cargarDepartamento(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT departamento,departamentoID FROM choferes_departamentos ";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con;
        con = utils.startConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                combo.addItem(rs.getString(1));
                list.add(rs.getString(2));
            }
            con.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    public static void cargarDepartamentoBuscador(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT departamento,departamentoID FROM choferes_departamentos ";
        combo.removeAllItems();
        list.clear();

        combo.addItem("Ver Todos");
        list.add("-1");

        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con;
        con = utils.startConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                combo.addItem(rs.getString(1));
                list.add(rs.getString(2));
            }
            con.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    public static void cargarColonias(JComboBox combo, ArrayList list, ArrayList listid, String startwith, String cp) {
        String filtrocp = "";

        if (!cp.equals("") && !cp.equals("0")) {
            filtrocp = " and CP = '" + cp + "' ";
        }
        String query = "SELECT descripcion,codigo, coloniaid FROM catalogossatdb.c_colonia where status = true " + filtrocp + " order by descripcion ";
        combo.removeAllItems();
        list.clear();
        listid.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("");
            listid.add("0");
        }
        Connection con;
        con = utils.startConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                listid.add(rs.getString(3));
                combo.addItem(rs.getString(1));

            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    public static void cargarSubTipoRem(JComboBox combo, ArrayList list, String startwith) {
        String query = "select subtipoid, CONCAT(codigo,' - ',descripcion) from c_subtiporem;";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);

        }
        Connection con;
        con = utils.startConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(1));
                combo.addItem(rs.getString(2));

            }
            con.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    public static void cargarLocalidades(JComboBox combo, ArrayList list, ArrayList listid, String startwith, String c_estado) {
        String filtroestado = "";

        if (!c_estado.equals("")) {
            filtroestado = " and c_estado = '" + c_estado + "' ";
        }

        String query = "SELECT descripcion,codigo, localidadid FROM catalogossatdb.c_localidad where status = true " + filtroestado + " order by descripcion ";
        System.out.println(query);
        combo.removeAllItems();
        list.clear();
        listid.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("");
            listid.add("0");
        }
        Connection con;
        con = utils.startConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                listid.add(rs.getString(3));
                combo.addItem(rs.getString(1));

            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    public static void cargarRutas(JComboBox combo, ArrayList list, String startwith, int tipotarifa, String clienteid) {
        String filtrocliente = "";
        if (tipotarifa == 1) {
            if (!clienteid.equals("0")) {
                filtrocliente = " and ClienteID = '" + clienteid + "' ";
            }
        }
        String query = "SELECT Nombre,RutaID FROM rutas_tbl where Status = true and TipoTarifa = '" + tipotarifa + "' " + filtrocliente + " order by Nombre ";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));

            }

        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarHorariosHora(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT Nombre,HorarioID FROM horarioshora_tbl where Status = true order by Nombre ";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));

            }

        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarMetodoPagoDiesel(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT CONCAT(Codigo,' ',Nombre) as nombre,MetodoID FROM metodospago_tbl where Status = true order by nombre ";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                combo.addItem(rs.getString(1));
                list.add(rs.getString(2));
            }

        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarCorreos(JComboBox combo, ArrayList list, String startwith) {
        String query = "select Correo,MailID from mailinfo_tbl";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                combo.addItem(rs.getString(1));
                list.add(rs.getString(2));
            }

        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarProveedoresCombustible(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT NComercial,ProveedorID FROM proveedores_tbl where Status = true and CCombustible = true order by NComercial ";//"SELECT Tipo_Hab FROM tarifa_tbl WHERE Grupo ='" + boxGrupo.getSelectedItem() + "' AND Tarifa = '" + boxTarifa.getSelectedItem() + "'";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                combo.addItem(rs.getString(1));
                list.add(rs.getString(2));
            }

        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarFSC(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT Nombre as nom,DefinicionID from definicionfsc_tbl where Status = true order by nom ";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }

    }
    
    public static void cargarFechasRequeridas(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT nombre as nom,id from tipofechasrequeridas_tbl where status = true order by nom ";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }

    }

    public static void cargarTablasCombustible(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT CONCAT(Nombre,' - ',(SELECT Nombre from monedas_tbl where tablaspreciocom_tbl.Moneda = monedas_tbl.Moneda) ) as nom,TablaPrecioID from tablaspreciocom_tbl where Status = true order by nom ";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }

    }

    public static void cargarTiposMovimiento(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT Nombre,TipoID from tiposmovimiento_tbl where Status = true order by Nombre ";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }

    }

    public static void cargarEvidencias(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT Nombre,EvidenciaID from evidencias_tbl where Status = true order by Nombre ";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }

    }

    public static void cargarEvidenciasCliente(JComboBox combo, ArrayList list, String startwith, String clienteid) {
        String query = "SELECT Nombre,EvidenciaID from evidencias_tbl where Status = true and IF(" + clienteid + " = 0, true, EvidenciaID in (SELECT evc.EvidenciaID from evidenciasclientes_tbl as evc where ClienteID = '" + clienteid + "' and evc.Status = true) ) order by Nombre ";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }

    }

    public static void cargarUnidadesNegocio(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT Nombre,UnidadID from unidadnegocio_tbl where Status = true order by Nombre ";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }

    }
    public static void cargarFuelGroup(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT Nombre,FuelGID from fuel_group_tbl where Status = true order by Nombre ";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }

    }
    public static void cargarNaviera(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT Nombre,NavieraID from navieras_tbl where Status = true ";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }

    }
    
    

    public static void cargarTipoOperacionOtros(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT Nombre,TipoID from tipooperacion_tbl where Status = true and Otros = true ";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }

    }

    public static void cargarTipoOperacion(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT Nombre,TipoID from tipooperacion_tbl where Status = true";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }

    }

    public static void cargarEstados(JComboBox combo, ArrayList list, ArrayList codigoestados, String startwith, String paisid) {
        String query = "SELECT Nombre,EstadoID, Abreviacion from estados_tbl where Status = true and PaisID = '" + paisid + "'";
        combo.removeAllItems();
        list.clear();
        codigoestados.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            codigoestados.add("");
            combo.addItem(startwith);
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                codigoestados.add(rs.getString(3));
                combo.addItem(rs.getString(3));

            }

        } catch (SQLException e) {
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }

    }
    
    public static void cargarPaises(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT Nombre,PaisID from paises_tbl where Status = true";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }
    
    public static void cargarPacs(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT nombre,id from pacs_tbl where status = true";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }
    
    public static void cargarEstadosAbreviacion(JComboBox combo, ArrayList list, String startwith, String paisid) {
        String query = "SELECT Abreviacion,EstadoID from estados_tbl where Status = true and PaisID = '" + paisid + "'";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }

    }
    public static void cargarPais(JComboBox combo, ArrayList list, String startwith)
    {
        String query = "SELECT Nombre,PaisID from paises_tbl where Status = true";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);
        }
        Connection con = utils.startConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
            con.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }
    public static void cargarEstados(JComboBox combo, ArrayList list, String startwith, String paisid) {
        String query = "SELECT Nombre,EstadoID from estados_tbl where Status = true and PaisID = '" + paisid + "'";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }

    }

    
    public static void cargarTarifaCliente(JComboBox combo, ArrayList list, String startwith,String ClienteID) {
        String query = "SELECT Nombre,TarifaCID from tarifasclientes_tbl where Status = true and ClienteID="+ClienteID;
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }

    }
    public static void cargarTarifaPago(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT Nombre,TarifaID from tarifaspago_tbl where Status = true";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }

    }

    public static void cargarTarifaCobro(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT Nombre,TarifaID from tarifas_tbl where Status = true";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }

    }

    public static void cargarTiposMov(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT Nombre,TipoID from tiposmov_tbl";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }

    }

    public static void cargarMonedas(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT Nombre,Moneda from monedas_tbl";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }

    }

    public static void cargarTamano(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT Tamano,TamanoID from tamanoremo_tbl where Status = true order by Tamano";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }

    }

    public static void cargarLocaciones(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT Nombre,LocacionID from locaciones_tbl where Status = true order by Nombre";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }

    }

    public static void cargarLocacionesPuntos(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT Nombre,LocacionID from locaciones_tbl where (Status = true or PuertoReservado = true or TrenReservado = true) order by Nombre";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }

    }

    public static void cargarStatusMovTren(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT Nombre,Status from statusmoviconttren_tbl where ShowItinerario = true";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }

    }

    public static void cargarStatusMov(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT Nombre,Status from statusmovicont_tbl where ShowItinerario = true";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }

    }

    public static void cargarUnidadesDif(String id, JComboBox combo, ArrayList list) {
        combo.removeAllItems();
        list.clear();
        //String idpro = utils.dbConsult("SELECT ProyectoID FROM proyectos_tbl Where Activo = true AND Nombre = '"+jTable1.getValueAt(jTable1.getSelectedRow(), 4)+"' and restring = 2");
        String query = "";
        if (id.equals("1")) {
            query = "SELECT NoEconomico, CamionID from camiones_tbl where Status = true order by CAST(NoEconomico as UNSIGNED)";
        }
        if (id.equals("2")) {
            query = "SELECT NoEconomico, CajaID from cajas_tbl where Status = true order by CAST(NoEconomico as UNSIGNED)";
        }
        if (id.equals("3")) {
            query = "SELECT NoEconomico, DollyID from dollys_tbl where Status = true order by CAST(NoEconomico as UNSIGNED)";
        }
        if (id.equals("4")) {
            query = "SELECT NoEconomico, GeneradorID from generadores_tbl where Status = true order by CAST(NoEconomico as UNSIGNED)";
        }
        if (id.equals("5")) {
            query = "SELECT Nombre, OficinaID from oficinacat_tbl where Status = true";
        }
        if (id.equals("6")) {
            query = "SELECT Nombre, AlmacenCatID from almacencat_tbl where Status = true";
        }
        if (id.equals("7")) {
            query = "SELECT Nombre, OtroID from otrocat_tbl where Status = true";
        }
        if (id.equals("8")) {
            query = "SELECT Nombre, almID from almaceninvetariocat_tbl where Status = true";
        }
        if (!query.isEmpty()) {
            Connection con = null;
            Statement state = null;
            ResultSet rs = null;
            try {
                con = utils.startConnection();
                state = con.createStatement();
                rs = state.executeQuery(query);
                while (rs.next()) {
                    combo.addItem(rs.getString(1));
                    list.add(rs.getString(2));
                }

            } catch (SQLException e) {
                utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
                System.out.println("Error " + e);
            } finally {
                utils.closeAllConnections(con, state, rs);
            }
        }
    }

    public static void cargarTipoRelacion(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT Nombre,Codigo from tiporelacioncfdi_tbl where Status = true";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                combo.addItem(rs.getString(1));
                list.add(rs.getString(2));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }

    }

    public static void cargarMetodoPagoSat(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT Nombre,Codigo FROM metodopagosat_tbl ";//"SELECT Tipo_Hab FROM tarifa_tbl WHERE Grupo ='" + boxGrupo.getSelectedItem() + "' AND Tarifa = '" + boxTarifa.getSelectedItem() + "'";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                combo.addItem(rs.getString(1));
                list.add(rs.getString(2));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarUsosCFDI(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT Nombre,Codigo  FROM usocfdi_tbl where Status = true";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                combo.addItem(rs.getString(1));
                list.add(rs.getString(2));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarTipoRemolqueRuta(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT Nombre, Tipo FROM tipocajas_tbl where OpcionRutas = true";//"SELECT Tipo_Hab FROM tarifa_tbl WHERE Grupo ='" + boxGrupo.getSelectedItem() + "' AND Tarifa = '" + boxTarifa.getSelectedItem() + "'";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                combo.addItem(rs.getString(1));
                list.add(rs.getString(2));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarTiposChoferes(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT Nombre, TipoC FROM tiposchoferes_tbl where Status = true";//"SELECT Tipo_Hab FROM tarifa_tbl WHERE Grupo ='" + boxGrupo.getSelectedItem() + "' AND Tarifa = '" + boxTarifa.getSelectedItem() + "'";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }

        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }

    }

    public static void cargarUnidadNegocio(JComboBox combo, ArrayList list, String startwith) {
        String query = "select concat(Nombre,' ~ ',Celula), UnidadID from unidadnegocio_tbl";//"SELECT Tipo_Hab FROM tarifa_tbl WHERE Grupo ='" + boxGrupo.getSelectedItem() + "' AND Tarifa = '" + boxTarifa.getSelectedItem() + "'";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                combo.addItem(rs.getString(1));
                list.add(rs.getString(2));
            }

        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarPatiosExcluyendo(JComboBox combo, ArrayList list, String startwith, String tipopatioexcluido) {
        String query = "SELECT Nombre, PatioID FROM patios_tbl where Status = true and TipoPatio != '" + tipopatioexcluido + "' order by Nombre ";//"SELECT Tipo_Hab FROM tarifa_tbl WHERE Grupo ='" + boxGrupo.getSelectedItem() + "' AND Tarifa = '" + boxTarifa.getSelectedItem() + "'";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                combo.addItem(rs.getString(1));
                list.add(rs.getString(2));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarPatios(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT CONCAT(codigo,' - ',Nombre) as Nombre, PatioID FROM patios_tbl where Status = true and Renta is not true order by Nombre ";//"SELECT Tipo_Hab FROM tarifa_tbl WHERE Grupo ='" + boxGrupo.getSelectedItem() + "' AND Tarifa = '" + boxTarifa.getSelectedItem() + "'";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);
            
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
                
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }
    
    public static void cargarPatiosVirtuales(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT CONCAT(codigo,' - ',Nombre) as Nombre, PatioID FROM patios_tbl where Status = true and vt is true and Renta is true order by Nombre ";//"SELECT Tipo_Hab FROM tarifa_tbl WHERE Grupo ='" + boxGrupo.getSelectedItem() + "' AND Tarifa = '" + boxTarifa.getSelectedItem() + "'";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);
            
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
                
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarPatiosExterno(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT CONCAT(codigo,' - ',Nombre) as Nombre, PatioID FROM patios_tbl where Status = true and MostrarExternos is true order by Nombre ";//"SELECT Tipo_Hab FROM tarifa_tbl WHERE Grupo ='" + boxGrupo.getSelectedItem() + "' AND Tarifa = '" + boxTarifa.getSelectedItem() + "'";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                combo.addItem(rs.getString(1));
                list.add(rs.getString(2));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarTipoICont(JComboBox combo, ArrayList list, String startwith) {

        String query = "SELECT Nombre,Tipo from tipoicont_tbl ";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarTipoTren(JComboBox combo, ArrayList list, String startwith) {

        String query = "SELECT Nombre,Tipo from tipotren_tbl ";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarConceptosCClientes(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT Nombre,CargoID from conceptoscargosclientes_tbl where Status = true order by Nombre";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));

            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }
    
    public static void cargarConceptosCClientes(JComboBox combo, ArrayList list, String startwith, String clienteid) {
        String query = "SELECT Nombre,CargoID from conceptoscargosclientes_tbl where Status = true and PaisID = (SELECT TipoFac+1 from clientes_tbl where ClienteID = '"+clienteid+"') order by Nombre";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));

            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }
    public static void cargarConceptosCargosChofer(JComboBox combo, ArrayList list, String ChoferID) {

        String query = "SELECT con.Nombre,con.CargoID from choferes_tbl cho  "
                + "LEFT JOIN defespecificaoperadores_tbl def on cho.TipoID=def.TipoFK "
                + "left join conceptoscargoschoferes_tbl con on def.CargoID=con.CargoID "
                + "where def.Status=true and cho.ChoferID="+ChoferID;
        combo.removeAllItems();
        list.clear();
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));

            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }
    public static void cargarConceptosCargos(JComboBox combo, ArrayList list, String startwith) {

        String query = "SELECT Nombre,CargoID from conceptoscargoschoferes_tbl where Status = true order by Nombre";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));

            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarConceptosComprobacion(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT Nombre,ID from conceptoscompro_tbl";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                combo.addItem(rs.getString(1));
                list.add(rs.getString(2));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarStatusViaticos(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT Nombre,Status from statusviaticos_tbl";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                combo.addItem(rs.getString(1));
                list.add(rs.getString(2));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarCategorias(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT Nombre,CategoriaID from categoriasdestino_tbl";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                combo.addItem(rs.getString(1));
                list.add(rs.getString(2));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }
    public static void cargarArticulos(JComboBox combo, ArrayList list, String startwith){
        String query = "SELECT descripcion, ArticuloID FROM articulos_tbl where Status = true and carriersexternos=true order by descripcion ";//"SELECT Tipo_Hab FROM tarifa_tbl WHERE Grupo ='" + boxGrupo.getSelectedItem() + "' AND Tarifa = '" + boxTarifa.getSelectedItem() + "'";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                combo.addItem(rs.getString(1));
                list.add(rs.getString(2));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }
    public static void cargarProveedores(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT NComercial,ProveedorID FROM proveedores_tbl where Status = true order by NComercial ";//"SELECT Tipo_Hab FROM tarifa_tbl WHERE Grupo ='" + boxGrupo.getSelectedItem() + "' AND Tarifa = '" + boxTarifa.getSelectedItem() + "'";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                combo.addItem(rs.getString(1));
                list.add(rs.getString(2));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarTipoRemolque(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT Nombre,Tipo  FROM tipocajas_tbl order by Tipo";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                combo.addItem(rs.getString(1));
                list.add(rs.getString(2));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarTurnos(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT Nombre,TurnoID  FROM turnos_tbl where Status = true";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                combo.addItem(rs.getString(1));
                list.add(rs.getString(2));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarCuentaContable(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT Cuenta,CuentaID FROM cuentascontables_tbl where Status = true ";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                combo.addItem(rs.getString(1));
                list.add(rs.getString(2));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarFamilia(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT familia,familiaID FROM articulos_tbl_familias ";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                combo.addItem(rs.getString(1));
                list.add(rs.getString(2));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarMetodoPago(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT CONCAT(Codigo,' ',Nombre),Codigo FROM metodospago_tbl where Status = true ";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                combo.addItem(rs.getString(1));
                list.add(rs.getString(2));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarSemanas(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT CONCAT(Semana,' - ', Anio),SemanaID  FROM semanas_tbl";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                combo.addItem(rs.getString(1));
                list.add(rs.getString(2));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarTipoPago(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT Nombre,TipoID  FROM tiposmov_tbl";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                combo.addItem(rs.getString(1));
                list.add(rs.getString(2));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarPeriodos(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT Nombre,PeriodoID FROM periodos_tbl";//"SELECT Tipo_Hab FROM tarifa_tbl WHERE Grupo ='" + boxGrupo.getSelectedItem() + "' AND Tarifa = '" + boxTarifa.getSelectedItem() + "'";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                combo.addItem(rs.getString(1));
                list.add(rs.getString(2));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarGeneradores(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT NoEconomico,GeneradorID "
                + "from generadores_tbl where Status = true order by CAST(NoEconomico as UNSIGNED)";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                combo.addItem(rs.getString(1));
                list.add(rs.getString(2));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarDollys(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT NoEconomico,DollyID "
                + "from dollys_tbl where Status = true order by CAST(NoEconomico as UNSIGNED)";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                combo.addItem(rs.getString(1));
                list.add(rs.getString(2));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarCajas(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT NoEconomico,CajaID "
                + "from cajas_tbl where Status = true order by CAST(NoEconomico as UNSIGNED)";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                combo.addItem(rs.getString(1));
                list.add(rs.getString(2));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarCamiones(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT NoEconomico as camion,CamionID "
                + "from camiones_tbl where Status = true order by CAST(NoEconomico as UNSIGNED)";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                combo.addItem(rs.getString(1));
                list.add(rs.getString(2));
            }

        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }

    }
    
    public static void cargarCamionesPatio(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT NoEconomico as camion,CamionID "
                + "from camiones_tbl order by CAST(NoEconomico as UNSIGNED)";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                combo.addItem(rs.getString(1));
                list.add(rs.getString(2));
            }

        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }

    }

    public static void cargarGrupoClientes (JComboBox combo, ArrayList list, String startwith){
        
        String query = "SELECT NombreComun,ClienteID from clientes_tbl where Status = true group by NombreComun";
        
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }
    
    public static void cargarClientesBillTo(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT NComercial,ClienteID from clientes_tbl where Status = true and Inactivo = false and "
                + "3 in (SELECT TipoID from reltipoc_tbl where reltipoc_tbl.ClienteID = clientes_tbl.ClienteID and Status = true)  order by NComercial";
//        
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarClientesBillToGrupo(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT NombreComun,GROUP_CONCAT(ClienteID) from clientes_tbl where Status = true and Inactivo = false and "
                + "3 in (SELECT TipoID from reltipoc_tbl where reltipoc_tbl.ClienteID = clientes_tbl.ClienteID and Status = true) group by NombreComun order by NComercial";
//        
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarClientesBillToTodos(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT NComercial,ClienteID from clientes_tbl where ((Status = true and Inactivo = false) or Inactivo = true) and "
                + "3 in (SELECT TipoID from reltipoc_tbl where reltipoc_tbl.ClienteID = clientes_tbl.ClienteID and Status = true)  order by NComercial";
//        
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarClientesProveedorEquipo(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT NComercial,ClienteID from clientes_tbl where Status = true and 5 in (SELECT TipoID from reltipoc_tbl where reltipoc_tbl.ClienteID = clientes_tbl.ClienteID and Status = true)  order by NComercial";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);

        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));

            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }
    
    public static void cargarClientesProveedorEquipos(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT NComercial,ClienteID from clientes_tbl where Status = true and equipmentprovider is true  order by NComercial";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);

        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));

            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }
    

    public static void cargarClientesCons(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT NComercial,ClienteID from clientes_tbl where Status = true and 2 in (SELECT TipoID from reltipoc_tbl where reltipoc_tbl.ClienteID = clientes_tbl.ClienteID and Status = true)  order by NComercial";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);

        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));

            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarClientes(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT NComercial,ClienteID from clientes_tbl where Status = true and Equipmentprovider is not true order by NComercial";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);

        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));

            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }
    
    public static void cargarVendedores(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT choferes_tbl.Nombre, ChoferID from choferes_tbl "
                + "inner join puestos_tbl on puestos_tbl.PuestoID = choferes_tbl.Puesto "
                + "where puestos_tbl.Vendedor = true";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);

        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));

            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarUsuarios(JComboBox combo, ArrayList list, String startwith) {
        String usuariouno = " and UsuarioID != 1 ";
        if (global.usuario == 1) {
            usuariouno = "";
        }
        String query = "SELECT Nombre, UsuarioID from usuarios_tbl where Status = true " + usuariouno + " order by Nombre";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);

        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));

            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarClientesInternos(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT NComercial,ClienteID from clientes_tbl where Status = true and EmpresaFK > 0 order by NComercial";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);

        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));

            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarClientesExternos(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT NComercial,ClienteID from clientes_tbl where Status = true order by NComercial"; //and MostrarExternos is true 
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("0");
            combo.addItem(startwith);

        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(2));
                combo.addItem(rs.getString(1));

            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

//    public static void cargarChoferesporTipo(JComboBox combo, ArrayList list, String tipo, String startwith) {
//        String filtrochofer = "";
//        if(!tipo.equals("0")){
//            filtrochofer = "and TipoOperador = '"+tipo+"'"; 
//        }
//        
//        String query = "SELECT Nombre as nom,ChoferID FROM choferes_tbl where Status = true and Puesto = 1 "+filtrochofer+" and Detener = false order by nom";//"SELECT Tipo_Hab FROM tarifa_tbl WHERE Grupo ='" + boxGrupo.getSelectedItem() + "' AND Tarifa = '" + boxTarifa.getSelectedItem() + "'";
//        System.out.println("query = " + query);
//        combo.removeAllItems();
//        list.clear();
//        if (!startwith.isEmpty()) {
//            combo.addItem(startwith);
//            list.add("0");
//        }
//        Connection con;
//        con = utils.startConnection();
//        try {
//            Statement statement = con.createStatement();
//            ResultSet rs = statement.executeQuery(query);
//            while (rs.next()) {
//                combo.addItem(rs.getString(1));
//                list.add(rs.getString(2));
//            }
//
//            
//        } catch (SQLException e) {
//            System.out.println("Error " + e);
//        }
//    }
    public static void cargarChoferesForaneo(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT Nombre as nom,ChoferID FROM choferes_tbl where Status = true and Puesto = 1 and TipoChofer = 1 and Detener = false order by nom";//"SELECT Tipo_Hab FROM tarifa_tbl WHERE Grupo ='" + boxGrupo.getSelectedItem() + "' AND Tarifa = '" + boxTarifa.getSelectedItem() + "'";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                combo.addItem(rs.getString(1));
                list.add(rs.getString(2));
            }

        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarChoferesLocal(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT Nombre as nom,ChoferID FROM choferes_tbl where Status = true and Puesto = 1 and TipoChofer != 1 and Detener = false order by nom";//"SELECT Tipo_Hab FROM tarifa_tbl WHERE Grupo ='" + boxGrupo.getSelectedItem() + "' AND Tarifa = '" + boxTarifa.getSelectedItem() + "'";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                combo.addItem(rs.getString(1));
                list.add(rs.getString(2));
            }

        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }
public static void cargarChoferes(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT CONCAT(NoEmpleado, ' ', Nombre) as nom,ChoferID "
                + "FROM choferes_tbl "
                + "where Status = true and Puesto = 1 and Detener = false "
                + "order by CAST(NoEmpleado as UNSIGNED), Nombre";//"SELECT Tipo_Hab FROM tarifa_tbl WHERE Grupo ='" + boxGrupo.getSelectedItem() + "' AND Tarifa = '" + boxTarifa.getSelectedItem() + "'";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                combo.addItem(rs.getString(1));
                list.add(rs.getString(2));
            }

        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }
    
    public static void cargarChoferesSinNoEmpleado(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT  Nombre as nom,ChoferID "
                + "FROM choferes_tbl "
                + "where Status = true and Puesto = 1 and Detener = false "
                + "order by CAST(NoEmpleado as UNSIGNED), Nombre";//"SELECT Tipo_Hab FROM tarifa_tbl WHERE Grupo ='" + boxGrupo.getSelectedItem() + "' AND Tarifa = '" + boxTarifa.getSelectedItem() + "'";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                combo.addItem(rs.getString(1));
                list.add(rs.getString(2));
            }

        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }
    
    public static void cargarChofer(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT Nombre ,ChoferID FROM choferes_tbl where Status = true order by Nombre"; //and Puesto = 1 and Detener = false 
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                combo.addItem(rs.getString(1));
                list.add(rs.getString(2));
            }

        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarDescripcionArticulos(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT Descripcion,ArticuloID from articulos_tbl where Status = true order by Descripcion";//"SELECT Tipo_Hab FROM tarifa_tbl WHERE Grupo ='" + boxGrupo.getSelectedItem() + "' AND Tarifa = '" + boxTarifa.getSelectedItem() + "'";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {

            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                combo.addItem(rs.getString(1));
                list.add(rs.getString(2));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarCodigoArticulos(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT Codigo,ArticuloID FROM articulos_tbl where Status = true order by Codigo";//"SELECT Tipo_Hab FROM tarifa_tbl WHERE Grupo ='" + boxGrupo.getSelectedItem() + "' AND Tarifa = '" + boxTarifa.getSelectedItem() + "'";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {

            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                combo.addItem(rs.getString(1));
                list.add(rs.getString(2));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarAlmacenes(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT Nombre,AlmacenID FROM almacenes_tbl where Status = true";//"SELECT Tipo_Hab FROM tarifa_tbl WHERE Grupo ='" + boxGrupo.getSelectedItem() + "' AND Tarifa = '" + boxTarifa.getSelectedItem() + "'";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {

            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                combo.addItem(rs.getString(1));
                list.add(rs.getString(2));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarGastos(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT Nombre,GastoID FROM gasto_tbl ";//"SELECT Tipo_Hab FROM tarifa_tbl WHERE Grupo ='" + boxGrupo.getSelectedItem() + "' AND Tarifa = '" + boxTarifa.getSelectedItem() + "'";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {

            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                combo.addItem(rs.getString(1));
                list.add(rs.getString(2));
            }

        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarCuentas(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT CONCAT(Nombre,' ',Moneda,' - ',Cuenta), BancoID FROM bancos_tbl ";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            combo.addItem(startwith);
            list.add("0");
        }
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                combo.addItem(rs.getString(1));
                list.add(rs.getString(2));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }

    }

    public static void cargarFormaPago(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT Nombre,TipoID from tiposmov_tbl order by TipoID";
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            combo.removeAllItems();
            list.clear();
            if (!startwith.isEmpty()) {
                combo.addItem(startwith);
                list.add("0");
            }
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {

                combo.addItem(rs.getString(1));
                list.add(rs.getString(2));
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargarCombo(JComboBox combo, ArrayList list, String query, String startwith) {                
        if (!query.replace(" ", "").replace("\n", "").isEmpty()) {
            Connection con = null;
            Statement state = null;
            ResultSet rs = null;
            try {
                combo.removeAllItems();
                list.clear();
                if (!startwith.isEmpty()) {
                    combo.addItem(startwith);
                    list.add("0");
                }
                con = utils.startConnection();
                state = con.createStatement();
                rs = state.executeQuery(query);
                while (rs.next()) {
                    combo.addItem(rs.getString(1));
                    list.add(rs.getString(2));
                }
            } catch (SQLException e) {
                utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
                System.out.println("Error " + e);
            } finally {
                utils.closeAllConnections(con, state, rs);
            }
        }
    }
    
    public static void cargarDespachadores(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT Nombre, UsuarioID from usuarios_tbl "
                + "where Status = true";
        combo.removeAllItems();
        list.clear();
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            if (!startwith.isEmpty()) {
                combo.addItem(startwith);
                list.add("0");
            }
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {

                combo.addItem(rs.getString(1));
                list.add(rs.getString(2));
                if(Integer.parseInt(rs.getString(2)) == global.usuario){
                    combo.setSelectedIndex(list.size()- 1);
                }
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cargaTipoContactos(JComboBox combo, ArrayList list, String startwith) {
        String query = "select nombre, tipoID from tipocontacto_tbl where Status = 1";
        combo.removeAllItems();
        list.clear();
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            if (!startwith.isEmpty()) {
                combo.addItem(startwith);
                list.add("0");
            }
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {

                combo.addItem(rs.getString(1));
                list.add(rs.getString(2));
                if(Integer.parseInt(rs.getString(2)) == global.usuario){
                    combo.setSelectedIndex(list.size()- 1);
                }
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

      public static void cargarTipoAsistencia(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT nombre, tipoasistid from tipoasistencia_tbl "
                + "where Status = true";
        combo.removeAllItems();
        list.clear();
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            if (!startwith.isEmpty()) {
                combo.addItem(startwith);
                list.add("0");
            }
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {

                combo.addItem(rs.getString(1));
                list.add(rs.getString(2));
                if (Integer.parseInt(rs.getString(2)) == global.usuario) {
                    combo.setSelectedIndex(list.size() - 1);
                }
            }
        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }
    
}
