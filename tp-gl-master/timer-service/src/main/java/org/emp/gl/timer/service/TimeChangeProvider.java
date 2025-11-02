package org.emp.gl.timer.service;

/**
 * Interface pour les fournisseurs de notifications de changement de temps.
 * Implémente le pattern Observer : permet aux objets de s'abonner
 * et de se désabonner des notifications de changement de temps.
 *
 * @author Amine
 */
public interface TimeChangeProvider {

    /**
     * Ajoute un listener pour être notifié des changements temporels.
     *
     * @param listener le listener à ajouter
     */
    void addTimeChangeListener(TimerChangeListener listener);

    /**
     * Retire un listener pour qu'il ne soit plus notifié.
     *
     * @param listener le listener à retirer
     */
    void removeTimeChangeListener(TimerChangeListener listener);
}
