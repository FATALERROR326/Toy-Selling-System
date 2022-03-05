import com.company.Client.Client;
import com.company.Client.RequestEntity;
import com.google.code.tempusfugit.concurrency.ConcurrentRule;
import com.google.code.tempusfugit.concurrency.RepeatingRule;
import com.google.code.tempusfugit.concurrency.annotations.Concurrent;
import com.google.code.tempusfugit.concurrency.annotations.Repeating;
import net.gRpcClient.GrpcClient;
import org.junit.Rule;
import org.junit.Test;
import sun.security.provider.NativePRNG;

import java.util.concurrent.ThreadLocalRandom;

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
        String toyName = Thread.currentThread().getId() % 2 == 0 ? "Tux" : "Whale";
        String result = Client.runSocket(new RequestEntity("query", toyName));
        long etime = System.currentTimeMillis();
        System.out.println(etime - startTime + "ms later, the result is: " + result
                + " for thread ID: " + Thread.currentThread().getName()
                + " with the query type of " + toyName + ".");

    }

    @Test
    @Concurrent(count = 10)
    @Repeating(repetition = 3)
    public void testGrpc() {
        long startTime = System.currentTimeMillis();
        String toyName = Thread.currentThread().getId() % 2 == 0 ?
                (Thread.currentThread().getId() % 4 == 2 ? "Tux" : "Whale")
                : (Thread.currentThread().getId() % 4 == 1 ? "Bird" : "Elephant");
        String methodName = (Thread.currentThread().getId() + ThreadLocalRandom.current().nextInt()) % 2 == 0 ? "query" : "buy";
        String result = GrpcClient.runGRPC(new RequestEntity(methodName, toyName));
        long etime = System.currentTimeMillis();
        System.out.println(etime - startTime + "ms later, the result is: " + result
                + " for thread ID: " + Thread.currentThread().getName()
                + " with the query type of " + toyName + " " + methodName + ".");
    }
}
