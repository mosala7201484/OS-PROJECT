import java.util.List;
import java.util.Scanner;

import java.awt.*;
import javax.swing.*;
import java.util.List;

public class FinalProject {

    private static JFrame frame;
    private static JTextField rowsField;
    private static JTextArea textArea;
    private static JButton printSolutionsButton;

    public static void main(String[] args) {
       
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame(" The Solution");
            frame.setSize(550,550);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            JPanel panel = new JPanel(new FlowLayout());
            panel.add(new JLabel("Number of rows:"));
            rowsField = new JTextField(4);
            panel.add(rowsField);

            printSolutionsButton = new JButton("Solve");
            printSolutionsButton.addActionListener((e) -> {
                try {
                    int numberOfRows = Integer.parseInt(rowsField.getText());
                        solveNQueens(numberOfRows);
                } catch (NumberFormatException var2) {
                    JOptionPane.showMessageDialog(frame, "Enter number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            panel.add(printSolutionsButton);

            frame.add(panel, BorderLayout.NORTH);

            textArea = new JTextArea();
            textArea.setEditable(false);
            textArea.setFont(new Font("Arial", Font.PLAIN, 16));
            textArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            frame.add(new JScrollPane(textArea), BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }

    private static void solveNQueens(int n) {
        FindSolution solver = new FindSolution();
        List<List<String>> solutions = solver.solveNQueens(n);

        if (solutions.isEmpty()) {
            textArea.setText("No solutions found for N = " + n);
        } else {
            textArea.setText("");
            for (List<String> solution : solutions) {
                for (String row : solution) {
                    textArea.append(row + "\n");
                }
                textArea.append("\n");
            }
        }
    }
}
