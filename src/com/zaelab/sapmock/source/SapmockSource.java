package com.zaelab.sapmock.source;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

public class SapmockSource implements Source {
    private DOMSource domSource;
    private String systemId;

    public SapmockSource(DOMSource domSource) {
        this.domSource = domSource;
    }
    @Override
    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    @Override
    public String getSystemId() {
        return this.systemId;
    }

    @Override
    public String toString() {
        try {
            StringWriter writer = new StringWriter();
            Transformer transformer2 = TransformerFactory.newInstance().newTransformer();
            transformer2.transform(domSource, new StreamResult(writer));
            return writer.toString();
        } catch (TransformerException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
