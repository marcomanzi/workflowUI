package com.easy.your.life.workflow.reader;

import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.SequenceFlow;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;
/**
 * Created by marcolin on 05/05/15.
 */
public class Bpmn20ReaderTest {

    private static Bpmn20Reader startStepEnd = new Bpmn20Reader(inputStreamFor("start-step-end.bpmn20.xml"));

    @Test
    public void readProcessFromBpm20File() {
        assertNotNull(startStepEnd.getMainProcess());
    }

    @Test
    public void getStartFromProcess() {
        assertIdIs("1", startElementForTest());
    }

    @Test
    public void getConnectionsForStartStep() {
        List<SequenceFlow> connections = startStepEnd.getConnectionsOf(startElementForTest());
        assertIdIs("2", connections.get(0));
    }

    @Test
    public void getNextStepFromStart() {
        assertIdIs("3", startStepEnd.getNextStepAfter(startElementForTest()));
    }

    private void assertIdIs(String expectedId, FlowElement element) {
        assertNotNull(element);
        assertEquals(expectedId, element.getId());
    }

    private FlowElement startElementForTest() {
        return startStepEnd.getProcessStart();
    }

    private static InputStream inputStreamFor(String fileName) {
        return Bpmn20ReaderTest.class.getClassLoader().getResourceAsStream(fileName);
    }

}