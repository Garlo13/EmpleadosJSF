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
@Table(name = "DEPT_EMP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DeptEmp.findAll", query = "SELECT d FROM DeptEmp d")
    , @NamedQuery(name = "DeptEmp.findByEmpNo", query = "SELECT d FROM DeptEmp d WHERE d.deptEmpPK.empNo = :empNo")
    , @NamedQuery(name = "DeptEmp.findByDeptNo", query = "SELECT d FROM DeptEmp d WHERE d.deptEmpPK.deptNo = :deptNo")
    , @NamedQuery(name = "DeptEmp.findByFechaDesde", query = "SELECT d FROM DeptEmp d WHERE d.fechaDesde = :fechaDesde")
    , @NamedQuery(name = "DeptEmp.findByFechaHasta", query = "SELECT d FROM DeptEmp d WHERE d.fechaHasta = :fechaHasta")})
public class DeptEmp implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DeptEmpPK deptEmpPK;
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

    public DeptEmp() {
    }

    public DeptEmp(DeptEmpPK deptEmpPK) {
        this.deptEmpPK = deptEmpPK;
    }

    public DeptEmp(DeptEmpPK deptEmpPK, Date fechaDesde, Date fechaHasta) {
        this.deptEmpPK = deptEmpPK;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
    }

    public DeptEmp(int empNo, String deptNo) {
        this.deptEmpPK = new DeptEmpPK(empNo, deptNo);
    }

    public DeptEmpPK getDeptEmpPK() {
        return deptEmpPK;
    }

    public void setDeptEmpPK(DeptEmpPK deptEmpPK) {
        this.deptEmpPK = deptEmpPK;
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
        hash += (deptEmpPK != null ? deptEmpPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DeptEmp)) {
            return false;
        }
        DeptEmp other = (DeptEmp) object;
        if ((this.deptEmpPK == null && other.deptEmpPK != null) || (this.deptEmpPK != null && !this.deptEmpPK.equals(other.deptEmpPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "empleadosjsf.entity.DeptEmp[ deptEmpPK=" + deptEmpPK + " ]";
    }
    
}
