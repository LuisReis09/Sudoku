

import javax.swing.*;
import java.awt.*;

public class Analytics extends JPanel{
    JLabel titulo = new JLabel();


    JPanel mainPanel = new JPanel();
        JPanel infoPanel = new JPanel();
            InfoAnalytics[] metodsPanel = new InfoAnalytics[3];

    JPanel buttons = new JPanel();
        ImageIcon newPuzzleIcon = new ImageIcon("assets/newPuzzleBtn.png");
        JLabel newPuzzleBtn = new JLabel();
        ImageIcon solutionIcon = new ImageIcon("assets/solutionBtn.png");
        JLabel solutionBtn = new JLabel();

    public Analytics(){
        this.setPreferredSize(new Dimension(930, 600));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(new Color(0xbfbfbf));


        // titulo
        titulo.setText("Sudoku Analytics");
        titulo.setHorizontalTextPosition(SwingConstants.CENTER);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setForeground(new Color(0x1e1e1e));
        titulo.setFont(Fontes.CHANGA_MEDIUM.deriveFont(28f));


        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        mainPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.setBackground(new Color(0xbfbfbf));
        

        // infoPanel
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.setBackground(new Color(0xbfbfbf));

        // APAGAR ESSA PARTE E SUBSTITUIR PELA RECEPCAO DOS RESULTADOS 
 
        for(int i = 0; i < 3; i++){
            metodsPanel[i] = new InfoAnalytics();
            metodsPanel[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            metodsPanel[i].setBackground(new Color(0xbfbfbf));
            infoPanel.add(metodsPanel[i]);
            infoPanel.add(Box.createVerticalStrut(10));
        }

        mainPanel.add(infoPanel);

        // buttons
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
        buttons.setPreferredSize(new Dimension(300, 50));
        buttons.setMinimumSize(getPreferredSize());
        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttons.setBackground(new Color(0xbfbfbf));

        newPuzzleBtn.setIcon(newPuzzleIcon);
        newPuzzleBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        solutionBtn.setIcon(solutionIcon);
        solutionBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        buttons.add(newPuzzleBtn);
        buttons.add(Box.createHorizontalStrut(10));
        buttons.add(solutionBtn);


        this.add(titulo);
        this.add(Box.createVerticalStrut(20));
        this.add(mainPanel);
        this.add(buttons);
    }

    public void update(Resultados[] res){

        for(int i = 0; i < 3; i++){
            
            metodsPanel[i].updateInfo(res[i]);

            revalidate();
            repaint();
        }

    }
}
