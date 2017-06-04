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
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Adrián
 */
@Embeddable
public class PuestoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "EMP_NO")
    private int empNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DENOMINACION")
    private String denominacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_DESDE")
    @Temporal(TemporalType.DATE)
    private Date fechaDesde;

    public PuestoPK() {
    }

    public PuestoPK(int empNo, String denominacion, Date fechaDesde) {
        this.empNo = empNo;
        this.denominacion = denominacion;
        this.fechaDesde = fechaDesde;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) empNo;
        hash += (denominacion != null ? denominacion.hashCode() : 0);
        hash += (fechaDesde != null ? fechaDesde.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PuestoPK)) {
            return false;
        }
        PuestoPK other = (PuestoPK) object;
        if (this.empNo != other.empNo) {
            return false;
        }
        if ((this.denominacion == null && other.denominacion != null) || (this.denominacion != null && !this.denominacion.equals(other.denominacion))) {
            return false;
        }
        if ((this.fechaDesde == null && other.fechaDesde != null) || (this.fechaDesde != null && !this.fechaDesde.equals(other.fechaDesde))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "empleadosjsf.entity.PuestoPK[ empNo=" + empNo + ", denominacion=" + denominacion + ", fechaDesde=" + fechaDesde + " ]";
    }
    
}
