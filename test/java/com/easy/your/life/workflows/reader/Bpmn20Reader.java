package com.easy.your.life.workflows.reader;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.SequenceFlow;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Created by marcolin on 05/05/15.
 */
public class Bpmn20Reader {
    private final BpmnModel model;

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
        return getMainProcess().getFlowElements().iterator().next();
    }

    protected List<SequenceFlow> getConnectionsOf(FlowElement element) {
        return getMainProcess().getFlowElements().stream()
                .filter(fe -> fe instanceof SequenceFlow)
                .map(fe -> (SequenceFlow) fe)
                .filter(fe -> element.getId().equals(fe.getSourceRef()))
                .collect(Collectors.toList());
    }

    public FlowElement getNextStepAfter(FlowElement element) {
        for (SequenceFlow sequenceFlow : getConnectionsOf(element)) {
            return getMainProcess().getFlowElement(sequenceFlow.getTargetRef());
        }
        return null;
    }
}
