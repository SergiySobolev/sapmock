package com.zaelab.sapmock.config;

import com.zaelab.sapmock.endpoint.ADR2MAS03Endpoint;
import com.zaelab.sapmock.endpoint.DEBMAS06Endpoint;
import com.zaelab.sapmock.endpoint.DEBMAS07Endpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointAdapter;
import org.springframework.ws.server.EndpointMapping;
import org.springframework.ws.server.endpoint.PayloadEndpoint;
import org.springframework.ws.server.endpoint.adapter.PayloadEndpointAdapter;
import org.springframework.ws.server.endpoint.mapping.PayloadRootQNameEndpointMapping;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.util.Properties;

import static com.zaelab.sapmock.constants.SapmockConstants.*;

@EnableWs
@Configuration
public class SapMockConfig extends WsConfigurerAdapter {

    @Bean
    public EndpointMapping adr2mas03Endpoint(ADR2MAS03Endpoint adr2mas03Endpoint) {
        return createPayloadEndpoint(adr2mas03Endpoint, ADR2MAS03);
    }

    @Bean
    public EndpointMapping debmas06Endpoint(DEBMAS06Endpoint debmas06Endpoint) {
        return createPayloadEndpoint(debmas06Endpoint, DEBMAS06);
    }

    @Bean
    public EndpointMapping debmas07Endpoint(DEBMAS07Endpoint debmas07Endpoint) {
        return createPayloadEndpoint(debmas07Endpoint, DEBMAS07);
    }

    @Bean
    public EndpointAdapter messageEndpoint() {
        return new PayloadEndpointAdapter();
    }


    @Bean(name = "sapmock")
    public DefaultWsdl11Definition defaultWsdl11Definition (XsdSchema sapMockSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setLocationUri(LOCATION_URI + "/*");
        wsdl11Definition.setPortTypeName(IDOC_PORT);
        wsdl11Definition.setTargetNamespace(TARGET_NAMESPACE);
        wsdl11Definition.setSchema(sapMockSchema);
        return wsdl11Definition;
    }

    @Bean
    public SimpleXsdSchema sapMockSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schema/sapmock.xsd"));
    }

    private EndpointMapping createPayloadEndpoint(PayloadEndpoint endpoint, String idocCode) {
        PayloadRootQNameEndpointMapping em = new PayloadRootQNameEndpointMapping();
        Properties mappings = new Properties();
        mappings.put(idocCode, endpoint);
        em.setMappings(mappings);
        return em;
    }

}
