package com.vcs.core.listeners;

import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.replication.ReplicationAction;
import com.day.cq.replication.ReplicationActionType;
import com.day.cq.replication.ReplicationEvent;
import com.day.cq.workflow.WorkflowService;
import com.day.cq.workflow.model.WorkflowModel;
import com.vcs.core.services.ResourceResolverService;
import com.day.cq.workflow.WorkflowSession;

import java.util.HashMap;
import java.util.Map;

@Component(immediate = true, service = EventHandler.class, property = {
        "event.topics=" + ReplicationEvent.EVENT_TOPIC
})
public class PageActivationListener implements EventHandler {

    private static final Logger log = LoggerFactory.getLogger(PageActivationListener.class);

    @Reference
    private WorkflowService workflowService;

    @Reference
    private ResourceResolverService resolverService;

    @Override
    public void handleEvent(Event event) {
        ReplicationAction action = ReplicationAction.fromEvent(event);
        if (action.getType().equals(ReplicationActionType.ACTIVATE)) {
            String path = action.getPath();
            log.info("Page activated: {}", path);

            try (ResourceResolver resolver = resolverService.getResourceResolver()) {
                WorkflowSession workflowSession = resolver.adaptTo(WorkflowSession.class);
                WorkflowModel workflowModel = workflowSession.getModel("/var/workflow/models/page-approval-workflow-model");

                Map<String, Object> workflowData = new HashMap<>();
                workflowData.put("payload", path);
                workflowSession.startWorkflow(workflowModel, workflowSession.newWorkflowData("JCR_PATH", path));

                log.info("Started workflow for page activation: {}", path);
            } catch (Exception e) {
                log.error("Error starting workflow for page activation: {}", path, e);
            }
        }
    }
}
