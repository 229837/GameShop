package com.app.gameshop.repositories;

import com.app.gameshop.model.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class ClientRepositoryTest {

    @Test
    public void ClientRepositoryTest() {
        Client client1 = new Client(UUID.randomUUID(), "client1", "1234", 1,1,1990);
        Client client2 = new Client(UUID.randomUUID(), "client2", "4321", 31,12,1990);

        ClientRepository cr = new ClientRepository();

        Assertions.assertEquals(cr.getAll().size(),0);

        Assertions.assertTrue(cr.add(client1));
        Assertions.assertEquals(cr.getAll().size(),1);
        Assertions.assertEquals(client1, cr.get(0));
        Assertions.assertFalse(cr.remove(client2));

        Assertions.assertTrue(cr.add(client2));
        Assertions.assertEquals(cr.getAll().size(),2);

        Assertions.assertEquals(client1, cr.get(0));
        Assertions.assertEquals(client2, cr.get(1));

        Assertions.assertTrue(cr.remove(client1));
        Assertions.assertEquals(cr.getAll().size(),1);
        Assertions.assertFalse(cr.remove(client1));
        Assertions.assertEquals(cr.get(0), client2);

        Assertions.assertEquals(cr.get(0).getLogin(), "client2");
        Assertions.assertEquals(cr.get(0).getMonth(), 12);
        Assertions.assertEquals(cr.get(0).getDay(), 31);
        Assertions.assertEquals(cr.get(0).getYear(), 1990);




    }
}
