package persistence.dao.jpa;

import persistence.beans.Livret;
import persistence.dao.LivretDao;

public class LivretDaoImpl extends AbstractDaoImpl<Livret> implements LivretDao {
    /**
     * Constructeur
     *
     * @param entityClass
     */
    public LivretDaoImpl(Class<Livret> entityClass) {
        super(entityClass);
    }

    public LivretDaoImpl() {
        this(Livret.class);
    }
}
