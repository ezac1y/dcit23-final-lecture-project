import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class CalculatorApp extends JFrame {
    private JTextField display;
    private StringBuilder currentInput;
    private boolean isResultDisplayed;
    private DefaultListModel<String> historyModel;

    public CalculatorApp() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        // Custom fonts
        Font displayFont = new Font("Segoe UI", Font.PLAIN, 32);
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 20);
        Font historyFont = new Font("Segoe UI", Font.PLAIN, 14);

        currentInput = new StringBuilder();
        isResultDisplayed = false;

        // Main container with padding
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(30, 30, 40)); // dark blue gray background

        // Display panel on top of calculator panel
        display = new JTextField();
        display.setFont(displayFont);
        display.setEditable(false);
        display.setBackground(new Color(20, 20, 30));
        display.setForeground(Color.WHITE);
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(100, 100, 150), 3),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        display.setPreferredSize(new Dimension(0, 70));

        // Buttons panel with grid layout
        JPanel buttonsPanel = new JPanel(new GridLayout(6, 4, 12,12));
        buttonsPanel.setBackground(new Color(38, 38, 55)); // darker blue background

        // Buttons text array
        String[] buttons = {
            "C", "DEL", "%", "/",
            "7", "8", "9", "*",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "+/-", "0", ".", "=",
            "√", "^", " ", " "
        };

        for (String text : buttons) {
            if (text.equals(" ")) {
                buttonsPanel.add(new JLabel()); // empty spacer
                continue;
            }
            JButton btn = new JButton(text);
            btn.setFont(buttonFont);
            btn.setFocusPainted(false);
            btn.setOpaque(true);
            btn.setBorderPainted(false);
            btn.setForeground(Color.WHITE);

            // Assign distinct colors for button types
            if ("0123456789.".contains(text)) {
                btn.setBackground(new Color(70, 130, 180)); // steel blue for number buttons
            } else if ("C DEL".contains(text)) {
                btn.setBackground(new Color(220, 20, 60)); // crimson red for clear/delete
            } else if ("= + - * / % ^ √ +/-".contains(text)) {
                btn.setBackground(new Color(72, 209, 204)); // medium turquoise for operators
            } else {
                btn.setBackground(new Color(50, 50, 70)); // default dark
            }

            // Hover effect
            btn.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btn.setBackground(btn.getBackground().brighter());
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    if ("0123456789.".contains(text)) {
                        btn.setBackground(new Color(70, 130, 180));
                    } else if ("C DEL".contains(text)) {
                        btn.setBackground(new Color(220, 20, 60));
                    } else if ("= + - * / % ^ √ +/-".contains(text)) {
                        btn.setBackground(new Color(72, 209, 204));
                    } else {
                        btn.setBackground(new Color(50, 50, 70));
                    }
                }
            });

            btn.addActionListener(e -> buttonClicked(e.getActionCommand()));
            buttonsPanel.add(btn);
        }

        // Left side panel containing display and buttons, vertical layout
        JPanel calculatorPanel = new JPanel(new BorderLayout(15, 15));
        calculatorPanel.setOpaque(false);
        calculatorPanel.add(display, BorderLayout.NORTH);
        calculatorPanel.add(buttonsPanel, BorderLayout.CENTER);

        // History panel on right side
        JPanel historyPanel = new JPanel(new BorderLayout(10, 10));
        historyPanel.setPreferredSize(new Dimension(280, 0));
        historyPanel.setBackground(new Color(50, 55, 90)); // deep blue
        historyPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(100,100,150), 2),
            "History", 0, 0, new Font("Segoe UI", Font.BOLD, 16), Color.YELLOW));

        // History list and model
        historyModel = new DefaultListModel<String>();
        JList<String> historyList = new JList<String>(historyModel);
        historyList.setFont(historyFont);
        Color pinkBg = new Color(255, 182, 193);
        historyList.setBackground(pinkBg);
        historyList.setForeground(Color.BLACK);
        historyList.setSelectionBackground(new Color(72, 209, 204));
        historyList.setSelectionForeground(new Color(20,20,30));
        historyList.setFixedCellHeight(24);

        JScrollPane scrollPane = new JScrollPane(historyList);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBackground(pinkBg);
        historyPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(calculatorPanel, BorderLayout.CENTER);
        mainPanel.add(historyPanel, BorderLayout.EAST);

        add(mainPanel);

        updateDisplay("0");
    }

    private void buttonClicked(String command) {
        if (command.matches("[0-9]")) {
            if (isResultDisplayed) {
                currentInput.setLength(0);
                isResultDisplayed = false;
            }
            if (currentInput.length() == 1 && currentInput.charAt(0) == '0') {
                currentInput.setLength(0);
            }
            currentInput.append(command);
            updateDisplay(currentInput.toString());
        } else if (command.equals(".")) {
            if (isResultDisplayed) {
                currentInput.setLength(0);
                currentInput.append("0.");
                isResultDisplayed = false;
                updateDisplay(currentInput.toString());
                return;
            }
            if (currentInput.length() == 0) {
                currentInput.append("0.");
                updateDisplay(currentInput.toString());
                return;
            }
            int lastOperatorIndex = Math.max(currentInput.lastIndexOf("+"),
                    Math.max(currentInput.lastIndexOf("-"),
                    Math.max(currentInput.lastIndexOf("*"),
                    Math.max(currentInput.lastIndexOf("/"),
                             currentInput.lastIndexOf("%")))));
            String lastNumber = currentInput.substring(lastOperatorIndex + 1);
            if (!lastNumber.contains(".")) {
                currentInput.append(".");
            }
            updateDisplay(currentInput.toString());
        } else if (command.matches("[+\\-*/%^]")) {
            if (currentInput.length() == 0) {
                if (command.equals("-")) {
                    currentInput.append(command);
                    updateDisplay(currentInput.toString());
                }
                return;
            }
            if (isOperator(lastChar())) {
                currentInput.setCharAt(currentInput.length() - 1, command.charAt(0));
            } else {
                currentInput.append(command);
            }
            isResultDisplayed = false;
            updateDisplay(currentInput.toString());
        } else if (command.equals("C")) {
            currentInput.setLength(0);
            updateDisplay("0");
        } else if (command.equals("DEL")) {
            if (isResultDisplayed) {
                currentInput.setLength(0);
                updateDisplay("0");
                isResultDisplayed = false;
                return;
            }
            if (currentInput.length() > 0) {
                currentInput.deleteCharAt(currentInput.length() - 1);
                if (currentInput.length() == 0) {
                    updateDisplay("0");
                } else {
                    updateDisplay(currentInput.toString());
                }
            }
        } else if (command.equals("=")) {
            calculateResult();
        } else if (command.equals("+/-")) {
            toggleSign();
        } else if (command.equals("√")) {
            applySquareRoot();
        }
    }

    private char lastChar() {
        return currentInput.length() > 0 ? currentInput.charAt(currentInput.length() - 1) : '\0';
    }

    private boolean isOperator(char c) {
        return "+-*/%^".indexOf(c) >= 0;
    }

    private void updateDisplay(String text) {
        display.setText(text);
    }

    private void calculateResult() {
        try {
            if (currentInput.length() == 0) {
                return;
            }
            String expression = currentInput.toString();

            // Preprocess unary minus
            expression = preprocessUnaryMinus(expression);

            // Evaluate expression
            double result = evaluateExpression(expression);

            // Format result
            DecimalFormat df = new DecimalFormat("0.##########");
            String resultStr = df.format(result);

            // Add to history
            historyModel.add(0, currentInput.toString() + " = " + resultStr);

            updateDisplay(resultStr);
            currentInput.setLength(0);
            currentInput.append(resultStr);
            isResultDisplayed = true;
        } catch (Exception e) {
            updateDisplay("Error");
            currentInput.setLength(0);
            isResultDisplayed = true;
        }
    }

    private void toggleSign() {
        if (currentInput.length() == 0 || isResultDisplayed) {
            currentInput.setLength(0);
            currentInput.append("-");
            isResultDisplayed = false;
            updateDisplay(currentInput.toString());
            return;
        }

        int lastOperatorIndex = Math.max(currentInput.lastIndexOf("+"),
                Math.max(currentInput.lastIndexOf("-"),
                Math.max(currentInput.lastIndexOf("*"),
                Math.max(currentInput.lastIndexOf("/"),
                         currentInput.lastIndexOf("%")))));

        int start = lastOperatorIndex + 1;
        if (start < currentInput.length() && currentInput.charAt(start) == '-') {
            currentInput.deleteCharAt(start);
        } else {
            currentInput.insert(start, '-');
        }
        updateDisplay(currentInput.toString());
    }

    private void applySquareRoot() {
        try {
            if (currentInput.length() == 0) {
                return;
            }
            int lastOperatorIndex = Math.max(currentInput.lastIndexOf("+"),
                    Math.max(currentInput.lastIndexOf("-"),
                    Math.max(currentInput.lastIndexOf("*"),
                    Math.max(currentInput.lastIndexOf("/"),
                             currentInput.lastIndexOf("%")))));

            String lastNumStr = currentInput.substring(lastOperatorIndex + 1);
            if (lastNumStr.isEmpty()) {
                return;
            }
            double num = Double.parseDouble(lastNumStr);
            if (num < 0) {
                updateDisplay("Error");
                currentInput.setLength(0);
                isResultDisplayed = true;
                return;
            }
            double sqrtVal = Math.sqrt(num);
            DecimalFormat df = new DecimalFormat("0.##########");
            String sqrtStr = df.format(sqrtVal);

            currentInput.replace(lastOperatorIndex + 1, currentInput.length(), sqrtStr);
            updateDisplay(currentInput.toString());
        } catch (Exception e) {
            updateDisplay("Error");
            currentInput.setLength(0);
            isResultDisplayed = true;
        }
    }

    private String preprocessUnaryMinus(String input) {
        StringBuilder output = new StringBuilder();
        char prev = '\0';
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '-') {
                if (i == 0 || isOperator(prev) || prev == '(') {
                    output.append('~');
                } else {
                    output.append(c);
                }
            } else {
                output.append(c);
            }
            prev = c;
        }
        return output.toString();
    }

    private double evaluateExpression(String expr) throws Exception {
        Parser parser = new Parser(expr);
        return parser.parseExpression();
    }

    private static class Parser {
        private final String input;
        private int pos;
        private int length;

        public Parser(String input) {
            this.input = input;
            this.pos = 0;
            this.length = input.length();
        }

        public double parseExpression() throws Exception {
            double value = parseTerm();
            while (true) {
                if (eat('+')) {
                    value += parseTerm();
                } else if (eat('-')) {
                    value -= parseTerm();
                } else {
                    return value;
                }
            }
        }

        private double parseTerm() throws Exception {
            double value = parseFactor();
            while (true) {
                if (eat('*')) {
                    value *= parseFactor();
                } else if (eat('/')) {
                    double denominator = parseFactor();
                    if (denominator == 0) throw new ArithmeticException("Division by zero");
                    value /= denominator;
                } else if (eat('%')) {
                    double divisor = parseFactor();
                    if (divisor == 0) throw new ArithmeticException("Division by zero");
                    value %= divisor;
                } else {
                    return value;
                }
            }
        }

        private double parseFactor() throws Exception {
            double value = parseUnary();
            while (true) {
                if (eat('^')) {
                    value = Math.pow(value, parseUnary());
                } else {
                    return value;
                }
            }
        }

        private double parseUnary() throws Exception {
            if (eat('~')) {
                return -parseUnary();
            }
            return parsePrimary();
        }

        private double parsePrimary() throws Exception {
            skipWhitespace();
            if (eat('(')) {
                double value = parseExpression();
                if (!eat(')')) throw new Exception("Missing closing parenthesis");
                return value;
            }
            StringBuilder sb = new StringBuilder();
            boolean decimalFound = false;
            while (pos < length) {
                char c = input.charAt(pos);
                if ((c >= '0' && c <= '9') || c == '.') {
                    if (c == '.') {
                        if (decimalFound) break;
                        decimalFound = true;
                    }
                    sb.append(c);
                    pos++;
                } else {
                    break;
                }
            }
            if (sb.length() == 0) {
                throw new Exception("Unexpected char: " + (pos < length ? input.charAt(pos) : "end of input"));
            }
            return Double.parseDouble(sb.toString());
        }

        private void skipWhitespace() {
            while (pos < length && Character.isWhitespace(input.charAt(pos))) {
                pos++;
            }
        }

        private boolean eat(char c) {
            skipWhitespace();
            if (pos < length && input.charAt(pos) == c) {
                pos++;
                return true;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Ignore and continue
        }
        SwingUtilities.invokeLater(() -> {
            CalculatorApp calc = new CalculatorApp();
            calc.setVisible(true);
        });
    }
}