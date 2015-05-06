package com.easy.your.life.workflows.reader;

import org.activiti.bpmn.model.FlowElement;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Created by marcolin on 05/05/15.
 */
public class Bpmn20ReaderTest {

    @Test
    public void readProcessFromBpm20File() {
        assertNotNull(getBpmn20ReaderForOneStepWorkflow().getMainProcess());
    }

    @Test
    public void getStartFromProcess() {
        FlowElement start = getBpmn20ReaderForOneStepWorkflow().getProcessStart();
        assertNotNull(start);
        assertEquals("start-id", start.getId());
        assertEquals("start-name", start.getName());
    }

    @Test
    public void getNextStepFrom() {
        FlowElement element = getBpmn20ReaderForOneStepWorkflow().getFlowElement(2);
        assertNotNull(element);
        assertEquals("start-first-step-id", element.getId());
        assertEquals("start-first-step-name", element.getName());
    }

    private Bpmn20Reader getBpmn20ReaderForOneStepWorkflow() {
        return new Bpmn20Reader(Bpmn20ReaderTest.class.getClassLoader().getResourceAsStream("oneStepWorkflow.bpmn20.xml"));
    }

}