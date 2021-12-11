package df.cn.edu.zafu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserBean {
    // 数据库连接
    private Connection conn;

    public UserBean() {

    }

//    添加用户
    public int Add(UserInfo info){
        int count = -1; // 受影响的记录条数
        conn = DbConn.GetConnection();// 获得连接
        try{
            StringBuffer sBuffer = new StringBuffer();
            sBuffer.append( "insert into user " )
                    .append( " ( username, password, permission ) " )
                    .append( " values ( ?, ?, ? ) " );
            PreparedStatement st = conn.prepareStatement(  sBuffer.toString() );
            int i = 1;
            // username， 书名
            st.setString( i, info.getUsername() );
            ++i;
            // password, 作者
            st.setString( i, info.getPassword() );
            ++i;
            // permission, 页数
            st.setString( i, info.getPermission() );

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
    }

    /**
     * 修改一条记录
     * @param: info, 用户对应的表信息
     * @param: id, 主键
     ***/
    public int Update( UserInfo info, int id ){

        int count = -1; // 受影响的记录条数
        conn = DbConn.GetConnection();// 获得连接

        try{
            StringBuffer sBuffer = new StringBuffer();
            sBuffer.append( "update user " )
                    .append( " set username = ?  "
                            + ", password = ? "
                            + ", permission = ? "
                            + " where id = ? " );
            PreparedStatement st = conn.prepareStatement(  sBuffer.toString() );
            int i = 1;
            // username， 书名
            st.setString( i, info.getUsername() );
            ++i;
            // password, 作者
            st.setString( i, info.getPassword() );
            ++i;
            // permission, 页数
            st.setString( i, info.getPermission() );
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
     * return: ArrayList< UserInfo >, 书籍列表
     ***/
    public ArrayList< UserInfo > GetAll( ){

        conn = DbConn.GetConnection();// 取得连接
        System.out.println(conn);

        ArrayList< UserInfo > list = new ArrayList< UserInfo >();
        try{

            StringBuffer sBuffer = new StringBuffer();
            sBuffer.append( " select id, username, password, permission " )
                    .append( " from user " );
            PreparedStatement st = conn.prepareStatement(  sBuffer.toString() );

            
            ResultSet rs = st.executeQuery(  );// 执行查询语句

            while( rs.next() ){
                UserInfo info  = GetDataFromResultSet( rs );
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
     *@para: rs, 数据库集合
     *return: UserInfo, 封装了书本表的字段
     ***/
    private UserInfo GetDataFromResultSet( ResultSet rs ){

        UserInfo info = null;
        try{

            // id int
            int id = rs.getInt( "id" );
            // username，书名 
            String username = rs.getString( "username" );
            // password, 作者
            String password = rs.getString("password");
            // permission，页码
            String permission = rs.getString( "permission" );

            info = new UserInfo( id, username, password, permission );


        }catch( SQLException e ){
            System.out.printf( "数据库查询失败\n" + e.getMessage()  );
            e.printStackTrace();
        }

        return info;

    }
}
