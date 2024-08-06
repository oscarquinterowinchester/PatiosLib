
package modulespat;
import basic.*;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class Transbordos extends javax.swing.JFrame {
    DefaultTableModel mOrigenCarga;
    DefaultTableModel mDestinoCarga;
    DefaultTableModel mTransbordos;
    
    ArrayList<String> cajasid = new ArrayList<>();
    ArrayList<String> inventarioID = new ArrayList<>();
    ArrayList<String> patiosid = new ArrayList<>();
    ArrayList<String> origenid =new ArrayList<>();
    ArrayList<String> destinoid = new ArrayList<>();
    
    //Columnas tOrigenCarga
    int t1inventario=0, t1cont=1, t1camion=2, t1chofer=3, t1cliente=4, t1fecha=5;
    //Columnas tDestinoCarga
    int t2inventario=0, t2cont=1, t2camion=2, t2chofer=3, t2cliente=4, t2fecha=5;
    //Columnas tTransbordos
    int t3notransb=0, t3origen=1, t3contO=2,t3CamionO=3, t3ChoferO=4,t3ClienteO=5, t3destino=6,t3contD=7, t3CamionD=8, t3ChoferD=9,t3ClienteD=10, t3fecha=11;

    public Transbordos() {
        try{
            initComponents();
            Config.setDatePicker(txtInicioC, 14);
            Config.setDatePicker(txtFinalC, 14);
            
           mOrigenCarga = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {

                    return false;
                }
            };
           mDestinoCarga = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {

                    return false;
                }
            };
            mTransbordos = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {

                    return false;
                }
            };
               txtInicioC.addDateChangeListener(new DateChangeListener() {
                @Override
                public void dateChanged(DateChangeEvent event) {
                    cargarTablaOrigen();
                    cargarTablaDestino();
                }
            });
            txtFinalC.addDateChangeListener(new DateChangeListener() {
                @Override
                public void dateChanged(DateChangeEvent event) {
                     cargarTablaOrigen();
                    cargarTablaDestino();
                }
            });    
           
           
           mOrigenCarga.addColumn("Inventario");
           mOrigenCarga.addColumn("Contenedor");
           mOrigenCarga.addColumn("Camion");
           mOrigenCarga.addColumn("Chofer");
           mOrigenCarga.addColumn("Cliente");
           mOrigenCarga.addColumn("Fecha");
           tOrigenCarga.setModel(mOrigenCarga);
           Config.setTable(tOrigenCarga);
           
           mDestinoCarga.addColumn("Inventario");
           mDestinoCarga.addColumn("Contenedor");
           mDestinoCarga.addColumn("Camion");
           mDestinoCarga.addColumn("Chofer");
           mDestinoCarga.addColumn("Cliente");
           mDestinoCarga.addColumn("Fecha");
           tDestinoCarga.setModel(mDestinoCarga);
           Config.setTable(tDestinoCarga);
           
           mTransbordos.addColumn("No. Transbordo");
           mTransbordos.addColumn("Origen");
           mTransbordos.addColumn("Contenedor");
           mTransbordos.addColumn("Camion");
           mTransbordos.addColumn("Chofer");
           mTransbordos.addColumn("Cliente");
           mTransbordos.addColumn("Destino");
           mTransbordos.addColumn("Contenedor");
           mTransbordos.addColumn("Camion");
           mTransbordos.addColumn("Chofer");
           mTransbordos.addColumn("Cliente");
           mTransbordos.addColumn("Fecha");
           tTransbordos.setModel(mTransbordos);
           Config.setTable(tTransbordos);
           
           FillCombo.cargarPatios(boxPatios, patiosid, "Todos");
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dTransbordos = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tTransbordos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txtInicioC = new com.github.lgooddatepicker.components.DatePicker();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txtFinalC = new com.github.lgooddatepicker.components.DatePicker();
        jLabel3 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        cAplicarFecha = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tOrigenCarga = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tDestinoCarga = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        pYardas = new javax.swing.JPanel();
        boxPatios = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        btnHistorial = new javax.swing.JButton();
        btnTransborde = new javax.swing.JButton();

        dTransbordos.setBackground(new java.awt.Color(255, 255, 255));
        dTransbordos.setMinimumSize(new java.awt.Dimension(1280, 720));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5));
        jPanel1.setLayout(new java.awt.BorderLayout());

        tTransbordos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tTransbordos);

        jPanel1.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        dTransbordos.getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtros"));
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.BorderLayout());
        jPanel3.add(txtInicioC, java.awt.BorderLayout.CENTER);

        jLabel2.setText("Desde");
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel3.add(jLabel2, java.awt.BorderLayout.PAGE_START);

        jPanel2.add(jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new java.awt.BorderLayout());
        jPanel4.add(txtFinalC, java.awt.BorderLayout.CENTER);

        jLabel3.setText("Hasta");
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel4.add(jLabel3, java.awt.BorderLayout.PAGE_START);

        jPanel2.add(jPanel4);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new java.awt.BorderLayout());

        cAplicarFecha.setText("Aplicar Fecha");
        cAplicarFecha.setContentAreaFilled(false);
        cAplicarFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cAplicarFechaActionPerformed(evt);
            }
        });
        jPanel9.add(cAplicarFecha, java.awt.BorderLayout.CENTER);

        jLabel4.setText("                                    ");
        jPanel9.add(jLabel4, java.awt.BorderLayout.PAGE_START);

        jPanel2.add(jPanel9);

        dTransbordos.getContentPane().add(jPanel2, java.awt.BorderLayout.NORTH);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(1300, 683));
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Origen"));
        jPanel6.setMinimumSize(new java.awt.Dimension(601, 402));
        jPanel6.setLayout(new java.awt.BorderLayout());

        tOrigenCarga.setModel(new javax.swing.table.DefaultTableModel(
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
        tOrigenCarga.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tOrigenCarga);

        jPanel6.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Destino"));
        jPanel7.setMinimumSize(new java.awt.Dimension(601, 525));
        jPanel7.setLayout(new java.awt.BorderLayout());

        tDestinoCarga.setModel(new javax.swing.table.DefaultTableModel(
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
        tDestinoCarga.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(tDestinoCarga);

        jPanel7.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtro"));
        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        pYardas.setBackground(new java.awt.Color(255, 255, 255));
        pYardas.setMinimumSize(new java.awt.Dimension(64, 50));
        pYardas.setPreferredSize(new java.awt.Dimension(200, 40));
        pYardas.setLayout(new java.awt.BorderLayout());

        boxPatios.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        boxPatios.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        boxPatios.setMinimumSize(new java.awt.Dimension(64, 40));
        boxPatios.setPreferredSize(new java.awt.Dimension(64, 40));
        boxPatios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxPatiosActionPerformed(evt);
            }
        });
        pYardas.add(boxPatios, java.awt.BorderLayout.CENTER);

        jLabel1.setText("Yardas");
        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        pYardas.add(jLabel1, java.awt.BorderLayout.PAGE_START);

        jPanel8.add(pYardas);

        btnHistorial.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnHistorial.setText("Historial transbordos");
        btnHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistorialActionPerformed(evt);
            }
        });
        jPanel8.add(btnHistorial);

        btnTransborde.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnTransborde.setText("Realizar transbordo ");
        btnTransborde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransbordeActionPerformed(evt);
            }
        });
        jPanel8.add(btnTransborde);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(jPanel5, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        // TODO add your handling code here:
        if (this.isEnabled()) {
            cargarTablaOrigen();
            cargarTablaDestino();
        }
    }//GEN-LAST:event_formWindowGainedFocus

    private void boxPatiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxPatiosActionPerformed
        // TODO add your handling code here:
         if (boxPatios.getSelectedIndex() >= 0) {
            cargarTablaOrigen();
            cargarTablaDestino();
        }
    }//GEN-LAST:event_boxPatiosActionPerformed

    private void btnTransbordeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransbordeActionPerformed
        // TODO add your handling code here:
        int rowO= tOrigenCarga.getSelectedRow();
        int rowD= tDestinoCarga.getSelectedRow();
       if(rowO >=0 && rowD >=0){
           int option = JOptionPane.showConfirmDialog(this, 
                "Se va a transbordar la carga del contenedor " + tOrigenCarga.getValueAt(rowO, t1cont) + " al contenedor " + tDestinoCarga.getValueAt(rowD, t2cont)
                + "\nDesea continuar?", "Confirmacion", JOptionPane.YES_NO_OPTION);
           if(option == 0){
               transbordarCarga(tOrigenCarga.getValueAt(rowO, t1inventario).toString());
               transbordarCarga(tDestinoCarga.getValueAt(rowD, t2inventario).toString());
              
              utils.dbInsert("INSERT INTO transbordos_tbl (transbordoid, origen, destino, fecha) VALUES(0,'" + tOrigenCarga.getValueAt(rowO, t1inventario) + "','" + tDestinoCarga.getValueAt(rowD, t2inventario) + "', NOW())");
              cargarTablaOrigen();
              cargarTablaDestino();

           }
       }else{
           JOptionPane.showMessageDialog(this, "Selecione un origen y un destino para la carga", "Error", JOptionPane.ERROR_MESSAGE);
       }
    }//GEN-LAST:event_btnTransbordeActionPerformed

    private void btnHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistorialActionPerformed
        // TODO add your handling code here:
        cargarTransbordos();
        dTransbordos.setLocationRelativeTo(this);
        dTransbordos.setVisible(true);
    }//GEN-LAST:event_btnHistorialActionPerformed

    private void cAplicarFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cAplicarFechaActionPerformed
        cargarTransbordos();
    }//GEN-LAST:event_cAplicarFechaActionPerformed

    private void cargarTablaOrigen() {
        int opcion= boxPatios.getSelectedIndex();
        String filtropatio ="";
        
        if(opcion != 0){
            filtropatio = " and PatioID = '" + opcion + "' ";
        }
        
        String query="SELECT i.inventarioID, i.contenedor, c.Nombre as chofer, cl.NComercial as cliente, ca.NoEconomico as camion, i.fechaevento, i.estadocarga " +
                    "FROM inventarioexterno_tbl AS i " +
                    "LEFT JOIN choferes_tbl AS c ON i.ChoferID = c.ChoferID " +
                    "LEFT JOIN clientes_tbl AS cl ON i.ClienteID = cl.ClienteID " +
                    "LEFT JOIN camiones_tbl AS ca ON i.CamionID = ca.CamionID " +
                    "WHERE IFNULL((SELECT x.inventarioID FROM inventarioexterno_tbl AS x WHERE x.anteriorID = i.InventarioID AND x.TipoEvento = 2 LIMIT 1), 0) = 0 AND i.tipoevento = 1 AND i.estadocarga = 1 " + filtropatio;
         Object[] temp = new Object[mOrigenCarga.getColumnCount()];
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        
        mOrigenCarga.setRowCount(0);
        patiosid.clear();
        origenid.clear();
        
        try{
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                //patiosid.add(rs.getString("patio"));
                origenid.add(rs.getString("contenedor"));
                temp[t1inventario] = rs.getString("inventarioID");
                temp[t1cont] = rs.getString("contenedor");
                temp[t1camion] = rs.getString("camion");
                temp[t1chofer] = rs.getString("chofer");
                temp[t1cliente] = rs.getString("cliente");
                temp[t1fecha] = rs.getString("fechaevento");
                
                mOrigenCarga.addRow(temp);
            
            }
         } catch (SQLException e) {
            System.out.println("e = " + e);
            utils.errorGenerado("Inventario patios / cargarTablaOrigen / sqlex " + e.toString());
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
        
    }
        private void cargarTablaDestino() {
        int opcion= boxPatios.getSelectedIndex();
        String filtropatio ="";
        
        if(opcion != 0){
            filtropatio = " and PatioID = '" + opcion + "' ";
        }
        
 String query="SELECT i.inventarioID, i.contenedor, i.CamionID, c.Nombre as chofer, cl.NComercial as cliente, ca.NoEconomico as camion, i.fechaevento, i.estadocarga " +
                    "FROM inventarioexterno_tbl AS i " +
                    "LEFT JOIN choferes_tbl AS c ON i.ChoferID = c.ChoferID " +
                    "LEFT JOIN clientes_tbl AS cl ON i.ClienteID = cl.ClienteID " +
                    "LEFT JOIN camiones_tbl AS ca ON i.CamionID = ca.CamionID " +
                    "WHERE IFNULL((SELECT x.inventarioID FROM inventarioexterno_tbl AS x WHERE x.anteriorID = i.InventarioID AND x.TipoEvento = 2 LIMIT 1), 0) = 0 AND i.tipoevento = 1 AND i.estadocarga = 0 " + filtropatio;
         Object[] temp = new Object[mDestinoCarga.getColumnCount()];
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        
        mDestinoCarga.setRowCount(0);
        patiosid.clear();
        origenid.clear();
        
        try{
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                //patiosid.add(rs.getString("patio"));
                destinoid.add(rs.getString("contenedor"));
                temp[t2inventario] = rs.getString("inventarioID");
                temp[t2cont] = rs.getString("contenedor");
                temp[t2camion] = rs.getString("camion");
                temp[t2chofer] = rs.getString("chofer");
                temp[t2cliente] = rs.getString("cliente");
                temp[t2fecha] = rs.getString("fechaevento");
                
                mDestinoCarga.addRow(temp);
            
            }
         } catch (SQLException e) {
            System.out.println("e = " + e);
            utils.errorGenerado("Inventario patios / cargarTablaDestino / sqlex " + e.toString());
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
        
    }
    private void transbordarCarga(String invid){
        //String query= "UPDATE inventarioexterno_tbl SET EstadoCarga=0 WHERE inventarioID= '" + origen+ "';";
        //String query2= "UPDATE inventarioexterno_tbl SET EstadoCarga=0 WHERE inventarioID= '"+ destino + "';";
        String query = "SELECT inventarioID, contenedor, fechaevento,estadocarga FROM inventarioexterno_tbl\n" +
                "WHERE IFNULL((SELECT i.inventarioID FROM inventarioexterno_tbl AS i WHERE i.anteriorID=inventarioexterno_tbl.InventarioID AND i.TipoEvento=2 LIMIT 1),0)=0\n" +
                "AND tipoevento=1 AND InventarioID= '" + invid +"';";
         Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try{
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            
            while(rs.next()){
                if(rs.getInt("estadocarga")==1){
                    System.out.println("Es origen" + rs.getString("contenedor"));
                      utils.dbUpdate("UPDATE inventarioexterno_tbl SET EstadoCarga=0 WHERE inventarioID= '" + invid + "';");
                }
                if(rs.getInt("estadocarga")==0){
                    System.out.println("Es destino" + rs.getString("contenedor"));
                    utils.dbUpdate("UPDATE inventarioexterno_tbl SET EstadoCarga=1 WHERE inventarioID= '" + invid + "';");
                } 
            }
        } catch (SQLException e) {
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }
    private void cargarTransbordos(){
        String filtrofecha="";
        if (cAplicarFecha.isSelected()) {
        filtrofecha= " WHERE fecha >= '" +  (utils.dateFromFieldtoDBwoH(txtInicioC.getText())+ " 00:00:00" )+ "' and fecha <= '"+ (utils.dateFromFieldtoDBwoH(txtFinalC.getText())+ " 23:59:59")+"'";
        }
        String query= "SELECT t.transbordoid, t.origen, " +
"    (Select Contenedor from inventarioexterno_tbl where InventarioID= t.origen) as contO, " +
"    (SELECT NoEconomico FROM camiones_tbl WHERE CamionID = (SELECT camionID FROM inventarioexterno_tbl WHERE InventarioID = t.origen)) AS CamionO, " +
"    (SELECT Nombre FROM choferes_tbl WHERE ChoferID = (SELECT ChoferID FROM inventarioexterno_tbl WHERE InventarioID = t.origen)) AS ChoferO, " +
"    (SELECT NComercial FROM clientes_tbl WHERE ClienteID = (SELECT ClienteID FROM inventarioexterno_tbl WHERE InventarioID = t.origen)) AS ClienteO, " +
"    t.destino, " +
"    (SELECT contenedor FROM inventarioexterno_tbl WHERE InventarioID= t.destino) as contD, " +
"    (SELECT NoEconomico FROM camiones_tbl WHERE CamionID = (SELECT camionID FROM inventarioexterno_tbl WHERE InventarioID = t.destino)) AS CamionD, " +
"    (SELECT Nombre FROM choferes_tbl WHERE ChoferID = (SELECT ChoferID FROM inventarioexterno_tbl WHERE InventarioID = t.destino)) AS ChoferD, " +
"    (SELECT NComercial FROM clientes_tbl WHERE ClienteID = (SELECT ClienteID FROM inventarioexterno_tbl WHERE InventarioID = t.destino)) AS ClienteD, " +
"    t.fecha FROM transbordos_tbl AS t " + filtrofecha + ";";
         Object[] temp = new Object[mTransbordos.getColumnCount()];
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        
        mTransbordos.setRowCount(0);
           try{
            con = utils.startConnection();
            state = con.createStatement();
            rs = state.executeQuery(query);
            while (rs.next()) {
                temp[t3notransb] = rs.getString("transbordoid");
                temp[t3origen] = rs.getString("origen");
                temp[t3contO] = rs.getString("contO");
                temp[t3CamionO] = rs.getString("CamionO");
                temp[t3ChoferO] = rs.getString("ChoferO");
                temp[t3ClienteO] = rs.getString("ClienteO");
                temp[t3destino] = rs.getString("destino");
                temp[t3contD] = rs.getString("contD");
                temp[t3CamionD] = rs.getString("CamionD");
                temp[t3ChoferD] = rs.getString("ChoferD");
                temp[t3ClienteD] = rs.getString("ClienteD");
                temp[t3fecha]= rs.getString("fecha");
                
                mTransbordos.addRow(temp);
            
            }
         } catch (SQLException e) {
            System.out.println("e = " + e);
            utils.errorGenerado("Inventario patios / Transbordos/ cargarTransbordos / sqlex " + e.toString());
        } finally {
            utils.closeAllConnections(con, state, rs);
        }
    }
    
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Transbordos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Transbordos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Transbordos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Transbordos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Transbordos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxPatios;
    private javax.swing.JButton btnHistorial;
    private javax.swing.JButton btnTransborde;
    private javax.swing.JCheckBox cAplicarFecha;
    private javax.swing.JDialog dTransbordos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel pYardas;
    private javax.swing.JTable tDestinoCarga;
    private javax.swing.JTable tOrigenCarga;
    private javax.swing.JTable tTransbordos;
    private com.github.lgooddatepicker.components.DatePicker txtFinalC;
    private com.github.lgooddatepicker.components.DatePicker txtInicioC;
    // End of variables declaration//GEN-END:variables
}
