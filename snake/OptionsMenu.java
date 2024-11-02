import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnakeGame extends JFrame implements KeyListener, ActionListener {

    // Vos variables et méthodes existantes restent inchangées

    // Déclaration des composants pour le menu des options
    private JMenuItem optionsMenuItem;
    private Menu optionsMenu;

    public SnakeGame() {
        // Votre constructeur existant reste inchangé

        // Ajout du menu des options
        JMenuBar menuBar = new JMenuBar();
        JMenu optionsMenu = new JMenu("Options");
        optionsMenuItem = new JMenuItem("Options");
        optionsMenuItem.addActionListener(this);
        optionsMenu.add(optionsMenuItem);
        menuBar.add(optionsMenu);
        setJMenuBar(menuBar);

        // Initialisation du menu des options
        this.optionsMenu = new Menu(this);
    }

    // Vos autres méthodes et variables restent inchangées
}
class Menu extends JDialog implements ActionListener {

    private JComboBox<String> sizeComboBox;
    private JComboBox<String> speedComboBox;
    private JComboBox<String> colorComboBox;
    private JButton startButton;

    private SnakeGame parent;

    public Menu(SnakeGame parent) {
        super(parent, "Options", true);
        this.parent = parent;

        setSize(300, 200);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(4, 1));

        JLabel sizeLabel = new JLabel("Choose map size:");
        sizeComboBox = new JComboBox<>(new String[]{"x1", "x2", "x6", "x10"});
        add(sizeLabel);
        add(sizeComboBox);

        JLabel speedLabel = new JLabel("Choose snake speed:");
        speedComboBox = new JComboBox<>(new String[]{"Slow", "Medium", "Fast"});
        add(speedLabel);
        add(speedComboBox);

        JLabel colorLabel = new JLabel("Choose snake color:");
        colorComboBox = new JComboBox<>(new String[]{"Green", "Blue", "Yellow", "Red"});
        add(colorLabel);
        add(colorComboBox);

        startButton = new JButton("Start Game");
        startButton.addActionListener(this);
        add(startButton);
    }

    public void actionPerformed(ActionEvent e) {
        String size = (String) sizeComboBox.getSelectedItem();
        String speed = (String) speedComboBox.getSelectedItem();
        String color = (String) colorComboBox.getSelectedItem();
        parent.startGame(size, speed, color);
        dispose(); // Fermer la fenêtre de menu
    }
}

