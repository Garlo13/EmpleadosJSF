/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empleadosjsf.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Adrián
 */
@Entity
@Table(name = "EMPLEADO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e")
    , @NamedQuery(name = "Empleado.findByEmpNo", query = "SELECT e FROM Empleado e WHERE e.empNo = :empNo")
    , @NamedQuery(name = "Empleado.findByNacimiento", query = "SELECT e FROM Empleado e WHERE e.nacimiento = :nacimiento")
    , @NamedQuery(name = "Empleado.findByNombre", query = "SELECT e FROM Empleado e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "Empleado.findByApellidos", query = "SELECT e FROM Empleado e WHERE e.apellidos = :apellidos")
    , @NamedQuery(name = "Empleado.findBySexo", query = "SELECT e FROM Empleado e WHERE e.sexo = :sexo")
    , @NamedQuery(name = "Empleado.findByFechaContrato", query = "SELECT e FROM Empleado e WHERE e.fechaContrato = :fechaContrato")})
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "EMP_NO")
    private Integer empNo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NACIMIENTO")
    @Temporal(TemporalType.DATE)
    private Date nacimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "APELLIDOS")
    private String apellidos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "SEXO")
    private String sexo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_CONTRATO")
    @Temporal(TemporalType.DATE)
    private Date fechaContrato;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleado")
    private Collection<DeptEmp> deptEmpCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleado")
    private Collection<DeptManager> deptManagerCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleado")
    private Collection<Sueldo> sueldoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleado")
    private Collection<Puesto> puestoCollection;

    public Empleado() {
    }

    public Empleado(Integer empNo) {
        this.empNo = empNo;
    }

    public Empleado(Integer empNo, Date nacimiento, String nombre, String apellidos, String sexo, Date fechaContrato) {
        this.empNo = empNo;
        this.nacimiento = nacimiento;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.fechaContrato = fechaContrato;
    }

    public Integer getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Integer empNo) {
        this.empNo = empNo;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getFechaContrato() {
        return fechaContrato;
    }

    public void setFechaContrato(Date fechaContrato) {
        this.fechaContrato = fechaContrato;
    }

    @XmlTransient
    public Collection<DeptEmp> getDeptEmpCollection() {
        return deptEmpCollection;
    }

    public void setDeptEmpCollection(Collection<DeptEmp> deptEmpCollection) {
        this.deptEmpCollection = deptEmpCollection;
    }

    @XmlTransient
    public Collection<DeptManager> getDeptManagerCollection() {
        return deptManagerCollection;
    }

    public void setDeptManagerCollection(Collection<DeptManager> deptManagerCollection) {
        this.deptManagerCollection = deptManagerCollection;
    }

    @XmlTransient
    public Collection<Sueldo> getSueldoCollection() {
        return sueldoCollection;
    }

    public void setSueldoCollection(Collection<Sueldo> sueldoCollection) {
        this.sueldoCollection = sueldoCollection;
    }

    @XmlTransient
    public Collection<Puesto> getPuestoCollection() {
        return puestoCollection;
    }

    public void setPuestoCollection(Collection<Puesto> puestoCollection) {
        this.puestoCollection = puestoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empNo != null ? empNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.empNo == null && other.empNo != null) || (this.empNo != null && !this.empNo.equals(other.empNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "empleadosjsf.entity.Empleado[ empNo=" + empNo + " ]";
    }
    
}
