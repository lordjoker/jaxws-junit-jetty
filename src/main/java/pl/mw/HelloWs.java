package pl.mw;

import javax.jws.WebService;

/**
 * Created by Joker on 2015-04-29.
 */
@WebService(targetNamespace = "http://pl.mw.services")
public class HelloWs implements HelloWsSEI {

    public String sayHi(String name) {
        return "Hello " + name + "!";
    }
}
