package javaee.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;

import java.util.*;

import model.User;

/**
 * Servlet implementation class LoginServlet
 */

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName="Online")
	private EntityManager manager;
	
	@Resource
	private UserTransaction utx;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("userName");
		String password = request.getParameter("password");
		PrintWriter out = response.getWriter();
		Query query = manager.createQuery("select c from User c where user_name = '"+name+"'");
		List results = query.getResultList();
		HttpSession session = request.getSession();
		if(results != null){
			User user = (User) results.get(0);
			if(user.getPassword().equals(password)){
				session.setAttribute("userid", user.getId());
				//øÕªß
				if(user.getType()==0){
					System.out.println(user.getPassword()+"   "+user.getType());
					response.sendRedirect("Query.jsp");
				}else if(user.getType() == 1){
					//π‹¿Ì‘±
					response.sendRedirect("AddBook.jsp");
				}
			}
			else{
				System.out.println("√‹¬Î¥ÌŒÛ");
				out.print("<script language = 'javascript'>alert('µ«¬º ß∞‹!');window.location.href='login.jsp';<script>");
			}
		}
		/**
		Iterator iter = results.iterator();
		while(iter.hasNext()){
			
		}
		try {
			utx.begin();
			manager.persist(oneUser);
			utx.commit();
		} catch (NotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeuristicMixedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeuristicRollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}**/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
