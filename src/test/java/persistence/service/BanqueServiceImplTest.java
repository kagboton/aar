package persistence.service;

import org.junit.Before;
import persistence.dao.ClientDao;
import persistence.dao.CompteDao;
import persistence.dao.LivretDao;

import static org.easymock.EasyMock.createMock;

public class BanqueServiceImplTest {
    ClientDao clientDao = null;
    CompteDao compteDao = null;
    LivretDao livretDao = null;

    BanqueService service = null;

    @Before
    public void setUp(){
        clientDao = createMock(ClientDao.class);
        compteDao = createMock(CompteDao.class);
        livretDao = createMock(LivretDao.class);;
        service = new BanqueServiceImpl(clientDao, compteDao, livretDao);
    }
}
