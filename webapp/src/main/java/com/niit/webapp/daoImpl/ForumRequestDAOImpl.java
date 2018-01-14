package com.niit.webapp.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.webapp.dao.ForumRequestDAO;
import com.niit.webapp.model.Forum;
import com.niit.webapp.model.ForumRequest;

@Repository("forumRequestDAO")
public class ForumRequestDAOImpl implements ForumRequestDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<ForumRequest> list() {
		String hql = "FROM ForumRequest";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	@Transactional
	public List<ForumRequest> list(String status) {
		String hql = "FROM ForumRequest where status = '" + status +"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	@Override
	@Transactional
	public List<ForumRequest> list(int id) {
		String hql = "FROM ForumRequest where forum = '" + id +"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	
	@Override
	@Transactional
	public ForumRequest getForumRequest(int id) {
		return sessionFactory.getCurrentSession().get(ForumRequest.class, id);
	}

	@Override
	@Transactional
	public boolean addForumRequest(ForumRequest forumRequest) {
		try {
			sessionFactory.getCurrentSession().save(forumRequest);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean updateForumRequest(ForumRequest forumRequest) {
		try {
			sessionFactory.getCurrentSession().update(forumRequest);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean deleteForumRequest(ForumRequest forumRequest) {
		try {
			sessionFactory.getCurrentSession().delete(forumRequest);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	

}
