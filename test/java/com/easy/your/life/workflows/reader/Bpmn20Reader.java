package com.easy.your.life.workflows.reader;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.Process;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.InputStream;

/**
 * Created by marcolin on 05/05/15.
 */
public class Bpmn20Reader {
    private final BpmnModel model;
    private FlowElement startElement;

    public Bpmn20Reader(InputStream bpmn20InputStream) {
        final XMLInputFactory factory = XMLInputFactory.newInstance();
        try {
            model = new BpmnXMLConverter().convertToBpmnModel(factory.createXMLStreamReader(bpmn20InputStream));
        } catch (XMLStreamException e) {
            throw new RuntimeException("Impossible to create a XmlStreamReader");
        }
    }

    public Process getMainProcess() {
        return model.getMainProcess();
    }

    public FlowElement getProcessStart() {
        return getFlowElement(0);
    }

    public FlowElement getFlowElement(int position) {
        return getFlowElementsArray()[position];
    }

    private FlowElement[] getFlowElementsArray() {
        return getMainProcess().getFlowElements().toArray(new FlowElement[0]);
    }
}
