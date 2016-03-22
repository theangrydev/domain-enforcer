package acceptance.wiring.domain;

@SuppressWarnings("unused")
public class DomainService {

    private final Wiring wiring = new Wiring();

    public void doSomething() {
        wiring.repository().doImportantThings();
    }
}
