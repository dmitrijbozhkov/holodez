package org.nure.services;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.nure.models.QueryResults;
import org.nure.models.UserQuery;
import org.springframework.stereotype.Service;

@Service
public class QueryService {
	
	private final KieContainer kieContainer;
	
	public QueryService(KieContainer kieContainer) {
		this.kieContainer = kieContainer;
	}
	
	public QueryResults processQuery(UserQuery query) {
		KieSession kieSession = kieContainer.newKieSession("QueryProcessingSession");
        kieSession.insert(query);
        kieSession.fireAllRules();
        QueryResults result = findResults(kieSession);
		return result;
	}
	
	public QueryResults findResults(KieSession session) {
		return new QueryResults();
	}
}
