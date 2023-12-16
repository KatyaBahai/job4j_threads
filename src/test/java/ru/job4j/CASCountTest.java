package ru.job4j;

import org.junit.jupiter.api.Test;
import java.util.stream.IntStream;
import static org.assertj.core.api.Assertions.*;

class CASCountTest {

    @Test
    public void when2ThreadsCountTo100ThenResultIs200() throws InterruptedException {
        CASCount casCount = new CASCount();
        Thread firstThread = new Thread(() ->
                IntStream.range(1, 101).forEach((i) -> casCount.increment())
        );
        Thread secondThread = new Thread(() ->
                IntStream.range(1, 101).forEach((i) -> casCount.increment())
        );
        firstThread.start();
        secondThread.start();
        firstThread.join();
        secondThread.join();
        assertThat(casCount.get()).isEqualTo(200);
    }
}