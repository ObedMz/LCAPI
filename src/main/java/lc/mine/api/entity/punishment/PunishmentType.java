package lc.mine.api.entity.punishment;

public enum PunishmentType {

    BAN,
    MUTE,
    KICK,
    WARN;

    public static PunishmentType fromString(String type) {
        for (PunishmentType punishmentType : values()) {
            if (punishmentType.name().equalsIgnoreCase(type)) {
                return punishmentType;
            }
        }
        return null;
    }
}
