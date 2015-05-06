package com.easy.your.life.workflow;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;

/**
 * Created by marcolin on 05/05/15.
 */
public class Bpm20Reader {

    public static void main(String[] args) throws XMLStreamException {
        final XMLInputFactory factory = XMLInputFactory.newInstance();
        InputStream inputFile = Bpm20Reader.class.getClassLoader().getResourceAsStream("test/resources/customer.bpmn20.xml");
        XMLStreamReader xmlStreamReader = factory.createXMLStreamReader(inputFile);
        BpmnModel model = new BpmnXMLConverter().convertToBpmnModel(xmlStreamReader);
        model.getProcesses().get(0).getFlowElements();
    }
}
