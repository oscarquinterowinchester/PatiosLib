/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package others;

import basic.*;
import basic.utils;
import com.alee.utils.FileUtils;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author nebur
 */
public class dNuevoDoc extends javax.swing.JDialog {

    /**
     * Creates new form dWContPuertoTren
     */
    public DefaultTableModel mnuevodoc;
    DefaultTableModel mclientes;
    DefaultTableModel mwos;
    DefaultTableModel mconfac;
    DefaultTableModel macobros;
    ArrayList<String> tipomov = new ArrayList<>();
    ArrayList<String> icont = new ArrayList<>();
    ArrayList<String> wcont = new ArrayList<>();
    ArrayList<String> rutaid = new ArrayList<>();
    public ArrayList<String> saveaccesoriond = new ArrayList<>();
    ArrayList<String> clientestid = new ArrayList<>();
//    ArrayList<String> unegocioid = new ArrayList<>();
    ArrayList<String> concargoid = new ArrayList<>();
    ArrayList<String> tipopagoid = new ArrayList<>();
    ArrayList<String> cuentaid = new ArrayList<>();
    ArrayList<String> monedascuentas = new ArrayList<>();
    ArrayList<String> formapagocod = new ArrayList<>();
    public ArrayList<String> usocfdi = new ArrayList<>();
    ArrayList<String> archivosfac = new ArrayList<>();
    ArrayList<String> etiquetaid = new ArrayList<>();
    ArrayList<String> saveetiqueta = new ArrayList<>();
    
    public ArrayList<String> inventarioID = new ArrayList<>();

    JComboBox boxEtiqueta = new WiderCombo();

    public String itiid = "0", clientend = "0", clienteor = "0", empresaid = "0", amplfact = "0", conceptofact = "", selectedclient = "";
    int cont = 0;
    public boolean esfactura = true, ampl = false;
    
    /////////ObjetoImpuesto
    public int ndwo = 0, nddesc = 1, ndcantidad = 2, ndvaloru = 3, ndsubtotal = 4, ndobjimp = 5, ndiva = 6, ndivaret = 7, ndisr = 8, ndconsat = 9, ndunisat = 10, ndref = 11, ndcont = 12;
    boolean doneload = false, esinvoice = false, freal = true;

    public dNuevoDoc(java.awt.Frame parent, boolean modal, boolean esinvoice, String clienteid, String empresaid) {
        super(parent, modal);
        initComponents();
        if (!esinvoice) {
            boxTipo.addItem("Anticipo");
        }
        this.itiid = itiid;
        this.esinvoice = esinvoice;
        clienteor = clienteid;
        clientend = clienteid;
        this.empresaid = empresaid;

        macobros = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return true;
                }
                return false;
            }
        };
        macobros.addColumn("Documento");
        macobros.addColumn("Archivo");
        tDocs.setModel(macobros);
        ConfigLib.setTable(tDocs);
        ConfigLib.addCombo(tDocs, boxEtiqueta, 0);

        tDocs.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                tableListenerdocs(e);
            }
        });

        mconfac = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        mconfac.addColumn("");
        mconfac.addColumn("Conceptos");
        tConceptosF.setModel(mconfac);
        ConfigLib.setTable(tConceptosF);
        tConceptosF.getColumnModel().getColumn(0).setPreferredWidth(10);
        tConceptosF.getColumnModel().getColumn(1).setPreferredWidth(600);

        mwos = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        mwos.addColumn("Cliente");
        mwos.addColumn("#WO");
        mwos.addColumn("Contenedor");
        mwos.addColumn("Customer Ref");
        tWOs.setModel(mwos);
        ConfigLib.setTable(tWOs);

        mclientes = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {

                return false;
            }
        };
        mclientes.addColumn("Razon Social");
        mclientes.addColumn("Nombre Comercial");
        tClientes.setModel(mclientes);
        ConfigLib.setTable(tClientes);

        mnuevodoc = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == ndsubtotal || column == ndwo || column == nddesc) {
                    return false;
                }
                /////////ObjetoImpuesto
                if (column == ndiva || column == ndivaret || column == ndisr) {
                    if (!(Boolean) getValueAt(row, ndobjimp)) {
                        return false;
                    }
                }
                /////////ObjetoImpuesto

                return true;
            }
        };

        mnuevodoc.addColumn("#WO");
        mnuevodoc.addColumn("Descripcion");
        mnuevodoc.addColumn("Cantidad");
        mnuevodoc.addColumn("Valor U");
        mnuevodoc.addColumn("Subtotal");
        /////////ObjetoImpuesto
        mnuevodoc.addColumn("ObjetoImpuesto");
        mnuevodoc.addColumn("IVA");
        mnuevodoc.addColumn("IVARET");
        mnuevodoc.addColumn("ISR");
        mnuevodoc.addColumn("Concepto SAT");
        mnuevodoc.addColumn("Unidad SAT");
        mnuevodoc.addColumn("Referencia");
        mnuevodoc.addColumn("Equipo");
        tNuevoDoc.setModel(mnuevodoc);
        ConfigLib.setTable(tNuevoDoc);
        /////////ObjetoImpuesto
        ConfigLib.addCheck(tNuevoDoc, ndobjimp);

        tNuevoDoc.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                tableListenerndoc(e);
            }
        });

        txtTipoCambioND.setText(utils.dbConsult("SELECT ROUND(getTipoCambio(CURDATE()),4)"));

//        FillCombo.cargarUnidadesNegocio(boxUNegocio, unegocioid, "");
        cargarTipoPago(boxFormaPago, tipopagoid, "");
        FillCombo.cargarMetodoPago(boxFormaPago, formapagocod, "");
        FillCombo.cargarUsosCFDI(boxUsoCFDI, usocfdi, " ");
        FillCombo.cargarEvidencias(boxEtiqueta, etiquetaid, " ");
        cargarCuentas();

        seleccionCliente(clienteor, utils.dbConsult("SELECT RazonSocial from clientes_tbl where ClienteID = '" + clienteor + "'"));
        validarCampos();
        doneload = true;
    }

    private void cargarCuentas() {
        String query = "SELECT CONCAT(Nombre,' ',(SELECT Nombre from monedas_tbl where monedas_tbl.Moneda = bancos_tbl.Moneda),' - ',Cuenta) as nocuenta, "
                + "BancoID,"
                + "Moneda "
                + "FROM bancos_tbl "
                + "where Status = true ";//"SELECT Tipo_Hab FROM tarifa_tbl WHERE Grupo ='" + boxGrupo.getSelectedItem() + "' AND Tarifa = '" + boxTarifa.getSelectedItem() + "'";
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        boxCuenta.removeAllItems();
        cuentaid.clear();
        monedascuentas.clear();
        try {
            boxCuenta.addItem("Cuentas");
            cuentaid.add("0");
            monedascuentas.add("100");
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                boxCuenta.addItem(rs.getString("nocuenta"));
                cuentaid.add(rs.getString("BancoID"));
                monedascuentas.add(rs.getString("Moneda"));
            }
        } catch (SQLException e) {
            System.out.println("Error " + e);
            utils.errorGenerado("dNuevoDoc / cargarCuentas / sqlex = " + e.toString());
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    dNuevoDoc() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    private void tableListenerndoc(javax.swing.event.TableModelEvent e) {
        if (e.getType() == TableModelEvent.UPDATE) {
            if (cont < 1) {
                int row = tNuevoDoc.getSelectedRow();
                int col = tNuevoDoc.getSelectedColumn();

                if (col == ndcantidad) {
                    calcularRenglon(row);
                    calcularTotalesDoc();
                }

                if (col == ndvaloru) {
                    calcularRenglon(row);
                    calcularTotalesDoc();
                }
                /////////ObjetoImpuesto
                if (col == ndobjimp) {
                    if (!(Boolean) tNuevoDoc.getValueAt(row, col)) {
                        cont = 1;
                        tNuevoDoc.setValueAt("0", row, ndiva);
                        cont = 1;
                        tNuevoDoc.setValueAt("0", row, ndivaret);
                    }
                    calcularRenglon(row);
                    calcularTotalesDoc();
                }
                /////////ObjetoImpuesto
                if (col == ndiva) {
                    calcularRenglon(row);
                    calcularTotalesDoc();
                }

                if (col == ndivaret) {
                    calcularRenglon(row);
                    calcularTotalesDoc();
                }

                if (col == ndisr) {
                    calcularRenglon(row);
                    calcularTotalesDoc();
                }

            } else {
                cont = 0;
            }
        }
    }

    private void tableListenerdocs(javax.swing.event.TableModelEvent e) {
        if (e.getType() == TableModelEvent.UPDATE) {
            if (cont < 1) {
                int row = tDocs.getSelectedRow();
                int col = tDocs.getSelectedColumn();

                if (col == 0) {
                    if (tDocs.getValueAt(row, col) != null) {
                        saveetiqueta.set(row, etiquetaid.get(boxEtiqueta.getSelectedIndex()));
                    }
                }

            } else {
                cont = 0;
            }
        }
    }

    public void calcularTotalesDoc() {
        double subtotal = 0, iva = 0, ivaret = 0, isr = 0, total = 0, subt = 0;
        for (int i = 0; i < mnuevodoc.getRowCount(); i++) {
            subt = Double.parseDouble(utils.dbConsult("SELECT digits('" + mnuevodoc.getValueAt(i, ndcantidad) + "')*digits('" + mnuevodoc.getValueAt(i, ndvaloru) + "')"));
            subtotal = subtotal + subt;
            //isr = isr + Double.parseDouble(utils.dbConsult("SELECT " + subt + "*( (digits('" + mnuevodoc.getValueAt(i, ndiva) + "')/100)-(digits('" + mnuevodoc.getValueAt(i, ndivaret) + "')/100)-(digits('" + mnuevodoc.getValueAt(i, ndisr) + "')/100))"));

            iva = iva + Double.parseDouble(utils.dbConsult("SELECT " + subt + "*(digits('" + mnuevodoc.getValueAt(i, ndiva) + "')/100)"));
            ivaret = ivaret + Double.parseDouble(utils.dbConsult("SELECT " + subt + "* (digits('" + mnuevodoc.getValueAt(i, ndivaret) + "')/100)"));
            isr = isr + Double.parseDouble(utils.dbConsult("SELECT " + subt + "*(digits('" + mnuevodoc.getValueAt(i, ndisr) + "')/100)"));

        }
        txtSubtotal.setText(utils.dbConsult("SELECT FORMAT(" + subtotal + ",2)"));
        txtIvand.setText(utils.dbConsult("SELECT FORMAT(" + iva + ",2)"));
        txtIvaretnd.setText(utils.dbConsult("SELECT FORMAT(" + ivaret + ",2)"));
        txtIsrnd.setText(utils.dbConsult("SELECT FORMAT(" + isr + ",2)"));
        txtTotal.setText(utils.dbConsult("SELECT FORMAT(" + (subtotal + (iva - ivaret - isr)) + ",2)"));
    }

    private void calcularRenglon(int row) {
        cont = 1;
        tNuevoDoc.setValueAt(utils.dbConsult("SELECT (digits('" + mnuevodoc.getValueAt(row, ndcantidad) + "')*digits('" + mnuevodoc.getValueAt(row, ndvaloru) + "'))*(1+ (digits('" + mnuevodoc.getValueAt(row, ndiva) + "')/100)-(digits('" + mnuevodoc.getValueAt(row, ndivaret) + "')/100) - (digits('" + mnuevodoc.getValueAt(row, ndisr) + "')/100) )"), row, ndsubtotal);
    }

    private void resetDoc() {
        txtTotal.setText("0.00");
        clientend = "0";
        txtCliente.setText("");
        mnuevodoc.setRowCount(0);
        saveaccesoriond.clear();
        txtSubtotal.setText("0.00");
        txtIvand.setText("0.00");
    }
    
    private void generarDocumentoRentasSingular() {
        if (!clientend.equals("0")) {
            //son 3 tipos de factura: Factura normal con contenedores y extras en el mismo doc, factura con los contenedores en uno y los extras en otro, y factura con todo separado, cada concepto y contenedor
            String moneda = boxMonedaND.getSelectedIndex() + "";

            //se inserta el primer registro correspondiente al primer contenedor o de todo si va junto
            //factura tipo 1
            String foliodoc = "";
            if (!esfactura) {
                foliodoc = utils.dbConsult("SELECT FolioInvoice from utils_tbl limit 1");

            }

            //versiontimbre
            String version = "40";

            //versiontimbre
            String id = utils.dbInsert("INSERT INTO facturas_tbl(NoFactura, Monto, TotalFactura,ClienteID,Fecha,Moneda,"
                    + "TipoCambio,TipoCambioAlta,FechaFac,Nota,IVA,IVARET,ISR,FechaVencimiento, SepararConceptosExtra,"
                    + " cFCierre, RefFac, Prefijo, UsuarioID, Factura, NotaFactura,EmpresaID,versiontimbre) VALUES("
                    + "'" + foliodoc + "',digits('" + txtSubtotal.getText() + "'),digits('" + txtTotal.getText() + "'),'" + clientend + "', (now()),"
                    + "'" + moneda + "',(SELECT digits('" + txtTipoCambioND.getText() + "')),(SELECT digits('" + txtTipoCambioND.getText() + "')),(now()),'" + txtNotaNuevoDoc.getText() + "',0.08,0,0,(SELECT DATE_ADD(CURDATE(), INTERVAL DiasCredito DAY) from clientes_tbl where clientes_tbl.ClienteID = '" + clientend + "'), "
                    + "false, false,false, '', '" + global.usuario + "', " + esfactura + ", '" + txtNotaNuevoDoc.getText() + "','1','" + version + "' )");

            if (ampl == true) {
                utils.dbUpdate("Update inventarioexterno_tbl_facturacion set factura='" + id + "', subtotalx='" + txtSubtotal.getText() + "', ivax='" + txtIvand.getText() + "', "
                        + "ivaretx='" + txtIvaretnd.getText() + "', isrx='" + txtIsrnd.getText() + "', totalx='" + txtTotal.getText() + "' where factID='" + amplfact + "'");
            }

            if (id.length() <= 11 && !id.isEmpty()) {
                if (!esfactura) {
                    utils.dbUpdate("UPDATE utils_tbl SET FolioInvoice = (FolioInvoice +1) where UtilID = 1");
                }
//bl // booking // contenedor // puede ser en la insercion de conceptosf_tbl o en facturas_tbl.NotaFactura
                String idcargo = "0";
                /*for (int j = 0; j < mnuevodoc.getRowCount(); j++) {
                    if (!saveaccesoriond.get(j).equals("0")) {
                        idcargo = utils.dbInsert("INSERT INTO cargosclientes_tbl(CClienteID, WContID, Concepto, Observaciones, Moneda, IVAc, IVARETc, ISRc) "
                                + "VALUES('" + saveaccesoriond.get(j) + "', '0', '" + mnuevodoc.getValueAt(j, nddesc) + "', 'factura directa', '1', (SELECT digits('" + mnuevodoc.getValueAt(j, ndiva) + "')/100),(SELECT digits('" + mnuevodoc.getValueAt(j, ndivaret) + "')/100),(SELECT digits('" + mnuevodoc.getValueAt(j, ndisr) + "')/100))");
                    } else {
                        idcargo = "0";
                    }
                    /////////ObjetoImpuesto
                    String idcon = utils.dbInsert("INSERT INTO conceptosf_tbl(FacturaID,TipoID, CobroID, Cantidad, Importe, Nota,Descripcion, objimpcf, IVAcf, IVARETcf, ISRcf, ItinerarioID, ConceptoSAT, UnidadSAT, VerComo, "
                            + "Contenedorc, UNegocioID, AccesorioID, proyectoid) "
                            + "VALUES('" + id + "', 2,'" + mnuevodoc.getValueAt(j, ndwo) + "', digits('" + mnuevodoc.getValueAt(j, ndcantidad) + "'), "
                            + "digits('" + mnuevodoc.getValueAt(j, ndvaloru) + "'), '','" + mnuevodoc.getValueAt(j, nddesc) + "', " + (Boolean) mnuevodoc.getValueAt(j, ndobjimp) + ", (SELECT digits('" + mnuevodoc.getValueAt(j, ndiva) + "')/100),"
                            + "(SELECT digits('" + mnuevodoc.getValueAt(j, ndivaret) + "')/100),(SELECT digits('" + mnuevodoc.getValueAt(j, ndisr) + "')/100), 0, '" + mnuevodoc.getValueAt(j, ndconsat) + "', "
                            + "'" + mnuevodoc.getValueAt(j, ndunisat) + "', '1',(SELECT Contenedor from workcontenedores_tbl where WContenedorID = '" + mnuevodoc.getValueAt(j, ndwo) + "'), "
                            + "'" + unegocioid.get(boxUNegocio.getSelectedIndex()) + "', '" + idcargo + "', '" + proyectoid.get(boxProyecto.getSelectedIndex()) + "')");
                    if (idcon.length() <= 11) {
                        //utils.dbUpdate("UPDATE workcontenedores_tbl SET StatusAdmin = 3, SeleccionFactura = false, UsuarioSeleccion = 0 where WContenedorID = '" + selwcontid.get(j) + "'");
                    }
                }*/

                idcargo = "0";
                
                for(int i=0; i<tNuevoDoc.getRowCount();i++) {
                
                // private int ndwo = 0, nddesc = 1, ndcantidad = 2, ndvaloru = 3, ndsubtotal = 4, ndobjimp = 5, ndiva = 6, ndivaret = 7, ndisr = 8, ndconsat = 9, ndunisat = 10;
                /////////ObjetoImpuesto | todos los conceptos
                String idcon = utils.dbInsert("INSERT INTO conceptosf_tbl(FacturaID,TipoID, CobroID, Cantidad, Importe, Nota,Descripcion, objimpcf, IVAcf, IVARETcf, ISRcf, ItinerarioID, ConceptoSAT, UnidadSAT, VerComo, "
                        + "Contenedorc, AccesorioID,rentasPatios) "
                        + "VALUES('" + id + "', 2,'" + mnuevodoc.getValueAt(i, ndwo) + "', '" + mnuevodoc.getValueAt(i, ndcantidad) + "', "
                        + "digits('" + mnuevodoc.getValueAt(i, ndvaloru) + "'), '','" + mnuevodoc.getValueAt(i, nddesc) + "', " + (Boolean) mnuevodoc.getValueAt(i, ndobjimp) + ", (SELECT digits('" + mnuevodoc.getValueAt(i, ndiva) + "')/100),"
                        + "(SELECT digits('" + mnuevodoc.getValueAt(i, ndivaret) + "')/100),(SELECT digits('" + mnuevodoc.getValueAt(i, ndisr) + "')/100), 0, '" + mnuevodoc.getValueAt(i, ndconsat) + "', "
                        + "'" + mnuevodoc.getValueAt(i, ndunisat) + "', '1','', "
                        + "'" + idcargo + "', true)");
                
                utils.dbUpdate("UPDATE inventarioexterno_tbl set facturado=true where inventarioID='"+inventarioID.get(i)+"'");
                
                /* 1 concepto, forma anterior
                
                String idcon = utils.dbInsert("INSERT INTO conceptosf_tbl(FacturaID,TipoID, CobroID, Cantidad, Importe, Nota,Descripcion, objimpcf, IVAcf, IVARETcf, ISRcf, ItinerarioID, ConceptoSAT, UnidadSAT, VerComo, "
                        + "Contenedorc, UNegocioID, AccesorioID, proyectoid,rentasPatios) "
                        + "VALUES('" + id + "', 2,'" + mnuevodoc.getValueAt(0, ndwo) + "', '1', "
                        + "digits('" + txtSubtotal.getText() + "'), '','" + conceptofact + "', " + (Boolean) mnuevodoc.getValueAt(0, ndobjimp) + ", (SELECT digits('" + mnuevodoc.getValueAt(0, 6) + "')/100),"
                        + "(SELECT digits('" + txtIvaretnd.getText() + "')/100),(SELECT digits('" + txtIsrnd.getText() + "')/100), 0, '" + mnuevodoc.getValueAt(0, ndconsat) + "', "
                        + "'" + mnuevodoc.getValueAt(0, ndunisat) + "', '1','', "
                        + "'" + unegocioid.get(boxUNegocio.getSelectedIndex()) + "', '" + idcargo + "', '" + proyectoid.get(boxProyecto.getSelectedIndex()) + "', true)");
                
                */
                }
                
                if (!esfactura) {
                    try {
                        basic.FuncionesCXC.verInvoice(id);
                    } catch (JRException ex) {
                        JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                this.dispose();
            } else {//if id error
                JOptionPane.showMessageDialog(this, "Error " + id, "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            String msj = "";
            if (clientend.equals("0")) {
                msj = "Favor de seleccionar un cliente del listado.\n";
            }
            JOptionPane.showMessageDialog(this, "Error\n" + msj, "Error", JOptionPane.ERROR_MESSAGE);
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

        dClientes = new javax.swing.JDialog();
        jPanel23 = new javax.swing.JPanel();
        jScrollPane19 = new javax.swing.JScrollPane();
        tClientes = new javax.swing.JTable();
        jPanel24 = new javax.swing.JPanel();
        txtBusquedaCliente = new javax.swing.JTextField();
        dWO = new javax.swing.JDialog();
        jPanel36 = new javax.swing.JPanel();
        jScrollPane27 = new javax.swing.JScrollPane();
        tWOs = new javax.swing.JTable();
        jPanel37 = new javax.swing.JPanel();
        txtBusquedaWOs = new javax.swing.JTextField();
        dConceptosF = new javax.swing.JDialog();
        jScrollPane5 = new javax.swing.JScrollPane();
        tConceptosF = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        txtBusquedaC = new javax.swing.JTextField();
        dTextoExtra = new javax.swing.JDialog();
        jScrollPane20 = new javax.swing.JScrollPane();
        txtConExtra = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        dArchivosf = new javax.swing.JDialog();
        jPanel32 = new javax.swing.JPanel();
        scroll2 = new javax.swing.JScrollPane();
        tDocs = new javax.swing.JTable();
        jPanel33 = new javax.swing.JPanel();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane18 = new javax.swing.JScrollPane();
        tNuevoDoc = new javax.swing.JTable();
        txtCliente = new javax.swing.JTextField();
        boxMonedaND = new javax.swing.JComboBox<>();
        jLabel70 = new javax.swing.JLabel();
        txtNotaNuevoDoc = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        txtTipoCambioND = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();
        txtSubtotal = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        txtIvand = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jButton16 = new javax.swing.JButton();
        jLabel90 = new javax.swing.JLabel();
        txtIvaretnd = new javax.swing.JTextField();
        jLabel91 = new javax.swing.JLabel();
        txtIsrnd = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel92 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        boxCondicionP = new WiderCombo();
        jButton1 = new javax.swing.JButton();
        boxTipo = new javax.swing.JComboBox<>();
        jLabel93 = new javax.swing.JLabel();
        lblCuenta = new javax.swing.JLabel();
        boxCuenta = new WiderCombo();
        lblFormaPago = new javax.swing.JLabel();
        boxFormaPago = new WiderCombo();
        lblReferencia = new javax.swing.JLabel();
        txtReferencia = new javax.swing.JTextField();
        lblBancoCliente = new javax.swing.JLabel();
        txtBancoP = new javax.swing.JTextField();
        lblCuentaCliente = new javax.swing.JLabel();
        txtNoCuentaP = new javax.swing.JTextField();
        txtRFCBancoP = new javax.swing.JTextField();
        lblRFCCliente = new javax.swing.JLabel();
        lblFormaPago1 = new javax.swing.JLabel();
        boxUsoCFDI = new WiderCombo();
        cEnvio = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();
        factnew = new javax.swing.JButton();

        dClientes.setIconImage(new javax.swing.ImageIcon("images\\icon.png").getImage());
        dClientes.setMinimumSize(new java.awt.Dimension(600, 500));
        dClientes.setModal(true);

        jPanel23.setLayout(new java.awt.BorderLayout());

        tClientes.setModel(new javax.swing.table.DefaultTableModel(
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
        tClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tClientesMouseClicked(evt);
            }
        });
        jScrollPane19.setViewportView(tClientes);

        jPanel23.add(jScrollPane19, java.awt.BorderLayout.CENTER);

        jPanel24.setLayout(new java.awt.BorderLayout());

        txtBusquedaCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtBusquedaCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBusquedaClienteKeyPressed(evt);
            }
        });
        jPanel24.add(txtBusquedaCliente, java.awt.BorderLayout.CENTER);

        jPanel23.add(jPanel24, java.awt.BorderLayout.PAGE_START);

        dClientes.getContentPane().add(jPanel23, java.awt.BorderLayout.CENTER);

        dWO.setIconImage(new javax.swing.ImageIcon("images\\icon.png").getImage());
        dWO.setMinimumSize(new java.awt.Dimension(600, 500));
        dWO.setModal(true);

        jPanel36.setLayout(new java.awt.BorderLayout());

        tWOs.setModel(new javax.swing.table.DefaultTableModel(
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
        tWOs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tWOsMouseClicked(evt);
            }
        });
        jScrollPane27.setViewportView(tWOs);

        jPanel36.add(jScrollPane27, java.awt.BorderLayout.CENTER);

        jPanel37.setLayout(new java.awt.BorderLayout());

        txtBusquedaWOs.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtBusquedaWOs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBusquedaWOsKeyPressed(evt);
            }
        });
        jPanel37.add(txtBusquedaWOs, java.awt.BorderLayout.CENTER);

        jPanel36.add(jPanel37, java.awt.BorderLayout.PAGE_START);

        dWO.getContentPane().add(jPanel36, java.awt.BorderLayout.CENTER);

        dConceptosF.setIconImage(new ImageIcon("images\\icon.png").getImage());
        dConceptosF.setMinimumSize(new java.awt.Dimension(800, 400));
        dConceptosF.setModal(true);

        tConceptosF.setModel(new javax.swing.table.DefaultTableModel(
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
        tConceptosF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tConceptosFMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tConceptosF);

        dConceptosF.getContentPane().add(jScrollPane5, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.BorderLayout());

        txtBusquedaC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBusquedaCKeyPressed(evt);
            }
        });
        jPanel2.add(txtBusquedaC, java.awt.BorderLayout.CENTER);

        dConceptosF.getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        dTextoExtra.setIconImage(new javax.swing.ImageIcon("images\\icon.png").getImage());
        dTextoExtra.setMinimumSize(new java.awt.Dimension(500, 300));
        dTextoExtra.setModal(true);
        dTextoExtra.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                dTextoExtraWindowClosing(evt);
            }
        });

        txtConExtra.setColumns(20);
        txtConExtra.setLineWrap(true);
        txtConExtra.setRows(5);
        txtConExtra.setWrapStyleWord(true);
        txtConExtra.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jScrollPane20.setViewportView(txtConExtra);

        dTextoExtra.getContentPane().add(jScrollPane20, java.awt.BorderLayout.CENTER);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jButton4.setText("Guardar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4);

        dTextoExtra.getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        dArchivosf.setIconImage(new javax.swing.ImageIcon("images\\icon.png").getImage());
        dArchivosf.setMinimumSize(new java.awt.Dimension(400, 400));
        dArchivosf.setModal(true);

        jPanel32.setLayout(new java.awt.BorderLayout());

        scroll2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5));

        tDocs.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tDocs.setModel(new javax.swing.table.DefaultTableModel(
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
        tDocs.setFillsViewportHeight(true);
        tDocs.setRowHeight(20);
        tDocs.getTableHeader().setReorderingAllowed(false);
        tDocs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tDocsMouseClicked(evt);
            }
        });
        scroll2.setViewportView(tDocs);

        jPanel32.add(scroll2, java.awt.BorderLayout.CENTER);

        jPanel33.setBackground(new java.awt.Color(255, 255, 255));
        jPanel33.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5));
        jPanel33.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 5));

        jButton24.setText("Agregar Doc");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });
        jPanel33.add(jButton24);

        jButton25.setText("Eliminar Doc");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });
        jPanel33.add(jButton25);

        jPanel32.add(jPanel33, java.awt.BorderLayout.PAGE_START);

        dArchivosf.getContentPane().add(jPanel32, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Seleccion forma de timbrado");
        setMinimumSize(new java.awt.Dimension(1030, 700));
        setModal(true);
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(null);

        tNuevoDoc.setModel(new javax.swing.table.DefaultTableModel(
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
        tNuevoDoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tNuevoDocMouseClicked(evt);
            }
        });
        tNuevoDoc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tNuevoDocKeyPressed(evt);
            }
        });
        jScrollPane18.setViewportView(tNuevoDoc);

        jPanel8.add(jScrollPane18);
        jScrollPane18.setBounds(30, 130, 970, 240);

        txtCliente.setEditable(false);
        txtCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtClienteMouseClicked(evt);
            }
        });
        jPanel8.add(txtCliente);
        txtCliente.setBounds(30, 90, 230, 30);

        boxMonedaND.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        boxMonedaND.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MXN", "USD" }));
        jPanel8.add(boxMonedaND);
        boxMonedaND.setBounds(270, 90, 100, 30);

        jLabel70.setText("Cliente");
        jPanel8.add(jLabel70);
        jLabel70.setBounds(30, 70, 230, 20);

        txtNotaNuevoDoc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel8.add(txtNotaNuevoDoc);
        txtNotaNuevoDoc.setBounds(490, 90, 230, 30);

        jLabel71.setText("Nota");
        jPanel8.add(jLabel71);
        jLabel71.setBounds(490, 70, 230, 20);

        txtTipoCambioND.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTipoCambioND.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTipoCambioND.setText("20.00");
        jPanel8.add(txtTipoCambioND);
        txtTipoCambioND.setBounds(380, 90, 100, 30);

        jLabel72.setText("Tipo Cambio");
        jPanel8.add(jLabel72);
        jLabel72.setBounds(380, 70, 100, 20);

        jButton15.setText("Agregar Concepto");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton15);
        jButton15.setBounds(850, 90, 150, 30);

        txtSubtotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSubtotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSubtotal.setText("0.00");
        jPanel8.add(txtSubtotal);
        txtSubtotal.setBounds(900, 380, 100, 30);

        jLabel73.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel73.setText("SubTotal");
        jPanel8.add(jLabel73);
        jLabel73.setBounds(800, 380, 100, 30);

        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel74.setText("IVA");
        jPanel8.add(jLabel74);
        jLabel74.setBounds(800, 410, 100, 30);

        txtIvand.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtIvand.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtIvand.setText("0.00");
        jPanel8.add(txtIvand);
        txtIvand.setBounds(900, 410, 100, 30);

        jLabel75.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel75.setText("Total");
        jPanel8.add(jLabel75);
        jLabel75.setBounds(800, 510, 100, 30);

        txtTotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotal.setText("0.00");
        jPanel8.add(txtTotal);
        txtTotal.setBounds(900, 510, 100, 30);

        jButton16.setText("Guardar");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton16);
        jButton16.setBounds(900, 580, 100, 30);

        jLabel90.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel90.setText("IVARET");
        jPanel8.add(jLabel90);
        jLabel90.setBounds(800, 440, 100, 30);

        txtIvaretnd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtIvaretnd.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtIvaretnd.setText("0.00");
        jPanel8.add(txtIvaretnd);
        txtIvaretnd.setBounds(900, 440, 100, 30);

        jLabel91.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel91.setText("ISR");
        jPanel8.add(jLabel91);
        jLabel91.setBounds(800, 470, 100, 30);

        txtIsrnd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtIsrnd.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtIsrnd.setText("0.00");
        jPanel8.add(txtIsrnd);
        txtIsrnd.setBounds(900, 470, 100, 30);

        jButton5.setText("Aplicar Concepto");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton5);
        jButton5.setBounds(30, 400, 130, 30);

        jLabel92.setText("Moneda");
        jPanel8.add(jLabel92);
        jLabel92.setBounds(270, 70, 100, 20);

        jLabel95.setText("Condicion de pago");
        jPanel8.add(jLabel95);
        jLabel95.setBounds(730, 70, 100, 20);

        boxCondicionP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        boxCondicionP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Contado" }));
        jPanel8.add(boxCondicionP);
        boxCondicionP.setBounds(730, 90, 100, 30);

        jButton1.setText("Previsualizar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton1);
        jButton1.setBounds(790, 580, 100, 30);

        boxTipo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        boxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Documento de Cobro" }));
        boxTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxTipoActionPerformed(evt);
            }
        });
        jPanel8.add(boxTipo);
        boxTipo.setBounds(30, 30, 230, 30);

        jLabel93.setText("Tipo");
        jPanel8.add(jLabel93);
        jLabel93.setBounds(30, 10, 230, 20);

        lblCuenta.setText("Cuenta");
        jPanel8.add(lblCuenta);
        lblCuenta.setBounds(510, 440, 280, 20);

        boxCuenta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        boxCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxCuentaActionPerformed(evt);
            }
        });
        jPanel8.add(boxCuenta);
        boxCuenta.setBounds(510, 460, 280, 30);

        lblFormaPago.setText("Forma de Pago");
        jPanel8.add(lblFormaPago);
        lblFormaPago.setBounds(320, 440, 180, 20);

        boxFormaPago.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        boxFormaPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxFormaPagoActionPerformed(evt);
            }
        });
        jPanel8.add(boxFormaPago);
        boxFormaPago.setBounds(320, 460, 180, 30);

        lblReferencia.setForeground(new java.awt.Color(51, 51, 51));
        lblReferencia.setText("Referencia");
        jPanel8.add(lblReferencia);
        lblReferencia.setBounds(30, 500, 210, 20);

        txtReferencia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtReferencia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel8.add(txtReferencia);
        txtReferencia.setBounds(30, 520, 210, 30);

        lblBancoCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblBancoCliente.setForeground(new java.awt.Color(51, 51, 51));
        lblBancoCliente.setText("Banco");
        jPanel8.add(lblBancoCliente);
        lblBancoCliente.setBounds(250, 500, 150, 20);

        txtBancoP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtBancoP.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel8.add(txtBancoP);
        txtBancoP.setBounds(250, 520, 150, 30);

        lblCuentaCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblCuentaCliente.setForeground(new java.awt.Color(51, 51, 51));
        lblCuentaCliente.setText("No. Cuenta");
        jPanel8.add(lblCuentaCliente);
        lblCuentaCliente.setBounds(410, 500, 130, 20);

        txtNoCuentaP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNoCuentaP.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel8.add(txtNoCuentaP);
        txtNoCuentaP.setBounds(410, 520, 160, 30);

        txtRFCBancoP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtRFCBancoP.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel8.add(txtRFCBancoP);
        txtRFCBancoP.setBounds(580, 520, 160, 30);

        lblRFCCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblRFCCliente.setForeground(new java.awt.Color(51, 51, 51));
        lblRFCCliente.setText("RFC Banco");
        jPanel8.add(lblRFCCliente);
        lblRFCCliente.setBounds(580, 500, 150, 20);

        lblFormaPago1.setText("Uso CFDI");
        jPanel8.add(lblFormaPago1);
        lblFormaPago1.setBounds(30, 440, 280, 20);

        boxUsoCFDI.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        boxUsoCFDI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxUsoCFDIActionPerformed(evt);
            }
        });
        jPanel8.add(boxUsoCFDI);
        boxUsoCFDI.setBounds(30, 460, 280, 30);

        cEnvio.setText("Envio doc");
        cEnvio.setContentAreaFilled(false);
        jPanel8.add(cEnvio);
        cEnvio.setBounds(690, 580, 90, 30);

        jButton2.setText("Documentos");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton2);
        jButton2.setBounds(30, 580, 120, 30);

        factnew.setText("Generar Factura Nueva");
        factnew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                factnewActionPerformed(evt);
            }
        });
        jPanel8.add(factnew);
        factnew.setBounds(790, 620, 210, 30);

        getContentPane().add(jPanel8, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus

    }//GEN-LAST:event_formWindowGainedFocus

    private void tNuevoDocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tNuevoDocMouseClicked
        int row = tNuevoDoc.getSelectedRow();
        int col = tNuevoDoc.getSelectedColumn();
        if (row >= 0) {
            if (evt.getClickCount() == 2) {
                if (col == nddesc) {
                    txtConExtra.setText(tNuevoDoc.getValueAt(row, nddesc).toString());
                    dTextoExtra.setLocationRelativeTo(this);
                    dTextoExtra.setVisible(true);
                }
                if (col == ndwo) {
                    cargarWOs();
                    dWO.setLocationRelativeTo(this);
                    dWO.setVisible(true);
                }
            }
        }
    }//GEN-LAST:event_tNuevoDocMouseClicked

    private void tNuevoDocKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tNuevoDocKeyPressed
        int row = tNuevoDoc.getSelectedRow();
        if (row >= 0) {
            if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
                mnuevodoc.removeRow(row);
                saveaccesoriond.remove(row);
                calcularTotalesDoc();
            }
        }
    }//GEN-LAST:event_tNuevoDocKeyPressed

    private void txtClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtClienteMouseClicked
        if (evt.getClickCount() == 2) {
            if (clienteor.equals("0")) {
                cargarClientesTabla();
                dClientes.setLocationRelativeTo(this);
                dClientes.setVisible(true);
            }
        }
    }//GEN-LAST:event_txtClienteMouseClicked

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed

        Object temp[] = new Object[mnuevodoc.getColumnCount()];
        temp[ndwo] = "0";
        temp[nddesc] = "";
        temp[ndcantidad] = "1";
        temp[ndvaloru] = "0.00";
        temp[ndsubtotal] = "0.00";
        temp[ndobjimp] = true;
        temp[ndiva] = "8.0";
        temp[ndivaret] = "0.0";
        temp[ndisr] = "0.0";
        temp[ndconsat] = "";
        temp[ndunisat] = "";
        temp[ndref] = "";
        temp[ndcont] = "";

        mnuevodoc.addRow(temp);
        saveaccesoriond.add("0");
        
        inventarioID.add("0");

    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        boolean continuar = true;
        if (boxTipo.getSelectedIndex() == 1) {
            if (boxUsoCFDI.getSelectedIndex() == 0) {
                continuar = false;
                JOptionPane.showMessageDialog(this, "Es necesario capturar el uso de cfdi.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (boxTipo.getSelectedIndex() == 1) {
            if (txtReferencia.getText().isEmpty()) {
                continuar = false;
                JOptionPane.showMessageDialog(this, "Es necesario capturar una referencia de pago para el anticipo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        /*if (saveetiqueta.contains("0")) {
            continuar = false;
            JOptionPane.showMessageDialog(this, "Es necesario capturar etiquetar todos los documentos", "Error", JOptionPane.ERROR_MESSAGE);
        }*/

        if (continuar) {
            freal = true;
            generarDocumento();
        }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int row = tNuevoDoc.getSelectedRow();
        if (row >= 0) {
            cargarConceptosFactura();
            dConceptosF.setLocationRelativeTo(this);
            dConceptosF.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Es necesario seleccionar un renglon para asignar el concepto", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void tClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tClientesMouseClicked
        int row = tClientes.getSelectedRow();
        if (row >= 0) {
            if (evt.getClickCount() == 2) {
                seleccionCliente(clientestid.get(row), tClientes.getValueAt(row, 0).toString());
            }
        }
    }//GEN-LAST:event_tClientesMouseClicked

    private void txtBusquedaClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaClienteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarClientesTabla();
        }
    }//GEN-LAST:event_txtBusquedaClienteKeyPressed

    private void tWOsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tWOsMouseClicked
        int row = tWOs.getSelectedRow();
        if (row >= 0) {
            if (evt.getClickCount() == 2) {
                tNuevoDoc.setValueAt(tWOs.getValueAt(row, 1), tNuevoDoc.getSelectedRow(), ndwo);
                tNuevoDoc.setValueAt(tWOs.getValueAt(row, 2), tNuevoDoc.getSelectedRow(), ndcont);
                tNuevoDoc.setValueAt(tWOs.getValueAt(row, 3), tNuevoDoc.getSelectedRow(), ndref);
                dWO.dispose();
            }
        }
    }//GEN-LAST:event_tWOsMouseClicked

    private void txtBusquedaWOsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaWOsKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarWOs();
        }
    }//GEN-LAST:event_txtBusquedaWOsKeyPressed

    private void tConceptosFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tConceptosFMouseClicked
        int row = tConceptosF.getSelectedRow();
        int ndrow = tNuevoDoc.getSelectedRow();

        if (row >= 0) {
            if (evt.getClickCount() == 2) {
                cont = 1;
                tNuevoDoc.setValueAt(utils.dbConsult("SELECT IF(ConceptoSATcc = '' or ConceptoSATcc is null, Nombre, CONCAT(IFNULL((SELECT Concepto from conceptossatr_tbl where Codigo = ConceptoSATcc),''),'\n',Nombre)) from conceptoscargosclientes_tbl where CargoID = '" + concargoid.get(row) + "'"), ndrow, nddesc);
                cont = 1;
                tNuevoDoc.setValueAt(utils.dbConsult("SELECT ROUND(IF('" + boxMonedaND.getSelectedIndex() + "' = MonedaBase, ImporteBase, IF('" + boxMonedaND.getSelectedIndex() + "' = 0, ImporteBase*digits('" + txtTipoCambioND.getText() + "'), ImporteBase/digits('" + txtTipoCambioND.getText() + "') ) ),2) from conceptoscargosclientes_tbl where CargoID = '" + concargoid.get(row) + "'"), ndrow, ndvaloru);
                /////////ObjetoImpuesto
                cont = 1;
                tNuevoDoc.setValueAt(Boolean.parseBoolean(utils.dbConsult("SELECT IF(objimp, 'true', 'false') from conceptoscargosclientes_tbl where CargoID = '" + concargoid.get(row) + "'")), ndrow, ndobjimp);
                /////////ObjetoImpuesto
                cont = 1;
                tNuevoDoc.setValueAt(utils.dbConsult("SELECT ROUND(IVA*100,2) from conceptoscargosclientes_tbl where CargoID = '" + concargoid.get(row) + "'"), ndrow, ndiva);
                cont = 1;
                tNuevoDoc.setValueAt(utils.dbConsult("SELECT ROUND(IVARET*100,2) from conceptoscargosclientes_tbl where CargoID = '" + concargoid.get(row) + "'"), ndrow, ndivaret);
                cont = 1;
                tNuevoDoc.setValueAt(utils.dbConsult("SELECT ROUND(ISR*100,2) from conceptoscargosclientes_tbl where CargoID = '" + concargoid.get(row) + "'"), ndrow, ndisr);
                cont = 1;
                tNuevoDoc.setValueAt(utils.dbConsult("SELECT IFNULL((SELECT ConceptoSATcc from conceptoscargosclientes_tbl where CargoID = '" + concargoid.get(row) + "'),'')"), ndrow, ndconsat);
                cont = 1;
                tNuevoDoc.setValueAt(utils.dbConsult("SELECT IFNULL((SELECT UnidadSATcc from conceptoscargosclientes_tbl where CargoID = '" + concargoid.get(row) + "'),'')"), ndrow, ndunisat);
                saveaccesoriond.set(ndrow, concargoid.get(row));
                calcularRenglon(ndrow);
                calcularTotalesDoc();
                dConceptosF.dispose();
            }
        }
    }//GEN-LAST:event_tConceptosFMouseClicked

    private void txtBusquedaCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaCKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarConceptosFactura();
        }
    }//GEN-LAST:event_txtBusquedaCKeyPressed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String texto = txtConExtra.getText();
        if (texto.length() < 1000) {

            tNuevoDoc.setValueAt(txtConExtra.getText(), tNuevoDoc.getSelectedRow(), nddesc);

            dTextoExtra.dispose();
        } else {
            JOptionPane.showMessageDialog(dTextoExtra, "Texto muy largo " + texto.length() + "", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void dTextoExtraWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_dTextoExtraWindowClosing
        //        if (!txtConExtra.getText().isEmpty()) {
        //            utils.dbUpdate("UPDATE conceptosf_tbl SET Descripcion = '" + txtConExtra.getText() + "' where ConceptoID = '" + conceptosexid.get(tDesglose.getSelectedRow()) + "'");
        //            cont = 1;
        //            tDesglose.setValueAt(txtConExtra.getText(), tDesglose.getSelectedRow(), 1);
        //        }
    }//GEN-LAST:event_dTextoExtraWindowClosing

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        freal = false;
        generarDocumento();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void boxTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxTipoActionPerformed
        validarCampos();
        obtenerDatosPago();
        if (boxTipo.getSelectedIndex() == 0) {
            mnuevodoc.setRowCount(0);
        } else {
            String conid = utils.dbConsult("SELECT IFNULL((SELECT CargoID from conceptoscargosclientes_tbl where Nombre = 'Anticipo' and Status = false order by CargoID asc limit 1),0)");
            mnuevodoc.setRowCount(0);
            String temp[] = new String[mnuevodoc.getColumnCount()];
            temp[ndwo] = "0";
            temp[nddesc] = "Anticipo";
            temp[ndcantidad] = "1";
            temp[ndvaloru] = "0.00";
            temp[ndsubtotal] = "0.00";
            temp[ndiva] = "8.0";
            temp[ndivaret] = "0.0";
            temp[ndisr] = "0.0";
            temp[ndconsat] = "84111506";
            temp[ndunisat] = "ACT";
            mnuevodoc.addRow(temp);
            saveaccesoriond.add(conid);
        }
    }//GEN-LAST:event_boxTipoActionPerformed

    private void boxCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxCuentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boxCuentaActionPerformed

    private void tDocsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tDocsMouseClicked
        int row = tDocs.getSelectedRow();
        if (row >= 0) {
            if (evt.getClickCount() == 2) {
                utils.openFile(new File(archivosfac.get(row)));
            }
        }
    }//GEN-LAST:event_tDocsMouseClicked

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        JFileChooser fcxls = new JFileChooser();
        fcxls.setMultiSelectionEnabled(false);
        fcxls.setFileSelectionMode(JFileChooser.FILES_ONLY);

        if (fcxls.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fcxls.getSelectedFile();
            macobros.addRow(new String[]{"", file.getName()});
            archivosfac.add(file.getAbsolutePath());
            saveetiqueta.add("0");

        }
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        int row = tDocs.getSelectedRow();
        if (row >= 0) {
            macobros.removeRow(row);
            archivosfac.remove(row);
        }
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dArchivosf.setLocationRelativeTo(this);
        dArchivosf.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void boxFormaPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxFormaPagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boxFormaPagoActionPerformed

    private void boxUsoCFDIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxUsoCFDIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boxUsoCFDIActionPerformed

    private void factnewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_factnewActionPerformed

        generarDocumentoRentasSingular();
        
    }//GEN-LAST:event_factnewActionPerformed

    private void validarCampos() {
        if (boxTipo.getSelectedIndex() == 0) {
            lblCuenta.setVisible(false);
            boxCuenta.setVisible(false);
            lblFormaPago.setVisible(false);
            boxFormaPago.setVisible(false);
            lblReferencia.setVisible(false);
            txtReferencia.setVisible(false);
            lblBancoCliente.setVisible(false);
            txtBancoP.setVisible(false);
            lblCuentaCliente.setVisible(false);
            txtNoCuentaP.setVisible(false);
            lblRFCCliente.setVisible(false);
            txtRFCBancoP.setVisible(false);
        }
        if (boxTipo.getSelectedIndex() == 1) {
            lblCuenta.setVisible(true);
            boxCuenta.setVisible(true);
            lblFormaPago.setVisible(true);
            boxFormaPago.setVisible(true);
            lblReferencia.setVisible(true);
            txtReferencia.setVisible(true);
            lblBancoCliente.setVisible(true);
            txtBancoP.setVisible(true);
            lblCuentaCliente.setVisible(true);
            txtNoCuentaP.setVisible(true);
            lblRFCCliente.setVisible(true);
            txtRFCBancoP.setVisible(true);
        }
    }

    private void seleccionCliente(String idcliente, String nombrec) {
        clientend = idcliente;
        boxMonedaND.setSelectedItem(utils.dbConsult("SELECT IFNULL((SELECT Nombre from monedas_tbl where monedas_tbl.Moneda = clientes_tbl.Moneda), 'USD') from clientes_tbl where ClienteID = '" + idcliente + "'"));
        txtCliente.setText(nombrec);
        obtenerDatosPago();
        cargarDiasCredito(idcliente);
        dClientes.dispose();
    }

    private void obtenerDatosPago() {
        if (boxTipo.getSelectedIndex() == 1) {
            if (!clientend.equals("0")) {
                String datosbanco[] = utils.dbConsult("SELECT CONCAT(IFNULL(Banco,''),';',IFNULL(NoCuenta,''),';',IFNULL(RFCBanco,'')) from clientes_tbl where ClienteID = '" + clientend + "'").split(";");
                try {
                    txtBancoP.setText(datosbanco[0]);
                } catch (Exception e) {

                }
                try {
                    txtNoCuentaP.setText(datosbanco[1]);
                } catch (Exception e) {

                }
                try {
                    txtRFCBancoP.setText(datosbanco[2]);
                } catch (Exception e) {

                }
            }
        }
    }

    private void generarDocumento() {
        int check = 0;// 0 no problem. 1 un concepto no tiene accesorio.
        Boolean body = Boolean.parseBoolean(utils.dbConsult("SELECT IF(CorreoBody = 1, 'true', 'false') from clientes_tbl where ClienteID = '" + clientend + "'"));
        for (int j = 0; j < mnuevodoc.getRowCount(); j++) {
            if (saveaccesoriond.get(j).equals("0")) {
                check = 1;
                System.out.println("saveaccesoriond("+j+") ");
            }
        }
        if (check == 0) {
            if (!clientend.equals("0")) {
                System.out.println("entra");
                //son 3 tipos de factura: Factura normal con contenedores y extras en el mismo doc, factura con los contenedores en uno y los extras en otro, y factura con todo separado, cada concepto y contenedor
                String moneda = boxMonedaND.getSelectedIndex() + "", status = "1", formapago = "", metodopago = "", condicionpago = "", vencimiento = "(SELECT DATE_ADD(CURDATE(), INTERVAL " + boxCondicionP.getSelectedIndex() + " DAY))", ucfdi = "", clientef = "0", generacionfac = "0";
                if (!freal) {
                    status = "null";
                }
                //se inserta el primer registro correspondiente al primer contenedor o de todo si va junto
                //factura tipo 1
                String foliodoc = "";
                if (esinvoice && freal) {
                    foliodoc = utils.dbConsult("SELECT FolioInvoice from utils_tbl limit 1");
                }
                String anticipo = "false";
                if (boxTipo.getSelectedIndex() == 1) {
                    anticipo = "true";
                    status = "2";
                    generacionfac = "0";
                    formapago = formapagocod.get(boxFormaPago.getSelectedIndex());
                    metodopago = "PUE";
                    condicionpago = "CONTADO";
                    vencimiento = "CURDATE()";
                    ucfdi = usocfdi.get(boxUsoCFDI.getSelectedIndex());
                    clientef = clientend;
                }

                //versiontimbre
                String version = "40";

                //versiontimbre
                String id = utils.dbInsert("INSERT INTO facturas_tbl(NoFactura, Monto, TotalFactura, ClienteID, Fecha, Moneda, TipoCambio, TipoCambioAlta, FechaFac, Nota, IVA, IVARET, ISR,"
                        + "FechaVencimiento, SepararConceptosExtra, cFCierre, RefFac, Prefijo, UsuarioID, Factura, NotaFactura, EmpresaID, Status, anticipo, FormaPago, MetodoPago, CondicionPago, "
                        + "UsoCFDI, GeneracionFactura, ClienteFacturadoID, versiontimbre) "
                        + "VALUES('" + foliodoc + "',digits('" + txtSubtotal.getText() + "'),digits('" + txtTotal.getText() + "'),'" + clientend + "',(now()),"
                        + "'" + moneda + "',(SELECT digits('" + txtTipoCambioND.getText() + "')),(SELECT digits('" + txtTipoCambioND.getText() + "')),(now()),'" + txtNotaNuevoDoc.getText().replace("'", "\\'") + "',"
                        + "0.08, 0, 0, " + vencimiento + ", false, false, false, '', '" + global.usuario + "', " + !esinvoice + ", '" + txtNotaNuevoDoc.getText().replace("'", "\\'") + "', "
                        + "'" + empresaid + "', " + status + ", " + anticipo + ", '" + formapago + "', '" + metodopago + "', '" + condicionpago + "', '" + ucfdi + "', 0, '" + clientef + "', '" + version + "')");
                    
                if (id.length() <= 11 && !id.isEmpty()) {
                    if (esinvoice && freal) {
                        utils.dbUpdate("UPDATE utils_tbl SET FolioInvoice = (FolioInvoice +1) where UtilID = 1");
                    }
                    //bl // booking // contenedor // puede ser en la insercion de conceptosf_tbl o en facturas_tbl.NotaFactura
                    String idcargo = "0";
                    for (int j = 0; j < mnuevodoc.getRowCount(); j++) {
                        if (!saveaccesoriond.get(j).equals("0")) {
                            idcargo = utils.dbInsert("INSERT INTO cargosclientes_tbl(CClienteID, WContID, Concepto, Observaciones, Moneda, IVAc, IVARETc, ISRc) "
                                    + "VALUES('" + saveaccesoriond.get(j) + "', '0', '" + mnuevodoc.getValueAt(j, nddesc).toString().replace("'", "\\'") + "', 'factura directa', '1', (SELECT digits('" + mnuevodoc.getValueAt(j, ndiva) + "')/100),(SELECT digits('" + mnuevodoc.getValueAt(j, ndivaret) + "')/100),(SELECT digits('" + mnuevodoc.getValueAt(j, ndisr) + "')/100))");
                        } else {
                            idcargo = "0";
                        }
                        /////////ObjetoImpuesto
                        String idcon = utils.dbInsert("INSERT INTO conceptosf_tbl(FacturaID,TipoID, CobroID, Cantidad, Importe, Nota,Descripcion, objimpcf, IVAcf, IVARETcf, ISRcf, ItinerarioID, "
                                + "ConceptoSAT, UnidadSAT, VerComo,Contenedorc, UNegocioID, CargoID, AccesorioID, Referencia) "
                                + "VALUES('" + id + "', 2,'" + mnuevodoc.getValueAt(j, ndwo) + "', digits('" + mnuevodoc.getValueAt(j, ndcantidad) + "'),"
                                + " digits('" + mnuevodoc.getValueAt(j, ndvaloru) + "'), '',"
                                + "'" + mnuevodoc.getValueAt(j, nddesc).toString().replace("'", "\\'").replace("&", "and") + "', " + (Boolean) mnuevodoc.getValueAt(j, ndobjimp) + ",(SELECT digits('" + mnuevodoc.getValueAt(j, ndiva) + "')/100),"
                                + "(SELECT digits('" + mnuevodoc.getValueAt(j, ndivaret) + "')/100),(SELECT digits('" + mnuevodoc.getValueAt(j, ndisr) + "')/100), 0, "
                                + "'" + mnuevodoc.getValueAt(j, ndconsat) + "', '" + mnuevodoc.getValueAt(j, ndunisat) + "', '1', '" + mnuevodoc.getValueAt(j, ndcont) + "', "
                                + "'0', '" + idcargo + "', "
                                + "(SELECT CClienteID from cargosclientes_tbl where cargosclientes_tbl.CargoID = '" + idcargo + "'), REPLACE('" + mnuevodoc.getValueAt(j, ndref) + "','­','') )");
                        if (idcon.length() <= 11) {
                            //utils.dbUpdate("UPDATE workcontenedores_tbl SET StatusAdmin = 3, SeleccionFactura = false, UsuarioSeleccion = 0 where WContenedorID = '" + selwcontid.get(j) + "'");
                        }
                    }
                    guardarDocs(id);
                    if (boxTipo.getSelectedIndex() == 1) {
                        aplicarPago(id, !esinvoice);
                    }
                    if (freal) {
                        if (esinvoice) {
                            String response;
                            try {
                                response = utils.descargaMasivaInvoice(global.rectim + "Facturas", clientend, id, id, cEnvio.isSelected(), false, body);
                                if (!response.isEmpty()) {
                                    JOptionPane.showMessageDialog(this, response, "Error", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    utils.dbUpdate("UPDATE facturas_tbl SET cFCierre = true, FechaCierre = (now()) where FacturaID = '" + id + "' ");
//                                utils.openFile(global.rectim + "Facturas\\" + foliodoc + ".pdf");
                                }
                            } catch (IOException ex) {
                                utils.errorGenerado("Facturas / gendocs / ioex singular / " + ex.toString());
                            }
                        }

                        resetDoc();
                        this.dispose();
                    } else {
                        /*
                        if (idwcf.length() > 11) {
                JOptionPane.showMessageDialog(this, idwcf, "Error", JOptionPane.ERROR_MESSAGE);
            } 
                        */
                        
                        if (!freal) {
                            if (esinvoice) {
                               utils.generaInvoice(id,this);
                            } else {
                                utils.reporteRemision(id,this);
                            }
                            utils.dbUpdate("UPDATE cargosclientes_tbl SET Status = false where CargoID in (SELECT conceptosf_tbl.CargoID from conceptosf_tbl where FacturaID = '" + id + "')");
                        }
                    }
                } else {//if id error
                    this.dispose();
                    JOptionPane.showMessageDialog(this, "Error " + id, "Error", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                String msj = "";
                if (clientend.equals("0")) {
                    msj = "Favor de seleccionar un cliente del listado.\n";
                }
                JOptionPane.showMessageDialog(this, "Error\n" + msj, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            String msj = "";
            if (clientend.equals("0")) {
                msj = "Todos los conceptos deben de estar seleccionados con accesorio en la primera columna de cada concepto.\n";
            }
            JOptionPane.showMessageDialog(this, "Error\n" + msj, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void guardarDocs(String idfac) {
        for (int i = 0; i < macobros.getRowCount(); i++) {
            File file = new File(archivosfac.get(i));
            String idwcf = utils.dbInsert("INSERT INTO workcfiles_tbl (FacturaID, EvidenciaID, Nombre, Archivo, Extension, APath, UsuarioID) "
                    + "VALUES('" + idfac + "', '" + saveetiqueta.get(i) + "','" + file.getName() + "', '" + FileUtils.getFileNamePart(file) + "', '" + FileUtils.getFileExtPart(file.getAbsolutePath(), false) + "', '', '" + global.usuario + "')");
            if (idwcf.length() > 11) {
                JOptionPane.showMessageDialog(this, idwcf, "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                if (!FileUtils.copyFile(file, new File(global.carpetaevidencias + idwcf + FileUtils.getFileExtPart(file.getAbsolutePath(), true)))) {
                    utils.dbUpdate("UPDATE workcfiles_tbl SET Status = false where FileID = '" + idwcf + "'");
                    JOptionPane.showMessageDialog(this, "Error al copiar el archivo", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    utils.dbUpdate("UPDATE workcfiles_tbl SET APath = '" + utils.FiletoDB(global.carpetaevidencias + idwcf + FileUtils.getFileExtPart(file.getAbsolutePath(), true)) + "' where FileID = '" + idwcf + "'");
                }
            }
        }
    }

    private void aplicarPago(String facid, boolean factura) {
        String moneda = monedascuentas.get(boxCuenta.getSelectedIndex()), convert = "";
        String trans = utils.dbInsert("INSERT INTO transacciones_tbl(BancoID, Tipo, Monto, Fecha, Referencia, Categoria, ID, Concepto, EntSal, TipoCambioT, EmpresaID, Mexicana, Banco, NoCuenta, RFCBanco) "
                + "VALUES('" + cuentaid.get(boxCuenta.getSelectedIndex()) + "', '" + tipopagoid.get(boxFormaPago.getSelectedIndex()) + "', getTotalFacturas('" + facid + "'), now(),"
                + "'" + txtReferencia.getText() + "','1',(SELECT ClienteID from facturas_tbl where FacturaID = '" + facid + "'),'Cobro Facturas','2',(SELECT digits('" + txtTipoCambioND.getText() + "')), '" + empresaid + "', " + factura + ","
                + "'" + txtBancoP.getText().replace("'", "\\'") + "','" + txtNoCuentaP.getText().replace("'", "\\'") + "','" + txtRFCBancoP.getText().replace("'", "\\'") + "') ");
        if (trans.length() < 11 && !trans.isEmpty()) {
            String pagoid = "";
            if ((boxMonedaND.getSelectedIndex() + "").equals(moneda)) {
                String contadorparcial = utils.dbConsult("SELECT IFNULL(COUNT(PagoID)+1,1) from pagos_tbl where FacturaID = '" + facid + "' and TipoID = 1");
                pagoid = utils.dbInsert("INSERT INTO pagos_tbl (TransaccionID, FacturaID, Monto, Fecha,TipoID, CParcial) VALUES('" + trans + "','" + facid + "',getTotalFacturas('" + facid + "'),(now()),'1', '" + contadorparcial + "')");
            } else {
                if (monedascuentas.get(boxCuenta.getSelectedIndex()).equals("0")) {
                    convert = "*digits('" + txtTipoCambioND.getText() + "')";
                }
                if (monedascuentas.get(boxCuenta.getSelectedIndex()).equals("1")) {
                    convert = "/digits('" + txtTipoCambioND.getText() + "')";
                }
                String contadorparcial = utils.dbConsult("SELECT IFNULL(COUNT(PagoID)+1,1) from pagos_tbl where FacturaID = '" + facid + "' and TipoID = 1 and Statusp = true");
                pagoid = utils.dbInsert("INSERT INTO pagos_tbl (TransaccionID, FacturaID, Monto, Fecha,TipoID, CParcial) VALUES('" + trans + "','" + facid + "',(SELECT getTotalFacturas('" + facid + "')" + convert + "),(now()),'1', '" + contadorparcial + "')");
            }

            String restante = "0.00";
            if (restante.equals("0.00")) {
                utils.dbUpdate("UPDATE facturas_tbl SET Status = 2, FechaPago = CURDATE() where FacturaID = '" + facid + "'");
            }

        } else {
            JOptionPane.showMessageDialog(this, "Error " + trans, "Exito", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void cargarConceptosFactura() {
        String query = "SELECT CargoID, Nombre "
                + "from conceptoscargosclientes_tbl "
                + "where Status = true and MostrarFDirecta = true and Nombre like '%" + txtBusquedaC.getText() + "%' "
                + "order by Nombre ";
        mconfac.setRowCount(0);
        concargoid.clear();
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                concargoid.add(rs.getString("CargoID"));
                mconfac.addRow(new String[]{rs.getString("CargoID"), rs.getString("Nombre")});
            }
        } catch (SQLException e) {
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }

    }

    private void cargarWOs() {
        mwos.setRowCount(0);
        String query = "SELECT WContenedorID, "
                + "(SELECT RazonSocial from clientes_tbl left join workorder_tbl on clientes_tbl.ClienteID = workorder_tbl.ClienteID where WorkID = WorkOrderID) as cliente, "
                + "Contenedor, "
                + "CusRef as referencia "
                + "from workcontenedores_tbl "
                + "where Status = true and "
                + "(WContenedorID = '" + txtBusquedaWOs.getText() + "' or Contenedor like '%" + txtBusquedaWOs.getText() + "%' or CusRef like '%" + txtBusquedaWOs.getText() + "%') "
                + "order by cliente, Contenedor";
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {

                mwos.addRow(new String[]{rs.getString("cliente"), rs.getString("WContenedorID"), rs.getString("Contenedor"), rs.getString("referencia")});
            }

        } catch (SQLException e) {
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }

    }

    private void cargarClientesTabla() {
        String query = "SELECT ClienteID, RazonSocial, NComercial "
                + "from clientes_tbl "
                + "where Status = true and RazonSocial like '%" + txtBusquedaCliente.getText() + "%' and Inactivo = false "
                + "order by RazonSocial ";
        mclientes.setRowCount(0);
        clientestid.clear();
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                mclientes.addRow(new Object[]{rs.getString("RazonSocial"), rs.getString("NComercial")});
                clientestid.add(rs.getString("ClienteID"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }

    private void cargarDiasCredito(String cliid) {
        //int diascredito = Integer.parseInt(utils.dbConsult("SELECT DiasCredito from clientes_tbl where ClienteID = '" + cliid + "'"));
        int diascredito = Integer.parseInt(utils.dbConsult("SELECT IFNULL((SELECT DiasCredito from clientes_tbl where ClienteID = '" + cliid + "'),0)"));
        boxCondicionP.removeAllItems();
        boxCondicionP.addItem("CONTADO");
        for (int i = 0; i < diascredito; i++) {
            boxCondicionP.addItem("Credito " + (i + 1) + " dias");
        }
        boxCondicionP.setSelectedIndex(diascredito);
        if(global.nivel > 1){
            boxCondicionP.setEnabled(false);
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
            java.util.logging.Logger.getLogger(dNuevoDoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dNuevoDoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dNuevoDoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dNuevoDoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                dNuevoDoc dialog = new dNuevoDoc(new javax.swing.JFrame(), true, true, "0", "0");
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox<String> boxCondicionP;
    public javax.swing.JComboBox<String> boxCuenta;
    public javax.swing.JComboBox<String> boxFormaPago;
    public javax.swing.JComboBox<String> boxMonedaND;
    public javax.swing.JComboBox<String> boxTipo;
    public javax.swing.JComboBox<String> boxUsoCFDI;
    public javax.swing.JCheckBox cEnvio;
    private javax.swing.JDialog dArchivosf;
    private javax.swing.JDialog dClientes;
    private javax.swing.JDialog dConceptosF;
    private javax.swing.JDialog dTextoExtra;
    private javax.swing.JDialog dWO;
    public javax.swing.JButton factnew;
    public javax.swing.JButton jButton1;
    private javax.swing.JButton jButton15;
    public javax.swing.JButton jButton16;
    public javax.swing.JButton jButton2;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane27;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblBancoCliente;
    private javax.swing.JLabel lblCuenta;
    private javax.swing.JLabel lblCuentaCliente;
    private javax.swing.JLabel lblFormaPago;
    private javax.swing.JLabel lblFormaPago1;
    private javax.swing.JLabel lblRFCCliente;
    private javax.swing.JLabel lblReferencia;
    private javax.swing.JScrollPane scroll2;
    private javax.swing.JTable tClientes;
    private javax.swing.JTable tConceptosF;
    private javax.swing.JTable tDocs;
    private javax.swing.JTable tNuevoDoc;
    private javax.swing.JTable tWOs;
    private javax.swing.JTextField txtBancoP;
    private javax.swing.JTextField txtBusquedaC;
    private javax.swing.JTextField txtBusquedaCliente;
    private javax.swing.JTextField txtBusquedaWOs;
    public javax.swing.JTextField txtCliente;
    private javax.swing.JTextArea txtConExtra;
    private javax.swing.JTextField txtIsrnd;
    private javax.swing.JTextField txtIvand;
    private javax.swing.JTextField txtIvaretnd;
    private javax.swing.JTextField txtNoCuentaP;
    private javax.swing.JTextField txtNotaNuevoDoc;
    private javax.swing.JTextField txtRFCBancoP;
    private javax.swing.JTextField txtReferencia;
    private javax.swing.JTextField txtSubtotal;
    public javax.swing.JTextField txtTipoCambioND;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
