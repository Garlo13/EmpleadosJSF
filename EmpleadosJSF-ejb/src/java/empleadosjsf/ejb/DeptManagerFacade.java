/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empleadosjsf.ejb;

import empleadosjsf.entity.DeptManager;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Adrián
 */
@Stateless
public class DeptManagerFacade extends AbstractFacade<DeptManager> {

    @PersistenceContext(unitName = "EmpleadosJSF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DeptManagerFacade() {
        super(DeptManager.class);
    }

    public void elminarHistorialUsuario(int idEmpleado) {
        Query q;
        q = this.em.createQuery("DELETE FROM DeptManager d WHERE d.deptManagerPK.empNo = :idEmpleado");
        q.setParameter("idEmpleado", idEmpleado);
        q.executeUpdate();
    }
}
