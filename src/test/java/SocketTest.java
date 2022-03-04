//import com.company.client.Client;
//import com.company.client.RequstEntity;
//import com.google.code.tempusfugit.concurrency.ConcurrentRule;
//import com.google.code.tempusfugit.concurrency.RepeatingRule;
//import com.google.code.tempusfugit.concurrency.annotations.Concurrent;
//import com.google.code.tempusfugit.concurrency.annotations.Repeating;
//import org.junit.Rule;
//import org.junit.Test;
//
//import java.io.IOException;
//
//public class SocketTest {
//    @Rule
//    public ConcurrentRule concurrently = new ConcurrentRule();
//    @Rule
//    public RepeatingRule repeatingly = new RepeatingRule();
//
//    @Test
//    @Concurrent(count = 100)
//    @Repeating(repetition = 2)
//    public void test() {
//        long startTime = System.currentTimeMillis();
//        String result = Client.runSocket(new RequstEntity("query", "Tux"));
//        long etime = System.currentTimeMillis();
//        System.out.println(etime - startTime + " after, the result is: " + result);
//    }
//}
