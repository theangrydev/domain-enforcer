package acceptance.realworld.userinterface;

import acceptance.realworld.wiring.Wiring;

public class ApplicationLauncher {

    public static void main(String... args) {
        Application application = new Application(new Wiring());
        application.start();
    }
}
