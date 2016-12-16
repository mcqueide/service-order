package br.com.codeshare.filter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * Created by john.clemente on 16/12/2016.
 */

@WebFilter(
        filterName = "CORS",
        initParams = @WebInitParam(
                name = "cors.supportedMethods",
                value = "GET,POST,HEAD,OPTIONS,PUT,DELETE"
        ),
        urlPatterns = "/*"
)
public class CORSFilter extends com.thetransactioncompany.cors.CORSFilter{
}
