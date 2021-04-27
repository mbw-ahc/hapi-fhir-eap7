call mvnw.cmd clean install

@ECHO ON
DEL jboss-eap-7.3\standalone\deployments\hapi-fhir-eap7.war.*
COPY target\hapi-fhir-eap7.war jboss-eap-7.3\standalone\deployments\hapi-fhir-eap7.war
echo > jboss-eap-7.3\standalone\deployments\hapi-fhir-eap7.war.dodeploy
