

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FindSolution {

    private List<List<String>> res;

    public List<List<String>> solveNQueens(int n) {
        res = Collections.synchronizedList(new ArrayList<>());
        List<Thread> threads = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            threads.add(new MultiThreadPrbolem(i, n, res));
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return res;
    }
}