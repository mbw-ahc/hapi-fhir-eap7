package com.github.ahcmbw;

import java.util.Arrays;

import ca.uhn.fhir.rest.server.RestfulServer;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {"/*"}, displayName = "FHIR R4 entrance")
public class FhirServlet extends RestfulServer {

    @Inject
    DiagnosticReportProvider diagnosticReportProvider;

    @Override
    protected void initialize() {
        setResourceProviders(Arrays.asList(diagnosticReportProvider));
    }

}