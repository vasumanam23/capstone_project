package com.vcs.core.servicesimpl;

import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vcs.core.services.ResourceResolverService;

@Component(service = ResourceResolverService.class)
public class ResourceResolverServiceImpl implements ResourceResolverService {
    private static final Logger LOG = LoggerFactory.getLogger(ResourceResolverService.class);

    @Reference
    private ResourceResolverFactory resolverFactory;

    @Override
    public ResourceResolver getResourceResolver() {
        Map<String, Object> serviceUserMap = new HashMap<>();
        serviceUserMap.put(ResourceResolverFactory.SUBSERVICE, "test-user");
        ResourceResolver resolver = null;
        try {
            resolver = resolverFactory.getServiceResourceResolver(serviceUserMap);
            if (resolver == null) {
                LOG.error("Failed to obtain ResourceResolver: resolver is null");
            }
        } catch (LoginException e) {
            LOG.error("Error while getting the resolver using subservice of name {}, error: {}", "mysite-user", e);
        }
        return resolver;
    }
}
