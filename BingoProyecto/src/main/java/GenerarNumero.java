import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenerarNumero extends JFrame {
    private final JLabel numberLabel;
    private final List<Integer> numbers;
    private final List<JLabel> tarjetaLabels;
    private int currentIndex;

    public GenerarNumero(List<JLabel> tarjetaLabels) {
        super("Generar Número Aleatorio");
        numbers = new ArrayList<>();
        for (int i = 1; i <= 75; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        currentIndex = 0;
        this.tarjetaLabels = tarjetaLabels;

        numberLabel = new JLabel("", JLabel.CENTER);
        numberLabel.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 30));

        JButton drawButton = new JButton("Generar Número Aleatorio");
        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawNumber();
            }
        });

        JPanel contentPane = new JPanel(new java.awt.BorderLayout());
        contentPane.add(numberLabel, java.awt.BorderLayout.CENTER);
        contentPane.add(drawButton, java.awt.BorderLayout.SOUTH);

        setContentPane(contentPane);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //Funcion que permite llevar un conteo de que no se generen mas de los numeros que estan estipulados
    private void drawNumber() {
        if (currentIndex < numbers.size()) {
            int number = numbers.get(currentIndex);
            numberLabel.setText(String.valueOf(number));
            currentIndex++;
            updateTarjeta(number);
        } else {
            JOptionPane.showMessageDialog(this, "Todos los números han sido generados.", "Fin del Juego", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void updateTarjeta(int number) {
        for (JLabel label : tarjetaLabels) {
            if (label.getText().equals(String.valueOf(number))) {
                label.setText("X");
                break;
            }
        }

        if (checkBingo()) {
            JOptionPane.showMessageDialog(this, "¡BINGO!", "BINGO", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    private boolean checkBingo() {
        // Verificar filas
        for (int i = 0; i < TablaBingo.ROWS; i++) {
            int count = 0;
            for (int j = 0; j < TablaBingo.COLS; j++) {
                JLabel label = tarjetaLabels.get(i * TablaBingo.COLS + j);
                if (label.getText().equals("X")) {
                    count++;
                }
            }
            if (count == TablaBingo.COLS) {
                return true;
            }
        }

        // Verificar columnas
        for (int i = 0; i < TablaBingo.COLS; i++) {
            int count = 0;
            for (int j = 0; j < TablaBingo.ROWS; j++) {
                JLabel label = tarjetaLabels.get(j * TablaBingo.COLS + i);
                if (label.getText().equals("X")) {
                    count++;
                }
            }
            if (count == TablaBingo.ROWS) {
                return true;
            }
        }

        // Verificar diagonal principal
        int countDiagonalPrincipal = 0;
        for (int i = 0; i < TablaBingo.ROWS; i++) {
            JLabel label = tarjetaLabels.get(i * TablaBingo.COLS + i);
            if (label.getText().equals("X")) {
                countDiagonalPrincipal++;
            }
        }
        if (countDiagonalPrincipal == TablaBingo.ROWS) {
            return true;
        }

        // Verificar diagonal inversa
        int countDiagonalInversa = 0;
        for (int i = 0; i < TablaBingo.ROWS; i++) {
            JLabel label = tarjetaLabels.get(i * TablaBingo.COLS + (TablaBingo.COLS - 1 - i));
            if (label.getText().equals("X")) {
                countDiagonalInversa++;
            }
        }
        return countDiagonalInversa == TablaBingo.ROWS;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TablaBingo tarjetaBingo = new TablaBingo();
                new GenerarNumero(tarjetaBingo.getNumberLabels());
            }
        });
    }
}
