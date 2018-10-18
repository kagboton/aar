package persistence.dao.jpa;

import persistence.beans.Client;
import persistence.dao.ClientDao;

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
