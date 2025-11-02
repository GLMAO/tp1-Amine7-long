package org.emp.gl.clients;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.emp.gl.timer.service.TimerService;
import org.emp.gl.timer.service.TimerChangeListener;

/**
 * Horloge graphique simple qui affiche l'heure en temps réel.
 * Utilise Swing pour l'interface graphique et le pattern Observer via PropertyChangeSupport.
 *
 * @author Amine
 */
public class HorlogeGUI extends JFrame implements TimerChangeListener {

    private final JLabel timeLabel;
    private final JLabel dateLabel;
    private final JLabel nameLabel;
    private final SimpleDateFormat timeFormat;
    private final SimpleDateFormat dateFormat;
    private TimerService timerService;
    private final String name;

    /**
     * Constructeur avec nom personnalisé.
     *
     * @param name          le nom de l'horloge
     * @param timerService  le service de temps
     */
    public HorlogeGUI(String name, TimerService timerService) {
        this.name = name;
        this.timerService = timerService;
        this.timeFormat = new SimpleDateFormat("HH:mm:ss");
        this.dateFormat = new SimpleDateFormat("EEEE dd MMMM yyyy");

        // Labels
        this.nameLabel = new JLabel(name, SwingConstants.CENTER);
        this.timeLabel = new JLabel("00:00:00", SwingConstants.CENTER);
        this.dateLabel = new JLabel("", SwingConstants.CENTER);

        // Configuration graphique
        initializeGUI();

        // S'abonner au service de temps
        if (timerService != null) {
            timerService.addTimeChangeListener(this);
            updateTime(); // Mise à jour initiale
        }
    }

    /** Constructeur simple (nom par défaut). */
    public HorlogeGUI(TimerService timerService) {
        this("Horloge", timerService);
    }

    /** Initialise la fenêtre Swing. */
    private void initializeGUI() {
        setTitle("🕒 " + name);
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(30, 30, 40));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Titre
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        nameLabel.setForeground(new Color(150, 150, 160));
        mainPanel.add(nameLabel, BorderLayout.NORTH);

        // Centre : heure + date
        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 0, 10));
        centerPanel.setOpaque(false);

        timeLabel.setFont(new Font("Consolas", Font.BOLD, 56));
        timeLabel.setForeground(new Color(0, 255, 150));
        centerPanel.add(timeLabel);

        dateLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        dateLabel.setForeground(new Color(120, 180, 150));
        centerPanel.add(dateLabel);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Statut
        JLabel statusLabel = new JLabel("● EN LIGNE", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        statusLabel.setForeground(new Color(0, 255, 100));
        mainPanel.add(statusLabel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    /** Met à jour l'affichage de l'heure et de la date. */
    private void updateTime() {
        Date now = new Date();
        timeLabel.setText(timeFormat.format(now));
        dateLabel.setText(dateFormat.format(now));
    }

    /**
     * Réagit aux changements du service de temps.
     * Met à jour l'affichage uniquement quand la seconde change.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (TimerChangeListener.SECONDE_PROP.equals(evt.getPropertyName())) {
            SwingUtilities.invokeLater(this::updateTime);
        }
    }

    /** Change le service de temps observé. */
    public void setTimerService(TimerService service) {
        if (this.timerService != null) {
            this.timerService.removeTimeChangeListener(this);
        }
        this.timerService = service;
        if (service != null) {
            service.addTimeChangeListener(this);
            updateTime();
        }
    }

    /** Retourne le nom de l'horloge. */
    public String getName() {
        return name;
    }

    /** Ferme proprement la fenêtre (désinscription automatique). */
    @Override
    public void dispose() {
        if (timerService != null) {
            timerService.removeTimeChangeListener(this);
        }
        super.dispose();
    }
}
