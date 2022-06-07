package com.app.gameshop.services;

import com.app.gameshop.model.Client;
import com.app.gameshop.repositories.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientServiceTest {
    @Test
    public void ClientServiceTest(){
        ClientService cs = new ClientService(new ClientRepository());

        Client client1 = new Client(UUID.randomUUID(), "client1", "1234", 1,1,1990);
        Client client2 = new Client(UUID.randomUUID(), "client2", "4321", 31,12,1990);

        Assertions.assertEquals(cs.getAll().size(), 0);
        Assertions.assertTrue(cs.add(client1));
        Assertions.assertEquals(cs.getAll().size(), 1);

        Assertions.assertEquals(cs.getByID(0), client1);

        Assertions.assertFalse(cs.remove(client2));

        Assertions.assertTrue(cs.add(client2));
        Assertions.assertEquals(cs.getAll().size(), 2);

        Assertions.assertEquals(cs.getByID(0), client1);
        Assertions.assertEquals(cs.getByID(1), client2);
        Assertions.assertEquals(cs.findByLogin("client1"),client1);
        Assertions.assertEquals(cs.findByLogin("client2"),client2);
    }
}
