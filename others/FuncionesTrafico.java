/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package others;

import com.alee.utils.FileUtils;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import basic.Config;
import basic.SendEmail;
import basic.WiderCombo;
import basic.global;
import basic.utils;
import basic.ComboBoxCellEditor;
import basic.FillCombo;
/**
 *
 * @author nebur
 */
public class FuncionesTrafico {

    public static boolean revisarFacturaPendiente(String wcontid) {
        if (utils.dbConsult("SELECT IFNULL((SELECT WContenedorID from workcontenedores_tbl where StatusAdmin >=3 and workcontenedores_tbl.Status = true and WContenedorID = '" + wcontid + "' limit 1),0)").equals("0")) {
            return true;
        }
        return false;
    }
//    public static void copiarRutaFac(String rutaid) {
//        String query = "SELECT "
//                + "ccpcruce,"
//                + "cusrefunica,"
//                + "cusrefcierre "
//                + "from gconfigs "
//                + "where id = 1";
//        Connection con = null;
//        Statement state = null;
//        ResultSet rs = null;
//        try {
//            con = utils.startConnection();
//            state = con.createStatement();
//            rs = state.executeQuery(query);
//            String campos = "clientefk", valores = cliid;
//            while (rs.next()) {
//                ResultSetMetaData metaData = rs.getMetaData();
//                int columnCount = metaData.getColumnCount();
//
//                // Recorre todas las columnas del ResultSet e imprime sus nombres
//                for (int i = 1; i <= columnCount; i++) {
////                    if (i == 1) {
////                        campos = metaData.getColumnName(i);
////                        valores = rs.getString(i);
////                    } else {
//                    campos = campos + ", " + metaData.getColumnName(i);
//                    valores = valores + ", " + rs.getString(i);
////                    }
//                }
//            }
//            utils.dbInsert("INSERT INTO configcliente(" + campos + ") VALUES(" + valores + ")");
//        } catch (SQLException e) {
//            System.out.println("Error " + e);
//        } finally {
//            utils.closeAllConnections(con, state, rs);
//        }
//    }

    public static boolean woConRenta(String wcid) {//se tiene que considerar si ya estaba marcado la renta y si la orden no esta cerrada ya, porque se necesita para proceder con el 
        if (!utils.dbConsult("SELECT IFNULL((SELECT id "
                + "from rentaunidades_tbl "
                + "where "
                + "proyectofk = (SELECT proyectoid from workcontenedores_tbl as wc left join rutas_tbl on rutas_tbl.RutaID = wc.RutaID where WContenedorID = '" + wcid + "') and "
                + "clientefk = (SELECT wc.ClienteFK from workcontenedores_tbl as wc where WContenedorID = '" + wcid + "') and "
                + "Fecha = (SELECT DATE(AppoimentDate) from workcontenedores_tbl as wc where WContenedorID = '" + wcid + "') and "
                + "IF((SELECT esrenta from workcontenedores_tbl where WContenedorID = '" + wcid + "') = true, Status >=1 ,Status = 1) limit 1),0)").equals("0")) {
            return true;
        }

        return false;
    }

//    public static void revisarRenta(String wcid, String itiid) {
//        if (utils.dbConsult("SELECT IF(esrenta, 1, 0) from workcontenedores_tbl where WContenedorID = '" + wcid + "'").equals("1")) {
//            String rentaid = "0";
//            String camionid = utils.dbConsult("SELECT IFNULL((SELECT CamionID from itinerarios_tbl where ItinerarioID = '" + itiid + "'),0)");
////                System.out.println("camionid = " + camionid);
//            String cargadt = utils.dbConsult("SELECT IFNULL(AppoimentDate,'') from workcontenedores_tbl where WContenedorID = '" + wcid + "'");
////                System.out.println("cargadt = " + cargadt);
//            String cargad = utils.dbConsult("SELECT IFNULL(DATE(AppoimentDate),'') from workcontenedores_tbl where WContenedorID = '" + wcid + "'");
////                System.out.println("cargad = " + cargad);
//            if (!camionid.equals("0") && !cargadt.isEmpty()) {
//                rentaid = utils.dbConsult("SELECT IFNULL((SELECT id from rentaunidades_tbl as rentau where rentau.CamionID = '" + camionid + "' and rentau.proyectofk = (SELECT proyectoid from rutas_tbl left join workcontenedores_tbl on rutas_tbl.RutaID = workcontenedores_tbl.RutaID where WContenedorID = '" + wcid + "') and rentau.clientefk = (SELECT wc.ClienteFK from workcontenedores_tbl as wc where wc.WContenedorID = '" + wcid + "') and rentau.Status >= 1 and Fecha = '" + cargad + "' limit 1),0)");
//            }
////                System.out.println("rentaid = " + rentaid);
//            utils.dbUpdate("UPDATE workcontenedores_tbl SET rentaid = '" + rentaid + "', rentaextra = IF('" + rentaid + "' = 0, true, IFNULL((SELECT false from rentaunidades_tbl where '" + cargadt + "' >= FechaICamion and '" + cargadt + "' <= FechaFCamion and id = '" + rentaid + "' limit 1), true) )  where WContenedorID = '" + wcid + "'");
//        }
//    }
    //AppoimentDate
    public static String seleccionCamion(String itiid, String wcontid, int tiposel, String camid, String opid) {
        String resp = "";
        if (!itiid.equals("0")) {
            if (!camid.equals("0") || !opid.equals("0")) {
                if (utils.validarServicio(itiid, camid, utils.dbConsult("SELECT IFNULL(Carga,'') from itinerarios_tbl where ItinerarioID = '" + itiid + "'"), utils.dbConsult("SELECT IFNULL((SELECT LocacionPUID from rutas_tbl left join itinerarios_tbl on rutas_tbl.RutaID = itinerarios_tbl.ItinerarioID where ItinerarioID = '" + itiid + "'),0)"), null, opid)) {
                    String camionselid = "0", choferselid = "0", camionup = "", choferup = "";
                    if (tiposel == 0) {
                        camionselid = camid;
                        choferselid = opid;
                        camionup = ", CamionID = '" + camionselid + "' ";
                        choferup = ", ChoferID = '" + choferselid + "', MonedaChofer = (SELECT rutas_tbl.MonedaChofer from rutas_tbl where rutas_tbl.RutaID = itinerarios_tbl.RutaID) ";
                    }
                    if (tiposel == 1) {
                        choferselid = opid;
                        choferup = ", ChoferID = '" + choferselid + "', MonedaChofer = (SELECT rutas_tbl.MonedaChofer from rutas_tbl where rutas_tbl.RutaID = itinerarios_tbl.RutaID) ";
                    }
                    if (tiposel == 2) {
                        camionselid = camid;
                        camionup = ", CamionID = '" + camionselid + "' ";
                    }

                    String up = utils.dbUpdate("UPDATE itinerarios_tbl SET Fuente = Fuente " + camionup + " " + choferup + " where ItinerarioID = '" + itiid + "'");

                    if (up.isEmpty()) {
                        utils.dbUpdate("UPDATE itinerarios_tbl SET PagoSV = getPagoRutaDin(ItinerarioID), MonedaChofer = getMonedaPagoRutaDin(ItinerarioID) where ItinerarioID = '" + itiid + "'");
//                        FuncionesTrafico.revisarRenta(wcontid, itiid);
                        //                        if (!utils.dbConsult("Select ChoferSecundarioID from itinerarios_tbl where ItinerarioID = " + itiid).equals("0")
                        //                                && tiposel < 2) {
                        //                            FuncionesTrafico.removerChoferSecOnlyIt(itiid + "");
                        //                            FuncionesTrafico.insertarChoferSecOnlyIt(itiid + "");
                        //                        }
                    } else {
                        resp = up.toString();
                    }
                }
            } else {
                String up = utils.dbUpdate("UPDATE itinerarios_tbl SET CamionID = '0', ChoferID = '0', PagoSV = 0 where ItinerarioID = '" + itiid + "'");
                if (up.isEmpty()) {

                } else {
                    resp = up.toString();
                }
            }
        } else {
            if (!camid.equals("0") || !opid.equals("0")) {
                String camionselid = "0", choferselid = "0", camionup = "", choferup = "";
                if (tiposel == 0) {
                    camionselid = camid;
                    choferselid = opid;
                    camionup = ", camionfk = '" + camionselid + "' ";
                    choferup = ", choferfk = '" + choferselid + "' ";
                }
                if (tiposel == 1) {
                    choferselid = opid;
                    choferup = ", choferfk = '" + choferselid + "' ";
                }
                if (tiposel == 2) {
                    camionselid = camid;
                    camionup = ", camionfk = '" + camionselid + "' ";
                }
                String up = utils.dbUpdate("UPDATE workcontenedores_tbl SET StatusAdmin = StatusAdmin " + camionup + " " + choferup + " where WContenedorID = '" + wcontid + "'");
                if (up.isEmpty()) {

                } else {
                    resp = up.toString();
                }
            } else {
                String up = utils.dbUpdate("UPDATE workcontenedores_tbl SET camionfk = '0', choferfk = '0' where WContenedorID = '" + wcontid + "'");
                if (up.isEmpty()) {

                } else {
                    resp = up.toString();
                }
            }
        }
        return resp;
    }

    public static String obtenerRutaOperativa(String wcid) {
        String rutafac = utils.dbConsult("SELECT RutaID from workcontenedores_tbl where WContenedorID = '" + wcid + "'");
        String rutaop = utils.dbConsult("SELECT IFNULL((SELECT RutaID from rutas_tbl where TipoTarifa = 2 and Status = true and LocacionPUID = (SELECT rut.LocacionPUID from rutas_tbl as rut where rut.RutaID = '" + rutafac + "') and LocacionTOID = (SELECT rut.LocacionTOID from rutas_tbl as rut where rut.RutaID = '" + rutafac + "') and LocacionintID = (SELECT rut.LocacionintID from rutas_tbl as rut where rut.RutaID = '" + rutafac + "') limit 1),0)");
        return rutaop;//utils.dbConsult("SELECT IFNULL((SELECT RutaID from rutas_tbl where TipoTarifa = 2 and Status = true and LocacionPUID = (SELECT rut.LocacionPUID from rutas_tbl as rut where rut.RutaID = '" + rutafac + "') and LocacionTOID = (SELECT rut.LocacionTOID from rutas_tbl as rut where rut.RutaID = '" + rutafac + "') limit 1),0)")
    }

    public static void cargarInspeccion(String id) {
        String query = "SELECT ListadoID, Nombre,true from listadoinspecciones_tbl where Status = true";
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                utils.dbInsert("INSERT INTO inspecciones_tbl (ItinerarioID,ListadoID,Fecha,Bien) VALUES('" + id + "','" + rs.getString(1) + "',(now()),true)");
            }
        } catch (SQLException e) {
            utils.errorGenerado("ProgramacionGeneral / cargarInspeccion / sqlex = " + e.toString());
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void revisarCostoParadaExtra(String itiid, String rutaid) {
        String accid = utils.dbConsult("SELECT accesorioparadaid from rutas_tbl where RutaID = '" + rutaid + "'");
        String up = utils.dbUpdate("UPDATE cargosclientes_tbl SET Status = false where ItinerarioID = '" + itiid + "' and paradaextra = true");
        if (!accid.equals("0")) {
            String mismomovwo = utils.dbConsult("SELECT IFNULL((SELECT WContenedorID from workcontenedores_tbl as wc where WContenedorID = (SELECT WContFK from itinerarios_tbl where ItinerarioID = '" + itiid + "') and (SELECT LocacionPUID from rutas_tbl where rutas_tbl.RutaID = wc.RutaID) = (SELECT LocacionPUID from rutas_tbl where rutas_tbl.RutaID = '" + rutaid + "') and (SELECT LocacionTOID from rutas_tbl where rutas_tbl.RutaID = wc.RutaID) = (SELECT LocacionTOID from rutas_tbl where rutas_tbl.RutaID = '" + rutaid + "') and (SELECT LocacionintID from rutas_tbl where rutas_tbl.RutaID = wc.RutaID) = (SELECT LocacionintID from rutas_tbl where rutas_tbl.RutaID = '" + rutaid + "') limit 1), 0)");
            if (mismomovwo.equals("0")) {
                String clientefk = utils.dbConsult("SELECT clientefk from workcontenedores_tbl where WContenedorID = (SELECT WContFK from itinerarios_tbl where ItinerarioID = '" + itiid + "')");
                String proyectoid = utils.dbConsult("SELECT proyectoid from workcontenedores_tbl left join rutas_tbl on rutas_tbl.RutaID = workcontenedores_tbl.RutaID where WContenedorID = (SELECT WContFK from itinerarios_tbl where ItinerarioID = '" + itiid + "')");
                String importe = utils.dbConsult("SELECT IFNULL((SELECT IF((SELECT EspecificoID from cccespecifico_tbl as ccce where CargoFK = ccc.CargoID and ClienteFK = '" + clientefk + "' and proyectofk = '" + proyectoid + "' and ccce.Status = true limit 1) is null, ImporteBase, (SELECT ccce.Importe from cccespecifico_tbl as ccce where CargoFK = ccc.CargoID and ClienteFK = '" + clientefk + "' and proyectofk = '" + proyectoid + "' and ccce.Status = true limit 1)) from conceptoscargosclientes_tbl as ccc where CargoID = '" + accid + "'),0)");
                if (utils.dbConsult("SELECT IF(" + importe + " > 0 , 1, 0)").equals("1")) {
                    String id = utils.dbInsert("INSERT INTO cargosclientes_tbl(WcontID, ItinerarioID, CClienteID,Concepto, Observaciones,Importe,Cantidad,Moneda,UsuarioID, TipoCobro,Fecha,ConceptoSAT, UnidadSAT,IVAc,IVARETc,ISRc, paradaextra) "
                            + "VALUES("
                            + "(SELECT WContFK from itinerarios_tbl where ItinerarioID = '" + itiid + "'),"
                            + "'" + itiid + "', "
                            + "'" + accid + "', "
                            + "(SELECT Nombre from conceptoscargosclientes_tbl where CargoID = '" + accid + "'), "
                            + "(SELECT rutas_tbl.Nombre from itinerarios_tbl left join rutas_tbl on itinerarios_tbl.RutaID = rutas_tbl.RutaID where ItinerarioID = '" + itiid + "'), "
                            + "'" + importe + "', "
                            + "'1', "
                            + "IFNULL((SELECT MonedaBase from conceptoscargosclientes_tbl as ccc where ccc.CargoID = '" + accid + "'),1)"
                            + ",'" + global.usuario + "','0',(now()), "
                            + "IFNULL((SELECT ConceptoSATcc from conceptoscargosclientes_tbl as ccc where ccc.CargoID = '" + accid + "'),''),"
                            + "IFNULL((SELECT UnidadSATcc from conceptoscargosclientes_tbl as ccc where ccc.CargoID = '" + accid + "'),''), "
                            + "IFNULL((SELECT IVA from conceptoscargosclientes_tbl as ccc where ccc.CargoID = '" + accid + "'),0), "
                            + "IFNULL((SELECT IVARET from conceptoscargosclientes_tbl as ccc where ccc.CargoID = '" + accid + "'),0), "
                            + "IFNULL((SELECT ISR from conceptoscargosclientes_tbl as ccc where ccc.CargoID = '" + accid + "'),0), true )");
                    if (id.length() > 11) {
                        utils.errorGenerado("FuncionesTrafico / revisarCostoParadaExtra / insert = " + id);
                    }
                }
            }
        }
    }

    public static void insertarChoferSecOnlyIt(String ItinerarioID) {
        // si es float round 2, no redondea 0.005->0.00 . Si es Decimal redondea 0.005->0.01
        String Pago = utils.dbConsult("SELECT round(PagoSV,2) FROM itinerarios_tbl WHERE ItinerarioID=" + ItinerarioID);
        String ChoferID = utils.dbConsult("SELECT ChoferSecundarioID FROM itinerarios_tbl WHERE ItinerarioID=" + ItinerarioID);
        utils.dbUpdate("UPDATE itinerarios_tbl SET PagoSV=" + Pago + " WHERE ItinerarioID=" + ItinerarioID);
        String Moneda = utils.dbConsult("SELECT MonedaChofer FROM itinerarios_tbl WHERE ItinerarioID=" + ItinerarioID);
        String SemanaID = utils.dbConsult("SELECT "
                + "ifnull((SELECT SemanaID FROM semanas_tbl WHERE itinerarios_tbl.Carga between Fechaini and Fechafin),0) SemanaID "
                + "FROM itinerarios_tbl WHERE ItinerarioID=" + ItinerarioID);
        utils.dbInsert("INSERT INTO bonospersonales_tbl (Importe,ChoferID,Moneda,Concepto,UsuarioID,ItinerarioFK,SemanaID) "
                + "VALUES(" + Pago + "," + ChoferID + "," + Moneda + ","
                + "'DM: " + ItinerarioID + ","
                + " WO:" + utils.dbConsult("SELECT GROUP_CONCAT(DISTINCT WContID) from icont_tbl where ItinerarioID = '" + ItinerarioID + "' and Status = true") + ", "
                + "From-To:" + utils.dbConsult("SELECT (SELECT Nombre from rutas_tbl where rutas_tbl.RutaID = itinerarios_tbl.RutaID) from itinerarios_tbl where ItinerarioID = '" + ItinerarioID + "'") + "',"
                + "" + global.usuario + "," + ItinerarioID + "," + SemanaID + ")");
    }

    public static void cargarRefs(String wcid, DefaultTableModel model, ArrayList<String> list) {
        list.clear();
        model.setRowCount(0);
        String query = "SELECT id,"
                + "(SELECT nombre from tiporefs_tbl where tiporefs_tbl.id = idref) as tiporef, "
                + "referencia "
                + "from wcref_tbl "
                + "where status = true and wcontid = '" + wcid + "' "
                + "order by id limit 1,100";
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString("id"));
                model.addRow(new String[]{rs.getString("tiporef"), rs.getString("referencia")});
            }

        } catch (SQLException e) {
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void cierreItinerario(String itiid) {
        utils.dbUpdate("UPDATE itinerarios_tbl SET "
                + "Status = IF(Status >=2, Status, 2), "
                //                    + "UsuarioDocs = IF(UsuarioDocs = 0, IF(getDocsPendienteIti(ItinerarioID) = '', '" + global.usuario + "', 0), UsuarioDocs), "
                //                    + "FechaDocs = IF(FechaDocs is null, IF(getDocsPendienteIti(ItinerarioID) = '', now(), null), FechaDocs), "
                + "UsuarioCierreViaje = '" + global.usuario + "', "
                + "FechaCierreViaje = now() "
                + "where ItinerarioID = '" + itiid + "'");
    }

    public static void copiarTarifasCobro(String rutafrom, String rutato) {
        String query = "SELECT id, "
                + "tipoufk, "
                + "estadoc, "
                + "importe, "
                + "importeccp,"
                + "moneda "
                + "from tarifasuc_tbl "
                + "where rutafk = '" + rutafrom + "' and status = true ";
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                String id = utils.dbInsert("INSERT INTO tarifasuc_tbl(rutafk, tipoufk, estadoc, importe, importeccp, usuariofk,moneda) "
                        + "VALUES('" + rutato + "', '" + rs.getString("tipoufk") + "', " + rs.getString("estadoc") + ", "
                        + "'" + rs.getString("importe") + "', '" + rs.getString("importeccp") + "', "
                        + "'" + global.usuario + "'," + rs.getString("moneda") + ")");
                if (id.length() > 11) {
//                    JOptionPane.showMessageDialog(this, id, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void insertarTarifaCobro(String rutafrom, String rutato, String tipou, String estadoc, String importe) {
        String query = "SELECT id, "
                + "tipoufk, "
                + "estadoc, "
                + "importe, "
                + "importeccp,"
                + "moneda "
                + "from tarifasuc_tbl "
                + "where rutafk = '" + rutafrom + "' and tipoufk in (" + tipou + ") and estadoc = '" + estadoc + "' and status = true ";
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            boolean entro = false;
            while (rs.next()) {
                entro = true;
                String id = utils.dbInsert("INSERT INTO tarifasuc_tbl(rutafk, tipoufk, estadoc, importe, importeccp, usuariofk,moneda) "
                        + "VALUES('" + rutato + "', '" + tipou + "', " + estadoc + ", digits('" + importe + "'), '" + rs.getString("importeccp") + "', '" + global.usuario + "'," + rs.getString("moneda") + ")");
                if (id.length() > 11) {
//                    JOptionPane.showMessageDialog(this, id, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (!entro) {
                String id = utils.dbInsert("INSERT INTO tarifasuc_tbl(rutafk, tipoufk, estadoc, importe, importeccp, usuariofk,moneda) "
                        + "VALUES('" + rutato + "', '" + tipou + "', " + estadoc + ", digits('" + importe + "'), '0', '" + global.usuario + "',1)");
            }
        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }

    }

    public static void insertarTarifasDefinidas(String rutaid, String tarifa, String millas) {
        millas = utils.dbConsult("SELECT digits('" + millas + "')");
        String query = "SELECT tipoufk,"
                + "estadoc,"
                + "IF(pormilla," + millas + "*importe, importe) as importetotal  "
                + "FROM tarifaspa_tbl "
                + "where status = true and tarifaid = '" + tarifa + "' ";
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            String id = "0";
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {

                id = utils.dbInsert("INSERT INTO tarifasup_tbl(rutafk, TipoUFK, estadoc, importe, usuariofk) "
                        + "VALUES('" + rutaid + "', '" + rs.getString("tipoufk") + "', " + rs.getString("estadoc") + ", '" + rs.getString("importetotal") + "', '" + global.usuario + "')");
                if (id.length() > 11) {
                    utils.errorGenerado("insertarTarifasDefinidas / err = " + id);
                }

            }
        } catch (SQLException e) {
            utils.errorGenerado("insertarTarifasDefinidas / sqlex err = " + e.toString());
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    private void cargarMillas(String rutafrom, String rutato) {
        String query = "SELECT DefinicionID,"
                + "Millas,"
                + "EstadoID "
                + "from millasrutas_tbl "
                + "where Status = true and RutaID = '" + rutafrom + "'";
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                utils.dbInsert("INSERT INTO millasrutas_tbl(RutaID, EstadoID, Millas) "
                        + "VALUES('" + rutato + "','" + rs.getString("EstadoID") + "', '" + rs.getString("Millas") + "')");
            }
        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void moverParada(String wcont, String paradaid, String nuevaposicion, String posicion) {
        utils.dbUpdate("UPDATE paradaswc_tbl SET orden = '" + nuevaposicion + "' where id = '" + paradaid + "'");

        String query = "SELECT id, "
                + "orden "
                + "from paradaswc_tbl "
                + "where wcontfk = '" + wcont + "' and status = true "
                + "order by orden, IF(" + posicion + " > " + nuevaposicion + ", IF(id = '" + paradaid + "', 0, 1), IF(id = '" + paradaid + "', 1, 0) ) ";
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            int i = 1;
            while (rs.next()) {
                if (rs.getInt("orden") != i) {
                    if (!rs.getString("id").equals(paradaid)) {
                        utils.dbUpdate("UPDATE paradaswc_tbl SET orden = " + i + " where id = '" + rs.getString("id") + "'");
                    }
                }
                i++;
            }
        } catch (SQLException e) {
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }

    }

    public static void relacionarItinerarioCargaDiesel(String ItinerarioID) {

        String Fecha = utils.dbConsult("SELECT Carga "
                + "FROM itinerarios_tbl "
                + "WHERE ItinerarioID=" + ItinerarioID + " and RutaID>0 and Carga is not null and CargaDieselID=0 and CamionID>0");
        if (Fecha.equals("")) {
            String FechaFin = utils.dbConsult("SELECT adddate(FechaCierreViaje,interval 2 day ) " // para no tomar una carga muy fuera de fecha en caso que tengan mucho espacio entre fechas.
                    + "FROM itinerarios_tbl "
                    + "WHERE ItinerarioID=" + ItinerarioID + " and RutaID>0 and FechaCierreViaje is not null and CargaDieselID=0"),
                    UnidadID = utils.dbConsult("SELECT CamionID "
                            + "FROM itinerarios_tbl "
                            + "WHERE ItinerarioID=" + ItinerarioID + " ");
            String Filtro = "now()";
            if (FechaFin.equals("")) {
                Filtro = "'" + FechaFin + "'";
            }
            String CargaID = utils.dbConsult("SELECT CargaID FROM cargasdiesel_tbl "
                    + "WHERE FechayHora between '" + Fecha + "' and " + Filtro + " and Status=1 and Tipo=0 and UnidadID=" + UnidadID + " and "
                    + "(SELECT CargaDieselID FROM itinerarios_tbl WHERE CargaDieselID=CargaID and Status>0 limit 1) is null  "
                    + "ORDER BY FechaYHora asc limit 1 ");
            if (!CargaID.equals("")) {
                String Folio = utils.dbConsult("SELECT concat(CargaID,'-',Folio) FROM cargasdiesel_tbl WHERE CargaID=" + CargaID);
                int options;
                options = JOptionPane.showConfirmDialog(null, "Desea relacionar el itinerario con la carga de diesel con folio '" + Folio + "'?",
                        "Confirmación", JOptionPane.YES_NO_OPTION);
                if (options == 0) {
                    String RendimientoCarga = utils.dbConsult("SELECT RendimientoCarga FROM cargasdiesel_tbl WHERE CargaID=round('" + CargaID + "',0)");
                    utils.dbUpdate("UPDATE itinerarios_tbl SET CargaID=round('" + CargaID + "',0),RendimientoDiesel = round('" + RendimientoCarga + "',4) WHERE ItinerarioID='" + ItinerarioID + "'");
                }
            }
        }

    }

    public static String revisarTarifaRenta(String rentaid) {
        String datos[] = utils.dbConsult("SELECT CONCAT(clientefk, ';', proyectofk,';', tipom) from rentaunidades_tbl where id = '" + rentaid + "'").split(";");
        String tarifa = utils.dbConsult("SELECT IFNULL((SELECT GROUP_CONCAT(DISTINCT trent.id) from tarifasrenta_tbl as trent where trent.clientefk = '" + datos[0] + "' and trent.proyectoid = '" + datos[1] + "' and tipomovrentaid = '" + datos[2] + "'),0) ");
        if (!tarifa.contains(",")) {
            utils.dbUpdate("UPDATE rentaunidades_tbl SET "
                    + "CobroCliente = IFNULL((SELECT cobro from tarifasrenta_tbl where tarifasrenta_tbl.id = '" + tarifa + "'),0),"
                    + "ImporteHE = IF((SELECT tipoextra from tarifasrenta_tbl as tarr where tarr.id = tarifarentafk) = 0, 0, HorasExtra*IFNULL((SELECT cobromovextra from tarifasrenta_tbl where tarifasrenta_tbl.id = '" + tarifa + "'),0) ), "
                    + "monedac = IFNULL((SELECT tarifasrenta_tbl.monedac from tarifasrenta_tbl where tarifasrenta_tbl.id = '" + tarifa + "'),0),"
                    + "FechaFCamion = IF(FechaICamion is null, FechaFCamion, DATE_ADD(FechaICamion, interval IFNULL((SELECT horasrenta from tarifasrenta_tbl where tarifasrenta_tbl.id = '" + tarifa + "'),1) HOUR)), "
                    + "tarifarentafk = '" + tarifa + "' "
                    + "where id = '" + rentaid + "'");//esrenta
        }
        return tarifa;
    }

    public static void cargarParadas(String wcid, DefaultTableModel model, ArrayList<String> list) {
        list.clear();
        model.setRowCount(0);
        String rutaid = utils.dbConsult("SELECT RutaID from workcontenedores_tbl where WContenedorID = '" + wcid + "'");
        String query = "SELECT id,"
                + "orden,"
                + "locacionfk,"
                + "tipop,"
                + "IFNULL((SELECT Nombre from tipoparada_tbl where tipoparada_tbl.id = tipop),'') as tipomov,"
                + "IFNULL((SELECT Nombre from locaciones_tbl where LocacionID = locacionfk),'') as nombre,"
                + "IFNULL(getDireccionLocacion(locacionfk),'') as direccion,"
                + "IFNULL(DATE_FORMAT(fechacita,'" + global.fdatedb + "'),'') as fcita,"
                + "IFNULL(DATE_FORMAT(fechacita,'%H:%i'),'') as hcita,"
                + "ruta,"
                + "IFNULL((SELECT horarios from locaciones_tbl where LocacionID = (SELECT LocacionPUID from rutas_tbl where RutaID = '" + rutaid + "')),'') as horariofrom,"
                + "IFNULL((SELECT horarios from locaciones_tbl where LocacionID = (SELECT LocacionTOID from rutas_tbl where RutaID = '" + rutaid + "')),'') as horarioto "
                + "from paradaswc_tbl "
                + "where status = true and wcontfk = '" + wcid + "' "
                + "order by orden";
        //System.out.println(query);
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString("id"));
                model.addRow(new Object[]{rs.getString("tipomov"), rs.getString("orden"), rs.getString("nombre"), rs.getString("direccion"), rs.getString("fcita"), rs.getString("hcita"), rs.getBoolean("ruta"), rs.getString("horariofrom"), rs.getString("horarioto")});
            }

        } catch (SQLException e) {
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    public static void revisarTarifa(String rentaid) {
        String datos[] = utils.dbConsult("SELECT CONCAT(clientefk,';', HorarioID) from plantillahoras_tbl where PlantillaID = '" + rentaid + "'").split(";");
        String tarifa = utils.dbConsult("SELECT IFNULL((SELECT trent.id from tarifasrenta_tbl as trent where trent.clientefk = '" + datos[0] + "' and tipomovrentaid = '" + datos[1] + "' limit 1),0) ");
        utils.dbUpdate("UPDATE plantillahoras_tbl SET "
                + "CobroCliente = IFNULL((SELECT cobro from tarifasrenta_tbl where tarifasrenta_tbl.id = '" + tarifa + "'),0),"
                + "ImporteHE = IF((SELECT tipoextra from tarifasrenta_tbl as tarr where tarr.id = tarifarentaid) = 0, 0, horasextra*IFNULL((SELECT cobromovextra from tarifasrenta_tbl where tarifasrenta_tbl.id = '" + tarifa + "'),0) ), "
                + "monedac = IFNULL((SELECT tarifasrenta_tbl.monedac from tarifasrenta_tbl where tarifasrenta_tbl.id = '" + tarifa + "'),0),"
                + "FechaFCamion = IF(FechaICamion is not null, FechaFCamion, DATE_ADD(FechaICamion, interval IFNULL((SELECT horasrenta from tarifasrenta_tbl where tarifasrenta_tbl.id = '" + tarifa + "'),1) HOUR)), "
                + "tarifarentaid = '" + tarifa + "' "
                + "where PlantillaID = '" + rentaid + "'");//esrenta
//        return tarifa;
    }

    public static String llenarProgramacion(String work, String wcid) {
        String filtro = "WorkOrderID = '" + work + "'";
        if (!wcid.equals("0")) {
            filtro = "WContenedorID = '" + wcid + "'";
        }

        String err = "";
        String query = "SELECT WContenedorID,"
                + "RutaID,"
                + "Cantidad,"
                + "clientefk as clienteid,"
                + "TipoOperacion,"
                + "IFNULL(AppoimentDate, Fecha) as fwo "
                + "from workcontenedores_tbl "
                + "where " + filtro + " and Status = true ";

        int entro = 0;
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            String fecha = null;
            while (rs.next()) {
                if (rs.getString("fwo") != null) {
                    fecha = "'" + rs.getString("fwo") + "'";
                } else {
                    fecha = null;
                }
                //if (!ruta.equals(rs.getString("RutaID")) || !fecha.equals(rs.getString("fp"))) {
                String id = utils.dbInsert("INSERT INTO programacion_tbl (RutaID,UsuarioID,FechaCarga, FechaLlegada, ClienteSerID, TipoOperacion) "
                        + "VALUES('" + rs.getString("RutaID") + "','" + global.usuario + "'," + fecha + ",null, '" + rs.getString("clienteid") + "', '" + rs.getString("TipoOperacion") + "')");///////////////
                if (id.length() < 11 && !id.isEmpty()) {
                    String id2 = utils.dbInsert("INSERT INTO progcont_tbl(WContenedorID,ProgramacionID) VALUES('" + rs.getString("WContenedorID") + "','" + id + "')");
                    if (id2.length() <= 11) {
//                        utils.dbUpdate("UPDATE workcontenedores_tbl SET Statusf = 2 where WContenedorID = '" + rs.getString("WContenedorID") + "'");
                    }
                    entro++;
                }
            }
            if (entro > 0) {
                utils.dbUpdate("UPDATE workcontenedores_tbl SET Statusf = 2 where WorkOrderID = '" + work + "'");
                utils.dbUpdate("UPDATE workorder_tbl SET Status = 2 where WorkID = '" + work + "'");
            }

        } catch (SQLException e) {
            err = e.toString();
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
        return err;
    }

    public static boolean validarWCiti(String wcid) {
        if (!utils.dbConsult("SELECT IFNULL((SELECT ItinerarioID from icont_tbl where WContID = '" + wcid + "' limit 1),0)").equals("0")) {
            return false;
        }
        return true;
    }

    public static boolean validarWOiti(String woid) {
        if (!utils.dbConsult("SELECT IFNULL((SELECT ItinerarioID from workcontenedores_tbl left join icont_tbl on WContenedorID = WContID where WorkOrderID = '" + woid + "' limit 1),0)").equals("0")) {
            return false;
        }
        return true;
    }

    public static String eliminarExtraOp(String ccid) {
        String up = utils.dbUpdate("UPDATE cajachica_tbl SET Status = false where CajachicaID = '" + ccid + "'");
        if (up.isEmpty()) {
//                if (!cajachicaid.get(row).equals("0")) {
            utils.dbUpdate("UPDATE bonospersonales_tbl SET Status = false, usuarioeliminafk = '" + global.usuario + "', fechaelimina = now() where CajachicaFK = '" + ccid + "'");
        }
        return up;
    }

    public static void revisarCargosExtraDefinidos(String itiid, String rutaid) {
        String up = utils.dbUpdate("UPDATE cajachica_tbl SET Status = false where ItinerarioID = '" + itiid + "' and definicionid > 0");
        if (up.isEmpty()) {
            utils.dbUpdate("UPDATE bonospersonales_tbl SET Status = false, usuarioeliminafk = '" + global.usuario + "', fechaelimina = now() where Status = true and CajachicaFK in (SELECT CajachicaID from cajachica_tbl where ItinerarioID = '" + itiid + "' and definicionid > 0)");
            String query = "SELECT id,"
                    + "cargofk,"
                    + "descripcion,"
                    + "nota,"
                    + "importe,"
                    + "moneda,"
                    + "movimiento "
                    + "from defcargosoperadores_tbl as defop "
                    + "where rutafk = '" + rutaid + "' and status = true";

            Connection con = null;
            Statement state = null;
            ResultSet rs = null;
            try {
                con = utils.startConnection();
                state = con.createStatement();
                rs = state.executeQuery(query);
                int i = 1;
                while (rs.next()) {
                    String id = utils.dbInsert("INSERT INTO cajachica_tbl(CargoID, Descripcion, Cantidad, Unitario, Importe, ImporteOriginal, Moneda, Movimiento, UsuarioID, ItinerarioID, definicionid, Fecha) "
                            + "VALUES('" + rs.getString("cargofk") + "', '" + rs.getString("descripcion") + "', 1, '" + rs.getString("importe") + "', '" + rs.getString("importe") + "', '" + rs.getString("importe") + "', '" + rs.getString("moneda") + "', '" + rs.getString("movimiento") + "', '" + global.usuario + "', '" + itiid + "', '" + rs.getString("id") + "', now())");
                    if (id.length() <= 11) {
                        FuncionesTrafico.revisionExtraSecundario(itiid, id);
                    }
                }
            } catch (SQLException e) {
                utils.errorGenerado("FuncionesTrafico / revisarCargosExtraDefinidos / sqlex = " + e.toString());
            } finally {
                utils.closeAllConnections(con, state, rs);
            }
        }
    }

    public static String eliminarOrden(String wcont, String motivo) {
        String resp = utils.dbUpdate("UPDATE workcontenedores_tbl SET Status = false, UsuarioCancela = '" + global.usuario + "', FechaCancela = now(), MotivoCancela = '" + motivo + "'  where WContenedorID = '" + wcont + "'");
        if (resp.isEmpty()) {
            utils.dbUpdate("UPDATE cargosclientes_tbl SET Status = false where WContID = '" + wcont + "'");
            utils.dbUpdate("UPDATE cajachica_tbl SET Status = 0 where ItinerarioID in (SELECT icont_tbl.ItinerarioID from icont_tbl where icont_tbl.WContID = '" + wcont + "' and icont_tbl.Status = true)");
            utils.dbUpdate("UPDATE itinerarios_tbl SET Status = 0, UsuarioEliminaID = '" + global.usuario + "', FechaElimina = now()  where ItinerarioID in (SELECT icont_tbl.ItinerarioID from icont_tbl where icont_tbl.WContID = '" + wcont + "' and icont_tbl.Status = true)");
            utils.dbUpdate("UPDATE icont_tbl SET Status = false where WContID = '" + wcont + "'");
            utils.dbUpdate("UPDATE bonospersonales_tbl SET Status=0, usuarioeliminafk = '" + global.usuario + "', fechaelimina = now(), Nota = 'order_delete' WHERE ItinerarioFK in (SELECT icont_tbl.ItinerarioID from icont_tbl where icont_tbl.WContID = '" + wcont + "' and icont_tbl.Status = true)");
        }
        return resp;
    }

    public static void eliminarItinerario(String itiid) {
        if (!itiid.equals("0")) {
            String up = utils.dbUpdate("UPDATE itinerarios_tbl SET Status = 0, esplan = false, UsuarioEliminaID = '" + global.usuario + "', FechaElimina = now()  where ItinerarioID = '" + itiid + "'");
            if (up.isEmpty()) {
                String wos = utils.dbConsult("SELECT IFNULL((SELECT GROUP_CONCAT(DISTINCT WContID) from icont_tbl where ItinerarioID = '" + itiid + "' and Status = true),0)");
                if (!wos.contains(",")) {
                    FuncionesTrafico.guardarMods(itiid, wos, "Driver move eliminado.");
                } else {
                    String split[] = wos.split(",");
                    for (int i = 0; i < split.length; i++) {
                        FuncionesTrafico.guardarMods(itiid, split[i], "Driver move eliminado.");
                    }
                }
                utils.dbUpdate("UPDATE icont_tbl SET Status = false where ItinerarioID = '" + itiid + "'");
                utils.dbUpdate("UPDATE cajachica_tbl SET Status = 0 where ItinerarioID = '" + itiid + "'");
                utils.dbUpdate("UPDATE bonospersonales_tbl SET Status=0, usuarioeliminafk = '" + global.usuario + "', fechaelimina = now(), Nota = 'dm_delete' WHERE ItinerarioFK = '" + itiid + "' and Status = true");
            }
        }
    }

    public static void insertarChoferSec(String ItinerarioID, String wcontid, String choferid, String fromto) {
        utils.dbUpdate("UPDATE itinerarios_tbl SET ChoferSecundarioID = " + choferid + " WHERE ItinerarioID = " + ItinerarioID);
        // si es float round 2, no redondea 0.005->0.00 . Si es Decimal redondea 0.005->0.01
        String Pago = utils.dbConsult("SELECT getPagoRutaDin(ItinerarioID) FROM itinerarios_tbl WHERE ItinerarioID=" + ItinerarioID);
        utils.dbUpdate("UPDATE itinerarios_tbl SET PagoSV = " + Pago + ", MonedaChofer = getMonedaPagoRutaDin(ItinerarioID) WHERE ItinerarioID = " + ItinerarioID);
        String Moneda = utils.dbConsult("SELECT MonedaChofer FROM itinerarios_tbl WHERE ItinerarioID=" + ItinerarioID);
        String SemanaID = getSemanaIti(ItinerarioID);
        if (fromto == null) {
            fromto = "";
        }
        utils.dbInsert("INSERT INTO bonospersonales_tbl (Importe,ChoferID,Moneda,Concepto,UsuarioID,ItinerarioFK,SemanaID) "
                + "VALUES(" + Pago + "," + choferid + "," + Moneda + ",'DM: " + ItinerarioID + ", WO:" + wcontid + ", From-To:" + fromto + "',"
                + "" + global.usuario + "," + ItinerarioID + "," + SemanaID + ")");

        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        String query = "SELECT CajachicaID,Importe,Moneda,round(Importe/2,2) as ImporteD,Descripcion "
                + "FROM cajachica_tbl "
                + "WHERE ItinerarioID=" + ItinerarioID + " and Status=1 ";
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                revisionExtraSecundario(ItinerarioID, rs.getString("CajachicaID"));
            }
            FuncionesTrafico.guardarMods(ItinerarioID, wcontid, "Cambio operador secundario " + utils.dbConsult("SELECT Nombre from choferes_tbl where ChoferID = '" + choferid + "'"));
        } catch (SQLException e) {
            utils.errorGenerado("FuncionesTrafico / insertarChoferSec / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } catch (Exception e) {
            utils.errorGenerado("FuncionesTrafico / insertarChoferSec / ex = " + e.toString());
        } finally {
            utils.closeAllConnections(con, state, rs);
        }

    }

    public static String getSemanaIti(String itiid) {
        return utils.dbConsult("SELECT "
                + "ifnull((SELECT SemanaID FROM semanas_tbl WHERE IF(FechaNomina is null, Carga, FechaNomina) between Fechaini and Fechafin),0) SemanaID "
                + "FROM itinerarios_tbl WHERE ItinerarioID=" + itiid);
    }

    public static void updateSemanaChoferSecundario(String itiid) {
        if (!utils.dbConsult("SELECT ChoferSecundarioID FROM itinerarios_tbl WHERE ItinerarioID=" + itiid).equals("0")) {
            String semana = getSemanaIti(itiid);
            utils.dbUpdate("UPDATE bonospersonales_tbl SET SemanaID = '" + semana + "' WHERE (ItinerarioFK = '" + itiid + "' or CajachicaFK in (SELECT CajachicaID from cajachica_tbl where ItinerarioID = '" + itiid + "' and cajachica_tbl.Status = true)) and Status = true and BonoID > 0");
        }
    }

    public static String getSemanaExtra(String extraid) {
        return utils.dbConsult("SELECT ifnull((SELECT SemanaID FROM semanas_tbl WHERE IFNULL(fechaNomina, (SELECT IFNULL(iti.FechaNomina,Carga) from itinerarios_tbl as iti where iti.ItinerarioID = cajachica_tbl.ItinerarioID) ) between Fechaini and Fechafin),0) from cajachica_tbl where CajachicaID = '" + extraid + "'");
    }

    public static void updateSemanaChoferSecundarioExtra(String extraid) {
        if (!utils.dbConsult("SELECT ChoferSecundarioID FROM itinerarios_tbl WHERE ItinerarioID = (SELECT cc.ItinerarioID from cajachica_tbl as cc where CajachicaID = " + extraid + ")").equals("0")) {
            String semana = getSemanaExtra(extraid);
            utils.dbUpdate("UPDATE bonospersonales_tbl SET SemanaID = '" + semana + "' WHERE CajachicaFK = '" + extraid + "' and Status = true and BonoID > 0");
        }
    }

    public static void insertarChoferSecOnlyIt(String ItinerarioID, String wcontid) {
        // si es float round 2, no redondea 0.005->0.00 . Si es Decimal redondea 0.005->0.01
        String Pago = utils.dbConsult("SELECT round(PagoSV,2) FROM itinerarios_tbl WHERE ItinerarioID=" + ItinerarioID);
        String ChoferID = utils.dbConsult("SELECT ChoferSecundarioID FROM itinerarios_tbl WHERE ItinerarioID=" + ItinerarioID);
        utils.dbUpdate("UPDATE itinerarios_tbl SET PagoSV=" + Pago + " WHERE ItinerarioID=" + ItinerarioID);
        String Moneda = utils.dbConsult("SELECT MonedaChofer FROM itinerarios_tbl WHERE ItinerarioID=" + ItinerarioID);
        String SemanaID = getSemanaIti(ItinerarioID);
        utils.dbInsert("INSERT INTO bonospersonales_tbl (Importe,ChoferID,Moneda,Concepto,UsuarioID,ItinerarioFK,SemanaID) "
                + "VALUES(" + Pago + "," + ChoferID + "," + Moneda + ","
                + "'DM: " + ItinerarioID + ","
                + " WO:" + utils.dbConsult("SELECT GROUP_CONCAT(DISTINCT WContID) from icont_tbl where ItinerarioID = '" + ItinerarioID + "' and Status = true") + ", "
                + "From-To:" + utils.dbConsult("SELECT (SELECT Nombre from rutas_tbl where rutas_tbl.RutaID = itinerarios_tbl.RutaID) from itinerarios_tbl where ItinerarioID = '" + ItinerarioID + "'") + "',"
                + "" + global.usuario + "," + ItinerarioID + "," + SemanaID + ")");

        FuncionesTrafico.guardarMods(ItinerarioID, wcontid, "Nuevo operador secundario");
    }

    public static void removerChoferSecOnlyIt(String ItinerarioID) {
//        utils.dbUpdate("UPDATE itinerarios_tbl SET ChoferSecundarioID=0,PagoSV=PagoSV*2 WHERE ItinerarioID="+ItinerarioID);
//        utils.dbUpdate("UPDATE cajachica_tbl SET Importe=Importe*2 WHERE ItinerarioID="+ItinerarioID+" and Status=1 ");
        utils.dbUpdate("UPDATE bonospersonales_tbl SET Status = 0, usuarioeliminafk = '" + global.usuario + "', fechaelimina = now(), Nota = 'update_delete' WHERE ItinerarioFK=" + ItinerarioID + " and Status = true");
//        utils.dbUpdate("UPDATE bonospersonales_tbl SET Status = 0, usuarioeliminafk = '" + global.usuario + "', fechaelimina = now(), Nota = 'update_delete' WHERE CajachicaFK in (SELECT CajachicaID from cajachica_tbl where ItinerarioID = '"+ItinerarioID+"' and cajachica_tbl.Status = true) and bonospersonales_tbl.Status = true");
    }

    public static void removerChoferSec(String ItinerarioID, String wcontid) {
        String upc = utils.dbUpdate("UPDATE itinerarios_tbl SET ChoferSecundarioID=0,PagoSV=PagoSV*2 WHERE ItinerarioID=" + ItinerarioID);
        if (upc.isEmpty()) {
            utils.dbUpdate("UPDATE cajachica_tbl SET Importe=Importe*2 WHERE ItinerarioID=" + ItinerarioID + " and Status = 1");
            utils.dbUpdate("UPDATE bonospersonales_tbl SET Status = 0, usuarioeliminafk = '" + global.usuario + "', fechaelimina = now(), Nota = 'removerchofersec_delete' WHERE ItinerarioFK=" + ItinerarioID + " and Status = true");
            String query
                    = "SELECT CajachicaID "
                    + "FROM cajachica_tbl "
                    + "WHERE ItinerarioID=" + ItinerarioID + " and Status=1 ";
            Connection con = null;
            Statement state = null;
            ResultSet rs = null;
            try {
                con = utils.startConnection();
                state = con.createStatement();
                rs = state.executeQuery(query);
                while (rs.next()) {
                    utils.dbUpdate("UPDATE bonospersonales_tbl SET Status = 0, usuarioeliminafk = '" + global.usuario + "', fechaelimina = now(), Nota = 'removerchofersec_delete' WHERE CajaChicaFK=" + rs.getString("CajachicaID") + " and Status = true");
                }
                FuncionesTrafico.guardarMods(ItinerarioID, wcontid, "Se removio operador secundario");
            } catch (SQLException e) {
                System.out.println("Error " + e);
            } finally {
                utils.closeAllConnections(con, state, rs);
            }
        }
    }

    public static String revisionExtraSecundario(String itiid, String extraid) {
        String msj = "";
        String ChoferSec = utils.dbConsult("SELECT ChoferSecundarioID FROM itinerarios_tbl WHERE ItinerarioID=" + itiid);
        if (!ChoferSec.equals("0")) {//se revisa si tiene chofer secundario
            String SemanaID = getSemanaIti(itiid);// se revisa en que semana cae el movimiento
            String bonoid = utils.dbConsult("SELECT IFNULL((SELECT BonoID from bonospersonales_tbl where CajachicaFK = '" + extraid + "' and Status = true limit 1),0)");// se revisa si el extra ya cuenta con divison de importe.
            if (bonoid.equals("0")) {
                String idbono = utils.dbInsert("INSERT INTO bonospersonales_tbl (Importe,ChoferID,Moneda,Concepto,UsuarioID,CajaChicaFK,SemanaID) "
                        + "VALUES((SELECT ImporteOriginal/2 from cajachica_tbl where CajachicaID = '" + extraid + "'), " + ChoferSec + ", IFNULL((SELECT cc.Moneda from cajachica_tbl as cc where CajachicaID = '" + extraid + "'),0),"
                        + "IFNULL((SELECT Descripcion from cajachica_tbl where CajachicaID = '" + extraid + "'),''),"
                        + "" + global.usuario + "," + extraid + "," + SemanaID + ")");
                if (idbono.length() <= 11) {
                    utils.dbUpdate("UPDATE cajachica_tbl SET Importe = ImporteOriginal/2 where CajachicaID = '" + extraid + "'");
                }
            } else {
                //en dado caso de que si tenga ya la division, actualizas ambos importes
                utils.dbUpdate("UPDATE cajachica_tbl SET Importe = ImporteOriginal/2 where CajachicaID = '" + extraid + "'");

                utils.dbUpdate("UPDATE bonospersonales_tbl SET SemanaID = '" + SemanaID + "', "
                        + "Importe = (SELECT ImporteOriginal/2 from cajachica_tbl where CajachicaID = CajaChicaFK),"
                        + "Moneda = IFNULL((SELECT cc.Moneda from cajachica_tbl as cc where CajachicaID = '" + extraid + "'),0),"
                        + "Concepto = IFNULL((SELECT Descripcion from cajachica_tbl where CajachicaID = '" + extraid + "'),'') "
                        + "where BonoID = '" + bonoid + "'");
            }
            msj = "Este movimiento entrara al flujo de teams";
//            JOptionPane.showMessageDialog(null, "Se asigno la mitad al Chofer Secundario", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
        return msj;
    }

    public static String aplicarTarifaRenta(String rentaid, String tarifaid) {
//        if (utils.dbConsult("SELECT tarifarentafk from rentaunidades_tbl where id = '" + rentaid + "'").equals("0")) {
        utils.dbUpdate("UPDATE rentaunidades_tbl SET tarifarentafk = '" + tarifaid + "',"
                + "FechaFCamion = IF(FechaICamion is null, FechaFCamion, DATE_ADD(FechaICamion, interval IFNULL((SELECT horasrenta from tarifasrenta_tbl where tarifasrenta_tbl.id = '" + tarifaid + "'),1) HOUR)) "
                + "where id = '" + rentaid + "'");

        obtenerHoras(rentaid);
//        }
        utils.dbUpdate("UPDATE rentaunidades_tbl SET "
                + "CobroCliente = IFNULL((SELECT cobro from tarifasrenta_tbl where tarifasrenta_tbl.id = '" + tarifaid + "'),0),"
                + "monedac = IFNULL((SELECT tarifasrenta_tbl.monedac from tarifasrenta_tbl where tarifasrenta_tbl.id = '" + tarifaid + "'),0) "
                + "where id = '" + rentaid + "'");//esrenta
        return tarifaid;
    }

    public static void obtenerHoras(String rentaid) {
        String up = utils.dbUpdate("UPDATE rentaunidades_tbl SET "
                + "HorasTotales = CEIL(TIMESTAMPDIFF(MINUTE, FechaICamion, FechaFCamion)/60), "
                + "HorasRegulares = IFNULL((SELECT horasrenta from tarifasrenta_tbl where tarifasrenta_tbl.id = tarifarentafk),0),"
                + "HorasExtra = IF(CEIL(TIMESTAMPDIFF(MINUTE, FechaICamion, FechaFCamion)/60) < IFNULL((SELECT horasrenta from tarifasrenta_tbl where tarifasrenta_tbl.id = tarifarentafk),0), 0, CEIL(TIMESTAMPDIFF(MINUTE, FechaICamion, FechaFCamion)/60) - IFNULL((SELECT horasrenta from tarifasrenta_tbl where tarifasrenta_tbl.id = tarifarentafk),0)) "
                + "where id = '" + rentaid + "'");
        if (up.isEmpty()) {//esrenta
            utils.dbUpdate("UPDATE rentaunidades_tbl SET ImporteHE = IF((SELECT tipoextra from tarifasrenta_tbl as tarr where tarr.id = tarifarentafk) = 0, 0, IFNULL((SELECT cobromovextra from tarifasrenta_tbl where tarifasrenta_tbl.id = tarifarentafk),0)* HorasExtra ) where id = '" + rentaid + "'");
        } else {
//            JOptionPane.showMessageDialog(this, up, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void guardarMods(String itiid, String woid, String text) {
        String id = utils.dbInsert("INSERT INTO historialwo_tbl(woid, itiid, Modificacion, usuarioid, fecha) "
                + "VALUES('" + woid + "', '" + itiid + "', '" + text.replace("'", "\\'") + "', '" + global.usuario + "' , now())");
        if (id.length() >= 11) {
            utils.errorGenerado("FuncionesTrafico / guardarMods / " + id);
        }
    }

    public static void ajusteCargosClienteFijos(String wcontsid) {
        String up = utils.dbUpdate("UPDATE cargosclientes_tbl SET Status = false, juntodoc = false, usuarioelimina = '" + global.usuario + "', fechaelimina = now()  where WContID in (" + wcontsid + ")");
        if (up.isEmpty()) {
            if (!wcontsid.contains(",")) {
                utils.generarCargosFijos(wcontsid);
            } else {
                String wconts[] = wcontsid.split(",");
                for (int i = 0; i < wconts.length; i++) {
                    utils.generarCargosFijos(wconts[i]);
                }
            }
        }
    }

    /**
     * crear edi 214
     *
     *
     * @param fechaship string con formato
     * @param ediid string con formato
     * @param fechaship por %Y%m%d
     * @param horaship string HHmm
     * @param Direccion Edi214Direccion
     * @return string con DATE listo para BD (con todo y comillas sencillas),
     * null en caso de que NO tenga formato adecuado.
     * @since 1.5
     */
    public static String Crear214(String FechaShip, String EdiID, String HoraShipment, String StatusID, String ReasonID, String Serie, String ShipmentAppointmentStatusCodes,
            int Origen, int Destino, Edi214Direccion Direccion, String itiid) {// ShipmentAppointmentStatusCodes='No Aplica' agregar opcion.Opciones {'No Aplica','AA','AB'} // AA=Pick-up Appointment Date/Time , AB=Delivery Appointment Date/Time
        String Message = "";
        String WOrderID = utils.dbConsult("SELECT WOrderID FROM edi_tbl WHERE EdiID=" + EdiID);
        if (!WOrderID.equals("0")) {
            if (!Serie.equals("")) {
                //el carrier id es (SELECT WOrderID FROM edi_tbl WHERE EdiID=" + EdiID)
                String CarrierID = WOrderID;
//                        utils.dbConsult("SELECT work.FolioWork FROM edi_tbl edi "
//                        + "LEFT JOIN workorder_tbl work ON work.WorkID=edi.WOrderID "
//                        + "WHERE edi.EdiID=" + EdiID);//"577888";//Carrier Assigned Shipment ID
                String control = utils.dbConsult("SELECT count(1)+1039999 from edis_anexos_tbl where Tipo='214'");// modificar id
                String control2 = utils.dbConsult("SELECT if('" + control + "'>999999999,'" + control + "',RIGHT(CONCAT('00000000', IFNULL('" + control + "','')),9))");
                String Fecha4 = utils.dbConsult("SELECT date_format(now(),'%Y%m%d')");
                String Fecha2 = utils.dbConsult("SELECT date_format(now(),'%y%m%d')");
                String Hora = utils.dbConsult("SELECT date_format(now(),'%H%i')");
                //         HoraShipment=CHora.getSelectedItem()+""+CMin.getSelectedItem();
                //         FechaShip=utils.dbConsult("SELECT date_format('"+utils.dateFromFieldtoDBwoH(txtFechaIC.getText())+"','%Y%m%d')");
                String Server = "015106482      ";//"015106482Y2K   " pruebas // produccion?: "015106482      "
//                String Server2 = Server.replace(" ", "");
                String ApplSender = utils.dbConsult("SELECT left(CONCAT(ApplSender,'                 '),15) FROM edi_tbl WHERE EdiID='" + EdiID + "'");
                String ApplSender2 = utils.dbConsult("SELECT ApplSender FROM edi_tbl WHERE EdiID='" + EdiID + "'");
                String jCSASC = "";//ShipmentAppointmentStatusCodes
                String Reason = utils.dbConsult("SELECT Code FROM shipmentstatusreasoncodes_tbl where SStatusRCID=" + ReasonID);
                String shipmentstatuscodes = "";
                if (!ShipmentAppointmentStatusCodes.equalsIgnoreCase("No Aplica")) {
                    jCSASC = "**" + ShipmentAppointmentStatusCodes + "*" + Reason + "*";
                } else {
                    shipmentstatuscodes = utils.dbConsult("SELECT Code FROM shipmentstatuscodes_tbl where SStatusCID=" + StatusID);
                    jCSASC = "" + shipmentstatuscodes + "*"
                            + "" + Reason + "***";
                }
                String DireccionOrigen = "";
                String DireccionDestino = "";
                int Manual = 0;// 2 AA AB  - 1 AA AB AB
                String MS1 = "";
                if (Manual == 1) {
                    if (Origen == 0) {
                        DireccionOrigen = "N1*SH*CROWN XPRESS TRANSPORT~"
                                + "N3*6903 CACTUS CT~"
                                + "N4*SAN DIEGO*CA*92154*US~";
                    } else {
                        DireccionOrigen = "N1*" + utils.dbConsult("SELECT concat('SH*',group_concat(ele.Nombre SEPARATOR '*')) FROM edi_tbl edi \n"
                                + "                LEFT JOIN segment_tbl seg oN seg.EdiID=edi.EdiID and seg.Nombre='N1' \n"
                                + "                LEFT JOIN element_tbl ele ON ele.SegmentID=seg.SegmentID \n"
                                + "                WHERE seg.Serie=" + Origen + " and ele.Elemento='N102' and edi.EdiID='" + EdiID + "'") + "~"
                                + "N3*" + utils.dbConsult("SELECT ele.Nombre FROM edi_tbl edi "
                                        + "LEFT JOIN segment_tbl seg oN seg.EdiID=edi.EdiID and seg.Nombre='N3' "
                                        + "LEFT JOIN element_tbl ele ON ele.SegmentID=seg.SegmentID "
                                        + "WHERE ele.Elemento='N301' and seg.Serie=" + Origen + " and edi.EdiID='" + EdiID + "'") + "~"
                                + "N4*" + utils.dbConsult("SELECT group_concat(ele.Nombre SEPARATOR '*') FROM edi_tbl edi "
                                        + "LEFT JOIN segment_tbl seg oN seg.EdiID=edi.EdiID and seg.Nombre='N4' "
                                        + "LEFT JOIN element_tbl ele ON ele.SegmentID=seg.SegmentID "
                                        + "WHERE seg.Serie=" + Origen + " and edi.EdiID='" + EdiID + "'") + "~";
                    }
                    if (Destino == 0) {
                        DireccionDestino = "N1*SH*CROWN XPRESS TRANSPORT~"
                                + "N3*6903 CACTUS CT~"
                                + "N4*SAN DIEGO*CA*92154*US~";
                    } else {
                        DireccionDestino = "N1*" + utils.dbConsult("SELECT concat('CN*',group_concat(ele.Nombre SEPARATOR '*')) FROM edi_tbl edi \n"
                                + "LEFT JOIN segment_tbl seg oN seg.EdiID=edi.EdiID and seg.Nombre='N1' \n"
                                + "LEFT JOIN element_tbl ele ON ele.SegmentID=seg.SegmentID \n"
                                + "WHERE seg.Serie='" + Destino + "' and ele.Elemento='N102' and edi.EdiID='" + EdiID + "'") + "~"
                                + "N3*" + utils.dbConsult("SELECT ele.Nombre FROM edi_tbl edi "
                                        + "LEFT JOIN segment_tbl seg oN seg.EdiID=edi.EdiID and seg.Nombre='N3' "
                                        + "LEFT JOIN element_tbl ele ON ele.SegmentID=seg.SegmentID "
                                        + "WHERE ele.Elemento='N301' and seg.Serie='" + Destino + "' and edi.EdiID='" + EdiID + "'") + "~"
                                + "N4*" + utils.dbConsult("SELECT group_concat(ele.Nombre SEPARATOR '*') FROM edi_tbl edi "
                                        + "LEFT JOIN segment_tbl seg oN seg.EdiID=edi.EdiID and seg.Nombre='N4' "
                                        + "LEFT JOIN element_tbl ele ON ele.SegmentID=seg.SegmentID "
                                        + "WHERE seg.Serie='" + Destino + "' and edi.EdiID='" + EdiID + "'") + "~";
                    }

                    if (Destino == 0) {
                        MS1 = "MS1*SAN DIEGO*CA~";
                    } else {
                        MS1 = "MS1*"//STOCKTON*CA~"+// lugar del camion? DESTINO! 
                                + "" + utils.dbConsult("SELECT group_concat(ele.Nombre SEPARATOR '*') FROM edi_tbl edi \n"
                                        + "                LEFT JOIN segment_tbl seg oN seg.EdiID=edi.EdiID and seg.Nombre='N4' \n"
                                        + "                LEFT JOIN element_tbl ele ON ele.SegmentID=seg.SegmentID \n"
                                        + "                WHERE seg.Serie=" + Destino + " and ele.Elemento!='N403' and edi.EdiID='" + EdiID + "'") + "~";
                        System.out.println("SELECT group_concat(ele.Nombre SEPARATOR '*') FROM edi_tbl edi \n"
                                + "                LEFT JOIN segment_tbl seg oN seg.EdiID=edi.EdiID and seg.Nombre='N4' \n"
                                + "                LEFT JOIN element_tbl ele ON ele.SegmentID=seg.SegmentID \n"
                                + "                WHERE seg.Serie=" + Destino + " and ele.Elemento!='N403' and edi.EdiID='" + EdiID + "'");
                    }
                } else {
                    //ruben
                    //crear objeto domicilio del origen;
                    if (Serie.equals("1")) {
                        MS1 = "MS1*" + Direccion.ShipperCity + "*" + Direccion.ShipperState + "~";
                        DireccionOrigen = "N1*SH*" + Direccion.ShipperName + "~"
                                + "N3*" + Direccion.ShipperAddressLine1 + "~"
                                + "N4*" + Direccion.ShipperCity + "*" + Direccion.ShipperState + "*" + Direccion.ShipperZipCode + "*" + Direccion.Country + "~";
                    } else {
                        MS1 = "MS1*"//STOCKTON*CA~"+// lugar del camion? DESTINO! 
                                + "" + utils.dbConsult("SELECT group_concat(ele.Nombre SEPARATOR '*') FROM edi_tbl edi \n"
                                        + "                LEFT JOIN segment_tbl seg oN seg.EdiID=edi.EdiID and seg.Nombre='N4' \n"
                                        + "                LEFT JOIN element_tbl ele ON ele.SegmentID=seg.SegmentID \n"
                                        + "                WHERE seg.Serie=(" + Serie + "-1) and ele.Elemento!='N403' and edi.EdiID='" + EdiID + "'") + "~";
                        DireccionOrigen = "N1*" + utils.dbConsult("SELECT concat('SH*',group_concat(ele.Nombre SEPARATOR '*')) FROM edi_tbl edi \n"
                                + "                LEFT JOIN segment_tbl seg oN seg.EdiID=edi.EdiID and seg.Nombre='N1' \n"
                                + "                LEFT JOIN element_tbl ele ON ele.SegmentID=seg.SegmentID \n"
                                + "                WHERE seg.Serie=(" + Serie + "-1) and ele.Elemento='N102' and edi.EdiID='" + EdiID + "'") + "~"
                                + "N3*" + utils.dbConsult("SELECT ele.Nombre FROM edi_tbl edi "
                                        + "LEFT JOIN segment_tbl seg oN seg.EdiID=edi.EdiID and seg.Nombre='N3' "
                                        + "LEFT JOIN element_tbl ele ON ele.SegmentID=seg.SegmentID "
                                        + "WHERE ele.Elemento='N301' and seg.Serie=(" + Serie + "-1) and edi.EdiID='" + EdiID + "'") + "~"
                                + "N4*" + utils.dbConsult("SELECT group_concat(ele.Nombre SEPARATOR '*') FROM edi_tbl edi "
                                        + "LEFT JOIN segment_tbl seg oN seg.EdiID=edi.EdiID and seg.Nombre='N4' "
                                        + "LEFT JOIN element_tbl ele ON ele.SegmentID=seg.SegmentID "
                                        + "WHERE seg.Serie=(" + Serie + "-1) and edi.EdiID='" + EdiID + "'") + "~";
                    }
                    DireccionDestino = "N1*" + utils.dbConsult("SELECT concat('CN*',group_concat(ele.Nombre SEPARATOR '*')) FROM edi_tbl edi \n"
                            + "                LEFT JOIN segment_tbl seg oN seg.EdiID=edi.EdiID and seg.Nombre='N1' \n"
                            + "                LEFT JOIN element_tbl ele ON ele.SegmentID=seg.SegmentID \n"
                            + "                WHERE seg.Serie=" + Serie + " and ele.Elemento='N102' and edi.EdiID='" + EdiID + "'") + "~"
                            + "N3*" + utils.dbConsult("SELECT ele.Nombre FROM edi_tbl edi "
                                    + "LEFT JOIN segment_tbl seg oN seg.EdiID=edi.EdiID and seg.Nombre='N3' "
                                    + "LEFT JOIN element_tbl ele ON ele.SegmentID=seg.SegmentID "
                                    + "WHERE ele.Elemento='N301' and seg.Serie=" + Serie + " and edi.EdiID='" + EdiID + "'") + "~"
                            + "N4*" + utils.dbConsult("SELECT group_concat(ele.Nombre SEPARATOR '*') FROM edi_tbl edi "
                                    + "LEFT JOIN segment_tbl seg oN seg.EdiID=edi.EdiID and seg.Nombre='N4' "
                                    + "LEFT JOIN element_tbl ele ON ele.SegmentID=seg.SegmentID "
                                    + "WHERE seg.Serie=" + Serie + " and edi.EdiID='" + EdiID + "'") + "~";
                }
                String L11_SN = "";
                String Sello = utils.dbConsult("SELECT NumeroSello FROM workcontenedores_tbl where WorkOrderID=" + WOrderID + " limit 1");
                if (ShipmentAppointmentStatusCodes.equalsIgnoreCase("No Aplica") && !Sello.equals("")) {
                    L11_SN = "L11*" + Sello + "*SN~";//aplicar?
                }//sello? 
                String IDE = "ISA*00*          *00*          *02*CXPJ           *01*" + ApplSender + "*" + Fecha2 + "*" + Hora + "*U*00401*" + control2 + "*0*P*<~"
                        + "GS*QM*CXPJ*" + ApplSender2 + "*" + Fecha4 + "*" + Hora + "*" + control + "*X*004010~"
                        + "ST*214*" + control2 + "~"
                        + "B10*" + CarrierID + "*" + utils.dbConsult("SELECT ele.Nombre "
                                + "FROM segment_tbl seg "
                                + "LEFT JOIN element_tbl ele ON ele.SegmentID=seg.SegmentID "
                                + "WHERE seg.Nombre='B2' and seg.Serie='0' and ele.Elemento='B204' and seg.EdiID='" + EdiID + "'") + "*CXPJ~"
                        + L11_SN
                        + DireccionOrigen
                        + DireccionDestino
                        + "MS3*CXPJ*O**M~"
                        + "LX*1~"
                        + "AT7*" + jCSASC + "" + FechaShip + "*" + HoraShipment + "*PT~"
                        + MS1
                        + //        "MS1*"//STOCKTON*CA~"+// lugar del camion? DESTINO!
                        //                + ""+utils.dbConsult("SELECT group_concat(ele.Nombre SEPARATOR '*') FROM edi_tbl edi \n" +
                        //    "                LEFT JOIN segment_tbl seg oN seg.EdiID=edi.EdiID and seg.Nombre='N4' \n" +
                        //    "                LEFT JOIN element_tbl ele ON ele.SegmentID=seg.SegmentID \n" +
                        //    "                WHERE seg.Serie="+Destino+" and ele.Elemento!='N403' and edi.EdiID='"+EdiID+"'")+"~" +
                        //para el equipment number es (SELECT Contenedor from workcontenedores_tbl where WContenedorID = 'work_order')
                        "MS2*CXPJ*" + utils.dbConsult("SELECT Contenedor from workcontenedores_tbl where WContenedorID = " + WOrderID) + "~"//CXT-5510
                        + // camion?
                        "L11*" + Serie + "*QN~"
                        + "AT8*G*L*" + utils.dbConsult("SELECT ele.Nombre FROM segment_tbl seg "
                                + "left join element_tbl ele on ele.SegmentID=seg.SegmentID "
                                + "WHERE seg.EdiID=" + EdiID + " and seg.Serie=" + Serie + " and ele.Elemento='S503' and seg.Nombre='S5'")/*WEIGHT*/ + "*"
                        + "" + utils.dbConsult("SELECT ele.Nombre FROM segment_tbl seg "
                                + "left join element_tbl ele on ele.SegmentID=seg.SegmentID "
                                + "WHERE seg.EdiID=" + EdiID + " and ele.Elemento='L311' and seg.Nombre='L3'")/*Lading Quantity*/ + "~";
//        utils.dbConsult("SELECT count()");
                IDE = IDE
                        + "SE*16*" + control2 + "~"
                        + "GE*1*" + control + "~"
                        + "IEA*1*" + control2 + "~";

                utils.dbInsert("INSERT INTO edis_anexos_tbl (Tipo,Archivo,EdiID,Control,UsuarioID,ShipmentAppointmentStatusCodes,ShipmentStatusCodes,Reason, ItinerarioID) "
                        + "VALUES('214','" + control + "-214.edi','" + EdiID + "','" + control + "'," + global.usuario + ",'" + ShipmentAppointmentStatusCodes + "','"
                        + shipmentstatuscodes + "',"
                        + "'" + Reason + "', '" + itiid + "')");
                try {
                    File myObj = new File("" + global.rutaEDI + "\\" + control + "-214.edi");
                    if (myObj.createNewFile()) {
                        System.out.println("File created: " + myObj.getName());
                    } else {
                        System.out.println("File already exists.");
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
                try {
                    FileWriter myWriter = new FileWriter("" + global.rutaEDI + "\\" + control + "-214.edi");
                    myWriter.write(IDE);
                    myWriter.close();
                    System.out.println(IDE);
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            } else {
                System.out.println("Seleccionar Serie (Se encuentra en Segment_tbl), debe ser mayor a 0");
            }
        } else {
            Message = "Debe tener una Work Order relacionada.";
        }
        return Message;
    }

    public static void revisarCargaWebService(String wcont) {

        String referencia = utils.dbConsult("SELECT referenciaccp from workcontenedores_tbl where WContenedorID = '" + wcont + "'");
        String clientews = utils.dbConsult("SELECT (SELECT NombrewsMerca from clientes_tbl where clientes_tbl.ClienteID = clientefk) from workcontenedores_tbl where WContenedorID = '" + wcont + "'");
        String up = utils.dbUpdate("UPDATE workcontenedores_cartaporte SET WContID = '" + wcont + "' where WContID = 0 and (SELECT tcp.id from tbl_carta_porte_usuario_externo as tcp where NoOrden = '" + referencia + "' and Empresa = '" + clientews + "' and tcp.id = workcontenedores_cartaporte.id_carta_porte limit 1) is not null ");
        if (!up.isEmpty()) {

        }

    }

    public static String agregarArchivo(File file, String woid) {
        String extencion = FileUtils.getFileExtPart(file.getAbsolutePath(), false), err = "";
        String id = utils.dbInsert("INSERT INTO workcfiles_tbl (WorkOID, Nombre, Archivo, Extension, APath, UsuarioID) "
                + "VALUES(" + woid + ",'" + file.getName() + "', '" + FileUtils.getFileNamePart(file) + "', '" + extencion + "', '', '" + global.usuario + "')");

        File bfile = new File(global.carpetaevidencias + id + "." + extencion);
        if (FileUtils.copyFile(file, bfile)) {
            utils.dbUpdate("UPDATE workcfiles_tbl SET APath = '" + utils.FiletoDB(global.carpetaevidencias + id + "." + extencion) + "' where FileID = '" + id + "'");
        } else {
            utils.dbUpdate("UPDATE workcfiles_tbl SET Status = false where FileID = '" + id + "'");
            err = "Archivo no se pudo cargar al sistema";
        }
        return err;
    }


    public static void cargarBonoAntiguedadporID(String itiids) {
//SELECT IF(ChoferUSA = true, getBonoAntiguedad(rutid, chofid, estadocarga, bobt), 0) from choferes_tbl where ChoferID = chofid
        String query = "SELECT ItinerarioID,"
                + "RutaID,"
                + "ChoferID,"
                + "getBonoAntiguedad(RutaID, ChoferID, Estado, Bobtail) as bono "
                + "from itinerarios_tbl "
                + "where ItinerarioID in (" + itiids + ") and "
                //                + "(SELECT ChoferUSA from choferes_tbl where choferes_tbl.ChoferID = itinerarios_tbl.ChoferID) = true and "
                + "IFNULL((SELECT CajachicaID from cajachica_tbl as cc where cc.ItinerarioID = itinerarios_tbl.ItinerarioID and cc.Status = true and BonoAntiguedad = true),0) = 0 ";
//IF(Status = true, ChoferID > 0, IFNULL((SELECT DATE(razones_tbl.Fecha) from razones_tbl where razones_tbl.ChoferID = '" + choferesid.get(i) + "' order by RazonID desc limit 1),CURDATE()) >= '" + utils.dateFromFieldtoDBwoH(txtIni.getText()) + "' )
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                if (rs.getFloat("bono") > 0) {
                    String id = utils.dbInsert("INSERT INTO cajachica_tbl (CargoID, Importe, Moneda, Movimiento, UsuarioID, Fecha,Status, ItinerarioID, Descripcion, BonoAntiguedad) "
                            + "VALUES('0','" + rs.getString("bono") + "','1','1','0',(now()),true,'" + rs.getString("ItinerarioID") + "', IFNULL((SELECT Descripcion from antiguedadc_tbl where TarifaID = IFNULL((SELECT rutas_tbl.TarifaID from rutas_tbl where rutas_tbl.RutaID = '" + rs.getString("RutaID") + "'),0) and Anios <= IFNULL((SELECT TIMESTAMPDIFF(YEAR, DATE(Ingreso),CURDATE()) from choferes_tbl where ChoferID = '" + rs.getString("ChoferID") + "'),0) and Status = true order by Anios desc, AntiguedadID desc limit 1),'DRIVER'), true)");
                }
            }

        } catch (SQLException e) {
            utils.errorGenerado("funcionestrafico / cargarbonoantiguedadporid / sqlex = " + e.toString());
//            JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }

    }

    //SELECT ItinerarioID from itinerarios_tbl where Status >= 3 and (SELECT ChoferUSA from choferes_tbl where choferes_tbl.ChoferID = itinerarios_tbl.ChoferID) = false and Carga>= '2021-05-03 00:00:00'
    public static void cargarBonoAntiguedadporID() {
//SELECT IF(ChoferUSA = true, getBonoAntiguedad(rutid, chofid, estadocarga, bobt), 0) from choferes_tbl where ChoferID = chofid
        String query = "SELECT ItinerarioID,"
                + "RutaID,"
                + "ChoferID,"
                + "getBonoAntiguedad(RutaID, ChoferID, Estado, Bobtail) as bono "
                + "from itinerarios_tbl "
                + "where ItinerarioID in (SELECT iti.ItinerarioID from itinerarios_tbl as iti where iti.Status >= 3 and (SELECT ChoferUSA from choferes_tbl where choferes_tbl.ChoferID = iti.ChoferID) = false and iti.Carga>= '2021-05-03 00:00:00') and "
                //                + "(SELECT ChoferUSA from choferes_tbl where choferes_tbl.ChoferID = itinerarios_tbl.ChoferID) = true and "
                + "IFNULL((SELECT CajachicaID from cajachica_tbl as cc where cc.ItinerarioID = itinerarios_tbl.ItinerarioID and cc.Status = true and BonoAntiguedad = true),0) = 0 ";
//IF(Status = true, ChoferID > 0, IFNULL((SELECT DATE(razones_tbl.Fecha) from razones_tbl where razones_tbl.ChoferID = '" + choferesid.get(i) + "' order by RazonID desc limit 1),CURDATE()) >= '" + utils.dateFromFieldtoDBwoH(txtIni.getText()) + "' )
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                if (rs.getFloat("bono") > 0) {
                    String id = utils.dbInsert("INSERT INTO cajachica_tbl (CargoID, Importe, Moneda, Movimiento, UsuarioID, Fecha,Status, ItinerarioID, Descripcion, BonoAntiguedad) "
                            + "VALUES('0','" + rs.getString("bono") + "','1','1','0',(now()),true,'" + rs.getString("ItinerarioID") + "', IFNULL((SELECT Descripcion from antiguedadc_tbl where TarifaID = IFNULL((SELECT rutas_tbl.TarifaID from rutas_tbl where rutas_tbl.RutaID = '" + rs.getString("RutaID") + "'),0) and Anios <= IFNULL((SELECT TIMESTAMPDIFF(YEAR, DATE(Ingreso),CURDATE()) from choferes_tbl where ChoferID = '" + rs.getString("ChoferID") + "'),0) and Status = true order by Anios desc, AntiguedadID desc limit 1),'DRIVER'), true)");
                }
            }

        } catch (SQLException e) {
            utils.errorGenerado("funcionestrafico / cargarbonoantiguedadporid / sqlex = " + e.toString());
//            JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }

    }

    public static String insertarCargaCombustible(String fechacarga, String driverid,
            String transid, String camionid, String odometro, String volumen, String preciounitario, String estado, String bomba, String locid) {
        if (Float.parseFloat(utils.dbConsult("SELECT digits('" + volumen + "')")) > 0) {
            String recorridocarga = "0", recorridoruta = "0";
            if (!camionid.equals("0")) {
                String existeanterior = utils.dbConsult("SELECT IFNULL((SELECT CargaID from cargasdiesel_tbl where Tipo = 0 and UnidadID = '" + camionid + "' and CONCAT(Fecha,' ',Hora) < '" + fechacarga + "' and Status = true order by Fecha desc limit 1),0)");
                String conanterior = "";
                if (!existeanterior.equals("0")) {
                    conanterior = " and Carga > (SELECT FechayHora from cargasdiesel_tbl where CargaID = '" + existeanterior + "') ";
                }
                recorridocarga = utils.dbConsult("SELECT IFNULL(" + odometro.replace(",", "") + "-IFNULL((SELECT OdometroCarga from cargasdiesel_tbl where CargaID = '" + existeanterior + "'),0),0)");
                recorridoruta = utils.dbConsult("SELECT SUM((SELECT KM from rutas_Tbl where rutas_tbl.RutaID = itinerarios_tbl.RutaID)) from itinerarios_tbl where CamionID = '" + camionid + "' " + conanterior + " and Carga <= '" + fechacarga + "'");
                String id = utils.dbInsert("INSERT INTO cargasdiesel_tbl(Tipo, UnidadID,Litros,Folio,Nota,Fecha,Hora, FechayHora,Status,ProveedorID,CantidadActual,MetodoPago, SerieBomba,RendimientoCarga,RecorridoCarga, RecorridoRutas, RendimientoRutas, OdometroCarga, Sellos, UsuarioID ) "
                        + "VALUES('0', '" + camionid + "',(SELECT digits('" + volumen + "')),'" + transid + "','FromWebService','" + fechacarga.split(" ")[0] + "','" + fechacarga.split(" ")[1] + "','" + fechacarga + "'"
                        + ",true,'0',(SELECT TanqueActual from camiones_tbl where CamionID = '" + camionid + "' )"
                        + ",'0','" + bomba + "',(SELECT " + recorridocarga + "/" + volumen + "),(SELECT digits('" + recorridocarga + "')),(SELECT digits('" + recorridoruta + "')), (SELECT " + recorridoruta + "/" + volumen + "), (SELECT digits('" + odometro + "')), '', '" + global.usuario + "' )");
                if (id.length() > 11) {
                    return id + "\nTransID: " + transid + ".";
                } else {
                    String tanqueStr = utils.dbConsult("(SELECT IFNULL(TanqueActual,0)  from camiones_tbl where CamionID = '" + camionid + "' )");
                    float tanqueActual = Float.parseFloat(tanqueStr);
                    if(volumen.isEmpty()){
                        volumen = "0";
                    }
                    float galonesFloat = Float.parseFloat(volumen);
                  utils.dbUpdate("UPDATE camiones_tbl SET TanqueActual = "+(galonesFloat + tanqueActual)+" where CamionID = " + camionid + " ");
                    if (!existeanterior.equals("0")) {//checo que sea haya carga anterior, de lo contrario no tengo como calcular el rendimiento, porque no se cuanto ni cuando fue la carga
                        utils.dbUpdate("UPDATE itinerarios_tbl SET RendimientoDiesel = '" + recorridocarga + "', CargaDieselID = '" + id + "' "
                                + "where CamionID = '" + camionid + "' and Carga > (SELECT CONCAT(Fecha,' ',Hora) from cargasdiesel_tbl where CargaID = '" + existeanterior + "') and Carga <= (SELECT CONCAT(Fecha,' ',Hora) from cargasdiesel_tbl where CargaID = '" + id + "')  ");
                    }
                    return id;
                }
            } else {
                return "El numero de tarjeta no esta asignado a ninguna unidad, favor de revisar el alta de unidades, TransID: " + transid + ".";
            }
        } else {
            return "Favor de ingresar una carga valida (Galones > 0), TransID: " + transid + ".";
        }
    }
public static String insertarCargaCombustibleWeb(String fechacarga, String driverid,
            String transid, String camionid, String odometro, String volumen, String preciounitario, String estado, String bomba, String locid,String recorrido) {
        if (Float.parseFloat(utils.dbConsult("SELECT digits('" + volumen + "')")) > 0) {
            String recorridocarga = "0";
            if (!camionid.equals("0")) {
                String existeanterior = utils.dbConsult("SELECT IFNULL((SELECT CargaID from cargasdiesel_tbl where Tipo = 0 and UnidadID = '" + camionid + "' and CONCAT(Fecha,' ',Hora) < '" + fechacarga + "' and Status = true order by Fecha desc limit 1),0)");
                String conanterior = "";
                if (!existeanterior.equals("0")) {
                    conanterior = " and Carga > (SELECT FechayHora from cargasdiesel_tbl where CargaID = '" + existeanterior + "') ";
                }
              String recorridoruta = utils.dbConsult("SELECT SUM((SELECT KM from rutas_Tbl where rutas_tbl.RutaID = itinerarios_tbl.RutaID)) from itinerarios_tbl where CamionID = '" + camionid + "' " + conanterior + " and Carga <= '" + fechacarga + "'");
               String id = utils.dbInsert("INSERT INTO cargasdiesel_tbl(Tipo, UnidadID,Litros,Folio,Nota,Fecha,Hora, FechayHora,Status,ProveedorID,CantidadActual,MetodoPago, SerieBomba,RendimientoCarga,RecorridoCarga, RecorridoRutas, RendimientoRutas, OdometroCarga, Sellos, UsuarioID ) "
                        + "VALUES('0', '" + camionid + "',(SELECT digits('" + volumen + "')),'" + transid + "','FromWebService','" + fechacarga.split(" ")[0] + "','" + fechacarga.split(" ")[1] + "','" + fechacarga + "'"
                        + ",true,'0',(SELECT TanqueActual from camiones_tbl where CamionID = '" + camionid + "' )"
                        + ",'0','" + bomba + "',(SELECT " + recorridocarga + "/" + volumen + "),(SELECT digits('" + recorridocarga + "')),(SELECT digits('" + recorridoruta + "')), (SELECT " + recorridoruta + "/" + volumen + "), (SELECT digits('" + odometro + "')), '', '" + global.usuario + "' )");
                if (id.length() > 11) {
                    return id + "\nTransID: " + transid + ".";
                } else {
                    String tanqueStr = utils.dbConsult("(SELECT IFNULL(TanqueActual,0)  from camiones_tbl where CamionID = '" + camionid + "' )");
                    float tanqueActual = Float.parseFloat(tanqueStr);
                    if(volumen.isEmpty()){
                        volumen = "0";
                    }
                    float galonesFloat = Float.parseFloat(volumen);
                  utils.dbUpdate("UPDATE camiones_tbl SET TanqueActual = "+(galonesFloat + tanqueActual)+" where CamionID = " + camionid + " ");
                   if (!existeanterior.equals("0")) {//checo que sea haya carga anterior, de lo contrario no tengo como calcular el rendimiento, porque no se cuanto ni cuando fue la carga
                        utils.dbUpdate("UPDATE itinerarios_tbl SET RendimientoDiesel = '" + recorridocarga + "', CargaDieselID = '" + id + "' "
                                + "where CamionID = '" + camionid + "' and Carga > (SELECT CONCAT(Fecha,' ',Hora) from cargasdiesel_tbl where CargaID = '" + existeanterior + "') and Carga <= (SELECT CONCAT(Fecha,' ',Hora) from cargasdiesel_tbl where CargaID = '" + id + "')  ");
                    }  
                  return id;
                }
            } else {
                return "El numero de tarjeta no esta asignado a ninguna unidad, favor de revisar el alta de unidades, TransID: " + transid + ".";
            }
        } else {
            return "Favor de ingresar una carga valida (Galones > 0), TransID: " + transid + ".";
        }
    }

}
