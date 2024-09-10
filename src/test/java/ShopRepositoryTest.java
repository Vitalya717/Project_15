import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.statistic.NotFoundException;
import ru.netology.statistic.Product;
import ru.netology.statistic.ShopRepository;

import java.util.Objects;

public class ShopRepositoryTest {

    Product product1 = new Product(5, "тетрадь", 20);
    Product product2 = new Product(55, "ручка", 60);
    Product product3 = new Product(555, "книга", 100);

    ShopRepository shop = new ShopRepository();

    @Test
    public void addAllProducts() {

        shop.add(product1);
        shop.add(product2);
        shop.add(product3);

        Product[] expected = {product1, product2, product3};
        Product[] actual = shop.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void addOneProduct() {

        shop.add(product1);

        Product[] expected = {product1};
        Product[] actual = shop.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void doNotAddProducts() {

        Product[] expected = {};
        Product[] actual = shop.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void productSearchById_1() {

        shop.add(product1);
        shop.add(product2);
        shop.add(product3);

        Product expected = product1;
        Product actual = shop.findById(5);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void productSearchById_2() {

        shop.add(product1);
        shop.add(product2);
        shop.add(product3);

        Product expected = product3;
        Product actual = shop.findById(555);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void searchForProductByNonExistentId() {

        shop.add(product1);
        shop.add(product2);
        shop.add(product3);

        Product expected = null;
        Product actual = shop.findById(5555);
        Assertions.assertEquals(expected, actual);
    }


    @Test
    public void removeTheFirstProductById() {

        shop.add(product1);
        shop.add(product2);
        shop.add(product3);
        shop.remove(5);
        Product[] expected = {product2, product3};
        Product[] actual = shop.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void removeTheLastProductById() {

        shop.add(product1);
        shop.add(product2);
        shop.add(product3);
        shop.remove(555);
        Product[] expected = {product1, product2};
        Product[] actual = shop.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void removeAllProductsById() {

        shop.add(product1);
        shop.add(product2);
        shop.add(product3);
        shop.remove(5);
        shop.remove(55);
        shop.remove(555);
        Product[] expected = {};
        Product[] actual = shop.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void removeNonExistentId() {

        shop.add(product1);
        shop.add(product2);
        shop.add(product3);

        Assertions.assertThrows(NotFoundException.class, () -> {
            shop.removeById(5555);
        });
    }

    @Test
    public void writeDownHashCodeProduct1() {

        int expected = Objects.hash(5, "тетрадь", 20);
        int actual = product1.hashCode();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void writeDownId() {

        int expected = 5;
        int actual = product1.getId();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void writeDownPrice() {

        product1.setPrice(200);

        int expected = 200;
        int actual = product1.getPrice();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void writeDownTitle() {

        product1.setTitle("арбуз");

        String expected = "арбуз";
        String actual = product1.getTitle();
        Assertions.assertEquals(expected, actual);
    }

}
