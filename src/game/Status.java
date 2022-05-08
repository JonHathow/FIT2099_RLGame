package game;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
    HOSTILE_TO_ENEMY, // use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
    TALL, // use this status to tell that current instance has "grown".
    DESTROY_SHELL, // use this status to allows actors to destroy Koopa's shells
    DORMANT, // use this status to tell that Koopa is dormant(hiding in shell)
    INVINCIBLE, // use this status to tell that Actor is invincible
    FERTILE, //Ground capability for new trees (sprouts) to spawn on it.
    SAVE_PRINCESS,

    CAN_FLY,

    POWER_UP,
}
