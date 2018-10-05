package com.bub.sho.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bub.sho.entity.Comments;
import com.bub.sho.entity.Priority;
import com.bub.sho.entity.Status;
import com.bub.sho.entity.Ticket;
import com.bub.sho.entity.UserRevisionEntity;
import com.bub.sho.model.HistoryTracker;

@Repository
public class TicketDaoImpl implements TicketDao {
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(Ticket ticket) {

		Session currentSession = sessionFactory.getCurrentSession();
		/*if(ticket.getComment()!=null) {
			Comments comment = new Comments();
			comment.setContent(ticket.getComment());
			ticket.addComment(comment);
			currentSession.save(comment);
		}*/
		currentSession.saveOrUpdate(ticket);
		
	}

	@Override
	public Priority findPrioritybyValue(Integer val) {

		Session currentSession = sessionFactory.getCurrentSession();
		Query<Priority> theQuery = currentSession.createQuery("from Priority where value=:thePriority", Priority.class);
		theQuery.setParameter("thePriority",val);
		
		Priority thePriority = null;
		
		logger.info("qwertyuiop"+thePriority);

		
		try {
			thePriority = theQuery.getSingleResult();
		} catch (Exception e) {
			logger.info("error in ticketdao"+e.getMessage());
			thePriority = null;
		}
		
		logger.info("qwertyuiopafter"+thePriority);

		
		return thePriority;
	}

	@Override
	public Status findStatusbyName(String name) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Status> theQuery = currentSession.createQuery("from Status where value=:statusS", Status.class);
		theQuery.setParameter("statusS", name);
		
		Status status = null;
		
		try {
			status = theQuery.getSingleResult();
		} catch (Exception e) {
			status = null;
		}
		
		
		return status;
	}

	@Override
	public List<Ticket> getTickets() {

		Session currentSession = sessionFactory.getCurrentSession();
		Query<Ticket> theQuery = currentSession.createQuery("from Ticket order by createdOn desc",Ticket.class);
		theQuery.setMaxResults(10);
		List<Ticket> tickets = null;
		try {
			tickets = theQuery.getResultList();
		}catch(Exception e) {
			tickets=null;
		}
		return tickets;
	}

	@Override
	public List<Ticket> getTickets(String keyWord) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Ticket> theQuery = currentSession.createQuery("from Ticket where createdBy.userName=:keyWord or assignedTo.userName=:keyWord"
				+ " or priority.value=:keyWord or status.value=:keyWord or bug like :keyWor",Ticket.class);
		theQuery.setParameter("keyWord",keyWord);
		theQuery.setParameter("keyWor", "%"+keyWord+"%");
		List<Ticket> tickets = null;
		try {
			tickets = theQuery.getResultList();
		}catch(Exception e) {
			tickets=null;
		}
		return tickets;
	}

	@Override
	public Ticket getTicket(long theId) {

		Session currentSession = sessionFactory.getCurrentSession();
		Ticket ticket = currentSession.get(Ticket.class, theId);
		Hibernate.initialize(ticket.getComments());
		return ticket;
	}

	@Override
	public void deleteTicket(long theId) {

		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery = 
				currentSession.createQuery("delete from Ticket where id=:ticketId");
		theQuery.setParameter("ticketId", theId);
		theQuery.executeUpdate();		
	}

	@Override
	public List<Priority> findAllPriorities() {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Priority> theQuery = currentSession.createQuery("from Priority", Priority.class);
		
		List<Priority> priority = null;
		
		try {
			priority = theQuery.getResultList();
		} catch (Exception e) {
			priority = null;
		}
		
		
		return priority;
	}

	@Override
	public List<Status> findAllStatuses() {

		Session currentSession = sessionFactory.getCurrentSession();
		Query<Status> theQuery = currentSession.createQuery("from Status", Status.class);
		
		List<Status> status = null;
		
		try {
			status = theQuery.getResultList();
		} catch (Exception e) {
			status = null;
		}
		
		
		return status;
	}

	@Override
	public void save(@Valid Ticket ticket, String comment) {
		Session currentSession = sessionFactory.getCurrentSession();
		Hibernate.initialize(ticket.getComments());
		if(comment!=null) {
			//ticket = getTicket(ticket.getId());
			logger.info("what the error comments "+ticket.getComments());
			Comments nComment = new Comments();
			nComment.setContent(comment);
			nComment.setTicketId(ticket);;
			ticket.addComment(nComment);
			currentSession.save(nComment);
		}
		currentSession.saveOrUpdate(ticket);		
	}

	@Override
	public List<HistoryTracker> getTicketHistory(long theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		AuditReader reader = AuditReaderFactory.get(currentSession);
		List<Object[]> historyList = null;
		List<HistoryTracker> hisList=null;
		AuditQuery query = reader.createQuery().forRevisionsOfEntity(Ticket.class,false,true).add(AuditEntity.id().eq(theId));
		try {
			//logger.info("list ticketdao history "+query.getResultList());
			historyList = query.getResultList();
			logger.info("list ticketdao history "+historyList);
			hisList = new ArrayList<HistoryTracker>();
			for(Object[] obj:historyList) {
				hisList.add(new HistoryTracker((Ticket)obj[0],(UserRevisionEntity)obj[1],(String)((RevisionType)obj[2]).toString()));
				logger.info("list ticketdao history " + (Ticket)obj[0]);
				logger.info("list ticketdao history " + (UserRevisionEntity)obj[1]);
				logger.info("list ticketdao history " + (RevisionType)obj[2]);
			}
			
		}catch(Exception e) {
			logger.info("error in ticketdao history "+e.getMessage());
			hisList = null;
		}
		return hisList;
	}

}
