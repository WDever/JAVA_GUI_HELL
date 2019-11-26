import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class Label extends JLabel {
    Label(String text) {
        setText(text);
    }
}

class Button extends JButton {
    Button(String text, ActionListener listener) {
        setText(text);
        addActionListener(listener);
    }

    Button(int num, ActionListener listener) {
        setText(Integer.toString(num));
        addActionListener(listener);
    }
}

class Frame extends JFrame {
    GridBagConstraints gridBagConstraints;
    Label expressionLabel;
    Label resultLabel;

    ArrayList<String> strArr = new ArrayList<String>();

    void calculate() {
        strArr = new ArrayList<>(Arrays.asList(expressionLabel.getText().split(" ")));

        for (int i = 1; i < strArr.size(); i++) {
            if (strArr.get(i).equals("÷")) {
                strArr.set(i - 1, Integer.toString(Integer.parseInt(strArr.get(i - 1)) / Integer.parseInt(strArr.get(i + 1))));
                strArr.remove(i);
                strArr.remove(i);
                i--;
            } else if (strArr.get(i).equals("×")) {
                strArr.set(i - 1, Integer.toString(Integer.parseInt(strArr.get(i - 1)) * Integer.parseInt(strArr.get(i + 1))));
                strArr.remove(i);
                strArr.remove(i);
                i--;
            }
        }

        for (int i = 1; i < strArr.size(); i++) {
            if (strArr.get(i).equals("+")) {
                strArr.set(i - 1, Integer.toString(Integer.parseInt(strArr.get(i - 1)) + Integer.parseInt(strArr.get(i + 1))));
                strArr.remove(i);
                strArr.remove(i);
                i--;
            } else if (strArr.get(i).equals("-")) {
                strArr.set(i - 1, Integer.toString(Integer.parseInt(strArr.get(i - 1)) - Integer.parseInt(strArr.get(i + 1))));
                strArr.remove(i);
                strArr.remove(i);
                i--;
            }
        }

        resultLabel.setText("결과: " + strArr.get(strArr.size() - 1));
    }

    private void addComponent(JComponent obj, int x, int y, int width, int height) {
        gridBagConstraints.gridx = x;
        gridBagConstraints.gridy = y;

        gridBagConstraints.gridwidth = width;
        gridBagConstraints.gridheight = height;

        add(obj, gridBagConstraints);
    }

    class ButtonEvent implements ActionListener {
        String calc;
        int num;

        ButtonEvent(String input) {
            calc = input;
        }

        ButtonEvent(int input) {
            num = input;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (calc != null) {
                expressionLabel.setText(expressionLabel.getText() + " " + calc + " ");
            } else {
                expressionLabel.setText(expressionLabel.getText() + Integer.toString(num));
            }
        }
    }

    class ClearEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            expressionLabel.setText("식: ");
            resultLabel.setText("결과: ");
        }
    }

    class ResultEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            calculate();
        }
    }

    Frame() {
        GridBagLayout gridBag = new GridBagLayout();
        setLayout(gridBag);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;

        gridBagConstraints.fill = GridBagConstraints.BOTH;

        setSize(500, 300);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Calculator");

        expressionLabel = new Label("식: ");
        resultLabel = new Label("결과: ");

        addComponent(expressionLabel, 0, 0, 4, 2);
        addComponent(resultLabel, 0, 2, 4, 2);

        addComponent(new Button("C", new ClearEvent()), 0, 4, 3, 1);
        addComponent(new Button("÷", new ButtonEvent("÷")), 3, 4, 1, 1);

        addComponent(new Button(7, new ButtonEvent(7)), 0, 5, 1, 1);
        addComponent(new Button(8, new ButtonEvent(8)), 1, 5, 1, 1);
        addComponent(new Button(9, new ButtonEvent(9)), 2, 5, 1, 1);
        addComponent(new Button("×", new ButtonEvent("×")), 3, 5, 1, 1);

        addComponent(new Button(4, new ButtonEvent(4)), 0, 6, 1, 1);
        addComponent(new Button(5, new ButtonEvent(5)), 1, 6, 1, 1);
        addComponent(new Button(6, new ButtonEvent(6)), 2, 6, 1, 1);
        addComponent(new Button("-", new ButtonEvent("-")), 3, 6, 1, 1);

        addComponent(new Button(3, new ButtonEvent(3)), 0, 7, 1, 1);
        addComponent(new Button(2, new ButtonEvent(2)), 1, 7, 1, 1);
        addComponent(new Button(1, new ButtonEvent(1)), 2, 7, 1, 1);
        addComponent(new Button("+", new ButtonEvent("+")), 3, 7, 1, 1);

        addComponent(new Button(0, new ButtonEvent(0)), 0, 8, 3, 1);
        addComponent(new Button("=", new ResultEvent()), 3, 8, 1, 1);

        setVisible(true);
    }
}

public class Calculator extends JFrame {
    public static void main(String[] args) {
        Frame frame = new Frame();
    }
}
