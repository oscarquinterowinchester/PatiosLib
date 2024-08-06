/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulespat;

import basic.Config;
import basic.FillCombo;
import basic.SendEmail;
import basic.WiderCombo;
import basic.global;
import basic.utils;
import basicop.ComboBoxCellEditor;
import basicop.InfoRemolque;
import com.alee.extended.date.WebDateField;
import com.alee.managers.tooltip.TooltipManager;
import com.alee.managers.tooltip.TooltipWay;
import com.alee.utils.FileUtils;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import modulescxc.dNuevoDoc;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import others.ConfigLib;

/**
 *
 * @author Alex
 */
public class ItinerarioGuardiasContExternos2 extends javax.swing.JFrame {

    /**
     * Creates new form Relacion
     */
    DefaultTableModel model1;
    DefaultTableModel mens;
    DefaultTableModel mtij;
    DefaultTableModel mmxl;
    DefaultTableModel mfora;
    DefaultTableModel mher;
    DefaultTableModel tablemodel9;
    DefaultTableModel mcargoscliente;
    DefaultTableModel tablemodel15;
    DefaultTableModel contenedor_tablemodel;
    DefaultTableModel tablemodelRango;
    DefaultTableModel facturacion_tablemodel;
    DefaultTableModel mpuntos;
    DefaultTableModel equipmentctrl;
    DefaultTableModel modelDm;

    //JTable CTRL = new JTable();
    int cont = 0;
    ArrayList<String> cajasid = new ArrayList<>();
    ArrayList<String> tablacajasid = new ArrayList<>();
    ArrayList<String> camionid = new ArrayList<>();
    ArrayList<String> tablacamionid = new ArrayList<>();
    ArrayList<String> rutasid = new ArrayList<>();
    ArrayList<String> rutasrepid = new ArrayList<>();
    ArrayList<String> choferesid = new ArrayList<>();
    ArrayList<String> tablachoferesid = new ArrayList<>();
    ArrayList<String> clientesid = new ArrayList<>();
    ArrayList<String> clientesrepid = new ArrayList<>();
    ArrayList<String> savesubcliente = new ArrayList<>();
    ArrayList<String> savesubruta = new ArrayList<>();
    ArrayList<String> subitinerarioid = new ArrayList<>();
    ArrayList<String> asignacionid = new ArrayList<>();
    ArrayList<String> cargosid = new ArrayList<>();
    ArrayList<String> workid = new ArrayList<>();
    ArrayList<String> chasisid = new ArrayList<>();
    ArrayList<String> comboworkid = new ArrayList<>();
    ArrayList<String> dollysid = new ArrayList<>();
    ArrayList<String> generadorid = new ArrayList<>();
    ArrayList<String> rutasfacid = new ArrayList<>();
    ArrayList<String> icontid = new ArrayList<>();
    ArrayList<String> icontregreso = new ArrayList<>();
    ArrayList<String> icontstatus = new ArrayList<>();
    ArrayList<String> tipoicont = new ArrayList<>();
    ArrayList<String> statusviaticos = new ArrayList<>();
    ArrayList<String> savemoneda = new ArrayList<>();
    ArrayList<String> contcargoid = new ArrayList<>();
    //ArrayList<Boolean> otob = new ArrayList<>();
    ArrayList<String> buttons = new ArrayList<>();
    ArrayList<String> justaddedpaths = new ArrayList<>();
    ArrayList<String> rutawo = new ArrayList<>();
    ArrayList<String> nuevarutawo = new ArrayList<>();
    ArrayList<String> tarifaid = new ArrayList<>();
    ArrayList<String> tarifacobroid = new ArrayList<>();
    ArrayList<String> wcontenedorid = new ArrayList<>();
    ArrayList<String> patiosalidaid = new ArrayList<>();
    ArrayList<String> patiollegadaid = new ArrayList<>();
    ArrayList<String> cajachicaid = new ArrayList<>();
    ArrayList<String> savestatmov = new ArrayList<>();
    ArrayList<String> savestat = new ArrayList<>();
    ArrayList<String> estadosid = new ArrayList<>();
    ArrayList<String> estadosid1 = new ArrayList<>();
    ArrayList<String> chofereslocalid = new ArrayList<>();
    ArrayList<String> contenedorid = new ArrayList<>();
    ArrayList<String> clienteid = new ArrayList<>();
    ArrayList<String> Anteriorid = new ArrayList<>();
    ArrayList<String> inventariosalidaid = new ArrayList<>();
    ArrayList<String> cliente1id = new ArrayList<>();
    ArrayList<String> archivoc = new ArrayList<>();
    ArrayList<String> cajachasisboxID = new ArrayList<>();
    ArrayList<String> dmId = new ArrayList<>();

    ArrayList<String> inventarioID = new ArrayList<>();
    ArrayList<String> itinerarioID = new ArrayList<>();
    ArrayList<String> icontID = new ArrayList<>();

    ArrayList<String> patiosid = new ArrayList<>();

    List contenedores = new ArrayList<String>();
    ArrayList<String> foliosid = new ArrayList<>();
    ArrayList<String> usoid = new ArrayList<>();

    ArrayList<InfoRemolque> notsaved = new ArrayList<>();
    ArrayList<String> PatioID = new ArrayList<>();

    String wcont = "0", wosel = "0", rutaaltaid;
    int rowicont = -1;
    WiderCombo boxRutas = new WiderCombo();
    WiderCombo boxChoferes = new WiderCombo();
    WiderCombo boxCamion = new WiderCombo();
    WiderCombo boxStatus = new WiderCombo();
    WiderCombo boxMoneda = new WiderCombo();
    WiderCombo boxClientes = new WiderCombo();
    WiderCombo boxStatusViaticos = new WiderCombo();
    WiderCombo boxContenedorCargo = new WiderCombo();
    WiderCombo boxFolio = new WiderCombo();
    WiderCombo boxRutaNC = new WiderCombo();
    WiderCombo boxTipoCont = new WiderCombo();
    JComboBox boxDirecciones = new JComboBox();
    JComboBox boxTipoMov = new JComboBox();
    JComboBox boxEstadoCont = new JComboBox();
    JFormattedTextField txtInicio = new JFormattedTextField();
    JFormattedTextField txtFinal = new JFormattedTextField();

    WebDateField hoy = new WebDateField(new Date());

    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");

    boolean doneload = false, ingresarchofer = false, generated = false, EquipmentControlGenerado = false, capturaFull = false, bandLoad = false;
    String iticont = "0", invid = "0", basefull = "";
    int origenruta = 0;
    boolean tractorInventario =  false;
    //estado entre to y size
    //cus ref entre el te y pu 

    int t1folio = 0, t1entsal = 1, t1cliente = 2, t1eqp = 3, t1chofer = 4, t1size = 5, t1origen = 6, t1contenedor = 7, t1placas = 8, t1lugarplacas = 9, t1emlo = 10, t1entrada = 11, /*t1salida = 8, */ t1sello = 12, t1remark = 13;
    int inicioids = 19;
    int cclconcepto = 0, cclfromto = 1, cclcantidad = 2, cclimporte = 3, cclmoneda = 4, cclfecha = 5;

    String WContID = "0", AnteriorID = "0", InventarioSalidaID = "", ItiID = "0";

    ArrayList<String> choferidx = new ArrayList<>();
    ArrayList<String> camionidx = new ArrayList<>();
    ArrayList<String> rutaidx = new ArrayList<>();

    ArrayList<String> BoxClienteID = new ArrayList<>();
    ArrayList<String> BoxCliente1ID = new ArrayList<>();
    ArrayList<String> PerfilID = new ArrayList<>();
    ArrayList<String> clientePerfilID = new ArrayList<>();
    ArrayList<String> perfilBuscadorID = new ArrayList<>();
    ArrayList<String> perfilBuscador1ID = new ArrayList<>();
    ArrayList<String> rangoID = new ArrayList<>();
    ArrayList<String> boxcontenedorxID = new ArrayList<>();

    WebDateField fi = new WebDateField(new Date());
    WebDateField ff = new WebDateField(new Date());

    WebDateField txtFLlegada2 = new WebDateField(new Date());

    WebDateField WebFLlegada = new WebDateField(new Date());

    boolean fist_time = false;

    String QUERY = "", QUERYAMPL = "";

    boolean esfactura = true;

    String SelectedInventarioID = "0", SelectedItinerarioID = "0";

    DefaultTableModel maccesos;
    ArrayList<Integer> accesoid = new ArrayList<>();
    ArrayList<Integer> accesotid = new ArrayList<>();

    ArrayList<String> facturacionID = new ArrayList<>();

    ArrayList<String> puntosID = new ArrayList<>();
    ArrayList<String> fotoe = new ArrayList<>();
    ArrayList<String> fotos = new ArrayList<>();
    File filepdf = null, filepdf2 = null;

    ArrayList<String> codigoes = new ArrayList<>();
    ArrayList<String> estxid = new ArrayList<>();

    public ItinerarioGuardiasContExternos2() {
        try {
            initComponents();

            jLabel106.setVisible(false);
            grade.setVisible(false);
            jComboBox1.setVisible(false);
            jToggleButton1.setVisible(false);
            statz1.setVisible(false);

            jButton19.setVisible(false);
            Fullx.setVisible(false);
            Fullx1.setVisible(false);
            icont_contenedor.setVisible(false);

            ConfigLib.WebDate(bcamion, 14);
            ConfigLib.WebDate(bremolque, 14);
            ConfigLib.WebDate(bchasis, 14);

            jCheckBox6.setVisible(false);
            jButton15.setVisible(false);

            jButton1.setVisible(false);
            jCheckBox2.setVisible(false);

            String superUser = utils.dbConsult("select ifnull(superUser,'') from usuarios_tbl where usuarioID='" + global.usuario + "'");

            if (!superUser.equals("")) {
                bContenedores3.setVisible(true);
                yv.setVisible(true);
            } else {
                bContenedores3.setVisible(false);
                yv.setVisible(false);
            }

            ConfigLib.WebDate(fi, 12);
            ConfigLib.WebDate(ff, 12);
            ConfigLib.WebDate(txtFLlegada2, 12);

            fi.setHorizontalAlignment(SwingConstants.CENTER);
            fi.setPreferredWidth(100);
            fi.setPreferredHeight(22);
            fi.setFont(new Font("Tahoma", Font.PLAIN, 12));
            fi.setEditable(false);
            jPanel16.add(fi);
            jPanel16.setOpaque(false);

            txtFLlegada2.setHorizontalAlignment(SwingConstants.CENTER);
            txtFLlegada2.setPreferredWidth(100);
            txtFLlegada2.setPreferredHeight(30);
            txtFLlegada2.setFont(new Font("Tahoma", Font.PLAIN, 12));
            txtFLlegada2.setEditable(false);
            jPanel30.add(txtFLlegada2);
            jPanel30.setOpaque(false);

            ff.setHorizontalAlignment(SwingConstants.CENTER);
            ff.setPreferredWidth(100);
            ff.setPreferredHeight(22);
            ff.setFont(new Font("Tahoma", Font.PLAIN, 12));
            ff.setEditable(false);
            jPanel17.add(ff);
            jPanel17.setOpaque(false);

            boxTipoMov.addItem("Descuento");
            boxTipoMov.addItem("Bono");

            boxMoneda.addItem("MXN");
            boxMoneda.addItem("6USD");

            boxEstadoCont.addItem("Empty");
            boxEstadoCont.addItem("Load");
            boxStatus.addItem("Por Partir");
            Config.setDatePicker(txtFechafin, 14);
            Config.setDatePicker(txtFechaini, 14);

            Config.setFormattedHM(txtInicio, 14);
            Config.setFormattedHM(txtFinal, 14);

            boxRutas.addItem("Rutas");
            jLabel8.setIcon(new ImageIcon("images\\itinerarioguardiahead.png"));
            model1 = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            mens = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            mtij = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            mmxl = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            mfora = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            mher = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            tablemodel9 = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    if (column == 0) {
                        return true;
                    } else {
                        return false;
                    }
                }
            };

            mcargoscliente = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    if (column >= cclfecha) {
                        return false;
                    }
                    return true;
                }
            };

            tablemodel15 = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            modelDm = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            modelDm.addColumn("Drive Move");
            tDm.setModel(modelDm);

            contenedor_tablemodel = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            mpuntos = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            mpuntos.addColumn("Puntos");
            mpuntos.addColumn("Check");
            mpuntos.addColumn("Comentario Entrada");
            mpuntos.addColumn("Foto Entrada");
            mpuntos.addColumn("Comentario Salida");
            mpuntos.addColumn("Foto Salida");
            puntos.setModel(mpuntos);

            puntos.getColumnModel().getColumn(0).setPreferredWidth(300);
            Config.setTable(puntos);
            Config.addCheck(puntos, 1);

            contenedor_tablemodel.addColumn("Contenedor");
            contenedor_tablemodel.addColumn("Ruta");
            contenedor_tablemodel.addColumn("Chofer");
            contenedor_tablemodel.addColumn("Cliente");
            contenedor_tablemodel.addColumn("Driver Move");
            contenedor_tbl.setModel(contenedor_tablemodel);

            tablemodel15.addColumn("No Economico");
            tablemodel15.addColumn("Ubicacion");
            tUnidades.setModel(tablemodel15);

            mcargoscliente.addColumn("Concepto");
            mcargoscliente.addColumn("From - To");
            mcargoscliente.addColumn("Cantidad");
            mcargoscliente.addColumn("Importe");
            mcargoscliente.addColumn("Moneda");
            mcargoscliente.addColumn("Fecha");
            tCargosCliente.setModel(mcargoscliente);

            //tablemodel9.addColumn("Direccion");
            mher.addColumn("Chofer");
            mher.addColumn("Ruta");
            mher.addColumn("Fecha");
            tHermosillo.setModel(mher);

            mfora.addColumn("Chofer");
            mfora.addColumn("Ruta");
            mfora.addColumn("Fecha");
            tForaneo.setModel(mfora);

            String tipocambio = utils.dbConsult("SELECT FORMAT(TipoCambio,2) from utils_tbl");
            mmxl.addColumn("Chofer");
            mmxl.addColumn("Disponible");
            mmxl.addColumn("MN a 7 dias, TC " + tipocambio);
            mmxl.addColumn("MN a 15 dias, TC " + tipocambio);
            tMexicali.setModel(mmxl);

            mtij.addColumn("Chofer");
            mtij.addColumn("Disponible");
            mtij.addColumn("MN a 7 dias, TC " + tipocambio);
            mtij.addColumn("MN a 15 dias, TC " + tipocambio);
            tTijuana.setModel(mtij);

            mens.addColumn("Chofer");
            mens.addColumn("Disponible");
            mens.addColumn("MN a 7 dias, TC " + tipocambio);
            mens.addColumn("MN a 15 dias, TC " + tipocambio);
            tEnsenada.setModel(mens);

            /*model1.addColumn("#");
             model1.addColumn("Ent/Sal");
             model1.addColumn("Cliente");
             model1.addColumn("Equipment Prov.");
             model1.addColumn("Chofer");
             model1.addColumn("Size");
             model1.addColumn("Origen");
             model1.addColumn("Contenedor");
             model1.addColumn("Placas");
             model1.addColumn("Lugar Placas");
             model1.addColumn("E/L");
             model1.addColumn("Fecha");
             model1.addColumn("Sello");
             model1.addColumn("Remark");*/
            model1.addColumn("#");
            model1.addColumn("In/Out");
            model1.addColumn("Truck");
            model1.addColumn("Equipment");
            model1.addColumn("Plates");
            model1.addColumn("State");
            model1.addColumn("E/L");
            model1.addColumn("EQ.Provider");
            model1.addColumn("Customer");
            model1.addColumn("Date In");
            model1.addColumn("Time In");
            model1.addColumn("Days");
            model1.addColumn("Origin");
            model1.addColumn("Grade");
            model1.addColumn("Assigned To");
            model1.addColumn("Remark");
            model1.addColumn("Folio");
            model1.addColumn("Relacion");
            model1.addColumn("Ultima Loc.");
            model1.addColumn("Perfil Cobro");
            model1.addColumn("Sello");
            model1.addColumn("Estatus");
            model1.addColumn("Contenedor2");

            jTable1.setModel(model1);

            jTable1.getColumnModel().getColumn(14).setPreferredWidth(350);

            /*jTable1.getColumnModel().getColumn(t1folio).setPreferredWidth(30);
             jTable1.getColumnModel().getColumn(t1chofer).setPreferredWidth(200);
             jTable1.getColumnModel().getColumn(t1size).setPreferredWidth(40);
             jTable1.getColumnModel().getColumn(t1contenedor).setPreferredWidth(40);
             jTable1.getColumnModel().getColumn(t1placas).setPreferredWidth(40);
             jTable1.getColumnModel().getColumn(t1lugarplacas).setPreferredWidth(40);
             jTable1.getColumnModel().getColumn(t1emlo).setPreferredWidth(40);
             jTable1.getColumnModel().getColumn(t1entrada).setPreferredWidth(70);
             //jTable1.getColumnModel().getColumn(t1salida).setPreferredWidth(70);
             jTable1.getColumnModel().getColumn(t1sello).setPreferredWidth(70);
             jTable1.getColumnModel().getColumn(t1remark).setPreferredWidth(100);*/
            jTable1.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 12));

            tCargosCliente.getColumnModel().getColumn(cclmoneda).setCellEditor(new ComboBoxCellEditor(boxMoneda));

            jTable1.getColumnModel().getColumn(t1chofer).setCellEditor(new ComboBoxCellEditor(boxChoferes));

            tablemodel9.addColumn("Concepto");
            tablemodel9.addColumn("Archivo");
            jTable9.setModel(tablemodel9);

            jTable1.getModel().addTableModelListener(new TableModelListener() {
                @Override
                public void tableChanged(TableModelEvent e) {
                    tableListener(e);
                }
            });

            tCargosCliente.getModel().addTableModelListener(new TableModelListener() {
                @Override
                public void tableChanged(TableModelEvent e) {
                    tableListener10(e);
                }
            });

            jTable9.getModel().addTableModelListener(new TableModelListener() {
                @Override
                public void tableChanged(TableModelEvent e) {
                    tableListener9(e);
                }
            });

            jTable1.putClientProperty("terminateEditOnFocusLost", true);
            tCargosCliente.putClientProperty("terminateEditOnFocusLost", true);

            FillCombo.cargarEstadosAbreviacion(estx, estxid, " ", "" + (paisx.getSelectedIndex() + 1));
            //FillCombo.cargarEstados(estx, estxid, codigoes, " ", (paisx.getSelectedIndex() + 1) + "");

            FillCombo.cargarClientes(boxClientes, clientesid, "");
            FillCombo.cargarClientes(boxClienteRep, clientesrepid, "Todos los clientes");
            FillCombo.cargarStatusViaticos(boxStatusViaticos, statusviaticos, "");
            //FillCombo.cargarStatusMov(boxStatusMov, statusmov, "Todos");
            FillCombo.cargarTipoICont(boxTipoCont, tipoicont, "");
            FillCombo.cargarTamano(boxTamano, new ArrayList<>(), "");
            FillCombo.cargarClientes(cliente, clienteid, "");
            FillCombo.cargarClientes(clientePerfil, clientePerfilID, "");
            //cargarClientesLocaTo();
            //FillCombo.cargarClientesBillTo(cliente, clienteid, "");
            FillCombo.cargarClientesExternos(cliente1, cliente1id, ""); //

            FillCombo.cargarCamionesPatio(camionx, camionidx, "EXTERNO");

            FillCombo.cargarCajas(cajachasisbox, cajachasisboxID, "Ninguno");

            //txtBusquedaReporte.setVisible(false);
            txtFechaini.setVisible(false);
            txtFechafin.setVisible(false);
            boxClienteRep.setVisible(false);
            boxRutasRep.setVisible(false);

            cargarRutas("0");
            System.out.println("rutas");

            System.out.println("generadores");
            cargarTabla();
            System.out.println("tabla");

            cargarChoferes();
            cargarChoferesLocales();
            System.out.println("choferes");
            if (global.nivel > 1) {
                boxFiltroStatus.setEnabled(false);
            }

            try {
                fi.addDateSelectionListener(new com.alee.extended.date.DateSelectionListener() {
                    @Override
                    public void dateSelected(Date date) {

                        if (fist_time == true) {
                            cargarTabla();
                        }
                        fist_time = true;
                    }
                });
            } catch (Exception d) {
                System.out.println("d:" + d);
            }

            try {
                ff.addDateSelectionListener(new com.alee.extended.date.DateSelectionListener() {
                    @Override
                    public void dateSelected(Date date) {

                        if (fist_time == true) {
                            cargarTabla();
                        }
                        fist_time = true;
                    }
                });
            } catch (Exception d) {
                System.out.println("d:" + d);
            }

            doneload = true;
            FillCombo.cargarEstados(boxEstadoPlacas, estadosid, "", 1 + ""); //(boxPaisPlacas.getSelectedIndex() + 1)
            FillCombo.cargarEstados(boxEstadoPlacas1, estadosid1, "", 2 + ""); //(boxPaisPlacas.getSelectedIndex() + 1)

            cargarPatios(BoxPatios, patiosid, "Todos");

            FillCombo.cargarLocaciones(Orgn, rutaidx, "Todos");

            cargarPatios(Patio, PatioID, "");

            cargarClientesAVAILABLE(BoxCliente, BoxClienteID, "Todos");
            cargarClientesAVAILABLEEXT(BoxCliente1, BoxCliente1ID, "Todos"); //
            //cargarClientesLocaToGroup();

            BoxPatios.setSelectedIndex(patiosid.indexOf(utils.dbConsult("select patioID from usuarios_tbl where usuarioID='" + global.usuario + "'")));

            tablemodelRango = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return true;
                }
            };

            tablemodelRango.addColumn("Desde");
            tablemodelRango.addColumn("Hasta");
            tablemodelRango.addColumn("Costo");
            tableRango.setModel(tablemodelRango);

            facturacion_tablemodel = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            facturacion_tablemodel.addColumn("Cliente");
            facturacion_tablemodel.addColumn("Perfil");
            facturacion_tablemodel.addColumn("Factura");
            facturacion_tablemodel.addColumn("Inicio");
            facturacion_tablemodel.addColumn("Fin");
            facturacion_tbl.setModel(facturacion_tablemodel);

            maccesos = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int col) {
                    switch (col) {
                        default:
                            return true;
                    }
                }
            };

            JTableHeader F = taccesos.getTableHeader();
            F.setForeground(new Color(102, 102, 102));
            taccesos.setTableHeader(F);
            taccesos.getTableHeader().setFont(new Font("Myriad Pro", Font.PLAIN, 14));
            taccesos.setFillsViewportHeight(true);

            maccesos.addColumn("Yarda");

            this.taccesos.setModel(maccesos);

            cargarPerfiles();

            boxFiltroStatus.setEnabled(false);

            equipmentctrl = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            equipmentctrl.addColumn("equipo");
            equipmentctrl.addColumn("descripcion");
            equipmentctrl.addColumn("tam.");

            equipmentctrl.addColumn("ubicacion");
            //equipmentctrl.addColumn("despacho");
            equipmentctrl.addColumn("fecha despacho");
            equipmentctrl.addColumn("hora depacho");
            equipmentctrl.addColumn("dias despacho");
            equipmentctrl.addColumn("carga despacho.");
            equipmentctrl.addColumn("cliente despacho");
            equipmentctrl.addColumn("ingreso patio");
            equipmentctrl.addColumn("ubicacion patio");
            equipmentctrl.addColumn("fecha patio");
            equipmentctrl.addColumn("hora patio");
            equipmentctrl.addColumn("dias patio");

            equipmentctrl.addColumn("carga patio");
            equipmentctrl.addColumn("cliente patio");
            equipmentctrl.addColumn("seals.");
            equipmentctrl.addColumn("celula distribuicion.");
            equipmentctrl.addColumn("comentarios");
            equipmentctrl.addColumn("fecha taller");
            equipmentctrl.addColumn("dias taller");
            equipmentctrl.addColumn("status");

            CTRL.setModel(equipmentctrl);

            TooltipManager.setTooltip(txtContenedor, "Filtre un contenedor y presione enter.", TooltipWay.up, 0);
            TooltipManager.setTooltip(contenedor_tbl, "Seleccione un contenedor.", TooltipWay.up, 0);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error " + e, "Error", JOptionPane.ERROR_MESSAGE);
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
    private void tableListener9(javax.swing.event.TableModelEvent e) {
        if (e.getType() == TableModelEvent.UPDATE) {
            if (cont < 1) {
                int row = jTable9.getSelectedRow();
                int col = jTable9.getSelectedColumn();
                try {

                    if (col == 0) {
                        utils.dbUpdate("UPDATE inventarioexterno_archivos SET Concepto = replace('" + jTable9.getValueAt(row, col) + "',' ','_') where ArchivoID = '" + archivoc.get(row) + "'");
                    }

                } catch (Exception o) {
                    System.out.println(o);
                }
            } else {
                cont = 0;
            }

        }
    }

    public static void cargarClientesAVAILABLE(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT NComercial,ClienteID from clientes_tbl where Status = true and Equipmentprovider is not true order by NComercial";
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("-1");
            combo.addItem("Todos");

        }

        /*list.add("0");
         combo.addItem("AVAILABLE");*/
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

    public static void cargarClientesAVAILABLEEXT(JComboBox combo, ArrayList list, String startwith) {
        String query = "SELECT NComercial,ClienteID from clientes_tbl where Status = true order by NComercial"; // and MostrarExternos is true
        combo.removeAllItems();
        list.clear();
        if (!startwith.isEmpty()) {
            list.add("-1");
            combo.addItem("Todos");

        }

        /*list.add("0");
         combo.addItem("AVAILABLE");*/
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

    /* public void cargarClientesLocaTo() {
     String query = "SELECT Destino,LocacionTOID from rutas_tbl where Status = true group by LocacionTOID order by Destino";
     cliente.removeAllItems();
     clienteid.clear();

     clienteid.add("0");
     cliente.addItem("");

     Connection con;
     con = utils.startConnection();
     try {
     Statement statement = con.createStatement();
     ResultSet rs = statement.executeQuery(query);
     while (rs.next()) {
     clienteid.add(rs.getString(2));
     cliente.addItem(rs.getString(1));

     }
     con.close();
     statement.close();
     } catch (SQLException e) {
     System.out.println("Error " + e);
     }
     }*/
    public void cargarClientesLocaToGroup() {

        BoxCliente.removeAllItems();
        BoxClienteID.clear();

        String query = "SELECT Destino,LocacionTOID from rutas_tbl where Status = true group by LocacionTOID order by Destino";
        /*BoxCliente.addItem("");
         BoxClienteID.add("0");*/

        Connection con;
        con = utils.startConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                BoxClienteID.add(rs.getString(2));
                BoxCliente.addItem(rs.getString(1));

            }
            con.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    private boolean revisarActivo(String wcont, String itiactual) {
        if (!utils.dbConsult("SELECT IFNULL((SELECT ItinerarioID from itinerarios_tbl where WContFK = '" + wcont + "' and Status = 1 and ItinerarioID != '" + itiactual + "' limit 1), 0)").equals("0")) {
            return false;
        }
        return true;
    }

    void cargarContenedoresFiltrados() {

        String query = "select *,(select NComercial from clientes_tbl where clienteID= workcontenedores_tbl.clienteFK) as cliente, \n"
                + "workcontenedores_tbl.clienteFK as clienteID, \n"
                + "(SELECT concat(origen,'-',destino) from rutas_tbl where rutas_tbl.RutaID = (select RutaID from itinerarios_tbl where WcontFK=workcontenedores_tbl.WContenedorID and Status = 1 order by itinerarios_tbl.ItinerarioID desc limit 1)) as ruta, \n"
                + "(SELECT Nombre from choferes_tbl where choferID=(select choferID from itinerarios_tbl where WcontFK=workcontenedores_tbl.WContenedorID order by itinerarios_tbl.ItinerarioID desc limit 1)) as chofer, \n"
                + "IFNULL((select itinerarioID from itinerarios_tbl where WcontFK=workcontenedores_tbl.WContenedorID order by itinerarios_tbl.ItinerarioID desc limit 1 ),'') as driverm "
                + "from workcontenedores_tbl where contenedor = '" + txtContenedor.getText() + "'  and `status`=1 "
                + "and IFNULL((select ItinerarioID from itinerarios_tbl where WcontFK=workcontenedores_tbl.WContenedorID and itinerarios_tbl.Status=1 limit 1 ),'') > 0 "
                + "order by wcontenedorID desc"; //  limit 1

        contenedor_tablemodel.setRowCount(0);
        contenedorid.clear();
        itinerarioID.clear();
        Connection con = utils.startConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            int i = 1;
            while (rs.next()) {
                contenedor_tablemodel.addRow(new Object[]{rs.getString("contenedor"), rs.getString("ruta"), rs.getString("chofer"), rs.getString("cliente"), rs.getString("driverm")});
                contenedorid.add(rs.getString("WContenedorID"));
                itinerarioID.add((rs.getString("driverm")));
                cliente.setSelectedIndex(clienteid.indexOf(rs.getString("clienteID")));
            }

            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    void cargarEntradas() {

        String virtual = "", vt = "";
        virtual = utils.dbConsult("select ifnull(vt,false) from patios_tbl where  patioID='" + PatioID.get(Patio.getSelectedIndex()) + "'");

        if (virtual.equals("1")) {
            vt = " and (select ifnull(vt,false) from patios_tbl where  patioID='" + PatioID.get(Patio.getSelectedIndex()) + "') is true ";
        } else {
            vt = " and patioID='" + PatioID.get(Patio.getSelectedIndex()) + "' "; //utils.dbConsult("select patioID from usuarios_Tbl where usuarioID='"+global.usuario+"'")
        }
        String drivermove = utils.dbConsult("select IFNULL((select itinerarioID from itinerarios_tbl where WcontFK=workcontenedores_tbl.WContenedorID order by itinerarios_tbl.ItinerarioID desc limit 1 ),'') as driverm \n"
                + "from workcontenedores_tbl where contenedor = '" + txtContenedor.getText() + "'  and `status`=1 \n"
                + "and IFNULL((select ItinerarioID from itinerarios_tbl where WcontFK=workcontenedores_tbl.WContenedorID and itinerarios_tbl.Status=1 limit 1 ),'') > 0 "
                + "AND (SELECT (SELECT PatioID from locaciones_tbl where `LocacionID` = LocacionPUID) from rutas_tbl where "
                + "(select RutaID from itinerarios_tbl where WcontFK=workcontenedores_tbl.WContenedorID and itinerarios_tbl.Status=1 limit 1 ) = rutas_tbl.RutaID) = 1 "
                + "limit 1");

        String externo = utils.dbConsult("select inventarioID "
                + "from inventarioexterno_tbl where contenedor = '" + txtContenedor.getText() + "' and anteriorID=0  and WContenedorID=0 and ItinerarioID=0 and '" + PatioID.get(Patio.getSelectedIndex()) + "' "
                + " " + vt + " order by inventarioID desc limit 1");

        String driverMoveInterno = "";
        if (externo.equals("") && !drivermove.equals("") && !virtual.equals("1")) {
            driverMoveInterno = " and AND (SELECT (SELECT PatioID from locaciones_tbl where `LocacionID` = LocacionPUID) from rutas_tbl where "
                    + "(select RutaID from itinerarios_tbl where WcontFK=workcontenedores_tbl.WContenedorID and itinerarios_tbl.Status=1 limit 1 ) = rutas_tbl.RutaID) = '" + utils.dbConsult("select patioID from usuarios_Tbl where usuarioID='" + global.usuario + "'") + "' ";
        } else {
            driverMoveInterno = "";
        }

        String query = "select inventarioID,WContenedorID, contenedor,(select NComercial from clientes_tbl where clienteID=inventarioexterno_tbl.clienteID) as cliente, AnteriorID, "
                + "IFNULL((select itinerarioID from itinerarios_tbl where WcontFK=inventarioexterno_tbl.WContenedorID and itinerarios_tbl.Status=1 order by itinerarios_tbl.ItinerarioID desc limit 1 ),'') as driverm "
                + "from inventarioexterno_tbl where contenedor = '" + txtContenedor.getText() + "' and anteriorID=0 and patioID='" + PatioID.get(Patio.getSelectedIndex()) + "'"
                + " " + vt + " " + driverMoveInterno + " order by inventarioID desc limit 1";

        System.out.println("query driver: " + query);
        // (SELECT (SELECT PatioID from locaciones_tbl where `LocacionID` = LocacionPUID) from rutas_tbl where itinerarios_tbl.RutaID = rutas_tbl.RutaID) = 2 
        //INSERT INTO inventarioexterno_tbl (Contenedor, PlacasChasis, Sello, PaisID, EstadoID, ChoferID, NombreChofer, FechaLlegada, UsuarioLlegadaID, Nota, EstadoCarga, UsuarioID, Fecha, PatioID, Tamano,clienteID)

        contenedor_tablemodel.setRowCount(0);
        contenedorid.clear();
        Anteriorid.clear();
        inventariosalidaid.clear();
        Connection con = utils.startConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            int i = 1;
            while (rs.next()) {
                contenedor_tablemodel.addRow(new Object[]{rs.getString("cliente"), rs.getString("WContenedorID"), rs.getString("contenedor"), rs.getString("driverm")});
                contenedorid.add(rs.getString("WContenedorID"));
                Anteriorid.add(rs.getString("AnteriorID"));
                inventariosalidaid.add(rs.getString("inventarioID"));
                AnteriorID = rs.getString("inventarioID");
            }

            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    private void cargarChoferesLocales() {

        String query = "SELECT "
                + "concat(Noempleado,' - ',Nombre) as nom,"
                + "ChoferID "
                + "FROM choferes_tbl "
                // + "where Status = true  " // and Puesto = 1  and Detener = false and CamionID > 0
                + "order by (noempleado+0)";//"SELECT Tipo_Hab FROM tarifa_tbl WHERE Grupo ='" + boxGrupo.getSelectedItem() + "' AND Tarifa = '" + boxTarifa.getSelectedItem() + "'";

        boxChofer.removeAllItems();
        chofereslocalid.clear();

        chofereslocalid.add("0");
        boxChofer.addItem("EXTERNO");

        Connection con;
        con = utils.startConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                boxChofer.addItem(rs.getString("nom"));
                chofereslocalid.add(rs.getString("ChoferID"));
            }
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarChoferes() {
        String query = "SELECT "
                + "Nombre as nom,"
                + "ChoferID "
                + "FROM choferes_tbl "
                + "where Status = true   " //and Puesto = 1 and Detener = false and CamionID > 0
                + "order by nom";//"SELECT Tipo_Hab FROM tarifa_tbl WHERE Grupo ='" + boxGrupo.getSelectedItem() + "' AND Tarifa = '" + boxTarifa.getSelectedItem() + "'";
        boxChoferes.removeAllItems();
        choferesid.clear();

        choferesid.add("0");
        boxChoferes.addItem(" ");

        Connection con;
        con = utils.startConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                boxChoferes.addItem(rs.getString("nom"));
                choferesid.add(rs.getString("ChoferID"));
            }
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarRutas(String cliente) {
        String query = "SELECT RutaID, Nombre from rutas_tbl where Status = true and (TipoTarifa = 0 or TipoTarifa = 2) order by Nombre";
        boxRutas.removeAllItems();
        rutasid.removeAll(rutasid);
        Connection con = utils.startConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                boxRutas.addItem(rs.getString(2));
                rutasid.add(rs.getString(1));
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

//    private void cargarRutasWO(String cliente) {
//        String query = "SELECT RutaID, Nombre from rutas_tbl where Status = true and ClienteID = '" + cliente + "' order by Nombre";
//        boxRutaNC.removeAllItems();
//        rutawo.clear();
//        Connection con = utils.startConnection();
//        try {
//            Statement statement = con.createStatement();
//            ResultSet rs = statement.executeQuery(query);
//            while (rs.next()) {
//                boxRutaNC.addItem(rs.getString(2));
//                rutawo.add(rs.getString(1));
//            }
//            con.close();
//        } catch (SQLException e) {
//            System.out.println("Error " + e);
//        }
//    }
    private void cargarRutasRep(String cliente) {
        String query = "SELECT RutaID, Nombre from rutas_tbl where Status = true order by Nombre";
        boxRutasRep.removeAllItems();
        rutasrepid.clear();
        boxRutasRep.addItem("Todas las rutas");
        rutasrepid.add("0");
        Connection con = utils.startConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                boxRutasRep.addItem(rs.getString(2));
                rutasrepid.add(rs.getString(1));
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    private void cargarPatioz() {
        String query = "SELECT RutaID, Nombre from rutas_tbl where Status = true order by Nombre";
        boxRutasRep.removeAllItems();
        rutasrepid.clear();
        boxRutasRep.addItem("Todas las rutas");
        rutasrepid.add("0");
        Connection con = utils.startConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                boxRutasRep.addItem(rs.getString(2));
                rutasrepid.add(rs.getString(1));
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    private void cargarTabla() {

        String estadocarga = "", orderby = "InventarioID", patio = "", cliente = "", actual = "", provider = "", isCamion = "", estatuscaja = "", fulle = "", solochassis = "";
        // "IF(Carga is null, 1, 0), Carga, IF(chofer = '',1,0) asc, IFNULL(IF(RutaID > 0, (SELECT Origen from rutas_tbl where rutas_tbl.RutaID = itinerarios_tbl.RutaID), (SELECT Nombre from locaciones_tbl where locaciones_tbl.LocacionID = LocacionOrigenID)),''),getRecogeLimite(WcontFK, (SELECT wc.StatusMov from workcontenedores_tbl as wc where wc.WContenedorID = WcontFK) )"
        String filtrobus = "and "
                + "( "
                + "Contenedor like '%" + txtBusqueda.getText() + "%' or "
                + "NombreChofer like '%" + txtBusqueda.getText() + "%' or "
                + "PlacasChasis like '%" + txtBusqueda.getText() + "%' or "
                + "PlacasUnidad like '%" + txtBusqueda.getText() + "%' or "
                + "NumeroChasis like '%" + txtBusqueda.getText() + "%' or "
                + "NombreChofer like '%" + txtBusqueda.getText() + "%' or "
                + "camion like '%" + txtBusqueda.getText() + "%' or "
                + "Sello like '%" + txtBusqueda.getText() + "%' or "
                + "(select nombre from choferes_tbl where choferID=inventarioexterno_tbl.choferID) like '%" + txtBusqueda.getText() + "%' or "
                + "(select NoEconomico from camiones_tbl where camionID=inventarioexterno_tbl.camionID) like '%" + txtBusqueda.getText() + "%' "
                + ") ";

        String filtrobusampl = "and "
                + "( "
                + "Contenedor like '%" + txtBusqueda.getText() + "%' or "
                + "NombreChofer like '%" + txtBusqueda.getText() + "%' or "
                + "PlacasChasis like '%" + txtBusqueda.getText() + "%' or "
                + "PlacasUnidad like '%" + txtBusqueda.getText() + "%' or "
                + "NumeroChasis like '%" + txtBusqueda.getText() + "%' or "
                + "NombreChofer like '%" + txtBusqueda.getText() + "%' or "
                + "camion like '%" + txtBusqueda.getText() + "%' or "
                + "Sello like '%" + txtBusqueda.getText() + "%' or "
                + "(select nombre from choferes_tbl where choferID=inventarioexterno_tbl.choferID) like '%" + txtBusqueda.getText() + "%' or "
                + "(select NoEconomico from camiones_tbl where camionID=inventarioexterno_tbl.camionID) like '%" + txtBusqueda.getText() + "%' "
                + ") ";

        String filtrostatus = " Status = 1 ";
        if (boxFiltroStatus.getSelectedIndex() == 1) {
            filtrostatus = " Status >= 1 ";
        }

        String tipoEvento = "";
        if (boxFiltroBusqueda.getSelectedIndex() > 0) {
            tipoEvento = " and TipoEvento='" + boxFiltroBusqueda.getSelectedIndex() + "' ";
        }

        if (boxFiltroBusqueda1.getSelectedIndex() > 0) {
            estadocarga = " and EstadoCarga='" + boxFiltroBusqueda1.getSelectedIndex() + "'-1 ";
        }

        if (BoxPatios.getSelectedIndex() > 0) {
            patio = " and patioID='" + patiosid.get(BoxPatios.getSelectedIndex()) + "' ";
        }

        if (BoxCliente.getSelectedIndex() > 0) {
            cliente = " and clienteID='" + BoxClienteID.get(BoxCliente.getSelectedIndex()) + "' ";
        }

        if (BoxCliente1.getSelectedIndex() > 0) {
            provider = " and EquipmentProvider='" + BoxCliente1ID.get(BoxCliente1.getSelectedIndex()) + "' ";
        }

        if (HistorialActual.getSelectedIndex() == 1) {
            actual = " and (select anteriorID from inventarioexterno_tbl as w where anteriorID=inventarioexterno_tbl.InventarioID limit 1) is null and TipoEvento=1 ";
        }

        if (FilterFull.isSelected()) {
            fulle = " and isFull is true ";
        } else {
            fulle = " ";
        }

        if (schasis.isSelected()) {
            solochassis = " and solochasis is true ";
        } else {
            solochassis = " ";
        }

        if (jCheckBox1.isSelected()) {
            orderby = " inventarioID desc ";
        } else {

            String orden1 = "", orden2 = "";

            if (jCheckBox2.isSelected() == true) {
                orden1 = "desc";
                orden2 = "asc";
            } else {
                orden1 = "asc";
                orden2 = "desc";
            }

            orderby = " IF(EquipmentProvider=0,'AVAILABLE',(select NCOMERCIAL from clientes_tbl where clienteID=inventarioexterno_tbl.EquipmentProvider)), "
                    + "IF(" + jCheckBox2.isSelected() + "=true,(select DATEDIFF(now(), inventarioexterno_tbl.FechaEvento)+1) , contenedor ) " + orden1 + ",  "
                    + "IF(" + jCheckBox2.isSelected() + "=true,contenedor+0 ,(select DATEDIFF(now(), inventarioexterno_tbl.FechaEvento)+1) ) " + orden2 + " ";
        }

        if (jCheckBox3.isSelected()) {
            isCamion = " and botando is true ";
        } else {
            isCamion = " "; //and botando is not true 
        }        // se movio el filtro de villalobos

        if (estatus_caja.getSelectedIndex() == 0) {
            estatuscaja = " ";
        } else {
            estatuscaja = " and (SELECT estatus_caja from cajas_tbl where noeconomico = inventarioexterno_tbl.contenedor limit 1) = '" + estatus_caja.getSelectedItem().toString() + "' ";
        }

        String fechaz = "", fechazampl = "";
        if (FechaFiltro.isSelected()) {

            fechaz = " and date(fecha) >= date('" + utils.dateFromFieldtoDBwoH(fi.getText()) + "') and date(fecha)<= date('" + utils.dateFromFieldtoDBwoH(ff.getText()) + "') ";

            //date(fechaevento) >= date('"+utils.dateFromFieldtoDBwoH(fi.getText())+"') or 
            fechazampl = " and date(fechaevento)<= date('" + utils.dateFromFieldtoDBwoH(ff.getText()) + "') and ( date(fechaevento)<= date('" + utils.dateFromFieldtoDBwoH(ff.getText()) + "') or date(fechaevento) <= "
                    + "IFNULL((select IF(date('" + utils.dateFromFieldtoDBwoH(ff.getText()) + "')>date(FechaEvento),DATE_FORMAT(FechaEvento,'%d/%m/%Y'),"
                    + "DATE_FORMAT(date('" + utils.dateFromFieldtoDBwoH(ff.getText()) + "'),'%d/%m/%Y')) from inventarioexterno_tbl as w where w.anteriorID="
                    + "inventarioexterno_tbl.inventarioID order by inventarioID desc limit 1),DATE_FORMAT('" + utils.dateFromFieldtoDBwoH(ff.getText()) + "','%d/%m/%Y')) is not null "
                    + "or (select fechaevento from inventarioexterno_tbl as w where w.anteriorID="
                    + "inventarioexterno_tbl.inventarioID order by inventarioID desc limit 1) is null ) "; //(select EndDate)
        } else {
            fechaz = "";
            fechazampl = "";
        }

        jTable1.repaint();

        boolean permisos = true;

        if (permisos) {
            String query = "SELECT now() as hoy, InventarioID, "
                    + "(select noeconomico from camiones_tbl where camionID=inventarioexterno_tbl.camionID) as camion, "
                    + "IFNULL((select nombre from choferes_tbl where choferID=inventarioexterno_tbl.choferID),NombreChofer) AS NCHOFER, "
                    + "Tamano,"
                    + "Contenedor,"
                    + "PlacasChasis,"
                    + "(SELECT Nombre from estados_tbl where estados_tbl.EstadoID = inventarioexterno_tbl.EstadoID) as estado, "
                    + "IF(EstadoCarga = 0, 'E', 'L') as estadoc, "
                    + "DATE_FORMAT(FechaEvento, '%m/%d/%Y') as fechaevento, "
                    + "DATE_FORMAT(FechaEvento, '%H:%i') as horaevento,  "
                    + "Sello, "
                    + "Nota, "
                    + "IF(TipoEvento=1,'In','Out') as Entsal,"
                    //+ "(select Destino from rutas_tbl where rutaID=(select RutaID from workcontenedores_tbl where workcontenedores_tbl.WContenedorID=inventarioexterno_tbl.WContenedorID)) as cliente, "
                    + "IF(clienteID=0,'AVAILABLE',(select NCOMERCIAL from clientes_tbl where clienteID=inventarioexterno_tbl.clienteID)) as cliente,"
                    + "IF(EquipmentProvider=0,'AVAILABLE',(select NCOMERCIAL from clientes_tbl where clienteID=inventarioexterno_tbl.EquipmentProvider)) as Eqprov,"
                    + "(select NoEconomico from camiones_tbl where camionID=(select choferID from itinerarios_tbl where WcontFK=inventarioexterno_tbl.WContenedorID limit 1)) as camion, "
                    //+ "(select NoEconomico from camiones_tbl where camionID=inventarioexterno_tbl.camionID) as camion, "
                    //+"(select Nombre from choferes_tbl where choferID=inventarioexterno_tbl.choferID) as chofer, "
                    + "IF(origen='',(SELECT Origen from rutas_tbl where rutas_tbl.RutaID = (select RutaID from workcontenedores_tbl where workcontenedores_tbl.WContenedorID=inventarioexterno_tbl.WContenedorID)),origen) as orign, "
                    + ""
                    + "(select abreviacion from estados_tbl where estadoID=inventarioexterno_tbl.remolqueestado) as estadoab, "
                    + "(select DATEDIFF(now(), inventarioexterno_tbl.FechaEvento)+1) as daysin, "
                    + "grade, assignedto, inventarioID, IF(TipoEvento=2,IF(anteriorID=0,'-',anteriorID), "
                    + "IFNULL((select inventarioID from inventarioexterno_tbl as w where anteriorID=inventarioexterno_tbl.InventarioID and TipoEvento=2 limit 1),'-') ) as ant,  "
                    + ""
                    + "IFNULL(( select rutas_tbl.destino from itinerarios_tbl as iti "
                    + //locaciones_tbl.Nombre
                    "inner join rutas_tbl on rutas_tbl.`RutaID` = iti.`RutaID` "
                    + "inner join locaciones_tbl on locaciones_tbl.`LocacionID` = rutas_tbl.`LocacionTOID` "
                    + "where WContFK = WContenedorID and iti.Status >= 1 and iti.RutaID > 0 "
                    + "order by Carga desc limit 1),'') as loc, destino, sello, "
                    + "IFNULL((SELECT nombre from perfilcobro where ID=perfilCobroID),'') as perfilcobro, sello,  "
                    + "(SELECT estatus_caja from cajas_tbl where noeconomico = inventarioexterno_tbl.contenedor limit 1) as estatuz, "
                    + "IF(isFull is true and FullItinerarioBase=0,IFNULL((select c.contenedor from inventarioexterno_tbl as c where c.FullItinerarioBase=inventarioexterno_tbl.inventarioID),''),'') as contenedor2 "
                    + ""
                    + "from inventarioexterno_tbl "
                    + "where Status = 1 " + filtrobus + tipoEvento + estadocarga + patio + cliente + actual + provider + fechaz + isCamion + estatuscaja + fulle + solochassis
                    + " order by  " + orderby + " limit " + txtTotalReg1.getText();
            //+ "" + orderby ;
            System.out.println("query = " + query);
            QUERY = query;

            String perfilbuscadorx = "";
            if (perfilBuscador.getSelectedIndex() > 0) {
                perfilbuscadorx = perfilBuscadorID.get(perfilBuscador.getSelectedIndex());
            } else {
                perfilbuscadorx = "0";
            }

            String virtual = "", perfilapplied = "";
            if (BoxPatios.getSelectedIndex() > 0) {
                virtual = utils.dbConsult("select IFNULL(vt,FALSE) from patios_tbl where  patioID='" + patiosid.get(BoxPatios.getSelectedIndex()) + "'");
            }

            /*if(virtual.equals("1")) { perfilapplied=" and perfilCobroID='"+perfilbuscadorx+"' ";} 
             if(!jCheckBox5.isSelected())  { perfilapplied="";} */
            QUERYAMPL = "select Contenedor, "
                    + "IF(EquipmentProvider=0,'AVAILABLE',(select NCOMERCIAL from clientes_tbl where clienteID=inventarioexterno_tbl.EquipmentProvider)) as Eqprov, "
                    + "(select Nombre from estados_tbl where estadoID=inventarioexterno_tbl.estadoID) as estado, "
                    + "IF(EstadoCarga=0,'E','L') as Carga, "
                    + "DATEDIFF(IF( (SELECT InventarioID FROM inventarioexterno_tbl as w WHERE w.AnteriorID = inventarioexterno_tbl.InventarioID AND TipoEvento = 2 ORDER BY InventarioID DESC LIMIT 1)+0>0, "
                    + "(SELECT IF(date('" + utils.dateFromFieldtoDBwoH(ff.getText()) + "')>date(FechaEvento),(FechaEvento),(concat(('" + utils.dateFromFieldtoDBwoH(ff.getText()) + "'),' 23:59:59'))) "
                    + "FROM inventarioexterno_tbl as w WHERE w.AnteriorID = inventarioexterno_tbl.InventarioID AND TipoEvento = 2 ORDER BY InventarioID DESC LIMIT 1), (concat(('" + utils.dateFromFieldtoDBwoH(ff.getText()) + "'),' 23:59:59')) ), "
                    + "IF(date('" + utils.dateFromFieldtoDBwoH(fi.getText()) + "')>date(FechaEvento),concat(date('" + utils.dateFromFieldtoDBwoH(fi.getText()) + "'),' 00:00:00'),FechaEvento)) as Dias, "
                    + "IF( (SELECT InventarioID FROM inventarioexterno_tbl as w WHERE w.AnteriorID = inventarioexterno_tbl.InventarioID AND TipoEvento = 2 ORDER BY InventarioID DESC LIMIT 1)+0>0, IFNULL((select Dias+1),1), IFNULL((Select Dias+1),1)) as TTLDias, "
                    //+ "IF((select TTLDia)=0,1,(select TTLDia)) as TTLDias, " +
                    + "DATE_FORMAT(FechaEvento,'%m/%d/%Y') as ArriveDate,  "
                    + "DATE_FORMAT(FechaEvento,'%H:%i:%s') as ArriveTime, "
                    + "IFNULL((select IF(date('" + utils.dateFromFieldtoDBwoH(ff.getText()) + "')>date(FechaEvento),DATE_FORMAT(FechaEvento,'%m/%d/%Y'),DATE_FORMAT(date('" + utils.dateFromFieldtoDBwoH(ff.getText()) + "'),"
                    + "'%m/%d/%Y')) from inventarioexterno_tbl as w where w.anteriorID=inventarioexterno_tbl.inventarioID order by inventarioID desc limit 1),DATE_FORMAT('" + utils.dateFromFieldtoDBwoH(ff.getText()) + "','%m/%d/%Y')) as EndDate, "
                    + "IFNULL((select DATE_FORMAT(FechaEvento,'%H:%i:%s') from inventarioexterno_tbl as w where w.anteriorID=inventarioexterno_tbl.inventarioID order by inventarioID desc limit 1),'23:59:59') as EndTime, "
                    + //DATE_FORMAT(now(),'%H:%i:%s')
                    "IFNULL((select IF(date('" + utils.dateFromFieldtoDBwoH(ff.getText()) + "')>date(FechaEvento),(FechaEvento),(date('" + utils.dateFromFieldtoDBwoH(ff.getText()) + "'))) "
                    + "from inventarioexterno_tbl as w where w.anteriorID=inventarioexterno_tbl.inventarioID order by inventarioID desc limit 1),('" + utils.dateFromFieldtoDBwoH(ff.getText()) + "')) as EndDateHour, "
                    + "@rownum := @rownum + 1 AS rank, "
                    + "ROUND(IFNULL((select costo from perfilcobro_rango "
                    + //"where perfilID='"+perfilbuscadorx+"' and (minimo+0)<=(select @rownum) and (maximo+0)>=(select @rownum)  limit 1),0)*(select TTLDias) as TOTALMONEY, " < Yocu cobro segun el renglon?
                    "where perfilID='" + perfilbuscadorx + "' and (minimo+0)<=(select TTLDias) and (maximo+0)>=(select TTLDias)  limit 1),0)*(select TTLDias)+0) as TOTALMONEY, "
                    + "concat( (select @rownum3 := @rownum3 + 1),' ',contenedor,' ',(select Carga),' In[',(select ArriveDate),'] / Out[',(select EndDate),'] (',(select TTLDias),' Dias)' ) as concatenado "
                    + "from inventarioexterno_tbl , (SELECT @rownum := 0) r, (SELECT @rownum1 := 0) r1, (SELECT @rownum2 := 0) r2, (SELECT @rownum3 := 0) r3 "
                    + "where inventarioID>0 and TipoEvento=1 and contenedor!=''  " + perfilapplied + "  and "
                    + "((select IFNULL((select IF(date('" + utils.dateFromFieldtoDBwoH(ff.getText()) + "')>date(FechaEvento),(FechaEvento),(date('" + utils.dateFromFieldtoDBwoH(ff.getText()) + "')"
                    + ")) from inventarioexterno_tbl as w where w.anteriorID=inventarioexterno_tbl.inventarioID order by inventarioID desc limit 1),('" + utils.dateFromFieldtoDBwoH(ff.getText()) + "')))"
                    + ">=date('" + utils.dateFromFieldtoDBwoH(fi.getText()) + "')) " + filtrobusampl + tipoEvento + estadocarga + patio + cliente + actual + provider + fechazampl + estatuscaja + fulle + " "
                    + "order by  (select EndDate),(select EndTime) asc " /*"IFNULL((select IF(date('"+utils.dateFromFieldtoDBwoH(ff.getText())+"')>date(FechaEvento),FechaEvento,"
                     + "(select '"+utils.dateFromFieldtoDBwoH(ff.getText())+"'+' 23:59:59' from inventarioexterno_tbl where anteriorID="
                     + "w.inventarioID order by inventarioID desc limit 1))),'"+utils.dateFromFieldtoDBwoH(ff.getText())+"'+' 23:59:59') asc "*/;
            //"and IF( $P{clienteID}+0>0, clienteID=$P{clienteID} , clienteID>=0 )";

            System.out.println("QUERY AMPL: " + QUERYAMPL);

            Object[] temp = new Object[model1.getColumnCount()];
            model1.setRowCount(0);
            inventarioID.clear();
            savestatmov.clear();
            savestat.clear();
            Connection con = utils.startConnection();
            try {
                Statement statement = con.createStatement();
                ResultSet rs = statement.executeQuery(query);

                int cntz = 1;

                while (rs.next()) {

                    temp[0] = cntz + "";
                    temp[1] = rs.getString("Entsal");
                    temp[2] = rs.getString("Camion");
                    temp[3] = rs.getString("Contenedor");
                    temp[4] = rs.getString("PlacasChasis");
                    temp[5] = rs.getString("estadoab");
                    temp[6] = rs.getString("estadoc");
                    temp[7] = rs.getString("Eqprov");
                    temp[8] = rs.getString("cliente");
                    temp[9] = rs.getString("fechaevento");
                    temp[10] = rs.getString("horaevento");
                    temp[11] = rs.getString("daysin");
                    temp[12] = rs.getString("orign");
                    temp[13] = rs.getString("grade");
                    //temp[t1salida] = rs.getString("salida");
                    temp[14] = rs.getString("assignedto");
                    temp[15] = rs.getString("Nota");
                    temp[16] = rs.getString("inventarioID");
                    temp[17] = rs.getString("ant");
                    temp[18] = rs.getString("destino");
                    temp[19] = rs.getString("perfilcobro");
                    temp[20] = rs.getString("sello");
                    temp[21] = rs.getString("estatuz");
                    temp[22] = rs.getString("contenedor2");

                    model1.addRow(temp);
                    inventarioID.add(rs.getString("inventarioID"));
                    cntz++;
                    //System.out.println(rs.getString("folio") + " - " + rs.getBoolean("OTObtenida"));
                }
                txtTotalReg.setText(model1.getRowCount() + "");
                con.close();
                //jTable1.setAutoCreateRowSorter(true);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private void tableListener10(javax.swing.event.TableModelEvent e) {
        if (e.getType() == TableModelEvent.UPDATE) {
            if (cont < 1) {
                int row = tCargosCliente.getSelectedRow();
                int col = tCargosCliente.getSelectedColumn();
                try {

                    if (col == cclconcepto) {
                        String value = tCargosCliente.getValueAt(row, col).toString();
                        if (value != null & !value.isEmpty()) {
                            if (cargosid.get(row).equals("0")) {
//                                String id = utils.dbInsert("INSERT INTO cargosclientes_tbl(ItinerarioID, Concepto,Cantidad,WContID,Importe,Moneda,Status,Fecha,UsuarioID) "
//                                        + "VALUES('" + model1.getValueAt(jTable1.getSelectedRow(), t1itiid) + "','" + value + "','" + tCargosCliente.getValueAt(row, 1) + "','" + model1.getValueAt(jTable1.getSelectedRow(), t1wcontid) + "','" + tCargosCliente.getValueAt(row, 2) + "','" + boxMoneda.getSelectedIndex() + "',true,(now()),'" + global.usuario + "')");
//                                if (id.length() < 11 && !id.isEmpty()) {
//                                    System.out.println(id);
//                                    cargosid.set(row, id);
//                                } else {
//                                    JOptionPane.showMessageDialog(dDetalles, "Error " + id, "Error", JOptionPane.ERROR_MESSAGE);
//                                }
                            } else {
                                utils.dbUpdate("UPDATE cargosclientes_tbl SET Concepto = '" + value + "' where CargoID = '" + cargosid.get(row) + "'");
                            }
                        }
                    }

                    if (col == cclfromto) {
                        utils.dbUpdate("UPDATE cargosclientes_tbl SET Observaciones = '" + tCargosCliente.getValueAt(row, col) + "' where CargoID = '" + cargosid.get(row) + "'");
                    }

                    if (col == cclcantidad) {
                        String value = tCargosCliente.getValueAt(row, col).toString();
                        if (value != null & !value.isEmpty()) {
                            if (cargosid.get(row).equals("0")) {
//                                String id = utils.dbInsert("INSERT INTO cargosclientes_tbl(ItinerarioID, Concepto,Cantidad,WContID,Importe,Moneda,Status,Fecha,UsuarioID) "
//                                        + "VALUES('" + model1.getValueAt(jTable1.getSelectedRow(), t1itiid) + "','" + tCargosCliente.getValueAt(row, 0) + "',digits('" + value + "'),'" + model1.getValueAt(jTable1.getSelectedRow(), t1wcontid) + "','" + tCargosCliente.getValueAt(row, 2) + "','" + boxMoneda.getSelectedIndex() + "',true,(now()),'" + global.usuario + "')");
//                                if (id.length() < 11 && !id.isEmpty()) {
//                                    System.out.println(id);
//                                    cargosid.set(row, id);
//                                } else {
//                                    JOptionPane.showMessageDialog(dDetalles, "Error " + id, "Error", JOptionPane.ERROR_MESSAGE);
//                                }
                            } else {
                                utils.dbUpdate("UPDATE cargosclientes_tbl SET Cantidad = digits('" + value + "') where CargoID = '" + cargosid.get(row) + "'");
                            }
                        }
                    }

                    if (col == cclimporte) {
                        String value = tCargosCliente.getValueAt(row, col).toString();
                        if (value != null & !value.isEmpty()) {
                            if (cargosid.get(row).equals("0")) {
//                                String id = utils.dbInsert("INSERT INTO cargosclientes_tbl(ItinerarioID, Concepto,Cantidad,WContID,Importe,Moneda,Status,Fecha,UsuarioID) "
//                                        + "VALUES('" + model1.getValueAt(jTable1.getSelectedRow(), t1itiid) + "','" + tCargosCliente.getValueAt(row, 0) + "','" + tCargosCliente.getValueAt(row, 1) + "','" + model1.getValueAt(jTable1.getSelectedRow(), t1wcontid) + "',digits('" + tCargosCliente.getValueAt(row, 2) + "'),'" + boxMoneda.getSelectedIndex() + "',true,(now()),'" + global.usuario + "')");
//                                if (id.length() < 11 && !id.isEmpty()) {
//                                    cargosid.set(row, id);
//                                } else {
//                                    JOptionPane.showMessageDialog(dDetalles, "Error " + id, "Error", JOptionPane.ERROR_MESSAGE);
//                                }
                            } else {
                                utils.dbUpdate("UPDATE cargosclientes_tbl SET Importe = digits('" + tCargosCliente.getValueAt(row, 2) + "') where CargoID = '" + cargosid.get(row) + "'");
                            }
                        }
                    }

                    if (col == cclmoneda) {

                        if (cargosid.get(row).equals("0")) {
//                            String id = utils.dbInsert("INSERT INTO cargosclientes_tbl(ItinerarioID, Concepto,Cantidad,WContID,Importe,Moneda,Status,Fecha,UsuarioID) "
//                                    + "VALUES('" + model1.getValueAt(jTable1.getSelectedRow(), t1itiid) + "','" + tCargosCliente.getValueAt(row, 0) + "','" + tCargosCliente.getValueAt(row, 1) + "','" + model1.getValueAt(jTable1.getSelectedRow(), t1wcontid) + "',digits('" + tCargosCliente.getValueAt(row, 2) + "'),'" + boxMoneda.getSelectedIndex() + "',true,(now()),'" + global.usuario + "')");
//                            if (id.length() < 11 && !id.isEmpty()) {
//                                cargosid.set(row, id);
//                            } else {
//                                JOptionPane.showMessageDialog(dDetalles, "Error " + id, "Error", JOptionPane.ERROR_MESSAGE);
//                            }
                        } else {
                            utils.dbUpdate("UPDATE cargosclientes_tbl SET Moneda = '" + boxMoneda.getSelectedIndex() + "' where CargoID = '" + cargosid.get(row) + "'");
                        }
                    }

                } catch (Exception o) {
                    System.out.println(o);
                }
            } else {
                cont = 0;
            }

        }
    }

    private void tableListener(javax.swing.event.TableModelEvent e) {
        if (e.getType() == TableModelEvent.UPDATE) {
            if (cont < 1) {
                int row = jTable1.getSelectedRow();
                int col = jTable1.getSelectedColumn();
                try {

                } catch (Exception o) {
                    JOptionPane.showMessageDialog(this, "Favor de Selecionar un Elemento\nError " + o, "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                cont = 0;
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dDetalles = new javax.swing.JDialog();
        jPanel42 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tCargosCliente = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel68 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        mEntradaSalida = new javax.swing.JMenuItem();
        mCargarArchivo = new javax.swing.JMenuItem();
        mPuntos = new javax.swing.JMenuItem();
        dRolChoferes = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        txtBusquedaChofer = new javax.swing.JTextField();
        lblFechas = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        boxOrdenChoferes = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tEnsenada = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jScrollPane19 = new javax.swing.JScrollPane();
        tTijuana = new javax.swing.JTable();
        jLabel39 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jScrollPane20 = new javax.swing.JScrollPane();
        tMexicali = new javax.swing.JTable();
        jLabel40 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tForaneo = new javax.swing.JTable();
        jLabel28 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jScrollPane21 = new javax.swing.JScrollPane();
        tHermosillo = new javax.swing.JTable();
        jLabel41 = new javax.swing.JLabel();
        dUnidades = new javax.swing.JDialog();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane16 = new javax.swing.JScrollPane();
        tUnidades = new javax.swing.JTable();
        boxFiltro = new javax.swing.JComboBox<>();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        mPrecioRuta = new javax.swing.JMenuItem();
        mModificar = new javax.swing.JMenuItem();
        mExtraChoferes = new javax.swing.JMenuItem();
        mCancelar1 = new javax.swing.JMenuItem();
        dReportes = new javax.swing.JDialog();
        jPanel18 = new javax.swing.JPanel();
        boxTipoReporte = new javax.swing.JComboBox<>();
        jButton13 = new javax.swing.JButton();
        boxClienteRep = new WiderCombo();
        txtBusquedaReporte = new javax.swing.JTextField();
        boxRutasRep = new javax.swing.JComboBox<>();
        txtFechafin = new com.github.lgooddatepicker.components.DatePicker();
        txtFechaini = new com.github.lgooddatepicker.components.DatePicker();
        dEnvioCorreo = new javax.swing.JDialog();
        jPanel22 = new javax.swing.JPanel();
        txtSubject1 = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        jScrollPane18 = new javax.swing.JScrollPane();
        areaBody1 = new javax.swing.JTextArea();
        jLabel54 = new javax.swing.JLabel();
        pAttach = new javax.swing.JPanel();
        jButton14 = new javax.swing.JButton();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        txtTo1 = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        puCargosClientes = new javax.swing.JPopupMenu();
        mRevisar = new javax.swing.JMenuItem();
        dRevision = new javax.swing.JDialog();
        jScrollPane23 = new javax.swing.JScrollPane();
        txtRevision = new javax.swing.JTextArea();
        dEntradaSalida = new javax.swing.JDialog();
        jPanel36 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        txtHLlegada = new javax.swing.JFormattedTextField();
        jLabel37 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        boxEstadoCarga = new WiderCombo();
        txtPlacasChasis = new javax.swing.JTextField();
        jLabel88 = new javax.swing.JLabel();
        txtSello = new javax.swing.JTextField();
        boxEstadoPlacas = new javax.swing.JComboBox<>();
        boxPaisPlacas = new javax.swing.JComboBox<>();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        boxChofer = new WiderCombo();
        jLabel91 = new javax.swing.JLabel();
        bEliminarLlegada = new javax.swing.JButton();
        bLlegada = new javax.swing.JButton();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        txtNota = new javax.swing.JTextField();
        txtContenedor = new javax.swing.JTextField();
        jLabel94 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        cliente = new javax.swing.JComboBox<>();
        txtNombreChofer = new javax.swing.JTextField();
        jLabel100 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        boxTamano = new javax.swing.JComboBox<>();
        jLabel96 = new javax.swing.JLabel();
        InOut = new javax.swing.JComboBox();
        cliente1 = new javax.swing.JComboBox<>();
        jLabel98 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        camiones = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        camionx = new WiderCombo();
        Orgn = new WiderCombo();
        botando = new javax.swing.JCheckBox();
        jLabel19 = new javax.swing.JLabel();
        bremolque = new com.alee.extended.date.WebDateField();
        jLabel101 = new javax.swing.JLabel();
        placasunidad = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        NCHASIS = new javax.swing.JTextField();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        CARRIER = new javax.swing.JTextField();
        edicion = new javax.swing.JLabel();
        Patio = new WiderCombo();
        jLabel102 = new javax.swing.JLabel();
        destino = new javax.swing.JTextField();
        jLabel106 = new javax.swing.JLabel();
        grade = new WiderCombo();
        jLabel107 = new javax.swing.JLabel();
        assignedto = new javax.swing.JTextField();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtDriverMove = new javax.swing.JTextField();
        jButton19 = new javax.swing.JButton();
        Camion = new javax.swing.JCheckBox();
        jButton10 = new javax.swing.JButton();
        bcamion = new com.alee.extended.date.WebDateField();
        jLabel105 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        icont_contenedor = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        yardaTittle = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        statz1 = new javax.swing.JLabel();
        bchasis = new com.alee.extended.date.WebDateField();
        jLabel42 = new javax.swing.JLabel();
        boxPaisPlacas1 = new javax.swing.JComboBox<>();
        jLabel110 = new javax.swing.JLabel();
        boxEstadoPlacas1 = new javax.swing.JComboBox<>();
        jLabel111 = new javax.swing.JLabel();
        placasunidad1 = new javax.swing.JTextField();
        jLabel113 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        placasunidad2 = new javax.swing.JTextField();
        placasunidad3 = new javax.swing.JTextField();
        paisx = new javax.swing.JComboBox<>();
        estx = new javax.swing.JComboBox<>();
        jLabel115 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        solochasis = new javax.swing.JCheckBox();
        statz = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        edicion1 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        cajachasisboxExterno = new javax.swing.JTextField();
        cajachasisbox = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        licencia = new javax.swing.JTextField();
        jLabel117 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel118 = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        Fullx = new javax.swing.JLabel();
        Fullx1 = new javax.swing.JLabel();
        jLabel120 = new javax.swing.JLabel();
        sellocomplementario = new javax.swing.JTextField();
        jButton23 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        dArchivos = new javax.swing.JDialog();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTable9 = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        jPanel37 = new javax.swing.JPanel();
        bAgregarDocs2 = new javax.swing.JButton();
        jPanel34 = new javax.swing.JPanel();
        bAgregarDocs1 = new javax.swing.JButton();
        jCheckBox4 = new javax.swing.JCheckBox();
        jPanel38 = new javax.swing.JPanel();
        bAgregarDocs3 = new javax.swing.JButton();
        ContenedorFiltro = new javax.swing.JDialog();
        Pagos14 = new javax.swing.JButton();
        anexo_add8 = new javax.swing.JButton();
        anexo_del8 = new javax.swing.JButton();
        plus12 = new javax.swing.JButton();
        plus13 = new javax.swing.JButton();
        jScrollPane45 = new javax.swing.JScrollPane();
        contenedor_tbl = new javax.swing.JTable();
        Pagos15 = new javax.swing.JButton();
        jCheckBox6 = new javax.swing.JCheckBox();
        jPopupMenu3 = new javax.swing.JPopupMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        dArchivos1 = new javax.swing.JDialog();
        jScrollPane17 = new javax.swing.JScrollPane();
        jTable10 = new javax.swing.JTable();
        jPanel21 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jPanel27 = new javax.swing.JPanel();
        jTextField4 = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        Perfiles = new javax.swing.JDialog();
        Pagos16 = new javax.swing.JButton();
        anexo_add9 = new javax.swing.JButton();
        anexo_del9 = new javax.swing.JButton();
        plus14 = new javax.swing.JButton();
        plus15 = new javax.swing.JButton();
        Pagos17 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        perfilnombre = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        perfilconcepto = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        clientePerfil = new javax.swing.JComboBox();
        Perfil = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableRango = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        Facturacion = new javax.swing.JDialog();
        anexo_add10 = new javax.swing.JButton();
        anexo_del10 = new javax.swing.JButton();
        plus16 = new javax.swing.JButton();
        plus17 = new javax.swing.JButton();
        Pagos19 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane22 = new javax.swing.JScrollPane();
        facturacion_tbl = new javax.swing.JTable();
        jButton9 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        facturados = new javax.swing.JCheckBox();
        AltaYarda = new javax.swing.JDialog();
        jPanel28 = new javax.swing.JPanel();
        jScrollPane24 = new javax.swing.JScrollPane();
        taccesos = new javax.swing.JTable();
        jButton22 = new javax.swing.JButton();
        pwd = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        PuntosRevision = new javax.swing.JDialog();
        jPanel29 = new javax.swing.JPanel();
        jScrollPane25 = new javax.swing.JScrollPane();
        puntos = new javax.swing.JTable();
        lblFoto = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lblFoto1 = new javax.swing.JLabel();
        EquipmentControl = new javax.swing.JDialog();
        jScrollPane3 = new javax.swing.JScrollPane();
        CTRL = new javax.swing.JTable();
        jPanel31 = new javax.swing.JPanel();
        jButton18 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        ContenedorInterno = new javax.swing.JDialog();
        boxcontenedorx = new WiderCombo();
        busquedaContenedor = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jButton20 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        dTractoDM = new javax.swing.JDialog();
        jPanel32 = new javax.swing.JPanel();
        jScrollPane26 = new javax.swing.JScrollPane();
        tDm = new javax.swing.JTable();
        dmText = new javax.swing.JTextField();
        perfilBuscador1 = new javax.swing.JComboBox<>();
        jLabel78 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        bContenedores2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        perfilBuscador = new javax.swing.JComboBox<>();
        jLabel77 = new javax.swing.JLabel();
        pEstados11 = new javax.swing.JPanel();
        jButton16 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        bControlEquipment = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        pEstados6 = new javax.swing.JPanel();
        jLabel74 = new javax.swing.JLabel();
        HistorialActual = new javax.swing.JComboBox<>();
        pEstados1 = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        boxFiltroBusqueda = new javax.swing.JComboBox<>();
        pEstados3 = new javax.swing.JPanel();
        jLabel71 = new javax.swing.JLabel();
        boxFiltroBusqueda1 = new javax.swing.JComboBox<>();
        pEstados4 = new javax.swing.JPanel();
        jLabel72 = new javax.swing.JLabel();
        BoxPatios = new javax.swing.JComboBox<>();
        pEstados5 = new javax.swing.JPanel();
        jLabel73 = new javax.swing.JLabel();
        BoxCliente = new javax.swing.JComboBox<>();
        pEstados7 = new javax.swing.JPanel();
        jLabel75 = new javax.swing.JLabel();
        BoxCliente1 = new javax.swing.JComboBox<>();
        pEstados12 = new javax.swing.JPanel();
        jLabel83 = new javax.swing.JLabel();
        estatus_caja = new javax.swing.JComboBox<>();
        jPanel19 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtBusqueda = new javax.swing.JTextField();
        pEstados2 = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        boxFiltroStatus = new javax.swing.JComboBox<>();
        jPanel44 = new javax.swing.JPanel();
        jLabel76 = new javax.swing.JLabel();
        txtTotalReg1 = new javax.swing.JTextField();
        jPanel43 = new javax.swing.JPanel();
        jLabel69 = new javax.swing.JLabel();
        txtTotalReg = new javax.swing.JTextField();
        jPanel49 = new javax.swing.JPanel();
        jLabel79 = new javax.swing.JLabel();
        txtSeleccionados = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        bNuevaEntrada = new javax.swing.JButton();
        yv = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        bActualizar = new javax.swing.JButton();
        pEstados10 = new javax.swing.JPanel();
        FechaFiltro = new javax.swing.JCheckBox();
        pEstados8 = new javax.swing.JPanel();
        jLabel81 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        pEstados9 = new javax.swing.JPanel();
        jLabel82 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        bContenedores = new javax.swing.JButton();
        bContenedores1 = new javax.swing.JButton();
        bContenedores3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jCheckBox3 = new javax.swing.JCheckBox();
        FilterFull = new javax.swing.JCheckBox();
        schasis = new javax.swing.JCheckBox();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();

        dDetalles.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        dDetalles.setTitle("Detalles");
        dDetalles.setIconImage(new ImageIcon("images\\icon.png").getImage());
        dDetalles.setMinimumSize(new java.awt.Dimension(1200, 400));
        dDetalles.setModal(true);
        dDetalles.getContentPane().setLayout(new javax.swing.BoxLayout(dDetalles.getContentPane(), javax.swing.BoxLayout.PAGE_AXIS));

        jPanel42.setBackground(new java.awt.Color(255, 255, 255));
        jPanel42.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5));
        jPanel42.setLayout(new java.awt.BorderLayout());

        tCargosCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tCargosCliente.setFillsViewportHeight(true);
        tCargosCliente.getTableHeader().setReorderingAllowed(false);
        tCargosCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tCargosClienteKeyPressed(evt);
            }
        });
        jScrollPane10.setViewportView(tCargosCliente);

        jPanel42.add(jScrollPane10, java.awt.BorderLayout.CENTER);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jLabel68.setText("Cargos Cliente");
        jPanel7.add(jLabel68, java.awt.BorderLayout.CENTER);

        jButton8.setText("Nuevo Cargo");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton8, java.awt.BorderLayout.EAST);

        jPanel42.add(jPanel7, java.awt.BorderLayout.NORTH);

        dDetalles.getContentPane().add(jPanel42);

        mEntradaSalida.setText("Modificar...");
        mEntradaSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mEntradaSalidaActionPerformed(evt);
            }
        });
        jPopupMenu1.add(mEntradaSalida);

        mCargarArchivo.setText("Cargar Archivo...");
        mCargarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mCargarArchivoActionPerformed(evt);
            }
        });
        jPopupMenu1.add(mCargarArchivo);

        mPuntos.setText("Puntos de Revision...");
        mPuntos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mPuntosActionPerformed(evt);
            }
        });
        jPopupMenu1.add(mPuntos);

        dRolChoferes.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        dRolChoferes.setIconImage(new ImageIcon("images\\icon.png").getImage());
        dRolChoferes.setMinimumSize(new java.awt.Dimension(1280, 720));
        dRolChoferes.addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                dRolChoferesWindowLostFocus(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5));
        jPanel6.setLayout(new javax.swing.BoxLayout(jPanel6, javax.swing.BoxLayout.LINE_AXIS));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(new java.awt.BorderLayout());

        txtBusquedaChofer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtBusquedaChofer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBusquedaChoferKeyPressed(evt);
            }
        });
        jPanel10.add(txtBusquedaChofer, java.awt.BorderLayout.CENTER);

        lblFechas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblFechas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFechas.setText("Fechas");
        jPanel10.add(lblFechas, java.awt.BorderLayout.PAGE_START);

        jPanel6.add(jPanel10);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setLayout(new java.awt.BorderLayout());

        boxOrdenChoferes.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        boxOrdenChoferes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "Salario 7 dias", "Salario 15 dias" }));
        boxOrdenChoferes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxOrdenChoferesActionPerformed(evt);
            }
        });
        jPanel11.add(boxOrdenChoferes, java.awt.BorderLayout.CENTER);

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Ordenar por");
        jPanel11.add(jLabel26, java.awt.BorderLayout.PAGE_START);

        jPanel6.add(jPanel11);

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.add(jPanel20);

        dRolChoferes.getContentPane().add(jPanel6, java.awt.BorderLayout.NORTH);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5));
        jPanel1.setLayout(new java.awt.GridLayout(2, 0));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setLayout(new java.awt.BorderLayout());

        tEnsenada.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tEnsenada.setFillsViewportHeight(true);
        tEnsenada.getTableHeader().setReorderingAllowed(false);
        tEnsenada.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tEnsenadaMouseClicked(evt);
            }
        });
        tEnsenada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tEnsenadaKeyPressed(evt);
            }
        });
        jScrollPane4.setViewportView(tEnsenada);

        jPanel14.add(jScrollPane4, java.awt.BorderLayout.CENTER);

        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Ensenada");
        jPanel14.add(jLabel4, java.awt.BorderLayout.PAGE_START);

        jPanel1.add(jPanel14);

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel23.setLayout(new java.awt.BorderLayout());

        tTijuana.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tTijuana.setFillsViewportHeight(true);
        tTijuana.getTableHeader().setReorderingAllowed(false);
        tTijuana.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tTijuanaMouseClicked(evt);
            }
        });
        tTijuana.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tTijuanaKeyPressed(evt);
            }
        });
        jScrollPane19.setViewportView(tTijuana);

        jPanel23.add(jScrollPane19, java.awt.BorderLayout.CENTER);

        jLabel39.setForeground(new java.awt.Color(51, 51, 51));
        jLabel39.setText("Tijuana");
        jPanel23.add(jLabel39, java.awt.BorderLayout.PAGE_START);

        jPanel1.add(jPanel23);

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setLayout(new java.awt.BorderLayout());

        tMexicali.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tMexicali.setFillsViewportHeight(true);
        tMexicali.getTableHeader().setReorderingAllowed(false);
        tMexicali.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tMexicaliMouseClicked(evt);
            }
        });
        tMexicali.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tMexicaliKeyPressed(evt);
            }
        });
        jScrollPane20.setViewportView(tMexicali);

        jPanel24.add(jScrollPane20, java.awt.BorderLayout.CENTER);

        jLabel40.setForeground(new java.awt.Color(51, 51, 51));
        jLabel40.setText("Mexicali");
        jPanel24.add(jLabel40, java.awt.BorderLayout.PAGE_START);

        jPanel1.add(jPanel24);

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setLayout(new java.awt.BorderLayout());

        tForaneo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tForaneo.setFillsViewportHeight(true);
        jScrollPane5.setViewportView(tForaneo);

        jPanel15.add(jScrollPane5, java.awt.BorderLayout.CENTER);

        jLabel28.setForeground(new java.awt.Color(51, 51, 51));
        jLabel28.setText("Foraneos");
        jPanel15.add(jLabel28, java.awt.BorderLayout.PAGE_START);

        jPanel1.add(jPanel15);

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setLayout(new java.awt.BorderLayout());

        tHermosillo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tHermosillo.setFillsViewportHeight(true);
        jScrollPane21.setViewportView(tHermosillo);

        jPanel25.add(jScrollPane21, java.awt.BorderLayout.CENTER);

        jLabel41.setForeground(new java.awt.Color(51, 51, 51));
        jLabel41.setText("Hermosillo");
        jPanel25.add(jLabel41, java.awt.BorderLayout.PAGE_START);

        jPanel1.add(jPanel25);

        dRolChoferes.getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        dUnidades.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        dUnidades.setMinimumSize(new java.awt.Dimension(300, 700));
        dUnidades.setResizable(false);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setLayout(new java.awt.BorderLayout());

        tUnidades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane16.setViewportView(tUnidades);

        jPanel13.add(jScrollPane16, java.awt.BorderLayout.CENTER);

        boxFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tractores", "Remolque", "Dolly", "Generador" }));
        boxFiltro.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        boxFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxFiltroActionPerformed(evt);
            }
        });
        jPanel13.add(boxFiltro, java.awt.BorderLayout.PAGE_START);

        dUnidades.getContentPane().add(jPanel13, java.awt.BorderLayout.CENTER);

        mPrecioRuta.setText("Importe para Chofer");
        mPrecioRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mPrecioRutaActionPerformed(evt);
            }
        });
        jPopupMenu2.add(mPrecioRuta);

        mModificar.setText("Modificar...");
        mModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mModificarActionPerformed(evt);
            }
        });
        jPopupMenu2.add(mModificar);

        mExtraChoferes.setText("Extra Choferes");
        mExtraChoferes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mExtraChoferesActionPerformed(evt);
            }
        });
        jPopupMenu2.add(mExtraChoferes);

        mCancelar1.setText("Cancelar Viaje...");
        mCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mCancelar1ActionPerformed(evt);
            }
        });
        jPopupMenu2.add(mCancelar1);

        dReportes.setTitle("Reportes");
        dReportes.setIconImage(new ImageIcon("images\\icon.png").getImage());
        dReportes.setMinimumSize(new java.awt.Dimension(330, 350));
        dReportes.setResizable(false);

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setLayout(null);

        boxTipoReporte.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Average age of unbilled orders", "Summary", "Load Containers in TJ", "Formato TCL Moka" }));
        boxTipoReporte.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        boxTipoReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxTipoReporteActionPerformed(evt);
            }
        });
        jPanel18.add(boxTipoReporte);
        boxTipoReporte.setBounds(20, 30, 280, 30);

        jButton13.setText("Exportar");
        jButton13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel18.add(jButton13);
        jButton13.setBounds(90, 270, 130, 30);

        boxClienteRep.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        boxClienteRep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxClienteRepActionPerformed(evt);
            }
        });
        jPanel18.add(boxClienteRep);
        boxClienteRep.setBounds(20, 110, 280, 30);

        txtBusquedaReporte.setText("Contenedor");
        txtBusquedaReporte.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtBusquedaReporte.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBusquedaReporteFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBusquedaReporteFocusLost(evt);
            }
        });
        jPanel18.add(txtBusquedaReporte);
        txtBusquedaReporte.setBounds(20, 150, 280, 30);

        boxRutasRep.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Importacion", "Exportacion" }));
        boxRutasRep.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel18.add(boxRutasRep);
        boxRutasRep.setBounds(20, 190, 280, 30);
        jPanel18.add(txtFechafin);
        txtFechafin.setBounds(170, 70, 130, 30);
        jPanel18.add(txtFechaini);
        txtFechaini.setBounds(20, 70, 130, 30);

        dReportes.getContentPane().add(jPanel18, java.awt.BorderLayout.CENTER);

        dEnvioCorreo.setMinimumSize(new java.awt.Dimension(1080, 620));
        dEnvioCorreo.setModal(true);
        dEnvioCorreo.setResizable(false);

        jPanel22.setLayout(null);

        txtSubject1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSubject1.setText("Deposito Viaticos SL");
        jPanel22.add(txtSubject1);
        txtSubject1.setBounds(50, 220, 970, 30);

        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setText("Body:");
        jPanel22.add(jLabel53);
        jLabel53.setBounds(50, 260, 40, 30);

        areaBody1.setColumns(20);
        areaBody1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        areaBody1.setLineWrap(true);
        areaBody1.setRows(5);
        areaBody1.setText("Relacion de depositos para viaticos");
        areaBody1.setWrapStyleWord(true);
        jScrollPane18.setViewportView(areaBody1);

        jPanel22.add(jScrollPane18);
        jScrollPane18.setBounds(50, 290, 970, 190);

        jLabel54.setForeground(new java.awt.Color(255, 255, 255));
        jLabel54.setText("To:");
        jPanel22.add(jLabel54);
        jLabel54.setBounds(50, 140, 90, 16);

        pAttach.setOpaque(false);
        pAttach.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel22.add(pAttach);
        pAttach.setBounds(90, 260, 930, 30);

        jButton14.setText("Enviar");
        jButton14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel22.add(jButton14);
        jButton14.setBounds(910, 490, 110, 30);

        jLabel55.setForeground(new java.awt.Color(255, 255, 255));
        jLabel55.setText("Subject:");
        jPanel22.add(jLabel55);
        jLabel55.setBounds(50, 200, 90, 16);

        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel56.setIcon(new ImageIcon("images\\headcorreo.png"));
        jPanel22.add(jLabel56);
        jLabel56.setBounds(0, 10, 1070, 120);

        txtTo1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTo1.setText("direccion@tcsl.mx");
        txtTo1.setToolTipText("Para Enviar a mas de un correo separe por ;");
        jPanel22.add(txtTo1);
        txtTo1.setBounds(50, 160, 970, 30);

        jLabel57.setIcon(new ImageIcon("images\\backlog.png"));
        jPanel22.add(jLabel57);
        jLabel57.setBounds(0, 0, 1090, 600);

        dEnvioCorreo.getContentPane().add(jPanel22, java.awt.BorderLayout.CENTER);

        mRevisar.setText("Revisar calculo");
        mRevisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mRevisarActionPerformed(evt);
            }
        });
        puCargosClientes.add(mRevisar);

        dRevision.setIconImage(new ImageIcon("images\\icon.png").getImage());
        dRevision.setMinimumSize(new java.awt.Dimension(800, 400));

        txtRevision.setColumns(20);
        txtRevision.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtRevision.setLineWrap(true);
        txtRevision.setRows(5);
        txtRevision.setWrapStyleWord(true);
        jScrollPane23.setViewportView(txtRevision);

        dRevision.getContentPane().add(jScrollPane23, java.awt.BorderLayout.CENTER);

        dEntradaSalida.setAutoRequestFocus(false);
        dEntradaSalida.setIconImage(new ImageIcon("images\\icon.png").getImage());
        dEntradaSalida.setMinimumSize(new java.awt.Dimension(1280, 720));
        dEntradaSalida.setModal(true);
        dEntradaSalida.setResizable(false);

        jPanel36.setBackground(new java.awt.Color(255, 255, 255));
        jPanel36.setLayout(null);

        jLabel36.setText("Fecha llegada");
        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(51, 51, 51));
        jPanel36.add(jLabel36);
        jLabel36.setBounds(550, 490, 160, 20);

        try {
            txtHLlegada.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtHLlegada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHLlegada.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel36.add(txtHLlegada);
        txtHLlegada.setBounds(660, 510, 50, 30);

        jLabel37.setText("Placas MEX");
        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(51, 51, 51));
        jPanel36.add(jLabel37);
        jLabel37.setBounds(1310, 180, 160, 20);

        jButton4.setText("Guardar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel36.add(jButton4);
        jButton4.setBounds(1100, 640, 160, 30);

        boxEstadoCarga.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vacio", "Cargado" }));
        boxEstadoCarga.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                boxEstadoCargaItemStateChanged(evt);
            }
        });
        boxEstadoCarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxEstadoCargaActionPerformed(evt);
            }
        });
        jPanel36.add(boxEstadoCarga);
        boxEstadoCarga.setBounds(360, 430, 160, 30);

        txtPlacasChasis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPlacasChasisKeyPressed(evt);
            }
        });
        jPanel36.add(txtPlacasChasis);
        txtPlacasChasis.setBounds(20, 360, 160, 30);

        jLabel88.setText("Pais");
        jLabel88.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(255, 0, 0));
        jPanel36.add(jLabel88);
        jLabel88.setBounds(20, 410, 130, 20);
        jPanel36.add(txtSello);
        txtSello.setBounds(530, 430, 160, 30);

        boxEstadoPlacas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel36.add(boxEstadoPlacas);
        boxEstadoPlacas.setBounds(1310, 260, 160, 30);

        boxPaisPlacas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MEX", "USA" }));
        boxPaisPlacas.setEnabled(false);
        boxPaisPlacas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxPaisPlacasActionPerformed(evt);
            }
        });
        jPanel36.add(boxPaisPlacas);
        boxPaisPlacas.setBounds(1310, 200, 160, 30);

        jLabel89.setText("Placas Remolque");
        jLabel89.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(255, 0, 0));
        jPanel36.add(jLabel89);
        jLabel89.setBounds(20, 340, 160, 20);

        jLabel90.setText("Chofer Interno");
        jLabel90.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(255, 0, 0));
        jPanel36.add(jLabel90);
        jLabel90.setBounds(20, 250, 330, 20);

        boxChofer.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel36.add(boxChofer);
        boxChofer.setBounds(20, 270, 330, 30);

        jLabel91.setText("Size");
        jLabel91.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(51, 51, 51));
        jPanel36.add(jLabel91);
        jLabel91.setBounds(870, 410, 160, 20);

        bEliminarLlegada.setText("Eliminar Llegada");
        bEliminarLlegada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEliminarLlegadaActionPerformed(evt);
            }
        });
        jPanel36.add(bEliminarLlegada);
        bEliminarLlegada.setBounds(720, 510, 160, 30);

        bLlegada.setText("Fecha Llegada >");
        bLlegada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLlegadaActionPerformed(evt);
            }
        });
        jPanel36.add(bLlegada);
        bLlegada.setBounds(370, 510, 160, 30);

        jLabel92.setText("Estado Placas MEX");
        jLabel92.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(51, 51, 51));
        jPanel36.add(jLabel92);
        jLabel92.setBounds(1310, 240, 160, 20);

        jLabel93.setText("Estado Carga");
        jLabel93.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(255, 0, 0));
        jPanel36.add(jLabel93);
        jLabel93.setBounds(360, 410, 160, 20);

        txtNota.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel36.add(txtNota);
        txtNota.setBounds(20, 580, 510, 30);

        txtContenedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContenedorActionPerformed(evt);
            }
        });
        txtContenedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtContenedorKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtContenedorKeyReleased(evt);
            }
        });
        jPanel36.add(txtContenedor);
        txtContenedor.setBounds(810, 20, 160, 30);

        jLabel94.setText("Driver Move");
        jLabel94.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel94.setForeground(new java.awt.Color(51, 51, 51));
        jPanel36.add(jLabel94);
        jLabel94.setBounds(650, 0, 70, 20);

        jLabel97.setText("Cliente");
        jLabel97.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel97.setForeground(new java.awt.Color(255, 0, 0));
        jPanel36.add(jLabel97);
        jLabel97.setBounds(20, 90, 160, 20);

        cliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cliente.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                clienteItemStateChanged(evt);
            }
        });
        jPanel36.add(cliente);
        cliente.setBounds(20, 110, 160, 30);
        jPanel36.add(txtNombreChofer);
        txtNombreChofer.setBounds(360, 270, 330, 30);

        jLabel100.setText("Origen");
        jLabel100.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel100.setForeground(new java.awt.Color(51, 51, 51));
        jPanel36.add(jLabel100);
        jLabel100.setBounds(20, 490, 160, 20);

        jLabel95.setText("Nombre Chofer Externo");
        jLabel95.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel95.setForeground(new java.awt.Color(51, 51, 51));
        jPanel36.add(jLabel95);
        jLabel95.setBounds(360, 250, 340, 20);

        boxTamano.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        boxTamano.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel36.add(boxTamano);
        boxTamano.setBounds(870, 430, 160, 30);

        jLabel96.setText("Remark");
        jLabel96.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel96.setForeground(new java.awt.Color(51, 51, 51));
        jPanel36.add(jLabel96);
        jLabel96.setBounds(20, 560, 330, 20);

        InOut.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Entrada", "Salida" }));
        InOut.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                InOutItemStateChanged(evt);
            }
        });
        jPanel36.add(InOut);
        InOut.setBounds(150, 20, 90, 30);

        cliente1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel36.add(cliente1);
        cliente1.setBounds(190, 110, 160, 30);

        jLabel98.setText("Cliente Proveedor De Equipo");
        jLabel98.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel98.setForeground(new java.awt.Color(255, 0, 0));
        jPanel36.add(jLabel98);
        jLabel98.setBounds(180, 90, 180, 20);

        jLabel99.setText("Remolque");
        jLabel99.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel99.setForeground(new java.awt.Color(51, 51, 51));
        jPanel36.add(jLabel99);
        jLabel99.setBounds(1490, 20, 160, 20);
        jPanel36.add(camiones);
        camiones.setBounds(190, 210, 270, 30);

        jLabel38.setText("Camion Interno");
        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 0, 0));
        jPanel36.add(jLabel38);
        jLabel38.setBounds(420, 0, 110, 20);

        camionx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MEX", "USA" }));
        camionx.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                camionxItemStateChanged(evt);
            }
        });
        camionx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                camionxActionPerformed(evt);
            }
        });
        jPanel36.add(camionx);
        camionx.setBounds(420, 20, 160, 30);

        Orgn.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MEX", "USA" }));
        Orgn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OrgnActionPerformed(evt);
            }
        });
        jPanel36.add(Orgn);
        Orgn.setBounds(20, 510, 160, 30);

        botando.setText("Botando");
        botando.setOpaque(false);
        botando.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                botandoItemStateChanged(evt);
            }
        });
        jPanel36.add(botando);
        botando.setBounds(1170, 10, 90, 25);

        jLabel19.setText("Ultimo Bit:");
        jPanel36.add(jLabel19);
        jLabel19.setBounds(200, 340, 70, 20);

        bremolque.setText("webDateField1");
        jPanel36.add(bremolque);
        bremolque.setBounds(190, 360, 160, 30);

        jLabel101.setText("Placas Camion Mex");
        jLabel101.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel101.setForeground(new java.awt.Color(255, 0, 0));
        jPanel36.add(jLabel101);
        jLabel101.setBounds(700, 190, 160, 20);
        jPanel36.add(placasunidad);
        placasunidad.setBounds(700, 210, 160, 30);

        jLabel18.setText("Ultimo Bit:");
        jPanel36.add(jLabel18);
        jLabel18.setBounds(370, 340, 70, 20);

        NCHASIS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NCHASISKeyPressed(evt);
            }
        });
        jPanel36.add(NCHASIS);
        NCHASIS.setBounds(1330, 380, 160, 30);

        jLabel103.setText("Numero de Chasis");
        jLabel103.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel103.setForeground(new java.awt.Color(51, 51, 51));
        jPanel36.add(jLabel103);
        jLabel103.setBounds(1330, 360, 160, 20);

        jLabel104.setText("Carrier");
        jLabel104.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel104.setForeground(new java.awt.Color(255, 0, 0));
        jPanel36.add(jLabel104);
        jLabel104.setBounds(20, 190, 160, 20);
        jPanel36.add(CARRIER);
        CARRIER.setBounds(20, 210, 160, 30);

        edicion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        edicion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        edicion.setForeground(new java.awt.Color(51, 51, 51));
        jPanel36.add(edicion);
        edicion.setBounds(390, 640, 290, 20);

        Patio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                PatioItemStateChanged(evt);
            }
        });
        Patio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PatioActionPerformed(evt);
            }
        });
        jPanel36.add(Patio);
        Patio.setBounds(250, 20, 160, 30);

        jLabel102.setText("Destino");
        jLabel102.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel102.setForeground(new java.awt.Color(51, 51, 51));
        jPanel36.add(jLabel102);
        jLabel102.setBounds(200, 490, 160, 20);
        jPanel36.add(destino);
        destino.setBounds(200, 510, 160, 30);

        jLabel106.setText("Damage");
        jLabel106.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel106.setForeground(new java.awt.Color(255, 0, 0));
        jPanel36.add(jLabel106);
        jLabel106.setBounds(1040, 360, 120, 20);

        grade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No", "Si", "Reparado" }));
        grade.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                gradeItemStateChanged(evt);
            }
        });
        grade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gradeActionPerformed(evt);
            }
        });
        jPanel36.add(grade);
        grade.setBounds(1040, 380, 120, 30);

        jLabel107.setText("Assigned to");
        jLabel107.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel107.setForeground(new java.awt.Color(51, 51, 51));
        jPanel36.add(jLabel107);
        jLabel107.setBounds(550, 560, 160, 20);
        jPanel36.add(assignedto);
        assignedto.setBounds(550, 580, 160, 30);

        jLabel108.setText("Yarda");
        jLabel108.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel108.setForeground(new java.awt.Color(51, 51, 51));
        jPanel36.add(jLabel108);
        jLabel108.setBounds(250, 0, 160, 20);

        jLabel109.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel109.setText("Ultima Edicion");
        jLabel109.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel109.setForeground(new java.awt.Color(51, 51, 51));
        jPanel36.add(jLabel109);
        jLabel109.setBounds(520, 620, 160, 20);

        jLabel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel36.add(jLabel1);
        jLabel1.setBounds(370, 620, 340, 50);

        txtDriverMove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDriverMoveActionPerformed(evt);
            }
        });
        txtDriverMove.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDriverMoveKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDriverMoveKeyReleased(evt);
            }
        });
        jPanel36.add(txtDriverMove);
        txtDriverMove.setBounds(650, 20, 150, 30);

        jButton19.setText("Buscar");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        jPanel36.add(jButton19);
        jButton19.setBounds(890, 0, 80, 15);

        Camion.setText("Camion");
        Camion.setOpaque(false);
        Camion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CamionActionPerformed(evt);
            }
        });
        jPanel36.add(Camion);
        Camion.setBounds(1520, 110, 130, 30);

        jButton10.setText("Guardar y Cargar documentos");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel36.add(jButton10);
        jButton10.setBounds(1510, 650, 240, 30);

        bcamion.setText("webDateField1");
        jPanel36.add(bcamion);
        bcamion.setBounds(530, 210, 160, 30);

        jLabel105.setText("Contenedor");
        jLabel105.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel105.setForeground(new java.awt.Color(255, 0, 0));
        jPanel36.add(jLabel105);
        jLabel105.setBounds(810, 0, 160, 20);

        jPanel30.setMinimumSize(new java.awt.Dimension(10, 30));
        jPanel30.setPreferredSize(new java.awt.Dimension(30, 25));
        jPanel36.add(jPanel30);
        jPanel30.setBounds(550, 510, 110, 34);
        jPanel36.add(jSeparator1);
        jSeparator1.setBounds(100, 170, 1160, 20);

        icont_contenedor.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        icont_contenedor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                icont_contenedorItemStateChanged(evt);
            }
        });
        jPanel36.add(icont_contenedor);
        icont_contenedor.setBounds(720, 0, 80, 19);

        jLabel11.setText("TRACTO");
        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel36.add(jLabel11);
        jLabel11.setBounds(20, 160, 90, 17);
        jPanel36.add(jSeparator2);
        jSeparator2.setBounds(120, 320, 1140, 20);

        jLabel13.setText("REMOLQUE");
        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel36.add(jLabel13);
        jLabel13.setBounds(20, 310, 90, 17);

        yardaTittle.setText("YARDA");
        yardaTittle.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jPanel36.add(yardaTittle);
        yardaTittle.setBounds(20, 10, 350, 50);
        jPanel36.add(jSeparator3);
        jSeparator3.setBounds(100, 70, 1160, 20);

        jLabel15.setText("INFORMACION GENERAL");
        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel36.add(jLabel15);
        jLabel15.setBounds(20, 470, 200, 17);
        jPanel36.add(jSeparator4);
        jSeparator4.setBounds(220, 480, 1040, 20);

        jLabel17.setText("Ultimo Bit:");
        jPanel36.add(jLabel17);
        jLabel17.setBounds(530, 190, 70, 20);

        statz1.setText("Taller Folio: ");
        statz1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        statz1.setForeground(new java.awt.Color(0, 51, 153));
        jPanel36.add(statz1);
        statz1.setBounds(1040, 460, 230, 16);

        bchasis.setText("webDateField1");
        jPanel36.add(bchasis);
        bchasis.setBounds(360, 360, 160, 30);

        jLabel42.setText("Placas USA");
        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(51, 51, 51));
        jPanel36.add(jLabel42);
        jLabel42.setBounds(1650, 180, 160, 20);

        boxPaisPlacas1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MEX", "USA" }));
        boxPaisPlacas1.setSelectedItem("USA");
        boxPaisPlacas1.setEnabled(false);
        boxPaisPlacas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxPaisPlacas1ActionPerformed(evt);
            }
        });
        jPanel36.add(boxPaisPlacas1);
        boxPaisPlacas1.setBounds(1650, 200, 160, 30);

        jLabel110.setText("Estado Placas USA");
        jLabel110.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel110.setForeground(new java.awt.Color(51, 51, 51));
        jPanel36.add(jLabel110);
        jLabel110.setBounds(1480, 240, 160, 20);

        boxEstadoPlacas1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel36.add(boxEstadoPlacas1);
        boxEstadoPlacas1.setBounds(1480, 260, 160, 30);

        jLabel111.setText("Placas Camion USA");
        jLabel111.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel111.setForeground(new java.awt.Color(255, 0, 0));
        jPanel36.add(jLabel111);
        jLabel111.setBounds(870, 190, 160, 20);
        jPanel36.add(placasunidad1);
        placasunidad1.setBounds(870, 210, 160, 30);

        jLabel113.setText("Estado Placas MEX");
        jLabel113.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel113.setForeground(new java.awt.Color(255, 0, 0));
        jPanel36.add(jLabel113);
        jLabel113.setBounds(700, 250, 160, 20);

        jLabel114.setText("Estado Placas USA");
        jLabel114.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel114.setForeground(new java.awt.Color(255, 0, 0));
        jPanel36.add(jLabel114);
        jLabel114.setBounds(870, 250, 160, 20);
        jPanel36.add(placasunidad2);
        placasunidad2.setBounds(700, 270, 160, 30);
        jPanel36.add(placasunidad3);
        placasunidad3.setBounds(870, 270, 160, 30);

        paisx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MEX", "USA", "CANADA" }));
        paisx.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        paisx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paisxActionPerformed(evt);
            }
        });
        jPanel36.add(paisx);
        paisx.setBounds(20, 430, 130, 30);

        estx.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        estx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estxActionPerformed(evt);
            }
        });
        jPanel36.add(estx);
        estx.setBounds(160, 430, 190, 30);

        jLabel115.setText("Sello");
        jLabel115.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel115.setForeground(new java.awt.Color(255, 0, 0));
        jPanel36.add(jLabel115);
        jLabel115.setBounds(530, 410, 160, 20);

        jLabel116.setText("Estado");
        jLabel116.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel116.setForeground(new java.awt.Color(255, 0, 0));
        jPanel36.add(jLabel116);
        jLabel116.setBounds(160, 410, 160, 20);

        solochasis.setText("Solo chasis");
        solochasis.setOpaque(false);
        jPanel36.add(solochasis);
        solochasis.setBounds(1170, 40, 93, 25);

        statz.setText("Estatus: ");
        statz.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        statz.setForeground(new java.awt.Color(255, 102, 0));
        jPanel36.add(statz);
        statz.setBounds(810, 50, 270, 16);

        jToggleButton1.setText("Solicitud Taller");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        jPanel36.add(jToggleButton1);
        jToggleButton1.setBounds(1040, 430, 230, 30);

        edicion1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        edicion1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        edicion1.setForeground(new java.awt.Color(51, 51, 51));
        jPanel36.add(edicion1);
        edicion1.setBounds(40, 640, 290, 20);

        jLabel112.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel112.setForeground(new java.awt.Color(51, 51, 51));
        jLabel112.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel112.setText("Creado Por");
        jPanel36.add(jLabel112);
        jLabel112.setBounds(170, 620, 160, 20);

        cajachasisboxExterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cajachasisboxExternoActionPerformed(evt);
            }
        });
        cajachasisboxExterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cajachasisboxExternoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cajachasisboxExternoKeyReleased(evt);
            }
        });
        jPanel36.add(cajachasisboxExterno);
        cajachasisboxExterno.setBounds(990, 20, 160, 30);

        cajachasisbox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ninguno" }));
        cajachasisbox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cajachasisbox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cajachasisboxItemStateChanged(evt);
            }
        });
        cajachasisbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cajachasisboxActionPerformed(evt);
            }
        });
        jPanel36.add(cajachasisbox);
        cajachasisbox.setBounds(1490, 40, 160, 30);

        jLabel23.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel36.add(jLabel23);
        jLabel23.setBounds(20, 620, 340, 50);
        jPanel36.add(licencia);
        licencia.setBounds(1040, 270, 160, 30);

        jLabel117.setText("Licencia");
        jLabel117.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel117.setForeground(new java.awt.Color(255, 0, 0));
        jPanel36.add(jLabel117);
        jLabel117.setBounds(1040, 250, 160, 20);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Remolque", "Tracto" }));
        jPanel36.add(jComboBox1);
        jComboBox1.setBounds(1160, 380, 100, 30);

        jLabel118.setText("Camion Externo");
        jLabel118.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel118.setForeground(new java.awt.Color(51, 51, 51));
        jPanel36.add(jLabel118);
        jLabel118.setBounds(190, 190, 160, 20);

        jLabel119.setText("Chassis");
        jLabel119.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel119.setForeground(new java.awt.Color(51, 51, 51));
        jPanel36.add(jLabel119);
        jLabel119.setBounds(990, 0, 160, 20);

        Fullx.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Fullx.setText("Full, se solicitara el segundo registro al final");
        Fullx.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jPanel36.add(Fullx);
        Fullx.setBounds(500, 50, 300, 16);

        Fullx1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Fullx1.setText("Captura Full");
        Fullx1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jPanel36.add(Fullx1);
        Fullx1.setBounds(640, 50, 160, 16);

        jLabel120.setText("Sello Complementario");
        jLabel120.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel36.add(jLabel120);
        jLabel120.setBounds(700, 410, 160, 20);
        jPanel36.add(sellocomplementario);
        sellocomplementario.setBounds(700, 430, 160, 30);

        jButton23.setText("DM");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });
        jPanel36.add(jButton23);
        jButton23.setBounds(520, 0, 60, 20);

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setText("CLIENTE");
        jPanel36.add(jLabel24);
        jLabel24.setBounds(20, 60, 90, 17);

        dEntradaSalida.getContentPane().add(jPanel36, java.awt.BorderLayout.CENTER);

        dArchivos.setTitle("Archivos");
        dArchivos.setAlwaysOnTop(true);
        dArchivos.setIconImage(new ImageIcon("images\\icon.png").getImage());
        dArchivos.setMinimumSize(new java.awt.Dimension(950, 600));
        dArchivos.setModal(true);

        jTable9.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable9.setFillsViewportHeight(true);
        jTable9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable9MouseClicked(evt);
            }
        });
        jTable9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable9KeyPressed(evt);
            }
        });
        jScrollPane15.setViewportView(jTable9);

        dArchivos.getContentPane().add(jScrollPane15, java.awt.BorderLayout.CENTER);

        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING));

        jPanel9.setPreferredSize(new java.awt.Dimension(300, 42));
        jPanel9.setLayout(new java.awt.BorderLayout());

        jLabel62.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel62.setText("Busqueda");
        jPanel9.add(jLabel62, java.awt.BorderLayout.NORTH);

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });
        jPanel9.add(jTextField1, java.awt.BorderLayout.CENTER);

        jPanel8.add(jPanel9);

        jPanel12.setMaximumSize(new java.awt.Dimension(50, 2147483647));
        jPanel12.setMinimumSize(new java.awt.Dimension(50, 42));
        jPanel12.setPreferredSize(new java.awt.Dimension(100, 42));
        jPanel12.setLayout(new java.awt.BorderLayout());

        jTextField2.setText("50");
        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel12.add(jTextField2, java.awt.BorderLayout.CENTER);

        jLabel63.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel63.setText("Limite");
        jPanel12.add(jLabel63, java.awt.BorderLayout.NORTH);

        jPanel8.add(jPanel12);

        jPanel37.setMaximumSize(new java.awt.Dimension(50, 2147483647));
        jPanel37.setMinimumSize(new java.awt.Dimension(50, 42));
        jPanel37.setPreferredSize(new java.awt.Dimension(100, 42));
        jPanel37.setLayout(new java.awt.BorderLayout());

        bAgregarDocs2.setText("Importar");
        bAgregarDocs2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAgregarDocs2ActionPerformed(evt);
            }
        });
        jPanel37.add(bAgregarDocs2, java.awt.BorderLayout.LINE_END);

        jPanel8.add(jPanel37);

        jPanel34.setMaximumSize(new java.awt.Dimension(50, 2147483647));
        jPanel34.setMinimumSize(new java.awt.Dimension(50, 42));
        jPanel34.setPreferredSize(new java.awt.Dimension(100, 42));
        jPanel34.setLayout(new java.awt.BorderLayout());

        bAgregarDocs1.setText(" Escanear");
        bAgregarDocs1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAgregarDocs1ActionPerformed(evt);
            }
        });
        jPanel34.add(bAgregarDocs1, java.awt.BorderLayout.LINE_END);

        jPanel8.add(jPanel34);

        jCheckBox4.setText("Hojas Separadas");
        jPanel8.add(jCheckBox4);

        jPanel38.setMaximumSize(new java.awt.Dimension(50, 2147483647));
        jPanel38.setMinimumSize(new java.awt.Dimension(50, 42));
        jPanel38.setPreferredSize(new java.awt.Dimension(100, 42));
        jPanel38.setLayout(new java.awt.BorderLayout());

        bAgregarDocs3.setText("Eliminar");
        bAgregarDocs3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAgregarDocs3ActionPerformed(evt);
            }
        });
        jPanel38.add(bAgregarDocs3, java.awt.BorderLayout.LINE_END);

        jPanel8.add(jPanel38);

        dArchivos.getContentPane().add(jPanel8, java.awt.BorderLayout.PAGE_START);

        ContenedorFiltro.setAlwaysOnTop(true);
        ContenedorFiltro.setMinimumSize(new java.awt.Dimension(510, 370));
        ContenedorFiltro.setModal(true);
        ContenedorFiltro.setResizable(false);
        ContenedorFiltro.addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                ContenedorFiltroWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        ContenedorFiltro.getContentPane().setLayout(null);

        Pagos14.setText("Seleccionar");
        Pagos14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Pagos14MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Pagos14MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Pagos14MousePressed(evt);
            }
        });
        Pagos14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Pagos14ActionPerformed(evt);
            }
        });
        ContenedorFiltro.getContentPane().add(Pagos14);
        Pagos14.setBounds(310, 300, 180, 30);

        anexo_add8.setBorderPainted(false);
        anexo_add8.setContentAreaFilled(false);
        anexo_add8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                anexo_add8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                anexo_add8MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                anexo_add8MousePressed(evt);
            }
        });
        anexo_add8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anexo_add8ActionPerformed(evt);
            }
        });
        ContenedorFiltro.getContentPane().add(anexo_add8);
        anexo_add8.setBounds(20, 800, 55, 60);

        anexo_del8.setBorderPainted(false);
        anexo_del8.setContentAreaFilled(false);
        anexo_del8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                anexo_del8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                anexo_del8MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                anexo_del8MousePressed(evt);
            }
        });
        anexo_del8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anexo_del8ActionPerformed(evt);
            }
        });
        ContenedorFiltro.getContentPane().add(anexo_del8);
        anexo_del8.setBounds(80, 800, 55, 60);

        plus12.setBorderPainted(false);
        plus12.setContentAreaFilled(false);
        plus12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                plus12MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                plus12MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                plus12MousePressed(evt);
            }
        });
        plus12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plus12ActionPerformed(evt);
            }
        });
        ContenedorFiltro.getContentPane().add(plus12);
        plus12.setBounds(140, 800, 60, 60);

        plus13.setBorderPainted(false);
        plus13.setContentAreaFilled(false);
        plus13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                plus13MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                plus13MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                plus13MousePressed(evt);
            }
        });
        plus13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plus13ActionPerformed(evt);
            }
        });
        ContenedorFiltro.getContentPane().add(plus13);
        plus13.setBounds(210, 800, 60, 60);

        jScrollPane45.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 3, true));

        contenedor_tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        contenedor_tbl.getTableHeader().setReorderingAllowed(false);
        contenedor_tbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                contenedor_tblMouseClicked(evt);
            }
        });
        jScrollPane45.setViewportView(contenedor_tbl);

        ContenedorFiltro.getContentPane().add(jScrollPane45);
        jScrollPane45.setBounds(10, 50, 484, 237);

        Pagos15.setBorderPainted(false);
        Pagos15.setContentAreaFilled(false);
        Pagos15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Pagos15MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Pagos15MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Pagos15MousePressed(evt);
            }
        });
        Pagos15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Pagos15ActionPerformed(evt);
            }
        });
        ContenedorFiltro.getContentPane().add(Pagos15);
        Pagos15.setBounds(300, 830, 30, 30);

        jCheckBox6.setSelected(true);
        jCheckBox6.setText("Cargar chofer,unidad,destino en base a entrada");
        jCheckBox6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox6ItemStateChanged(evt);
            }
        });
        ContenedorFiltro.getContentPane().add(jCheckBox6);
        jCheckBox6.setBounds(10, 20, 560, 25);

        jMenuItem7.setText("Abrir");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jPopupMenu3.add(jMenuItem7);

        jMenuItem9.setText("Eliminar...");
        jPopupMenu3.add(jMenuItem9);

        dArchivos1.setTitle("Archivos");
        dArchivos1.setIconImage(new ImageIcon("images\\icon.png").getImage());
        dArchivos1.setMinimumSize(new java.awt.Dimension(550, 600));

        jTable10.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable10.setFillsViewportHeight(true);
        jTable10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable10MouseClicked(evt);
            }
        });
        jTable10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable10KeyPressed(evt);
            }
        });
        jScrollPane17.setViewportView(jTable10);

        dArchivos1.getContentPane().add(jScrollPane17, java.awt.BorderLayout.CENTER);

        jPanel21.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING));

        jPanel26.setPreferredSize(new java.awt.Dimension(300, 42));
        jPanel26.setLayout(new java.awt.BorderLayout());

        jLabel64.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel64.setText("Busqueda");
        jPanel26.add(jLabel64, java.awt.BorderLayout.NORTH);

        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField3KeyPressed(evt);
            }
        });
        jPanel26.add(jTextField3, java.awt.BorderLayout.CENTER);

        jPanel21.add(jPanel26);

        jPanel27.setMaximumSize(new java.awt.Dimension(50, 2147483647));
        jPanel27.setMinimumSize(new java.awt.Dimension(50, 42));
        jPanel27.setPreferredSize(new java.awt.Dimension(100, 42));
        jPanel27.setLayout(new java.awt.BorderLayout());

        jTextField4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField4.setText("50");
        jPanel27.add(jTextField4, java.awt.BorderLayout.CENTER);

        jLabel65.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel65.setText("Limite");
        jPanel27.add(jLabel65, java.awt.BorderLayout.NORTH);

        jPanel21.add(jPanel27);

        dArchivos1.getContentPane().add(jPanel21, java.awt.BorderLayout.PAGE_START);

        Perfiles.setTitle("Perfiles de Cobro");
        Perfiles.setAlwaysOnTop(true);
        Perfiles.setMinimumSize(new java.awt.Dimension(970, 240));
        Perfiles.setModal(true);
        Perfiles.setResizable(false);
        Perfiles.addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                PerfilesWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        Perfiles.getContentPane().setLayout(null);

        Pagos16.setText("Guardar");
        Pagos16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Pagos16MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Pagos16MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Pagos16MousePressed(evt);
            }
        });
        Pagos16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Pagos16ActionPerformed(evt);
            }
        });
        Perfiles.getContentPane().add(Pagos16);
        Pagos16.setBounds(860, 130, 90, 60);

        anexo_add9.setBorderPainted(false);
        anexo_add9.setContentAreaFilled(false);
        anexo_add9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                anexo_add9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                anexo_add9MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                anexo_add9MousePressed(evt);
            }
        });
        anexo_add9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anexo_add9ActionPerformed(evt);
            }
        });
        Perfiles.getContentPane().add(anexo_add9);
        anexo_add9.setBounds(20, 800, 55, 60);

        anexo_del9.setBorderPainted(false);
        anexo_del9.setContentAreaFilled(false);
        anexo_del9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                anexo_del9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                anexo_del9MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                anexo_del9MousePressed(evt);
            }
        });
        anexo_del9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anexo_del9ActionPerformed(evt);
            }
        });
        Perfiles.getContentPane().add(anexo_del9);
        anexo_del9.setBounds(80, 800, 55, 60);

        plus14.setBorderPainted(false);
        plus14.setContentAreaFilled(false);
        plus14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                plus14MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                plus14MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                plus14MousePressed(evt);
            }
        });
        plus14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plus14ActionPerformed(evt);
            }
        });
        Perfiles.getContentPane().add(plus14);
        plus14.setBounds(140, 800, 60, 60);

        plus15.setBorderPainted(false);
        plus15.setContentAreaFilled(false);
        plus15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                plus15MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                plus15MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                plus15MousePressed(evt);
            }
        });
        plus15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plus15ActionPerformed(evt);
            }
        });
        Perfiles.getContentPane().add(plus15);
        plus15.setBounds(210, 800, 60, 60);

        Pagos17.setBorderPainted(false);
        Pagos17.setContentAreaFilled(false);
        Pagos17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Pagos17MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Pagos17MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Pagos17MousePressed(evt);
            }
        });
        Pagos17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Pagos17ActionPerformed(evt);
            }
        });
        Perfiles.getContentPane().add(Pagos17);
        Pagos17.setBounds(300, 830, 30, 30);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Nombre del perfil:");
        Perfiles.getContentPane().add(jLabel2);
        jLabel2.setBounds(20, 116, 170, 30);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Perfiles.getContentPane().add(jLabel3);
        jLabel3.setBounds(230, 30, 260, 0);
        Perfiles.getContentPane().add(perfilnombre);
        perfilnombre.setBounds(210, 120, 260, 30);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Concepto Facturacion:");
        Perfiles.getContentPane().add(jLabel6);
        jLabel6.setBounds(20, 156, 190, 30);
        Perfiles.getContentPane().add(perfilconcepto);
        perfilconcepto.setBounds(210, 160, 260, 30);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Rangos de cobro");
        Perfiles.getContentPane().add(jLabel7);
        jLabel7.setBounds(510, 22, 190, 30);

        clientePerfil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        clientePerfil.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                clientePerfilItemStateChanged(evt);
            }
        });
        Perfiles.getContentPane().add(clientePerfil);
        clientePerfil.setBounds(210, 30, 260, 30);

        Perfil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Perfil.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                PerfilItemStateChanged(evt);
            }
        });
        Perfiles.getContentPane().add(Perfil);
        Perfil.setBounds(210, 70, 260, 30);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Perfil:");
        Perfiles.getContentPane().add(jLabel9);
        jLabel9.setBounds(20, 72, 190, 30);

        tableRango.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tableRango);

        Perfiles.getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(510, 50, 340, 140);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Cliente: ");
        Perfiles.getContentPane().add(jLabel10);
        jLabel10.setBounds(20, 32, 190, 30);

        jButton5.setText("-");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        Perfiles.getContentPane().add(jButton5);
        jButton5.setBounds(860, 90, 40, 25);

        jButton6.setText("+");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        Perfiles.getContentPane().add(jButton6);
        jButton6.setBounds(860, 50, 40, 30);

        Facturacion.setTitle("Facturas Generadas");
        Facturacion.setAlwaysOnTop(true);
        Facturacion.setMinimumSize(new java.awt.Dimension(500, 500));
        Facturacion.setModal(true);
        Facturacion.setResizable(false);
        Facturacion.addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                FacturacionWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        Facturacion.getContentPane().setLayout(null);

        anexo_add10.setBorderPainted(false);
        anexo_add10.setContentAreaFilled(false);
        anexo_add10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                anexo_add10MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                anexo_add10MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                anexo_add10MousePressed(evt);
            }
        });
        anexo_add10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anexo_add10ActionPerformed(evt);
            }
        });
        Facturacion.getContentPane().add(anexo_add10);
        anexo_add10.setBounds(20, 800, 55, 60);

        anexo_del10.setBorderPainted(false);
        anexo_del10.setContentAreaFilled(false);
        anexo_del10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                anexo_del10MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                anexo_del10MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                anexo_del10MousePressed(evt);
            }
        });
        anexo_del10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anexo_del10ActionPerformed(evt);
            }
        });
        Facturacion.getContentPane().add(anexo_del10);
        anexo_del10.setBounds(80, 800, 55, 60);

        plus16.setBorderPainted(false);
        plus16.setContentAreaFilled(false);
        plus16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                plus16MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                plus16MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                plus16MousePressed(evt);
            }
        });
        plus16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plus16ActionPerformed(evt);
            }
        });
        Facturacion.getContentPane().add(plus16);
        plus16.setBounds(140, 800, 60, 60);

        plus17.setBorderPainted(false);
        plus17.setContentAreaFilled(false);
        plus17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                plus17MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                plus17MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                plus17MousePressed(evt);
            }
        });
        plus17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plus17ActionPerformed(evt);
            }
        });
        Facturacion.getContentPane().add(plus17);
        plus17.setBounds(210, 800, 60, 60);

        Pagos19.setBorderPainted(false);
        Pagos19.setContentAreaFilled(false);
        Pagos19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Pagos19MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Pagos19MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Pagos19MousePressed(evt);
            }
        });
        Pagos19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Pagos19ActionPerformed(evt);
            }
        });
        Facturacion.getContentPane().add(Pagos19);
        Pagos19.setBounds(300, 830, 30, 30);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Facturacion.getContentPane().add(jLabel12);
        jLabel12.setBounds(230, 30, 260, 0);

        jLabel16.setText("Facturacion");
        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Facturacion.getContentPane().add(jLabel16);
        jLabel16.setBounds(20, 10, 190, 30);

        facturacion_tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        facturacion_tbl.setFillsViewportHeight(true);
        facturacion_tbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                facturacion_tblMouseClicked(evt);
            }
        });
        facturacion_tbl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                facturacion_tblKeyPressed(evt);
            }
        });
        jScrollPane22.setViewportView(facturacion_tbl);

        Facturacion.getContentPane().add(jScrollPane22);
        jScrollPane22.setBounds(20, 50, 452, 370);

        jButton9.setText("Ver Factura");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        Facturacion.getContentPane().add(jButton9);
        jButton9.setBounds(240, 420, 230, 25);

        jButton11.setText("Ver Reporte Generado");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        Facturacion.getContentPane().add(jButton11);
        jButton11.setBounds(20, 420, 220, 25);

        facturados.setSelected(true);
        facturados.setText("Ver solo cortes facturados");
        facturados.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                facturadosItemStateChanged(evt);
            }
        });
        Facturacion.getContentPane().add(facturados);
        facturados.setBounds(270, 20, 200, 25);

        AltaYarda.setTitle("Yardas Virtuales");
        AltaYarda.setIconImage(new ImageIcon("images\\icon.png").getImage());
        AltaYarda.setMinimumSize(new java.awt.Dimension(315, 500));

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));
        jPanel28.setLayout(null);

        taccesos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        taccesos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                taccesosMouseClicked(evt);
            }
        });
        taccesos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                taccesosKeyPressed(evt);
            }
        });
        jScrollPane24.setViewportView(taccesos);

        jPanel28.add(jScrollPane24);
        jScrollPane24.setBounds(20, 80, 270, 320);

        jButton22.setText("-");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });
        jPanel28.add(jButton22);
        jButton22.setBounds(20, 410, 50, 30);
        jPanel28.add(pwd);
        pwd.setBounds(20, 30, 170, 22);

        jButton12.setText("Agregar");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel28.add(jButton12);
        jButton12.setBounds(210, 10, 79, 40);

        jLabel22.setText("Agregar yarda virtual");
        jPanel28.add(jLabel22);
        jLabel22.setBounds(20, 10, 170, 16);

        AltaYarda.getContentPane().add(jPanel28, java.awt.BorderLayout.CENTER);

        PuntosRevision.setTitle("Puntos de Revision");
        PuntosRevision.setIconImage(new ImageIcon("images\\icon.png").getImage());
        PuntosRevision.setMinimumSize(new java.awt.Dimension(1280, 670));

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));
        jPanel29.setLayout(null);

        puntos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        puntos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                puntosMouseClicked(evt);
            }
        });
        puntos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                puntosKeyPressed(evt);
            }
        });
        jScrollPane25.setViewportView(puntos);

        jPanel29.add(jScrollPane25);
        jScrollPane25.setBounds(20, 20, 990, 580);

        lblFoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel29.add(lblFoto);
        lblFoto.setBounds(1030, 40, 220, 260);

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Foto Entrada");
        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 51, 51));
        jPanel29.add(jLabel20);
        jLabel20.setBounds(1030, 20, 220, 20);

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(51, 51, 51));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Foto Salida");
        jPanel29.add(jLabel21);
        jLabel21.setBounds(1030, 320, 220, 20);

        lblFoto1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFoto1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel29.add(lblFoto1);
        lblFoto1.setBounds(1030, 340, 220, 260);

        PuntosRevision.getContentPane().add(jPanel29, java.awt.BorderLayout.CENTER);

        EquipmentControl.setAutoRequestFocus(false);
        EquipmentControl.setMinimumSize(new java.awt.Dimension(1280, 600));

        CTRL.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(CTRL);

        EquipmentControl.getContentPane().add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jButton18.setText("Actualizar listado");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        jPanel31.add(jButton18);

        jButton17.setText("Exportar a documento de excel");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        jPanel31.add(jButton17);

        EquipmentControl.getContentPane().add(jPanel31, java.awt.BorderLayout.PAGE_START);

        ContenedorInterno.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        ContenedorInterno.setTitle("Seleccionar contenedor de listado");
        ContenedorInterno.setAutoRequestFocus(false);
        ContenedorInterno.setIconImage(new ImageIcon("images\\icon.png").getImage());
        ContenedorInterno.setMinimumSize(new java.awt.Dimension(370, 270));
        ContenedorInterno.setModal(true);
        ContenedorInterno.setResizable(false);
        ContenedorInterno.getContentPane().setLayout(null);

        boxcontenedorx.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ContenedorInterno.getContentPane().add(boxcontenedorx);
        boxcontenedorx.setBounds(10, 130, 350, 40);

        busquedaContenedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                busquedaContenedorKeyPressed(evt);
            }
        });
        ContenedorInterno.getContentPane().add(busquedaContenedor);
        busquedaContenedor.setBounds(10, 50, 350, 40);

        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Busque un contenedor interno en la lista:");
        ContenedorInterno.getContentPane().add(jLabel30);
        jLabel30.setBounds(10, 10, 340, 30);

        jButton20.setText("Elegir");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        ContenedorInterno.getContentPane().add(jButton20);
        jButton20.setBounds(120, 200, 100, 25);

        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Seleccione el contenedor interno:");
        ContenedorInterno.getContentPane().add(jLabel25);
        jLabel25.setBounds(10, 100, 340, 30);

        jLabel29.setBackground(new java.awt.Color(51, 51, 51));
        jLabel29.setOpaque(true);
        ContenedorInterno.getContentPane().add(jLabel29);
        jLabel29.setBounds(0, 0, 370, 290);

        ContenedorInterno.getAccessibleContext().setAccessibleParent(dEntradaSalida);

        dTractoDM.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        dTractoDM.setAlwaysOnTop(true);
        dTractoDM.setAutoRequestFocus(false);
        dTractoDM.setMinimumSize(new java.awt.Dimension(300, 300));
        dTractoDM.setResizable(false);

        jPanel32.setBackground(new java.awt.Color(255, 255, 255));
        jPanel32.setLayout(new java.awt.BorderLayout());

        tDm.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tDm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tDmMouseClicked(evt);
            }
        });
        jScrollPane26.setViewportView(tDm);

        jPanel32.add(jScrollPane26, java.awt.BorderLayout.CENTER);

        dmText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dmTextActionPerformed(evt);
            }
        });
        jPanel32.add(dmText, java.awt.BorderLayout.PAGE_START);

        dTractoDM.getContentPane().add(jPanel32, java.awt.BorderLayout.CENTER);

        perfilBuscador1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ninguno" }));
        perfilBuscador1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        perfilBuscador1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                perfilBuscador1ItemStateChanged(evt);
            }
        });
        perfilBuscador1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                perfilBuscador1ActionPerformed(evt);
            }
        });

        jLabel78.setText("Perfil Cobro");
        jLabel78.setBackground(new java.awt.Color(51, 51, 51));
        jLabel78.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(51, 51, 51));

        jButton7.setText("Historial Facturas");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton2.setText("Factura Cobro");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        bContenedores2.setText("Reporte Cobro");
        bContenedores2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bContenedores2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bContenedores2ActionPerformed(evt);
            }
        });

        jButton3.setText("Crear Perfil Cobro");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        perfilBuscador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ninguno" }));
        perfilBuscador.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        perfilBuscador.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                perfilBuscadorItemStateChanged(evt);
            }
        });
        perfilBuscador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                perfilBuscadorActionPerformed(evt);
            }
        });

        jLabel77.setText("Perfil Cobro");
        jLabel77.setBackground(new java.awt.Color(51, 51, 51));
        jLabel77.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(51, 51, 51));

        pEstados11.setBackground(new java.awt.Color(255, 255, 255));
        pEstados11.setMaximumSize(new java.awt.Dimension(150, 2147483647));
        pEstados11.setMinimumSize(new java.awt.Dimension(80, 38));
        pEstados11.setPreferredSize(new java.awt.Dimension(120, 40));
        pEstados11.setLayout(new java.awt.BorderLayout());

        jButton16.setText("Equipment Control Excel");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton15.setText("Equipment Control");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        bControlEquipment.setText("Control de Remolques");
        bControlEquipment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bControlEquipmentActionPerformed(evt);
            }
        });

        jButton21.setText("Transbordos");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Guardias");
        setMinimumSize(new java.awt.Dimension(1750, 176));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel2.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 10));

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setCellSelectionEnabled(true);
        jTable1.setFillsViewportHeight(true);
        jTable1.setRowHeight(20);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable1MouseReleased(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5));
        jPanel3.setMinimumSize(new java.awt.Dimension(1672, 135));
        jPanel3.setPreferredSize(new java.awt.Dimension(2169, 135));
        jPanel3.setRequestFocusEnabled(false);

        pEstados6.setBackground(new java.awt.Color(255, 255, 255));
        pEstados6.setMaximumSize(new java.awt.Dimension(150, 2147483647));
        pEstados6.setMinimumSize(new java.awt.Dimension(80, 38));
        pEstados6.setPreferredSize(new java.awt.Dimension(120, 40));
        pEstados6.setLayout(new java.awt.BorderLayout());

        jLabel74.setText("Ver");
        jLabel74.setBackground(new java.awt.Color(51, 51, 51));
        jLabel74.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(51, 51, 51));
        pEstados6.add(jLabel74, java.awt.BorderLayout.NORTH);

        HistorialActual.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Historial", "Actual" }));
        HistorialActual.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        HistorialActual.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                HistorialActualItemStateChanged(evt);
            }
        });
        HistorialActual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HistorialActualActionPerformed(evt);
            }
        });
        pEstados6.add(HistorialActual, java.awt.BorderLayout.PAGE_END);

        jPanel3.add(pEstados6);

        pEstados1.setBackground(new java.awt.Color(255, 255, 255));
        pEstados1.setMaximumSize(new java.awt.Dimension(150, 2147483647));
        pEstados1.setMinimumSize(new java.awt.Dimension(80, 38));
        pEstados1.setPreferredSize(new java.awt.Dimension(120, 40));
        pEstados1.setLayout(new java.awt.BorderLayout());

        jLabel70.setText("Buscar En...");
        jLabel70.setBackground(new java.awt.Color(51, 51, 51));
        jLabel70.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(51, 51, 51));
        pEstados1.add(jLabel70, java.awt.BorderLayout.NORTH);

        boxFiltroBusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todo", "Entradas", "Salidas" }));
        boxFiltroBusqueda.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        boxFiltroBusqueda.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                boxFiltroBusquedaItemStateChanged(evt);
            }
        });
        boxFiltroBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxFiltroBusquedaActionPerformed(evt);
            }
        });
        pEstados1.add(boxFiltroBusqueda, java.awt.BorderLayout.PAGE_END);

        jPanel3.add(pEstados1);

        pEstados3.setBackground(new java.awt.Color(255, 255, 255));
        pEstados3.setMaximumSize(new java.awt.Dimension(150, 2147483647));
        pEstados3.setMinimumSize(new java.awt.Dimension(80, 38));
        pEstados3.setPreferredSize(new java.awt.Dimension(120, 40));
        pEstados3.setLayout(new java.awt.BorderLayout());

        jLabel71.setBackground(new java.awt.Color(51, 51, 51));
        jLabel71.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(51, 51, 51));
        jLabel71.setText("Carga");
        pEstados3.add(jLabel71, java.awt.BorderLayout.NORTH);

        boxFiltroBusqueda1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todo", "Empty", "Load" }));
        boxFiltroBusqueda1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        boxFiltroBusqueda1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                boxFiltroBusqueda1ItemStateChanged(evt);
            }
        });
        boxFiltroBusqueda1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxFiltroBusqueda1ActionPerformed(evt);
            }
        });
        pEstados3.add(boxFiltroBusqueda1, java.awt.BorderLayout.PAGE_END);

        jPanel3.add(pEstados3);

        pEstados4.setBackground(new java.awt.Color(255, 255, 255));
        pEstados4.setMaximumSize(new java.awt.Dimension(150, 2147483647));
        pEstados4.setMinimumSize(new java.awt.Dimension(80, 38));
        pEstados4.setPreferredSize(new java.awt.Dimension(120, 40));
        pEstados4.setLayout(new java.awt.BorderLayout());

        jLabel72.setBackground(new java.awt.Color(51, 51, 51));
        jLabel72.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(51, 51, 51));
        jLabel72.setText("Patio");
        pEstados4.add(jLabel72, java.awt.BorderLayout.NORTH);

        BoxPatios.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todo", "Empty", "Load" }));
        BoxPatios.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BoxPatios.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                BoxPatiosItemStateChanged(evt);
            }
        });
        BoxPatios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoxPatiosActionPerformed(evt);
            }
        });
        pEstados4.add(BoxPatios, java.awt.BorderLayout.PAGE_END);

        jPanel3.add(pEstados4);

        pEstados5.setBackground(new java.awt.Color(255, 255, 255));
        pEstados5.setMaximumSize(new java.awt.Dimension(150, 2147483647));
        pEstados5.setMinimumSize(new java.awt.Dimension(80, 38));
        pEstados5.setPreferredSize(new java.awt.Dimension(120, 40));
        pEstados5.setLayout(new java.awt.BorderLayout());

        jLabel73.setText("Cliente");
        jLabel73.setBackground(new java.awt.Color(51, 51, 51));
        jLabel73.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(51, 51, 51));
        pEstados5.add(jLabel73, java.awt.BorderLayout.NORTH);

        BoxCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BoxCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todo", "Empty", "Load" }));
        BoxCliente.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                BoxClienteItemStateChanged(evt);
            }
        });
        BoxCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoxClienteActionPerformed(evt);
            }
        });
        pEstados5.add(BoxCliente, java.awt.BorderLayout.PAGE_END);

        jPanel3.add(pEstados5);

        pEstados7.setBackground(new java.awt.Color(255, 255, 255));
        pEstados7.setMaximumSize(new java.awt.Dimension(150, 2147483647));
        pEstados7.setMinimumSize(new java.awt.Dimension(80, 38));
        pEstados7.setPreferredSize(new java.awt.Dimension(120, 40));
        pEstados7.setLayout(new java.awt.BorderLayout());

        jLabel75.setText("Equipment Prov");
        jLabel75.setBackground(new java.awt.Color(51, 51, 51));
        jLabel75.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(51, 51, 51));
        pEstados7.add(jLabel75, java.awt.BorderLayout.NORTH);

        BoxCliente1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todo", "Empty", "Load" }));
        BoxCliente1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BoxCliente1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                BoxCliente1ItemStateChanged(evt);
            }
        });
        BoxCliente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoxCliente1ActionPerformed(evt);
            }
        });
        pEstados7.add(BoxCliente1, java.awt.BorderLayout.PAGE_END);

        jPanel3.add(pEstados7);

        pEstados12.setBackground(new java.awt.Color(255, 255, 255));
        pEstados12.setMaximumSize(new java.awt.Dimension(150, 2147483647));
        pEstados12.setMinimumSize(new java.awt.Dimension(80, 38));
        pEstados12.setPreferredSize(new java.awt.Dimension(120, 40));
        pEstados12.setLayout(new java.awt.BorderLayout());

        jLabel83.setBackground(new java.awt.Color(51, 51, 51));
        jLabel83.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(51, 51, 51));
        jLabel83.setText("Estatus Caja");
        pEstados12.add(jLabel83, java.awt.BorderLayout.NORTH);

        estatus_caja.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ver Todos", "Disponible", "Asignado", "Inactivo (F/S)", "Renta" }));
        estatus_caja.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        estatus_caja.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                estatus_cajaItemStateChanged(evt);
            }
        });
        estatus_caja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estatus_cajaActionPerformed(evt);
            }
        });
        pEstados12.add(estatus_caja, java.awt.BorderLayout.PAGE_END);

        jPanel3.add(pEstados12);

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setPreferredSize(new java.awt.Dimension(150, 40));
        jPanel19.setLayout(new java.awt.BorderLayout());

        jLabel5.setText("Busqueda");
        jLabel5.setBackground(new java.awt.Color(51, 51, 51));
        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jPanel19.add(jLabel5, java.awt.BorderLayout.NORTH);

        txtBusqueda.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtBusqueda.setPreferredSize(new java.awt.Dimension(14, 30));
        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyPressed(evt);
            }
        });
        jPanel19.add(txtBusqueda, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel19);

        pEstados2.setBackground(new java.awt.Color(255, 255, 255));
        pEstados2.setMaximumSize(new java.awt.Dimension(150, 2147483647));
        pEstados2.setMinimumSize(new java.awt.Dimension(100, 38));
        pEstados2.setPreferredSize(new java.awt.Dimension(110, 40));
        pEstados2.setLayout(new java.awt.BorderLayout());

        jLabel80.setBackground(new java.awt.Color(51, 51, 51));
        jLabel80.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(51, 51, 51));
        jLabel80.setText("Dentro de");
        pEstados2.add(jLabel80, java.awt.BorderLayout.NORTH);

        boxFiltroStatus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        boxFiltroStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activos", "Todos" }));
        boxFiltroStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxFiltroStatusActionPerformed(evt);
            }
        });
        pEstados2.add(boxFiltroStatus, java.awt.BorderLayout.PAGE_END);

        jPanel3.add(pEstados2);

        jPanel44.setBackground(new java.awt.Color(255, 255, 255));
        jPanel44.setMaximumSize(new java.awt.Dimension(100, 2147483647));
        jPanel44.setMinimumSize(new java.awt.Dimension(100, 38));
        jPanel44.setPreferredSize(new java.awt.Dimension(50, 40));
        jPanel44.setLayout(new java.awt.BorderLayout());

        jLabel76.setBackground(new java.awt.Color(51, 51, 51));
        jLabel76.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(51, 51, 51));
        jLabel76.setText("Visibles");
        jPanel44.add(jLabel76, java.awt.BorderLayout.NORTH);

        txtTotalReg1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotalReg1.setText("1000");
        txtTotalReg1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTotalReg1.setPreferredSize(new java.awt.Dimension(14, 30));
        txtTotalReg1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTotalReg1KeyPressed(evt);
            }
        });
        jPanel44.add(txtTotalReg1, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel44);

        jPanel43.setBackground(new java.awt.Color(255, 255, 255));
        jPanel43.setMaximumSize(new java.awt.Dimension(100, 2147483647));
        jPanel43.setMinimumSize(new java.awt.Dimension(100, 38));
        jPanel43.setPreferredSize(new java.awt.Dimension(50, 40));
        jPanel43.setLayout(new java.awt.BorderLayout());

        jLabel69.setText("Registros");
        jLabel69.setBackground(new java.awt.Color(51, 51, 51));
        jLabel69.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(51, 51, 51));
        jPanel43.add(jLabel69, java.awt.BorderLayout.NORTH);

        txtTotalReg.setEditable(false);
        txtTotalReg.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotalReg.setText("0");
        txtTotalReg.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTotalReg.setPreferredSize(new java.awt.Dimension(14, 30));
        txtTotalReg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTotalRegKeyPressed(evt);
            }
        });
        jPanel43.add(txtTotalReg, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel43);

        jPanel49.setBackground(new java.awt.Color(255, 255, 255));
        jPanel49.setMaximumSize(new java.awt.Dimension(100, 2147483647));
        jPanel49.setMinimumSize(new java.awt.Dimension(100, 38));
        jPanel49.setPreferredSize(new java.awt.Dimension(80, 40));
        jPanel49.setLayout(new java.awt.BorderLayout());

        jLabel79.setText("Seleccionados");
        jLabel79.setBackground(new java.awt.Color(51, 51, 51));
        jLabel79.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(51, 51, 51));
        jPanel49.add(jLabel79, java.awt.BorderLayout.NORTH);

        txtSeleccionados.setEditable(false);
        txtSeleccionados.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSeleccionados.setText("0");
        txtSeleccionados.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSeleccionados.setPreferredSize(new java.awt.Dimension(14, 30));
        txtSeleccionados.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSeleccionadosKeyPressed(evt);
            }
        });
        jPanel49.add(txtSeleccionados, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel49);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.add(jPanel4);

        bNuevaEntrada.setText("Nuevo");
        bNuevaEntrada.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bNuevaEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNuevaEntradaActionPerformed(evt);
            }
        });
        jPanel3.add(bNuevaEntrada);

        yv.setText("Yardas Virtuales");
        yv.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        yv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yvActionPerformed(evt);
            }
        });
        jPanel3.add(yv);

        jCheckBox1.setSelected(true);
        jCheckBox1.setText("Ultimos Registros");
        jCheckBox1.setOpaque(false);
        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });
        jPanel3.add(jCheckBox1);

        jCheckBox2.setSelected(true);
        jCheckBox2.setText("Orden Dias/Contenedor");
        jCheckBox2.setOpaque(false);
        jCheckBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox2ItemStateChanged(evt);
            }
        });
        jPanel3.add(jCheckBox2);

        bActualizar.setText("Actualizar");
        bActualizar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bActualizarActionPerformed(evt);
            }
        });
        jPanel3.add(bActualizar);

        pEstados10.setBackground(new java.awt.Color(255, 255, 255));
        pEstados10.setMaximumSize(new java.awt.Dimension(150, 2147483647));
        pEstados10.setMinimumSize(new java.awt.Dimension(100, 38));
        pEstados10.setPreferredSize(new java.awt.Dimension(150, 40));
        pEstados10.setLayout(new java.awt.BorderLayout());

        FechaFiltro.setText("Filtrar por Fecha");
        FechaFiltro.setOpaque(false);
        FechaFiltro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                FechaFiltroItemStateChanged(evt);
            }
        });
        pEstados10.add(FechaFiltro, java.awt.BorderLayout.CENTER);

        jPanel3.add(pEstados10);

        pEstados8.setBackground(new java.awt.Color(255, 255, 255));
        pEstados8.setMaximumSize(new java.awt.Dimension(150, 2147483647));
        pEstados8.setMinimumSize(new java.awt.Dimension(80, 38));
        pEstados8.setPreferredSize(new java.awt.Dimension(100, 40));
        pEstados8.setLayout(new java.awt.BorderLayout());

        jLabel81.setText("Desde");
        jLabel81.setBackground(new java.awt.Color(51, 51, 51));
        jLabel81.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(51, 51, 51));
        jLabel81.setPreferredSize(new java.awt.Dimension(34, 10));
        pEstados8.add(jLabel81, java.awt.BorderLayout.NORTH);

        jPanel16.setMinimumSize(new java.awt.Dimension(10, 30));
        jPanel16.setPreferredSize(new java.awt.Dimension(30, 25));
        pEstados8.add(jPanel16, java.awt.BorderLayout.PAGE_END);

        jPanel3.add(pEstados8);

        pEstados9.setBackground(new java.awt.Color(255, 255, 255));
        pEstados9.setMaximumSize(new java.awt.Dimension(150, 2147483647));
        pEstados9.setMinimumSize(new java.awt.Dimension(80, 38));
        pEstados9.setPreferredSize(new java.awt.Dimension(100, 40));
        pEstados9.setLayout(new java.awt.BorderLayout());

        jLabel82.setText("Hasta");
        jLabel82.setBackground(new java.awt.Color(51, 51, 51));
        jLabel82.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(51, 51, 51));
        jLabel82.setPreferredSize(new java.awt.Dimension(30, 10));
        pEstados9.add(jLabel82, java.awt.BorderLayout.NORTH);

        jPanel17.setMinimumSize(new java.awt.Dimension(10, 30));
        jPanel17.setPreferredSize(new java.awt.Dimension(30, 25));
        pEstados9.add(jPanel17, java.awt.BorderLayout.PAGE_END);

        jPanel3.add(pEstados9);

        bContenedores.setText("Reporte");
        bContenedores.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bContenedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bContenedoresActionPerformed(evt);
            }
        });
        jPanel3.add(bContenedores);

        bContenedores1.setText("Reporte Cliente");
        bContenedores1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bContenedores1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bContenedores1ActionPerformed(evt);
            }
        });
        jPanel3.add(bContenedores1);

        bContenedores3.setText("Eliminar");
        bContenedores3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bContenedores3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bContenedores3ActionPerformed(evt);
            }
        });
        jPanel3.add(bContenedores3);

        jButton1.setText("Inventario");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1);

        jCheckBox3.setText("Botando");
        jCheckBox3.setOpaque(false);
        jCheckBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox3ItemStateChanged(evt);
            }
        });
        jPanel3.add(jCheckBox3);

        FilterFull.setText("Full");
        FilterFull.setOpaque(false);
        FilterFull.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                FilterFullItemStateChanged(evt);
            }
        });
        jPanel3.add(FilterFull);

        schasis.setText("Solo Chasis");
        schasis.setOpaque(false);
        schasis.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                schasisItemStateChanged(evt);
            }
        });
        jPanel3.add(schasis);

        jPanel2.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel5.setBackground(global.headerscolor);
        jPanel5.setLayout(new java.awt.BorderLayout());

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setPreferredSize(new java.awt.Dimension(0, 100));
        jPanel5.add(jLabel8, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel5, java.awt.BorderLayout.NORTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed

    }//GEN-LAST:event_jTable1KeyPressed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int count = jTable1.getRowCount();
        int row = jTable1.getSelectedRow();
        int rows[] = jTable1.getSelectedRows();
        int col = jTable1.getSelectedColumn();

        if (row >= 0) {
            if (evt.getButton() == 3) {
                jPopupMenu1.show(jTable1, evt.getX(), evt.getY());
            }
            //cargarRutas(clientesel.get(row));

            String superUser = utils.dbConsult("select ifnull(superUser,'') from usuarios_tbl where usuarioID='" + global.usuario + "'");

            if (col == 17) {
                if (evt.getClickCount() == 2 && !superUser.equals("")) {

                    int r = jTable1.getSelectedRow();

                    if (jTable1.getSelectedRow() >= 0) {

                        String PlzRespond
                                = (String) JOptionPane.showInputDialog(null, "", "Ingrese el nuevo folio a ligar: ", JOptionPane.QUESTION_MESSAGE, null, null, "");

                        if (PlzRespond != null) {

                            try {
                                String consult = "";
                                consult = utils.dbConsult("select ifnull(inventarioID,'') from inventariosexternos_Tbl where inventarioID='" + PlzRespond + "'");

                                if (jTable1.getValueAt(row, 1).equals("Out")) {
                                    if (!consult.equals("")) {
                                        utils.dbUpdate("Update inventarioexterno_Tbl set anteriorID='" + PlzRespond + "' where inventarioID='" + inventarioID.get(r) + "'");
                                    }
                                }

                                if (jTable1.getValueAt(row, 1).equals("In")) {
                                    if (!consult.equals("")) {
                                        utils.dbUpdate("Update inventarioexterno_Tbl set anteriorID='" + PlzRespond + "' where inventarioID='" + jTable1.getValueAt(row, 17).toString() + "'");
                                    }
                                }

                            } catch (Exception e) {
                                System.out.println("TRYYY" + e);
                            }
                            cargarTabla();
                        }
                    }

                    //JOptionPane.showMessageDialog(this, "ChoferID = '" + model1.getValueAt(row, t1choferid) + "', ItinerarioID = '" + model1.getValueAt(row, t1itiid) + "', RutaID = '" + model1.getValueAt(row, t1rutaid) + "', WcontID = '" + model1.getValueAt(row, t1wcontid) + "'", "Mensaje", JOptionPane.ERROR_MESSAGE);
                }
            }

        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void tEnsenadaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tEnsenadaKeyPressed
        /*int row4 = jTable4.getSelectedRow();
         int row = jTable1.getSelectedRow();
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
         if (row4 >= 0) {
         //if (jTable4.getValueAt(row4, 1).equals("Disponible")) {
         cont = 1;
         jTable1.setValueAt(jTable4.getValueAt(row4, 0), row, 5);
         if (model1.getValueAt(row, t1itiid).equals("0")) {
         String query = "SELECT CamionID,(SELECT CajaID from camiones_tbl where camiones_tbl.CamionID = choferes_tbl.CamionID) from choferes_tbl where ChoferID = '" + tablachoferesid.get(row4) + "' and Status = true";
         savechoferes.set(row, tablachoferesid.get(row4));
         Connection con = utils.startConnection();
         try {
         Statement statement = con.createStatement();
         ResultSet rs = statement.executeQuery(query);
         while (rs.next()) {
         cont = 1;
         jTable1.setValueAt(utils.dbConsult("SELECT NoEconomico from camiones_tbl where CamionID = '" + rs.getString(1) + "'"), row, 6);
         savecamiones.set(row, rs.getString(1));
         cont = 1;
         jTable1.setValueAt(utils.dbConsult("SELECT NoEconomico from cajas_tbl where CajaID = '" + rs.getString(2) + "'"), row, 7);
         savecajas.set(row, rs.getString(2));
         }
         con.close();
         } catch (SQLException a) {
         System.out.println("Error " + a);
         }
         } else {
         utils.dbUpdate("UPDATE itinerarios_tbl SET ChoferID = '" + tablachoferesid.get(row4) + "' where ItinerarioID = '" + model1.getValueAt(row, t1itiid) + "'");
         savechoferes.set(row, tablachoferesid.get(row4));
         }
         jDialog3.dispose();
         //} else {
         //    JOptionPane.showMessageDialog(this, "Este chofer no esta disponible", "Sin Cambio", JOptionPane.WARNING_MESSAGE);
         //}
         }
         }*/
    }//GEN-LAST:event_tEnsenadaKeyPressed

    private void dRolChoferesWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_dRolChoferesWindowLostFocus
        dRolChoferes.dispose();
    }//GEN-LAST:event_dRolChoferesWindowLostFocus

    private void tEnsenadaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tEnsenadaMouseClicked
        /*int row = jTable1.getSelectedRow();
         int row4 = jTable4.getSelectedRow();
         if (row4 >= 0) {
         if (jTable4.getValueAt(row4, 1).toString().equalsIgnoreCase("ocupado")) {
         String value = jTable1.getValueAt(row, 1).toString();
         cargarChoques(tablachoferesid.get(row4), utils.dateFromFieldtoDBwoH(value) + " " + jTable1.getValueAt(row, 2), model1.getValueAt(row, t1rutaid));
         } else {
         tablemodel5.setRowCount(0);
         }
        
         if (evt.getClickCount() == 2) {
         cont = 1;
         jTable1.setValueAt(jTable4.getValueAt(row4, 0), row, 5);
         if (model1.getValueAt(row, t1itiid).equals("0")) {
         String query = "SELECT CamionID,(SELECT CajaID from camiones_tbl where camiones_tbl.CamionID = choferes_tbl.CamionID) from choferes_tbl where ChoferID = '" + tablachoferesid.get(row4) + "' and Status = true";
         savechoferes.set(row, tablachoferesid.get(row4));
         Connection con = utils.startConnection();
         try {
         Statement statement = con.createStatement();
         ResultSet rs = statement.executeQuery(query);
         while (rs.next()) {
         cont = 1;
         jTable1.setValueAt(utils.dbConsult("SELECT NoEconomico from camiones_tbl where CamionID = '" + rs.getString(1) + "'"), row, 6);
         savecamiones.set(row, rs.getString(1));
         cont = 1;
         jTable1.setValueAt(utils.dbConsult("SELECT NoEconomico from cajas_tbl where CajaID = '" + rs.getString(2) + "'"), row, 7);
         savecajas.set(row, rs.getString(2));
         }
         con.close();
         } catch (SQLException a) {
         System.out.println("Error " + a);
         }
         } else {
         utils.dbUpdate("UPDATE itinerarios_tbl SET ChoferID = '" + tablachoferesid.get(row4) + "' where ItinerarioID = '" + model1.getValueAt(row, t1itiid) + "'");
         savechoferes.set(row, tablachoferesid.get(row4));
         }
         jDialog3.dispose();
        
         }
        
         }*/
    }//GEN-LAST:event_tEnsenadaMouseClicked

    private void txtBusquedaChoferKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaChoferKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int row = jTable1.getSelectedRow();

        }
    }//GEN-LAST:event_txtBusquedaChoferKeyPressed

    private void tCargosClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tCargosClienteKeyPressed
        int row = tCargosCliente.getSelectedRow();
        int count = tCargosCliente.getRowCount();
        if (row >= 0) {
            if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
                utils.dbUpdate("UPDATE cargosclientes_tbl SET Status = false where CargoID = '" + cargosid.get(row) + "'");
                mcargoscliente.removeRow(row);
                cargosid.remove(row);
            }
        }
    }//GEN-LAST:event_tCargosClienteKeyPressed

    private void bActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bActualizarActionPerformed
        cargarTabla();
    }//GEN-LAST:event_bActualizarActionPerformed

    private boolean revisarSecuenciaFechas(String appdate, String usarr, String xtomx, String delivery, String empty, String puc, String xtous, String inport) {
        boolean secuencia = true;
        String temp[] = new String[]{appdate, usarr, xtomx, delivery, empty, puc, xtous, inport};
        int i = temp.length - 1, comprobando = 0;//comprobado 0 =

        while (i >= 0 && secuencia) {
            if (temp[i] == null) {
                if (comprobando == 1) {
                    secuencia = false;
                }
            } else {
                comprobando = 1;
            }
            i--;
        }

        return secuencia;

    }

    private String validarPeso(String contenedorid, String itinerarioid, int vuelta, String peso, int rowcont) {
        String pesoicont = "0";
        float pesof = Float.parseFloat(utils.dbConsult("SELECT digits('" + peso + "')"));
        int itiint = Integer.parseInt(itinerarioid);
        if (!itinerarioid.equals("0")) {// cuando aun no se guarda el viaje este es -1
            if (contenedorid.equals("0")) {
                pesoicont = utils.dbConsult("SELECT IFNULL((SELECT SUM(Peso) from icont_tbl where ItinerarioID = '" + itinerarioid + "' and Status = true and Vuelta = '" + vuelta + "'),0)+digits('" + peso + "') ");
            } else {
                pesoicont = utils.dbConsult("SELECT IFNULL((SELECT SUM(Peso) from icont_tbl where ItinerarioID = '" + itinerarioid + "' and Status = true and Vuelta = '" + vuelta + "' and IcontID != '" + contenedorid + "'),0)+digits('" + peso + "')");
            }
        } else {
            pesoicont = pesof + "";
            if (rowcont < 0) {
                for (int i = 0; i < notsaved.size(); i++) {
                    if (notsaved.get(i).getVuelta() == vuelta) {
                        pesoicont = (Float.parseFloat(notsaved.get(i).getPeso()) + pesof) + "";
                    }
                }
            } else {
                for (int i = 0; i < notsaved.size(); i++) {
                    if (notsaved.get(i).getVuelta() == vuelta && i != rowcont) {
                        pesoicont = (Float.parseFloat(notsaved.get(i).getPeso()) + pesof) + "";
                    }
                }
            }
        }

        return pesoicont;
    }

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

        dDetalles.dispose();
        dRolChoferes.dispose();
    }//GEN-LAST:event_formWindowClosing

    private void boxOrdenChoferesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxOrdenChoferesActionPerformed
        int row = jTable1.getSelectedRow();
        if (row >= 0) {
            //String value = jTable1.getValueAt(row, 1).toString();
        }
    }//GEN-LAST:event_boxOrdenChoferesActionPerformed

    private void esSantalucia(String iti) {
        String query = "SELECT IcontID from icont_tbl where Status = true and ItinerarioID = '" + iti + "'";
        Connection con = utils.startConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                cargarEvidencias(iti, rs.getString(1));
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }

    }

    private void cargarEvidencias(String itinerario, String icont) {
        String query = "SELECT EvidenciaID from evidencias_tbl where Status = true";
        Connection con = utils.startConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            int i = 1;
            while (rs.next()) {
                utils.dbInsert("INSERT INTO evidenciasiti_tbl(EvidenciaID,ItinerarioID,IContID,Correcto) "
                        + "VALUES('" + rs.getString(1) + "','" + itinerario + "','" + icont + "',false)");
            }

            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    private void validarReporte() {
        txtBusquedaReporte.setVisible(false);
        txtFechaini.setVisible(false);
        txtFechafin.setVisible(false);
        boxClienteRep.setVisible(false);
        boxRutasRep.setVisible(false);

        if (boxTipoReporte.getSelectedIndex() == 0) {
            txtFechaini.setVisible(true);
            txtFechafin.setVisible(true);
            boxClienteRep.setVisible(true);
        }

        if (boxTipoReporte.getSelectedIndex() == 1) {
            txtFechaini.setVisible(true);
            txtFechafin.setVisible(true);
            boxClienteRep.setVisible(true);
        }

        if (boxTipoReporte.getSelectedIndex() == 2) {
            boxClienteRep.setVisible(true);
        }

        if (boxTipoReporte.getSelectedIndex() == 3) {
            boxClienteRep.setVisible(true);
            txtFechaini.setVisible(true);
            txtFechafin.setVisible(true);
        }

    }

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed

    }//GEN-LAST:event_jButton13ActionPerformed

    private void txtBusquedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarTabla();
        }
    }//GEN-LAST:event_txtBusquedaKeyPressed

    private void boxTipoReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxTipoReporteActionPerformed
        validarReporte();

    }//GEN-LAST:event_boxTipoReporteActionPerformed

    private void txtBusquedaReporteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBusquedaReporteFocusGained
        if (txtBusquedaReporte.getText().equalsIgnoreCase("Contenedor") || txtBusquedaReporte.getText().equalsIgnoreCase("TipoCambio")) {
            txtBusquedaReporte.setText("");
        }
    }//GEN-LAST:event_txtBusquedaReporteFocusGained

    private void txtBusquedaReporteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBusquedaReporteFocusLost
        if (txtBusquedaReporte.getText().equalsIgnoreCase("")) {
            if (boxTipoReporte.getSelectedIndex() == 4) {
                txtBusquedaReporte.setText("Contenedor");
            }
            if (boxTipoReporte.getSelectedIndex() == 6) {
                txtBusquedaReporte.setText("TipoCambio");
            }
        }
    }//GEN-LAST:event_txtBusquedaReporteFocusLost

    private void mPrecioRutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mPrecioRutaActionPerformed
        if (global.nivel == 1) {

        }
    }//GEN-LAST:event_mPrecioRutaActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        //bodymailpay.replace("{{textousuario}}", areaBody1.getText().replace("\n", "<br>"))
        String mailstatus = SendEmail.normalEmail(txtTo1.getText(), justaddedpaths, areaBody1.getText().replace("\n", "<br>"), txtSubject1.getText(), 1, new ArrayList<String[]>());
        //dialogSelect.setEnabled(true);
        if (!mailstatus.isEmpty()) {
            JOptionPane.showMessageDialog(dEnvioCorreo, mailstatus, "Error", JOptionPane.ERROR_MESSAGE);
        }
        dEnvioCorreo.dispose();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void tTijuanaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tTijuanaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tTijuanaMouseClicked

    private void tTijuanaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tTijuanaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tTijuanaKeyPressed

    private void tMexicaliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tMexicaliMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tMexicaliMouseClicked

    private void tMexicaliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tMexicaliKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tMexicaliKeyPressed

    private void boxClienteRepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxClienteRepActionPerformed
        if (boxClienteRep.getSelectedIndex() >= 0 && doneload) {
            cargarRutasRep(clientesrepid.get(boxClienteRep.getSelectedIndex()));
        }
    }//GEN-LAST:event_boxClienteRepActionPerformed

    private void mRevisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mRevisarActionPerformed
        txtRevision.setText(utils.dbConsult("SELECT Justificacion from cargosclientes_tbl where CargoID = '" + cargosid.get(tCargosCliente.getSelectedRow()) + "'"));
        dRevision.setLocationRelativeTo(dDetalles);
        dRevision.setVisible(true);
    }//GEN-LAST:event_mRevisarActionPerformed

    private void mEntradaSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mEntradaSalidaActionPerformed
        int row = jTable1.getSelectedRow();
        if (row >= 0) {
            resetter();
            invid = inventarioID.get(jTable1.getSelectedRow());
            SelectedInventarioID = inventarioID.get(row);
            cargarEntradaSalida();
            dEntradaSalida.setLocationRelativeTo(this);
            dEntradaSalida.setVisible(true);

        }
    }//GEN-LAST:event_mEntradaSalidaActionPerformed
private void prt(String ca)
{
    System.out.println(ca);
}
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        String checkEntrada = "", checkSalida = "", patio_usuario = "", super_usuario = "", multipatio = "", Full = "No",
                checkEntradaCamion = "", checkEntradaChassis = "", checkSalidaCamion = "", checkSalidaChasis = "";

        int checkDriverMove = 0;

        checkEntrada = utils.dbConsult("Select (select nombre from patios_tbl where patioID=inventarioexterno_tbl.patioID ) "
                + "from inventarioexterno_tbl where contenedor='" + txtContenedor.getText() + "' "
                + "and (select anteriorID from inventarioexterno_tbl as w where anteriorID=inventarioexterno_tbl.InventarioID limit 1) is null and TipoEvento=1 "
                + "&& inventarioID!='" + invid + "' && (select vt from patios_tbl where patioID=inventarioexterno_tbl.patioID) is not true ");

        checkEntradaCamion = utils.dbConsult("Select (select nombre from patios_tbl where patioID=inventarioexterno_tbl.patioID ) "
                + "from inventarioexterno_tbl where camionID='" + camionidx.get(camionx.getSelectedIndex()) + "' "
                + "and (select anteriorID from inventarioexterno_tbl as w where anteriorID=inventarioexterno_tbl.InventarioID limit 1) is null and TipoEvento=1 && inventarioID!='" + invid + "' ");

        checkEntradaChassis = utils.dbConsult("Select (select nombre from patios_tbl where patioID=inventarioexterno_tbl.patioID ) "
                + "from inventarioexterno_tbl where chasis='" + cajachasisboxExterno.getText() + "' "
                + "and (select anteriorID from inventarioexterno_tbl as w where anteriorID=inventarioexterno_tbl.InventarioID limit 1) is null and TipoEvento=1 && inventarioID!='" + invid + "' ");

        checkSalida = utils.dbConsult("Select inventarioID from inventarioexterno_tbl where contenedor='" + txtContenedor.getText() + "' "
                + "and (select anteriorID from inventarioexterno_tbl as w where anteriorID=inventarioexterno_tbl.InventarioID limit 1) is null and TipoEvento=1 ");

        checkSalidaCamion = utils.dbConsult("Select inventarioID from inventarioexterno_tbl where camionID='" + camionidx.get(camionx.getSelectedIndex()) + "' "
                + "and (select anteriorID from inventarioexterno_tbl as w where anteriorID=inventarioexterno_tbl.InventarioID limit 1) is null and TipoEvento=1 ");

        checkSalidaChasis = utils.dbConsult("Select inventarioID from inventarioexterno_tbl where chasis='" + cajachasisboxExterno.getText() + "' "
                + "and (select anteriorID from inventarioexterno_tbl as w where anteriorID=inventarioexterno_tbl.InventarioID limit 1) is null and TipoEvento=1 ");

        // Revisa si ya se inserto algun registro con el driver move, ignorando el 0, ya que los 0 son externos
        checkDriverMove = Integer.parseInt(utils.dbConsult("SELECT IFNULL((Select itinerarioID from inventarioexterno_tbl where itinerarioID='" + txtDriverMove.getText() + "' limit 1),0) "));

        patio_usuario = utils.dbConsult("select patioID from usuarios_Tbl where usuarioid='" + global.usuario + "'");
        super_usuario = utils.dbConsult("select IFNULL(superuser,0) from usuarios_Tbl where usuarioID='" + global.usuario + "'");
        multipatio = utils.dbConsult("select IFNULL(multiyarda,0) from usuarios_Tbl where usuarioID='" + global.usuario + "'");

        System.out.println("usuarioOn:" + patio_usuario + " ID:" + PatioID.get(Patio.getSelectedIndex()));

        String statx = utils.dbConsult("SELECT estatus_caja from cajas_tbl where noeconomico = '" + txtContenedor.getText() + "' limit 1");

        Full = utils.dbConsult("select IF(count(itinerarioID)>1,'Si','No') from icont_tbl where ItinerarioID='" + ItiID + "' and estado is true");

        int opt = 0;

        if (checkDriverMove > 0) { // revision de driver move repetido, solo entrara aqui si lo esta y preguntara si de igual manera se quiera insertar el registro avisando
            opt = JOptionPane.showConfirmDialog(this, "Este driver move ya fue ingresado, desea agregar el nuevo registro de igual manera?", "Driver move repetido", JOptionPane.YES_NO_OPTION);
        }

        prt("opc: " + opt + " YES: " + JOptionPane.YES_OPTION);
        if (opt == JOptionPane.YES_OPTION) {
            prt("Camion: "+camionx.getSelectedIndex() + " TractoStatus: "+tractorInventario);
            if (!txtContenedor.getText().equals("") || (camionx.getSelectedIndex() >=0 && tractorInventario)){ 
                
                prt("SALIDA/ENTRADA: "+ InOut.getSelectedItem().toString() + "CAM SAL: "+  checkSalidaCamion);
                if (patio_usuario.equals(PatioID.get(Patio.getSelectedIndex())) || super_usuario.equals("1") || multipatio.equals("1")) {

                    if ((InOut.getSelectedItem().toString().equals("Entrada") && !checkEntrada.equals("") && !tractorInventario) || (InOut.getSelectedItem().toString().equals("Entrada")  && !checkEntradaCamion.equals("") && tractorInventario) /*&& !checkEntradaCamion.equals("") && !checkEntradaChassis.equals("")*/) {

                        prt("CHECKCAM ENTRADA: "+ checkEntradaCamion);
                         prt("CHECKCAJA ENTRADA: "+ checkEntrada);
                        if ((!checkEntrada.equals("") && !tractorInventario) || (!checkEntradaCamion.equals("") && tractorInventario)) {
                            JOptionPane.showMessageDialog(dEntradaSalida, "El contenedor o tractor se encuentra en el patio " + (tractorInventario ?  checkEntrada : checkEntrada) + "./nFavor de darle salida primero.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else if ((InOut.getSelectedItem().toString().equals("Salida") && checkSalida.equals("") && !tractorInventario) || (InOut.getSelectedItem().toString().equals("Salida") && checkSalidaCamion.equals("") && tractorInventario) /*&& !checkSalidaCamion.equals("") && !checkSalidaChasis.equals("")*/) {

                        prt("SALIDA? " + InOut.getSelectedItem().toString() + "CAM SAL?: "+ checkSalidaCamion);
                        if ((checkSalida.equals("") && tractorInventario == false) || (checkSalidaCamion.equals("") && tractorInventario)) {
                            JOptionPane.showMessageDialog(dEntradaSalida, "El contenedor no se encuentra en el patio, favor de darle entrada primero.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        prt("Entre aqui.");
                        if (estx.getSelectedIndex() == -1) {
                            estx.setSelectedIndex(0);
                        }

                        String TempAnteriorID = "";

                        if (cliente1.getSelectedIndex() == -1) {
                            cliente1.setSelectedIndex(0);
                        }

                        if ((!txtContenedor.getText().equals("") && !tractorInventario) || (camionx.getSelectedIndex() >=0 && tractorInventario)) { //&& Orgn.getSelectedIndex()>0 && !placasunidad.getText().equals("") && !NCHASIS.getText().equals("") && !txtNota.equals("")

                            if (boxEstadoCarga.getSelectedIndex() == 1 && txtSello.getText().equals("")) {
                                JOptionPane.showMessageDialog(dEntradaSalida, "Si tiene carga debe introducir el sello para poder continuar.", "Error", JOptionPane.ERROR_MESSAGE);
                            } else {
                                try {
                                    String fsalida = null, fllegada = null, usuariosalida = "0", usuariollegada = "0", patiousuario = utils.dbConsult("SELECT PatioID from usuarios_tbl where UsuarioID = '" + global.usuario + "'");
                                    boolean newllegada = false;
                                    if (utils.validarDATE(txtFLlegada2.getText()) && utils.dbConsult("SELECT digits('" + txtHLlegada.getText() + "')").length() == 4) {
                                        fllegada = "'" + utils.dateFromFieldtoDBwoH(txtFLlegada2.getText()) + " " + txtHLlegada.getText() + ":00'";
                                        newllegada = true;
                                        usuariollegada = global.usuario + "";
                                    }
                                    prt("1. invid: "+ invid);
                                    if (invid.equals("0")) {

                                        String virtual = "", vt = "";
                                        virtual = utils.dbConsult("select ifnull(vt,false) from patios_tbl where  patioID='" + PatioID.get(Patio.getSelectedIndex()) + "'");

                                        if (virtual.equals("1")) {
                                            vt = " and patioID='" + PatioID.get(Patio.getSelectedIndex()) + "' ";
                                        } else {
                                            vt = " and patioID='" + PatioID.get(Patio.getSelectedIndex()) + "' ";
                                        }

                                        String cont = utils.dbConsult("SELECT IFNULL((SELECT IF( '" + InOut.getSelectedItem().toString() + "'='Entrada', IF(AnteriorID=0,InventarioID,0), IF(AnteriorID=0,0,InventarioID)  ) "
                                                + "from inventarioexterno_tbl where "+(tractorInventario ? (" camionId = " + camionidx.get(camionx.getSelectedIndex())) : " Contenedor = RTRIM('" + txtContenedor.getText() + "')")+" and Status = 1 " + vt + " ),0)");
                                      
                                        if (cont.equals("0")) {

                                            String salidaDebeTenerEntrada = "";
                                            
                                            prt("2... Cam:" + camionx.getSelectedIndex() + " tracto: " + tractorInventario);
                                            if(((camionx.getSelectedIndex() >=0 && tractorInventario))){
                                              salidaDebeTenerEntrada = utils.dbConsult("SELECT IFNULL((SELECT inventarioID "
                                                    + "from inventarioexterno_tbl as i where camionID = "+camionidx.get(camionx.getSelectedIndex())+" and Status = 1 and tipoevento=1 and patioID='" + PatioID.get(Patio.getSelectedIndex()) + "' "
                                                    + "and (select inventarioexterno_tbl.inventarioID from inventarioexterno_tbl where inventarioexterno_tbl.anteriorID=i.`InventarioID`) is null "
                                                    + "order by inventarioID desc limit 1 ),0)");
                                            }else{
                                            salidaDebeTenerEntrada = utils.dbConsult("SELECT IFNULL((SELECT inventarioID "
                                                    + "from inventarioexterno_tbl as i where Contenedor = RTRIM('" + txtContenedor.getText() + "') and Status = 1 and tipoevento=1 and patioID='" + PatioID.get(Patio.getSelectedIndex()) + "' "
                                                    + "and (select inventarioexterno_tbl.inventarioID from inventarioexterno_tbl where inventarioexterno_tbl.anteriorID=i.`InventarioID`) is null "
                                                    + "order by inventarioID desc limit 1 ),0)");    
                                            }
                                           

                                            
                                            if (!salidaDebeTenerEntrada.equals("0") || InOut.getSelectedItem().equals("Entrada")) {

                                                TempAnteriorID = utils.dbConsult(" select inventarioID "
                                                        + "from inventarioexterno_tbl as i where "+(tractorInventario ? (" camionId = " + camionidx.get(camionx.getSelectedIndex())) : " Contenedor = ('" + txtContenedor.getText() + "')")+" and TipoEvento=1 "
                                                        + "and (select inventarioexterno_tbl.inventarioID from inventarioexterno_tbl where inventarioexterno_tbl.anteriorID=i.`InventarioID`) is null "
                                                        + " " + vt + " order by inventarioID desc limit 1 "); 

                                                
                                                if (TempAnteriorID.equals("") ) {
                                                    TempAnteriorID = "0";
                                                }

                                                String fullz = "0", fulliti = "0";
                                                if (capturaFull == true) {
                                                    fullz = "1";
                                                    fulliti = basefull;
                                                } else {
                                                    fullz = "0";
                                                    fulliti = "0";
                                                }

                                                String id = utils.dbInsert("INSERT INTO inventarioexterno_tbl (Contenedor, PlacasChasis,  Sello, PaisID, EstadoID, EstadoUSAID,ChoferID, NombreChofer, FechaEvento, " //PlacasChasisUSA,
                                                        + "UsuarioEventoID, Nota, EstadoCarga, UsuarioID, Fecha, Tamano,clienteID, WContenedorID, TipoEvento, AnteriorID, camion, EquipmentProvider, Origen, camionID, botando,"
                                                        + "PlacasUnidad, PlacasUnidadUSA, Destino, NumeroChasis, Carrier, patioID, grade, assignedto, fechaedicion, isCamion, perfilCobroID, isVirtual, itinerarioID, EstadoPlacasMex, EstadoPlacasUsa,"
                                                        + "remolquePais, remolqueEstado, solochasis,CreacionUsuarioEventoID, licencia, cajaID, chasis, isFull, FullItinerarioBase, sellocomplementario) "
                                                        + "VALUES(RTRIM('" + txtContenedor.getText() + "'), '" + txtPlacasChasis.getText() + "',  '" + txtSello.getText() + "', " //'" + txtPlacasChasis1.getText() + "',
                                                        + "'" + (boxPaisPlacas.getSelectedIndex() + 1) + "', '" + estadosid.get(boxEstadoPlacas.getSelectedIndex()) + "', '" + estadosid1.get(boxEstadoPlacas1.getSelectedIndex()) + "' "
                                                        + ",'" + chofereslocalid.get(boxChofer.getSelectedIndex()) + "', '" + txtNombreChofer.getText() + "', " + fllegada + ", "
                                                        + "'" + usuariollegada + "', '" + txtNota.getText() + "', '" + boxEstadoCarga.getSelectedIndex() + "', '" + global.usuario + "'"
                                                        + ", (now()),  '" + boxTamano.getSelectedItem() + "','" + clienteid.get(cliente.getSelectedIndex()) + "', '" + WContID + "', "
                                                        + "IF('" + InOut.getSelectedItem().toString() + "'='Entrada',1,2), IF('" + InOut.getSelectedItem().toString() + "'='Entrada',0,'" + TempAnteriorID + "'), "
                                                        + "'" + camiones.getText() + "', '" + cliente1id.get(cliente1.getSelectedIndex()) + "', '" + Orgn.getSelectedItem().toString() + "', '" + camionidx.get(camionx.getSelectedIndex()) + "', "
                                                        + "" + botando.isSelected() + ", '" + placasunidad.getText() + "', '" + placasunidad1.getText() + "', '" + destino.getText() + "', '" + NCHASIS.getText() + "', '" + CARRIER.getText() + "', "
                                                        + "IF('" + PatioID.get(Patio.getSelectedIndex()) + "'+0>0,'" + PatioID.get(Patio.getSelectedIndex()) + "',0), '" + grade.getSelectedItem().toString() + "', "
                                                        + "'" + assignedto.getText() + "', now(), " + Camion.isSelected() + ", '" + perfilBuscador1ID.get(perfilBuscador1.getSelectedIndex()) + "', "
                                                        + "IF('" + utils.dbConsult("select ifnull(vt,false) from patios_tbl where  patioID='" + patiosid.get(BoxPatios.getSelectedIndex()) + "'") + "'='1',true,false ), "
                                                        + "IFNULL('" + ItiID + "'+0,0), '" + placasunidad2.getText() + "', '" + placasunidad3.getText() + "', "
                                                        + "'" + paisx.getSelectedItem().toString() + "','" + estxid.get(estx.getSelectedIndex()) + "', "
                                                        + "" + solochasis.isSelected() + ", '" + global.usuario + "', '" + licencia.getText() + "', "
                                                        + "'" + cajachasisboxID.get(cajachasisbox.getSelectedIndex()) + "', '" + cajachasisboxExterno.getText() + "', '" + fullz + "', "
                                                        + "'" + fulliti + "', '" + sellocomplementario.getText() + "' )");

                                                SelectedInventarioID = id;

                                                if (capturaFull == true) {
                                                    utils.dbUpdate("UPDATE inventarioexterno_tbl set isFull=1 where InventarioID = '" + basefull + "' ");
                                                }
                                                capturaFull = false;

                                               if (id.length() <= 11) {
                                                    cargarTabla();
                                                    dEntradaSalida.dispose();
                                                    tractorInventario = false;
                                                } else {
                                                    JOptionPane.showMessageDialog(dEntradaSalida, id, "Error", JOptionPane.ERROR_MESSAGE);
                                                }

                                            } else {
                                                JOptionPane.showMessageDialog(dEntradaSalida, "La salida que quiere registrar no tiene una entrada, realice la entrada primero.", "Error", JOptionPane.ERROR_MESSAGE);
                                            }

                                        } else {
                                            String locate = utils.dbConsult("select concat('El contenedor " + txtContenedor.getText() + " ya tiene una entrada en la yarda ',nombre,'.') from patios_tbl where patioID=(select patioID from inventarioexterno_tbl where inventarioID='" + cont + "')");
                                            JOptionPane.showMessageDialog(dEntradaSalida, "Este contenedor ya existe en el inventario favor de revisar su listado.\n" + locate, "Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                    } else {

                                        String cont = invid;

                                        String cot2 = utils.dbConsult("SELECT IFNULL((SELECT IF( '" + InOut.getSelectedItem().toString() + "'='Entrada', IF(AnteriorID=0,InventarioID,0), IF(AnteriorID=0,0,InventarioID)  ) "
                                                + "from inventarioexterno_tbl where "+(tractorInventario ? (" camionId = " + camionidx.get(camionx.getSelectedIndex())) : " Contenedor = ('" + txtContenedor.getText() + "')")+" and Status = 1 and inventarioID!='" + invid + "' "
                                                + " and patioID='" + PatioID.get(Patio.getSelectedIndex()) + "' " //
                                                + "order by inventarioID desc limit 1 "
                                                //+ "TipoEvento=IF('"+InOut.getSelectedItem().toString()+"'='Entrada',1,2) and "
                                                + " ),0)");

                                        if (cot2.equals("0")) {

                                            if (!cont.equals("0")) {
                                                String up = utils.dbUpdate("UPDATE inventarioexterno_tbl SET "
                                                        + "Contenedor = RTRIM('" + txtContenedor.getText() + "'), " //PlacasChasisUSA = '" + txtPlacasChasis1.getText() + "', 
                                                        + "PlacasChasis = '" + txtPlacasChasis.getText() + "', Sello = '" + txtSello.getText() + "', PaisID = '" + (boxPaisPlacas.getSelectedIndex() + 1) + "', "
                                                        + "EstadoID = '" + estadosid.get(boxEstadoPlacas.getSelectedIndex()) + "', EstadoUSAID = '" + estadosid1.get(boxEstadoPlacas1.getSelectedIndex()) + "'"
                                                        + ", ChoferID = '" + chofereslocalid.get(boxChofer.getSelectedIndex()) + "', NombreChofer = '" + txtNombreChofer.getText() + "', Nota = '" + txtNota.getText() + "', EstadoCarga = '" + boxEstadoCarga.getSelectedIndex() + "'"
                                                        + ", Tamano = '" + boxTamano.getSelectedItem() + "', clienteID='" + clienteid.get(cliente.getSelectedIndex()) + "', WContenedorID='" + WContID + "', "
                                                        + "TipoEvento=IF('" + InOut.getSelectedItem().toString() + "'='Entrada',1,2),  " //AnteriorID=IF('"+InOut.getSelectedItem().toString()+"'='Entrada',0,'"+AnteriorID+"'),
                                                        + " camion='" + camiones.getText() + "', EquipmentProvider='" + cliente1id.get(cliente1.getSelectedIndex()) + "', Origen='" + Orgn.getSelectedItem().toString() + "', "
                                                        + "camionID='" + camionidx.get(camionx.getSelectedIndex()) + "', botando=" + botando.isSelected() + ", PlacasUnidad='" + placasunidad.getText() + "', PlacasUnidadUSA='" + placasunidad1.getText() + "', Destino='" + destino.getText() + "', "
                                                        + "NumeroChasis='" + NCHASIS.getText() + "', Carrier='" + CARRIER.getText() + "', FechaEvento=" + fllegada + ", "
                                                        + "patioID=IF('" + PatioID.get(Patio.getSelectedIndex()) + "'+0>0,'" + PatioID.get(Patio.getSelectedIndex()) + "',0), grade='" + grade.getSelectedItem().toString() + "', "
                                                        + "assignedto='" + assignedto.getText() + "', UsuarioEventoID='" + usuariollegada + "', fechaedicion=now(), isCamion=" + Camion.isSelected() + ", "
                                                        + "perfilCobroID='" + perfilBuscador1ID.get(perfilBuscador1.getSelectedIndex()) + "',  "
                                                        + "isVirtual= IF('" + utils.dbConsult("select ifnull(vt,false) from patios_tbl where  patioID='" + patiosid.get(BoxPatios.getSelectedIndex()) + "'") + "'='1',true,false ), "
                                                        + "EstadoPlacasMex= '" + placasunidad2.getText() + "', EstadoPlacasUsa='" + placasunidad3.getText() + "', "
                                                        + "remolquePais='" + paisx.getSelectedItem().toString() + "', remolqueEstado='" + estxid.get(estx.getSelectedIndex()) + "', solochasis=" + solochasis.isSelected() + ", "
                                                        + "licencia='" + licencia.getText() + "', cajaID='" + cajachasisboxID.get(cajachasisbox.getSelectedIndex()) + "', chasis='" + cajachasisboxExterno.getText() + "', "
                                                        + "sellocomplementario='" + sellocomplementario.getText() + "' "
                                                        + "where InventarioID = '" + invid + "' ");

                                                SelectedInventarioID = invid;

                                                if (up.isEmpty()) {
                                                    invid = "0";
                                                    cargarTabla();
                                                    dEntradaSalida.dispose();
                                                    tractorInventario = false;
                                                    
                                                } else {
                                                    JOptionPane.showMessageDialog(dEntradaSalida, up, "Error", JOptionPane.ERROR_MESSAGE);
                                                }
                                            }
                                        } else {
                                            String locate = utils.dbConsult("select concat('El contenedor " + txtContenedor.getText() + " ya tiene una entrada en la yarda ',nombre,'.') from patios_tbl where patioID=(select patioID from inventarioexterno_tbl where inventarioID='" + cont + "')");
                                            JOptionPane.showMessageDialog(dEntradaSalida, " existe en el inventario favor de revisar su listado.\n" + locate, "Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                    }

                                } catch (Exception e) {
                                    JOptionPane.showMessageDialog(dEntradaSalida, e, "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(dEntradaSalida, "Asegurese de haber capturado contenedor.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                } else {
                    JOptionPane.showMessageDialog(dEntradaSalida, "Usted no esta designado para realizar movimientos en este patio o tiene permisos de administracion, solicite los permisos si debe tenerlos.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(dEntradaSalida, "Asegurese de haber capturado el contenedor", "Error", JOptionPane.ERROR_MESSAGE); //Asegurese de haber capturado los campos obligatorios (Rojos).
            }

            String isFull = utils.dbConsult(" select IF(isFull is true,'Si','No') from inventarioexterno_tbl where InventarioID = '" + SelectedInventarioID + "' ");
            String FullRegistered = utils.dbConsult(" select IF(FullItinerarioBase>0,'Si','No') from inventarioexterno_tbl where InventarioID = '" + SelectedInventarioID + "' ");

            if (Full.equals("Si") && isFull.equals("No") && FullRegistered.equals("No")) { //

                int option = JOptionPane.showConfirmDialog(this, "Este registro es un full, desea capturar el segundo contenedor?", "Full", JOptionPane.YES_NO_OPTION);
                if (option == 0) {

                    String driver = txtDriverMove.getText();

                    resetter();
                    cargarPerfilesRegistro();
                    basefull = SelectedInventarioID;
                    SelectedInventarioID = "0";

                    loadFull(driver);

                    dEntradaSalida.setLocationRelativeTo(this);
                    dEntradaSalida.setVisible(true);

                }
            }

            //}
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void mExtraChoferesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mExtraChoferesActionPerformed


    }//GEN-LAST:event_mExtraChoferesActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed

    }//GEN-LAST:event_jButton8ActionPerformed

    private void txtTotalRegKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalRegKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalRegKeyPressed

    private void boxFiltroBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxFiltroBusquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boxFiltroBusquedaActionPerformed

    private void txtSeleccionadosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSeleccionadosKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSeleccionadosKeyPressed

    private void jTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseReleased
        txtSeleccionados.setText(jTable1.getSelectedRows().length + "");
    }//GEN-LAST:event_jTable1MouseReleased

    private void boxFiltroStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxFiltroStatusActionPerformed
        if (boxFiltroStatus.getSelectedIndex() >= 0) {

            cargarTabla();
        }
    }//GEN-LAST:event_boxFiltroStatusActionPerformed

    private void mCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mCancelar1ActionPerformed

    }//GEN-LAST:event_mCancelar1ActionPerformed

    private void mModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mModificarActionPerformed

    }//GEN-LAST:event_mModificarActionPerformed

    private void boxPaisPlacasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxPaisPlacasActionPerformed
        if (boxPaisPlacas.getSelectedIndex() >= 0) {
            FillCombo.cargarEstados(boxEstadoPlacas, estadosid, "", 1 + ""); //(boxPaisPlacas.getSelectedIndex() + 1)
        }
    }//GEN-LAST:event_boxPaisPlacasActionPerformed

    private void bLlegadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLlegadaActionPerformed
        String fecha = utils.dbConsult("SELECT DATE_FORMAT(now(), '" + global.fdatedb + " %H:%i')");
        txtFLlegada2.setText(fecha.split(" ")[0]);
        txtHLlegada.setText(fecha.split(" ")[1]);
    }//GEN-LAST:event_bLlegadaActionPerformed

    private void bEliminarLlegadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEliminarLlegadaActionPerformed
        txtFLlegada2.setText("");
        txtHLlegada.setText("  :  ");
    }//GEN-LAST:event_bEliminarLlegadaActionPerformed

    private void bNuevaEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNuevaEntradaActionPerformed
        resetter();
        cargarPerfilesRegistro();
        SelectedInventarioID = "0";
        //dEntradaSalida.setAlwaysOnTop(true);
        dEntradaSalida.setLocationRelativeTo(this);
        dEntradaSalida.setVisible(true);
    }//GEN-LAST:event_bNuevaEntradaActionPerformed

    private void txtContenedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContenedorKeyPressed

        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            bremolque.setText(utils.dbConsult("SELECT DATE_FORMAT(fecha,'" + global.fdatedb + "') from requimant_tbl where "
                    + "camionID='" + utils.dbConsult("select cajaID from cajas_tbl where noeconomico='" + txtPlacasChasis.getText() + "'") + "' and  rev90 is true "
                    + "and categoriaID order by RequisicionID desc limit 1"));
        }

        if (InOut.getSelectedItem().toString().equals("Entrada")) { // Se solicito que se inhabilitara 

            if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {

                if (!txtContenedor.getText().equals("")) {
                    WContID = "0";
                    AnteriorID = "0";
                    ItiID = "0";
                    tractorInventario = false;
                    cargarContenedoresFiltrados();

                    /*if(contenedor_tbl.getRowCount()>0) {
         ContenedorFiltro.setLocationRelativeTo(dEntradaSalida); 
         ContenedorFiltro.repaint();
         ContenedorFiltro.setVisible(true);
         }*/
                    //else {
                    //NCHASIS.setText(utils.dbConsult("select NumeroChasis from inventarioexterno_tbl where contenedor='"+txtContenedor.getText()+"' and NumeroChasis!='' and NumeroChasis is not null order by inventarioID desc limit 1"));
                    txtPlacasChasis.setText(utils.dbConsult("select PlacasChasis from inventarioexterno_tbl where contenedor='" + txtContenedor.getText() + "' and NumeroChasis!='' and NumeroChasis is not null order by inventarioID desc limit 1"));
                    //txtPlacasChasis1.setText(utils.dbConsult("select PlacasChasisUSA from inventarioexterno_tbl where contenedor='"+txtContenedor.getText()+"' and NumeroChasis!='' and NumeroChasis is not null order by inventarioID desc limit 1"));

                    try {
                        paisx.setSelectedItem(utils.dbConsult("select remolqueEstado from inventarioexterno_tbl where contenedor='" + txtContenedor.getText() + "' and NumeroChasis!='' and NumeroChasis is not null order by inventarioID desc limit 1"));
                        estx.setSelectedIndex(estxid.indexOf((utils.dbConsult("select remolqueEstado from inventarioexterno_tbl where contenedor='" + txtContenedor.getText() + "' and NumeroChasis!='' and NumeroChasis is not null order by inventarioID desc limit 1"))));
                    } catch (Exception e) {
                    }

                    grade.setSelectedItem(utils.dbConsult("select grade from inventarioexterno_tbl where contenedor='" + txtContenedor.getText() + "' and NumeroChasis!='' and NumeroChasis is not null order by inventarioID desc limit 1"));
                    boxTamano.setSelectedItem(utils.dbConsult("select tamano from inventarioexterno_tbl where contenedor='" + txtContenedor.getText() + "' and NumeroChasis!='' and NumeroChasis is not null order by inventarioID desc limit 1"));

                    String queryx = "select inventarioID,WContenedorID, contenedor,(select NComercial from clientes_tbl where clienteID=inventarioexterno_tbl.clienteID) as cliente, AnteriorID, "
                            + "IFNULL((select itinerarioID from itinerarios_tbl where WcontFK=inventarioexterno_tbl.WContenedorID and itinerarios_tbl.Status=1 order by itinerarios_tbl.ItinerarioID desc limit 1 ),'') as driverm "
                            + "from inventarioexterno_tbl where contenedor = '" + txtContenedor.getText() + "' and anteriorID=0  "
                            + "order by inventarioID desc limit 1";

                    System.out.println("query driver: " + queryx);
                    //}
                } else {
                    JOptionPane.showMessageDialog(dEntradaSalida, "No hay ningun contenedor.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if (InOut.getSelectedItem().toString().equals("Salida")) {

            if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {

                if (!txtContenedor.getText().equals("")) {
                    tractorInventario = false;
                    WContID = "0";
                    AnteriorID = "0";
                    InventarioSalidaID = "0";
                    ItiID = "0";
                    cargarEntradas();

                    if (contenedor_tbl.getRowCount() > 0) {
                        ContenedorFiltro.setLocationRelativeTo(dEntradaSalida);
                        ContenedorFiltro.repaint();
                        ContenedorFiltro.setVisible(true);
                    } else {
                        //NCHASIS.setText(utils.dbConsult("select NumeroChasis from inventarioexterno_tbl where contenedor='"+txtContenedor.getText()+"' and NumeroChasis!='' and NumeroChasis is not null order by inventarioID desc limit 1"));
                        txtPlacasChasis.setText(utils.dbConsult("select PlacasChasis from inventarioexterno_tbl where contenedor='" + txtContenedor.getText() + "' and NumeroChasis!='' and NumeroChasis is not null order by inventarioID desc limit 1"));
                        //txtPlacasChasis1.setText(utils.dbConsult("select PlacasChasisUSA from inventarioexterno_tbl where contenedor='"+txtContenedor.getText()+"' and NumeroChasis!='' and NumeroChasis is not null order by inventarioID desc limit 1"));

                        try {
                            paisx.setSelectedItem(utils.dbConsult("select remolqueEstado from inventarioexterno_tbl where contenedor='" + txtContenedor.getText() + "' and NumeroChasis!='' and NumeroChasis is not null order by inventarioID desc limit 1"));
                            estx.setSelectedIndex(estxid.indexOf((utils.dbConsult("select remolqueEstado from inventarioexterno_tbl where contenedor='" + txtContenedor.getText() + "' and NumeroChasis!='' and NumeroChasis is not null order by inventarioID desc limit 1"))));
                        } catch (Exception e) {
                        }

                        grade.setSelectedItem(utils.dbConsult("select grade from inventarioexterno_tbl where contenedor='" + txtContenedor.getText() + "' and NumeroChasis!='' and NumeroChasis is not null order by inventarioID desc limit 1"));
                        boxTamano.setSelectedItem(utils.dbConsult("select tamano from inventarioexterno_tbl where contenedor='" + txtContenedor.getText() + "' and NumeroChasis!='' and NumeroChasis is not null order by inventarioID desc limit 1"));

                        String queryx = "select inventarioID,WContenedorID, contenedor,(select NComercial from clientes_tbl where clienteID=inventarioexterno_tbl.clienteID) as cliente, AnteriorID, "
                                + "IFNULL((select itinerarioID from itinerarios_tbl where WcontFK=inventarioexterno_tbl.WContenedorID and itinerarios_tbl.Status=1 order by itinerarios_tbl.ItinerarioID desc limit 1 ),'') as driverm "
                                + "from inventarioexterno_tbl where contenedor = '" + txtContenedor.getText() + "' and anteriorID=0  "
                                + "order by inventarioID desc limit 1";

                        System.out.println("query driver: " + queryx);
                    }
                } else {
                    JOptionPane.showMessageDialog(dEntradaSalida, "No hay ningun contenedor.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }

        }

    }//GEN-LAST:event_txtContenedorKeyPressed

    private void anexo_add8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_anexo_add8MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_anexo_add8MouseEntered

    private void anexo_add8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_anexo_add8MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_anexo_add8MouseExited

    private void anexo_add8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_anexo_add8MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_anexo_add8MousePressed

    private void anexo_add8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anexo_add8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_anexo_add8ActionPerformed

    private void anexo_del8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_anexo_del8MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_anexo_del8MouseEntered

    private void anexo_del8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_anexo_del8MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_anexo_del8MouseExited

    private void anexo_del8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_anexo_del8MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_anexo_del8MousePressed

    private void anexo_del8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anexo_del8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_anexo_del8ActionPerformed

    private void plus12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plus12MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_plus12MouseEntered

    private void plus12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plus12MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_plus12MouseExited

    private void plus12MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plus12MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_plus12MousePressed

    private void plus12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plus12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_plus12ActionPerformed

    private void plus13MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plus13MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_plus13MouseEntered

    private void plus13MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plus13MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_plus13MouseExited

    private void plus13MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plus13MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_plus13MousePressed

    private void plus13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plus13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_plus13ActionPerformed

    private void contenedor_tblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contenedor_tblMouseClicked
        //cargarTickets();
    }//GEN-LAST:event_contenedor_tblMouseClicked

    private void Pagos14MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Pagos14MouseEntered

    }//GEN-LAST:event_Pagos14MouseEntered

    private void Pagos14MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Pagos14MouseExited

    }//GEN-LAST:event_Pagos14MouseExited

    private void Pagos14MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Pagos14MousePressed

    }//GEN-LAST:event_Pagos14MousePressed

    private void Pagos14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Pagos14ActionPerformed

        if (contenedor_tbl.getSelectedRow() >= 0) {

            /*if (InOut.getSelectedItem().toString().equals("Entrada")) {

                entradaext();
            }*/
            if (InOut.getSelectedItem().toString().equals("Salida")) {

                WContID = contenedorid.get(contenedor_tbl.getSelectedRow());
                //AnteriorID= Anteriorid.get(contenedor_tbl.getSelectedRow());
                InventarioSalidaID = inventariosalidaid.get(contenedor_tbl.getSelectedRow());
                System.out.println("contenedorid: " + WContID + "   AnteriorID: " + AnteriorID);

                try {
                    /*
                     String consulta = utils.dbConsult("select IFNULL(clienteAMPLID,'') from clientes_tbl where clienteID=(select clienteID from workorder_tbl where workID=(select WorkOrderID from workcontenedores_tbl where WContenedorID='"+WContID+"'))");
                    
                     if(consulta.equals("")) 
                     cliente.setSelectedIndex(clienteid.indexOf((utils.dbConsult("select clienteID from workorder_tbl where workID=(select WorkOrderID from workcontenedores_tbl where WContenedorID='"+WContID+"')"))));
                    
                     else cliente.setSelectedIndex(clienteid.indexOf(consulta));
                    
                     cliente1.setSelectedIndex(cliente1id.indexOf((utils.dbConsult("select clienteID from workorder_tbl where workID=(select WorkOrderID from workcontenedores_tbl where WContenedorID='"+WContID+"')"))));
                     */
                    boxPaisPlacas.setSelectedIndex(Integer.parseInt(utils.dbConsult("select PaisID-1 from inventarioexterno_tbl where inventarioID='" + InventarioSalidaID + "'")));

                    placasunidad.setText(utils.dbConsult("select placas from camiones_Tbl where camionID='" + utils.dbConsult("select camionID from camiones_tbl where camionID=(select camionID from itinerarios_tbl where WcontFK='" + WContID + "' order by itinerarios_tbl.ItinerarioID desc limit 1)") + "'"));
                    placasunidad1.setText(utils.dbConsult("select placasusa from camiones_Tbl where camionID='" + utils.dbConsult("select camionID from camiones_tbl where camionID=(select camionID from itinerarios_tbl where WcontFK='" + WContID + "' order by itinerarios_tbl.ItinerarioID desc limit 1)") + "'"));

                    /*try {
                        paisx.setSelectedItem(utils.dbConsult("select remolqueEstado from inventarioexterno_tbl where contenedor='" + txtContenedor.getText() + "' order by inventarioID desc limit 1")); // and NumeroChasis!='' and NumeroChasis is not null 
                        estx.setSelectedIndex(estxid.indexOf((utils.dbConsult("select remolqueEstado from inventarioexterno_tbl where contenedor='" + txtContenedor.getText() + "'  order by inventarioID desc limit 1")))); //and NumeroChasis!='' and NumeroChasis is not null
                    } catch (Exception e) {
                    }*/
                    //txtPlacasChasis.setText(utils.dbConsult("select placas from cajas_tbl where cajaID='"+utils.dbConsult("select cajaID from workcontenedores_tbl where WContenedorID='"+WContID+"'")+"'"));
                    //txtPlacasChasis1.setText(utils.dbConsult("select placasusa from cajas_tbl where cajaID='"+utils.dbConsult("select cajaID from workcontenedores_tbl where WContenedorID='"+WContID+"'")+"'"));
                    /*NCHASIS.setText(utils.dbConsult("select PlacasChasis from workcontenedores_tbl where WContenedorID='"+WContID+"'"));
                     txtSello.setText(utils.dbConsult("select NumeroSello from workcontenedores_tbl where WContenedorID='"+WContID+"'"));*/
 /*String estadomex = utils.dbConsult("select EstadoID from inventarioexterno_tbl where inventarioID='"+InventarioSalidaID+"'");
                     String estadousa = utils.dbConsult("select EstadoUSAID from inventarioexterno_tbl where inventarioID='"+InventarioSalidaID+"'");
                    
                     if(estadomex.equals("") || estadomex.equals("0")) boxEstadoPlacas.setSelectedIndex(0);
                     else    boxEstadoPlacas.setSelectedIndex(estadosid.indexOf(estadomex));
                    
                     if(estadousa.equals("") || estadousa.equals("0")) boxEstadoPlacas1.setSelectedIndex(0);
                     else    boxEstadoPlacas1.setSelectedIndex(estadosid1.indexOf(estadousa));*/
                    placasunidad2.setText(utils.dbConsult("select EstadoPlacas from camiones_tbl where camionID='" + utils.dbConsult("select camionID from camiones_tbl where camionID=(select camionID from itinerarios_tbl where WcontFK='" + WContID + "' order by itinerarios_tbl.ItinerarioID desc limit 1)") + "'"));
                    placasunidad3.setText(utils.dbConsult("select EstadoPlacasUSA from camiones_tbl where camionID='" + utils.dbConsult("select camionID from camiones_tbl where camionID=(select camionID from itinerarios_tbl where WcontFK='" + WContID + "' order by itinerarios_tbl.ItinerarioID desc limit 1)") + "'"));

                    boxChofer.setSelectedIndex(chofereslocalid.indexOf(utils.dbConsult("select choferID from inventarioexterno_tbl where inventarioID='" + InventarioSalidaID + "'")));
                    txtNombreChofer.setText(utils.dbConsult("SELECT NombreChofer from inventarioexterno_tbl where inventarioID='" + InventarioSalidaID + "'"));
                    txtFLlegada2.setText(utils.dbConsult("select DATE_FORMAT(now(), '" + global.fdatedb + "')"));
                    txtHLlegada.setText(utils.dbConsult("select DATE_FORMAT(now(), '%H:%i')"));
                    txtNota.setText(utils.dbConsult("select nota from inventarioexterno_tbl where inventarioID='" + InventarioSalidaID + "'"));
                    boxEstadoCarga.setSelectedIndex(Integer.parseInt(utils.dbConsult("select EstadoCarga from inventarioexterno_tbl where inventarioID='" + InventarioSalidaID + "'")));
                    boxTamano.setSelectedItem(utils.dbConsult("select tamano from inventarioexterno_tbl where inventarioID='" + InventarioSalidaID + "'"));

                    camiones.setText(utils.dbConsult("SELECT camion from inventarioexterno_tbl where inventarioID='" + InventarioSalidaID + "'"));
                    cliente1.setSelectedIndex(cliente1id.indexOf((utils.dbConsult("select EquipmentProvider from inventarioexterno_tbl where inventarioID='" + InventarioSalidaID + "'"))));

                    Orgn.setSelectedItem(utils.dbConsult("SELECT Origen from inventarioexterno_tbl where inventarioID='" + InventarioSalidaID + "'"));
                    destino.setText(utils.dbConsult("SELECT Destino from inventarioexterno_tbl where inventarioID='" + InventarioSalidaID + "'"));

                    CARRIER.setText(utils.dbConsult("SELECT Carrier from inventarioexterno_tbl where inventarioID='" + InventarioSalidaID + "'"));

                    edicion.setText(utils.dbConsult("SELECT concat((select Nombre from usuarios_Tbl where usuarioID=UsuarioEventoID),' ',"
                            + "DATE_FORMAT(fechaedicion,'%m/%d/%Y %H:%i')) from inventarioexterno_tbl where inventarioID='" + InventarioSalidaID + "'"));

                    edicion1.setText(utils.dbConsult("SELECT concat((select Nombre from usuarios_Tbl where usuarioID=CreacionUsuarioEventoID),' ',"
                            + "DATE_FORMAT(fecha,'%m/%d/%Y %H:%i')) from inventarioexterno_tbl where inventarioID='" + InventarioSalidaID + "'"));

                    boxChofer.setSelectedIndex(chofereslocalid.indexOf(utils.dbConsult("select choferID from inventarioexterno_tbl where inventarioID='" + InventarioSalidaID + "'")));
                    txtNombreChofer.setText(utils.dbConsult("SELECT NombreChofer from inventarioexterno_tbl where inventarioID='" + InventarioSalidaID + "'"));
                    txtPlacasChasis.setText(utils.dbConsult("select PlacasChasis from inventarioexterno_tbl where inventarioID='" + InventarioSalidaID + "'"));
                    txtSello.setText(utils.dbConsult("select Sello from inventarioexterno_tbl where inventarioID='" + InventarioSalidaID + "'"));
                    NCHASIS.setText(utils.dbConsult("SELECT NumeroChasis from inventarioexterno_tbl where inventarioID='" + InventarioSalidaID + "'"));
                    txtPlacasChasis.setText(utils.dbConsult("select PlacasChasis from inventarioexterno_tbl where inventarioID='" + InventarioSalidaID + "'"));
                    //txtPlacasChasis1.setText(utils.dbConsult("select PlacasChasisUSA from inventarioexterno_tbl where inventarioID='"+InventarioSalidaID+"'"));

                    /*try {
                        paisx.setSelectedItem(utils.dbConsult("select remolqueEstado from inventarioexterno_tbl where contenedor='" + txtContenedor.getText() + "' and NumeroChasis!='' and NumeroChasis is not null order by inventarioID desc limit 1"));
                        estx.setSelectedIndex(estxid.indexOf((utils.dbConsult("select remolqueEstado from inventarioexterno_tbl where contenedor='" + txtContenedor.getText() + "' and NumeroChasis!='' and NumeroChasis is not null order by inventarioID desc limit 1"))));
                    } catch (Exception e) {
                    }*/
                    camionx.setSelectedIndex(camionidx.indexOf(utils.dbConsult("SELECT camionID from inventarioexterno_tbl where inventarioID='" + InventarioSalidaID + "'")));
                    destino.setText(utils.dbConsult("SELECT Destino from inventarioexterno_tbl where inventarioID='" + InventarioSalidaID + "'"));

                    perfilBuscador1.setSelectedIndex(perfilBuscador1ID.indexOf(utils.dbConsult("SELECT IFNULL(perfilCobroID,0) from inventarioexterno_tbl where inventarioID='" + InventarioSalidaID + "'")));

                    if (txtPlacasChasis.getText().equals("")) {
                        txtPlacasChasis.setText(utils.dbConsult("select PlacasChasis from inventarioexterno_tbl where contenedor='" + txtContenedor.getText() + "' and NumeroChasis!='' and NumeroChasis is not null order by inventarioID desc limit 1"));
                    }

                    try {
                        paisx.setSelectedItem(utils.dbConsult("select remolquePais from inventarioexterno_tbl where contenedor='" + txtContenedor.getText() + "'  order by inventarioID desc limit 1")); //and NumeroChasis!='' and NumeroChasis is not null
                        estx.setSelectedIndex(estxid.indexOf((utils.dbConsult("select remolqueEstado from inventarioexterno_tbl where contenedor='" + txtContenedor.getText() + "' order by inventarioID desc limit 1")))); // and NumeroChasis!='' and NumeroChasis is not null 
                    } catch (Exception e) {
                    }

                    /*if( txtPlacasChasis1.getText().equals("") )
                     txtPlacasChasis1.setText(utils.dbConsult("select PlacasChasisUSA from inventarioexterno_tbl where contenedor='"+txtContenedor.getText()+"' and NumeroChasis!='' and NumeroChasis is not null order by inventarioID desc limit 1"));
                     */
                    if (placasunidad.getText().equals("")) {
                        placasunidad.setText(utils.dbConsult("select PlacasUnidad from inventarioexterno_tbl where contenedor='" + txtContenedor.getText() + "' and NumeroChasis!='' and NumeroChasis is not null order by inventarioID desc limit 1"));
                    }

                    if (placasunidad2.getText().equals("")) {
                        placasunidad2.setText(utils.dbConsult("select EstadoPlacasMex from inventarioexterno_tbl where contenedor='" + txtContenedor.getText() + "' and NumeroChasis!='' and NumeroChasis is not null order by inventarioID desc limit 1"));
                    }

                    if (placasunidad1.getText().equals("")) {
                        placasunidad1.setText(utils.dbConsult("select PlacasUnidadUSA from inventarioexterno_tbl where contenedor='" + txtContenedor.getText() + "' and NumeroChasis!='' and NumeroChasis is not null order by inventarioID desc limit 1"));
                    }

                    if (placasunidad3.getText().equals("")) {
                        placasunidad3.setText(utils.dbConsult("select EstadoPlacasUsa from inventarioexterno_tbl where contenedor='" + txtContenedor.getText() + "' and NumeroChasis!='' and NumeroChasis is not null order by inventarioID desc limit 1"));
                    }

                    if (txtSello.getText().equals("")) {
                        txtSello.setText(utils.dbConsult("select Sello from inventarioexterno_tbl where inventarioID='" + InventarioSalidaID + "'"));
                        System.out.println("select Sello from inventarioexterno_tbl where inventarioID='" + InventarioSalidaID + "'");
                    }

                    cliente.setSelectedIndex(clienteid.indexOf((utils.dbConsult("select ClienteID from inventarioexterno_tbl where inventarioID='" + InventarioSalidaID + "'"))));
                    cliente1.setSelectedIndex(cliente1id.indexOf((utils.dbConsult("select EquipmentProvider from inventarioexterno_tbl where inventarioID='" + InventarioSalidaID + "'"))));

                    assignedto.setText(utils.dbConsult("select assignedto from inventarioexterno_tbl where inventarioID='" + InventarioSalidaID + "'"));

                    grade.setSelectedItem(utils.dbConsult("select grade from inventarioexterno_tbl where contenedor='" + txtContenedor.getText() + "' order by inventarioID desc limit 1"));

                    /*String virtual="";
                     virtual = utils.dbConsult("select ifnull(vt,false) from patios_tbl where  patioID='"+patiosid.get(BoxPatios.getSelectedIndex())+"'");
        
                     if(virtual.equals("1")) Patio.setSelectedIndex(BoxPatios.getSelectedIndex());
                    
                     String drivermove=utils.dbConsult("select IFNULL((select itinerarioID from itinerarios_tbl where WcontFK=workcontenedores_tbl.WContenedorID order by itinerarios_tbl.ItinerarioID desc limit 1 ),'') as driverm \n" +
                     "from workcontenedores_tbl where contenedor = '"+txtContenedor.getText()+"'  and `status`=1 \n" +
                     "and IFNULL((select ItinerarioID from itinerarios_tbl where WcontFK=workcontenedores_tbl.WContenedorID and itinerarios_tbl.Status=1 limit 1 ),'') > 0 " +
                     "limit 1");
                    
                     if(!virtual.equals("1") && jCheckBox6.isSelected() && !drivermove.equals("")) {
                        
                     cliente1.setSelectedIndex(cliente1id.indexOf((utils.dbConsult("select clienteID from workorder_tbl where workID=(select WorkOrderID from workcontenedores_tbl where WContenedorID='"+WContID+"')"))));
                     boxChofer.setSelectedIndex(choferesid.indexOf(utils.dbConsult("SELECT choferID from choferes_tbl where choferID=(select choferID from itinerarios_tbl where WcontFK='"+WContID+"'  order by itinerarios_tbl.ItinerarioID desc limit 1)")));
                     txtNombreChofer.setText("");
                     camionx.setSelectedIndex(camionidx.indexOf(utils.dbConsult("select camionID from camiones_tbl where camionID=(select camionID from itinerarios_tbl where WcontFK='"+WContID+"' order by itinerarios_tbl.ItinerarioID desc limit 1)"))); 
                     Orgn.setSelectedItem(utils.dbConsult("SELECT Origen from rutas_tbl where rutas_tbl.RutaID = (select RutaID from workcontenedores_tbl where workcontenedores_tbl.WContenedorID='"+WContID+"')"));
                     destino.setText(utils.dbConsult("SELECT Destino from rutas_tbl where rutas_tbl.RutaID = (select RutaID from workcontenedores_tbl where workcontenedores_tbl.WContenedorID='"+WContID+"')"));
                        
                     consulta = utils.dbConsult("select IFNULL(clienteAMPLID,'') from clientes_tbl where clienteID=(select clienteID from workorder_tbl where workID=(select WorkOrderID from workcontenedores_tbl where WContenedorID='"+WContID+"'))");
                    
                     if(consulta.equals("")) 
                     cliente.setSelectedIndex(clienteid.indexOf((utils.dbConsult("select clienteID from workorder_tbl where workID=(select WorkOrderID from workcontenedores_tbl where WContenedorID='"+WContID+"')"))));

                     else cliente.setSelectedIndex(clienteid.indexOf(consulta));
                     }
                    
                     else {
                        
                     cliente.setSelectedIndex(cliente1id.indexOf((utils.dbConsult("select ClienteID from inventarioexterno_tbl where inventarioID='"+InventarioSalidaID+"'"))));
                     cliente1.setSelectedIndex(cliente1id.indexOf((utils.dbConsult("select EquipmentProvider from inventarioexterno_tbl where inventarioID='"+InventarioSalidaID+"'"))));
                     boxChofer.setSelectedIndex(choferesid.indexOf(utils.dbConsult("select choferID from inventarioexterno_tbl where inventarioID='"+InventarioSalidaID+"'"))); 
                     txtNombreChofer.setText(utils.dbConsult("SELECT NombreChofer from inventarioexterno_tbl where inventarioID='"+InventarioSalidaID+"'"));
                     txtPlacasChasis.setText(utils.dbConsult("select PlacasChasis from inventarioexterno_tbl where inventarioID='"+InventarioSalidaID+"'"));
                     txtSello.setText(utils.dbConsult("select Sello from inventarioexterno_tbl where inventarioID='"+InventarioSalidaID+"'"));
                     NCHASIS.setText(utils.dbConsult("SELECT NumeroChasis from inventarioexterno_tbl where inventarioID='"+InventarioSalidaID+"'")); 
                     txtPlacasChasis.setText(utils.dbConsult("select PlacasChasis from inventarioexterno_tbl where inventarioID='"+InventarioSalidaID+"'"));
                     txtPlacasChasis1.setText(utils.dbConsult("select PlacasChasisUSA from inventarioexterno_tbl where inventarioID='"+InventarioSalidaID+"'"));
                     txtSello.setText(utils.dbConsult("select Sello from inventarioexterno_tbl where inventarioID='"+InventarioSalidaID+"'"));
                     camionx.setSelectedIndex(camionidx.indexOf(utils.dbConsult("SELECT camionID from inventarioexterno_tbl where inventarioID='"+InventarioSalidaID+"'"))); 
                     destino.setText(utils.dbConsult("SELECT Destino from inventarioexterno_tbl where inventarioID='"+InventarioSalidaID+"'"));

                     }*/
                } catch (Exception ex) {
                    System.out.println(ex);
                }

                ContenedorFiltro.dispose();
            }

        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una serie de la lista.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_Pagos14ActionPerformed

    void entradaext() {
        WContID = contenedorid.get(contenedor_tbl.getSelectedRow());
        ItiID = itinerarioID.get(contenedor_tbl.getSelectedRow());
        System.out.println(WContID);

        try {

            cliente1.setSelectedIndex(cliente1id.indexOf((utils.dbConsult("select clienteFK from workcontenedores_tbl where WContenedorID='" + WContID + "'"))));
            //cliente.setSelectedIndex(clienteid.indexOf((utils.dbConsult("select LocacionTOID from rutas_tbl where rutas_tbl.RutaID =(select RutaID from workcontenedores_tbl where workcontenedores_tbl.WContenedorID='"+WContID+"')"))));

            camionx.setSelectedIndex(camionidx.indexOf(utils.dbConsult("select camionID from camiones_tbl where camionID=(select camionID from itinerarios_tbl where WcontFK='" + WContID + "' order by itinerarios_tbl.ItinerarioID desc limit 1)")));

            //String consulta = utils.dbConsult("select IFNULL(clienteAMPLID,'') from clientes_tbl where clienteID=(select clienteID from workorder_tbl where workID=(select WorkOrderID from workcontenedores_tbl where WContenedorID='"+WContID+"'))");
            //if(consulta.equals("")) 
            cliente.setSelectedIndex(clienteid.indexOf((utils.dbConsult("select clienteFK from workcontenedores_tbl where WContenedorID='" + WContID + "'"))));

            //else cliente.setSelectedIndex(clienteid.indexOf(consulta));
            /*String foxFilter="";
                     foxFilter = utils.dbConsult("select clienteID from workorder_tbl where workID=(select WorkOrderID from workcontenedores_tbl where WContenedorID='"+WContID+"')");
                     if(!foxFilter.equals("") && !utils.dbConsult("select * from clientes_tbl where razonsocial like '%foxc%' and clienteID='"+foxFilter+"' limit 1 ").equals("") && 
                     utils.dbConsult("SELECT Destino from rutas_tbl where rutas_tbl.RutaID = (select RutaID from itinerarios_tbl where WcontFK='"+WContID+"' and Status = 1 order by itinerarios_tbl.ItinerarioID desc limit 1)").equals("YARDA5") &&   
                     utils.dbConsult("Select IF(Estado=0,'Vacio','Cargado') from itinerarios_tbl where WcontFK='"+WContID+"'  order by itinerarios_tbl.ItinerarioID desc limit 1 ").equals("Vacio")   ) {
                     cliente1.setSelectedIndex(cliente1id.indexOf("81"));
                     }*/
            placasunidad.setText(utils.dbConsult("select placas from camiones_Tbl where camionID='" + utils.dbConsult("select camionID from camiones_tbl where camionID=(select camionID from itinerarios_tbl where WcontFK='" + WContID + "' order by itinerarios_tbl.ItinerarioID desc limit 1)") + "'"));
            placasunidad1.setText(utils.dbConsult("select placasusa from camiones_Tbl where camionID='" + utils.dbConsult("select camionID from camiones_tbl where camionID=(select camionID from itinerarios_tbl where WcontFK='" + WContID + "' order by itinerarios_tbl.ItinerarioID desc limit 1)") + "'"));

            txtPlacasChasis.setText(utils.dbConsult("select IF('" + paisx.getSelectedItem().toString().equals("MEX") + "',placas,placasusa) from cajas_tbl where cajaID='" + utils.dbConsult("select cajaID from workcontenedores_tbl where WContenedorID='" + WContID + "'") + "'"));

            try {
                /*paisx.setSelectedItem(utils.dbConsult("select remolqueEstado from inventarioexterno_tbl where contenedor='"+txtContenedor.getText()+"' and NumeroChasis!='' and NumeroChasis is not null order by inventarioID desc limit 1"));
                         estx.setSelectedIndex(estxid.indexOf(Integer.parseInt(utils.dbConsult("select remolqueEstado from inventarioexterno_tbl where contenedor='"+txtContenedor.getText()+"' and NumeroChasis!='' and NumeroChasis is not null order by inventarioID desc limit 1"))));*/
                paisx.setSelectedIndex(0);
            } catch (Exception e) {
            }

            //txtPlacasChasis1.setText(utils.dbConsult("select placasusa from cajas_tbl where cajaID='"+utils.dbConsult("select cajaID from workcontenedores_tbl where WContenedorID='"+WContID+"'")+"'"));
            NCHASIS.setText(utils.dbConsult("select PlacasChasis from workcontenedores_tbl where WContenedorID='" + WContID + "'"));
            txtSello.setText(utils.dbConsult("select NumeroSello from workcontenedores_tbl where WContenedorID='" + WContID + "'"));

            String pais = utils.dbConsult("select PaisID-1 from workcontenedores_tbl where WContenedorID='" + WContID + "'");
            String estado = utils.dbConsult("select EstadoID from workcontenedores_tbl where WContenedorID='" + WContID + "'");

            /*if(pais.equals("1")) {  
                     if(estado.equals("") || estado.equals("0")) boxEstadoPlacas.setSelectedIndex(0);
                     else    boxEstadoPlacas.setSelectedIndex(estadosid.indexOf(estado));
                     }
                    
                     else    { 
                        
                     if(estado.equals("") || estado.equals("0")) boxEstadoPlacas1.setSelectedIndex(0);
                     else    boxEstadoPlacas1.setSelectedIndex(estadosid1.indexOf(estado));
                     }*/
            placasunidad2.setText(utils.dbConsult("select EstadoPlacas from camiones_tbl where camionID='" + utils.dbConsult("select camionID from camiones_tbl where camionID=(select camionID from itinerarios_tbl where WcontFK='" + WContID + "' order by itinerarios_tbl.ItinerarioID desc limit 1)") + "'"));
            placasunidad3.setText(utils.dbConsult("select EstadoPlacasUSA from camiones_tbl where camionID='" + utils.dbConsult("select camionID from camiones_tbl where camionID=(select camionID from itinerarios_tbl where WcontFK='" + WContID + "' order by itinerarios_tbl.ItinerarioID desc limit 1)") + "'"));

            CARRIER.setText(utils.dbConsult("Select razonsocial from empresas_tbl where empresaID=(select empresaID from camiones_tbl where camionID='" + utils.dbConsult("select camionID from camiones_tbl where camionID=(select camionID from itinerarios_tbl where WcontFK='" + WContID + "'  order by itinerarios_tbl.ItinerarioID desc limit 1 )") + "')"));
            //CARRIER.setText(utils.dbConsult("SELECT Nombre from choferes_tbl where choferID=(select choferID from itinerarios_tbl where WcontFK='"+WContID+"' and Status = 1  order by itinerarios_tbl.ItinerarioID desc limit 1)"));

            boxChofer.setSelectedIndex(chofereslocalid.indexOf(utils.dbConsult("select choferID from itinerarios_tbl where WcontFK='" + WContID + "' and Status = 1  order by itinerarios_tbl.ItinerarioID desc limit 1")));
            txtNombreChofer.setText("");
            txtFLlegada2.setText(utils.dbConsult("select DATE_FORMAT(now(), '" + global.fdatedb + "')"));
            txtHLlegada.setText(utils.dbConsult("select DATE_FORMAT(now(), '%H:%i')"));
            Orgn.setSelectedItem(utils.dbConsult("SELECT Origen from rutas_tbl where rutas_tbl.RutaID = (select RutaID from itinerarios_tbl where WcontFK='" + WContID + "'  and Status = 1 order by itinerarios_tbl.ItinerarioID desc limit 1)"));
            destino.setText(utils.dbConsult("SELECT Destino from rutas_tbl where rutas_tbl.RutaID = (select RutaID from itinerarios_tbl where WcontFK='" + WContID + "' and Status = 1 order by itinerarios_tbl.ItinerarioID desc limit 1)"));

            boxEstadoCarga.setSelectedItem(utils.dbConsult("Select IF(Estado=0,'Vacio','Cargado') from itinerarios_tbl where WcontFK='" + WContID + "'  order by itinerarios_tbl.ItinerarioID desc limit 1 "));
            boxTamano.setSelectedItem(utils.dbConsult("Select Tamano from workcontenedores_tbl where WContenedorID='" + WContID + "'"));

            perfilBuscador1.setSelectedIndex(0);

            if (txtPlacasChasis.getText().equals("")) {
                txtPlacasChasis.setText(utils.dbConsult("select PlacasChasis from inventarioexterno_tbl where contenedor='" + txtContenedor.getText() + "' and NumeroChasis!='' and NumeroChasis is not null order by inventarioID desc limit 1"));
            }

            /*if( txtPlacasChasis1.getText().equals("") )
                     txtPlacasChasis1.setText(utils.dbConsult("select PlacasChasisUSA from inventarioexterno_tbl where contenedor='"+txtContenedor.getText()+"' and NumeroChasis!='' and NumeroChasis is not null order by inventarioID desc limit 1"));
             */
            try {
                paisx.setSelectedItem(utils.dbConsult("select remolqueEstado from inventarioexterno_tbl where contenedor='" + txtContenedor.getText() + "' and NumeroChasis!='' and NumeroChasis is not null order by inventarioID desc limit 1"));
                estx.setSelectedIndex(estxid.indexOf((utils.dbConsult("select remolqueEstado from inventarioexterno_tbl where contenedor='" + txtContenedor.getText() + "' and NumeroChasis!='' and NumeroChasis is not null order by inventarioID desc limit 1"))));
            } catch (Exception e) {
            }

            /*txtNota.setText("");
                     boxEstadoCarga.setSelectedIndex(0);*/
        } catch (Exception e) {
        }

        ContenedorFiltro.dispose();
    }
    private void Pagos15MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Pagos15MouseEntered
        ImageIcon rollOver = new ImageIcon(getClass().getResource("/Images/ios/findrol.png"));
        Pagos15.setIcon(rollOver);
    }//GEN-LAST:event_Pagos15MouseEntered

    private void Pagos15MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Pagos15MouseExited
        ImageIcon rollOver = new ImageIcon(getClass().getResource("/Images/ios/find.png"));
        Pagos15.setIcon(rollOver);
    }//GEN-LAST:event_Pagos15MouseExited

    private void Pagos15MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Pagos15MousePressed
        ImageIcon rollOver = new ImageIcon(getClass().getResource("/Images/ios/findsel.png"));
        Pagos15.setIcon(rollOver);
    }//GEN-LAST:event_Pagos15MousePressed

    private void Pagos15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Pagos15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Pagos15ActionPerformed

    private void ContenedorFiltroWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_ContenedorFiltroWindowGainedFocus
        TooltipManager.showAllTooltips();
    }//GEN-LAST:event_ContenedorFiltroWindowGainedFocus

    private void boxFiltroBusquedaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_boxFiltroBusquedaItemStateChanged
        cargarTabla();
    }//GEN-LAST:event_boxFiltroBusquedaItemStateChanged

    private void boxFiltroBusqueda1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_boxFiltroBusqueda1ItemStateChanged
        cargarTabla();
    }//GEN-LAST:event_boxFiltroBusqueda1ItemStateChanged

    private void boxFiltroBusqueda1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxFiltroBusqueda1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boxFiltroBusqueda1ActionPerformed

    private void BoxPatiosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_BoxPatiosItemStateChanged
        if (BoxPatios.getSelectedIndex() > 0) {
            cargarTabla();
        }

        /*if( BoxPatios.getSelectedIndex()>=0 ) {
        
         String virtual="";
         virtual = utils.dbConsult("select ifnull(vt,false) from patios_tbl where  patioID='"+patiosid.get(BoxPatios.getSelectedIndex())+"'");
        
         if(virtual.equals("1")) { 
        
         jLabel78.setVisible(true);
         perfilBuscador1.setVisible(true);
         }
        
         else {
         jLabel78.setVisible(false);
         perfilBuscador1.setVisible(false);
         }
        
         }*/
    }//GEN-LAST:event_BoxPatiosItemStateChanged

    private void BoxPatiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoxPatiosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BoxPatiosActionPerformed

    private void camionxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_camionxActionPerformed
        if (camionx.getSelectedIndex() == 0 && InOut.getSelectedItem().equals("Salida")) {
            txtPlacasChasis.setText(utils.dbConsult("select PlacasChasis from inventarioexterno_tbl where contenedor='" + txtContenedor.getText() + "' order by inventarioID desc limit 1"));
            NCHASIS.setText(utils.dbConsult("select NumeroChasis from inventarioexterno_tbl where contenedor='" + txtContenedor.getText() + "' order by inventarioID desc limit 1"));
            txtSello.setText(utils.dbConsult("select Sello from inventarioexterno_tbl where contenedor='" + txtContenedor.getText() + "' order by inventarioID desc limit 1"));
        }
    }//GEN-LAST:event_camionxActionPerformed

    private void OrgnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OrgnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_OrgnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        Connection con = utils.startConnection();

        try {
            JasperReport reporte;
            JasperPrint jasperPrint = null;
            if (boxTipoReporte.getSelectedIndex() == 0) {
                reporte = (JasperReport) JRLoader.loadObject(new File("ReporteInventarioEntradas.jasper"));
                Map parametros = new HashMap();

                parametros.put("clienteID", BoxClienteID.get(BoxCliente.getSelectedIndex()));

                /*parametros.put("fechaini", utils.dateFromFieldtoDB(txtIni.getText()) + "");
                 parametros.put("fechafin", utils.dateFromFieldtoDB(txtFin.getText()) + "");
                
                 parametros.put("fechafilter", fechaz.isSelected() + "");
                
                 parametros.put("almacenID", almID.get(Alm.getSelectedIndex()) + "");
                 parametros.put("articuloID", artID.get(Art.getSelectedIndex()) + "");
                 parametros.put("proveedorID", provID.get(Prov.getSelectedIndex()) + "");*/
                jasperPrint = JasperFillManager.fillReport(reporte, parametros, con);
            }

            utils.windowJasper(jasperPrint);
            con.close();
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex, "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex, "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void BoxClienteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_BoxClienteItemStateChanged
        if (BoxCliente.getSelectedIndex() > 0) {
            cargarPerfilesMain();
            cargarTabla();
        }

    }//GEN-LAST:event_BoxClienteItemStateChanged

    private void BoxClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoxClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BoxClienteActionPerformed

    private void HistorialActualItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_HistorialActualItemStateChanged
        cargarTabla();
    }//GEN-LAST:event_HistorialActualItemStateChanged

    private void HistorialActualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HistorialActualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HistorialActualActionPerformed

    private void BoxCliente1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_BoxCliente1ItemStateChanged
        cargarTabla();
    }//GEN-LAST:event_BoxCliente1ItemStateChanged

    private void BoxCliente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoxCliente1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BoxCliente1ActionPerformed

    private void bContenedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bContenedoresActionPerformed
        try {

            Map parametros = new HashMap();

            String q = QUERY;

            System.out.println(q);
            JRDesignQuery query = new JRDesignQuery();
            query.setText(q);

            //JasperDesign jd = JRXmlLoader.load("src\\constructora\\Reporte_Transferencia.jrxml");
            /*String source = getClass().getResource("Almacen_Produccion_Filtrado.jrxml").toString();
             System.out.println(source);*/
            JasperDesign jd = JRXmlLoader.load("C:\\SERVER\\TransportesNOE\\dist\\Inventario_Cajas_Mexcal.jrxml");
            jd.setQuery(query);

            Connection con = utils.startConnection();
            JasperReport reporte = (JasperReport) JasperCompileManager.compileReport(jd);//(JasperReport) JRLoader.loadObject(new File("Reporte_Transferencias.jasper"));

            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, con);

            utils.windowJasper(jasperPrint);

        } catch (JRException ex) {
            Logger.getLogger(ItinerarioGuardiasContExternos2.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error " + ex + "\n" + ex.getStackTrace()[0], "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bContenedoresActionPerformed

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged

        if (jCheckBox1.isSelected()) {
            jCheckBox2.setSelected(true);
            jCheckBox2.setVisible(false);
        } else {
            jCheckBox2.setSelected(true);
            jCheckBox2.setVisible(true);
        }

        cargarTabla();
    }//GEN-LAST:event_jCheckBox1ItemStateChanged

    private void camionxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_camionxItemStateChanged
        //if( camionx.getSelectedIndex()>=0 ) CARRIER.setText("MX");
        if (camionx.getSelectedIndex() >= 0 && choferesid.size() > 0) {
            boxChofer.setSelectedIndex(chofereslocalid.indexOf(utils.dbConsult("select choferID from choferes_tbl where camionID='" + camionidx.get(camionx.getSelectedIndex()) + "' ")));
        }
        if (boxChofer.getSelectedIndex() == -1) {
            boxChofer.setSelectedIndex(0);
        }

        if (camionx.getSelectedIndex() > 0) {
            bcamion.setText(utils.dbConsult("SELECT DATE_FORMAT(fecha,'" + global.fdatedb + "') from requimant_tbl where camionID='" + camionidx.get(camionx.getSelectedIndex()) + "' and  rev90 is true "
                    + "and categoriaID=1 order by RequisicionID desc limit 1"));
        }

        if (camionx.getSelectedIndex() == 0) {
            camiones.setEditable(true);
        } else {
            camiones.setEditable(false);
        }

        if (camionx.getSelectedIndex() == 0 && InOut.getSelectedItem().equals("Salida")) {
            txtPlacasChasis.setText(utils.dbConsult("select PlacasChasis from inventarioexterno_tbl where contenedor='" + txtContenedor.getText() + "' order by inventarioID desc limit 1"));
            NCHASIS.setText(utils.dbConsult("select NumeroChasis from inventarioexterno_tbl where contenedor='" + txtContenedor.getText() + "' order by inventarioID desc limit 1"));
            txtSello.setText(utils.dbConsult("select Sello from inventarioexterno_tbl where contenedor='" + txtContenedor.getText() + "' order by inventarioID desc limit 1"));
        }

    }//GEN-LAST:event_camionxItemStateChanged

    private void bContenedores1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bContenedores1ActionPerformed
        try {

            Map parametros = new HashMap();
            String client = "";
            if (BoxCliente1.getSelectedIndex() > 0) {
                client = BoxCliente1.getSelectedItem().toString();
            } else {
                client = BoxCliente.getSelectedItem().toString();
            }

            parametros.put("Client", client);

            String q = QUERY;

            System.out.println(q);
            JRDesignQuery query = new JRDesignQuery();
            query.setText(q);

            //JasperDesign jd = JRXmlLoader.load("src\\constructora\\Reporte_Transferencia.jrxml");
            /*String source = getClass().getResource("Almacen_Produccion_Filtrado.jrxml").toString();
             System.out.println(source);*/
            JasperDesign jd = JRXmlLoader.load("C:\\SERVER\\TransportesNOE\\dist\\Inventario_Cajas_Cliente.jrxml");
            jd.setQuery(query);

            Connection con = utils.startConnection();
            JasperReport reporte = (JasperReport) JasperCompileManager.compileReport(jd);//(JasperReport) JRLoader.loadObject(new File("Reporte_Transferencias.jasper"));

            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, con);

            utils.windowJasper(jasperPrint);

        } catch (JRException ex) {
            Logger.getLogger(ItinerarioGuardiasContExternos2.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error " + ex + "\n" + ex.getStackTrace()[0], "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bContenedores1ActionPerformed

    private void jTable9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable9MouseClicked
        int count = jTable9.getRowCount();
        int row = jTable9.getSelectedRow();
        if (evt.getButton() == 3) {
            jPopupMenu3.show(jTable9, evt.getX(), evt.getY());
        }

        if (count == 0) {
            tablemodel9.addRow(new String[]{"", ""});
            archivoc.add("0");
        }

        if (row >= 0) {
            if (evt.getClickCount() == 2) {
                JFileChooser filechooser = new JFileChooser();

                filechooser.setMultiSelectionEnabled(false);
                if (filechooser.showOpenDialog(dArchivos) == JFileChooser.APPROVE_OPTION) {
                    File archivo = filechooser.getSelectedFile();
                    if (archivoc.get(row).equals("0")) {
                        String id = utils.dbInsert("INSERT INTO inventarioexterno_archivos (inventarioID, NombreArchivo, Concepto, Archivo) VALUES('" + inventarioID.get(jTable1.getSelectedRow()) + "','" + archivo.getName() + "',replace('" + jTable9.getValueAt(row, 0) + "',' ','_'),'" + FileUtils.getFileExtPart(archivo.toString(), true) + "')");
                        if (id.length() < 11 && !id.isEmpty()) {
                            File destino = new File(global.path + "archivospatios\\" + id + FileUtils.getFileExtPart(archivo.toString(), true));
                            if (!utils.copyFile(archivo, destino, false)) {
                                utils.dbUpdate("UPDATE inventarioexterno_archivos SET Fecha = null,Status = false where ArchivoID = '" + id + "'");
                            }
                            cargarArchivosChofer(inventarioID.get(jTable1.getSelectedRow()));
                        } else {
                            JOptionPane.showMessageDialog(dArchivos, "Error " + id, "Error", JOptionPane.ERROR_MESSAGE);
                        }

                    } else {

                        File destino = new File(global.path + "archivospatios\\" + archivoc.get(row) + FileUtils.getFileExtPart(archivo.toString(), true));
                        String up = utils.dbUpdate("UPDATE inventarioexterno_archivos SET Archivo = '" + FileUtils.getFileExtPart(archivo.toString(), true) + "',NombreArchivo = '" + archivo.getName() + "' where ArchivoID = '" + archivoc.get(row) + "'");
                        if (up.isEmpty()) {
                            if (!utils.copyFile(archivo, destino, false)) {
                                utils.dbUpdate("UPDATE inventarioexterno_archivos SET Fecha = null,Status = false where ArchivoID = '" + archivoc.get(row) + "'");
                            }
                            cargarArchivosChofer(inventarioID.get(jTable1.getSelectedRow()));
                        }
                    }
                }
            }
        }

    }//GEN-LAST:event_jTable9MouseClicked

    private void cargarArchivosChofer(String inventario) {
        String query = "SELECT ArchivoID,Concepto,NombreArchivo from inventarioexterno_archivos where inventarioID = '" + inventario + "' and Status = true";
        tablemodel9.setRowCount(0);
        archivoc.clear();
        Connection con = utils.startConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                tablemodel9.addRow(new Object[]{rs.getString(2), new File(rs.getString(3)).getName()});
                archivoc.add(rs.getString(1));
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    private void jTable9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable9KeyPressed
        int row = jTable9.getSelectedRow();
        int count = jTable9.getRowCount();
        if ((row + 1) == count) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                tablemodel9.addRow(new String[]{"", ""});
                archivoc.add("0");
            }
        }
    }//GEN-LAST:event_jTable9KeyPressed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarArchivosChofer(inventarioID.get(jTable1.getSelectedRow()));
        }
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        int row = jTable9.getSelectedRow();
        if (row >= 0) {
            if (Desktop.isDesktopSupported()) {
                try {
                    File myFile = new File(global.path + "\\archivospatios\\" + utils.dbConsult("SELECT CONCAT(ArchivoID,Archivo) from inventarioexterno_archivos where ArchivoID = '" + archivoc.get(row) + "'"));
                    System.out.println(global.path + "\\archivospatios\\" + utils.dbConsult("SELECT CONCAT(ArchivoID,Archivo) from inventarioexterno_archivos where ArchivoID = '" + archivoc.get(row) + "'"));
                    Desktop.getDesktop().open(myFile);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(dArchivos, "Error " + ex, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void mCargarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mCargarArchivoActionPerformed
        int row = jTable1.getSelectedRow();
        SelectedInventarioID = inventarioID.get(row);
        if (row >= 0) {
            cargarArchivosChofer(SelectedInventarioID);
            dArchivos.setLocationRelativeTo(this);
            dArchivos.setVisible(true);
            dArchivos.setLocationRelativeTo(this);
        }
    }//GEN-LAST:event_mCargarArchivoActionPerformed

    private void PatioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PatioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PatioActionPerformed

    private void gradeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gradeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gradeActionPerformed

    private void bContenedores2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bContenedores2ActionPerformed

        try {

            Map parametros = new HashMap();

            String fix = "", ffx = "";

            if (FechaFiltro.isSelected()) {
                fix = fi.getText();
                ffx = ff.getText();
            } else {
                fix = " - ";
                ffx = " - ";
            }

            String perfil = "0";
            if (perfilBuscador.getSelectedIndex() > 0) {
                perfil = perfilBuscadorID.get(perfilBuscador.getSelectedIndex());
            } else {
                perfil = "0";
            }

            parametros.put("yard", BoxPatios.getSelectedItem().toString());
            parametros.put("customer", BoxCliente.getSelectedItem().toString());
            parametros.put("from", fix);
            parametros.put("to", ffx);
            parametros.put("perfilID", perfil);

            String q = QUERYAMPL;

            System.out.println(q);
            JRDesignQuery query = new JRDesignQuery();
            query.setText(q);

            //JasperDesign jd = JRXmlLoader.load("src\\constructora\\Reporte_Transferencia.jrxml");
            /*String source = getClass().getResource("Almacen_Produccion_Filtrado.jrxml").toString();
             System.out.println(source);*/
            JasperDesign jd = JRXmlLoader.load("C:\\SERVER\\TransportesNOE\\dist\\ReporteInventarioAMPL.jrxml");
            jd.setQuery(query);

            Connection con = utils.startConnection();
            JasperReport reporte = (JasperReport) JasperCompileManager.compileReport(jd);//(JasperReport) JRLoader.loadObject(new File("Reporte_Transferencias.jasper"));

            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, con);

            utils.windowJasper(jasperPrint);

        } catch (JRException ex) {
            Logger.getLogger(ItinerarioGuardiasContExternos2.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error " + ex + "\n" + ex.getStackTrace()[0], "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_bContenedores2ActionPerformed

    private void FechaFiltroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_FechaFiltroItemStateChanged

        cargarTabla();
    }//GEN-LAST:event_FechaFiltroItemStateChanged

    private void bContenedores3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bContenedores3ActionPerformed
        utils.dbUpdate("Delete from inventarioexterno_tbl where inventarioID='" + inventarioID.get(jTable1.getSelectedRow()) + "'");
        cargarTabla();
    }//GEN-LAST:event_bContenedores3ActionPerformed

    private void jTable10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable10MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable10MouseClicked

    private void jTable10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable10KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable10KeyPressed

    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3KeyPressed

    private void jCheckBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox2ItemStateChanged
        cargarTabla();
    }//GEN-LAST:event_jCheckBox2ItemStateChanged

    private void txtTotalReg1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalReg1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalReg1KeyPressed

    private void perfilBuscadorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_perfilBuscadorItemStateChanged
        cargarTabla();
    }//GEN-LAST:event_perfilBuscadorItemStateChanged

    private void perfilBuscadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_perfilBuscadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_perfilBuscadorActionPerformed

    private void Pagos16MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Pagos16MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_Pagos16MouseEntered

    private void Pagos16MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Pagos16MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_Pagos16MouseExited

    private void Pagos16MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Pagos16MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Pagos16MousePressed

    private void Pagos16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Pagos16ActionPerformed

        String perfilxID = "";

        if (Perfil.getSelectedIndex() == 0) {
            perfilxID = utils.dbInsert("INSERT INTO perfilcobro (nombre, concepto, `clienteID`) VALUES ('" + perfilnombre.getText() + "', '" + perfilconcepto.getText() + "', '" + clientePerfilID.get(clientePerfil.getSelectedIndex()) + "')");

        } else {
            utils.dbUpdate("update perfilcobro set nombre='" + perfilnombre.getText() + "', concepto='" + perfilconcepto.getText() + "', clienteID='" + clientePerfilID.get(clientePerfil.getSelectedIndex()) + "' "
                    + "where ID='" + PerfilID.get(Perfil.getSelectedIndex()) + "'");

            perfilxID = PerfilID.get(Perfil.getSelectedIndex());
        }

        for (int i = 0; i < tableRango.getRowCount(); i++) {

            if (rangoID.get(i).equals("0")) {

                utils.dbInsert("INSERT INTO perfilcobro_rango (minimo, maximo, costo, `perfilID`) "
                        + "VALUES ('" + tableRango.getValueAt(i, 0) + "', '" + tableRango.getValueAt(i, 1) + "', '" + tableRango.getValueAt(i, 2) + "', '" + perfilxID + "')");
            } else {

                utils.dbUpdate("UPDATE perfilcobro_rango SET minimo='" + tableRango.getValueAt(i, 0) + "', maximo='" + tableRango.getValueAt(i, 1) + "', costo='" + tableRango.getValueAt(i, 2) + "' "
                        + "Where ID='" + rangoID.get(i) + "'");
            }

        }

        cargarPerfiles();
        cargarPerfilesMain();
        if (!perfilxID.equals("")) {
            Perfil.setSelectedIndex(PerfilID.indexOf(perfilxID));
        }

    }//GEN-LAST:event_Pagos16ActionPerformed

    void cargarPerfiles() {

        String query = "SELECT * from perfilcobro where clienteID = '" + clientePerfilID.get(clientePerfil.getSelectedIndex()) + "' ";

        Perfil.removeAllItems();
        PerfilID.clear();

        Perfil.addItem("Crear Nuevo");
        PerfilID.add("0");

        Connection con = utils.startConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            int i = 1;
            while (rs.next()) {
                PerfilID.add(rs.getString("ID"));
                Perfil.addItem(rs.getString("nombre"));

            }

            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    void cargarPerfilesMain() {

        String query = "SELECT * from perfilcobro where clienteID = '" + BoxClienteID.get(BoxCliente.getSelectedIndex()) + "' ";

        perfilBuscador.removeAllItems();
        perfilBuscadorID.clear();

        perfilBuscador.addItem("Ninguno");
        perfilBuscadorID.add("0");

        Connection con = utils.startConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            int i = 1;
            while (rs.next()) {
                perfilBuscadorID.add(rs.getString("ID"));
                perfilBuscador.addItem(rs.getString("nombre"));
            }

            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    void cargarPerfilesRegistro() {

        String query = "SELECT * from perfilcobro where clienteID = '" + clienteid.get(cliente.getSelectedIndex()) + "' ";

        perfilBuscador1.removeAllItems();
        perfilBuscador1ID.clear();

        perfilBuscador1.addItem("Ninguno");
        perfilBuscador1ID.add("0");

        Connection con = utils.startConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            int i = 1;
            while (rs.next()) {
                perfilBuscador1ID.add(rs.getString("ID"));
                perfilBuscador1.addItem(rs.getString("nombre"));
            }

            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    private void anexo_add9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_anexo_add9MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_anexo_add9MouseEntered

    private void anexo_add9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_anexo_add9MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_anexo_add9MouseExited

    private void anexo_add9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_anexo_add9MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_anexo_add9MousePressed

    private void anexo_add9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anexo_add9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_anexo_add9ActionPerformed

    private void anexo_del9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_anexo_del9MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_anexo_del9MouseEntered

    private void anexo_del9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_anexo_del9MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_anexo_del9MouseExited

    private void anexo_del9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_anexo_del9MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_anexo_del9MousePressed

    private void anexo_del9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anexo_del9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_anexo_del9ActionPerformed

    private void plus14MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plus14MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_plus14MouseEntered

    private void plus14MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plus14MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_plus14MouseExited

    private void plus14MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plus14MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_plus14MousePressed

    private void plus14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plus14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_plus14ActionPerformed

    private void plus15MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plus15MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_plus15MouseEntered

    private void plus15MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plus15MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_plus15MouseExited

    private void plus15MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plus15MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_plus15MousePressed

    private void plus15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plus15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_plus15ActionPerformed

    private void Pagos17MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Pagos17MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_Pagos17MouseEntered

    private void Pagos17MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Pagos17MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_Pagos17MouseExited

    private void Pagos17MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Pagos17MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Pagos17MousePressed

    private void Pagos17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Pagos17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Pagos17ActionPerformed

    private void PerfilesWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_PerfilesWindowGainedFocus
        // TODO add your handling code here:
    }//GEN-LAST:event_PerfilesWindowGainedFocus

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Perfiles.setLocationRelativeTo(this);
        Perfiles.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        tablemodelRango.addRow(new String[]{"", "", "0"});
        rangoID.add("0");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void cargarRangos() {

        String rangoIDx = "";
        if (Perfil.getSelectedIndex() > 0) {
            rangoIDx = " where perfilID='" + PerfilID.get(Perfil.getSelectedIndex()) + "' ";
        } else {
            rangoIDx = " where ID>0 ";
        }

        String query = "SELECT * from perfilcobro_rango " + rangoIDx + " ";

        tablemodelRango.setRowCount(0);
        rangoID.clear();
        Connection con = utils.startConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                tablemodelRango.addRow(new Object[]{rs.getString("minimo"), rs.getString("maximo"), rs.getString("costo")});
                rangoID.add(rs.getString("ID"));
            }

            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    private void PerfilItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_PerfilItemStateChanged

        if (Perfil.getSelectedIndex() > 0) {
            perfilnombre.setText(utils.dbConsult("select nombre from perfilcobro where ID='" + PerfilID.get(Perfil.getSelectedIndex()) + "'"));
            perfilconcepto.setText(utils.dbConsult("select concepto from perfilcobro where ID='" + PerfilID.get(Perfil.getSelectedIndex()) + "'"));
            cargarRangos();

        } else {
            perfilnombre.setText("");
            perfilconcepto.setText("");
            tablemodelRango.setRowCount(0);
            rangoID.clear();
        }


    }//GEN-LAST:event_PerfilItemStateChanged

    private void clientePerfilItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_clientePerfilItemStateChanged
        if (clientePerfil.getSelectedIndex() > 0) {
            cargarPerfiles();
        }
    }//GEN-LAST:event_clientePerfilItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
//
//        if (BoxCliente.getSelectedIndex() > 0) { //&& perfilBuscador.getSelectedIndex()>0
//
//            dNuevoDoc frame = new dNuevoDoc(this, true, true, BoxClienteID.get(BoxCliente.getSelectedIndex()), "1");
//
////            frame.clientend = BoxClienteID.get(BoxCliente.getSelectedIndex());
////            frame.boxMonedaND.setSelectedItem(utils.dbConsult("SELECT IFNULL((SELECT Nombre from monedas_tbl where monedas_tbl.Moneda = clientes_tbl.Moneda), 'USD') from clientes_tbl where ClienteID = '" + BoxClienteID.get(BoxCliente.getSelectedIndex()) + "'"));
////            frame.txtCliente.setText(BoxCliente.getSelectedItem().toString());
//            //frame.btTipoDoc.setSelected(true);
////            FillCombo.cargarUnidadesNegocio(frame.boxUNegocio, frame.unegocioid, "");
//            String amplxid = utils.dbInsert("INSERT INTO inventarioexterno_tbl_facturacion (`clienteID`, `perfilID`, factura, `FechaInicial`, `FechaFinal`, `Status`) "
//                    + "VALUES ('" + BoxClienteID.get(BoxCliente.getSelectedIndex()) + "', '" + perfilBuscadorID.get(perfilBuscador.getSelectedIndex()) + "', NULL, '" + utils.dateFromFieldtoDBwoH(fi.getText(), true) + "', "
//                    + "'" + utils.dateFromFieldtoDBwoH(ff.getText(), true) + "', true)");
//
//            String concepto = utils.dbConsult("select CONCAT( 'RENTAS DE CONTENEDORES DEL PERIODO ', DATE_FORMAT('" + utils.dateFromFieldtoDBwoH(fi.getText(), true) + "','%d/%m/%Y'), ' - ' ,DATE_FORMAT('" + utils.dateFromFieldtoDBwoH(ff.getText(), true) + "','%d/%m/%Y') )");
//
//            frame.ampl = true;
//            frame.amplfact = amplxid;
//            frame.conceptofact = concepto;
//            frame.selectedclient = BoxClienteID.get(BoxCliente.getSelectedIndex());
//
//            frame.clientend = BoxClienteID.get(BoxCliente.getSelectedIndex());
//            frame.boxMonedaND.setSelectedItem(utils.dbConsult("SELECT IFNULL((SELECT Nombre from monedas_tbl where monedas_tbl.Moneda = clientes_tbl.Moneda), 'USD') from clientes_tbl where ClienteID = '" + BoxClienteID.get(BoxCliente.getSelectedIndex()) + "'"));
//            frame.txtCliente.setText(BoxCliente.getSelectedItem().toString());
//            frame.boxUsoCFDI.setSelectedIndex(frame.usocfdi.indexOf(utils.dbConsult("select UsoCFDIc from clientes_tbl where clienteID='" + BoxClienteID.get(BoxCliente.getSelectedIndex()) + "'")));
//
//            String query = QUERYAMPL;
//            Connection con = utils.startConnection();
//            try {
//                Statement statement = con.createStatement();
//                ResultSet rs = statement.executeQuery(query);
//                while (rs.next()) {
//
//                    frame.mnuevodoc.addRow(new Object[]{0, rs.getString("concatenado"), 1, rs.getString("TOTALMONEY"), rs.getString("TOTALMONEY"), false, 0, 0, 0, "", ""});
//
//                    /*String queryx = "select * from perfilcobroadicional_aplicado where inventarioID='"+rs.getString("inventarioID")+"'";
//                    Connection conx = utils.startConnection();
//                    try {
//                        Statement statementx = conx.createStatement();
//                        ResultSet rsx = statementx.executeQuery(queryx);
//                        while (rsx.next()) {
//                            frame.mnuevodoc.addRow(new Object[]{0, rs.getString("concatenado")+" | "+rsx.getString("concepto"), 1, rsx.getString("valor"), rsx.getString("valor"), false, 0, 0, 0, "", ""});
//
//                            frame.saveaccesoriond.add("0");
//                        }
//                        conx.close();
//                    } catch (SQLException ex) {
//                        System.out.println("Error " + ex);
//                    }*/
//                    frame.saveaccesoriond.add("38");
//                }
//                con.close();
//            } catch (SQLException e) {
//                System.out.println("Error " + e);
//            }
//
//            frame.calcularTotalesDoc();
//
//            genReportCobro(amplxid);
//
//            /*frame.jButton20.setVisible(false);
//            frame.factex.setVisible(true);
//            frame.factnew.setVisible(true);*/
//            frame.txtTipoCambioND.setText(utils.dbConsult("SELECT ROUND(getTipoCambio(CURDATE()),4)"));
//            frame.setLocationRelativeTo(this);
//
//            frame.jButton2.setVisible(false);
//
//            frame.cEnvio.setVisible(false);
//            frame.jButton1.setVisible(false);
//            frame.jButton16.setVisible(false);
//
//            frame.setVisible(true);
//        }
//
//        if (BoxCliente.getSelectedIndex() < 1) {
//            JOptionPane.showMessageDialog(this, "Seleccione un cliente de la lista de filtrado.", "Error", JOptionPane.ERROR_MESSAGE);
//        }
//
//        //old
//        /*if (BoxCliente.getSelectedIndex() > 0 && perfilBuscador.getSelectedIndex() > 0) {
//
//            FacturasCobrosAMPL frame = new FacturasCobrosAMPL();
//
//            frame.clientend = BoxClienteID.get(BoxCliente.getSelectedIndex());
//            frame.boxMonedaND.setSelectedItem(utils.dbConsult("SELECT IFNULL((SELECT Nombre from monedas_tbl where monedas_tbl.Moneda = clientes_tbl.Moneda), 'USD') from clientes_tbl where ClienteID = '" + BoxClienteID.get(BoxCliente.getSelectedIndex()) + "'"));
//            frame.txtCliente.setText(BoxCliente.getSelectedItem().toString());
//            //frame.btTipoDoc.setSelected(true);
//
//            String amplxid = utils.dbInsert("INSERT INTO inventarioexterno_tbl_facturacion (`clienteID`, `perfilID`, factura, `FechaInicial`, `FechaFinal`, `Status`) "
//                    + "VALUES ('" + BoxClienteID.get(BoxCliente.getSelectedIndex()) + "', '" + perfilBuscadorID.get(perfilBuscador.getSelectedIndex()) + "', NULL, '" + utils.dateFromFieldtoDBwoH(fi.getText(), true) + "', '" + utils.dateFromFieldtoDBwoH(ff.getText(), true) + "', true)");
//
//            frame.ampl = true;
//            frame.amplfact = amplxid;
//
//            String query = QUERYAMPL;
//            Connection con = utils.startConnection();
//            try {
//                Statement statement = con.createStatement();
//                ResultSet rs = statement.executeQuery(query);
//                while (rs.next()) {
//                    frame.mnuevodoc.addRow(new Object[]{0, rs.getString("concatenado"), 1, rs.getString("TOTALMONEY"), rs.getString("TOTALMONEY"), 0, 0, 0.00, "", ""});
//                }
//                con.close();
//            } catch (SQLException e) {
//                System.out.println("Error " + e);
//            }
//
//            frame.calcularTotalesDoc();
//
//            genReportCobro(amplxid);
//
//            frame.txtTipoCambioND.setText(utils.dbConsult("SELECT ROUND(getTipoCambio(CURDATE()),4)"));
//            frame.dNuevoDocumento.setLocationRelativeTo(this);
//            frame.dNuevoDocumento.setVisible(true);
//        }
//
//        if (perfilBuscador.getSelectedIndex() < 1) {
//            JOptionPane.showMessageDialog(this, "Seleccione un perfil de la lista de filtrado.", "Error", JOptionPane.ERROR_MESSAGE);
//        } else {
//            JOptionPane.showMessageDialog(this, "Seleccione un cliente de la lista de filtrado.", "Error", JOptionPane.ERROR_MESSAGE);
//        }*/

    }//GEN-LAST:event_jButton2ActionPerformed

    void genReportCobro(String id) {
        try {

            Map parametros = new HashMap();

            String fix = "", ffx = "";

            if (FechaFiltro.isSelected()) {
                fix = fi.getText();
                ffx = ff.getText();
            } else {
                fix = " - ";
                ffx = " - ";
            }

            String perfil = "0";
            if (perfilBuscador.getSelectedIndex() > 0) {
                perfil = perfilBuscadorID.get(perfilBuscador.getSelectedIndex());
            } else {
                perfil = "0";
            }

            parametros.put("yard", BoxPatios.getSelectedItem().toString());
            parametros.put("customer", BoxCliente.getSelectedItem().toString());
            parametros.put("from", fix);
            parametros.put("to", ffx);
            parametros.put("perfilID", perfil);

            String q = QUERYAMPL;

            System.out.println(q);
            JRDesignQuery query = new JRDesignQuery();
            query.setText(q);

            //JasperDesign jd = JRXmlLoader.load("src\\constructora\\Reporte_Transferencia.jrxml");
            /*String source = getClass().getResource("Almacen_Produccion_Filtrado.jrxml").toString();
             System.out.println(source);*/
            JasperDesign jd = JRXmlLoader.load("C:\\SERVER\\TransportesNOE\\dist\\ReporteInventarioAMPL.jrxml");
            jd.setQuery(query);

            Connection con = utils.startConnection();
            JasperReport reporte = (JasperReport) JasperCompileManager.compileReport(jd);//(JasperReport) JRLoader.loadObject(new File("Reporte_Transferencias.jasper"));

            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, con);

            //utils.windowJasper(jasperPrint);
            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            boolean folderExist = new File("C:\\SERVER\\TransportesNOE\\dist\\AMPLCobros\\").exists();
            if (!folderExist) {
                Boolean folder = (new File("C:\\SERVER\\TransportesNOE\\dist\\AMPLCobros\\")).mkdir();
                if (folder) {
                    System.out.println("Folder creado con exito");
                }
            }
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(global.path + "AMPLCobros\\" + id + "" + ".pdf"));
            exporter.exportReport();


            /*if (Desktop.isDesktopSupported()) {
             try {
             File myFile = new File(global.path + "AMPLCobros\\" +id+""+".pdf"); 
             utils.dbUpdate("update inventarioexterno_tbl_facturacion set reporteCobro='"++"' where factID='"+id+"'")
             } catch (IOException ex) {} }*/
        } catch (JRException ex) {
            Logger.getLogger(ItinerarioGuardiasContExternos2.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error " + ex + "\n" + ex.getStackTrace()[0], "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void anexo_add10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_anexo_add10MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_anexo_add10MouseEntered

    private void anexo_add10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_anexo_add10MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_anexo_add10MouseExited

    private void anexo_add10MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_anexo_add10MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_anexo_add10MousePressed

    private void anexo_add10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anexo_add10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_anexo_add10ActionPerformed

    private void anexo_del10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_anexo_del10MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_anexo_del10MouseEntered

    private void anexo_del10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_anexo_del10MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_anexo_del10MouseExited

    private void anexo_del10MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_anexo_del10MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_anexo_del10MousePressed

    private void anexo_del10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anexo_del10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_anexo_del10ActionPerformed

    private void plus16MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plus16MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_plus16MouseEntered

    private void plus16MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plus16MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_plus16MouseExited

    private void plus16MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plus16MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_plus16MousePressed

    private void plus16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plus16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_plus16ActionPerformed

    private void plus17MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plus17MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_plus17MouseEntered

    private void plus17MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plus17MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_plus17MouseExited

    private void plus17MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plus17MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_plus17MousePressed

    private void plus17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plus17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_plus17ActionPerformed

    private void Pagos19MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Pagos19MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_Pagos19MouseEntered

    private void Pagos19MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Pagos19MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_Pagos19MouseExited

    private void Pagos19MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Pagos19MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Pagos19MousePressed

    private void Pagos19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Pagos19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Pagos19ActionPerformed

    private void FacturacionWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_FacturacionWindowGainedFocus
        // TODO add your handling code here:
    }//GEN-LAST:event_FacturacionWindowGainedFocus

    private void facturacion_tblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_facturacion_tblMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_facturacion_tblMouseClicked

    private void facturacion_tblKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_facturacion_tblKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_facturacion_tblKeyPressed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        cargarFacturacion();
        Facturacion.setLocationRelativeTo(this);
        Facturacion.setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        if (facturacion_tbl.getSelectedRow() >= 0) {
//           FacturasCobros frame = new FacturasCobros();
//           frame.txtBusqueda.setText(facturacion_tbl.getValueAt(facturacion_tbl.getSelectedRow(), 2).toString());
//           frame.cargarTablaBus();
//           frame.setVisible(true);
//           Facturacion.setVisible(false);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void CamionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CamionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CamionActionPerformed

    private void jCheckBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox3ItemStateChanged
        cargarTabla();
    }//GEN-LAST:event_jCheckBox3ItemStateChanged

    private void bAgregarDocs2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAgregarDocs2ActionPerformed
        try {

            empezarHilo();
        } catch (Exception e) {
            if (global.usuario == 1) {

                //separarPDFs();
                empezarHilo();
            }
        }
    }//GEN-LAST:event_bAgregarDocs2ActionPerformed

    private void empezarHilo() {
        Thread go = new Thread() {
            public void run() {
                String err = "";
                try {
                    String documentspath = new JFileChooser().getFileSystemView().getDefaultDirectory().toString();

                    if (!new File(documentspath + "\\TSSdocs").exists()) {
                        new File(documentspath + "\\TSSdocs").mkdir();
                    }

                    int i = 0;
                    boolean ready = false;
                    File scandir = new File(documentspath + "\\TSSdocs");

                    while (i < 10 && !ready) {
                        File files[] = scandir.listFiles();

                        if (files.length > 0) {
                            for (int it = 0; it < files.length; it++) {
                                if (files[i].isFile()) {
                                    if (!files[i].isHidden()) {
                                        if (files[i].canRead()) {
                                            try {
                                                //

                                                if (jCheckBox4.isSelected()) {
                                                    ArrayList<File> splitdocs = utils.splitPDF(files[i]);

                                                    for (int j = 0; j < splitdocs.size(); j++) {

                                                        String id = utils.dbInsert("INSERT INTO inventarioexterno_archivos (inventarioID, NombreArchivo, Concepto, Archivo) VALUES('" + SelectedInventarioID + "', "
                                                                + "'" + splitdocs.get(j).getName() + "', '','" + FileUtils.getFileExtPart(files[i].getName(), true) + "')");

                                                        if (id.length() > 11) {
                                                            //utils.errorGenerado("DocsWO / Scans / " + id);
                                                            //utils.errorGenerado("archivospatios/ " + id);
                                                        } else {
                                                            //File destino = new File(global.path + "archivospatios\\" + id + FileUtils.getFileExtPart(files[i].getName(), true));

                                                            if (!FileUtils.copyFile(splitdocs.get(j), new File(global.path + "archivospatios\\" + id + ".pdf"))) {
                                                                //utils.dbUpdate("UPDATE workcfiles_tbl SET Status = false where FileID = '" + id + "'");
                                                                utils.dbUpdate("UPDATE inventarioexterno_archivos SET Fecha = null,Status = false where ArchivoID = '" + id + "'");
                                                                //utils.errorGenerado("DocsWO / Scans / error al copiar archivo");
                                                                utils.errorGenerado("archivospatios/ error al copiar archivo");
                                                            } else {
                                                                //utils.dbUpdate("UPDATE workcfiles_tbl SET APath = '" + utils.FiletoDB(global.carpetaevidencias + id + ".pdf") + "' where FileID = '" + id + "'");
                                                                //JOptionPane.showMessageDialog(dArchivos, "Error " + id, "Error", JOptionPane.ERROR_MESSAGE);
                                                            }
                                                        }

                                                        splitdocs.get(j).delete();

                                                        //for (int j = 0; j < splitdocs.size(); j++) {
                                                        /*String id = utils.dbInsert("INSERT INTO workcfiles_tbl (WContID, ItinerarioID, Nombre, Archivo, Extension, APath, UsuarioID) "
                                                         + "VALUES(" + qcontenedor + " , " + qitinerario + ",'" + splitdocs.get(j).getName() + "', '" + FileUtils.getFileNamePart(splitdocs.get(j)) + "', 'pdf', '', '" + global.usuario + "')");*/
 /*if (id.length() < 11 && !id.isEmpty()) {
                                                         File destino = new File(global.path + "archivospatios\\" + id + FileUtils.getFileExtPart(archivo.toString(), true));
                                                         if (!utils.copyFile(archivo, destino, false)) {
                                                         utils.dbUpdate("UPDATE inventarioexterno_archivos SET Fecha = null,Status = false where ArchivoID = '" + id + "'");
                                                         }
                                                         cargarArchivosChofer(choferid.get(jTable1.getSelectedRow()));
                                                         } else {
                                                         JOptionPane.showMessageDialog(dArchivos, "Error " + id, "Error", JOptionPane.ERROR_MESSAGE);
                                                         }*/
                                                        //splitdocs.get(j).delete();//System.out.println
                                                    }

                                                } else {

                                                    String id = utils.dbInsert("INSERT INTO inventarioexterno_archivos (inventarioID, NombreArchivo, Concepto, Archivo) VALUES('" + SelectedInventarioID + "', "
                                                            + "'" + files[i].getName() + "', '','" + FileUtils.getFileExtPart(files[i].getName(), true) + "')");

                                                    if (id.length() > 11) {
                                                        //utils.errorGenerado("DocsWO / Scans / " + id);
                                                        //utils.errorGenerado("archivospatios/ " + id);
                                                    } else {
                                                        File destino = new File(global.path + "archivospatios\\" + id + FileUtils.getFileExtPart(files[i].getName(), true));
                                                        if (!utils.copyFile(files[i], destino, false)) {
                                                            //utils.dbUpdate("UPDATE workcfiles_tbl SET Status = false where FileID = '" + id + "'");
                                                            utils.dbUpdate("UPDATE inventarioexterno_archivos SET Fecha = null,Status = false where ArchivoID = '" + id + "'");
                                                            //utils.errorGenerado("DocsWO / Scans / error al copiar archivo");
                                                            utils.errorGenerado("archivospatios/ error al copiar archivo");
                                                        } else {
                                                            //utils.dbUpdate("UPDATE workcfiles_tbl SET APath = '" + utils.FiletoDB(global.carpetaevidencias + id + ".pdf") + "' where FileID = '" + id + "'");
                                                            //JOptionPane.showMessageDialog(dArchivos, "Error " + id, "Error", JOptionPane.ERROR_MESSAGE);
                                                        }
                                                    }

                                                }

                                                if (files[i].exists()) {
                                                    //System.out.println(files[i].delete());
                                                    files[i].delete();
                                                }
                                            } catch (Exception ex) {
                                                utils.errorGenerado("archivospatios / Scans / ex " + ex.toString());
                                            }

                                        }
                                    }
                                }
                                ready = true;
                            }
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            utils.errorGenerado("archivospatios / Scans / interrupted ex " + ex.toString());
                        }
                        i++;
                    }

                    //cargarArchivos();
                    cargarArchivosChofer(SelectedInventarioID);

                } catch (Exception e) {
                    err = err + "\nERROR EXCEPTION: " + e.toString();
                }
            }
        };
        go.start();
    }

    private void bAgregarDocs1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAgregarDocs1ActionPerformed
        try {
            String runtsscan = utils.runProgram("C:\\Program Files (x86)\\TerminalWorks\\TSScan Server\\TSScan.exe");
            if (runtsscan.isEmpty()) {
                String documentspath = new JFileChooser().getFileSystemView().getDefaultDirectory().toString();
                File scandir = new File(documentspath + "\\TSSdocs");

                File delfiles[] = scandir.listFiles();
                for (int idel = 0; idel < delfiles.length; idel++) {
                    delfiles[idel].delete();
                }
                JOptionPane.showMessageDialog(this, "Favor de terminar el proceso en la ventana emergente, antes de continuar aqui.", "Mensaje", JOptionPane.WARNING_MESSAGE);
                File files[] = scandir.listFiles();
                if (files.length == 0) {
                    JOptionPane.showMessageDialog(this, "No se encontraron escaneos, favor de intentarlo de nuevo", "Mensaje", JOptionPane.ERROR_MESSAGE);
                } else {
                    empezarHilo();
                }
                //separarPDFs();

            } else {
                if (global.usuario == 1) {

                    //separarPDFs();
                    empezarHilo();
                }
            }
        } catch (Exception e) {
            if (global.usuario == 1) {

                //separarPDFs();
                empezarHilo();
            }
        }
        //Runtime.getRuntime().exec("cmd /c start C:\\Program Files (x86)\\TerminalWorks\\TSScan Server\\TSScan.exe");
    }//GEN-LAST:event_bAgregarDocs1ActionPerformed

    private void bAgregarDocs3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAgregarDocs3ActionPerformed

        if (jTable9.getSelectedRow() >= 0) {

            int option = JOptionPane.showConfirmDialog(dArchivos, "Desea eliminar el archivo seleccionado?", "Error", JOptionPane.YES_NO_OPTION);
            if (option == 0) {

                int rows[] = jTable9.getSelectedRows();
                for (int i = 0; i < rows.length; i++) {
                    utils.dbUpdate("update inventarioexterno_archivos set status=false where archivoID = '" + archivoc.get(rows[i]) + "'");
                }

                /*String update = utils.dbUpdate("update archivoschoferes_tbl set status=false where archivoID = '" + archivoc.get(jTable9.getSelectedRow()) + "'");

                 if (!update.equals("")) {
                 JOptionPane.showMessageDialog(dArchivos, "Error " + update, "Error", JOptionPane.ERROR_MESSAGE);
                 }*/
                cargarArchivosChofer(SelectedInventarioID);
            }

        } else {
            JOptionPane.showMessageDialog(dArchivos, "Seleccione un registro ", "Error", JOptionPane.YES_NO_OPTION);
        }
    }//GEN-LAST:event_bAgregarDocs3ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed

        if (!SelectedInventarioID.equals("0")) {
            cargarArchivosChofer(SelectedInventarioID);
            dArchivos.setLocationRelativeTo(dEntradaSalida);
            dArchivos.setVisible(true);
        } else {
            //JOptionPane.showMessageDialog(dEntradaSalida, "Guarde primero, antes de poder adjuntar archivos.", "Error", JOptionPane.YES_NO_OPTION);

            String TempAnteriorID = "";

            if (!txtContenedor.getText().equals("") && Orgn.getSelectedIndex() > 0 && !placasunidad.getText().equals("") && !NCHASIS.getText().equals("")) {

                try {
                    String fsalida = null, fllegada = null, usuariosalida = "0", usuariollegada = "0", patiousuario = utils.dbConsult("SELECT PatioID from usuarios_tbl where UsuarioID = '" + global.usuario + "'");
                    boolean newllegada = false;
                    if (utils.validarDATE(txtFLlegada2.getText()) && utils.dbConsult("SELECT digits('" + txtHLlegada.getText() + "')").length() == 4) {
                        fllegada = "'" + utils.dateFromFieldtoDBwoH(txtFLlegada2.getText()) + " " + txtHLlegada.getText() + ":00'";
                        newllegada = true;
                        usuariollegada = global.usuario + "";
                    }
                    if (invid.equals("0")) {
                        String cont = utils.dbConsult("SELECT IFNULL((SELECT IF( '" + InOut.getSelectedItem().toString() + "'='Entrada', IF(AnteriorID=0,InventarioID,0), IF(AnteriorID=0,0,InventarioID)  ) "
                                + "from inventarioexterno_tbl where Contenedor = RTRIM('" + txtContenedor.getText() + "') and Status = 1 "
                                + "and patioID='" + utils.dbConsult("select patioID from usuarios_Tbl where usuarioID='" + global.usuario + "'") + "' "
                                + "order by inventarioID desc limit 1 "
                                //+ "TipoEvento=IF('"+InOut.getSelectedItem().toString()+"'='Entrada',1,2) and "
                                + " ),0)");

                        System.out.println("SELECT IFNULL((SELECT IF( '" + InOut.getSelectedItem().toString() + "'='Entrada', IF(AnteriorID=0,InventarioID,0), IF(AnteriorID=0,0,InventarioID)  ) "
                                + "from inventarioexterno_tbl where Contenedor = RTRIM('" + txtContenedor.getText() + "') and Status = 1 "
                                + "and patioID='" + utils.dbConsult("select patioID from usuarios_Tbl where usuarioID='" + global.usuario + "'") + "' "
                                + "order by inventarioID desc limit 1 "
                                //+ "TipoEvento=IF('"+InOut.getSelectedItem().toString()+"'='Entrada',1,2) and "
                                + " ),0)");

                        if (cont.equals("0")) {

                            TempAnteriorID = utils.dbConsult(" select inventarioID "
                                    + "from inventarioexterno_tbl where contenedor = '" + txtContenedor.getText() + "' and TipoEvento=1 "
                                    + "and patioID='" + utils.dbConsult("select patioID from usuarios_Tbl where usuarioID='" + global.usuario + "'") + "' order by inventarioID desc limit 1 ");

                            String id = utils.dbInsert("INSERT INTO inventarioexterno_tbl (Contenedor, PlacasChasis,  Sello, PaisID, EstadoID,EstadoUSAID, ChoferID, NombreChofer, FechaEvento, " //PlacasChasisUSA,
                                    + "UsuarioEventoID, Nota, EstadoCarga, UsuarioID, Fecha, Tamano,clienteID, WContenedorID, TipoEvento, AnteriorID, camion, EquipmentProvider, Origen, camionID, botando,"
                                    + "PlacasUnidad, PlacasUnidadUSA, Destino, NumeroChasis, Carrier, patioID, grade, assignedto, fechaedicion, isCamion,EstadoPlacasMex, EstadoPlacasUsa,remolquePais, remolqueEstado) "
                                    + "VALUES(RTRIM('" + txtContenedor.getText() + "'), '" + txtPlacasChasis.getText() + "', '" + txtSello.getText() + "', " //'" + txtPlacasChasis1.getText() + "', 
                                    + "'" + (boxPaisPlacas.getSelectedIndex() + 1) + "', '" + estadosid.get(boxEstadoPlacas.getSelectedIndex()) + "', '" + estadosid1.get(boxEstadoPlacas1.getSelectedIndex()) + "'"
                                    + ",'" + chofereslocalid.get(boxChofer.getSelectedIndex()) + "', '" + txtNombreChofer.getText() + "', " + fllegada + ", "
                                    + "'" + usuariollegada + "', '" + txtNota.getText() + "', '" + boxEstadoCarga.getSelectedIndex() + "', '" + global.usuario + "'"
                                    + ", (now()),  '" + boxTamano.getSelectedItem() + "','" + clienteid.get(cliente.getSelectedIndex()) + "', '" + WContID + "', "
                                    + "IF('" + InOut.getSelectedItem().toString() + "'='Entrada',1,2), IF('" + InOut.getSelectedItem().toString() + "'='Entrada',0,IF('" + AnteriorID + "'+0=0,'" + TempAnteriorID + "','" + AnteriorID + "')), "
                                    + "'" + camiones.getText() + "', '" + cliente1id.get(cliente1.getSelectedIndex()) + "', '" + Orgn.getSelectedItem().toString() + "', '" + camionidx.get(camionx.getSelectedIndex()) + "', "
                                    + "" + botando.isSelected() + ", '" + placasunidad.getText() + "', '" + placasunidad1.getText() + "', '" + destino.getText() + "', '" + NCHASIS.getText() + "', '" + CARRIER.getText() + "', "
                                    + "IF('" + PatioID.get(Patio.getSelectedIndex()) + "'+0>0,'" + PatioID.get(Patio.getSelectedIndex()) + "',0), '" + grade.getSelectedItem().toString() + "', "
                                    + "'" + assignedto.getText() + "', now(), " + Camion.isSelected() + ", '" + placasunidad2.getText() + "', '" + placasunidad3.getText() + "', "
                                    + "'" + paisx.getSelectedItem().toString() + "','" + estxid.get(estx.getSelectedIndex()) + "')");

                            SelectedInventarioID = id;

                            //if(InOut.getSelectedItem().toString().equals("Salida")) utils.dbConsult("UPDATE inventarioexterno_tbl set AnteriorID='"+id+"' where inventarioID='"+AnteriorID+"'");
                            if (id.length() <= 11) {
                                cargarTabla();
                                dEntradaSalida.dispose();

                                invid = SelectedInventarioID;
                                System.out.println("ESTE ES EL ID ACTUAL: " + SelectedInventarioID);
                                cargarEntradaSalida2();
                                dEntradaSalida.setLocationRelativeTo(this);
                                dEntradaSalida.setVisible(true);

                                /*dEntradaSalida.setAlwaysOnTop(false);

                                 cargarArchivosChofer(SelectedInventarioID);
                                 dArchivos.setAlwaysOnTop(true);
                                 dArchivos.setLocationRelativeTo(dEntradaSalida);
                                 dArchivos.setVisible(true);*/
                            } else {
                                JOptionPane.showMessageDialog(dEntradaSalida, id, "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(dEntradaSalida, "Este contenedor ya existe en el inventario favor de revisar su listado.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {

                        /*String cont = utils.dbConsult("SELECT IFNULL((SELECT InventarioID from inventarioexterno_tbl where Contenedor = RTRIM('" + txtContenedor.getText() + "') and Status = 1 and "
                         + "InventarioID != '" + invid + "' and TipoEvento=IF('"+InOut.getSelectedItem().toString()+"'='Entrada',1,2)),0)");*/
                        String cont = invid;/*utils.dbConsult("SELECT IFNULL((SELECT InventarioID from inventarioexterno_tbl where Contenedor = RTRIM('" + txtContenedor.getText() + "') and Status = 1 and "
                         + "InventarioID != '" + invid + "' and TipoEvento=IF('"+InOut.getSelectedItem().toString()+"'='Entrada',1,2)),0)");*/

                        System.out.println("CONT:" + cont);

                        if (!cont.equals("0")) {
                            String up = utils.dbUpdate("UPDATE inventarioexterno_tbl SET "
                                    + "Contenedor = RTRIM('" + txtContenedor.getText() + "'), " //PlacasChasisUSA = '" + txtPlacasChasis1.getText() + "', 
                                    + "PlacasChasis = '" + txtPlacasChasis.getText() + "', Sello = '" + txtSello.getText() + "', PaisID = '" + (boxPaisPlacas.getSelectedIndex() + 1) + "', "
                                    + "EstadoID = '" + estadosid.get(boxEstadoPlacas.getSelectedIndex()) + "', EstadoUSAID = '" + estadosid1.get(boxEstadoPlacas1.getSelectedIndex()) + "' "
                                    + ", ChoferID = '" + chofereslocalid.get(boxChofer.getSelectedIndex()) + "', NombreChofer = '" + txtNombreChofer.getText() + "', Nota = '" + txtNota.getText() + "', EstadoCarga = '" + boxEstadoCarga.getSelectedIndex() + "'"
                                    + ", Tamano = '" + boxTamano.getSelectedItem() + "', clienteID='" + clienteid.get(cliente.getSelectedIndex()) + "', WContenedorID='" + WContID + "', "
                                    + "TipoEvento=IF('" + InOut.getSelectedItem().toString() + "'='Entrada',1,2),  " //AnteriorID=IF('"+InOut.getSelectedItem().toString()+"'='Entrada',0,'"+AnteriorID+"'),
                                    + " camion='" + camiones.getText() + "', EquipmentProvider='" + cliente1id.get(cliente1.getSelectedIndex()) + "', Origen='" + Orgn.getSelectedItem().toString() + "', "
                                    + "camionID='" + camionidx.get(camionx.getSelectedIndex()) + "', botando=" + botando.isSelected() + ", PlacasUnidad='" + placasunidad.getText() + "', PlacasUnidadUSA='" + placasunidad1.getText() + "', Destino='" + destino.getText() + "', "
                                    + "NumeroChasis='" + NCHASIS.getText() + "', Carrier='" + CARRIER.getText() + "', FechaEvento=" + fllegada + ", "
                                    + "patioID=IF('" + PatioID.get(Patio.getSelectedIndex()) + "'+0>0,'" + PatioID.get(Patio.getSelectedIndex()) + "',0), grade='" + grade.getSelectedItem().toString() + "', "
                                    + "assignedto='" + assignedto.getText() + "', UsuarioEventoID='" + usuariollegada + "', fechaedicion=now(), isCamion=" + Camion.isSelected() + ",  "
                                    + "EstadoPlacasMex= '" + placasunidad2.getText() + "', EstadoPlacasUsa='" + placasunidad3.getText() + "', "
                                    + "remolquePais='" + paisx.getSelectedItem().toString() + "', remolqueEstado='" + estxid.get(estx.getSelectedIndex()) + "' "
                                    + "where InventarioID = '" + invid + "' ");

                            SelectedInventarioID = invid;

                            if (up.isEmpty()) {
                                invid = "0";
                                cargarTabla();
                                dEntradaSalida.dispose();
                            } else {
                                JOptionPane.showMessageDialog(dEntradaSalida, up, "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(dEntradaSalida, e, "Error", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(dEntradaSalida, "Asegurese de haber capturado contenedor, placas, numero de chasis y origen", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void boxEstadoCargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxEstadoCargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boxEstadoCargaActionPerformed

    private void yvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yvActionPerformed
        cargarAccesos();
        AltaYarda.setLocationRelativeTo(this);
        AltaYarda.setVisible(true);
    }//GEN-LAST:event_yvActionPerformed

    private void taccesosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taccesosMouseClicked

    }//GEN-LAST:event_taccesosMouseClicked

    private void taccesosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_taccesosKeyPressed

    }//GEN-LAST:event_taccesosKeyPressed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed

        if (taccesos.getSelectedRow() >= 0) {

            utils.dbUpdate("DELETE FROM patios_tbl where patioID='" + accesoid.get(taccesos.getSelectedRow()) + "'");
            cargarAccesos();
            cargarPatios(BoxPatios, patiosid, "Todos");
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un elemento de la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        utils.dbInsert("INSERT INTO patios_tbl (`Codigo`, `Nombre`, `Direccion`, `DireccionCorta`, `TipoPatio`, `Status`, `MostrarLFD`, vt) "
                + "VALUES ('" + pwd.getText() + "', '" + pwd.getText() + "', ' ', ' ', 'TJ', true, false, true)");
        cargarAccesos();
        cargarPatios(BoxPatios, patiosid, "Todos");
    }//GEN-LAST:event_jButton12ActionPerformed

    private void perfilBuscador1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_perfilBuscador1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_perfilBuscador1ItemStateChanged

    private void perfilBuscador1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_perfilBuscador1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_perfilBuscador1ActionPerformed

    private void clienteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_clienteItemStateChanged
        if (cliente.getSelectedIndex() > 0) {
            cargarPerfilesRegistro();
        }
    }//GEN-LAST:event_clienteItemStateChanged

    private void txtContenedorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContenedorKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContenedorKeyReleased

    private void InOutItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_InOutItemStateChanged
        if (InOut.getSelectedIndex() == 1) {
            jCheckBox6.setVisible(true);
            icont_contenedor.setVisible(true);
        } else {
            jCheckBox6.setVisible(false);
            icont_contenedor.setVisible(false);
        }

        /*String airway="";
         if(Patio.getItemAt(PatioID.indexOf(utils.dbConsult("select patioID from usuarios_tbl where usuarioID='"+global.usuario+"'"))).equals("AIRWAY")) airway="AIR";
         else airway = Patio.getItemAt(PatioID.indexOf(utils.dbConsult("select patioID from usuarios_tbl where usuarioID='"+global.usuario+"'")));
        
         if(InOut.getSelectedIndex()==0) {destino.setText(Patio.getItemAt(PatioID.indexOf(utils.dbConsult("select patioID from usuarios_tbl where usuarioID='"+global.usuario+"'")))); Orgn.setSelectedIndex(0);}
         else { Orgn.setSelectedItem(airway); destino.setText(""); }*/
    }//GEN-LAST:event_InOutItemStateChanged

    private void jCheckBox6ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox6ItemStateChanged
        /*if(jCheckBox6.isSelected()) cargarEntradas();
         else cargarEntradasFiltradoDriver();*/

    }//GEN-LAST:event_jCheckBox6ItemStateChanged

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed

        if (facturacion_tbl.getSelectedRow() >= 0) {

            if (Desktop.isDesktopSupported()) {
                try {
                    File myFile = new File(global.path + "AMPLCobros\\" + facturacionID.get(facturacion_tbl.getSelectedRow()) + "" + ".pdf");
                    if (myFile.exists()) {
                        Desktop.getDesktop().open(myFile);
                    }
                    //System.out.println(TKTtablemodel1ID.get(TKT1.getSelectedRow())+""+"");
                } catch (IOException ex) {
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una serie de la lista.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton11ActionPerformed

    private void txtContenedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContenedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContenedorActionPerformed

    private void txtDriverMoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDriverMoveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDriverMoveActionPerformed

    void cargarFullSeleccionSalida(String itinerarioid) {

        String query = "select icontID,ncontenedor from icont_tbl where ItinerarioID='" + itinerarioid + "'";

        icont_contenedor.removeAllItems();
        icontID.clear();

        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                icont_contenedor.addItem(rs.getString("ncontenedor"));
                icontID.add(rs.getString("icontID"));
            }

        } catch (SQLException e) {
            utils.errorGenerado("fillcombo / " + query + " / sqlex = " + e.toString());
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    private void txtDriverMoveKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDriverMoveKeyPressed

        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            bandLoad = false;

            loadDriverMove();
        }
    }//GEN-LAST:event_txtDriverMoveKeyPressed

    void loadDriverMove() {
        String contenedor, cajaxchassis2, estadoc, Full = "";

        if (bandLoad == false) {
            cargarFullSeleccionSalida(txtDriverMove.getText());
        }

        System.out.println("DM: " + txtDriverMove.getText());
        if (!txtDriverMove.getText().equals("")) {

            String valor = txtDriverMove.getText();
            resetter();
            txtDriverMove.setText(valor);

            WContID = "0";
            AnteriorID = "0";
            InventarioSalidaID = "0";
            ItiID = "0";

            String order = " asc ";
            if (InOut.getSelectedIndex() == 1
                    && utils.dbConsult("select IF(count(itinerarioID)>1,'Si','No') from icont_tbl where ItinerarioID='" + txtDriverMove.getText() + "' and status=1").equals("Si")
                    && icont_contenedor.getSelectedIndex() > 0) {
                order = " desc ";
            } else {
                order = " asc ";
            }

            String DrvMV = utils.dbConsult("select WContID from icont_tbl where ItinerarioID='" + txtDriverMove.getText() + "' and status=1  order by icontID " + order + " limit 1");

            if (!DrvMV.equals("")) {

                Full = utils.dbConsult("select IF(count(itinerarioID)>1,'Si','No') from icont_tbl where ItinerarioID='" + txtDriverMove.getText() + "' and status=1");
                if (Full.equals("Si")) {
                    Fullx.setVisible(true);
                } else {
                    Fullx.setVisible(false);
                }

                System.out.println("Full " + Full);

                //if( InOut.getSelectedItem().toString().equals("Entrada") ) {
                WContID = DrvMV;
                ItiID = txtDriverMove.getText();
                System.out.println(DrvMV);

                contenedor = utils.dbConsult("select ncontenedor from icont_tbl where ItinerarioID='" + txtDriverMove.getText() + "' and status=1  order by icontID " + order + " limit 1");
                cajaxchassis2 = utils.dbConsult("select caja from icont_tbl where ItinerarioID='" + txtDriverMove.getText() + "' and status=1  order by icontID " + order + " limit 1");
                //cajaxchassis = utils.dbConsult("select cajaID from workcontenedores_tbl where WContenedorID='" + DrvMV + "'"); //
                estadoc = utils.dbConsult("Select Estado from icont_tbl where itinerarioID='" + ItiID + "' and status=1 order by icontID " + order + " limit 1");

                if (contenedor.equals("")) {
                    contenedor = utils.dbConsult("select contenedor  from workcontenedores_tbl where WContenedorID='" + WContID + "'");
                }

                camionx.setSelectedIndex(camionidx.indexOf(utils.dbConsult("select camionID from camiones_tbl where camionID=(select camionID from itinerarios_tbl where  ITINERARIOID='" + ItiID + "' )"))); //WcontFK='" + WContID + "' ANDorder by itinerarios_tbl.ItinerarioID desc limit 1

                txtContenedor.setText(contenedor);
                cajachasisboxExterno.setText(cajaxchassis2);

                try {


                    /*if (solochasis.isSelected()) {
                            contenedor = utils.dbConsult("select ncontenedor  from workcontenedores_tbl where WContenedorID='" + WContID + "'");
                            if (contenedor.equals("")) {
                                contenedor = utils.dbConsult("select Caja from icont_tbl where itinerarioID='" + ItiID + "'");
                            }
                        } else {
                            contenedor = utils.dbConsult("select Contenedor from workcontenedores_tbl where WContenedorID='" + DrvMV + "'");
                        }*/
                    //cajachasisbox.setSelectedIndex(cajachasisboxID.indexOf(cajaxchassis));
                    statz.setText("Estatus: " + utils.dbConsult("SELECT estatus_caja from cajas_tbl where noeconomico = '" + contenedor + "' limit 1"));

                    /*if (!utils.dbConsult("select cajaID from cajas_tbl where noeconomico='" + contenedor + "'").equals("")) {
                        cliente1.setSelectedIndex(cliente1id.indexOf((utils.dbConsult("select clienteID from clientes_tbl where ncomercial='dfc' limit 1"))));
                    } else {*/
                    cliente1.setSelectedIndex(cliente1id.indexOf((utils.dbConsult("select clienteFK from workcontenedores_tbl where WContenedorID='" + WContID + "'"))));
                    //}
                    //cliente.setSelectedIndex(clienteid.indexOf((utils.dbConsult("select LocacionTOID from rutas_tbl where rutas_tbl.RutaID =(select RutaID from workcontenedores_tbl where workcontenedores_tbl.WContenedorID='"+WContID+"')"))));

                    System.out.println("select camionID from camiones_tbl where camionID=(select camionID from itinerarios_tbl where  ITINERARIOID='" + ItiID + "' )"); //WcontFK='" + WContID + "' AND
                    //String consulta = utils.dbConsult("select IFNULL(clienteAMPLID,'') from clientes_tbl where clienteID=(select clienteID from workorder_tbl where workID=(select WorkOrderID from workcontenedores_tbl where WContenedorID='"+WContID+"'))");
                    //if(consulta.equals("")) 
                    cliente.setSelectedIndex(clienteid.indexOf((utils.dbConsult("select clienteFK from workcontenedores_tbl where WContenedorID='" + WContID + "'"))));

                    //else cliente.setSelectedIndex(clienteid.indexOf(consulta));
                    /*String foxFilter="";
                         foxFilter = utils.dbConsult("select clienteID from workorder_tbl where workID=(select WorkOrderID from workcontenedores_tbl where WContenedorID='"+WContID+"')");
                         if(!foxFilter.equals("") && !utils.dbConsult("select * from clientes_tbl where razonsocial like '%foxc%' and clienteID='"+foxFilter+"' limit 1 ").equals("") && 
                         utils.dbConsult("SELECT Destino from rutas_tbl where rutas_tbl.RutaID = (select RutaID from itinerarios_tbl where WcontFK='"+WContID+"' and Status = 1 order by itinerarios_tbl.ItinerarioID desc limit 1)").equals("YARDA5") &&   
                         utils.dbConsult("Select IF(Estado=0,'Vacio','Cargado') from itinerarios_tbl where WcontFK='"+WContID+"'  order by itinerarios_tbl.ItinerarioID desc limit 1 ").equals("Vacio")   ) {
                         cliente1.setSelectedIndex(cliente1id.indexOf("81"));
                         }*/
                    placasunidad.setText(utils.dbConsult("select placas from camiones_Tbl where camionID='" + utils.dbConsult("select camionID from camiones_tbl where camionID=(select camionID from itinerarios_tbl where ITINERARIOID='" + ItiID + "')") + "'")); //WcontFK='" + WContID + "' AND order by itinerarios_tbl.ItinerarioID desc limit 1
                    placasunidad1.setText(utils.dbConsult("select placasusa from camiones_Tbl where camionID='" + utils.dbConsult("select camionID from camiones_tbl where camionID=(select camionID from itinerarios_tbl where  ITINERARIOID='" + ItiID + "' )") + "'")); //WcontFK='" + WContID + "' ANDorder by itinerarios_tbl.ItinerarioID desc limit 1

                    txtPlacasChasis.setText(utils.dbConsult("select IF('" + paisx.getSelectedItem().toString().equals("MEX") + "',placas,placasusa) from cajas_tbl where cajaID='" + utils.dbConsult("select cajaID from workcontenedores_tbl where WContenedorID='" + WContID + "'") + "'"));
                    //txtPlacasChasis1.setText(utils.dbConsult("select placasusa from cajas_tbl where cajaID='"+utils.dbConsult("select cajaID from workcontenedores_tbl where WContenedorID='"+WContID+"'")+"'"));
                    NCHASIS.setText(utils.dbConsult("select ncontenedor  from workcontenedores_tbl where WContenedorID='" + WContID + "'"));
                    txtSello.setText(utils.dbConsult("select NumeroSello from workcontenedores_tbl where WContenedorID='" + WContID + "'"));

                    /*String pais = utils.dbConsult("select PaisID-1 from workcontenedores_tbl where WContenedorID='"+WContID+"'");
                         if(pais.equals("1")) boxPaisPlacas.setSelectedIndex(1);
                         else    boxPaisPlacas.setSelectedIndex(0);

                         String estado = utils.dbConsult("select EstadoID from workcontenedores_tbl where WContenedorID='"+WContID+"'");
                         String estadoUSA = utils.dbConsult("select EstadoUSAID from workcontenedores_tbl where WContenedorID='"+WContID+"'");
                    
                         if(estado.equals("") || estado.equals("0")) boxEstadoPlacas.setSelectedIndex(0);
                         else    boxEstadoPlacas.setSelectedIndex(estadosid.indexOf(estado));
                    
                         if(estadoUSA.equals("") || estadoUSA.equals("0")) boxEstadoPlacas1.setSelectedIndex(0);
                         else    boxEstadoPlacas1.setSelectedIndex(estadosid1.indexOf(estadoUSA));*/
                    placasunidad2.setText(utils.dbConsult("select EstadoPlacas from camiones_tbl where camionID='" + utils.dbConsult("select camionID from camiones_tbl where camionID=(select camionID from itinerarios_tbl where ITINERARIOID='" + ItiID + "' )") + "'")); // where WcontFK='" + WContID + "' AND order by itinerarios_tbl.ItinerarioID desc limit 1
                    placasunidad3.setText(utils.dbConsult("select EstadoPlacasUSA from camiones_tbl where camionID='" + utils.dbConsult("select camionID from camiones_tbl where camionID=(select camionID from itinerarios_tbl where  ITINERARIOID='" + ItiID + "' )") + "'")); // WcontFK='" + WContID + "' ANDorder by itinerarios_tbl.ItinerarioID desc limit 1

                    CARRIER.setText(utils.dbConsult("Select razonsocial from empresas_tbl where empresaID=(select empresaID from camiones_tbl where camionID='" + utils.dbConsult("select camionID from camiones_tbl where camionID=(select camionID from itinerarios_tbl where  ITINERARIOID='" + ItiID + "' )") + "')")); // WcontFK='" + WContID + "'  ANDorder by itinerarios_tbl.ItinerarioID desc limit 1

                    boxChofer.setSelectedIndex(chofereslocalid.indexOf(utils.dbConsult("SELECT choferID from choferes_tbl where choferID=(select choferID from itinerarios_tbl where  ITINERARIOID='" + ItiID + "')"))); //WcontFK='" + WContID + "'  AND order by itinerarios_tbl.ItinerarioID desc limit 1
                    txtNombreChofer.setText("");

                    licencia.setText(utils.dbConsult("SELECT licencia from choferes_tbl where choferID=(select choferID from itinerarios_tbl where  ITINERARIOID='" + ItiID + "')")); //WcontFK='" + WContID + "'  AND

                    txtFLlegada2.setText(utils.dbConsult("select DATE_FORMAT(now(), '" + global.fdatedb + "')"));
                    txtHLlegada.setText(utils.dbConsult("select DATE_FORMAT(now(), '%H:%i')"));
                    Orgn.setSelectedItem(utils.dbConsult("SELECT Origen from rutas_tbl where rutas_tbl.RutaID = (select RutaID from itinerarios_tbl where ITINERARIOID='" + ItiID + "' )")); //WcontFK='" + WContID + "' AND  order by itinerarios_tbl.ItinerarioID desc limit 1          //and Status = 1 
                    destino.setText(utils.dbConsult("SELECT Destino from rutas_tbl where rutas_tbl.RutaID = (select RutaID from itinerarios_tbl where  ITINERARIOID='" + ItiID + "' )"));     //WcontFK='" + WContID + "' AND order by itinerarios_tbl.ItinerarioID desc limit 1                    //and Status = 1 

                    boxEstadoCarga.setSelectedItem(utils.dbConsult("Select IF(Estado=0,'Vacio','Cargado') from icont_tbl where itinerarioID='" + ItiID + "' and status=1 order by icontID " + order + " limit 1   ")); //order by itinerarios_tbl.ItinerarioID desc limit 1 

                    boxTamano.setSelectedItem(utils.dbConsult("Select Tamano from workcontenedores_tbl where WContenedorID='" + WContID + "'"));

                    perfilBuscador1.setSelectedIndex(0);

                    if (txtPlacasChasis.getText().equals("")) {
                        txtPlacasChasis.setText(utils.dbConsult("select PlacasChasis from inventarioexterno_tbl where contenedor='" + txtContenedor.getText() + "' and NumeroChasis!='' and NumeroChasis is not null order by inventarioID desc limit 1"));
                    }
                    /*
                         if( txtPlacasChasis1.getText().equals("") )
                         txtPlacasChasis1.setText(utils.dbConsult("select PlacasChasisUSA from inventarioexterno_tbl where contenedor='"+txtContenedor.getText()+"' and NumeroChasis!='' and NumeroChasis is not null order by inventarioID desc limit 1"));
                     */

                    try {
                        paisx.setSelectedItem(utils.dbConsult("select remolquePais from inventarioexterno_tbl where contenedor='" + txtContenedor.getText() + "' order by inventarioID desc limit 1")); //and NumeroChasis!='' and NumeroChasis is not null 
                        estx.setSelectedIndex(estxid.indexOf((utils.dbConsult("select remolqueEstado from inventarioexterno_tbl where contenedor='" + txtContenedor.getText() + "' order by inventarioID desc limit 1")))); //and NumeroChasis!='' and NumeroChasis is not null 
                    } catch (Exception e) {
                    }

                    txtNota.setText("");
                    assignedto.setText("");
                    /*boxEstadoCarga.setSelectedIndex(0);*/
                } catch (Exception e) {
                }

                //}
                if (InOut.getSelectedItem().toString().equals("Salida")) {

                    contenedor = utils.dbConsult("select ncontenedor from icont_tbl where ItinerarioID='" + txtDriverMove.getText() + "' and status=1  order by icontID " + order + " limit 1");
                    String vrt = "", vt = "";
                    vrt = utils.dbConsult("select ifnull(vt,false) from patios_tbl where  patioID='" + PatioID.get(Patio.getSelectedIndex()) + "'");

                    if (vrt.equals("1")) {
                        vt = " and (select ifnull(vt,false) from patios_tbl where  patioID='" + PatioID.get(Patio.getSelectedIndex()) + "') is true ";
                    } else {
                        vt = " and patioID='" + PatioID.get(Patio.getSelectedIndex()) + "' ";
                    }

                    String drivermov = utils.dbConsult("select IFNULL((select itinerarioID from itinerarios_tbl where WcontFK=workcontenedores_tbl.WContenedorID AND ITINERARIOID='" + ItiID + "' ),'') as driverm \n"
                            + //order by itinerarios_tbl.ItinerarioID desc limit 1
                            "from workcontenedores_tbl where contenedor = '" + txtContenedor.getText() + "'  and `status`=1 \n"
                            + "and IFNULL((select ItinerarioID from itinerarios_tbl where WcontFK=workcontenedores_tbl.WContenedorID and itinerarios_tbl.Status=1 AND ITINERARIOID='" + ItiID + "' limit 1 ),'') > 0 "
                            + "AND (SELECT (SELECT PatioID from locaciones_tbl where `LocacionID` = LocacionPUID) from rutas_tbl where "
                            + "(select RutaID from itinerarios_tbl where WcontFK=workcontenedores_tbl.WContenedorID and itinerarios_tbl.Status=1 AND ITINERARIOID='" + ItiID + "' limit 1 ) = rutas_tbl.RutaID) = 1 "
                            + "limit 1");

                    String externo = utils.dbConsult("select inventarioID "
                            + "from inventarioexterno_tbl where contenedor = '" + txtContenedor.getText() + "' and anteriorID=0  and WContenedorID=0 and ItinerarioID=0 "
                            + " " + vt + " order by inventarioID desc limit 1");

                    String driverMoveInterno = "";
                    if (externo.equals("") && !drivermov.equals("") && !vrt.equals("1")) {
                        driverMoveInterno = " AND (SELECT (SELECT PatioID from locaciones_tbl where `LocacionID` = LocacionPUID) from rutas_tbl where "
                                + "(select RutaID from itinerarios_tbl where WcontFK=workcontenedores_tbl.WContenedorID and itinerarios_tbl.Status=1 AND ITINERARIOID='" + ItiID + "' limit 1 ) = rutas_tbl.RutaID) = '" + PatioID.get(Patio.getSelectedIndex()) + "' ";
                    } else {
                        driverMoveInterno = "";
                    }

                    String query = "select inventarioID "
                            + "from inventarioexterno_tbl where contenedor = '" + contenedor + "' and anteriorID=0  "
                            + " " + vt + " " + driverMoveInterno + " order by inventarioID desc limit 1";

                    InventarioSalidaID = utils.dbConsult("select inventarioID from inventarioexterno_tbl where contenedor = '" + txtContenedor.getText() + "' and anteriorID=0  " + vt + " " + driverMoveInterno + " order by inventarioID desc limit 1");
                    System.out.println("select inventarioID from inventarioexterno_tbl where contenedor = '" + contenedor + "' and anteriorID=0  " + vt + " " + driverMoveInterno + " order by inventarioID desc limit 1");
                    if (InventarioSalidaID.equals("")) {
                        InventarioSalidaID = "0";
                    }
                    System.out.println("contenedorid: " + WContID + "   AnteriorID: " + AnteriorID);

                    //assignedto.setText(utils.dbConsult("select assignedto from inventarioexterno_tbl where inventarioID='" + InventarioSalidaID + "'"));
                    System.out.println("select Sello from inventarioexterno_tbl where inventarioID='" + InventarioSalidaID + "'");
                    txtSello.setText(utils.dbConsult("select Sello from inventarioexterno_tbl where inventarioID='" + InventarioSalidaID + "'"));
                    txtNota.setText(utils.dbConsult("select nota from inventarioexterno_tbl where inventarioID='" + InventarioSalidaID + "'"));

                    txtPlacasChasis.setText(utils.dbConsult("select PlacasChasis from inventarioexterno_tbl where inventarioID='" + InventarioSalidaID + "'"));

                    if (Orgn.getSelectedIndex() == -1) {
                        Orgn.setSelectedItem(utils.dbConsult("SELECT Origen from inventarioexterno_tbl where inventarioID='" + InventarioSalidaID + "'"));
                    }
                    if (destino.equals("")) {
                        destino.setText(utils.dbConsult("SELECT Destino from inventarioexterno_tbl where inventarioID='" + InventarioSalidaID + "'"));
                    }

                    if (placasunidad.equals("")) {
                        placasunidad.setText(utils.dbConsult("SELECT PlacasUnidad from inventarioexterno_tbl where inventarioID='" + InventarioSalidaID + "'"));
                    }
                    if (placasunidad1.equals("")) {
                        placasunidad1.setText(utils.dbConsult("SELECT PlacasUnidadUSA from inventarioexterno_tbl where inventarioID='" + InventarioSalidaID + "'"));
                    }

                    if (placasunidad2.equals("")) {
                        placasunidad2.setText(utils.dbConsult("SELECT EstadoPlacasMex from inventarioexterno_tbl where inventarioID='" + InventarioSalidaID + "'"));
                    }
                    if (placasunidad3.equals("")) {
                        placasunidad3.setText(utils.dbConsult("SELECT EstadoPlacasUsa from inventarioexterno_tbl where inventarioID='" + InventarioSalidaID + "'"));
                    }

                    sellocomplementario.setText(utils.dbConsult("select sellocomplementario from inventarioexterno_tbl where inventarioID='" + InventarioSalidaID + "'"));

                    if (txtNota.getText().equals("")) {
                        txtNota.setText(utils.dbConsult("select nota from inventarioexterno_tbl where inventarioID='" + utils.dbConsult("select inventarioID from inventarioexterno_tbl where contenedor = '" + contenedor + "' and anteriorID=0 and patioID='" + PatioID.get(Patio.getSelectedIndex()) + "' order by inventarioID desc limit 1") + "'"));
                    }

                    try {
                        paisx.setSelectedItem(utils.dbConsult("select remolquePais from inventarioexterno_tbl where contenedor='" + txtContenedor.getText() + "' order by inventarioID desc limit 1")); //and NumeroChasis!='' and NumeroChasis is not null 
                        estx.setSelectedIndex(estxid.indexOf((utils.dbConsult("select remolqueEstado from inventarioexterno_tbl where contenedor='" + txtContenedor.getText() + "' order by inventarioID desc limit 1")))); //and NumeroChasis!='' and NumeroChasis is not null 
                    } catch (Exception e) {
                    }
                }

                if (boxChofer.getSelectedIndex() == -1) {
                    boxChofer.setSelectedIndex(0);
                    licencia.setText("-");
                }

                if (camionx.getSelectedIndex() == 0) {
                    placasunidad2.setText("-");
                    placasunidad3.setText("-");
                    placasunidad.setText("-");
                    placasunidad1.setText("-");

                }

                //txtPlacasChasis.setText(utils.dbConsult("select IF(placas is null or placas='' or placas=' ' or placas='   ',placasusa,placas) from cajas_tbl where cajaID='" + utils.dbConsult("select cajaID from workcontenedores_tbl where WContenedorID='" + WContID + "'") + "'"));
                txtPlacasChasis.setText(utils.dbConsult("select PlacasChasis from workcontenedores_tbl where WContenedorID='" + WContID + "'"));

                String paisxp = "0";
                int paisplacasdespacho = 0;
                try {
                    paisxp = utils.dbConsult("SELECT IFNULL(est.PaisID-1,0) FROM estados_tbl est WHERE est.Abreviacion='" + utils.dbConsult("select estadoplacas from workcontenedores_tbl where WContenedorID='" + WContID + "'") + "'");
                } catch (Exception e) {
                }

                System.out.println(paisxp);

                if (paisxp == null || paisxp.equals("")) {
                    paisxp = "0";
                    paisplacasdespacho = 0;
                } else {
                    paisplacasdespacho = Integer.parseInt(paisxp);
                }

                paisx.setSelectedIndex(paisplacasdespacho);
                estx.setSelectedItem(utils.dbConsult("select estadoplacas from workcontenedores_tbl where WContenedorID='" + WContID + "'"));

                /*
                    int paisplacas = Integer.parseInt(utils.dbConsult("select IF(placas is null or placas='' or placas=' ' or placas='   ',1,0) from cajas_tbl where cajaID='" + utils.dbConsult("select cajaID from workcontenedores_tbl where WContenedorID='" + WContID + "'") + "'"));
                    paisx.setSelectedIndex(paisplacas);
                    estx.setSelectedItem(utils.dbConsult("select IF(placas is null or placas='' or placas=' ' or placas='   ',EstadoPlacasUsa,EstadoPlacas) from cajas_tbl where cajaID='" + utils.dbConsult("select cajaID from workcontenedores_tbl where WContenedorID='" + WContID + "'") + "'"));
                 */
                System.out.println(estadoc);

                if (estadoc.equals("2")) {
                    botando.setSelected(true);
                    txtContenedor.setText("BOTANDO - " + camionx.getSelectedItem().toString());
                } else {
                    botando.setSelected(false);
                }

                if (estadoc.equals("3")) {

                    solochasis.setSelected(true);
                    txtContenedor.setText("CHASIS - " + cajaxchassis2);
                    boxEstadoCarga.setSelectedIndex(0);

                    //txtPlacasChasis.setText(utils.dbConsult("select IF(placas is null or placas='' or placas=' ' or placas='   ',placasusa,placas) from cajas_tbl where noeconomico='" + cajaxchassis2 + "'"));
                    /*paisplacas = Integer.parseInt(utils.dbConsult("select IF(placas is null or placas='' or placas=' ' or placas='   ',1,0) from cajas_tbl where noeconomico='" + cajaxchassis2 + "'"));
                        paisx.setSelectedIndex(paisplacas);
                        estx.setSelectedItem(utils.dbConsult("select IF(placas is null or placas='' or placas=' ' or placas='   ',EstadoPlacasUsa,EstadoPlacas) from cajas_tbl where noeconomico='" + cajaxchassis2 + "'"));
                     */
                    //pusho
                } else {
                    solochasis.setSelected(false);
                }

                String remolqueInterno = utils.dbConsult("select cajaID from icont_tbl where ItinerarioID='" + txtDriverMove.getText() + "' and status=1  order by icontID " + order + " limit 1");
                String estadoCaja = utils.dbConsult("select EstadoPlacasUSA from cajas_tbl where cajaID='" + remolqueInterno + "'");
                String paisxpx = "0";
                int paisplacasdespachox = 0;

                if (estadoCaja.equals("")) {
                    estadoCaja = utils.dbConsult("select estadoplacas from cajas_tbl where cajaID='" + remolqueInterno + "'");
                    if (txtPlacasChasis.equals("")) {
                        txtPlacasChasis.setText(utils.dbConsult("select placas from cajas_tbl where cajaID='" + remolqueInterno + "'"));
                    }
                } else {
                    if (txtPlacasChasis.equals("")) {
                        txtPlacasChasis.setText(utils.dbConsult("select placasUSA from cajas_tbl where cajaID='" + remolqueInterno + "'"));
                    }
                }

                try {
                    paisxpx = utils.dbConsult("SELECT IFNULL(PaisID-1,0) FROM estados_tbl  WHERE Abreviacion='" + estadoCaja + "'");
                } catch (Exception e) {
                }

                System.out.println("SELECT IFNULL(PaisID-1,0) FROM estados_tbl  WHERE Abreviacion='" + estadoCaja + "'");
                System.out.println("estadoCaja:" + estadoCaja);

                if (paisxpx == null || paisxpx.equals("")) {
                    paisxpx = "0";
                    paisplacasdespachox = 0;
                } else {
                    paisplacasdespachox = Integer.parseInt(paisxpx);
                }

                paisx.setSelectedIndex(paisplacasdespachox);
                estx.setSelectedItem(estadoCaja);

                if (estx.getSelectedIndex() == -1) {

                    try {
                        paisx.setSelectedItem(utils.dbConsult("select remolquePais from inventarioexterno_tbl where contenedor='" + txtContenedor.getText() + "' order by inventarioID desc limit 1")); //and NumeroChasis!='' and NumeroChasis is not null 
                        estx.setSelectedIndex(estxid.indexOf((utils.dbConsult("select remolqueEstado from inventarioexterno_tbl where contenedor='" + txtContenedor.getText() + "' order by inventarioID desc limit 1")))); //and NumeroChasis!='' and NumeroChasis is not null 
                    } catch (Exception e) {
                    }
                }

                ContenedorFiltro.dispose();

                /*if( InOut.getSelectedItem().toString().equals("Salida") ) {
                
                     WContID = DrvMV;
                     String contenedor = utils.dbConsult("select Contenedor from workcontenedores_tbl where WContenedorID='"+DrvMV+"'");

                     txtContenedor.setText(contenedor);
                
                     String vrt="", vt="";
                     vrt = utils.dbConsult("select ifnull(vt,false) from patios_tbl where  patioID='"+patiosid.get(BoxPatios.getSelectedIndex())+"'");

                     if(vrt.equals("1")) { vt=" and (select ifnull(vt,false) from patios_tbl where  patioID='"+patiosid.get(BoxPatios.getSelectedIndex())+"') is true "; }
                     else vt=" and patioID='"+utils.dbConsult("select patioID from usuarios_Tbl where usuarioID='"+global.usuario+"'")+"' ";

                     String drivermov=utils.dbConsult("select IFNULL((select itinerarioID from itinerarios_tbl where WcontFK=workcontenedores_tbl.WContenedorID order by itinerarios_tbl.ItinerarioID desc limit 1 ),'') as driverm \n" +
                     "from workcontenedores_tbl where contenedor = '"+txtContenedor.getText()+"'  and `status`=1 \n" +
                     "and IFNULL((select ItinerarioID from itinerarios_tbl where WcontFK=workcontenedores_tbl.WContenedorID and itinerarios_tbl.Status=1 limit 1 ),'') > 0 "
                     + "AND (SELECT (SELECT PatioID from locaciones_tbl where `LocacionID` = LocacionPUID) from rutas_tbl where " +
                     "(select RutaID from itinerarios_tbl where WcontFK=workcontenedores_tbl.WContenedorID and itinerarios_tbl.Status=1 limit 1 ) = rutas_tbl.RutaID) = 1 " +
                     "limit 1");

                     String externo = utils.dbConsult("select inventarioID "
                     + "from inventarioexterno_tbl where contenedor = '"+txtContenedor.getText()+"' and anteriorID=0  and WContenedorID=0 and ItinerarioID=0 "
                     + " "+vt+" order by inventarioID desc limit 1");

                     String driverMoveInterno = "";
                     if(externo.equals("") && !drivermov.equals("") && !vrt.equals("1")) driverMoveInterno = " and AND (SELECT (SELECT PatioID from locaciones_tbl where `LocacionID` = LocacionPUID) from rutas_tbl where " +
                     "(select RutaID from itinerarios_tbl where WcontFK=workcontenedores_tbl.WContenedorID and itinerarios_tbl.Status=1 limit 1 ) = rutas_tbl.RutaID) = '"+utils.dbConsult("select patioID from usuarios_Tbl where usuarioID='"+global.usuario+"'")+"' ";

                     else driverMoveInterno="";

                     String query = "select inventarioID "
                     + "from inventarioexterno_tbl where contenedor = '"+contenedor+"' and anteriorID=0  "
                     + " "+vt+" "+driverMoveInterno+" order by inventarioID desc limit 1";
                
                
                     InventarioSalidaID = utils.dbConsult("select inventarioID from inventarioexterno_tbl where contenedor = '"+contenedor+"' and anteriorID=0  "+vt+" "+driverMoveInterno+" order by inventarioID desc limit 1");
                     System.out.println("contenedorid: "+WContID + "   AnteriorID: "+AnteriorID);
                
                     CARRIER.setText(utils.dbConsult("Select razonsocial from empresas_tbl where empresaID=(select empresaID from camiones_tbl where camionID='"+utils.dbConsult("select camionID from camiones_tbl where camionID=(select camionID from itinerarios_tbl where WcontFK='"+WContID+"' order by itinerarios_tbl.ItinerarioID desc limit 1)")+"')"));
            
                     try {
                    
                     //String consulta = utils.dbConsult("select IFNULL(clienteAMPLID,'') from clientes_tbl where clienteID=(select clienteID from workorder_tbl where workID=(select WorkOrderID from workcontenedores_tbl where WContenedorID='"+WContID+"'))");
                    
                     //if(consulta.equals("")) 
                     cliente.setSelectedIndex(clienteid.indexOf((utils.dbConsult("select clienteID from workorder_tbl where workID=(select WorkOrderID from workcontenedores_tbl where WContenedorID='"+WContID+"')"))));
                    
                     //else cliente.setSelectedIndex(clienteid.indexOf(consulta));
                    
                     cliente1.setSelectedIndex(cliente1id.indexOf((utils.dbConsult("select clienteID from workorder_tbl where workID=(select WorkOrderID from workcontenedores_tbl where WContenedorID='"+WContID+"')"))));

                     boxPaisPlacas.setSelectedIndex(Integer.parseInt(utils.dbConsult("select PaisID-1 from inventarioexterno_tbl where inventarioID='"+InventarioSalidaID+"'")));
                    
                     placasunidad.setText(utils.dbConsult("select placas from camiones_Tbl where camionID='"+utils.dbConsult("select camionID from camiones_tbl where camionID=(select camionID from itinerarios_tbl where WcontFK='"+WContID+"' order by itinerarios_tbl.ItinerarioID desc limit 1)")+"'"));
                     placasunidad1.setText(utils.dbConsult("select placasusa from camiones_Tbl where camionID='"+utils.dbConsult("select camionID from camiones_tbl where camionID=(select camionID from itinerarios_tbl where WcontFK='"+WContID+"' order by itinerarios_tbl.ItinerarioID desc limit 1)")+"'"));
                    
                     txtPlacasChasis.setText(utils.dbConsult("select placas from cajas_tbl where cajaID='"+utils.dbConsult("select cajaID from workcontenedores_tbl where WContenedorID='"+WContID+"'")+"'"));
                     txtPlacasChasis1.setText(utils.dbConsult("select placasusa from cajas_tbl where cajaID='"+utils.dbConsult("select cajaID from workcontenedores_tbl where WContenedorID='"+WContID+"'")+"'"));
                     NCHASIS.setText(utils.dbConsult("select PlacasChasis from workcontenedores_tbl where WContenedorID='"+WContID+"'"));
                     txtSello.setText(utils.dbConsult("select NumeroSello from workcontenedores_tbl where WContenedorID='"+WContID+"'"));
                    

                     //String estado = utils.dbConsult("select EstadoID from inventarioexterno_tbl where inventarioID='"+InventarioSalidaID+"'");
                     //if(estado.equals("") || estado.equals("0")) boxEstadoPlacas.setSelectedIndex(0);
                     //else    boxEstadoPlacas.setSelectedIndex(estadosid.indexOf(estado));
                    
                     //String estadoUSA = utils.dbConsult("select EstadoUSAID from inventarioexterno_tbl where inventarioID='"+InventarioSalidaID+"'");
                     //if(estadoUSA.equals("") || estadoUSA.equals("0")) boxEstadoPlacas1.setSelectedIndex(0);
                     //else    boxEstadoPlacas1.setSelectedIndex(estadosid1.indexOf(estadoUSA));
                    
                     placasunidad2.setText(utils.dbConsult("select EstadoPlacas from camiones_tbl where camionID='"+utils.dbConsult("select camionID from camiones_tbl where camionID=(select camionID from itinerarios_tbl where WcontFK='"+WContID+"' order by itinerarios_tbl.ItinerarioID desc limit 1)")+"'"));
                     placasunidad3.setText(utils.dbConsult("select EstadoPlacasUSA from camiones_tbl where camionID='"+utils.dbConsult("select camionID from camiones_tbl where camionID=(select camionID from itinerarios_tbl where WcontFK='"+WContID+"' order by itinerarios_tbl.ItinerarioID desc limit 1)")+"'"));
                    

                     boxChofer.setSelectedIndex(choferesid.indexOf(utils.dbConsult("select choferID from inventarioexterno_tbl where inventarioID='"+InventarioSalidaID+"'"))); 
                     txtNombreChofer.setText(utils.dbConsult("SELECT NombreChofer from inventarioexterno_tbl where inventarioID='"+InventarioSalidaID+"'"));
                     txtFLlegada2.setText(utils.dbConsult("select DATE_FORMAT(now(), '"+global.fdatedb+"')"));
                     txtHLlegada.setText(utils.dbConsult("select DATE_FORMAT(now(), '%H:%i')"));
                     txtNota.setText(utils.dbConsult("select nota from inventarioexterno_tbl where inventarioID='"+InventarioSalidaID+"'"));
                     boxEstadoCarga.setSelectedIndex(Integer.parseInt(utils.dbConsult("select EstadoCarga from inventarioexterno_tbl where inventarioID='"+InventarioSalidaID+"'")));
                     boxTamano.setSelectedItem(utils.dbConsult("select tamano from inventarioexterno_tbl where inventarioID='"+InventarioSalidaID+"'"));
                    
                     camiones.setText(utils.dbConsult("SELECT camion from inventarioexterno_tbl where inventarioID='"+InventarioSalidaID+"'"));
                     //cliente1.setSelectedIndex(cliente1id.indexOf((utils.dbConsult("select EquipmentProvider from inventarioexterno_tbl where inventarioID='"+InventarioSalidaID+"'"))));
                    
                     Orgn.setSelectedItem(utils.dbConsult("SELECT Origen from inventarioexterno_tbl where inventarioID='"+InventarioSalidaID+"'"));
                     destino.setText(utils.dbConsult("SELECT Destino from inventarioexterno_tbl where inventarioID='"+InventarioSalidaID+"'"));
                     destino.setText(utils.dbConsult("SELECT Destino from rutas_tbl where rutas_tbl.RutaID = (select RutaID from itinerarios_tbl where WcontFK='"+WContID+"' order by itinerarios_tbl.ItinerarioID desc limit 1)")); //and Status = 1 
                    
                     CARRIER.setText(utils.dbConsult("SELECT Carrier from inventarioexterno_tbl where inventarioID='"+InventarioSalidaID+"'"));
                    
                     edicion.setText(utils.dbConsult("SELECT concat((select Nombre from usuarios_Tbl where usuarioID=UsuarioEventoID),' ',"
                     + "DATE_FORMAT(fechaedicion,'%m/%d/%Y %H:%i')) from inventarioexterno_tbl where inventarioID='"+InventarioSalidaID+"'"));
                    
                     perfilBuscador1.setSelectedIndex(perfilBuscador1ID.indexOf(utils.dbConsult("SELECT IFNULL(perfilCobroID,0) from inventarioexterno_tbl where inventarioID='"+InventarioSalidaID+"'")));
                    
                     String virtual="";
                     virtual = utils.dbConsult("select ifnull(vt,false) from patios_tbl where  patioID='"+patiosid.get(BoxPatios.getSelectedIndex())+"'");
        
                     if(virtual.equals("1")) Patio.setSelectedIndex(BoxPatios.getSelectedIndex());
                    
                     String drivermove=utils.dbConsult("select IFNULL((select itinerarioID from itinerarios_tbl where WcontFK=workcontenedores_tbl.WContenedorID order by itinerarios_tbl.ItinerarioID desc limit 1 ),'') as driverm \n" +
                     "from workcontenedores_tbl where contenedor = '"+txtContenedor.getText()+"'  and `status`=1 \n" +
                     "and IFNULL((select ItinerarioID from itinerarios_tbl where WcontFK=workcontenedores_tbl.WContenedorID and itinerarios_tbl.Status=1 limit 1 ),'') > 0 " +
                     "limit 1");
                    
                     if(!virtual.equals("1") && jCheckBox6.isSelected() && !drivermove.equals("")) {
                        
                     cliente1.setSelectedIndex(cliente1id.indexOf((utils.dbConsult("select clienteID from workorder_tbl where workID=(select WorkOrderID from workcontenedores_tbl where WContenedorID='"+WContID+"')"))));
                     boxChofer.setSelectedIndex(choferesid.indexOf(utils.dbConsult("SELECT choferID from choferes_tbl where choferID=(select choferID from itinerarios_tbl where WcontFK='"+WContID+"'  order by itinerarios_tbl.ItinerarioID desc limit 1)")));
                     txtNombreChofer.setText("");
                     camionx.setSelectedIndex(camionidx.indexOf(utils.dbConsult("select camionID from camiones_tbl where camionID=(select camionID from itinerarios_tbl where WcontFK='"+WContID+"' order by itinerarios_tbl.ItinerarioID desc limit 1)"))); 
                     Orgn.setSelectedItem(utils.dbConsult("SELECT Origen from rutas_tbl where rutas_tbl.RutaID = (select RutaID from workcontenedores_tbl where workcontenedores_tbl.WContenedorID='"+WContID+"')"));
                     destino.setText(utils.dbConsult("SELECT Destino from rutas_tbl where rutas_tbl.RutaID = (select RutaID from workcontenedores_tbl where workcontenedores_tbl.WContenedorID='"+WContID+"')"));
                        
                     //consulta = utils.dbConsult("select IFNULL(clienteAMPLID,'') from clientes_tbl where clienteID=(select clienteID from workorder_tbl where workID=(select WorkOrderID from workcontenedores_tbl where WContenedorID='"+WContID+"'))");
                    
                     //if(consulta.equals("")) 
                     cliente.setSelectedIndex(clienteid.indexOf((utils.dbConsult("select clienteID from workorder_tbl where workID=(select WorkOrderID from workcontenedores_tbl where WContenedorID='"+WContID+"')"))));

                     //else cliente.setSelectedIndex(clienteid.indexOf(consulta));
                     }
                    
                     else {
                        
                     cliente.setSelectedIndex(cliente1id.indexOf((utils.dbConsult("select ClienteID from inventarioexterno_tbl where inventarioID='"+InventarioSalidaID+"'"))));
                     cliente1.setSelectedIndex(cliente1id.indexOf((utils.dbConsult("select EquipmentProvider from inventarioexterno_tbl where inventarioID='"+InventarioSalidaID+"'"))));
                     boxChofer.setSelectedIndex(choferesid.indexOf(utils.dbConsult("select choferID from inventarioexterno_tbl where inventarioID='"+InventarioSalidaID+"'"))); 
                     txtNombreChofer.setText(utils.dbConsult("SELECT NombreChofer from inventarioexterno_tbl where inventarioID='"+InventarioSalidaID+"'"));
                     txtPlacasChasis.setText(utils.dbConsult("select PlacasChasis from inventarioexterno_tbl where inventarioID='"+InventarioSalidaID+"'"));
                     txtSello.setText(utils.dbConsult("select Sello from inventarioexterno_tbl where inventarioID='"+InventarioSalidaID+"'"));
                     NCHASIS.setText(utils.dbConsult("SELECT NumeroChasis from inventarioexterno_tbl where inventarioID='"+InventarioSalidaID+"'")); 
                     txtPlacasChasis.setText(utils.dbConsult("select PlacasChasis from inventarioexterno_tbl where inventarioID='"+InventarioSalidaID+"'"));
                     txtPlacasChasis1.setText(utils.dbConsult("select PlacasChasis from inventarioexterno_tbl where inventarioID='"+InventarioSalidaID+"'"));
                     txtSello.setText(utils.dbConsult("select Sello from inventarioexterno_tbl where inventarioID='"+InventarioSalidaID+"'"));
                     camionx.setSelectedIndex(camionidx.indexOf(utils.dbConsult("SELECT camionID from inventarioexterno_tbl where inventarioID='"+InventarioSalidaID+"'"))); 
                     destino.setText(utils.dbConsult("SELECT Destino from inventarioexterno_tbl where inventarioID='"+InventarioSalidaID+"'"));

                     }
                    
                     if( txtPlacasChasis.getText().equals("") )
                     txtPlacasChasis.setText(utils.dbConsult("select PlacasChasis from inventarioexterno_tbl where contenedor='"+txtContenedor.getText()+"' and NumeroChasis!='' and NumeroChasis is not null order by inventarioID desc limit 1"));
                    
                     if( txtPlacasChasis1.getText().equals("") )
                     txtPlacasChasis1.setText(utils.dbConsult("select PlacasChasisUSA from inventarioexterno_tbl where contenedor='"+txtContenedor.getText()+"' and NumeroChasis!='' and NumeroChasis is not null order by inventarioID desc limit 1"));
                    
                    
                     } catch(Exception ex) {System.out.println(ex);}
                    
                     ContenedorFiltro.dispose();
                     }*/
            }

            /*cargarContenedoresFiltrados();
                
                 if(contenedor_tbl.getRowCount()>0) {
                 ContenedorFiltro.setLocationRelativeTo(dEntradaSalida); 
                 ContenedorFiltro.repaint();
                 ContenedorFiltro.setVisible(true);
                 }
                
                 else {
                 //NCHASIS.setText(utils.dbConsult("select NumeroChasis from inventarioexterno_tbl where contenedor='"+txtContenedor.getText()+"' and NumeroChasis!='' and NumeroChasis is not null order by inventarioID desc limit 1"));
                 txtPlacasChasis.setText(utils.dbConsult("select PlacasChasis from inventarioexterno_tbl where contenedor='"+txtContenedor.getText()+"' and NumeroChasis!='' and NumeroChasis is not null order by inventarioID desc limit 1"));
                 grade.setSelectedItem(utils.dbConsult("select grade from inventarioexterno_tbl where contenedor='"+txtContenedor.getText()+"' and NumeroChasis!='' and NumeroChasis is not null order by inventarioID desc limit 1"));
                 boxTamano.setSelectedItem(utils.dbConsult("select tamano from inventarioexterno_tbl where contenedor='"+txtContenedor.getText()+"' and NumeroChasis!='' and NumeroChasis is not null order by inventarioID desc limit 1"));
                 }*/
        } else {
            JOptionPane.showMessageDialog(dEntradaSalida, "No hay ningun registro.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    void loadFull(String driver) {

        String contenedor, cajaxchassis2, estadoc, Full = "No", icontID = "0";

        txtDriverMove.setText(driver);

        if (!txtDriverMove.getText().equals("")) {

            String valor = txtDriverMove.getText();
            resetter();
            capturaFull = true;
            txtDriverMove.setText(valor);

            WContID = "0";
            AnteriorID = "0";
            InventarioSalidaID = "0";
            ItiID = "0";

            String DrvMV = utils.dbConsult("select WContID from icont_tbl where ItinerarioID='" + txtDriverMove.getText() + "' and status=1 order by icontID desc limit 1");

            if (!DrvMV.equals("")) {

                Full = utils.dbConsult("select IF(count(itinerarioID)>1,'Si','No') from icont_tbl where ItinerarioID='" + txtDriverMove.getText() + "' and status=1");
                if (Full.equals("Si")) {
                    Fullx1.setVisible(true);
                } else {
                    Fullx1.setVisible(false);
                }

                //if( InOut.getSelectedItem().toString().equals("Entrada") ) {
                WContID = DrvMV;
                ItiID = txtDriverMove.getText();
                System.out.println(DrvMV);

                contenedor = utils.dbConsult("select ncontenedor from icont_tbl where ItinerarioID='" + txtDriverMove.getText() + "' and status=1  order by icontID desc limit 1");
                cajaxchassis2 = utils.dbConsult("select caja from icont_tbl where ItinerarioID='" + txtDriverMove.getText() + "' and status=1  order by icontID desc limit 1");
                //cajaxchassis = utils.dbConsult("select cajaID from workcontenedores_tbl where WContenedorID='" + DrvMV + "'"); //
                estadoc = utils.dbConsult("Select Estado from icont_tbl where itinerarioID='" + ItiID + "' and status=1 order by icontID desc limit 1");

                if (contenedor.equals("")) {
                    contenedor = utils.dbConsult("select contenedor  from workcontenedores_tbl where WContenedorID='" + WContID + "'");
                }

                camionx.setSelectedIndex(camionidx.indexOf(utils.dbConsult("select camionID from camiones_tbl where camionID=(select camionID from itinerarios_tbl where  ITINERARIOID='" + ItiID + "' )"))); //WcontFK='" + WContID + "' ANDorder by itinerarios_tbl.ItinerarioID desc limit 1

                txtContenedor.setText(contenedor);
                cajachasisboxExterno.setText(cajaxchassis2);

                try {


                    /*if (solochasis.isSelected()) {
                            contenedor = utils.dbConsult("select ncontenedor  from workcontenedores_tbl where WContenedorID='" + WContID + "'");
                            if (contenedor.equals("")) {
                                contenedor = utils.dbConsult("select Caja from icont_tbl where itinerarioID='" + ItiID + "'");
                            }
                        } else {
                            contenedor = utils.dbConsult("select Contenedor from workcontenedores_tbl where WContenedorID='" + DrvMV + "'");
                        }*/
                    //cajachasisbox.setSelectedIndex(cajachasisboxID.indexOf(cajaxchassis));
                    statz.setText("Estatus: " + utils.dbConsult("SELECT estatus_caja from cajas_tbl where noeconomico = '" + contenedor + "' limit 1"));

                    if (!utils.dbConsult("select cajaID from cajas_tbl where noeconomico='" + contenedor + "'").equals("")) {
                        cliente1.setSelectedIndex(cliente1id.indexOf((utils.dbConsult("select clienteID from clientes_tbl where ncomercial='dfc' limit 1"))));
                    } else {
                        cliente1.setSelectedIndex(cliente1id.indexOf((utils.dbConsult("select clienteFK from workcontenedores_tbl where WContenedorID='" + WContID + "')"))));
                    }
                    //cliente.setSelectedIndex(clienteid.indexOf((utils.dbConsult("select LocacionTOID from rutas_tbl where rutas_tbl.RutaID =(select RutaID from workcontenedores_tbl where workcontenedores_tbl.WContenedorID='"+WContID+"')"))));

                    System.out.println("select camionID from camiones_tbl where camionID=(select camionID from itinerarios_tbl where  ITINERARIOID='" + ItiID + "' )"); //WcontFK='" + WContID + "' AND
                    //String consulta = utils.dbConsult("select IFNULL(clienteAMPLID,'') from clientes_tbl where clienteID=(select clienteID from workorder_tbl where workID=(select WorkOrderID from workcontenedores_tbl where WContenedorID='"+WContID+"'))");
                    //if(consulta.equals("")) 
                    cliente.setSelectedIndex(clienteid.indexOf((utils.dbConsult("select clienteFK from workcontenedores_tbl where WContenedorID='" + WContID + "')"))));

                    //else cliente.setSelectedIndex(clienteid.indexOf(consulta));
                    /*String foxFilter="";
                         foxFilter = utils.dbConsult("select clienteID from workorder_tbl where workID=(select WorkOrderID from workcontenedores_tbl where WContenedorID='"+WContID+"')");
                         if(!foxFilter.equals("") && !utils.dbConsult("select * from clientes_tbl where razonsocial like '%foxc%' and clienteID='"+foxFilter+"' limit 1 ").equals("") && 
                         utils.dbConsult("SELECT Destino from rutas_tbl where rutas_tbl.RutaID = (select RutaID from itinerarios_tbl where WcontFK='"+WContID+"' and Status = 1 order by itinerarios_tbl.ItinerarioID desc limit 1)").equals("YARDA5") &&   
                         utils.dbConsult("Select IF(Estado=0,'Vacio','Cargado') from itinerarios_tbl where WcontFK='"+WContID+"'  order by itinerarios_tbl.ItinerarioID desc limit 1 ").equals("Vacio")   ) {
                         cliente1.setSelectedIndex(cliente1id.indexOf("81"));
                         }*/
                    placasunidad.setText(utils.dbConsult("select placas from camiones_Tbl where camionID='" + utils.dbConsult("select camionID from camiones_tbl where camionID=(select camionID from itinerarios_tbl where ITINERARIOID='" + ItiID + "')") + "'")); //WcontFK='" + WContID + "' AND order by itinerarios_tbl.ItinerarioID desc limit 1
                    placasunidad1.setText(utils.dbConsult("select placasusa from camiones_Tbl where camionID='" + utils.dbConsult("select camionID from camiones_tbl where camionID=(select camionID from itinerarios_tbl where  ITINERARIOID='" + ItiID + "' )") + "'")); //WcontFK='" + WContID + "' ANDorder by itinerarios_tbl.ItinerarioID desc limit 1

                    txtPlacasChasis.setText(utils.dbConsult("select IF('" + paisx.getSelectedItem().toString().equals("MEX") + "',placas,placasusa) from cajas_tbl where cajaID='" + utils.dbConsult("select cajaID from workcontenedores_tbl where WContenedorID='" + WContID + "'") + "'"));
                    //txtPlacasChasis1.setText(utils.dbConsult("select placasusa from cajas_tbl where cajaID='"+utils.dbConsult("select cajaID from workcontenedores_tbl where WContenedorID='"+WContID+"'")+"'"));
                    NCHASIS.setText(utils.dbConsult("select ncontenedor  from workcontenedores_tbl where WContenedorID='" + WContID + "'"));
                    txtSello.setText(utils.dbConsult("select NumeroSello from workcontenedores_tbl where WContenedorID='" + WContID + "'"));

                    /*String pais = utils.dbConsult("select PaisID-1 from workcontenedores_tbl where WContenedorID='"+WContID+"'");
                         if(pais.equals("1")) boxPaisPlacas.setSelectedIndex(1);
                         else    boxPaisPlacas.setSelectedIndex(0);

                         String estado = utils.dbConsult("select EstadoID from workcontenedores_tbl where WContenedorID='"+WContID+"'");
                         String estadoUSA = utils.dbConsult("select EstadoUSAID from workcontenedores_tbl where WContenedorID='"+WContID+"'");
                    
                         if(estado.equals("") || estado.equals("0")) boxEstadoPlacas.setSelectedIndex(0);
                         else    boxEstadoPlacas.setSelectedIndex(estadosid.indexOf(estado));
                    
                         if(estadoUSA.equals("") || estadoUSA.equals("0")) boxEstadoPlacas1.setSelectedIndex(0);
                         else    boxEstadoPlacas1.setSelectedIndex(estadosid1.indexOf(estadoUSA));*/
                    placasunidad2.setText(utils.dbConsult("select EstadoPlacas from camiones_tbl where camionID='" + utils.dbConsult("select camionID from camiones_tbl where camionID=(select camionID from itinerarios_tbl where ITINERARIOID='" + ItiID + "' )") + "'")); // where WcontFK='" + WContID + "' AND order by itinerarios_tbl.ItinerarioID desc limit 1
                    placasunidad3.setText(utils.dbConsult("select EstadoPlacasUSA from camiones_tbl where camionID='" + utils.dbConsult("select camionID from camiones_tbl where camionID=(select camionID from itinerarios_tbl where  ITINERARIOID='" + ItiID + "' )") + "'")); // WcontFK='" + WContID + "' ANDorder by itinerarios_tbl.ItinerarioID desc limit 1

                    CARRIER.setText(utils.dbConsult("Select razonsocial from empresas_tbl where empresaID=(select empresaID from camiones_tbl where camionID='" + utils.dbConsult("select camionID from camiones_tbl where camionID=(select camionID from itinerarios_tbl where  ITINERARIOID='" + ItiID + "' )") + "')")); // WcontFK='" + WContID + "'  ANDorder by itinerarios_tbl.ItinerarioID desc limit 1

                    boxChofer.setSelectedIndex(chofereslocalid.indexOf(utils.dbConsult("SELECT choferID from choferes_tbl where choferID=(select choferID from itinerarios_tbl where  ITINERARIOID='" + ItiID + "')"))); //WcontFK='" + WContID + "'  AND order by itinerarios_tbl.ItinerarioID desc limit 1
                    txtNombreChofer.setText("");

                    licencia.setText(utils.dbConsult("SELECT licencia from choferes_tbl where choferID=(select choferID from itinerarios_tbl where  ITINERARIOID='" + ItiID + "')")); //WcontFK='" + WContID + "'  AND

                    txtFLlegada2.setText(utils.dbConsult("select DATE_FORMAT(now(), '" + global.fdatedb + "')"));
                    txtHLlegada.setText(utils.dbConsult("select DATE_FORMAT(now(), '%H:%i')"));
                    Orgn.setSelectedItem(utils.dbConsult("SELECT Origen from rutas_tbl where rutas_tbl.RutaID = (select RutaID from itinerarios_tbl where ITINERARIOID='" + ItiID + "' )")); //WcontFK='" + WContID + "' AND  order by itinerarios_tbl.ItinerarioID desc limit 1          //and Status = 1 
                    destino.setText(utils.dbConsult("SELECT Destino from rutas_tbl where rutas_tbl.RutaID = (select RutaID from itinerarios_tbl where  ITINERARIOID='" + ItiID + "' )"));     //WcontFK='" + WContID + "' AND order by itinerarios_tbl.ItinerarioID desc limit 1                    //and Status = 1 

                    boxEstadoCarga.setSelectedItem(utils.dbConsult("Select IF(Estado=0,'Vacio','Cargado') from icont_tbl where itinerarioID='" + ItiID + "' and status=1 order by icontID desc limit 1   ")); //order by itinerarios_tbl.ItinerarioID desc limit 1 
                    boxTamano.setSelectedItem(utils.dbConsult("Select Tamano from workcontenedores_tbl where WContenedorID='" + WContID + "'"));

                    perfilBuscador1.setSelectedIndex(0);

                    if (txtPlacasChasis.getText().equals("")) {
                        txtPlacasChasis.setText(utils.dbConsult("select PlacasChasis from inventarioexterno_tbl where contenedor='" + txtContenedor.getText() + "' and NumeroChasis!='' and NumeroChasis is not null order by inventarioID desc limit 1"));
                    }
                    /*
                         if( txtPlacasChasis1.getText().equals("") )
                         txtPlacasChasis1.setText(utils.dbConsult("select PlacasChasisUSA from inventarioexterno_tbl where contenedor='"+txtContenedor.getText()+"' and NumeroChasis!='' and NumeroChasis is not null order by inventarioID desc limit 1"));
                     */

                    try {
                        paisx.setSelectedItem(utils.dbConsult("select remolquePais from inventarioexterno_tbl where contenedor='" + txtContenedor.getText() + "' order by inventarioID desc limit 1")); //and NumeroChasis!='' and NumeroChasis is not null 
                        estx.setSelectedIndex(estxid.indexOf((utils.dbConsult("select remolqueEstado from inventarioexterno_tbl where contenedor='" + txtContenedor.getText() + "' order by inventarioID desc limit 1")))); //and NumeroChasis!='' and NumeroChasis is not null 
                    } catch (Exception e) {
                    }

                    txtNota.setText("");
                    assignedto.setText("");
                    /*boxEstadoCarga.setSelectedIndex(0);*/
                } catch (Exception e) {
                }

                //}
                if (InOut.getSelectedItem().toString().equals("Salida")) {

                    contenedor = utils.dbConsult("select Contenedor from workcontenedores_tbl where WContenedorID='" + DrvMV + "'");
                    String vrt = "", vt = "";
                    vrt = utils.dbConsult("select ifnull(vt,false) from patios_tbl where  patioID='" + PatioID.get(Patio.getSelectedIndex()) + "'");

                    if (vrt.equals("1")) {
                        vt = " and (select ifnull(vt,false) from patios_tbl where  patioID='" + PatioID.get(Patio.getSelectedIndex()) + "') is true ";
                    } else {
                        vt = " and patioID='" + PatioID.get(Patio.getSelectedIndex()) + "' ";
                    }

                    String drivermov = utils.dbConsult("select IFNULL((select itinerarioID from itinerarios_tbl where WcontFK=workcontenedores_tbl.WContenedorID AND ITINERARIOID='" + ItiID + "' ),'') as driverm \n"
                            + //order by itinerarios_tbl.ItinerarioID desc limit 1
                            "from workcontenedores_tbl where contenedor = '" + txtContenedor.getText() + "'  and `status`=1 \n"
                            + "and IFNULL((select ItinerarioID from itinerarios_tbl where WcontFK=workcontenedores_tbl.WContenedorID and itinerarios_tbl.Status=1 AND ITINERARIOID='" + ItiID + "' limit 1 ),'') > 0 "
                            + "AND (SELECT (SELECT PatioID from locaciones_tbl where `LocacionID` = LocacionPUID) from rutas_tbl where "
                            + "(select RutaID from itinerarios_tbl where WcontFK=workcontenedores_tbl.WContenedorID and itinerarios_tbl.Status=1 AND ITINERARIOID='" + ItiID + "' limit 1 ) = rutas_tbl.RutaID) = 1 "
                            + "limit 1");

                    String externo = utils.dbConsult("select inventarioID "
                            + "from inventarioexterno_tbl where contenedor = '" + txtContenedor.getText() + "' and anteriorID=0  and WContenedorID=0 and ItinerarioID=0 "
                            + " " + vt + " order by inventarioID desc limit 1");

                    String driverMoveInterno = "";
                    if (externo.equals("") && !drivermov.equals("") && !vrt.equals("1")) {
                        driverMoveInterno = " and AND (SELECT (SELECT PatioID from locaciones_tbl where `LocacionID` = LocacionPUID) from rutas_tbl where "
                                + "(select RutaID from itinerarios_tbl where WcontFK=workcontenedores_tbl.WContenedorID and itinerarios_tbl.Status=1 AND ITINERARIOID='" + ItiID + "' limit 1 ) = rutas_tbl.RutaID) = '" + PatioID.get(Patio.getSelectedIndex()) + "' ";
                    } else {
                        driverMoveInterno = "";
                    }

                    String query = "select inventarioID "
                            + "from inventarioexterno_tbl where contenedor = '" + contenedor + "' and anteriorID=0  "
                            + " " + vt + " " + driverMoveInterno + " order by inventarioID desc limit 1";

                    InventarioSalidaID = utils.dbConsult("select inventarioID from inventarioexterno_tbl where contenedor = '" + contenedor + "' and anteriorID=0  " + vt + " " + driverMoveInterno + " order by inventarioID desc limit 1");
                    if (InventarioSalidaID.equals("")) {
                        InventarioSalidaID = "0";
                    }
                    System.out.println("contenedorid: " + WContID + "   AnteriorID: " + AnteriorID);

                    //assignedto.setText(utils.dbConsult("select assignedto from inventarioexterno_tbl where inventarioID='" + InventarioSalidaID + "'"));
                    txtSello.setText(utils.dbConsult("select Sello from inventarioexterno_tbl where inventarioID='" + InventarioSalidaID + "'"));
                    txtNota.setText(utils.dbConsult("select nota from inventarioexterno_tbl where inventarioID='" + InventarioSalidaID + "'"));
                    if (txtNota.getText().equals("")) {
                        txtNota.setText(utils.dbConsult("select nota from inventarioexterno_tbl where inventarioID='" + utils.dbConsult("select inventarioID from inventarioexterno_tbl where contenedor = '" + contenedor + "' and anteriorID=0 and patioID='" + PatioID.get(Patio.getSelectedIndex()) + "' order by inventarioID desc limit 1") + "'"));
                    }

                    try {
                        paisx.setSelectedItem(utils.dbConsult("select remolquePais from inventarioexterno_tbl where contenedor='" + txtContenedor.getText() + "' order by inventarioID desc limit 1")); //and NumeroChasis!='' and NumeroChasis is not null 
                        estx.setSelectedIndex(estxid.indexOf((utils.dbConsult("select remolqueEstado from inventarioexterno_tbl where contenedor='" + txtContenedor.getText() + "' order by inventarioID desc limit 1")))); //and NumeroChasis!='' and NumeroChasis is not null 
                    } catch (Exception e) {
                    }
                }

                if (boxChofer.getSelectedIndex() == -1) {
                    boxChofer.setSelectedIndex(0);
                    licencia.setText("-");
                }

                if (camionx.getSelectedIndex() == 0) {
                    placasunidad2.setText("-");
                    placasunidad3.setText("-");
                    placasunidad.setText("-");
                    placasunidad1.setText("-");

                }

                //txtPlacasChasis.setText(utils.dbConsult("select IF(placas is null or placas='' or placas=' ' or placas='   ',placasusa,placas) from cajas_tbl where cajaID='" + utils.dbConsult("select cajaID from workcontenedores_tbl where WContenedorID='" + WContID + "'") + "'"));
                txtPlacasChasis.setText(utils.dbConsult("select PlacasChasis from workcontenedores_tbl where WContenedorID='" + WContID + "'"));

                String paisxp = "0";
                int paisplacasdespacho = 0;
                try {
                    paisxp = utils.dbConsult("SELECT IFNULL(est.PaisID-1,0) FROM estados_tbl est WHERE est.Abreviacion='" + utils.dbConsult("select estadoplacas from workcontenedores_tbl where WContenedorID='" + WContID + "'") + "'");
                } catch (Exception e) {
                }

                System.out.println(paisxp);

                if (paisxp == null || paisxp.equals("")) {
                    paisxp = "0";
                    paisplacasdespacho = 0;
                } else {
                    paisplacasdespacho = Integer.parseInt(paisxp);
                }

                paisx.setSelectedIndex(paisplacasdespacho);
                estx.setSelectedItem(utils.dbConsult("select estadoplacas from workcontenedores_tbl where WContenedorID='" + WContID + "'"));

                /*
                    int paisplacas = Integer.parseInt(utils.dbConsult("select IF(placas is null or placas='' or placas=' ' or placas='   ',1,0) from cajas_tbl where cajaID='" + utils.dbConsult("select cajaID from workcontenedores_tbl where WContenedorID='" + WContID + "'") + "'"));
                    paisx.setSelectedIndex(paisplacas);
                    estx.setSelectedItem(utils.dbConsult("select IF(placas is null or placas='' or placas=' ' or placas='   ',EstadoPlacasUsa,EstadoPlacas) from cajas_tbl where cajaID='" + utils.dbConsult("select cajaID from workcontenedores_tbl where WContenedorID='" + WContID + "'") + "'"));
                 */
                System.out.println(estadoc);

                if (estadoc.equals("2")) {
                    botando.setSelected(true);
                    txtContenedor.setText("BOTANDO - " + camionx.getSelectedItem().toString());
                } else {
                    botando.setSelected(false);
                }

                if (estadoc.equals("3")) {

                    solochasis.setSelected(true);
                    txtContenedor.setText("CHASIS - " + cajaxchassis2);
                    boxEstadoCarga.setSelectedIndex(0);

                    //txtPlacasChasis.setText(utils.dbConsult("select IF(placas is null or placas='' or placas=' ' or placas='   ',placasusa,placas) from cajas_tbl where noeconomico='" + cajaxchassis2 + "'"));
                    /*paisplacas = Integer.parseInt(utils.dbConsult("select IF(placas is null or placas='' or placas=' ' or placas='   ',1,0) from cajas_tbl where noeconomico='" + cajaxchassis2 + "'"));
                        paisx.setSelectedIndex(paisplacas);
                        estx.setSelectedItem(utils.dbConsult("select IF(placas is null or placas='' or placas=' ' or placas='   ',EstadoPlacasUsa,EstadoPlacas) from cajas_tbl where noeconomico='" + cajaxchassis2 + "'"));
                     */
                    //pusho
                } else {
                    solochasis.setSelected(false);
                }

                String remolqueInterno = utils.dbConsult("select cajaID from icont_tbl where ItinerarioID='" + txtDriverMove.getText() + "' and status=1  order by icontID desc limit 1");
                String estadoCaja = utils.dbConsult("select EstadoPlacasUSA from cajas_tbl where cajaID='" + remolqueInterno + "'");
                String paisxpx = "0";
                int paisplacasdespachox = 0;

                if (estadoCaja.equals("")) {
                    estadoCaja = utils.dbConsult("select estadoplacas from cajas_tbl where cajaID='" + remolqueInterno + "'");
                    if (txtPlacasChasis.equals("")) {
                        txtPlacasChasis.setText(utils.dbConsult("select placas from cajas_tbl where cajaID='" + remolqueInterno + "'"));
                    }
                } else {
                    if (txtPlacasChasis.equals("")) {
                        txtPlacasChasis.setText(utils.dbConsult("select placasUSA from cajas_tbl where cajaID='" + remolqueInterno + "'"));
                    }
                }

                try {
                    paisxpx = utils.dbConsult("SELECT IFNULL(PaisID-1,0) FROM estados_tbl  WHERE Abreviacion='" + estadoCaja + "'");
                } catch (Exception e) {
                }

                System.out.println("SELECT IFNULL(PaisID-1,0) FROM estados_tbl  WHERE Abreviacion='" + estadoCaja + "'");
                System.out.println("estadoCaja:" + estadoCaja);

                if (paisxpx == null || paisxpx.equals("")) {
                    paisxpx = "0";
                    paisplacasdespachox = 0;
                } else {
                    paisplacasdespachox = Integer.parseInt(paisxpx);
                }

                paisx.setSelectedIndex(paisplacasdespachox);
                estx.setSelectedItem(estadoCaja);

                if (estx.getSelectedIndex() == -1) {

                    try {
                        paisx.setSelectedItem(utils.dbConsult("select remolquePais from inventarioexterno_tbl where contenedor='" + txtContenedor.getText() + "' order by inventarioID desc limit 1")); //and NumeroChasis!='' and NumeroChasis is not null 
                        estx.setSelectedIndex(estxid.indexOf((utils.dbConsult("select remolqueEstado from inventarioexterno_tbl where contenedor='" + txtContenedor.getText() + "' order by inventarioID desc limit 1")))); //and NumeroChasis!='' and NumeroChasis is not null 
                    } catch (Exception e) {
                    }
                }

                ContenedorFiltro.dispose();

            }

        } else {
            JOptionPane.showMessageDialog(dEntradaSalida, "No hay ningun registro.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void txtDriverMoveKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDriverMoveKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDriverMoveKeyReleased

    private void puntosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_puntosMouseClicked

        filepdf = null;
        filepdf2 = null;
        lblFoto.setIcon(null);

        try {
            lblFoto.setIcon(new ImageIcon(utils.imageResize(lblFoto, new File(fotoe.get(puntos.getSelectedRow())))));
            lblFoto1.setIcon(new ImageIcon(utils.imageResize(lblFoto, new File(fotos.get(puntos.getSelectedRow())))));
            //filepdf = new File(global.path + rs.getString("foto"));

        } catch (Exception e) {
            System.out.println("error foto " + e);
        }
    }//GEN-LAST:event_puntosMouseClicked

    private void puntosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_puntosKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_puntosKeyPressed

    private void mPuntosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mPuntosActionPerformed
        int row = jTable1.getSelectedRow();

        if (row >= 0) {
            SelectedInventarioID = inventarioID.get(row);
            cargarPuntos(SelectedInventarioID);
            PuntosRevision.setLocationRelativeTo(this);
            PuntosRevision.setVisible(true);
            PuntosRevision.setLocationRelativeTo(this);
        }
    }//GEN-LAST:event_mPuntosActionPerformed

    private void txtPlacasChasisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlacasChasisKeyPressed

    }//GEN-LAST:event_txtPlacasChasisKeyPressed

    private void NCHASISKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NCHASISKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            bchasis.setText(utils.dbConsult("SELECT DATE_FORMAT(fecha,'" + global.fdatedb + "') from requimant_tbl where "
                    + "camionID='" + utils.dbConsult("select cajaID from cajas_tbl where noeconomico='" + txtPlacasChasis.getText() + "'") + "' and  rev90 is true "
                    + "and categoriaID order by RequisicionID desc limit 1"));
        }
    }//GEN-LAST:event_NCHASISKeyPressed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        Connection con = utils.startConnection();

        try {
            JasperReport reporte;
            JasperPrint jasperPrint = null;
            if (boxTipoReporte.getSelectedIndex() == 0) {
                reporte = (JasperReport) JRLoader.loadObject(new File("Equipment_Control.jasper"));
                Map parametros = new HashMap();

                //parametros.put("clienteID", BoxClienteID.get(BoxCliente.getSelectedIndex()));
                /*parametros.put("fechaini", utils.dateFromFieldtoDB(txtIni.getText()) + "");
                 parametros.put("fechafin", utils.dateFromFieldtoDB(txtFin.getText()) + "");
                
                 parametros.put("fechafilter", fechaz.isSelected() + "");
                
                 parametros.put("almacenID", almID.get(Alm.getSelectedIndex()) + "");
                 parametros.put("articuloID", artID.get(Art.getSelectedIndex()) + "");
                 parametros.put("proveedorID", provID.get(Prov.getSelectedIndex()) + "");*/
                jasperPrint = JasperFillManager.fillReport(reporte, parametros, con);
            }

            utils.windowJasper(jasperPrint);
            con.close();
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex, "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void boxPaisPlacas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxPaisPlacas1ActionPerformed
        if (boxPaisPlacas1.getSelectedIndex() >= 0) {
            FillCombo.cargarEstados(boxEstadoPlacas1, estadosid1, "", 2 + ""); //(boxPaisPlacas.getSelectedIndex() + 1)
        }
    }//GEN-LAST:event_boxPaisPlacas1ActionPerformed

    private void paisxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paisxActionPerformed
        cargaPais();
    }//GEN-LAST:event_paisxActionPerformed

    private void cargaPais() {
        if (paisx.getSelectedIndex() >= 0) {
            //FillCombo.cargarEstados(estx, estxid, " ", (paisx.getSelectedIndex() + 1) + "");
            FillCombo.cargarEstadosAbreviacion(estx, estxid, " ", "" + (paisx.getSelectedIndex() + 1));
        }
    }

    private void estxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estxActionPerformed

    }//GEN-LAST:event_estxActionPerformed

    private void estatus_cajaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_estatus_cajaItemStateChanged
        cargarTabla();
    }//GEN-LAST:event_estatus_cajaItemStateChanged

    private void estatus_cajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estatus_cajaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_estatus_cajaActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
//        if (utils.dbConsult("select IF(" + invid + "+0>0,'Continue','Error')").equals("Error")) {
//            JOptionPane.showMessageDialog(dEntradaSalida, "Guarde su orden antes de enerar una solicitud a taller", "Error", JOptionPane.ERROR_MESSAGE);
//        } else {
//            RequisicionMantenimiento frame = new RequisicionMantenimiento(global.usuario + "", "");
//            frame.almacen = "Patios";
//            frame.inventarioexternoID = invid;
//
//            frame.addWindowListener(new java.awt.event.WindowAdapter() {
//                public void windowClosing(java.awt.event.WindowEvent evt) {
//                    frame.removeAll();
//                    frame.dispose();
//                    System.gc();
//                }
//            });
//
//            if (jComboBox1.getSelectedIndex() == 0) {
//                frame.boxCategoria.setSelectedIndex(1);
//                frame.boxUnidades.setSelectedItem(cajachasisbox.getSelectedItem().toString());
//            }
//
//            if (jComboBox1.getSelectedIndex() == 1) {
//                frame.boxCategoria.setSelectedIndex(0);
//                frame.boxUnidades.setSelectedItem(camionx.getSelectedItem().toString());
//
//                if (camionx.getSelectedIndex() == 0) {
//                    frame.ext.setText(camiones.getText());
//                }
//            }
//
//            frame.boxStatus.setSelectedIndex(1);
//            frame.boxChoferes.setSelectedIndex(frame.choferesid.indexOf(chofereslocalid.get(boxChofer.getSelectedIndex())));
//
//            if (PatioID.get(Patio.getSelectedIndex()).equals("4")) {
//                frame.taller.setSelectedIndex(0);
//            } else if (PatioID.get(Patio.getSelectedIndex()).equals("2")) {
//                frame.taller.setSelectedIndex(1);
//            } else if (PatioID.get(Patio.getSelectedIndex()).equals("1")) {
//                frame.taller.setSelectedIndex(2);
//            }
//
//            frame.boxEstadoCarga.setSelectedItem(boxEstadoCarga.getSelectedItem().toString());
//            frame.boxTipoMantenimiento.setSelectedIndex(2);
//            frame.cliente1.setSelectedIndex(frame.cliente1id.indexOf(cliente1id.get(cliente1.getSelectedIndex())));
//            frame.lFallas.setSelectedIndex(2);
//
//            dEntradaSalida.dispose();
//            Config.openWindow(frame);
//            frame.toFront();
//        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed

        EquipmentControl.setLocationRelativeTo(this);
        if (EquipmentControlGenerado == false) {
            actualizarListado();
            EquipmentControlGenerado = true;
        }
        EquipmentControl.setVisible(true);
        //jDialog1.setAlwaysOnTop(true);

    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        String ret = utils.exportTabletoExcel(this, CTRL);
        if (!ret.isEmpty()) {
            JOptionPane.showMessageDialog(this, ret, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed

        actualizarListado();
    }//GEN-LAST:event_jButton18ActionPerformed

    private void boxEstadoCargaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_boxEstadoCargaItemStateChanged
        if (boxEstadoCarga.getSelectedIndex() == 0) {
            txtSello.setEnabled(false);
        } else {
            txtSello.setEnabled(true);
        }
    }//GEN-LAST:event_boxEstadoCargaItemStateChanged

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        ContenedorInterno.setLocationRelativeTo(dEntradaSalida);
        ContenedorInterno.toFront();
        ContenedorInterno.setVisible(true);
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        txtContenedor.setText(boxcontenedorx.getSelectedItem().toString());
        ContenedorInterno.dispose();
    }//GEN-LAST:event_jButton20ActionPerformed

    private void busquedaContenedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_busquedaContenedorKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            cargarContenedorInterno();
        }
    }//GEN-LAST:event_busquedaContenedorKeyPressed

    private void gradeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_gradeItemStateChanged
        if (grade.getSelectedIndex() == 0) {
            jComboBox1.setEnabled(false);
        } else {
            jComboBox1.setEnabled(true);
        }
    }//GEN-LAST:event_gradeItemStateChanged

    private void botandoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_botandoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_botandoItemStateChanged

    private void cajachasisboxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cajachasisboxItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cajachasisboxItemStateChanged

    private void cajachasisboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cajachasisboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cajachasisboxActionPerformed

    private void cajachasisboxExternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cajachasisboxExternoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cajachasisboxExternoActionPerformed

    private void cajachasisboxExternoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cajachasisboxExternoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cajachasisboxExternoKeyPressed

    private void cajachasisboxExternoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cajachasisboxExternoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cajachasisboxExternoKeyReleased

    private void FilterFullItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_FilterFullItemStateChanged
        cargarTabla();
    }//GEN-LAST:event_FilterFullItemStateChanged

    private void icont_contenedorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_icont_contenedorItemStateChanged
        if (icont_contenedor.getSelectedIndex() >= 0) {

            bandLoad = true;
            loadDriverMove();

        }
    }//GEN-LAST:event_icont_contenedorItemStateChanged

    private void schasisItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_schasisItemStateChanged
        cargarTabla();
    }//GEN-LAST:event_schasisItemStateChanged

    private void PatioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_PatioItemStateChanged
        if (Patio.getSelectedIndex() >= 0) {

            int R = Integer.parseInt(utils.dbConsult("select IFNULL(R,0) from patios_tbl where patioID='" + PatioID.get(Patio.getSelectedIndex()) + "'"));
            int G = Integer.parseInt(utils.dbConsult("select IFNULL(G,0) from patios_tbl where patioID='" + PatioID.get(Patio.getSelectedIndex()) + "'"));
            int B = Integer.parseInt(utils.dbConsult("select IFNULL(B,0) from patios_tbl where patioID='" + PatioID.get(Patio.getSelectedIndex()) + "'"));

            yardaTittle.setText(Patio.getSelectedItem().toString());
            yardaTittle.setForeground(new Color(R, G, B));
        }
    }//GEN-LAST:event_PatioItemStateChanged

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        // TODO add your handling code here:
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        Transbordos frame = new Transbordos();
        Config.openWindow(frame);
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jButton21ActionPerformed

    private void facturadosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_facturadosItemStateChanged

        cargarFacturacion();
        // TODO add your handling code here:
    }//GEN-LAST:event_facturadosItemStateChanged

    private void bControlEquipmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bControlEquipmentActionPerformed
        dEquipmentControl temp = new dEquipmentControl(this, true);
        temp.setLocationRelativeTo(this);
        temp.setVisible(true);
    }//GEN-LAST:event_bControlEquipmentActionPerformed
    private void cargarTablaDm(String camionId) {

        String query = "select ItinerarioID from itinerarios_tbl where CamionID = " + camionId + " and status = 1\n"
                + "order by Fecha asc";
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        dmId.clear();
        modelDm.setRowCount(0);
        try {
            con = utils.startConnection();
            st = con.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {

                dmId.add(rs.getString("ItinerarioID"));
                modelDm.addRow(new Object[]{rs.getString("ItinerarioID")});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los DM por camion");
        }
    }
    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        if (camionx.getSelectedIndex() >= 0) {
            cargarTablaDm(camionidx.get(camionx.getSelectedIndex()));

//        dEntradaSalida.dispose();
            dTractoDM.setLocationRelativeTo(this);
//        dTractoDM.setVisible(true);
            dTractoDM.repaint();
//            dTractoDM.setAlwaysOnTop(true);
            
            dEntradaSalida.setVisible(false);
            dTractoDM.setVisible(true);
            
//        
//        dEntradaSalida.setLocationRelativeTo(this);
//        dEntradaSalida.setVisible(true);
        }
    }//GEN-LAST:event_jButton23ActionPerformed

    private void boxFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxFiltroActionPerformed
        cargarUnidades();
    }//GEN-LAST:event_boxFiltroActionPerformed

    private void dmTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dmTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dmTextActionPerformed

    private void tDmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tDmMouseClicked
        int row = tDm.getSelectedRow();
        if (evt.getClickCount() == 2) {
            if (row >= 0) {
                String dmStr = "";
                prt("Antes de " + tractorInventario);
                tractorInventario = true;
                prt("Despues de " + tractorInventario);
                dmStr = tDm.getValueAt(row, 0).toString();
                txtDriverMove.setText(dmStr);
                dTractoDM.dispose();
                dEntradaSalida.setVisible(true);
                bandLoad = false;
                
                loadDriverMove();
            }
        }
    }//GEN-LAST:event_tDmMouseClicked

    void cargarContenedorInterno() {
        boxcontenedorx.removeAllItems();
        boxcontenedorxID.clear();

        String busqueda = "";
        if (!busquedaContenedor.getText().equals("")) {
            busqueda = " and noeconomico like '%" + busquedaContenedor.getText() + "%' ";
        } else {
            busqueda = "";
        }

        String query = "SELECT NoEconomico, CajaID from cajas_tbl where Status = true and estatus_caja!='Inactivo (F/S)' " + busqueda + " order by CAST(NoEconomico as UNSIGNED)";

        if (!query.isEmpty()) {
            Connection con = null;
            Statement state = null;
            ResultSet rs = null;
            try {
                con = utils.startConnection();
                state = con.createStatement();
                rs = state.executeQuery(query);
                while (rs.next()) {
                    boxcontenedorx.addItem(rs.getString(1));
                    boxcontenedorxID.add(rs.getString(2));
                }

            } catch (SQLException e) {
                System.out.println("Error " + e);
            } finally {
                utils.closeAllConnections(con, state, rs);
            }
        }
    }

    void actualizarListado() {

        equipmentctrl.setRowCount(0);

        Connection con;
        con = utils.startConnection();

        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT estatus_caja, "
                    + "IF(descr='' or descr is null,'-',descr) AS descr , IFNULL(Noeconomico,'-') as equipo, IFNULL(tamano,'') as tamano, "
                    + "IF( (select tamano)='','-',(select tamano) ) as tamanox ,now() as hoy, \n"
                    //+ "ifnull((select destino from inventarioexterno_tbl where inventarioID=TBL.inventarioID limit 1),'-') as ubicacion,\n"
                    + ""
                    + "getUbicacionDespachoPatios(TBL.noeconomico) as ubicacionD,\n"
                    + "DATE_FORMAT(FechaD, '%m/%d/%Y')  as FechaDx, "
                    + //DATE(FechaD) as FechaDx, 
                    "DATE_FORMAT(FechaD,'%H:%i') as HoraD,  "
                    + //TIME(FechaD) as HoraD,
                    "IFNULL((select DATEDIFF(now(), (select FechaD))+1),'') as diasD,\n"
                    + "getCargaDespachoPatios(TBL.noeconomico)  as cargaD, \n"
                    + "clienteD, "
                    //+ "getClienteDespachoPatios(TBL.noeconomico) as clienteD, "
                    + ""
                    + "\n"
                    /*+ "IFNULL((SELECT \n"
                    + "(select nombre from locaciones_tbl where locacionID=IF(iti.RutaID > 0, (SELECT LocacionTOID from rutas_tbl where rutas_tbl.RutaID = iti.RutaID), LocacionOrigenID)) as iddireccion\n"
                    + "from workcontenedores_tbl as wc \n"
                    + "left join itinerarios_tbl as iti on WContenedorID = WcontFK\n"
                    + "where wc.Status = true and iti.Status > 0 and wc.Contenedor = TBL.noeconomico\n"
                    + "and Carga is not null\n"
                    + "order by IF(Carga is null, 0, 1), Carga desc \n"
                    + "limit 1),'-') as ubicacionD,\n"
                    + "\n"
                    + "IFNULL((SELECT DATE_FORMAT(iti.Carga, '%m/%d/%Y') \n"
                    + "from workcontenedores_tbl as wc \n"
                    + "inner join itinerarios_tbl as iti on WContenedorID = WcontFK\n"
                    + "where wc.Status = true and iti.Status > 0 and wc.Contenedor = TBL.noeconomico and Carga is not null\n"
                    + "order by IF(Carga is null, 0, 1), Carga desc\n"
                    + "limit 1),'-') as FechaD,\n"
                    + "IFNULL((SELECT DATE_FORMAT(iti.Carga,'%H:%i') \n"
                    + "from workcontenedores_tbl as wc \n"
                    + "inner join itinerarios_tbl as iti on WContenedorID = WcontFK\n"
                    + "where wc.Status = true and iti.Status > 0 and wc.Contenedor = TBL.noeconomico and Carga is not null\n"
                    + "order by IF(Carga is null, 0, 1), Carga desc\n"
                    + "limit 1),'-') as HoraD,\n"
                    + "\n"
                    + "IFNULL((select DATEDIFF(now(), (select FechaD))+1),'-') as diasD,\n"
                    + "\n"
                    + "IFNULL((SELECT IF(icont_tbl.Estado != 1, 'E', 'L')\n"
                    + "from workcontenedores_tbl as wc \n"
                    + "left join icont_tbl on WContenedorID = WContID\n"
                    + "left join itinerarios_tbl as iti on iti.ItinerarioID = icont_tbl.ItinerarioID\n"
                    + "\n"
                    + "where wc.Status = true and iti.Status > 0 and wc.Contenedor = TBL.noeconomico\n"
                    + "and Carga is not null\n"
                    + "order by IF(Carga is null, 0, 1), Carga desc\n"
                    + "limit 1),'-')  as cargaD, \n"
                    + "\n"
                    + "IFNULL((SELECT \n"
                    + "(select ncomercial from clientes_tbl where clientes_tbl.clienteID=wt.`ClienteID`)\n"
                    + "from workorder_tbl as wt \n"
                    + "left join workcontenedores_tbl as wc on wc.`WorkOrderID` = wt.`WorkID`\n"
                    + "left join itinerarios_tbl as iti on wc.WContenedorID = WcontFK\n"
                    + "where wc.Status = true and iti.Status > 0 and wc.Contenedor = TBL.noeconomico\n"
                    + "and Carga is not null\n"
                    + "order by IF(Carga is null, 0, 1), Carga desc\n"
                    + "limit 1), '-') as clienteD, "*/
                    + ""
                    + ""
                    //+ "if( (select clienteID)='','-',(select clienteID) ) as clienteDx, "
                    + "\n"
                    + "IFNULL((select Sello from inventarioexterno_tbl where inventarioID=TBL.inventarioID limit 1),'') as sellos, IF( (select sellos)='','-',(select sellos) ) as sellox, "
                    + "IFNULL((select DATE_FORMAT(FechaEvento, '%m/%d/%Y') from inventarioexterno_tbl where inventarioID=TBL.inventarioID limit 1),'-') as fechax,\n"
                    + "IFNULL((select DATE_FORMAT(fechaevento,'%H:%i') from inventarioexterno_tbl where inventarioID=TBL.inventarioID limit 1),'-') as fechaxhora,\n"
                    + "IF(ubicacionpatio!='' or ubicacionpatio is not null,'IN','-') as ingresopatio, "
                    + "IF(ubicacionpatio!='' or ubicacionpatio is not null,ubicacionpatio,'-') as ubicacionpatio, "
                    + "IFNULL((select IF(EstadoCarga = 0, 'E', 'L') from inventarioexterno_tbl where inventarioID=TBL.inventarioID limit 1),'-') as carga,\n"
                    + "IFNULL((select nombre from unidadnegocio_tbl where `UnidadID`=unegocioID),'-') as unidad_negocio, IF( (SELECT unidad_negocio)='','-',(SELECT unidad_negocio) ) AS unidad_negociox, "
                    + "\n"
                    + "IFNULL((select DATEDIFF(now(), (select fechaevento from inventarioexterno_tbl where inventarioID=TBL.inventarioID limit 1))+1),'-') as dias,\n"
                    + "\n"
                    + "IFNULL((select ncomercial from clientes_tbl where clienteID=((select clienteID from inventarioexterno_tbl where inventarioID=TBL.inventarioID limit 1))),'-') as cliente, "
                    + "if( (select cliente)='','-',(SELECT cliente) ) as clienteX, "
                    + "IFNULL((select nota from inventarioexterno_tbl where inventarioID=TBL.inventarioID limit 1),'-') as comment, IF( (select comment)='','-',(select comment) ) as commentx, "
                    + "\n"
                    + "IFNULL(DATE_FORMAT((select fecha from requimant_tbl where camionID=cajaID and categoriaID=2 and status>0 and (status!=4 or status!=5)\n"
                    + "order by requisicionID desc limit 1),'%m/%d/%Y'),'-') as Ultima_Entrada_Taller, IF( (select Ultima_Entrada_Taller)='','-',(select Ultima_Entrada_Taller) ) as Ultima_Entrada_Tallerx, "
                    + "\n"
                    + "IFNULL((select DATEDIFF(now(), fecha) from requimant_tbl where camionID=cajaID and categoriaID=2 and status>0 and (status!=4 or status!=5)\n"
                    + "order by requisicionID desc limit 1),'') as Dias_Taller, IF( (select Dias_Taller)='','-',(select Dias_Taller)) as Dias_Tallerx, "
                    + "\n"
                    + "IFNULL((select dias+0),0) as diax,\n"
                    + "(select 0) as Zero,\n"
                    + "(select 6) as Six,\n"
                    + "(select 10) as Ten   \n"
                    + "\n"
                    + "\n"
                    + "FROM \n"
                    + "\n"
                    + "(select *,\n"
                    + "(select inventarioID from inventarioexterno_tbl where contenedor=cajas_tbl.`NoEconomico` order by inventarioID desc limit 1) as inventarioID, "
                    + "getFechaDespachoPatios(noeconomico) as FechaD, "
                    + "IFNULL(getClienteDespachoPatios(noeconomico),'-') as clienteD, "
                    + "(select (select nombre from patios_tbl where patioID=inventarioexterno_tbl.`PatioID`) from inventarioexterno_tbl where contenedor=noeconomico "
                    + "and (select anteriorID from inventarioexterno_tbl as w where anteriorID=inventarioexterno_tbl.InventarioID limit 1) is null and TipoEvento=1 "
                    + "order by inventarioID desc limit 1) as ubicacionpatio "
                    + "from cajas_tbl\n"
                    + "where status is true ) TBL\n"
                    + "\n"
                    + "Order by (select diax) desc");
            while (rs.next()) {

                equipmentctrl.addRow(new Object[]{
                    rs.getString("equipo"),
                    rs.getString("descr"),
                    rs.getString("tamanox"),
                    rs.getString("ubicacionD"),
                    rs.getString("FechaDx"),
                    rs.getString("HoraD"),
                    rs.getString("diasD"),
                    rs.getString("cargaD"),
                    rs.getString("clienteD"),
                    //rs.getString("ubicacion"),
                    rs.getString("ingresopatio"),
                    rs.getString("ubicacionpatio"),
                    rs.getString("fechax"),
                    rs.getString("fechaxhora"),
                    rs.getString("dias"),
                    rs.getString("carga"),
                    rs.getString("clienteX"),
                    rs.getString("sellox"),
                    rs.getString("unidad_negociox"),
                    rs.getString("commentx"),
                    rs.getString("Ultima_Entrada_Tallerx"),
                    rs.getString("Dias_Tallerx"),
                    rs.getString("estatus_caja")});
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    void cargarPuntos(String inventarioID) {

        String inout = utils.dbConsult("select TipoEvento from inventarioexterno_tbl where inventarioID='" + inventarioID + "'");

        String query = "";
        if (inout.equals("1")) {
            query = "select id,(select nombre from listadoinspecciones_tbl where listadoID=inventarioexternoinspecciones_tbl.`ListadoID`) as lista, entrada as reg, "
                    + "comentarioentrada, fotoentrada, comentariosalida, fotosalida, replace(fotoentrada,'C:/xampp/htdocs/crownApi/revisiones/','') as fe, "
                    + "replace(fotosalida,'C:/xampp/htdocs/crownApi/revisiones/','') as fs "
                    + "from inventarioexternoinspecciones_tbl "
                    + "where inventarioID='" + inventarioID + "'";
        }

        if (inout.equals("2")) {
            query = "select id,(select nombre from listadoinspecciones_tbl where listadoID=inventarioexternoinspecciones_tbl.`ListadoID`) as lista, salida as reg, "
                    + "comentarioentrada, fotoentrada, comentariosalida, fotosalida, replace(fotoentrada,'C:/xampp/htdocs/crownApi/revisiones/','') as fe, "
                    + "replace(fotosalida,'C:/xampp/htdocs/crownApi/revisiones/','') as fs "
                    + "from inventarioexternoinspecciones_tbl "
                    + "where InventarioSalidaID='" + inventarioID + "'";
        }

        Connection con;
        con = utils.startConnection();

        mpuntos.setRowCount(0);
        puntosID.clear();

        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                puntosID.add(rs.getString("id"));
                fotoe.add(rs.getString("fotoentrada"));
                fotos.add(rs.getString("fotosalida"));
                mpuntos.addRow(new Object[]{rs.getString("lista"), rs.getBoolean("reg"), rs.getString("comentarioentrada"), rs.getString("fe"), rs.getString("comentariosalida"), rs.getString("fs")});

                /*
                 mpuntos.addColumn("Comentario Entrada");
                 mpuntos.addColumn("Foto Entrada");
                 mpuntos.addColumn("Comentario Salida");
                 mpuntos.addColumn("Foto Salida");
                 */
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    void cargarEntradasFiltradoDriver() {

        String virtual = "", vt = "";
        virtual = utils.dbConsult("select ifnull(vt,false) from patios_tbl where  patioID='" + patiosid.get(BoxPatios.getSelectedIndex()) + "'");

        if (virtual.equals("1")) {
            vt = " and (select ifnull(vt,false) from patios_tbl where  patioID='" + patiosid.get(BoxPatios.getSelectedIndex()) + "') is true ";
        } else {
            vt = " and patioID='" + utils.dbConsult("select patioID from usuarios_Tbl where usuarioID='" + global.usuario + "'") + "' ";
        }

        String last = utils.dbConsult("select inventarioID from inventarioexterno_tbl where contenedor = '" + txtContenedor.getText() + "' and anteriorID=0 "
                + " " + vt + " order by inventarioID desc limit 1");

        String query = "select inventarioID,WContenedorID, contenedor,(select NComercial from clientes_tbl where clienteID=inventarioexterno_tbl.clienteID) as cliente, AnteriorID, "
                + "IFNULL((select itinerarioID from itinerarios_tbl where WcontFK=inventarioexterno_tbl.WContenedorID order by itinerarios_tbl.ItinerarioID desc limit 1 ),'') as driverm "
                + "from inventarioexterno_tbl where inventarioID='" + last + "' and anteriorID=0 "
                + " " + vt + " order by inventarioID desc limit 1";

        //INSERT INTO inventarioexterno_tbl (Contenedor, PlacasChasis, Sello, PaisID, EstadoID, ChoferID, NombreChofer, FechaLlegada, UsuarioLlegadaID, Nota, EstadoCarga, UsuarioID, Fecha, PatioID, Tamano,clienteID)
        contenedor_tablemodel.setRowCount(0);
        contenedorid.clear();
        Anteriorid.clear();
        inventariosalidaid.clear();
        Connection con = utils.startConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            int i = 1;
            while (rs.next()) {
                contenedor_tablemodel.addRow(new Object[]{rs.getString("cliente"), rs.getString("WContenedorID"), rs.getString("contenedor"), rs.getString("driverm")});
                contenedorid.add(rs.getString("WContenedorID"));
                Anteriorid.add(rs.getString("AnteriorID"));
                inventariosalidaid.add(rs.getString("inventarioID"));
                AnteriorID = rs.getString("inventarioID");
            }

            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    void cargarAccesos() {

        String query = "SELECT patioID, nombre from patios_tbl where vt is true and Renta is not true";
        Connection con;
        con = utils.startConnection();

        maccesos.setRowCount(0);
        accesoid.clear();

        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                accesoid.add(rs.getInt("patioID"));
                maccesos.addRow(new Object[]{rs.getString("nombre")});
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }

    }

    void cargarFacturacion() {

        String facturado = "";
        if (facturados.isSelected()) {
            facturado = " where factura is not null ";
        } else {
            facturado = "";
        }

        String query = "SELECT *,(select NComercial from clientes_tbl where clienteID=inventarioexterno_tbl_facturacion.clienteID) as client, "
                + "(select nombre from perfilcobro where ID=perfilID) as perfil, DATE_FORMAT(FechaInicial,'%d/%m/%Y') as ini, DATE_FORMAT(FechaFinal,'%d/%m/%Y') as fin "
                + "from inventarioexterno_tbl_facturacion " + facturado;

        facturacion_tablemodel.setRowCount(0);
        facturacionID.clear();
        Connection con = utils.startConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                facturacion_tablemodel.addRow(new Object[]{rs.getString("client"), rs.getString("perfil"), rs.getString("factura"), rs.getString("ini"), rs.getString("fin")});
                facturacionID.add(rs.getString("factID"));
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    /*private void generarDocumento() {//revisar tambien funcion de generar documentoSSSSS
        
     //if (!clientefac.equals("0")) {
     //son 3 tipos de factura: Factura normal con contenedores y extras en el mismo doc, factura con los contenedores en uno y los extras en otro, y factura con todo separado, cada concepto y contenedor
     String moneda = "0";
     if (!boxMonedaFac.getSelectedItem().equals(utils.dbConsult("SELECT Nombre from monedas_tbl where Moneda = 0"))) {
     moneda = "1";
     }
     String importeprincipal = txtTotal.getText().replace(",", "");
     String foliodoc = "";
     String iva = txtIVA.getText();
     String ivaret = txtIVARET.getText();
     String isr = txtISR.getText();
     if (!esfactura) {
     foliodoc = utils.dbConsult("SELECT FolioInvoice from utils_tbl limit 1");
     iva = "0";
     ivaret = "0";
     isr = "0";
     }
     //pablo martin
     //hay un campo "TipoFactura" cuando lo insertes ponlo = 2
     //El campo "Factura" sera = true cuando es factura mexicana, false cuando es factura americana
     //el Campo "NoFactura" necesitaras llevar el consecutivo para lo americano, lo sacas de arribita, ahi esta la variable, le sumas +1 ya que este insertado correctamente el registro en facturas_tbl.... en caso de mexicano lo dejas vacio ''
     //los impuestos en esta linea ya no son necesarios.
     //"SepararConceptosExtra" Simpre en false
     String id = utils.dbInsert("INSERT INTO facturas_tbl(NoFactura, Monto, TotalFactura,ClienteID,Fecha,Moneda,TipoCambio,TipoCambioAlta,FechaFac,Nota,IVA,IVARET,ISR,FechaVencimiento, SepararConceptosExtra, UsuarioID, Factura) VALUES("
     + "'" + foliodoc + "','" + importeprincipal + "',(SELECT '" + importeprincipal + "'*(1+(SELECT digits('" + iva + "')/100)-(SELECT digits('" + ivaret + "')/100)-(SELECT digits('" + isr + "')/100))),'" + clientefac + "',(now()),"
     + "'" + moneda + "',(SELECT digits('" + txtTipoCambio.getText() + "')),(SELECT digits('" + txtTipoCambio.getText() + "')),(now()),'',(SELECT digits('" + iva + "')/100),(SELECT digits('" + ivaret + "')/100),(SELECT digits('" + isr + "')/100),(SELECT DATE_ADD(CURDATE(), INTERVAL DiasCredito DAY) from clientes_tbl where clientes_tbl.ClienteID = '" + clientefac + "'), "
     + "" + cFacturaWO.isSelected() + ", '" + global.usuario + "', " + esfactura + ")");

     if (id.length() <= 11 && !id.isEmpty()) {
     procesarSeleccionados(id);
     if (!esfactura) {

     if (global.usuario != 1) {
     String response;
     try {
     response = utils.descargaMasivaInvoice(global.path + "Facturas", clientefac, id, id, true);
     if (!response.isEmpty()) {
     JOptionPane.showMessageDialog(dSeleccionados, response, "Error", JOptionPane.ERROR_MESSAGE);
     } else {

     }
     } catch (IOException ex) {
     utils.errorGenerado("Facturas / gendocs / ioex singular / " + ex.toString());
     }
     }
     }

     txtTotal.setText("0");
     cargarTabla();
     seleccionFactura();
     } else {//if id error

     JOptionPane.showMessageDialog(dSeleccionados, "Error " + id, "Error", JOptionPane.ERROR_MESSAGE);
     }

     /*} else {
     String msj = "";
     if (clientefac.equals("0")) {
     msj = "Favor de seleccionar un cliente del listado.\n";
     }
     JOptionPane.showMessageDialog(this.getFocusOwner(), "Error\n" + msj, "Error", JOptionPane.ERROR_MESSAGE);
     }*/
    //}
    private String[] getArrayTable(String[] data) {
        String temp[] = new String[jTable1.getColumnCount()];
        for (int i = 0; i < data.length; i++) {
            temp[i] = data[i];
        }

        return temp;
    }

    private void resetter() {
        invid = "0";

        solochasis.setSelected(false);

        cliente.setSelectedIndex(0);
        try{
                    cliente1.setSelectedIndex(0);
        }catch(IllegalArgumentException w){
            System.out.println("Fallo al cargas el componente: " + w);
        }

        camionx.setSelectedIndex(0);
        camiones.setText("");
        placasunidad.setText("");
        placasunidad1.setText("");
        NCHASIS.setText("");
        CARRIER.setText("");

        boxPaisPlacas.setSelectedIndex(0);
        boxPaisPlacas1.setSelectedIndex(1);
        boxEstadoPlacas.setSelectedIndex(0);
        boxEstadoPlacas1.setSelectedIndex(1);

        txtPlacasChasis.setText("");
        //txtPlacasChasis1.setText("");
        paisx.setSelectedIndex(0);
        txtSello.setText("");
        Orgn.setSelectedIndex(0);
        destino.setText("");

        boxChofer.setSelectedIndex(0);
        txtNombreChofer.setText("");

        txtNota.setText("");
        botando.setSelected(false);
        boxTamano.setSelectedIndex(0);
        boxEstadoCarga.setSelectedIndex(0);
        txtSello.setEnabled(false);
        txtFLlegada2.setText(hoy.getText());
        //txtFLlegada.setText("  .  .    ");
        //txtHLlegada.setValue(utils.dbConsult("select DATE_FORMAT(CURTIME(), '%H:%i')"));rr
        ConfigLib.WebDate(WebFLlegada, 14);
        txtFLlegada2.setText(WebFLlegada.getText());
        //txtHLlegada.setValue(null);
        txtContenedor.setText("");
        grade.setSelectedIndex(0);
        assignedto.setText("");

        Patio.setSelectedIndex(PatioID.indexOf(utils.dbConsult("select patioID from usuarios_tbl where usuarioID='" + global.usuario + "'")));

        System.out.println("patio:" + patiosid.get(BoxPatios.getSelectedIndex()));
        String virtual = "";
        virtual = utils.dbConsult("select ifnull(vt,false) from patios_tbl where  patioID='" + patiosid.get(BoxPatios.getSelectedIndex()) + "'");
        if (virtual.equals("1")) {
            Patio.setSelectedItem(BoxPatios.getSelectedItem().toString());
        }

        edicion.setText("");
        edicion1.setText("");

        Camion.setSelected(false);

        String fecha = utils.dbConsult("SELECT DATE_FORMAT(now(), '" + global.fdatedb + " %H:%i')");
        txtFLlegada2.setText(fecha.split(" ")[0]);
        txtHLlegada.setText(fecha.split(" ")[1]);

        String airway = "";
        String patio ="";
        String patioCondicion = "";
        try{
         patio = utils.dbConsult("select patioID from usuarios_tbl where usuarioID='" + global.usuario + "'");
         patioCondicion = Patio.getItemAt(PatioID.indexOf(patio));
        }catch(NullPointerException e){
            System.out.println("Fallo en la condicional: " + e);
        }
        if (patioCondicion != null) {
            if(patioCondicion.equals("AIRWAY")){
              airway = "AIR";
            }else {
            airway = patioCondicion;
        }
        } 

        if (InOut.getSelectedIndex() == 0) {
            destino.setText(Patio.getItemAt(PatioID.indexOf(utils.dbConsult("select patioID from usuarios_tbl where usuarioID='" + global.usuario + "'"))));
            Orgn.setSelectedIndex(0);
        } else {
            Orgn.setSelectedItem(airway);
            destino.setText("");
        }

        txtDriverMove.setText("");
        licencia.setText("");
        cajachasisbox.setSelectedIndex(0);

        //capturaFull=false;
        Fullx.setVisible(false);
        Fullx1.setVisible(false);

        cajachasisboxExterno.setText("");
        sellocomplementario.setText("");

        if (InOut.getSelectedIndex() == 1) {
            icont_contenedor.setVisible(true);
        } else {
            icont_contenedor.setVisible(false);
        }

    }

    private void cargarEntradaSalida() {

        Connection con = utils.startConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT Contenedor,"
                    + "DATE_FORMAT(FechaEvento, '" + global.fdatedb + "') as fsalida,"
                    + "DATE_FORMAT(FechaEvento, '%H:%i') as hsalida,"
                    + "DATE_FORMAT(FechaEvento, '" + global.fdatedb + "') as fllegada,"
                    + "DATE_FORMAT(FechaEvento, '%H:%i') as hllegada,"
                    + "PlacasChasis,"
                    + "Sello,"
                    + "IF(PaisID = 0, 1,PaisID) as paisid,"
                    + "EstadoID as estadoid,"
                    + "Tamano,"
                    + "Nota,"
                    + "NombreChofer,"
                    + "EstadoCarga,"
                    + "ChoferID, "
                    + "camion, "
                    + "clienteID, "
                    + "EquipmentProvider, "
                    + "origen, "
                    + "camionID, "
                    + "botando,"
                    + "PlacasUnidad,"
                    + "PlacasUnidadUSA,"
                    + "Destino, "
                    + "NumeroChasis, "
                    + "Carrier, "
                    + "patioID, "
                    + "grade, "
                    + "assignedto, "
                    + "IF(TipoEvento=1,'Entrada','Salida') as Entsal, IFNULL(isCamion,false) as izCamion, estadoUSAID, "
                    + "IFNULL(perfilCobroID,0) AS PFX, estadoplacasmex, estadoplacasusa, placaschasisUSA, "
                    + "remolqueEstado, remolquePais, solochasis, itinerarioID, licencia, cajaID, chasis, sellocomplementario "
                    + "from inventarioexterno_tbl "
                    + "where InventarioID = '" + inventarioID.get(jTable1.getSelectedRow()) + "'");

            while (rs.next()) {

                solochasis.setSelected(rs.getBoolean("solochasis"));

                InOut.setSelectedItem(rs.getString("EntSal"));

                cliente.setSelectedIndex(clienteid.indexOf((rs.getString("clienteID"))));
                if (cliente.getSelectedIndex() == -1) {
                    cliente.setSelectedIndex(0);
                }

                cargarPerfilesRegistro();

                cliente1.setSelectedIndex(cliente1id.indexOf((rs.getString("EquipmentProvider"))));
                if (cliente1.getSelectedIndex() == -1) {
                    cliente1.setSelectedIndex(0);
                }

                camionx.setSelectedIndex(camionidx.indexOf((rs.getString("camionID"))));

                System.out.println((inventarioID.get(jTable1.getSelectedRow())));
                System.out.println(rs.getString("clienteID") + " " + rs.getString("EquipmentProvider") + " " + rs.getString("camionID"));

                camiones.setText(rs.getString("camion"));
                Orgn.setSelectedItem(rs.getString("origen"));
                txtContenedor.setText(rs.getString("Contenedor"));
                boxChofer.setSelectedIndex(chofereslocalid.indexOf(rs.getString("ChoferID")));
                txtFLlegada2.setText(rs.getString("fllegada"));
                txtHLlegada.setText(rs.getString("hllegada"));
                txtPlacasChasis.setText(rs.getString("placaschasis"));
                //txtPlacasChasis1.setText(rs.getString("placaschasisUSA"));
                paisx.setSelectedItem(rs.getString("remolquePais"));
                estx.setSelectedIndex(estxid.indexOf(rs.getString("remolqueEstado")));
                System.out.println(rs.getString("remolquePais") + " pais - estado: " + rs.getInt("remolqueEstado"));
                txtSello.setText(rs.getString("Sello"));
                boxPaisPlacas.setSelectedIndex(rs.getInt("paisid") - 1);

                FillCombo.cargarEstados(boxEstadoPlacas, estadosid, "", rs.getString("paisid"));
                FillCombo.cargarEstados(boxEstadoPlacas1, estadosid1, "", "2");

                if (rs.getInt("estadoid") > 0) {
                    boxEstadoPlacas.setSelectedIndex(estadosid.indexOf(rs.getString("estadoid")));
                } else {
                    boxEstadoPlacas.setSelectedIndex(0);
                }

                if (rs.getInt("EstadoUSAID") > 0) {
                    boxEstadoPlacas1.setSelectedIndex(estadosid1.indexOf(rs.getString("EstadoUSAID")));
                } else {
                    boxEstadoPlacas1.setSelectedIndex(0);
                }

                placasunidad2.setText(rs.getString("estadoplacasmex"));
                placasunidad3.setText(rs.getString("estadoplacasusa"));

                boxTamano.setSelectedItem(rs.getString("Tamano"));
                txtNombreChofer.setText(rs.getString("NombreChofer"));
                boxEstadoCarga.setSelectedIndex(rs.getInt("EstadoCarga"));
                txtNota.setText(rs.getString("Nota"));

                botando.setSelected(rs.getBoolean("botando"));
                placasunidad.setText(rs.getString("placasunidad"));
                placasunidad1.setText(rs.getString("placasunidadUSA"));
                destino.setText(rs.getString("destino"));

                NCHASIS.setText(rs.getString("NumeroChasis"));
                CARRIER.setText(rs.getString("Carrier"));

                assignedto.setText(rs.getString("assignedto"));
                grade.setSelectedItem(rs.getString("grade"));
                if (grade.getSelectedIndex() == -1) {
                    grade.setSelectedIndex(0);
                }

                edicion.setText(utils.dbConsult("SELECT concat((select Nombre from usuarios_Tbl where usuarioID=UsuarioEventoID),' ',"
                        + "DATE_FORMAT(fechaedicion,'%m/%d/%Y %H:%i')) from inventarioexterno_tbl where inventarioID='" + inventarioID.get(jTable1.getSelectedRow()) + "'"));

                edicion1.setText(utils.dbConsult("SELECT concat((select Nombre from usuarios_Tbl where usuarioID=CreacionUsuarioEventoID),' ',"
                        + "DATE_FORMAT(fecha,'%m/%d/%Y %H:%i')) from inventarioexterno_tbl where inventarioID='" + inventarioID.get(jTable1.getSelectedRow()) + "'"));

                Camion.setSelected(rs.getBoolean("izCamion"));

                perfilBuscador1.setSelectedIndex(perfilBuscador1ID.indexOf(rs.getString("PFX")));
                if (perfilBuscador1.getSelectedIndex() == -1) {
                    perfilBuscador1.setSelectedIndex(0);
                }

                bcamion.setText(utils.dbConsult("SELECT DATE_FORMAT(fecha,'" + global.fdatedb + "') from requimant_tbl where camionID='" + camionidx.get(camionx.getSelectedIndex()) + "' and  rev90 is true "
                        + "and categoriaID=1 order by RequisicionID desc limit 1"));

                bchasis.setText(utils.dbConsult("SELECT DATE_FORMAT(fecha,'" + global.fdatedb + "') from requimant_tbl where "
                        + "camionID='" + utils.dbConsult("select cajaID from cajas_tbl where noeconomico='" + txtPlacasChasis.getText() + "'") + "' and  rev90 is true "
                        + "and categoriaID order by RequisicionID desc limit 1"));

                bremolque.setText(utils.dbConsult("SELECT DATE_FORMAT(fecha,'" + global.fdatedb + "') from requimant_tbl where "
                        + "camionID='" + utils.dbConsult("select cajaID from cajas_tbl where noeconomico='" + txtPlacasChasis.getText() + "'") + "' and  rev90 is true "
                        + "and categoriaID order by RequisicionID desc limit 1"));

                Patio.setSelectedIndex(PatioID.indexOf(rs.getString("patioID")));
                System.out.println("PATIO: " + Patio.getSelectedItem().toString() + " index:" + PatioID.indexOf(rs.getString("patioID")) + " db: " + rs.getString("patioID") + "inventarioID: " + inventarioID.get(jTable1.getSelectedRow()));

                String req = utils.dbConsult("SELECT CONCAT('Taller Folio: ',IFNULL('" + utils.dbConsult("select IFNULL(GROUP_CONCAT(requisicionID),'N/A') "
                        + "from requimant_tbl where inventarioexternoID='" + inventarioID.get(jTable1.getSelectedRow()) + "'") + "',''))");
                statz1.setText(req);

                txtDriverMove.setText(rs.getString("itinerarioID"));

                licencia.setText(rs.getString("licencia"));

                cajachasisbox.setSelectedIndex(cajachasisboxID.indexOf(rs.getString("cajaID")));

                cajachasisboxExterno.setText(rs.getString("chasis"));

                sellocomplementario.setText(rs.getString("sellocomplementario"));

                //edicion.setText(utils.dbConsult("SELECT (select Nombre from usuarios_Tbl where usuarioID=UsuarioEventoID) from inventarioexterno_tbl where inventarioID="));
            }
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(dEntradaSalida, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarEntradaSalida2() {

        Connection con = utils.startConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT Contenedor,"
                    + "DATE_FORMAT(FechaEvento, '" + global.fdatedb + "') as fsalida,"
                    + "DATE_FORMAT(FechaEvento, '%H:%i') as hsalida,"
                    + "DATE_FORMAT(FechaEvento, '" + global.fdatedb + "') as fllegada,"
                    + "DATE_FORMAT(FechaEvento, '%H:%i') as hllegada,"
                    + "PlacasChasis,"
                    + "Sello,"
                    + "IF(PaisID = 0, 1,PaisID) as paisid,"
                    + "EstadoID as estadoid,"
                    + "Tamano,"
                    + "Nota,"
                    + "NombreChofer,"
                    + "EstadoCarga,"
                    + "ChoferID, "
                    + "camion, "
                    + "clienteID, "
                    + "EquipmentProvider, "
                    + "origen, "
                    + "camionID, "
                    + "botando,"
                    + "PlacasUnidad,"
                    + "PlacasUnidadUSA,"
                    + "Destino, "
                    + "NumeroChasis, "
                    + "Carrier, "
                    + "patioID, "
                    + "grade, "
                    + "assignedto, "
                    + "IF(TipoEvento=1,'Entrada','Salida') as Entsal, IFNULL(isCamion,false) as izCamion, estadoUSAID,estadoplacasmex,estadoplacasusa,placaschasisUSA, "
                    + "remolqueEstado, remolquePais "
                    + "from inventarioexterno_tbl "
                    + "where InventarioID = '" + SelectedInventarioID + "'");

            while (rs.next()) {

                InOut.setSelectedItem(rs.getString("EntSal"));

                cliente.setSelectedIndex(clienteid.indexOf((rs.getString("clienteID"))));
                cliente1.setSelectedIndex(cliente1id.indexOf((rs.getString("EquipmentProvider"))));
                camionx.setSelectedIndex(camionidx.indexOf((rs.getString("camionID"))));

                camiones.setText(rs.getString("camion"));
                Orgn.setSelectedItem(rs.getString("origen"));
                txtContenedor.setText(rs.getString("Contenedor"));
                boxChofer.setSelectedIndex(chofereslocalid.indexOf(rs.getString("ChoferID")));
                txtFLlegada2.setText(rs.getString("fllegada"));
                txtHLlegada.setText(rs.getString("hllegada"));
                txtPlacasChasis.setText(rs.getString("placaschasis"));
                //txtPlacasChasis1.setText(rs.getString("placaschasisUSA"));
                txtSello.setText(rs.getString("Sello"));
                //boxPaisPlacas.setSelectedIndex(rs.getInt("paisid") - 1);

                FillCombo.cargarEstados(boxEstadoPlacas, estadosid, "", rs.getString("paisid"));
                FillCombo.cargarEstados(boxEstadoPlacas1, estadosid1, "", "2");

                if (rs.getInt("estadoid") > 0) {
                    boxEstadoPlacas.setSelectedIndex(estadosid.indexOf(rs.getString("estadoid")));
                } else {
                    boxEstadoPlacas.setSelectedIndex(0);
                }

                if (rs.getInt("estadoUSAID") > 0) {
                    boxEstadoPlacas1.setSelectedIndex(estadosid1.indexOf(rs.getString("estadoUSAID")));
                } else {
                    boxEstadoPlacas1.setSelectedIndex(0);
                }

                placasunidad2.setText(rs.getString("estadoplacasmex"));
                placasunidad3.setText(rs.getString("estadoplacasusa"));

                boxTamano.setSelectedItem(rs.getString("Tamano"));
                txtNombreChofer.setText(rs.getString("NombreChofer"));
                boxEstadoCarga.setSelectedIndex(rs.getInt("EstadoCarga"));
                txtNota.setText(rs.getString("Nota"));

                botando.setSelected(rs.getBoolean("botando"));
                placasunidad.setText(rs.getString("placasunidad"));
                placasunidad1.setText(rs.getString("placasunidadUSA"));
                destino.setText(rs.getString("destino"));

                NCHASIS.setText(rs.getString("NumeroChasis"));
                CARRIER.setText(rs.getString("Carrier"));

                Patio.setSelectedIndex(PatioID.indexOf(rs.getString("patioID")));

                assignedto.setText(rs.getString("assignedto"));
                grade.setSelectedItem(rs.getString("grade"));
                if (grade.getSelectedIndex() == -1) {
                    grade.setSelectedIndex(0);
                }

                edicion.setText(utils.dbConsult("SELECT concat((select Nombre from usuarios_Tbl where usuarioID=UsuarioEventoID),' ',"
                        + "DATE_FORMAT(fechaedicion,'%m/%d/%Y %H:%i')) from inventarioexterno_tbl where inventarioID='" + SelectedInventarioID + "'"));

                edicion1.setText(utils.dbConsult("SELECT concat((select Nombre from usuarios_Tbl where usuarioID=CreacionUsuarioEventoID),' ',"
                        + "DATE_FORMAT(fecha,'%m/%d/%Y %H:%i')) from inventarioexterno_tbl where inventarioID='" + SelectedInventarioID + "'"));

                Camion.setSelected(rs.getBoolean("izCamion"));

                paisx.setSelectedItem(rs.getString("remolquePais"));
                estx.setSelectedIndex(estxid.indexOf(rs.getString("remolqueEstado")));

                //edicion.setText(utils.dbConsult("SELECT (select Nombre from usuarios_Tbl where usuarioID=UsuarioEventoID) from inventarioexterno_tbl where inventarioID="));
            }
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(dEntradaSalida, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarFolios() {
        foliosid.clear();
        boxFolio.removeAllItems();
        Connection con = utils.startConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT sellos_tbl.SelloID, CONCAT(Serie,Folio) as sello "
                    + "from sellos_tbl "
                    + "where sellos_tbl.`Status` = true and EnUso = false and ConsignadoID = '" + global.usuario + "'");
            while (rs.next()) {
                boxFolio.addItem(rs.getString("sello"));
                foliosid.add(rs.getString(1));
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    private void cobroAlmacenaje(String itinerario, boolean regresado) {
        Connection con = utils.startConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT IcontID, "
                    + "FLOOR(( IFNULL(TIMESTAMPDIFF(SECOND,InicioResguardo, FinResguardo)/3600,0) - IFNULL(TIME_TO_SEC(TIMEDIFF((SELECT FechaEvento from itinerarios_tbl where itinerarios_tbl.ItinerarioID = icont_tbl.ItinerarioID),InicioResguardo))/3600,0) )  / (SELECT AlmacenIn from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID))*(SELECT AlmacenC from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID) as cobro,"
                    + "(SELECT MonedaAlmacen from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID) as moneda,"
                    + "IFNULL(TIMESTAMPDIFF(SECOND,InicioResguardo, FinResguardo),0) as tiemporesguardo,"
                    + "(SELECT AlmacenC from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID) as cobrounitario,"
                    + "IFNULL(TIME_TO_SEC(TIMEDIFF((SELECT FechaEvento from itinerarios_tbl where itinerarios_tbl.ItinerarioID = icont_tbl.ItinerarioID),InicioResguardo))/3600,0) as horastarde,"
                    + "(SELECT AlmacenIn from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID) as intervalo "
                    + "from icont_tbl "
                    + "left join workcontenedores_tbl on icont_tbl.WContID = workcontenedores_tbl.WContenedorID "
                    + "where ItinerarioID = '" + itinerario + "' and icont_tbl.Status = true");

            while (rs.next()) {
                if (regresado) {
                    utils.dbUpdate("UPDATE cargosclientes_tbl SET Status = false where IcontID = '" + rs.getString("IcontID") + "' and TipoCobro = '3'");
                }
                if (rs.getFloat("cobro") > 0) {
                    if (rs.getInt("moneda") == 0) {
                        utils.dbInsert("INSERT INTO cargosclientes_tbl(ItinerarioID, Concepto,IcontID,MN,USD,Status,Fecha,UsuarioID,TipoCobro, Justificacion) "
                                + "VALUES('" + itinerario + "','Almacenaje Extra','" + rs.getString(1) + "','" + rs.getString("cobro") + "','0',true,(now()),'" + global.usuario + "','3', '(" + rs.getString("tiemporesguardo") + " <tiempo resguardo = Fin de Resguardo - Inicio Resguardo> - " + rs.getString("horastarde") + "<Horas tarde>) * " + rs.getString("cobrounitario") + "<Cobro por intervalo " + rs.getString("intervalo") + ">')");
                    }
                    if (rs.getInt("moneda") == 1) {
                        utils.dbInsert("INSERT INTO cargosclientes_tbl(ItinerarioID, Concepto,IcontID,MN,USD,Status,Fecha,UsuarioID,TipoCobro, Justificacion) "
                                + "VALUES('" + itinerario + "','Almacenaje Extra','" + rs.getString(1) + "','0','" + rs.getString("cobro") + "',true,(now()),'" + global.usuario + "','3', '(" + rs.getString("tiemporesguardo") + " <tiempo resguardo = Fin de Resguardo - Inicio Resguardo> - " + rs.getString("horastarde") + "<Horas tarde>) * " + rs.getString("cobrounitario") + "<Cobro por intervalo " + rs.getString("intervalo") + ">')");
                    }
                }
            }

            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e, "Error almacenaje", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cobroExtraChasis(String itinerario, boolean regresado) {
        Connection con = utils.startConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT IcontID, "
                    + "FLOOR(( IFNULL(TIMESTAMPDIFF(SECOND,LlegadaCliente,SalidaCliente)/3600,0) - IF(LlegadaCliente>(SELECT Carga from itinerarios_tbl where itinerarios_tbl.ItinerarioID = icont_tbl.ItinerarioID), (SELECT ChasisH+ChasisIm from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID),(SELECT ChasisH from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID))) / (SELECT ChasisIn from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID))*(SELECT ChasisC from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID) as cobro,"
                    + "(SELECT MonedaChasis from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID) as moneda,"
                    + "IFNULL(TIMESTAMPDIFF(HOUR, LlegadaCliente, SalidaCliente),0) as horascliente, "
                    + "(SELECT ChasisH from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID) as horaspermitidas,"
                    + "(SELECT ChasisC from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID) as cobrounitario,"
                    + "(SELECT ChasisIn from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID) as invervalo "
                    + "from icont_tbl where ItinerarioID = '" + itinerario + "' and Status = true and CajaID > 0 and (SELECT Pertenece from cajas_tbl where cajas_tbl.CajaID = icont_tbl.CajaID) = 0");
            while (rs.next()) {
                if (regresado) {
                    utils.dbUpdate("UPDATE cargosclientes_tbl SET Status = false where IcontID = '" + rs.getString("IcontID") + "' and TipoCobro = '1'");
                }

                if (rs.getFloat("cobro") > 0) {

                    if (rs.getInt("moneda") == 0) {
                        utils.dbInsert("INSERT INTO cargosclientes_tbl(ItinerarioID, Concepto,IcontID,MN,USD,Status,Fecha,UsuarioID,TipoCobro, Justificacion) "
                                + "VALUES('" + itinerario + "','Tiempo Chasis','" + rs.getString(1) + "','" + rs.getString("cobro") + "','0',true,(now()),'" + global.usuario + "','1', '(" + rs.getString("horascliente") + "<horas con cliente = Salida Cliente Chasis - Llegada Cliente> - " + rs.getString("horaspermitidas") + "<horas definidas>)* " + rs.getString("cobrounitario") + "<Cobro por intervalo " + rs.getString("intervalo") + ">')");
                    }
                    if (rs.getInt("moneda") == 1) {
                        utils.dbInsert("INSERT INTO cargosclientes_tbl(ItinerarioID, Concepto,IcontID,MN,USD,Status,Fecha,UsuarioID,TipoCobro, Justificacion) "
                                + "VALUES('" + itinerario + "','Tiempo Chasis','" + rs.getString(1) + "','0','" + rs.getString("cobro") + "',true,(now()),'" + global.usuario + "','1', '(" + rs.getString("horascliente") + "<horas con cliente = Salida Cliente Chasis - Llegada Cliente> - " + rs.getString("horaspermitidas") + "<horas definidas>)* " + rs.getString("cobrounitario") + "<Cobro por intervalo " + rs.getString("intervalo") + ">')");
                    }
                }
            }
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e, "Error extra chasis", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cobroVGM(String itinerario, boolean regresado) {
        Connection con = utils.startConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT IcontID, "
                    + "(SELECT clientes_tbl.VGM from clientes_tbl left join rutas_tbl on clientes_tbl.ClienteID = rutas_tbl.ClienteID where rutas_tbl.RutaID = icont_tbl.RutaID) as cobro,"
                    + "(SELECT MonedaVGM from clientes_tbl left join rutas_tbl on clientes_tbl.ClienteID = rutas_tbl.ClienteID where rutas_tbl.RutaID = icont_tbl.RutaID) as moneda "
                    + "from icont_tbl "
                    + "where ItinerarioID = '" + itinerario + "' and Status = true and VGM = true");
            while (rs.next()) {
                if (regresado) {
                    utils.dbUpdate("UPDATE cargosclientes_tbl SET Status = false where IcontID = '" + rs.getString("IcontID") + "' and TipoCobro = '8'");
                }
                if (rs.getFloat("cobro") > 0) {

                    if (rs.getInt("moneda") == 0) {
                        utils.dbInsert("INSERT INTO cargosclientes_tbl(ItinerarioID, Concepto,IcontID,MN,USD,Status,Fecha,UsuarioID,TipoCobro) "
                                + "VALUES('" + itinerario + "','VGM','" + rs.getString(1) + "','" + rs.getString("cobro") + "','0',true,(now()),'" + global.usuario + "','8')");
                    }
                    if (rs.getInt("moneda") == 1) {
                        utils.dbInsert("INSERT INTO cargosclientes_tbl(ItinerarioID, Concepto,IcontID,MN,USD,Status,Fecha,UsuarioID,TipoCobro) "
                                + "VALUES('" + itinerario + "','VGM','" + rs.getString(1) + "','0','" + rs.getString("cobro") + "',true,(now()),'" + global.usuario + "','8')");
                    }
                }
            }
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e, "Error extra VGM", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cobroExtraOVMT(String itinerario, boolean regresado) {
        Connection con = utils.startConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT IcontID, "
                    + "IF(VerificarOVMT = false, 0, FLOOR(( IFNULL(TIMESTAMPDIFF(SECOND,LlegadaPuerto,LlegadaTerminal)/3600,0) -  (SELECT VerificarH from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID))/ (SELECT VerificarIn from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID))*(SELECT VerificarC from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID)) as cobro,"
                    + "(SELECT MonedaVerificar from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID) as moneda,"
                    + "(SELECT VerificarC from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID) as cobrounitario,"
                    + "IFNULL(TIMESTAMPDIFF(HOUR,LlegadaPuerto,LlegadaTerminal),0) as horaspuerto, "
                    + "(SELECT VerificarH from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID) as horasdefinidas,"
                    + "(SELECT VerificarIn from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID) as intervalo "
                    + "from icont_tbl where ItinerarioID = '" + itinerario + "' and Status = true");
            while (rs.next()) {
                if (regresado) {
                    utils.dbUpdate("UPDATE cargosclientes_tbl SET Status = false where IcontID = '" + rs.getString("IcontID") + "' and TipoCobro = '7'");
                }
                if (rs.getFloat("cobro") > 0) {

                    if (rs.getInt("moneda") == 0) {
                        utils.dbInsert("INSERT INTO cargosclientes_tbl(ItinerarioID, Concepto,IcontID,MN,USD,Status,Fecha,UsuarioID,TipoCobro, Justificacion) "
                                + "VALUES('" + itinerario + "','OVMT','" + rs.getString(1) + "','" + rs.getString("cobro") + "','0',true,(now()),'" + global.usuario + "','7', '(" + rs.getString("horaspuerto") + "<horas puerto = Salida Patio Fiscal 2 - Llegada Patio Fiscal> - " + rs.getString("horasdefinidas") + "<horas permitidas>) * " + rs.getString("cobrounitario") + "<Cobro por intervalo " + rs.getString("intervalo") + ">')");
                    }
                    if (rs.getInt("moneda") == 1) {
                        utils.dbInsert("INSERT INTO cargosclientes_tbl(ItinerarioID, Concepto,IcontID,MN,USD,Status,Fecha,UsuarioID,TipoCobro, Justificacion) "
                                + "VALUES('" + itinerario + "','OVMT','" + rs.getString(1) + "','0','" + rs.getString("cobro") + "',true,(now()),'" + global.usuario + "','7', '(" + rs.getString("horaspuerto") + "<horas puerto = Salida Patio Fiscal 2 - Llegada Patio Fiscal> - " + rs.getString("horasdefinidas") + "<horas permitidas>) * " + rs.getString("cobrounitario") + "<Cobro por intervalo " + rs.getString("intervalo") + ">')");
                    }
                }
            }
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e, "Error extra ovmt", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cobroExtraGen(String itinerario, boolean regresado) {
        Connection con = utils.startConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT IcontID, "
                    + "FLOOR(( IFNULL(TIMESTAMPDIFF(SECOND,LlegadaCliente,SalidaCliente)/3600,0) - IF(LlegadaCliente>(SELECT Carga from itinerarios_tbl where itinerarios_tbl.ItinerarioID = icont_tbl.ItinerarioID), (SELECT GenH+GenIm from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID),(SELECT GenH from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID))) / (SELECT GenIn from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID))*(SELECT GenC from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID) as cobro,"
                    + "(SELECT MonedaGen from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID) as moneda,"
                    + "IFNULL(TIMESTAMPDIFF(HOUR,LlegadaCliente,SalidaCliente),0) as horascliente,"
                    + "(SELECT GenH from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID) as horasdefinidas,"
                    + "(SELECT GenIn from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID) as intervalo,"
                    + "(SELECT GenC from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID) as cobrounitario "
                    + "from icont_tbl "
                    + "where ItinerarioID = '" + itinerario + "' and Status = true and GeneradorID > 0");
            while (rs.next()) {
                if (regresado) {
                    utils.dbUpdate("UPDATE cargosclientes_tbl SET Status = false where IcontID = '" + rs.getString("IcontID") + "' and TipoCobro = '6'");
                }

                if (rs.getFloat("cobro") > 0) {

                    if (rs.getInt("moneda") == 0) {
                        utils.dbInsert("INSERT INTO cargosclientes_tbl(ItinerarioID, Concepto,IcontID,MN,USD,Status,Fecha,UsuarioID,TipoCobro, Justificacion) "
                                + "VALUES('" + itinerario + "','Tiempo Generador','" + rs.getString(1) + "','" + rs.getString("cobro") + "','0',true,(now()),'" + global.usuario + "','6', '(" + rs.getString("horascliente") + "<horas cliente = Salida Cliente Chasis - Llegada Cliente> - " + rs.getString("horasdefinidas") + "<horas permitidas>) * " + rs.getString("cobrounitario") + "<Cobro por intervalo " + rs.getString("intervalo") + ">')");
                    }
                    if (rs.getInt("moneda") == 1) {
                        utils.dbInsert("INSERT INTO cargosclientes_tbl(ItinerarioID, Concepto,IcontID,MN,USD,Status,Fecha,UsuarioID,TipoCobro, Justificacion) "
                                + "VALUES('" + itinerario + "','Tiempo Generador','" + rs.getString(1) + "','0','" + rs.getString("cobro") + "',true,(now()),'" + global.usuario + "','6', '(" + rs.getString("horascliente") + "<horas cliente = Salida Cliente Chasis - Llegada Cliente> - " + rs.getString("horasdefinidas") + "<horas permitidas>) * " + rs.getString("cobrounitario") + "<Cobro por intervalo " + rs.getString("intervalo") + ">')");
                    }
                }
            }
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e, "Error Extra Generador", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cobroExtraCamion(String itinerario, boolean regresado) {
        Connection con = utils.startConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT IcontID, "
                    + "FLOOR(( IFNULL(TIMESTAMPDIFF(SECOND,LlegadaCliente,SalidaClienteCamion)/3600,0) - IF(LlegadaCliente>(SELECT Carga from itinerarios_tbl where itinerarios_tbl.ItinerarioID = icont_tbl.ItinerarioID), (SELECT CamionH+CamionIm from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID),(SELECT CamionH from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID))) / (SELECT CamionIn from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID))*(SELECT CamionC from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID) as cobro,"
                    + "(SELECT MonedaCamion from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID) as moneda,"
                    + "IFNULL(TIMESTAMPDIFF(HOUR,LlegadaCliente,SalidaClienteCamion),0) as horascliente,"
                    + "(SELECT CamionH from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID) as horasdefinidas, "
                    + "(SELECT CamionC from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID) as costounitario,"
                    + "LlegadaCliente,"
                    + "SalidaClienteCamion,"
                    + "(SELECT CamionIn from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID) as intervalo "
                    + "from icont_tbl where ItinerarioID = '" + itinerario + "' and Status = true");
            while (rs.next()) {

                if (regresado) {
                    utils.dbUpdate("UPDATE cargosclientes_tbl SET Status = false where IcontID = '" + rs.getString("IcontID") + "' and TipoCobro = '4'");
                }

                if (rs.getFloat("cobro") > 0) {

                    if (rs.getInt("moneda") == 0) {
                        utils.dbInsert("INSERT INTO cargosclientes_tbl(ItinerarioID, Concepto,IcontID,MN,USD,Status,Fecha,UsuarioID,TipoCobro, Justificacion) "
                                + "VALUES('" + itinerario + "','Tiempo Camion','" + rs.getString(1) + "','" + rs.getString("cobro") + "','0',true,(now()),'" + global.usuario + "','4', '(" + rs.getString("horascliente") + "<horas cliente = Salida Cliente Camion - Llegada Cliente> - " + rs.getString("horasdefinidas") + "<horas Definidas>) * " + rs.getString("costounitario") + "<Cobro por intervalo " + rs.getString("intervalo") + ">')");
                    }
                    if (rs.getInt("moneda") == 1) {
                        utils.dbInsert("INSERT INTO cargosclientes_tbl(ItinerarioID, Concepto,IcontID,MN,USD,Status,Fecha,UsuarioID,TipoCobro, Justificacion) "
                                + "VALUES('" + itinerario + "','Tiempo Camion','" + rs.getString(1) + "','0','" + rs.getString("cobro") + "',true,(now()),'" + global.usuario + "','4', '(" + rs.getString("horascliente") + "<horas cliente = Salida Cliente Camion - Llegada Cliente> - " + rs.getString("horasdefinidas") + "<horas Definidas>) * " + rs.getString("costounitario") + "<Cobro por intervalo " + rs.getString("intervalo") + ">')");
                    }
                }
            }
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e, "Error extra camion", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cobroExtraChasisIngreso(String itinerario, boolean regresado) {
        Connection con = utils.startConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT IcontID, "
                    + "FLOOR(( IFNULL(TIMESTAMPDIFF(SECOND,(SELECT IF(TIME(FechaEvento) < '18:00:00',FechaEvento, CONCAT(DATE(DATE_ADD(FechaEvento, INTERVAL 1 DAY) ),' ',MAKETIME(3,0,0) )  )  from itinerarios_tbl where itinerarios_tbl.ItinerarioID = icont_tbl.ItinerarioID),FechaIngreso)/3600,0) - (SELECT ChasisIngresoH from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID)) / (SELECT ChasisIngresoIn from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID))*(SELECT ChasisIngresoC from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID) as cobro, "
                    + "(SELECT MonedaChasisIngreso from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID) as moneda,"
                    + "IFNULL(TIMESTAMPDIFF(HOUR,(SELECT IF(TIME(FechaEvento) < '18:00:00',FechaEvento, CONCAT(DATE(DATE_ADD(FechaEvento, INTERVAL 1 DAY) ),' ',MAKETIME(3,0,0) )  )  from itinerarios_tbl where itinerarios_tbl.ItinerarioID = icont_tbl.ItinerarioID),FechaIngreso),0) as horaschasis,"
                    + "(SELECT ChasisIngresoH from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID) as horasdefinidas,"
                    + "(SELECT ChasisIngresoIn from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID) as intervalo,"
                    + "(SELECT ChasisIngresoC from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID) as cobrounitario "
                    + "from icont_tbl where ItinerarioID = '" + itinerario + "' and Status = true and CajaID >0 and (SELECT Pertenece from cajas_tbl where cajas_tbl.CajaID = icont_tbl.CajaID) = 0");
            while (rs.next()) {
                if (regresado) {
                    utils.dbUpdate("UPDATE cargosclientes_tbl SET Status = false where IcontID = '" + rs.getString("IcontID") + "' and TipoCobro = '5'");
                }

                if (rs.getFloat("cobro") > 0) {

                    if (rs.getInt("moneda") == 0) {
                        utils.dbInsert("INSERT INTO cargosclientes_tbl(ItinerarioID, Concepto,IcontID,MN,USD,Status,Fecha,UsuarioID,TipoCobro, Justificacion) "
                                + "VALUES('" + itinerario + "','Tiempo Chasis Ingreso','" + rs.getString(1) + "','" + rs.getString("cobro") + "','0',true,(now()),'" + global.usuario + "','5', '(" + rs.getString("horaschasis") + "<horas chasis = Fecha Retorno - Fecha Ingreso> - " + rs.getString("horasdefinidas") + "<horas Definidas>) * " + rs.getString("costounitario") + "<Cobro por intervalo " + rs.getString("intervalo") + ">')");
                    }
                    if (rs.getInt("moneda") == 1) {
                        utils.dbInsert("INSERT INTO cargosclientes_tbl(ItinerarioID, Concepto,IcontID,MN,USD,Status,Fecha,UsuarioID,TipoCobro, Justificacion) "
                                + "VALUES('" + itinerario + "','Tiempo Chasis Ingreso','" + rs.getString(1) + "','0','" + rs.getString("cobro") + "',true,(now()),'" + global.usuario + "','5', '(" + rs.getString("horaschasis") + "<horas chasis = Fecha Retorno - Fecha Ingreso> - " + rs.getString("horasdefinidas") + "<horas Definidas>) * " + rs.getString("costounitario") + "<Cobro por intervalo " + rs.getString("intervalo") + ">')");
                    }
                }
            }
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e, "Error extra ingreso", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void excesoPeso(String itinerario, boolean regresado) {
        Connection con = utils.startConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT IcontID,"
                    + "IF( digits(Peso)>(SELECT PesoLimite from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID),CEIL( digits(Peso)-(SELECT PesoLimite from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID)),0) as peso,"
                    + "CEIL( digits(Peso)-(SELECT PesoLimite from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID))*(SELECT PesoC from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID)  as costo,"
                    + "(SELECT MonedaPeso from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID) as moneda,"
                    + "digits(Peso) as pesocont,"
                    + "(SELECT PesoLimite from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID) as pesoruta, "
                    + "(SELECT PesoC from rutas_tbl where rutas_tbl.RutaID = icont_tbl.RutaID) as costounitario "
                    + "from icont_tbl "
                    + "where ItinerarioID = '" + itinerario + "' and Status = true");
            while (rs.next()) {
                if (regresado) {//borrando las anteriores para no entrar en conflicto
                    utils.dbUpdate("UPDATE cargosclientes_tbl SET Status = false where IcontID = '" + rs.getString("IcontID") + "' and TipoCobro = '2'");
                }
                if (rs.getFloat("peso") > 0) {
                    if (rs.getInt("moneda") == 0) {

                        utils.dbInsert("INSERT INTO cargosclientes_tbl(ItinerarioID, Concepto,IcontID,MN,USD,Status,Fecha,UsuarioID,TipoCobro, Justificacion) "
                                + "VALUES('" + itinerario + "','Exceso de Peso " + rs.getString("peso") + " TON','" + rs.getString(1) + "','" + rs.getString("costo") + "','0',true,(now()),'" + global.usuario + "','2', '(" + rs.getString("pesocont") + "<Peso Contenedor> - " + rs.getString("pesoruta") + " <Peso maximo definido>) *" + rs.getString("costounitario") + "<costo unitario>')");

                    }
                    if (rs.getInt("moneda") == 1) {

                        utils.dbInsert("INSERT INTO cargosclientes_tbl(ItinerarioID, Concepto,IcontID,MN,USD,Status,Fecha,UsuarioID,TipoCobro, Justificacion) "
                                + "VALUES('" + itinerario + "','Exceso de Peso " + rs.getString("peso") + " TON','" + rs.getString(1) + "','0','" + rs.getString("costo") + "',true,(now()),'" + global.usuario + "','2', '(" + rs.getString("pesocont") + "<Peso Contenedor> - " + rs.getString("pesoruta") + "<Peso maximo definido>) *" + rs.getString("costounitario") + "<costo unitario>')");

                    }
                }
            }
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e, "Error exceso peso", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarUnidades() {
        String query = "";

        if (boxFiltro.getSelectedIndex() == 0) {
            query = "SELECT NoEconomico, IF((SELECT RequisicionID from requimant_tbl where CategoriaID = 1 and requimant_tbl.CamionID = camiones_tbl.CamionID and Status > 1 and Status < 4 limit 1) is null,'Disponible','Taller') from camiones_tbl where Status = true order by CAST(NoEconomico as UNSIGNED)";
        }
        if (boxFiltro.getSelectedIndex() == 1) {
            query = "SELECT NoEconomico, IF((SELECT RequisicionID from requimant_tbl where CategoriaID = 2 and requimant_tbl.CamionID = CajaID and Status > 1 and Status < 4 limit 1) is null,'Disponible','Taller') from cajas_tbl where Status = true order by CAST(NoEconomico as UNSIGNED)";
        }
        if (boxFiltro.getSelectedIndex() == 2) {
            query = "SELECT NoEconomico, IF((SELECT RequisicionID from requimant_tbl where CategoriaID = 3 and requimant_tbl.CamionID = DollyID and Status > 1 and Status < 4 limit 1) is null,'Disponible','Taller') from dollys_tbl where Status = true order by CAST(NoEconomico as UNSIGNED)";
        }
        if (boxFiltro.getSelectedIndex() == 3) {
            query = "SELECT NoEconomico, IF((SELECT RequisicionID from requimant_tbl where CategoriaID = 4 and requimant_tbl.CamionID = GeneradorID and Status > 1 and Status < 4 limit 1) is null,'Disponible','Taller') from generadores_tbl where Status = true order by CAST(digits(NoEconomico) as UNSIGNED)";
        }

        tablemodel15.setRowCount(0);
        Connection con = utils.startConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            int i = 1;
            while (rs.next()) {
                tablemodel15.addRow(new String[]{rs.getString(1), rs.getString(2)});
            }

            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    private void cargarDirecciones(String ruta) {
        String query = "SELECT Direccion from direccionesrutas_tbl where RutaID = '" + ruta + "'";

        boxDirecciones.removeAllItems();
        Connection con = utils.startConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            int i = 1;
            while (rs.next()) {
                boxDirecciones.addItem(rs.getString(1));
            }

            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    private void cargarDireccionItinerario(String itinerario) {
        String query = "SELECT Direccion,AsignacionID from direccionesasignadas_tbl where ItinerarioID = '" + itinerario + "'";

        tablemodel9.setRowCount(0);
        asignacionid.clear();
        Connection con = utils.startConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            int i = 1;
            while (rs.next()) {
                tablemodel9.addRow(new Object[]{rs.getString(1)});
                asignacionid.add(rs.getString(2));
            }
            if (tablemodel9.getRowCount() == 0) {
                tablemodel9.addRow(new Object[]{});
                asignacionid.add("0");
            }

            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    public JButton createButton(String name) {
        JButton btn = new JButton();
        btn.setForeground(new java.awt.Color(50, 20, 20));
        btn.setFont(new java.awt.Font("Tahoma", 0, 11));
        btn.setName("btn" + justaddedpaths.size());
        btn.setText(name);

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMouseClicked(evt);
            }
        });
        return btn;
    }

    private void lblMouseClicked(java.awt.event.MouseEvent evt) {
        int row = buttons.indexOf(((JButton) evt.getSource()).getText());
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File(justaddedpaths.get(row));
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this.getFocusOwner(), "Error " + ex, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ItinerarioGuardiasContExternos2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ItinerarioGuardiasContExternos2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ItinerarioGuardiasContExternos2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ItinerarioGuardiasContExternos2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ItinerarioGuardiasContExternos2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog AltaYarda;
    private javax.swing.JComboBox<String> BoxCliente;
    private javax.swing.JComboBox<String> BoxCliente1;
    private javax.swing.JComboBox<String> BoxPatios;
    private javax.swing.JTextField CARRIER;
    private javax.swing.JTable CTRL;
    private javax.swing.JCheckBox Camion;
    private javax.swing.JDialog ContenedorFiltro;
    private javax.swing.JDialog ContenedorInterno;
    private javax.swing.JDialog EquipmentControl;
    private javax.swing.JDialog Facturacion;
    private javax.swing.JCheckBox FechaFiltro;
    private javax.swing.JCheckBox FilterFull;
    private javax.swing.JLabel Fullx;
    private javax.swing.JLabel Fullx1;
    private javax.swing.JComboBox<String> HistorialActual;
    private javax.swing.JComboBox InOut;
    private javax.swing.JTextField NCHASIS;
    private javax.swing.JComboBox<String> Orgn;
    private javax.swing.JButton Pagos14;
    private javax.swing.JButton Pagos15;
    private javax.swing.JButton Pagos16;
    private javax.swing.JButton Pagos17;
    private javax.swing.JButton Pagos19;
    private javax.swing.JComboBox<String> Patio;
    private javax.swing.JComboBox Perfil;
    private javax.swing.JDialog Perfiles;
    private javax.swing.JDialog PuntosRevision;
    private javax.swing.JButton anexo_add10;
    private javax.swing.JButton anexo_add8;
    private javax.swing.JButton anexo_add9;
    private javax.swing.JButton anexo_del10;
    private javax.swing.JButton anexo_del8;
    private javax.swing.JButton anexo_del9;
    private javax.swing.JTextArea areaBody1;
    private javax.swing.JTextField assignedto;
    private javax.swing.JButton bActualizar;
    private javax.swing.JButton bAgregarDocs1;
    private javax.swing.JButton bAgregarDocs2;
    private javax.swing.JButton bAgregarDocs3;
    private javax.swing.JButton bContenedores;
    private javax.swing.JButton bContenedores1;
    private javax.swing.JButton bContenedores2;
    private javax.swing.JButton bContenedores3;
    private javax.swing.JButton bControlEquipment;
    private javax.swing.JButton bEliminarLlegada;
    private javax.swing.JButton bLlegada;
    private javax.swing.JButton bNuevaEntrada;
    private com.alee.extended.date.WebDateField bcamion;
    private com.alee.extended.date.WebDateField bchasis;
    private javax.swing.JCheckBox botando;
    private javax.swing.JComboBox<String> boxChofer;
    private javax.swing.JComboBox<String> boxClienteRep;
    private javax.swing.JComboBox<String> boxEstadoCarga;
    private javax.swing.JComboBox<String> boxEstadoPlacas;
    private javax.swing.JComboBox<String> boxEstadoPlacas1;
    private javax.swing.JComboBox<String> boxFiltro;
    private javax.swing.JComboBox<String> boxFiltroBusqueda;
    private javax.swing.JComboBox<String> boxFiltroBusqueda1;
    private javax.swing.JComboBox<String> boxFiltroStatus;
    private javax.swing.JComboBox<String> boxOrdenChoferes;
    private javax.swing.JComboBox<String> boxPaisPlacas;
    private javax.swing.JComboBox<String> boxPaisPlacas1;
    private javax.swing.JComboBox<String> boxRutasRep;
    private javax.swing.JComboBox<String> boxTamano;
    private javax.swing.JComboBox<String> boxTipoReporte;
    private javax.swing.JComboBox boxcontenedorx;
    private com.alee.extended.date.WebDateField bremolque;
    private javax.swing.JTextField busquedaContenedor;
    private javax.swing.JComboBox<String> cajachasisbox;
    private javax.swing.JTextField cajachasisboxExterno;
    private javax.swing.JTextField camiones;
    private javax.swing.JComboBox<String> camionx;
    private javax.swing.JComboBox<String> cliente;
    private javax.swing.JComboBox<String> cliente1;
    private javax.swing.JComboBox clientePerfil;
    private javax.swing.JTable contenedor_tbl;
    private javax.swing.JDialog dArchivos;
    private javax.swing.JDialog dArchivos1;
    private javax.swing.JDialog dDetalles;
    private javax.swing.JDialog dEntradaSalida;
    private javax.swing.JDialog dEnvioCorreo;
    private javax.swing.JDialog dReportes;
    private javax.swing.JDialog dRevision;
    private javax.swing.JDialog dRolChoferes;
    private javax.swing.JDialog dTractoDM;
    private javax.swing.JDialog dUnidades;
    private javax.swing.JTextField destino;
    private javax.swing.JTextField dmText;
    private javax.swing.JLabel edicion;
    private javax.swing.JLabel edicion1;
    private javax.swing.JComboBox<String> estatus_caja;
    private javax.swing.JComboBox<String> estx;
    private javax.swing.JTable facturacion_tbl;
    private javax.swing.JCheckBox facturados;
    private javax.swing.JComboBox<String> grade;
    private javax.swing.JComboBox icont_contenedor;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JPopupMenu jPopupMenu3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane26;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane45;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable10;
    private javax.swing.JTable jTable9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JLabel lblFechas;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JLabel lblFoto1;
    private javax.swing.JTextField licencia;
    private javax.swing.JMenuItem mCancelar1;
    private javax.swing.JMenuItem mCargarArchivo;
    private javax.swing.JMenuItem mEntradaSalida;
    private javax.swing.JMenuItem mExtraChoferes;
    private javax.swing.JMenuItem mModificar;
    private javax.swing.JMenuItem mPrecioRuta;
    private javax.swing.JMenuItem mPuntos;
    private javax.swing.JMenuItem mRevisar;
    private javax.swing.JPanel pAttach;
    private javax.swing.JPanel pEstados1;
    private javax.swing.JPanel pEstados10;
    private javax.swing.JPanel pEstados11;
    private javax.swing.JPanel pEstados12;
    private javax.swing.JPanel pEstados2;
    private javax.swing.JPanel pEstados3;
    private javax.swing.JPanel pEstados4;
    private javax.swing.JPanel pEstados5;
    private javax.swing.JPanel pEstados6;
    private javax.swing.JPanel pEstados7;
    private javax.swing.JPanel pEstados8;
    private javax.swing.JPanel pEstados9;
    private javax.swing.JComboBox<String> paisx;
    private javax.swing.JComboBox<String> perfilBuscador;
    private javax.swing.JComboBox<String> perfilBuscador1;
    private javax.swing.JTextField perfilconcepto;
    private javax.swing.JTextField perfilnombre;
    private javax.swing.JTextField placasunidad;
    private javax.swing.JTextField placasunidad1;
    private javax.swing.JTextField placasunidad2;
    private javax.swing.JTextField placasunidad3;
    private javax.swing.JButton plus12;
    private javax.swing.JButton plus13;
    private javax.swing.JButton plus14;
    private javax.swing.JButton plus15;
    private javax.swing.JButton plus16;
    private javax.swing.JButton plus17;
    private javax.swing.JPopupMenu puCargosClientes;
    private javax.swing.JTable puntos;
    private javax.swing.JTextField pwd;
    private javax.swing.JCheckBox schasis;
    private javax.swing.JTextField sellocomplementario;
    private javax.swing.JCheckBox solochasis;
    private javax.swing.JLabel statz;
    private javax.swing.JLabel statz1;
    private javax.swing.JTable tCargosCliente;
    private javax.swing.JTable tDm;
    private javax.swing.JTable tEnsenada;
    private javax.swing.JTable tForaneo;
    private javax.swing.JTable tHermosillo;
    private javax.swing.JTable tMexicali;
    private javax.swing.JTable tTijuana;
    private javax.swing.JTable tUnidades;
    private javax.swing.JTable tableRango;
    private javax.swing.JTable taccesos;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtBusquedaChofer;
    private javax.swing.JTextField txtBusquedaReporte;
    private javax.swing.JTextField txtContenedor;
    private javax.swing.JTextField txtDriverMove;
    private com.github.lgooddatepicker.components.DatePicker txtFechafin;
    private com.github.lgooddatepicker.components.DatePicker txtFechaini;
    private javax.swing.JFormattedTextField txtHLlegada;
    private javax.swing.JTextField txtNombreChofer;
    private javax.swing.JTextField txtNota;
    private javax.swing.JTextField txtPlacasChasis;
    private javax.swing.JTextArea txtRevision;
    private javax.swing.JTextField txtSeleccionados;
    private javax.swing.JTextField txtSello;
    private javax.swing.JTextField txtSubject1;
    private javax.swing.JTextField txtTo1;
    private javax.swing.JTextField txtTotalReg;
    private javax.swing.JTextField txtTotalReg1;
    private javax.swing.JLabel yardaTittle;
    private javax.swing.JButton yv;
    // End of variables declaration//GEN-END:variables
}
