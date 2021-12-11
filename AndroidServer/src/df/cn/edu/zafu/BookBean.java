package df.cn.edu.zafu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookBean {
	// 数据库连接
	private Connection conn;
	
	public BookBean() {
		
	}
	
	
	/**
	 * 添加一条记录
	 * @para: info, 书本对应的表信息
	 ***/
	public int Add( BookInfo info ){
		
		int count = -1; // 受影响的记录条数
		conn = DbConn.GetConnection();// 获得连接
	
		try{
			StringBuffer sBuffer = new StringBuffer();
			sBuffer.append( "insert into book " )
				.append( " ( name, author, pages, price ) " )
				.append( " values ( ?, ?, ?, ? ) " );
     		PreparedStatement st = conn.prepareStatement(  sBuffer.toString() );
			int i = 1;
			// name， 书名
			st.setString( i, info.getName() );
			++i;
			// author, 作者
			st.setString( i, info.getAuthor() ); 
			++i;
			// pages, 页数
			st.setInt( i, info.getPages() );
			++i;
			// price, 单价
			st.setDouble( i, info.getPrice() ); 
			
			count = st.executeUpdate( );// 执行语句
			System.out.printf( "插入%d条记录", count );
			return count;
		
		}
		catch( SQLException e ){
			System.out.printf( "插入失败:" + e.getMessage() );
			e.printStackTrace();
			return -1;
		}
		finally{
			if( conn != null ){
				try{
					conn.close();
				}
				catch( SQLException e ){
					System.out.printf( "关闭连接失败\n" + e.getMessage()  );
				}// try
			}// if
			
		}// finally
		
		
		
	}// 添加(add函数)
	
	/**
	 * 修改一条记录
	 * @param: info, 书本对应的表信息
	 * @param: id, 主键
	 ***/
	public int Update( BookInfo info, int id ){
		
		int count = -1; // 受影响的记录条数
		conn = DbConn.GetConnection();// 获得连接
	
		try{
			StringBuffer sBuffer = new StringBuffer();
			sBuffer.append( "update book " )
				.append( " set name = ?  "
						+ ", author = ? "
						+ ", pages = ? "
						+ ", price = ? "
						+ " where id = ? " );
     		PreparedStatement st = conn.prepareStatement(  sBuffer.toString() );
			int i = 1;
			// name， 书名
			st.setString( i, info.getName() );
			++i;
			// author, 作者
			st.setString( i, info.getAuthor() ); 
			++i;
			// pages, 页数
			st.setInt( i, info.getPages() );
			++i;
			// price, 单价
			st.setDouble( i, info.getPrice() ); 
			++i;
			// id, 主键
			st.setInt(i, id);
			
			count = st.executeUpdate( );// 执行语句
			System.out.printf( "修改%d条记录", count );
			return count;
		
		}
		catch( SQLException e ){
			System.out.printf( "插入失败:" + e.getMessage() );
			e.printStackTrace();
			return -1;
		}
		finally{
			if( conn != null ){
				try{
					conn.close();
				}
				catch( SQLException e ){
					System.out.printf( "关闭连接失败\n" + e.getMessage()  );
				}// try
			}// if
			
		}// finally
		
		
		
	}// 添加(update函数)
	
	/**
	 * 读取全部
	 * return: ArrayList< BookInfo >, 书籍列表
	 ***/
	public ArrayList< BookInfo > GetAll( ){
		
		conn = DbConn.GetConnection();// 取得连接 
		System.out.println(conn);
		
		ArrayList< BookInfo > list = new ArrayList< BookInfo >();
		try{
			
			StringBuffer sBuffer = new StringBuffer();
			sBuffer.append( " select id, name, author, pages, price " )
				.append( " from book " );
     		PreparedStatement st = conn.prepareStatement(  sBuffer.toString() );
     		
			
     	
			
			ResultSet rs = st.executeQuery(  );// 执行查询语句
			
			while( rs.next() ){
				BookInfo info  = GetDataFromResultSet( rs );
				list.add( info );
			}
			
			
			
		}
		catch( SQLException e ){
			System.out.printf( "数据库查询失败\n" + e.getMessage()  );
			e.printStackTrace();
		}
		finally{
			if( conn != null ){
				try{
					conn.close();
				}
				catch( SQLException e ){
					System.out.printf( "关闭连接失败\n" + e.getMessage()  );
				}// try
			}// if
			
		}// finally
		
		return list;

	} // GetAll
	
	/**
	 * 根据页码范围读取
	 * return: ArrayList< BookInfo >, 
	 ***/
	public ArrayList< BookInfo > GetByPagesRange(int minPages, int maxPages ){
		
		conn = DbConn.GetConnection();// 取得连接 
		System.out.println(conn);
		
		ArrayList< BookInfo > list = new ArrayList< BookInfo >();
		try{
			
			StringBuffer sBuffer = new StringBuffer();
			sBuffer.append( " select id, name, author, pages, price " )
				.append( " from book " )
				.append(" where pages > ? and pages < ? ");
     		PreparedStatement st = conn.prepareStatement(  sBuffer.toString() );
     		
     		int i = 1;
			// 最小页数
			st.setInt( i, minPages );
			++i;
			// 最大页数
			st.setInt( i, maxPages ); 
			++i;
     	
			
			ResultSet rs = st.executeQuery(  );// 执行查询语句
			
			
			while( rs.next() ){
				BookInfo info  = GetDataFromResultSet( rs );
				list.add( info );
			}
			
			
			
		}
		catch( SQLException e ){
			System.out.printf( "数据库查询失败\n" + e.getMessage()  );
			e.printStackTrace();
		}
		finally{
			if( conn != null ){
				try{
					conn.close();
				}
				catch( SQLException e ){
					System.out.printf( "关闭连接失败\n" + e.getMessage()  );
				}// try
			}// if
			
		}// finally
		
		return list;

	}

	
	
	/**
	 *@para: rs, 数据库集合
	 *return: BookInfo, 封装了书本表的字段
	 ***/
	private BookInfo GetDataFromResultSet( ResultSet rs ){
		
		BookInfo info = null;
		try{
					
			// id int
			int id = rs.getInt( "id" );
			// name，书名 
			String name = rs.getString( "name" ); 
			// author, 作者
			String author = rs.getString("author");
			// pages，页码
			int pages = rs.getInt( "pages" );
			// price, 单价
			double price = rs.getDouble( "price" );
			
	
			info = new BookInfo( id, name, author, pages, price );
					
			
		}catch( SQLException e ){
			System.out.printf( "数据库查询失败\n" + e.getMessage()  );
			e.printStackTrace();
		}
		
		return info;
				
	}
	
	
	
	
}
