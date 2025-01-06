

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
// import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.BoxLayout;

// import javax.swing.border.Border;
// import javax.swing.BorderFactory;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;


public class Info extends JPanel{
    // atributos da janela
        // cores
    static Color corDeFundo = new Color(0xbfbfbf);
        // imagens
    static ImageIcon logo = new ImageIcon("assets\\logo.png");
    static ImageIcon startBtnImg = new ImageIcon("assets\\startBtn.png");
        // labels
    JLabel titulo = new JLabel();
    JLabel descricao = new JLabel("By Herick Jose, Joao Marcos, Luis Reis and Rafael Franca.");
    JLabel startBtn = new JLabel();
    JLabel tempoIndexada = new JLabel();
    JLabel tempoSaltos = new JLabel();
        // borda
    // Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
    
    // construtor
    public Info(long tempoListaIndexada, long tempoListaSaltos){
        this.setPreferredSize(new Dimension(750, 600));
        this.setMinimumSize(this.getPreferredSize());
        this.setBackground(corDeFundo);

        titulo.setText("Data Structures II");
        titulo.setForeground(new Color(0x1e1e1e));
        titulo.setFont(Fontes.CHANGA_MEDIUM.deriveFont(30f));
        
        titulo.setIcon(logo);
        titulo.setIconTextGap(50);
        titulo.setVerticalTextPosition(JLabel.TOP);
        titulo.setHorizontalTextPosition(JLabel.CENTER);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // titulo.setBackground(Color.RED);
        // titulo.setOpaque(true);
        
        // titulo.setBorder(border);
        
        descricao.setForeground(new Color(0x1e1e1e));
        descricao.setFont(Fontes.CHANGA_REGULAR.deriveFont(18f));
        descricao.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Labels para os tempos
        tempoIndexada.setText("Arquivo carregado na Lista Indexada em: " + tempoListaIndexada + "ms");
        tempoIndexada.setForeground(new Color(0x1e1e1e));
        tempoIndexada.setFont(Fontes.CHANGA_REGULAR.deriveFont(14f));
        tempoIndexada.setAlignmentX(Component.CENTER_ALIGNMENT);

        tempoSaltos.setText("Arquivo carregado na Lista com Saltos em: " + tempoListaSaltos + "ms");
        tempoSaltos.setForeground(new Color(0x1e1e1e));
        tempoSaltos.setFont(Fontes.CHANGA_REGULAR.deriveFont(14f));
        tempoSaltos.setAlignmentX(Component.CENTER_ALIGNMENT);

        startBtn.setIcon(startBtnImg);
        startBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        startBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(titulo);
        this.add(Box.createVerticalStrut(10));
        this.add(tempoIndexada);
        this.add(tempoSaltos);
        this.add(Box.createVerticalStrut(20));
        this.add(startBtn);
        this.add(Box.createVerticalStrut(75));
        this.add(descricao);
    }

    public JLabel getStartBtn(){
        return startBtn;
    }
}
