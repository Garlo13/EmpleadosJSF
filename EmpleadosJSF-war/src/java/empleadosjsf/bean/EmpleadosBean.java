/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empleadosjsf.bean;

import empleadosjsf.ejb.DepartamentoFacade;
import empleadosjsf.ejb.DeptEmpFacade;
import empleadosjsf.ejb.DeptManagerFacade;
import empleadosjsf.ejb.EmpleadoFacade;
import empleadosjsf.ejb.PuestoFacade;
import empleadosjsf.ejb.SueldoFacade;
import empleadosjsf.entity.Departamento;
import empleadosjsf.entity.DeptEmp;
import empleadosjsf.entity.Empleado;
import empleadosjsf.entity.Puesto;
import empleadosjsf.entity.Sueldo;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author Adrián
 */
@Named(value = "empleadosBean")
@SessionScoped
public class EmpleadosBean implements Serializable {

    @EJB
    private SueldoFacade sueldoFacade;

    @EJB
    private PuestoFacade puestoFacade;

    @EJB
    private DeptManagerFacade deptManagerFacade;

    @EJB
    private DeptEmpFacade deptEmpFacade;

    @EJB
    private DepartamentoFacade departamentoFacade;
    
    @EJB
    private EmpleadoFacade empleadoFacade;
    
    
    
    
    /**
     * Creates a new instance of EmpleadosBean
     */
    public EmpleadosBean() {
    }
    
    @PostConstruct
    public void init(){
        this.listaEmpleados = this.empleadoFacade.findAll();
        this.listaDepartamentos = this.departamentoFacade.findAll();
    }
    
    private List<Empleado> listaEmpleados;
    private Empleado empleadoAEditar;
    private List<Departamento> listaDepartamentos;

    public List<Departamento> getListaDepartamentos() {
        return listaDepartamentos;
    }

    public void setListaDepartamentos(List<Departamento> listaDepartamentos) {
        this.listaDepartamentos = listaDepartamentos;
    }
    
    public Empleado getEmpleadoAEditar() {
        return empleadoAEditar;
    }

    public void setEmpleadoAEditar(Empleado empleadoAEditar) {
        this.empleadoAEditar = empleadoAEditar;
    }

    public List<Empleado> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(List<Empleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }
    
    public Departamento getDepartamentoEmpleado(Empleado empleado){
        List<DeptEmp> historialDepartamentos = new ArrayList(empleado.getDeptEmpCollection());
        historialDepartamentos.sort((depart1,depart2) -> depart1.getFechaHasta().compareTo(depart2.getFechaDesde()));
        Collections.reverse(historialDepartamentos);
        return historialDepartamentos.get(0).getDepartamento();
    }
    
    public List<DeptEmp> getHistorialDepartamentosEmpleadoAEditar(){
        List<DeptEmp> historialDepartamentos = new ArrayList(this.empleadoAEditar.getDeptEmpCollection());
        historialDepartamentos.sort((depart1,depart2) -> depart1.getFechaHasta().compareTo(depart2.getFechaDesde()));
        Collections.reverse(historialDepartamentos);
        return historialDepartamentos;
    }
    
    public Puesto getPuestoActualEmpleado(Empleado empleado){
        List<Puesto> historialPuestos = new ArrayList(empleado.getPuestoCollection());
        historialPuestos.sort((puesto1,puesto2) -> puesto1.getFechaHasta().compareTo(puesto2.getFechaHasta()));
        Collections.reverse(historialPuestos);
        return historialPuestos.get(0);
    }
    
    public List<Puesto> getHistorialPuestosEmpleadoAEditar(){
        List<Puesto> historialPuestos = new ArrayList(this.empleadoAEditar.getPuestoCollection());
        historialPuestos.sort((puesto1,puesto2) -> puesto1.getFechaHasta().compareTo(puesto2.getFechaHasta()));
        Collections.reverse(historialPuestos);
        return historialPuestos;
    }
    
    public List<Sueldo> getHistorialSueldosEmpleadoAEditar(){
        List<Sueldo> historialSueldos = new ArrayList(this.empleadoAEditar.getSueldoCollection());
        historialSueldos.sort((sueldo1,sueldo2) -> sueldo1.getFechaHasta().compareTo(sueldo2.getFechaHasta()));
        Collections.reverse(historialSueldos);
        return historialSueldos;
    }
    
    public Sueldo getSueldoActualEmpleado(Empleado empleado){
        List<Sueldo> historialSueldos = new ArrayList(empleado.getSueldoCollection());
        historialSueldos.sort((sueldo1,sueldo2) -> sueldo1.getFechaHasta().compareTo(sueldo2.getFechaHasta()));
        Collections.reverse(historialSueldos);
        return historialSueldos.get(0);
    }
    
    public String doBorrar(Empleado empleado){
        int numeroEmpleado = empleado.getEmpNo();
        this.deptEmpFacade.elminarHistorialUsuario(numeroEmpleado);
        this.deptManagerFacade.elminarHistorialUsuario(numeroEmpleado);
        this.puestoFacade.elminarHistorialUsuario(numeroEmpleado);
        this.sueldoFacade.elminarHistorialUsuario(numeroEmpleado);
        this.empleadoFacade.remove(empleado);
        this.init();
        return "listadoempleados";
    }
    
}
