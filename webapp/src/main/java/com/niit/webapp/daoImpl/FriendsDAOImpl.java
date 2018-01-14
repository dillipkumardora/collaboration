package com.niit.webapp.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.webapp.dao.FriendsDAO;
import com.niit.webapp.model.Blog;
import com.niit.webapp.model.Friends;
import com.niit.webapp.model.User;

@Repository("friendsDAO")
@Transactional
public class FriendsDAOImpl implements FriendsDAO{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Friends> list() {
		String hql = "FROM Friends";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	@Override
	public List<Friends> list(int id) {
		String hql = "FROM Friends where friendId = :id ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		return query.list();
	}
	
	
	
	
	@Override
	public List<Friends> list(String status) {
		String hql = "FROM Friends where status = '" + status +"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public Friends getFriend(int id) {
		return sessionFactory.getCurrentSession().get(Friends.class, id);
	}

	@Override
	public boolean addFriend(Friends friends) {
		try {
			sessionFactory.getCurrentSession().save(friends);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateFriend(Friends friends) {
		try {
			sessionFactory.getCurrentSession().update(friends);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteFriend(Friends friends) {
		try {
			sessionFactory.getCurrentSession().delete(friends);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<User> noFriends(int id) {
		String selectQuery = "SELECT * FROM USER_DETAILS WHERE USER_ID NOT IN (SELECT INITIATOR_ID FROM FRIENDS WHERE FRIEND_ID = :id OR INITIATOR_ID = :id UNION SELECT FRIEND_ID FROM FRIENDS WHERE FRIEND_ID = :id OR INITIATOR_ID = :id) AND STATUS = 'APPROVED'";
		
		return sessionFactory
				.getCurrentSession()
					.createNativeQuery(selectQuery,User.class)
						.setParameter("id", id)
							.getResultList();
		
	}

	@Override
	public List<User> myFriends(int id) {
		String selectQuery = "SELECT * FROM USER_DETAILS WHERE USER_ID IN (SELECT INITIATOR_ID FROM FRIENDS WHERE (FRIEND_ID = :id OR INITIATOR_ID = :id) AND STATUS = 'APPROVED' UNION SELECT FRIEND_ID FROM FRIENDS WHERE (FRIEND_ID = :id OR INITIATOR_ID = :id) AND STATUS = 'APPROVED')";
		return sessionFactory
				.getCurrentSession()
					.createNativeQuery(selectQuery,User.class)
						.setParameter("id", id)
							.getResultList();
	}

	
	
	

}
