package demo;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Component
@ManagedResource(objectName = "demo:name=foo")
public class FooMBean implements Foo {

    private String name = "cool";

    @ManagedAttribute(description="Name")
    @Override
    public String getName() {
        return name;
    }

    @ManagedAttribute(description="Name",
        defaultValue="bar",
        persistPolicy="OnUpdate")
    public void setName(String name) {
        this.name = name;
    }
}
