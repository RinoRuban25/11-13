import java.util.ArrayList;
import java.util.concurrent.*;

public class Main{
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        ArrayList<Future<Integer>> futures = new ArrayList<Future<Integer>>();
        for(int i = 0; i<100; i++){
            futures.add(executorService.submit(new Task()));
        }
        int result = 0;
        for(int i = 0; i<futures.size(); i++) {
            try {
                result += futures.get(i).get();
                System.out.println(result);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static class Task implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            int num = 0;
            for (int i = 0; i < 1000000; i++)
                num +=1;
            return num;
        }
    }
}