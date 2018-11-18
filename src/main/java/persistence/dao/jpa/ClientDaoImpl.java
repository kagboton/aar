package persistence.dao.jpa;

import org.springframework.stereotype.Repository;
import persistence.beans.Client;
import persistence.dao.ClientDao;

@Repository
public class ClientDaoImpl extends AbstractDaoImpl<Client> implements ClientDao {
    /**
     * Constructeur
     *
     * @param entityClass
     */
    public ClientDaoImpl(Class<Client> entityClass) {
        super(entityClass);
    }

    public ClientDaoImpl() {
        this(Client.class);
    }
}
