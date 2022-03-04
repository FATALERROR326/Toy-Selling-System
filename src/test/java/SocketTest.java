import com.company.Client.Client;
import com.company.Client.RequestEntity;
import com.google.code.tempusfugit.concurrency.ConcurrentRule;
import com.google.code.tempusfugit.concurrency.RepeatingRule;
import com.google.code.tempusfugit.concurrency.annotations.Concurrent;
import com.google.code.tempusfugit.concurrency.annotations.Repeating;
import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class SocketTest {
    @Rule
    public ConcurrentRule concurrently = new ConcurrentRule();
    @Rule
    public RepeatingRule repeatingly = new RepeatingRule();

    @Test
    @Concurrent(count = 10)
    @Repeating(repetition = 1)
    public void test() {
        long startTime = System.currentTimeMillis();
        String result = Client.runSocket(new RequestEntity("query", "Tux"));
        long etime = System.currentTimeMillis();
        System.out.println(etime - startTime + "ms later, the result is: " + result);
    }

    @Test
    @Concurrent(count = 5)
    @Repeating(repetition = 2)
    public void testGrpc() {
//        long startTime = System.currentTimeMillis();
//        String result =
    }
}
