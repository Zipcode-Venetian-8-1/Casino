package com.github.zipcodewilmington;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class HighLowDiceTest {

    @Test
    public void test() throws IOException { // TODO
        // given
        Casino runnable = new Casino();

        // when
        runnable.run();

        // then
        Assert.assertNotNull(runnable.toString());
    }

}
