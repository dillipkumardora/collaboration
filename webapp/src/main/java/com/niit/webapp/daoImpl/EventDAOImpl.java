package com.niit.webapp.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.webapp.dao.EventsDAO;
import com.niit.webapp.model.Events;

@Repository("eventsDAO")
public class EventDAOImpl implements EventsDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Events> list() {
		String hql = "FROM Events";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	@Override
	@Transactional
	public List<Events> getEventsByStatus(String status) {
		String hql = "FROM Events where status = '" + status +"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	@Override
	@Transactional
	public List<Events> getUserEvents(int id) {
		String hql = "FROM Events where USER_ID = '" + id +"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	@Transactional
	public Events getEvent(int id) {
		return sessionFactory.getCurrentSession().get(Events.class, id);
	}

	@Override
	@Transactional
	public boolean addEvent(Events event) {
		try {
			sessionFactory.getCurrentSession().save(event);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean updateEvent(Events event) {
		try {
			sessionFactory.getCurrentSession().update(event);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean deleteEvent(Events event) {
		try {
			sessionFactory.getCurrentSession().delete(event);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public List<Events> mainList() {
		String hql = "FROM Events where status = 'APPROVED' order by postDate desc";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(3);
		return query.list();
	}

	

}
