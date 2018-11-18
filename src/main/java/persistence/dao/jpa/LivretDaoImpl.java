package persistence.dao.jpa;

import org.springframework.stereotype.Repository;
import persistence.beans.Livret;
import persistence.dao.LivretDao;

@Repository
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
