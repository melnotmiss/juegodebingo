import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TablaBingo extends JFrame {
    private List<Integer> numbers;
    private List<JLabel> numberLabels;
    static final int ROWS = 5;
    static final int COLS = 5;

    public TablaBingo() {
        super("Tarjeta de Bingo");
        numbers = new ArrayList<>();
        for (int i = 1; i <= 75; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);

        numberLabels = new ArrayList<>();
        JPanel cardPanel = new JPanel(new GridLayout(ROWS, COLS));
        for (int i = 0; i < ROWS * COLS; i++) {
            JLabel label = new JLabel(String.valueOf(numbers.get(i)), JLabel.CENTER);
            label.setFont(new Font("Arial", Font.PLAIN, 30));
            numberLabels.add(label);
            cardPanel.add(label);
        }

        setContentPane(cardPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public List<JLabel> getNumberLabels() {
        return numberLabels;
    }
}
