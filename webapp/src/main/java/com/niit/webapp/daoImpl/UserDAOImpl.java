
package com.niit.webapp.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.webapp.dao.UserDAO;
import com.niit.webapp.model.User;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public List<User> list() {
		String hql = "FROM User";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	@Override
	@Transactional
	public List<User> list(String status) {
		String hql = "FROM User where status = '" + status +"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	@Transactional
	public User getUser(int id) {
		return sessionFactory.getCurrentSession().get(User.class, id);
	}

	@Override
	@Transactional
	public boolean addUser(User user) {
		try {
			sessionFactory.getCurrentSession().save(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	@Transactional
	public boolean updateUser(User user) {
		try {
			sessionFactory.getCurrentSession().update(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean deleteUser(User user) {
		try {
			sessionFactory.getCurrentSession().delete(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public User getByUserName(String username) {
		String hql="FROM User WHERE username = :username";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("username", username);
		try {
			System.out.println("Fetching user");
			return (User) query.getSingleResult();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	@Override
	@Transactional
	public User validateUser(User user) {
		
		String username = user.getUsername();
		String password = user.getPassword();
		
		String hql = "FROM User where username = '" + username + "' and password = '" + password + "'";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		try {
			user = (User) query.getSingleResult();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional
	public boolean updateUserProfile(String fileName, Integer id) {
		String updateQuery = "UPDATE User SET profile = :fileName WHERE id = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(updateQuery);
		query.setParameter("id", (Integer)id);
		query.setParameter("fileName", fileName);
		try {
			query.executeUpdate();
			return true;
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}	
		return false;
	}

	@Override
	@Transactional
	public List<User> fetchOnlineFriends(int id) {
		String selectQuery = "SELECT * FROM USER_DETAILS WHERE USER_ID IN (SELECT INITIATOR_ID FROM FRIENDS WHERE (FRIEND_ID = :id OR INITIATOR_ID = :id) AND STATUS = 'APPROVED' UNION SELECT FRIEND_ID FROM FRIENDS WHERE (FRIEND_ID = :id OR INITIATOR_ID = :id) AND STATUS = 'APPROVED') AND IS_ONLINE = 1";
		
		return sessionFactory
				.getCurrentSession()
					.createNativeQuery(selectQuery,User.class)
						.setParameter("id", id)
							.getResultList();
	}

	
}
