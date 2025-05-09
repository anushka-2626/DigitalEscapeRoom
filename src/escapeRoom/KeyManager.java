package escapeRoom;

/**
 * KeyManager manages the keys the player collects.
 * It ensures that keys are obtained in order and checked properly.
 */
public class KeyManager {
    private static boolean libraryKey = false;

    public static boolean hasLibraryKey() {
        return libraryKey;
    }

    public static void obtainLibraryKey() {
        libraryKey = true;
    }
}

