import me.redstoner2019.Version;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- OLDER NEWER ---");
        Version v1 = Version.fromString("v1.0.2-alpha.2");
        Version v2 = Version.fromString("v1.0.2-beta.3");
        System.out.println(v1.isOlder(v2));
        v1 = new Version("v1.0.0");
        v2 = new Version("v2.0.0");
        System.out.println(v2.isOlder(v1));
    }
}
