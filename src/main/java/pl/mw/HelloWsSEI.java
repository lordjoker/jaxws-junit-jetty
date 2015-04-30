package pl.mw;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created by Joker on 2015-04-29.
 */
@WebService(targetNamespace = "http://pl.mw.services")
public interface HelloWsSEI {

    String sayHi(@WebParam(name = "name") String name);

}
