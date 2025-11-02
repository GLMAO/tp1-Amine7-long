package org.emp.gl.timer.service;

/**
 * Interface du service de temps.
 * <p>
 * Fournit les informations temporelles actuelles (heures, minutes,
 * secondes, dixièmes de seconde) et permet à des observateurs
 * de s'abonner pour être notifiés de tout changement.
 * </p>
 *
 * Pattern Observer : les classes implémentant cette interface
 * agissent comme des "sujets" qui notifient leurs observateurs
 * à chaque variation du temps.
 *
 * @author Amine
 */
public interface TimerService extends TimeChangeProvider {

    /**
     * Retourne les minutes actuelles.
     *
     * @return les minutes (0–59)
     */
    int getMinutes();

    /**
     * Retourne les heures actuelles.
     *
     * @return les heures (0–23)
     */
    int getHeures();

    /**
     * Retourne les secondes actuelles.
     *
     * @return les secondes (0–59)
     */
    int getSecondes();

    /**
     * Retourne les dixièmes de seconde actuels.
     *
     * @return les dixièmes de seconde (0–9)
     */
    int getDixiemeDeSeconde();
}
