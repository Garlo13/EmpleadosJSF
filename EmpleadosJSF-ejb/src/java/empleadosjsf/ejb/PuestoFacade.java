/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empleadosjsf.ejb;

import empleadosjsf.entity.Puesto;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Adrián
 */
@Stateless
public class PuestoFacade extends AbstractFacade<Puesto> {

    @PersistenceContext(unitName = "EmpleadosJSF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PuestoFacade() {
        super(Puesto.class);
    }

    public void elminarHistorialUsuario(int idEmpleado) {
        Query q;
        q = this.em.createQuery("DELETE FROM Puesto p WHERE p.puestoPK.empNo = :idEmpleado");
        q.setParameter("idEmpleado", idEmpleado);
        q.executeUpdate();
    }
}
