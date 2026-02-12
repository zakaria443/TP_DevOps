package net.javaguides.usermanagement.model;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class UserTest {

    @Test
    public void testUserGettersAndSetters() {
        User user = new User();
        user.setId(1);
        user.setName("Amine");
        user.setEmail("amine@example.com");
        user.setCountry("Morocco");

        assertEquals(1, user.getId());
        assertEquals("Amine", user.getName());
        assertEquals("amine@example.com", user.getEmail());
        assertEquals("Morocco", user.getCountry());
    }

    @Test
    public void testUserConstructors() {
        User userFull = new User(1, "Ahmed", "ahmed@ensi.ma", "Rabat");
        assertEquals("Ahmed", userFull.getName());
        
        User userNoId = new User("Sara", "sara@ensi.ma", "Casablanca");
        assertEquals("Sara", userNoId.getName());
    }
}