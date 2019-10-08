package se.techinsight.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by denys.stoianov on 16/02/16
 */
@AllArgsConstructor
@Getter
public enum OperatingSystem {

    WINDOWS("windows"),
    MAC("mac"),
    LINUX("linux");

    private String operatingSystemName;



    public static OperatingSystem getOperatingSystem() {

        String name = System.getProperties().getProperty("os.name");

        for (OperatingSystem operatingSystemName : values()) {
            if (name.toLowerCase().contains(operatingSystemName.getOperatingSystemName())) {
                return operatingSystemName;
            }
        }

        throw new IllegalArgumentException("Unrecognised operating system name '" + name + "'");
    }
}
