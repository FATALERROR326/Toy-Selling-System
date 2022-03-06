import com.google.code.tempusfugit.concurrency.ConcurrentRule;
import com.google.code.tempusfugit.concurrency.RepeatingRule;
import com.google.code.tempusfugit.concurrency.annotations.Concurrent;
import com.google.code.tempusfugit.concurrency.annotations.Repeating;
import net.gRpcClient.GrpcClient;
import net.gRpcClient.RequestGRPCEntity;
import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

public class GRPCTest {
    @Rule
    public ConcurrentRule concurrently = new ConcurrentRule();
    @Rule
    public RepeatingRule repeatingly = new RepeatingRule();

    @Test
    @Concurrent(count = 10)
    @Repeating(repetition = 3)
    public void testGrpc() {
        long startTime = System.currentTimeMillis();

        //generate toy name and method name randomly by RNG and thread number
        String toyName = Thread.currentThread().getId() % 2 == 0 ?
                (Thread.currentThread().getId() % 4 == 2 ? "Tux" : "Whale")
                : (Thread.currentThread().getId() % 4 == 1 ? "Bird" : "Elephant");
        String methodName = (Thread.currentThread().getId() + ThreadLocalRandom.current().nextInt()) % 2 == 0 ? "query" : "buy";

        String result = GrpcClient.runGRPC(new RequestGRPCEntity(methodName, toyName));
        long etime = System.currentTimeMillis();

        System.out.println(etime - startTime + "ms later, the result is: " + result
                + " for thread ID: " + Thread.currentThread().getName()
                + " with the query type of " + toyName + " " + methodName + ".");
    }
}
