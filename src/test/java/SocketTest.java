import com.company.Client.Client;
import com.company.Client.RequestEntity;
import com.google.code.tempusfugit.concurrency.ConcurrentRule;
import com.google.code.tempusfugit.concurrency.RepeatingRule;
import com.google.code.tempusfugit.concurrency.annotations.Concurrent;
import com.google.code.tempusfugit.concurrency.annotations.Repeating;
import org.junit.Rule;
import org.junit.Test;

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
        //generate toy name randomly by thread number
        String toyName = Thread.currentThread().getId() % 2 == 0 ? "Tux" : "Whale";
        String result = Client.runSocket(new RequestEntity("query", toyName));
        long etime = System.currentTimeMillis();
        System.out.println(etime - startTime + "ms later, the result is: " + result
                + " for thread ID: " + Thread.currentThread().getName()
                + " with the query type of " + toyName + ".");
    }
}
