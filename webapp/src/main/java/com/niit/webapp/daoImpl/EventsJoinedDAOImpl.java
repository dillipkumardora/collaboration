package com.niit.webapp.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.webapp.dao.EventsJoinedDAO;
import com.niit.webapp.model.EventJoined;
import com.niit.webapp.model.Events;
import com.niit.webapp.model.User;

@Repository("eventsJoinedDAO")
public class EventsJoinedDAOImpl implements EventsJoinedDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<EventJoined> list() {
			String hql = "FROM EventJoined";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			return query.list();
	}
	
	@Override
	@Transactional
	public List<EventJoined> list(int id) {
		String hql = "FROM EventJoined where userId = '" + id +"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	@Transactional
	public EventJoined getEventJoined(int id) {
		return sessionFactory.getCurrentSession().get(EventJoined.class, id);
	}

	@Override
	@Transactional
	public boolean addEventJoined(EventJoined eventJoined) {
		try {
			sessionFactory.getCurrentSession().save(eventJoined);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean updateEventJoined(EventJoined eventJoined) {
		try {
			sessionFactory.getCurrentSession().update(eventJoined);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean deleteEventJoined(EventJoined eventJoined) {
		try {
			sessionFactory.getCurrentSession().delete(eventJoined);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
