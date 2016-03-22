package io.github.theangrydev.domainenforcer.examples.b.domain;

import java.util.Collection;
import java.util.Collections;

@SuppressWarnings("unused")
public class DomainClassThatUsesStandardLibraries {

    public void doStuffWithStandardLibraries() {
        Collection<String> collection = Collections.emptyList();
        System.out.println("Look at my empty collection: " + collection);
    }
}
