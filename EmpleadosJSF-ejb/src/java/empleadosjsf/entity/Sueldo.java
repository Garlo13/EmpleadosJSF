/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empleadosjsf.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Adrián
 */
@Entity
@Table(name = "SUELDO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sueldo.findAll", query = "SELECT s FROM Sueldo s")
    , @NamedQuery(name = "Sueldo.findByEmpNo", query = "SELECT s FROM Sueldo s WHERE s.sueldoPK.empNo = :empNo")
    , @NamedQuery(name = "Sueldo.findByCantidad", query = "SELECT s FROM Sueldo s WHERE s.cantidad = :cantidad")
    , @NamedQuery(name = "Sueldo.findByFechaDesde", query = "SELECT s FROM Sueldo s WHERE s.sueldoPK.fechaDesde = :fechaDesde")
    , @NamedQuery(name = "Sueldo.findByFechaHasta", query = "SELECT s FROM Sueldo s WHERE s.fechaHasta = :fechaHasta")})
public class Sueldo implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SueldoPK sueldoPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD")
    private int cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_HASTA")
    @Temporal(TemporalType.DATE)
    private Date fechaHasta;
    @JoinColumn(name = "EMP_NO", referencedColumnName = "EMP_NO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Empleado empleado;

    public Sueldo() {
    }

    public Sueldo(SueldoPK sueldoPK) {
        this.sueldoPK = sueldoPK;
    }

    public Sueldo(SueldoPK sueldoPK, int cantidad, Date fechaHasta) {
        this.sueldoPK = sueldoPK;
        this.cantidad = cantidad;
        this.fechaHasta = fechaHasta;
    }

    public Sueldo(int empNo, Date fechaDesde) {
        this.sueldoPK = new SueldoPK(empNo, fechaDesde);
    }

    public SueldoPK getSueldoPK() {
        return sueldoPK;
    }

    public void setSueldoPK(SueldoPK sueldoPK) {
        this.sueldoPK = sueldoPK;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sueldoPK != null ? sueldoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sueldo)) {
            return false;
        }
        Sueldo other = (Sueldo) object;
        if ((this.sueldoPK == null && other.sueldoPK != null) || (this.sueldoPK != null && !this.sueldoPK.equals(other.sueldoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "empleadosjsf.entity.Sueldo[ sueldoPK=" + sueldoPK + " ]";
    }
    
}
