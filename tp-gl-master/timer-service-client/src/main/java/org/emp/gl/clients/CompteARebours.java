package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

import java.beans.PropertyChangeEvent;

/**
 * Compte à rebours qui se décrémente à chaque seconde
 * et s'arrête automatiquement lorsqu'il atteint 0.
 *
 * Compatible avec PropertyChangeSupport.
 *
 * @author Amine
 */
public class CompteARebours implements TimerChangeListener {

    private int compteur;
    private final TimerService timerService;
    private boolean actif;

    /**
     * Constructeur du compte à rebours.
     *
     * @param initial      le nombre de secondes initial
     * @param timerService le service de temps
     */
    public CompteARebours(int initial, TimerService timerService) {
        this.compteur = initial;
        this.timerService = timerService;
        this.actif = true;

        // S'inscrire au service de temps
        timerService.addTimeChangeListener(this);

        System.out.println("⏳ Compte à rebours démarré avec " + initial + " secondes");
    }

    /**
     * Réagit aux changements de propriétés du service de temps.
     * Ne prend en compte que les changements de secondes.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (TimerChangeListener.SECONDE_PROP.equals(evt.getPropertyName()) && actif) {
            tick();
        }
    }

    /** Décrémente le compteur à chaque tick */
    private void tick() {
        compteur--;

        if (compteur > 0) {
            System.out.println("⏳ " + compteur + " seconde(s) restante(s)");
        } else {
            System.out.println("💥 BOOM! Compte à rebours terminé!");
            arreter();
        }
    }

    /** Arrête le compte à rebours */
    public void arreter() {
        if (actif) {
            actif = false;
            timerService.removeTimeChangeListener(this);
            System.out.println("⏹️  Compte à rebours arrêté");
        }
    }

    /** Redémarre le compte à rebours avec une nouvelle valeur */
    public void redemarrer(int nouvelleValeur) {
        if (!actif) {
            actif = true;
            compteur = nouvelleValeur;
            timerService.addTimeChangeListener(this);
            System.out.println("🔄 Compte à rebours redémarré avec " + nouvelleValeur + " secondes");
        }
    }

    /** Obtient la valeur actuelle du compteur */
    public int getCompteur() {
        return compteur;
    }

    /** Vérifie si le compte à rebours est actif */
    public boolean isActif() {
        return actif;
    }
}
