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
@Table(name = "DEPT_MANAGER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DeptManager.findAll", query = "SELECT d FROM DeptManager d")
    , @NamedQuery(name = "DeptManager.findByDeptNo", query = "SELECT d FROM DeptManager d WHERE d.deptManagerPK.deptNo = :deptNo")
    , @NamedQuery(name = "DeptManager.findByEmpNo", query = "SELECT d FROM DeptManager d WHERE d.deptManagerPK.empNo = :empNo")
    , @NamedQuery(name = "DeptManager.findByFechaDesde", query = "SELECT d FROM DeptManager d WHERE d.fechaDesde = :fechaDesde")
    , @NamedQuery(name = "DeptManager.findByFechaHasta", query = "SELECT d FROM DeptManager d WHERE d.fechaHasta = :fechaHasta")})
public class DeptManager implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DeptManagerPK deptManagerPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_DESDE")
    @Temporal(TemporalType.DATE)
    private Date fechaDesde;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_HASTA")
    @Temporal(TemporalType.DATE)
    private Date fechaHasta;
    @JoinColumn(name = "DEPT_NO", referencedColumnName = "DEPT_NO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Departamento departamento;
    @JoinColumn(name = "EMP_NO", referencedColumnName = "EMP_NO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Empleado empleado;

    public DeptManager() {
    }

    public DeptManager(DeptManagerPK deptManagerPK) {
        this.deptManagerPK = deptManagerPK;
    }

    public DeptManager(DeptManagerPK deptManagerPK, Date fechaDesde, Date fechaHasta) {
        this.deptManagerPK = deptManagerPK;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
    }

    public DeptManager(String deptNo, int empNo) {
        this.deptManagerPK = new DeptManagerPK(deptNo, empNo);
    }

    public DeptManagerPK getDeptManagerPK() {
        return deptManagerPK;
    }

    public void setDeptManagerPK(DeptManagerPK deptManagerPK) {
        this.deptManagerPK = deptManagerPK;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
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
        hash += (deptManagerPK != null ? deptManagerPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DeptManager)) {
            return false;
        }
        DeptManager other = (DeptManager) object;
        if ((this.deptManagerPK == null && other.deptManagerPK != null) || (this.deptManagerPK != null && !this.deptManagerPK.equals(other.deptManagerPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "empleadosjsf.entity.DeptManager[ deptManagerPK=" + deptManagerPK + " ]";
    }
    
}
