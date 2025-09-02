package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class ConsultantPoolTest {

    @Test
    public void ConsultantPoolTest(){
        ArrayList<Client> clients = new ArrayList<>();
        clients.add(new Client(10, 20));
        clients.add(new Client(10, 15));
        clients.add(new Client(10, 10));

        clients.add(new Client(15, 10)); // 5
        clients.add(new Client(15, 10)); // 10
        clients.add(new Client(15, 10)); // 15

        ConsultantPool consultantPool = new ConsultantPool(clients, 3);

        Assertions.assertEquals(consultantPool.getWaittedTime(), 30);
    }

}
