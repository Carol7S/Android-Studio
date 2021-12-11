package df.cn.edu.zafu;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;

public class SimpleTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Connection conn = DbConn.GetConnection();
//		if(conn != null) {
//			System.out.println("数据库连接成功");
//			System.out.println(conn);
//		}
		
		//BookBean bean = new BookBean();
		// 新增
//		BookInfo info = new BookInfo("汉书", "班固", 1800, 100.0f );
//		int count = bean.Add(info);
//		System.out.printf("result is: %d\n", count);
		
//		BookInfo info = new BookInfo("汉书", "班固, 班昭", 1800, 100.0f );
//		int count = bean.Update(info, 3);
//		System.out.printf("result is: %d\n", count);
		
//		ArrayList<BookInfo> data = bean.GetAll();
//		ArrayList<BookInfo> data = bean.GetByPagesRange(1500, 3000);
//		StringBuilder builder = new StringBuilder();
//		Iterator<BookInfo> iter = data.iterator();
//		while(iter.hasNext()) {
//			BookInfo elem = (BookInfo)iter.next();
//			System.out.printf(elem.toString() + "\n");
////			builder.append(iter.next().toString()).append("\n");
//		}
//		System.out.printf(builder.toString());

		UserBean bean1 = new UserBean();
		ArrayList<UserInfo> data1 = bean1.GetAll();
		StringBuilder builder = new StringBuilder();
		Iterator<UserInfo> iter = data1.iterator();
		while(iter.hasNext()) {
			UserInfo elem = (UserInfo)iter.next();
			System.out.printf(elem.toString() + "\n");
//			builder.append(iter.next().toString()).append("\n");
		}
		System.out.printf(builder.toString());
	}

}
