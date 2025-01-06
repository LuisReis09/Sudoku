

import javax.swing.*;
import java.awt.*;

public class InfoAnalytics extends JPanel {
    private JLabel titulo = new JLabel();

    private JPanel infoPanel = new JPanel();
    private JPanel labelPanel = new JPanel();
    private JPanel valuePanel = new JPanel();

    private JLabel tempoLabel = new JLabel();
    private JLabel operacoesLabel = new JLabel();

    private JLabel tempoValue = new JLabel();
    private JLabel operacoesValue = new JLabel();

    public InfoAnalytics() {
        this.setPreferredSize(new Dimension(240, 92));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(new Color(0xbfbfbf));

        // Título
        titulo.setText("");
        titulo.setHorizontalTextPosition(SwingConstants.LEFT);
        titulo.setForeground(new Color(0x1e1e1e));
        titulo.setFont(Fontes.CHANGA_MEDIUM.deriveFont(16f));
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Painel de informações
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.X_AXIS));
        infoPanel.setBackground(new Color(0xABABAB));
        infoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Painel dos rótulos (Time, Operations)
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
        labelPanel.setOpaque(false); // Deixa o painel transparente

        tempoLabel.setText("Time:");
        tempoLabel.setForeground(new Color(0xf1f1f1));
        tempoLabel.setFont(Fontes.CHANGA_MEDIUM.deriveFont(14f));
        tempoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        operacoesLabel.setText("Operations:");
        operacoesLabel.setForeground(new Color(0xf1f1f1));
        operacoesLabel.setFont(Fontes.CHANGA_MEDIUM.deriveFont(14f));
        operacoesLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        labelPanel.add(tempoLabel);
        labelPanel.add(Box.createVerticalStrut(5)); 
        labelPanel.add(operacoesLabel);
        
        valuePanel.setLayout(new BoxLayout(valuePanel, BoxLayout.Y_AXIS));
        valuePanel.setOpaque(false);
        
        tempoValue.setText("");
        tempoValue.setForeground(new Color(0x1e1e1e));
        tempoValue.setFont(Fontes.CHANGA_MEDIUM.deriveFont(14f));
        tempoValue.setAlignmentX(Component.RIGHT_ALIGNMENT);
        
        operacoesValue.setText("");
        operacoesValue.setForeground(new Color(0x1e1e1e));
        operacoesValue.setFont(Fontes.CHANGA_MEDIUM.deriveFont(14f));
        operacoesValue.setAlignmentX(Component.RIGHT_ALIGNMENT);
        
        valuePanel.add(tempoValue);
        labelPanel.add(Box.createVerticalStrut(5)); 
        valuePanel.add(operacoesValue);

        // Adicionar subpainéis ao infoPanel
        infoPanel.add(Box.createHorizontalStrut(20));
        infoPanel.add(labelPanel);
        infoPanel.add(Box.createHorizontalGlue()); // Espaço flexível entre label e valor
        infoPanel.add(valuePanel);
        infoPanel.add(Box.createHorizontalStrut(20));

        titulo.setBackground(new Color(0xbfbfbf));
        this.setBackground(new Color(0xbfbfbf));
        // Adicionar componentes ao painel principal
        this.add(titulo);
        // this.add(Box.createVerticalStrut(5));
        this.add(infoPanel);
    }

    public void updateInfo(Resultados res) {
        titulo.setText(res.metodo + ":");
        tempoValue.setText(res.tempo + " us");
        operacoesValue.setText(String.valueOf(res.operacoes));

        revalidate();
        repaint();
    }
}
