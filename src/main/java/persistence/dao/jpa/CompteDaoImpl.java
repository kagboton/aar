package persistence.dao.jpa;

import persistence.beans.Compte;
import persistence.dao.CompteDao;

public class CompteDaoImpl extends AbstractDaoImpl<Compte> implements CompteDao {
    /**
     * Constructeur
     *
     * @param entityClass
     */
    public CompteDaoImpl(Class<Compte> entityClass) {
        super(entityClass);
    }

    public CompteDaoImpl() {
        this(Compte.class);
    }
}
