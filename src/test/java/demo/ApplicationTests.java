package demo;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.BDDAssertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.management.Attribute;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;

import static org.assertj.core.api.BDDAssertions.then;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ApplicationTests {

    private MBeanServerConnection mBeanServerConnection;

    @Before
    public void setup() throws IOException, MalformedObjectNameException {
        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi://localhost:1099/jndi/rmi://localhost:1099/jmxrmi");

        JMXConnector connect = JMXConnectorFactory.connect(url);
        mBeanServerConnection = connect.getMBeanServerConnection();

    }

    @Test
    public void contextLoads() throws Exception {

        ObjectName objectName = new ObjectName("demo:name=foo");

        System.out.println(mBeanServerConnection.getMBeanCount());
        System.out.println(mBeanServerConnection.getMBeanInfo(objectName).toString());
        System.out.println(mBeanServerConnection.getAttribute(objectName, "Name"));
        mBeanServerConnection.setAttribute(objectName, new Attribute("Name", "foo"));

        String name = (String) mBeanServerConnection.getAttribute(objectName, "Name");

        then(name).isEqualTo("foo");


    }

}
