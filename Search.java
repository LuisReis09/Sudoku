

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;
import java.awt.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.text.AttributeSet;


public class Search extends JPanel{
    private String puzzle = "000000000000000000000000000000000000000000000000000000000000000000000000000000000";

    private JLabel titulo = new JLabel("Sudoku Search");

    private JPanel inputs = new JPanel();

    private JPanel inputTabela = new JPanel();
        private JPanel input = new JPanel();
            private JLabel inputLabel = new JLabel("Input in table:");
            public Tabuleiro t = new Tabuleiro(puzzle, false);
        private JPanel buttons = new JPanel();
            public JLabel randomBtn = new JLabel(new ImageIcon("assets\\randomBtn.png"));
            private JLabel resetBtn = new JLabel(new ImageIcon("assets\\resetBtn.png"));
            public JLabel searchBtn = new JLabel(new ImageIcon("assets\\searchBtn.png"));
    private JPanel inputLine = new JPanel();
        private JLabel inputLineLabel = new JLabel("Input inline:");
        public JTextField inputLineField = new JTextField();


    public Search(){
        this.setPreferredSize(new Dimension(750, 600));
        this.setMinimumSize(this.getPreferredSize());
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(new Color(0xbfbfbf));

        // titulo
        titulo.setFont(Fontes.CHANGA_MEDIUM.deriveFont(30f));
        titulo.setForeground(new Color(0x1e1e1e));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);


        // inputTabela
        inputTabela.setLayout(new BoxLayout(inputTabela, BoxLayout.X_AXIS));
        inputTabela.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // input
        input.setLayout(new BoxLayout(input, BoxLayout.Y_AXIS));
        input.setBackground(new Color(0xbfbfbf));
        inputLabel.setFont(Fontes.CHANGA_MEDIUM.deriveFont(20f));
        inputLabel.setForeground(new Color(0x1e1e1e));
        inputLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // Aligned text to the left
        input.add(inputLabel);
        t.setAlignmentX(LEFT_ALIGNMENT);
        input.add(t);
        // buttons
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        randomBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        randomBtn.setVerticalAlignment(SwingConstants.TOP);
        randomBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        resetBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        resetBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        resetBtn.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                resetBtnClicked();
            }
        });

        searchBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        searchBtn.setVerticalAlignment(SwingConstants.BOTTOM);
        searchBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        buttons.add(Box.createVerticalStrut(35));
        buttons.add(randomBtn);
        buttons.add(Box.createVerticalStrut(230));
        buttons.add(resetBtn);
        buttons.add(Box.createVerticalStrut(8));
        buttons.add(searchBtn);
        buttons.setBackground(new Color(0xbfbfbf));
        
        inputTabela.add(input);
        inputTabela.add(Box.createHorizontalStrut(20));
        inputTabela.add(buttons);
        inputTabela.setBackground(new Color(0xbfbfbf));
        
        
        // inputLine
        inputLine.setLayout(new BoxLayout(inputLine, BoxLayout.Y_AXIS));
        inputLine.setAlignmentX(Component.LEFT_ALIGNMENT);
        inputLine.setBackground(new Color(0xbfbfbf));

        inputLineLabel.setFont(Fontes.CHANGA_MEDIUM.deriveFont(20f));
        inputLineLabel.setForeground(new Color(0x1e1e1e));
        inputLineLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // "padding" interno
        inputLineField.setBorder(new CompoundBorder(
            inputLineField.getBorder(), // Borda padrão
            new EmptyBorder(0, 10, 0, 10) // Padding interno
        ));

        inputLineField.setPreferredSize(new Dimension(600, 40));
        inputLineField.setMaximumSize(inputLineField.getPreferredSize());
        inputLineField.setFont(Fontes.CHANGA_MEDIUM.deriveFont(20f));
        inputLineField.setForeground(new Color(0x1d1d1d));
        inputLineField.setBackground(new Color(0xd9d9d9));
        inputLineField.setAlignmentX(Component.LEFT_ALIGNMENT);
        inputLineField.getDocument().addDocumentListener(new DocumentListener(){
            public void insertUpdate(DocumentEvent e){
                update();
            }
            public void removeUpdate(DocumentEvent e){
                update();
            }
            public void changedUpdate(DocumentEvent e){
                update();
            }

            private void update(){
                if(inputLineField.getText().length() <= 81 && inputLineField.getText().matches("\\d*")){
                    updateTabuleiro();
                }
                if(inputLineField.getText().length() > 81){
                    SwingUtilities.invokeLater(() -> {
                        inputLineField.setText(inputLineField.getText().substring(0, 81));
                    });
                }
            }
        });
        
        inputLine.add(inputLineLabel);
        inputLine.add(Box.createHorizontalStrut(10));
        inputLine.add(inputLineField);
        inputLine.add(Box.createHorizontalStrut(10));

        inputs.setLayout(new BoxLayout(inputs, BoxLayout.Y_AXIS));
        inputs.setAlignmentX(Component.LEFT_ALIGNMENT);
        inputs.add(inputTabela);
        inputs.add(Box.createVerticalStrut(10));
        inputs.add(inputLine);
        inputs.setBackground(new Color(0xbfbfbf));

        JPanel inputsBox = new JPanel();
        inputsBox.setLayout(new BoxLayout(inputsBox, BoxLayout.X_AXIS));
        inputsBox.add(Box.createHorizontalStrut(50));
        inputsBox.add(inputs);
        inputsBox.setBackground(new Color(0xbfbfbf));


        this.add(titulo);
        // this.add(Box.createVerticalStrut(10));
        this.add(inputsBox);
    }

    public String getInputLine(){
        return this.t.getTabuleiro();
    }


    private void updateTabuleiro(){
        String str_tratada = inputLineField.getText();

        if(str_tratada.length() < 81){
            str_tratada += "0".repeat(81 - str_tratada.length());
        }else if(str_tratada.length() > 81){
            str_tratada = str_tratada.substring(0, 81);
        }

        t.update(str_tratada);

        this.revalidate();
        this.repaint();
    }


    private void resetBtnClicked(){
        t.update("0".repeat(81));
        this.inputLineField.setText("");
        this.revalidate();
        this.repaint();
    }

    

    static class LimitDocument extends PlainDocument {
        private int max;

        public LimitDocument(int max) {
            this.max = max;
        }

        @Override
        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
            if (str == null) return;

            // Verifica se a inserção excede o limite
            if ((getLength() + str.length()) <= max) {
                super.insertString(offset, str, attr);
            }
        }
    }
}
