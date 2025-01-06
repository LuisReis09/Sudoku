

import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import javax.swing.text.*;
import java.awt.*;

public class Casa extends JPanel{
    private char valor;
    private JTextField number = new JTextField();
    
    public Casa(char valor, boolean isSolution){
        this.valor = valor;
        
        number.setDocument(new LimitDocument(1));

        number.setText(this.valor == '0' ? "" : String.valueOf(this.valor));
        number.setEditable(!isSolution); 
        number.setBorder(null); 
        number.setBackground(null); 
        number.setOpaque(false); 
        number.setFont(Fontes.CHANGA_MEDIUM.deriveFont(25f)); 
        number.setForeground(new Color(0x1e1e1e)); 
        number.setHorizontalAlignment(SwingConstants.CENTER); 
        number.getDocument().addDocumentListener(new DocumentListener() {
            
            public void insertUpdate(DocumentEvent e) {
                onTextChanged();
            }

            public void removeUpdate(DocumentEvent e) {
                onTextChanged();
            }

            public void changedUpdate(DocumentEvent e) {
                onTextChanged();
            }

            private void onTextChanged() {
                setValor(number.getText());
                setColor(number);
                repaint();
                revalidate();
            }
        });

        this.setPreferredSize(new Dimension(40, 40));
        this.setBorder(BorderFactory.createLineBorder(new Color(0x1e1e1e), 1));
        this.setLayout(new GridLayout(1,1,0,0));
        this.add(number);

        if(String.valueOf(valor).equals("0"))
            this.setBackground(new Color(0xd9d9d9));
        else
            this.setBackground(new Color(0xababab));
    }

    private void setValor(String c){
        if(c.equals("")){
            this.valor = '0';
            return;
        }
        this.valor = c.charAt(0);
    }
    
    public Casa(char valor, char resposta, boolean isSolution){
        this.valor = valor;
        
        number.setDocument(new LimitDocument(1));

        number.setText(this.valor == '0' ? "" : String.valueOf(this.valor));
        number.setEditable(!isSolution); 
        number.setBorder(null); 
        number.setBackground(null); 
        number.setOpaque(false); 
        number.setFont(Fontes.CHANGA_MEDIUM.deriveFont(25f)); 
        number.setForeground(new Color(0x1e1e1e)); 
        number.setHorizontalAlignment(SwingConstants.CENTER); 
        number.getDocument().addDocumentListener(new DocumentListener() {
            
            public void insertUpdate(DocumentEvent e) {
                onTextChanged();
            }

            public void removeUpdate(DocumentEvent e) {
                onTextChanged();
            }

            public void changedUpdate(DocumentEvent e) {
                onTextChanged();
            }

            private void onTextChanged() {
                setValor(number.getText());
                setColor(number);
                repaint();
                revalidate();
            }
        });

        this.setPreferredSize(new Dimension(40, 40));
        this.setBorder(BorderFactory.createLineBorder(new Color(0x1e1e1e), 1));
        this.setLayout(new GridLayout(1,1,0,0));
        this.add(number);

        if(String.valueOf(valor).equals("0") || this.valor != resposta)
            this.setBackground(new Color(0xd9d9d9));
        else
            this.setBackground(new Color(0xababab));
    }

    // funcao auxiliar que limita o input a 1 caractere
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

    void setColor(JTextField number) {
        SwingUtilities.invokeLater(() -> {  // usando invoke later para evitar problemas de concorrencia
            if (number.getText().equals("0") || !number.getText().matches("\\d+")) {
                number.setText("");
                this.setBackground(new Color(0xd9d9d9));
            }else {
                this.setBackground(new Color(0xababab));
                this.valor = number.getText().charAt(0); // atualiza o valor da casa apos finalizada a edicao
            }
        });
    }

    public char getValor(){
        if(String.valueOf(this.valor).equals("0"))
            return '0';
            
        return this.valor;
    }

    public void update(char valor){
        this.valor = valor;
        number.setText(this.valor == '0' ? "" : String.valueOf(this.valor));

        if(valor == '0'){
            this.setBackground(new Color(0xd9d9d9));
        }else{
            this.setBackground(new Color(0xababab));
        }
    }
}
