package de.telran.my_secured_shop.services.jpa;

import de.telran.my_secured_shop.repository.jpa.CartRepository;
import de.telran.my_secured_shop.repository.jpa.UserRepository;
import de.telran.my_secured_shop.repository.jpa.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class JpaCustomerServiceTest {

    @Mock
    private static UserRepository repo;
    @Mock
    private static CartRepository cartRepo;
    @Mock
    private static ProductRepository productRepo;
    private static JpaUserService service;

    /*
    @BeforeAll
    public static void init() {
        service = new JpaCustomerService(repo, cartRepo, productRepo);
        service.add(new JpaCustomer(
                23, "boris",
                42, "borisbo@ghjk.rtz",
                new JpaCart()));
    }*/
    @Test
    void getAll() {
    }

    /*
    @Test
    static void getByIdTest() {
        Customer c = service.getById(23);
        assertEquals("boris", c.getName());
    }
     */
    @Test
    void getById() {

    }

    @Test
    void add() {

    }

    @Test
    void addToCartById() {
    }

    @Test
    void deleteByName() {
    }

    @Test
    void deleteFromCartById() {
    }
}