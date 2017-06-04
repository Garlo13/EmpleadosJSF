/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empleadosjsf.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Adrián
 */
@Entity
@Table(name = "DEPARTAMENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Departamento.findAll", query = "SELECT d FROM Departamento d")
    , @NamedQuery(name = "Departamento.findByDeptNo", query = "SELECT d FROM Departamento d WHERE d.deptNo = :deptNo")
    , @NamedQuery(name = "Departamento.findByNombre", query = "SELECT d FROM Departamento d WHERE d.nombre = :nombre")})
public class Departamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "DEPT_NO")
    private String deptNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departamento")
    private Collection<DeptEmp> deptEmpCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departamento")
    private Collection<DeptManager> deptManagerCollection;

    public Departamento() {
    }

    public Departamento(String deptNo) {
        this.deptNo = deptNo;
    }

    public Departamento(String deptNo, String nombre) {
        this.deptNo = deptNo;
        this.nombre = nombre;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (deptNo != null ? deptNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departamento)) {
            return false;
        }
        Departamento other = (Departamento) object;
        if ((this.deptNo == null && other.deptNo != null) || (this.deptNo != null && !this.deptNo.equals(other.deptNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "empleadosjsf.entity.Departamento[ deptNo=" + deptNo + " ]";
    }
    
}
