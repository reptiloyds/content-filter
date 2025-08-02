package ru.naumov.contentfilter.pathvalidation;

import java.nio.file.InvalidPathException;
import java.util.Set;

public class PathValidator {

    private static final Set<Character> ILLEGAL_CHARS = Set.of(
            '/', '\\', '?', '*', ':', '|', '"', '<', '>', '\0'
    );

    private static final String ILLEGAL_WINDOWS_NAMES = "con|prn|aux|nul|com[1-9]|lpt[1-9]";

    public static void validateDirectionPart(String name, String source) throws IllegalArgumentException {
        checkForIllegalCharacters(name, source);
        checkForOSConstraint(name, source);
    }

    public static void validateFileNamePart(String name, String source) throws IllegalArgumentException {
        checkForIllegalCharacters(name, source);
    }

    private static void checkForIllegalCharacters(String name, String source) throws IllegalArgumentException {
        for (char c : name.toCharArray()) {
            if (ILLEGAL_CHARS.contains(c)) {
                throw new IllegalArgumentException(
                        source + " contains illegal char: '" + c + "'" + " in \"" + name + "\""
                );
            }
        }
    }

    private static void checkForOSConstraint(String name, String source) throws IllegalArgumentException {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("windows")) {
            checkForIllegalWindowsNames(name, source);
        }
    }

    private static void checkForIllegalWindowsNames(String name, String source) {
        String lower = name.toLowerCase();
        if (lower.matches(ILLEGAL_WINDOWS_NAMES)) {
            throw new IllegalArgumentException(
                    source + " contain a reserved Windows name: " + name
            );
        }
    }
}
