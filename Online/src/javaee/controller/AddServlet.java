package javaee.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Hashtable;

import javaee.ejb.AddBookRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Book;

/**
 * Servlet implementation class AddServlet
 */
@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AddBookRemote remote = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(remote == null){
			Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
		    jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		    Context context;
			try {
				context = new InitialContext(jndiProperties);
	        final String appName = "";
		    final String moduleName = "Online";
		    final String distinctName = "";         
		    System.out.println("ejb:" + appName + "/" + moduleName + "/" + distinctName +  "/AddBook!javaee.ejb.AddBookRemote");
			remote = (AddBookRemote)context.lookup("ejb:" + appName + "/" + moduleName +"/" + distinctName+ "/AddBook!javaee.ejb.AddBookRemote?stateful");
			}catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try{
			request.setCharacterEncoding("UTF-8");
			//继续添加，先加入列表
			if(request.getParameter("continueAdd") != null && request.getParameter("continueAdd").equals("继续添加")){
			Book oneBook = new Book();
			oneBook.setName(request.getParameter("bookName"));
			oneBook.setIsbn(request.getParameter("bookID"));
			oneBook.setAuthorList(request.getParameter("author"));
			oneBook.setPubDate(new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("Pubtime")).getTime()));
			oneBook.setPrice(Float.parseFloat(request.getParameter("price")));
			oneBook.setCategory(Integer.parseInt(request.getParameter("bookClass")));
			oneBook.setNum(Integer.parseInt(request.getParameter("num")));
			//oneBook.set
			remote.addToList(oneBook);
			response.sendRedirect("AddBook.jsp");
			return;
		}
		//查看已添加列表
		if(request.getParameter("showBookList") != null && request.getParameter("showBookList").equals("查看已添加列表")){
			System.out.println("showBookList");
			request.setAttribute("BookList", remote.getList());
			response.sendRedirect("BookListShow.jsp");
		}
		
		//添加结束
		if(request.getParameter("AddOver") != null && request.getParameter("AddOver").equals("退出添加")){
			System.out.println("AddOver");
			remote.insertBook();
		}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
