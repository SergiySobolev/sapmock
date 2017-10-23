package com.zaelab.sapmock.endpoint;

import com.zaelab.sapmock.enums.IdocType;
import com.zaelab.sapmock.service.SapmockService;
import com.zaelab.sapmock.source.SapmockSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.server.endpoint.PayloadEndpoint;

import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;

@Component
public class ADR2MAS03Endpoint implements PayloadEndpoint {

    private static final Logger LOG = LoggerFactory.getLogger(ADR2MAS03Endpoint.class);

    private SapmockService sapmockService;

    @Autowired
    public ADR2MAS03Endpoint(SapmockService sapmockService) {
        this.sapmockService = sapmockService;
    }

    @Override
    public Source invoke(Source source) throws Exception {
        SapmockSource sapmockSource = new SapmockSource((DOMSource)source);
        String content = sapmockSource.toString();
        LOG.info("ADR2MAS03 idoc = [{}]", content);
        sapmockService.createIdoc(IdocType.ADR2MAS03, content);
        return source;
    }
}
