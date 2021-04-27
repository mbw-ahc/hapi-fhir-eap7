package com.github.ahcmbw;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import ca.uhn.fhir.model.api.Include;
import ca.uhn.fhir.rest.annotation.IncludeParam;
import ca.uhn.fhir.rest.annotation.RequiredParam;
import ca.uhn.fhir.rest.annotation.Search;
import ca.uhn.fhir.rest.param.TokenParam;
import ca.uhn.fhir.rest.server.IResourceProvider;
import javax.enterprise.context.ApplicationScoped;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.instance.model.api.IIdType;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.Patient;

@ApplicationScoped
public class DiagnosticReportProvider implements IResourceProvider {

    @Override public Class<? extends IBaseResource> getResourceType() {
        return DiagnosticReport.class;
    }

    // From: https://hapifhir.io/hapi-fhir/docs/server_plain/rest_operations_search.html#resource-includes-include
    @Search()
    public List<DiagnosticReport> getDiagnosticReport(
            @RequiredParam(name = DiagnosticReport.SP_IDENTIFIER)
                    TokenParam theIdentifier,

            @IncludeParam(allow = {"DiagnosticReport:subject"})
                    Set<Include> theIncludes) {

        List<DiagnosticReport> retVal = new ArrayList<DiagnosticReport>();

        // Assume this method exists and loads the report from the DB
        DiagnosticReport report = loadSomeDiagnosticReportFromDatabase(theIdentifier);

        // If the client has asked for the subject to be included:
        if (theIncludes.contains(new Include("DiagnosticReport:subject"))) {

            // The resource reference should contain the ID of the patient
            IIdType subjectId = report.getSubject().getReferenceElement();

            // So load the patient ID and return it
            Patient subject = loadSomePatientFromDatabase(subjectId);
            report.getSubject().setResource(subject);

        }

        retVal.add(report);
        return retVal;
    }

    private Patient loadSomePatientFromDatabase(IIdType subjectId) {
        return new Patient();
    }

    private DiagnosticReport loadSomeDiagnosticReportFromDatabase(TokenParam theIdentifier) {
        return new DiagnosticReport();
    }

}
