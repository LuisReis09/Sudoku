

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.datatransfer.*;

public class Solutions extends JPanel{

    JLabel titulo = new JLabel();

    JPanel tabsPanel = new JPanel();
        JPanel puzzlePanel = new JPanel();
            JLabel puzzleLabel = new JLabel();
            Tabuleiro puzzleTab;
        JPanel solutionPanel = new JPanel();
            JLabel solutionLabel = new JLabel();
            Tabuleiro solutionTab;
    
    JPanel buttons = new JPanel();
        ImageIcon newPuzzle = new ImageIcon("assets\\newPuzzleBtn.png");
        JLabel newPuzzleBtn = new JLabel();
        ImageIcon analytics = new ImageIcon("assets\\analyticsBtn.png");
        JLabel analyticsBtn = new JLabel();


    public Solutions(String puzzle, String solution){
        this.setPreferredSize(new Dimension(930, 600));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(new Color(0xbfbfbf));


        // titulo
        titulo.setText("Sudoku Solutions");
        titulo.setHorizontalTextPosition(SwingConstants.CENTER);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setForeground(new Color(0x1e1e1e));
        titulo.setFont(Fontes.CHANGA_MEDIUM.deriveFont(28f));

        // tabsPanel
        tabsPanel.setLayout(new BoxLayout(tabsPanel, BoxLayout.X_AXIS));
        tabsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        tabsPanel.setBackground(new Color(0xbfbfbf));

        puzzlePanel.setLayout(new BoxLayout(puzzlePanel, BoxLayout.Y_AXIS));
        puzzlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        puzzleLabel.setText("Puzzle (input):");
        puzzleLabel.setForeground(new Color(0x1e1e1e));
        puzzleLabel.setFont(Fontes.CHANGA_REGULAR.deriveFont(20f));
        puzzleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        puzzleTab = new Tabuleiro(puzzle, true);
        puzzleTab.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        puzzlePanel.add(puzzleLabel);
        // puzzlePanel.add(Box.createVerticalStrut(10));
        puzzlePanel.add(puzzleTab);
        puzzlePanel.setBackground(new Color(0xbfbfbf));
        
        solutionPanel.setLayout(new BoxLayout(solutionPanel, BoxLayout.Y_AXIS));
        solutionPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        solutionLabel.setText("Solution (output):");
        solutionLabel.setForeground(new Color(0x1e1e1e));
        solutionLabel.setFont(Fontes.CHANGA_REGULAR.deriveFont(20f));
        solutionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        solutionTab = new Tabuleiro(solution, puzzle,  true);
        solutionTab.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String texto = solutionTab.getTabuleiro();

            // Criar o conteúdo para a área de transferência
            StringSelection selecao = new StringSelection(texto);

            // Obter a área de transferência
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

            // Colocar o texto na área de transferência
            clipboard.setContents(selecao, null);

            JOptionPane.showMessageDialog(solutionPanel, "Text copied!");
            }
        });
        solutionTab.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        solutionPanel.add(solutionLabel);
        // solutionPanel.add(Box.createVerticalStrut(10));
        solutionPanel.add(solutionTab);
        solutionPanel.setBackground(new Color(0xbfbfbf));
        solutionPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String texto = solutionTab.getTabuleiro();

            // Criar o conteúdo para a área de transferência
            StringSelection selecao = new StringSelection(texto);

            // Obter a área de transferência
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

            // Colocar o texto na área de transferência
            clipboard.setContents(selecao, null);

            JOptionPane.showMessageDialog(solutionPanel, "Text copied!");
            }
        });
        
        tabsPanel.add(Box.createHorizontalStrut(20));
        tabsPanel.add(puzzlePanel);
        tabsPanel.add(Box.createHorizontalStrut(30));
        tabsPanel.add(solutionPanel);
        tabsPanel.setAlignmentX(Container.CENTER_ALIGNMENT);


        // buttons
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttons.setBackground(new Color(0xbfbfbf));

        newPuzzleBtn.setIcon(newPuzzle);
        newPuzzleBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        analyticsBtn.setIcon(analytics);
        analyticsBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        buttons.add(newPuzzleBtn);
        buttons.add(Box.createHorizontalStrut(20));
        buttons.add(analyticsBtn);


        this.add(titulo);
        this.add(Box.createVerticalStrut(10));
        this.add(tabsPanel);
        this.add(Box.createVerticalStrut(20));
        this.add(buttons);
    }

    public void update(String puzzle, String solution){
        System.out.println(puzzle + " " + solution);
        puzzleTab = new Tabuleiro(puzzle, true);
        solutionTab = new Tabuleiro(solution, puzzle, true);
        this.revalidate();
        this.repaint();
    }
}
