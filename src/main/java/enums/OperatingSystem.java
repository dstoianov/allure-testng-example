package enums;

/**
 * Created by denys.stoianov on 16/02/16
 * Email: denys.stoianov@auto1.com
 */
public enum OperatingSystem {

    WINDOWS("windows"),
    MAC("mac"),
    LINUX("linux");

    private String operatingSystemName;

    OperatingSystem(String operatingSystemName) {
        this.operatingSystemName = operatingSystemName;
    }

    String getOperatingSystemType() {
        return operatingSystemName;
    }

    public static OperatingSystem getOperatingSystem() {

        String name = System.getProperties().getProperty("os.name");

        for (OperatingSystem operatingSystemName : values()) {
            if (name.toLowerCase().contains(operatingSystemName.getOperatingSystemType())) {
                return operatingSystemName;
            }
        }

        throw new IllegalArgumentException("Unrecognised operating system name '" + name + "'");
    }
}
