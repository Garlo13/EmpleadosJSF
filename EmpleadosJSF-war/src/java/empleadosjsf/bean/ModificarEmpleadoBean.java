/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empleadosjsf.bean;

import empleadosjsf.ejb.DepartamentoFacade;
import empleadosjsf.ejb.DeptEmpFacade;
import empleadosjsf.ejb.EmpleadoFacade;
import empleadosjsf.ejb.PuestoFacade;
import empleadosjsf.ejb.SueldoFacade;
import empleadosjsf.entity.Departamento;
import empleadosjsf.entity.DeptEmp;
import empleadosjsf.entity.DeptEmpPK;
import empleadosjsf.entity.Empleado;
import empleadosjsf.entity.Puesto;
import empleadosjsf.entity.Sueldo;
import empleadosjsf.entity.SueldoPK;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Adrián
 */
@Named(value = "modificarEmpleadoBean")
@RequestScoped
public class ModificarEmpleadoBean {

    @EJB
    private SueldoFacade sueldoFacade;

    @EJB
    private PuestoFacade puestoFacade;

    @EJB
    private DepartamentoFacade departamentoFacade;

    @EJB
    private DeptEmpFacade deptEmpFacade;

    @EJB
    private EmpleadoFacade empleadoFacade;

    @Inject
    private EmpleadosBean empleadosBean;
    
    
    /**
     * Creates a new instance of ModificarEmpleadoBean
     */
    public ModificarEmpleadoBean() {
    }
    
    private String departamentoActual;
    private List<Puesto> historialPuestos;
    private String denominacion;
    private List<Sueldo> historialSueldos;
    private String cantidad;

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
    
    public List<Sueldo> getHistorialSueldos() {
        return historialSueldos;
    }

    public void setHistorialSueldos(List<Sueldo> historialSueldos) {
        this.historialSueldos = historialSueldos;
    }
    
    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public List<Puesto> getHistorialPuestos() {
        return historialPuestos;
    }

    public void setHistorialPuestos(List<Puesto> historialPuestos) {
        this.historialPuestos = historialPuestos;
    }

    public String getDepartamentoActual() {
        return departamentoActual;
    }

    public void setDepartamentoActual(String departamentoActual) {
        this.departamentoActual = departamentoActual;
    }
    
    public void cargarDepartamentoEmpleado(){
        this.departamentoActual =  this.empleadosBean.getDepartamentoEmpleado(this.empleadosBean.getEmpleadoAEditar()).getDeptNo();
    }
    
    private void cargarHistorialPuestos() {
        this.historialPuestos = this.empleadosBean.getHistorialPuestosEmpleadoAEditar();
    }
    
    private void cargarHistorialSueldos(){
        this.historialSueldos = this.empleadosBean.getHistorialSueldosEmpleadoAEditar();
    }
    
    public String doEditar(Empleado empleado){
        this.empleadosBean.setEmpleadoAEditar(empleado);
        this.cargarDepartamentoEmpleado();
        this.cargarHistorialPuestos();
        
        return "editarempleado";
    }
    
    public String doGuardar() throws ParseException{
        if (!this.empleadosBean.getDepartamentoEmpleado(this.empleadosBean.getEmpleadoAEditar()).getDeptNo().equals(this.departamentoActual)){
            List<DeptEmp> historialDepartamentos = this.empleadosBean.getHistorialDepartamentosEmpleadoAEditar();
            DeptEmp ultimoDepartamento = historialDepartamentos.get(0);
            ultimoDepartamento.setFechaHasta(new Date());
            historialDepartamentos.set(0, ultimoDepartamento);
            //-------------------------------------------
            SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
            DeptEmp nuevoDepartamento = new DeptEmp(new DeptEmpPK(this.empleadosBean.getEmpleadoAEditar().getEmpNo(), this.departamentoActual),
                     new Date(),
                     sdf.parse("9999-01-01"));
            nuevoDepartamento.setDepartamento(this.departamentoFacade.find(this.departamentoActual));
            nuevoDepartamento.setEmpleado(this.empleadosBean.getEmpleadoAEditar());
            historialDepartamentos.add(nuevoDepartamento);
            this.empleadosBean.getEmpleadoAEditar().setDeptEmpCollection(historialDepartamentos);
            this.deptEmpFacade.edit(ultimoDepartamento);
            this.deptEmpFacade.create(nuevoDepartamento);
        }
        this.empleadoFacade.edit(this.empleadosBean.getEmpleadoAEditar());
        this.empleadosBean.init();
        return "listadoempleados";
    }
    
    public String doGuardarPuesto() throws ParseException{
        if (this.denominacion != null && !this.denominacion.isEmpty()){
            //List<Puesto> historialPuestos = this.empleadosBean.getHistorialPuestosEmpleadoAEditar();
            this.cargarHistorialPuestos();
            Puesto ultimoPuesto = historialPuestos.get(0);
            ultimoPuesto.setFechaHasta(new Date());
            historialPuestos.set(0, ultimoPuesto);
            //-------------------------------
            Puesto nuevoPuesto = new Puesto(this.empleadosBean.getEmpleadoAEditar().getEmpNo()
                                            ,this.denominacion
                                            ,new Date());
            nuevoPuesto.setEmpleado(this.empleadosBean.getEmpleadoAEditar());
            nuevoPuesto.setFechaHasta(new SimpleDateFormat("yyyy-MM-dd").parse("9999-01-01"));
            this.historialPuestos.add(nuevoPuesto);
            this.empleadosBean.getEmpleadoAEditar().setPuestoCollection(historialPuestos);
            this.puestoFacade.edit(ultimoPuesto);
            this.puestoFacade.create(nuevoPuesto);
        }
        this.denominacion = "";
        this.cargarDepartamentoEmpleado();
        this.cargarHistorialPuestos();
        this.cargarHistorialSueldos();
        return "editarempleado";
    }
    public String doGuardarSueldo() throws ParseException{
        if (this.cantidad != null && !this.cantidad.isEmpty()){
            this.cargarHistorialSueldos();
            Sueldo ultimoSueldo = this.historialSueldos.get(0);
            ultimoSueldo.setFechaHasta(new Date());
            this.historialSueldos.set(0, ultimoSueldo);
            //-------------------------------
            Sueldo nuevoSueldo = new Sueldo(new SueldoPK(this.empleadosBean.getEmpleadoAEditar().getEmpNo()
                                                        ,new Date()));
            nuevoSueldo.setCantidad(Integer.parseInt(this.cantidad));
            nuevoSueldo.setFechaHasta(new SimpleDateFormat("yyyy-MM-dd").parse("9999-01-01"));
            nuevoSueldo.setEmpleado(this.empleadosBean.getEmpleadoAEditar());
            this.historialSueldos.add(nuevoSueldo);
            this.empleadosBean.getEmpleadoAEditar().setSueldoCollection(historialSueldos);
            this.sueldoFacade.create(nuevoSueldo);
            this.sueldoFacade.edit(ultimoSueldo);
        }
        this.cantidad = "";
        this.cargarDepartamentoEmpleado();
        this.cargarHistorialPuestos();
        this.cargarHistorialSueldos();
        return "editarempleado";
    }
}
