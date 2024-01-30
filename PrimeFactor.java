import java.io.PrintWriter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrimeFactor {

    private static final int MAX_LIMIT = 100_000_000; // 10^8

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        List<Integer> primes = IntStream.rangeClosed(2, MAX_LIMIT)
                                        .parallel()
                                        .filter(PrimeFactor::isPrime)
                                        .boxed()
                                        .collect(Collectors.toList());

        long endTime = System.currentTimeMillis();

        long sumOfPrimes = primes.stream().mapToLong(Integer::longValue).sum();
        List<Integer> topTenPrimes = primes.stream()
                                           .sorted(Comparator.reverseOrder())
                                           .limit(10)
                                           .sorted()
                                           .collect(Collectors.toList());

        try (PrintWriter writer = new PrintWriter("primes.txt")) {
            writer.print((endTime - startTime)/1000+ " ");
            writer.print(primes.size()+ " ");
            writer.print(sumOfPrimes+ " ");
            topTenPrimes.forEach(prime -> writer.print(prime + " "));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
