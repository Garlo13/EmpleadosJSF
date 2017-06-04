/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empleadosjsf.ejb;

import empleadosjsf.entity.Sueldo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Adrián
 */
@Stateless
public class SueldoFacade extends AbstractFacade<Sueldo> {

    @PersistenceContext(unitName = "EmpleadosJSF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SueldoFacade() {
        super(Sueldo.class);
    }

    public void elminarHistorialUsuario(int idEmpleado) {
        Query q;
        q = this.em.createQuery("DELETE FROM Sueldo s WHERE s.sueldoPK.empNo = :idEmpleado");
        q.setParameter("idEmpleado", idEmpleado);
        q.executeUpdate();
    }
}
