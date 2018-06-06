package ru.vasilevky;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.vasilevsky.Checker;
import ru.vasilevsky.document.DocumentException;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class CheckerTest extends Assert {
    private InputStream is;

    @Before
    public void init() {
        is = getClass().getResourceAsStream("/ru/vasilevsky/test.t");
    }

    @Test
    public void test() throws DocumentException {
        Checker checker = new Checker();
        checker.init(is);
        assertEquals(3, checker.countDeadLinks());
        assertEquals(1, checker.countNotResolved());
    }
}
