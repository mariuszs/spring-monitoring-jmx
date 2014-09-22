package demo;

import org.springframework.jmx.export.annotation.ManagedAttribute;

/**
 * @author Mariusz Smykuła
 */
public interface Foo {
    @ManagedAttribute(description="The Name Attribute")
    String getName();
}
