/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datacom.jaspermaven;

/**
 *
 * @author derek
 */
import com.jaspersoft.jasperserver.jaxrs.client.core.JasperserverRestClient;
import com.jaspersoft.jasperserver.jaxrs.client.core.RestClientConfiguration;
import com.jaspersoft.jasperserver.jaxrs.client.core.Session;
import com.jaspersoft.jasperserver.jaxrs.client.core.operationresult.OperationResult;
import java.io.*;
import java.util.Arrays;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.commons.io.IOUtils;

public class HelloJasper extends HttpServlet {

    private String message;

    @Override
    public void init() throws ServletException {
        // Do required initialization
    }

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        // Set response content type
        response.setContentType("text/html");

        // TODO code application logic here
        RestClientConfiguration configuration = RestClientConfiguration.loadConfiguration("configuration.properties");

        JasperserverRestClient client = new JasperserverRestClient(configuration);
        Session authenticate = client.authenticate("jasperadmin", "jasperadmin");
        System.out.println(configuration.getJrsVersion());

//        SessionStorage ss = new SessionStorage
//        RunReportAdapter rra = new RunReportAdapter(sessionStorage, message, message);
        OperationResult<InputStream> result = client
                .authenticate("jasperadmin", "jasperadmin")
                .reportingService()
                .report("/reports/interactive/CustomersReport")
                .prepareForRun("HTML", 1)
                .run();
        byte[] toByteArray = IOUtils.toByteArray(result.getEntity());
        System.out.println(Arrays.toString(toByteArray));
        // Actual logic goes here.
        PrintWriter out = response.getWriter();
        out.println(new String(toByteArray));
    }

    @Override
    public void destroy() {
        // do nothing.
    }
}
