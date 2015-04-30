package pl.mw;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.apache.cxf.message.MessageContentsList;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.xml.ws.Endpoint;

/**
 * Created by Joker on 2015-04-29.
 */
public class JaxWSTest extends CamelSpringTestSupport {

    @Produce(uri = "direct:start")
    private ProducerTemplate producerTemplate;
    @EndpointInject(uri = "mock:end")
    private MockEndpoint mockEndpoint;

    @BeforeClass
    public static final void setup() {
        Endpoint.publish("http://localhost:8888/services/Hello", new HelloWs());
    }

    @Test
    public void test() throws Exception {
        //given
        String name = "Martin";
        mockEndpoint.expectedMessageCount(1);
        //when
        producerTemplate.sendBody(name);
        //then
        mockEndpoint.assertIsSatisfied();
        MessageContentsList body = (MessageContentsList) mockEndpoint.assertExchangeReceived(0).getIn().getBody();
        assertEquals("Hello Martin!", body.get(0));
    }

    @Override
    protected AbstractApplicationContext createApplicationContext() {
        return new ClassPathXmlApplicationContext("/META-INF/spring/test-context.xml");
    }
}
