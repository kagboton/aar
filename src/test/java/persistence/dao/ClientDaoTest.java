package persistence.dao;

import org.junit.Assert;
import org.junit.Test;
import persistence.beans.Client;

import javax.annotation.Resource;

public class ClientDaoTest extends AbstractDaoTest {

    @Resource
    private ClientDao clientDao;

    @Test
    public void countClientsTest(){
        Assert.assertEquals("ClientNb",3, clientDao.count());
    }

    @Test
    public void findClientTest(){
        Client client = clientDao.find(1015L);
        Assert.assertEquals("prenom", "Sylvie", client.getPrenom());
    }


}
