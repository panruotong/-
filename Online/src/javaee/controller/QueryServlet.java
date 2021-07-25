package javaee.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javaee.ejb.AddBookRemote;
import javaee.ejb.AddShoppingRemote;
import javaee.ejb.PlaceOrderRemote;
import javaee.ejb.QueryBookRemote;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.JMSException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Book;
import model.Record;
import model.User;

/**
 * Servlet implementation class QueryServlet
 */
@WebServlet("/QueryServlet")
public class QueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QueryBookRemote remote = null;
	private AddShoppingRemote shopremote = null;
	private PlaceOrderRemote orderemote = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(remote == null){
			Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
		    jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		    Context context;
			try {
				context = new InitialContext(jndiProperties);
	        final String appName = "";
		    final String moduleName = "Online";
		    final String distinctName = "";         
		    System.out.println("ejb:" + appName + "/" + moduleName + "/" + distinctName +  "/QueryBook!javaee.ejb.QueryBookRemote");
			remote = (QueryBookRemote)context.lookup("ejb:" + appName + "/" + moduleName +"/" + distinctName+ "/QueryBook!javaee.ejb.QueryBookRemote");
			}catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		request.setCharacterEncoding("UTF-8");
		if(request.getParameter("qName") != null && request.getParameter("qName").equals("书名查询")){
			List<Book> res= new ArrayList<Book>();
			res.add(remote.QueryByName(request.getParameter("bookName")));
			session.setAttribute("bookList", res);
			response.sendRedirect("Query.jsp");
		}
		
		if(request.getParameter("iName") != null && request.getParameter("iName").equals("书号查询")){
			List<Book> res= new ArrayList<Book>();
			res.add(remote.QueryByISBN(request.getParameter("bookID")));
			session.setAttribute("bookList", res);
			response.sendRedirect("Query.jsp");
		}
		
		if(request.getParameter("aName") != null && request.getParameter("aName").equals("作者查询")){
			
		}
		
		if(request.getParameter("tName") != null && request.getParameter("tName").equals("出版时间查询")){
			Timestamp btime;
			try {
				btime = new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("btime")).getTime());
				Timestamp atime = new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("atime")).getTime());
				List<Book> res = remote.QueryByTime(btime,atime);
				session.setAttribute("bookList", res);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect("Query.jsp");
		}
		
		if(request.getParameter("uName") != null && request.getParameter("uName").equals("价格查询")){
			List<Book> res = remote.QueryByPrice(Float.parseFloat(request.getParameter("dprice")),Float.parseFloat(request.getParameter("uprice")));
			session.setAttribute("bookList", res);
			response.sendRedirect("Query.jsp");
		}
		if(request.getParameter("cName") != null && request.getParameter("cName").equals("类别查询")){
			List<Book> res = remote.QueryByCategory(Integer.parseInt(request.getParameter("bookClass")));
			session.setAttribute("bookList", res);
			response.sendRedirect("Query.jsp");
		}
		//查看订单
		if(request.getParameter("op")!= null && request.getParameter("op").equals("o")){
			//List<Record> orderList= (List<Record>) manager.find(User.class, session.getAttribute("userid"));
			request.getRequestDispatcher("/OrderServlet").forward(request, response);
			//session.setAttribute("orderList", orderList);
			//response.sendRedirect("Query.jsp");
		}
		//System.out.println("::"+request.getParameter("shopid"));
		if(request.getParameter("op")!= null && request.getParameter("op").equals("shopping")){
			if(shopremote == null){
				Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
			    jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			    Context context;
				try {
					context = new InitialContext(jndiProperties);
		        final String appName = "";
			    final String moduleName = "Online";
			    final String distinctName = "";         
			    System.out.println("ejb:" + appName + "/" + moduleName + "/" + distinctName +  "/AddShopping!javaee.ejb.AddShoppingRemote");
			    shopremote = (AddShoppingRemote)context.lookup("ejb:" + appName + "/" + moduleName +"/" + distinctName+ "/AddShopping!javaee.ejb.AddShoppingRemote?stateful");
				}catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			shopremote.add(remote.QueryByID(Integer.parseInt(request.getParameter("shopid"))));
			response.sendRedirect("Query.jsp");
			return;
		}
		if(request.getParameter("showShop") != null && request.getParameter("showShop").equals("查看购物车")){
			//System.out.println(shopremote.getShopping().get(0).getName());
			session.setAttribute("shopList", shopremote.getShopping());
			response.sendRedirect("ShowShopping.jsp");
			System.out.println("查看购物车");
			return;
		}
		//下单
		if(request.getParameter("sure") != null && request.getParameter("sure").equals("下单")){
			if(orderemote == null){
				Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
			    jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			    Context context;
				try {
					context = new InitialContext(jndiProperties);
		        final String appName = "";
			    final String moduleName = "Online";
			    final String distinctName = "";         
			    System.out.println("ejb:" + appName + "/" + moduleName + "/" + distinctName +  "/PlaceOrder!javaee.ejb.PlaceOrderRemote");
			    orderemote = (PlaceOrderRemote)context.lookup("ejb:" + appName + "/" + moduleName +"/" + distinctName+ "/PlaceOrder!javaee.ejb.PlaceOrderRemote?stateful");
				}catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Record oneRecord = new Record();
			oneRecord.setBookList(shopremote.getShopping());
			oneRecord.setUser(orderemote.findUser((Integer) session.getAttribute("userid")));
			orderemote.add(oneRecord);
			session.setAttribute("orderid",oneRecord.getId());
			response.sendRedirect("PlaceOrder.jsp");
		}
		if(request.getParameter("buy")!= null && request.getParameter("buy").equals("确认下单")){
			int orderid = (Integer) session.getAttribute("orderid");
			Record myrecord = orderemote.getOrder(orderid);
			myrecord.setAddress(request.getParameter("address"));
			try {
				myrecord.setTime(new Date(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("time")).getTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			myrecord.setPhone(request.getParameter("phone"));
			myrecord.setRevName(request.getParameter("receiver"));
			orderemote.submit(myrecord);
			SendOrderInfo(myrecord);
			response.sendRedirect("Query.jsp");
		}
		return;	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}
	
	private void SendOrderInfo(Record oneRecord){
		final Logger log = Logger.getLogger(QueryServlet.class.getName());

		final String DEFAULT_MESSAGE = "Welcome to JMS queue!";
		final String DEFAULT_CONNECTION_FACTORY = "java:/RemoteConnectionFactory";
		final String DEFAULT_DESTINATION = "java:/queue/test";
		final String DEFAULT_MESSAGE_COUNT = "1";

		final String DEFAULT_USERNAME = "test";
		final String DEFAULT_PASSWORD = "123456";
		final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
		final String PROVIDER_URL = "remote://localhost:4447";

		Context context=null;
		Connection connection=null;
		try {
				// 设置上下文的JNDI查找
				System.out.println("设置JNDI访问环境信息也就是设置应用服务器的上下文信息!");
				final Properties env = new Properties();
				env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);// 该KEY的值为初始化Context的工厂类,JNDI驱动的类名
				env.put(Context.PROVIDER_URL,  PROVIDER_URL);// 该KEY的值为Context服务提供者的URL.命名服务提供者的URL
				env.put(Context.SECURITY_PRINCIPAL, DEFAULT_USERNAME);
				env.put(Context.SECURITY_CREDENTIALS, DEFAULT_PASSWORD);//应用用户的登录名,密码.
				// 获取到InitialContext对象.
				context = new InitialContext();
				System.out.println ("获取连接工厂!");
				ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup(DEFAULT_CONNECTION_FACTORY);
				System.out.println ("获取目的地!");
				Destination destination = (Destination) context.lookup(DEFAULT_DESTINATION);

				// 创建JMS连接、会话、生产者和消费者
				connection = connectionFactory.createConnection(DEFAULT_USERNAME, DEFAULT_PASSWORD);
				Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
				MessageProducer producer = session.createProducer(destination);
				connection.start();

				int count = Integer.parseInt(DEFAULT_MESSAGE_COUNT);
				// 发送订单消息
				TextMessage message = null;
				StringBuffer s = new StringBuffer();
				s.append("订单号 "+oneRecord.getId());
				s.append("配送地址 "+oneRecord.getAddress());
				s.append("配送时间 "+oneRecord.getTime());
				s.append("收件人姓名 "+oneRecord.getRevName());
				s.append("收件人电话 "+oneRecord.getPhone());
				for(Book b:oneRecord.getBookList()){
					s.append("购买图书  "+b.getName());
				}
				message.setText(s.toString());
				producer.send(message);
				// 等待30秒退出
				CountDownLatch latch = new CountDownLatch(1);
				latch.await(30, TimeUnit.SECONDS);
				
			} catch (Exception e) {
				log.severe(e.getMessage());
				try {
					throw e;
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} finally {
				if (context != null) {
					try {
						context.close();
					} catch (NamingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				// 关闭连接负责会话,生产商和消费者
				if (connection != null) {
					try {
						connection.close();
					} catch (JMSException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
}
