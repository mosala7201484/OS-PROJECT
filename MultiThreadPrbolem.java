
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.List;


public class MultiThreadPrbolem extends Thread {
    private Set<Integer> vertical;
    private Set<Integer> posDiagonal;
    private Set<Integer> negDiagonal;
    private List<Integer> currSol;
    private int col;
    private int n;
    private List<List<String>> res;

    public MultiThreadPrbolem(int col, int n, List<List<String>> res) {
        this.vertical = new HashSet<>();
        this.posDiagonal = new HashSet<>();
        this.negDiagonal = new HashSet<>();
        this.currSol = new LinkedList<>();
        this.col = col;
        this.n = n;
        this.res = res;
    }

    @Override
    public void run() {
        try {
            vertical.add(col);
            negDiagonal.add(col);
            posDiagonal.add(col);
            currSol.add(col);
            solveNQueens(n, vertical, posDiagonal, negDiagonal, 1, res, currSol);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void solveNQueens(int n, Set<Integer> vertical, Set<Integer> posDiagonal, Set<Integer> negDiagonal,
            int row, List<List<String>> res, List<Integer> currSol) {
        if (row == n) {
            res.add(formatRes(currSol, n));
        } else {
            for (int col = 0; col < n; col++) {
                if (vertical.contains(col) || negDiagonal.contains(col - row) || posDiagonal.contains(col + row)) {
                    continue;
                }
                vertical.add(col);
                negDiagonal.add(col - row);
                posDiagonal.add(col + row);
                currSol.add(col);
                solveNQueens(n, vertical, posDiagonal, negDiagonal, row + 1, res, currSol);
                vertical.remove(col);
                negDiagonal.remove(col - row);
                posDiagonal.remove(col + row);
                currSol.remove(currSol.size() - 1);
            }
        }
    }

    private List<String> formatRes(List<Integer> currSol, int n) {
        List<String> res = new LinkedList<>();
        for (int solCol : currSol) {
            StringBuilder sb = new StringBuilder();
            for (int index = 0; index < n; index++) {
                if (index == solCol) {
                    sb.append('Q');
                } else {
                    sb.append('.');
                }
            }
            res.add(sb.toString());
        }
         return res;

}
}
