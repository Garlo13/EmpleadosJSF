/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empleadosjsf.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Adrián
 */
@Entity
@Table(name = "PUESTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Puesto.findAll", query = "SELECT p FROM Puesto p")
    , @NamedQuery(name = "Puesto.findByEmpNo", query = "SELECT p FROM Puesto p WHERE p.puestoPK.empNo = :empNo")
    , @NamedQuery(name = "Puesto.findByDenominacion", query = "SELECT p FROM Puesto p WHERE p.puestoPK.denominacion = :denominacion")
    , @NamedQuery(name = "Puesto.findByFechaDesde", query = "SELECT p FROM Puesto p WHERE p.puestoPK.fechaDesde = :fechaDesde")
    , @NamedQuery(name = "Puesto.findByFechaHasta", query = "SELECT p FROM Puesto p WHERE p.fechaHasta = :fechaHasta")})
public class Puesto implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PuestoPK puestoPK;
    @Column(name = "FECHA_HASTA")
    @Temporal(TemporalType.DATE)
    private Date fechaHasta;
    @JoinColumn(name = "EMP_NO", referencedColumnName = "EMP_NO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Empleado empleado;

    public Puesto() {
    }

    public Puesto(PuestoPK puestoPK) {
        this.puestoPK = puestoPK;
    }

    public Puesto(int empNo, String denominacion, Date fechaDesde) {
        this.puestoPK = new PuestoPK(empNo, denominacion, fechaDesde);
    }

    public PuestoPK getPuestoPK() {
        return puestoPK;
    }

    public void setPuestoPK(PuestoPK puestoPK) {
        this.puestoPK = puestoPK;
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
        hash += (puestoPK != null ? puestoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Puesto)) {
            return false;
        }
        Puesto other = (Puesto) object;
        if ((this.puestoPK == null && other.puestoPK != null) || (this.puestoPK != null && !this.puestoPK.equals(other.puestoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "empleadosjsf.entity.Puesto[ puestoPK=" + puestoPK + " ]";
    }
    
}
