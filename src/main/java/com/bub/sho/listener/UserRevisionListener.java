package com.bub.sho.listener;

import java.util.logging.Logger;

import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.context.SecurityContextHolder;

import com.bub.sho.entity.UserRevisionEntity;

public class UserRevisionListener implements RevisionListener {
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	/*private Authentication authentication;
	
	@Autowired
	private IAuthenticationFacade authenticationFacade;
	
	@PostConstruct
	protected void loadAuthentication(){
		authentication = authenticationFacade.getAuthentication();
	}*/
	@Override
	public void newRevision(Object revisionEntity) {
		
		UserRevisionEntity revEntity = (UserRevisionEntity) revisionEntity;

		try{
			revEntity.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		}catch(Exception e) {
			revEntity.setUsername("olee");
			logger.info("what the error revision listener"+e.getMessage());
		}
	}

}
