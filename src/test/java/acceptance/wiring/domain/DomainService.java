package acceptance.wiring.domain;

import acceptance.wiring.wiring.Wiring;

@SuppressWarnings("unused")
public class DomainService {

    private final Wiring wiring = new Wiring();

    public void doSomething() {
        wiring.repository().doImportantThings();
    }
}
