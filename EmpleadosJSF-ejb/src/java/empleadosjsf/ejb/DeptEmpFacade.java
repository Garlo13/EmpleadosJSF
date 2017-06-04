/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empleadosjsf.ejb;

import empleadosjsf.entity.DeptEmp;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Adrián
 */
@Stateless
public class DeptEmpFacade extends AbstractFacade<DeptEmp> {

    @PersistenceContext(unitName = "EmpleadosJSF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DeptEmpFacade() {
        super(DeptEmp.class);
    }
    
    public void elminarHistorialUsuario(int idEmpleado){
        Query q;
        q = this.em.createQuery("DELETE FROM DeptEmp d WHERE d.deptEmpPK.empNo = :idEmpleado");
        q.setParameter("idEmpleado", idEmpleado);
        q.executeUpdate();
    }
    
}
