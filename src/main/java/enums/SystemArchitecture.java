package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Funker on 31.05.2016.
 */

@AllArgsConstructor
@Getter
enum SystemArchitecture {

    ARCHITECTURE_64_BIT("64 bit"),
    ARCHITECTURE_32_BIT("32 bit");

    private String systemArchitectureName;

    public static final SystemArchitecture defaultSystemArchitecture = ARCHITECTURE_32_BIT;
    private static List<String> architecture64bitNames = Arrays.asList("amd64", "x86_64");

    public static SystemArchitecture getSystemArchitecture() {
        final String currentArchitecture = System.getProperties().getProperty("os.arch");

        SystemArchitecture result = defaultSystemArchitecture;

        if (architecture64bitNames.contains(currentArchitecture)) {
            result = ARCHITECTURE_64_BIT;
        }

        return result;
    }

}
