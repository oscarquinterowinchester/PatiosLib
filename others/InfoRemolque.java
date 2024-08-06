/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package others;
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
public class InfoRemolque {

    private String workid;
    private String contenedor;
    private String cajaid;
    private String caja;
    private String placascaja;
    private String dollyid;
    private String dollyex;
    private String generadorid;
    private String generador;
    private String sello;
    private String rutaid;
    private String temperatura;
    private int estado;
    private int vuelta;
    private String fingreso;
    private String guiasalida;
    private String guiaretorno;
    private int tipocont;
    private String medida;
    private String pick;
    private String pick2;
    private String nota;
    private String BL;
    private String peso;
    private String diesel;
    private String booking;
    private boolean enfalso;
    
    public InfoRemolque(String work, String cont,String intcaja,String strcaja, String placascaja,String dolly, String dollyexterno,String generadorid,String generador,String sello,String ruta,String temp, int estado,int vuelta,String ingreso,String guias,String guiar,int tipoc, String medida, String pick1, String pick2, String nota, String BL, String peso,String diesel, String booking, boolean enfalso) {
        
        this.workid = work;
        this.contenedor = cont;
        this.cajaid = intcaja;
        this.caja = strcaja;
        this.placascaja = placascaja;
        this.dollyid = dolly;
        this.dollyex = dollyexterno;
        this.generadorid = generadorid;
        this.generador = generador;
        this.sello = sello;
        this.rutaid = ruta;
        this.temperatura = temp;
        this.estado = estado;
        this.vuelta = vuelta;
        this.fingreso = ingreso;
        this.guiasalida = guias;
        this.guiaretorno = guiar;
        this.tipocont = tipoc;
        this.medida = medida;
        this.pick = pick1;
        this.pick2 = pick2;
        this.nota = nota;
        this.diesel = diesel;
        this.BL = BL;
        this.peso = peso;
        this.booking = booking;
        this.enfalso = enfalso;
    }
    
    public String getWork() {
        return workid;
    }

    public void setWork(String work) {
        this.workid = work;
    }

    public String getContenedor() {
        return contenedor;
    }

    public void setContenedor(String cont) {
        this.contenedor = cont;
    }
    
    public String getCajaID() {
        
        return cajaid;
    }

    public void setCajaID(String cid) {
        this.cajaid = cid;
    }
    
    public String getChasis(){
        if(cajaid.equals("0")){
            return caja;
        }else{
            return utils.dbConsult("SELECT NoEconomico from cajas_tbl where CajaID = '"+cajaid+"'");
        }
    }
    
    public String getPlacasCaja(){
        return placascaja;
    }
    
    public void setPlacasCaja(String pcaja){
        this.placascaja = pcaja;
    }
    
    public String getDolly(){
        if(dollyid.equals("0")){
            return dollyex;
        }else{
            return utils.dbConsult("SELECT NoEconomico from dollys_tbl where DollyID = '" + dollyid + "' ");
        }
    }
    
    public String getCaja() {
        return caja;
    }

    public void setCaja(String caja) {
        this.caja = caja;
    }

    public String getDollyID() {
        return dollyid;
    }

    public void setDollyID(String dollyex) {
        this.dollyid = dollyex;
    }
    
    public String getDollyEx() {
        return dollyex;
    }

    public void setDollyEx(String dollyex) {
        this.dollyex = dollyex;
    }
    
    public String getGeneradorID() {
        return generadorid;
    }

    public void setGeneradorID(String generadorid) {
        this.generadorid = generadorid;
    }
    
    public String getGenerador() {
        return generador;
    }

    public void setGenerador(String generador) {
        this.generador = generador;
    }

    public String getSello() {
        return sello;
    }

    public void setSello(String sell) {
        this.sello = sell;
    }
    
    public String getRutaID() {
        return rutaid;
    }

    public void setRutaID(String ruta) {
        this.rutaid = ruta;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temp) {
        this.temperatura = temp;
    }
    
    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    public int getVuelta() {
        return vuelta;
    }

    public void setVuelta(int vuelta) {
        this.vuelta = vuelta;
    }
    
    public String getFIngreso() {
        return fingreso;
    }

    public void setFIngreso(String ingreso) {
        this.fingreso = ingreso;
    }
    
    public String getGuiaSalida() {
        return guiasalida;
    }

    public void setGuiaSalida(String guias) {
        this.guiasalida = guias;
    }
    
    public String getGuiaRetorno() {
        return guiaretorno;
    }

    public void setGuiaRetorno(String guiar) {
        this.guiaretorno = guiar;
    }
    
    public int getTipoCont() {
        return tipocont;
    }

    public void setTipoCont(int tipoc) {
        this.tipocont = tipoc;
    }
    
    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }
    
    public String getPick() {
        return pick;
    }

    public void setPick(String pick) {
        this.pick = pick;
    }
    
    public String getPick2() {
        return pick2;
    }

    public void setPick2(String pick2) {
        this.pick2 = pick2;
    }
    
    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getDiesel() {
        return diesel;
    }

    public void setDiesel(String diesel) {
        this.diesel = diesel;
    }
    
    public String getBL() {
        return BL;
    }

    public void setBL(String BL) {
        this.BL = BL;
    }
    
    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }
    public String getBooking(){
        return booking;
    }
    public void setBooking(String booking){
        this.booking = booking;
    }
    
    public boolean getEnFalso(){
        return enfalso;
    }
    public void setEnFalso(boolean enfalso){
        this.enfalso = enfalso;
    }

}
