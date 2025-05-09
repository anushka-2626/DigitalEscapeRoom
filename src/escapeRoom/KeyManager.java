package escapeRoom;

/**
 * KeyManager manages the keys the player collects.
 * It ensures that keys are obtained in order and checked properly.
 */
public class KeyManager {
    private static boolean libraryKey = false;
    private static boolean jailkey=false;

    public static boolean hasLibraryKey() {
        return libraryKey;
    }

    public static void obtainLibraryKey() {
        libraryKey = true;
    }

    public static boolean hasJailKey() {
        return jailkey;
    }

    public static void obtainJailKey() {
        jailkey = true;
    }

}

