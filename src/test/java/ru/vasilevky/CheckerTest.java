package ru.vasilevky;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.vasilevsky.Checker;
import ru.vasilevsky.document.DocumentException;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Класс, тестирующий корректность работы класса {@link Checker}.
 */
public class CheckerTest extends Assert {
    private Checker checker;

    /**
     * Метод инициализирующий окружение.
     * @throws DocumentException
     */
    @Before
    public void init() throws DocumentException {
        InputStream is = getClass().getResourceAsStream("/ru/vasilevsky/test.t");
        checker = new Checker();
        checker.init(is);

    }

    /**
     * Метод проверяющий соответствие количества мертвых ссылок
     * в тестовом входном файле с вычисленным.
     */
    @Test
    public void testCountDeadLinks() {
        assertEquals(2, checker.countDeadLinks());
    }

    /**
     * Метод проверяющий соответствие количества недостижимых документов
     * в тестовом входном файле с вычисленным.
     */
    @Test
    public void testCountNotResolved() {
        assertEquals(1, checker.countNotResolved());
    }
}
