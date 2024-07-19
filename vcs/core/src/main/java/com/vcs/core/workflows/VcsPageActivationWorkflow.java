package com.vcs.core.workflows;

import javax.jcr.Session;

import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.day.cq.replication.ReplicationActionType;
import com.day.cq.replication.ReplicationException;
import com.day.cq.replication.Replicator;
import com.vcs.core.services.ResourceResolverService;

@Component(service = WorkflowProcess.class, property = {"process.label=Vcs PageActivation Process"}, immediate = true)
public class VcsPageActivationWorkflow implements WorkflowProcess {
    private static final Logger LOG = LoggerFactory.getLogger(VcsPageActivationWorkflow.class);

    @Reference
    Replicator replicator;

    @Reference
    ResourceResolverService resolverService;

    @Override
    public void execute(WorkItem wItem, WorkflowSession workflowSession, MetaDataMap wDataMap) throws WorkflowException {
        try (ResourceResolver resolver = resolverService.getResourceResolver()) {
            String payload = wItem.getWorkflowData().getPayload().toString();
            Session session = resolver.adaptTo(Session.class);
            try {
                replicator.replicate(session, ReplicationActionType.ACTIVATE, payload);
                LOG.info("Successfully replicated page: {}", payload);
            } catch (ReplicationException e) {
                LOG.error("Error while activating the page: {}", payload, e);
            }
        } catch (Exception e) {
            LOG.error("Error obtaining resource resolver", e);
        }
    }
}
