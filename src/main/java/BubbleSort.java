import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BubbleSort {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        System.out.println(sdf.format(new Date()));
        final int NUM_THREADS = Runtime.getRuntime().availableProcessors();
        final long numSteps = 70000000000L;
        final double step = 1.0 / (double) numSteps;

        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        List<Future<Double>> futures = new ArrayList<>();

        long chunk = numSteps / NUM_THREADS;

        for (int i = 0; i < NUM_THREADS; i++) {
            final long start = i * chunk;
            final long end = (i + 1) * chunk;
            futures.add(executor.submit(() -> {
                double sum = 0.0;
                for (long j = start; j < end; j++) {
                    double x = (j + 0.5) * step;
                    sum += 4.0 / (1.0 + x * x);
                }
                return sum * step;
            }));
        }

        executor.shutdown();

        double pi = 0.0;
        for (Future<Double> future : futures) {
            pi += future.get();
        }
        System.out.println(pi);
        System.out.println(sdf.format(new Date()));
    }
}

