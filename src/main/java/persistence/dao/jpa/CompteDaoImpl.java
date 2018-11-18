package persistence.dao.jpa;

import org.springframework.stereotype.Repository;
import persistence.beans.Compte;
import persistence.dao.CompteDao;

@Repository
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
