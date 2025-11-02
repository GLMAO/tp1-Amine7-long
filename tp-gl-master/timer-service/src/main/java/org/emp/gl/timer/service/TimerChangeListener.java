package org.emp.gl.timer.service;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Interface pour écouter les changements de temps.
 * Implémente le pattern Observer via PropertyChangeListener.
 *
 * Chaque listener reçoit des événements contenant le nom
 * de la propriété (seconde, minute, heure, dixième de seconde)
 * et les valeurs avant/après.
 *
 * @author Amine
 */
public interface TimerChangeListener extends PropertyChangeListener {

    /** Constante pour la propriété "dixième de seconde" */
    String DIXEME_DE_SECONDE_PROP = "dixieme";

    /** Constante pour la propriété "seconde" */
    String SECONDE_PROP = "seconde";

    /** Constante pour la propriété "minute" */
    String MINUTE_PROP = "minute";

    /** Constante pour la propriété "heure" */
    String HEURE_PROP = "heure";

    /**
     * Méthode appelée automatiquement lorsqu’une propriété du temps change.
     * Elle reçoit un PropertyChangeEvent contenant :
     *   - le nom de la propriété (prop)
     *   - l’ancienne valeur (oldValue)
     *   - la nouvelle valeur (newValue)
     *
     * @param evt événement de changement de propriété
     */
    @Override
    void propertyChange(PropertyChangeEvent evt);
}
