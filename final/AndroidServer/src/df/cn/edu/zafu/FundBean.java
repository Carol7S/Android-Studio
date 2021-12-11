package df.cn.edu.zafu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FundBean {
    // 数据库连接
    private Connection conn;

    public FundBean() {

    }

    //    添加用户
    public int Add(FundInfo info){
        int count = -1; // 受影响的记录条数
        conn = DbConn.GetConnection();// 获得连接
        try{
            StringBuffer sBuffer = new StringBuffer();
            sBuffer.append( "insert into fund " )
                    .append( " ( name, value, size, time, DailyIncrease, WeeklyIncrease, MonthlyIncrease, YearlyIncrease, holdNum ) " )
                    .append( " values ( ?, ?, ?, ?, ?, ?, ?, ?, ? ) " );
            PreparedStatement st = conn.prepareStatement(  sBuffer.toString() );
            int i = 1;
            // name， 名称
            st.setString( i, info.getname() );
            ++i;
            // value, 净值
            st.setString( i, info.getvalue() );
            ++i;
            // size, 规模
            st.setString( i, info.getsize() );
            ++i;
            // time, 周期
            st.setString( i, info.gettime() );
            ++i;
            // DailyIncrease,日增长
            st.setString( i, info.getDailyIncrease() );
            ++i;

            st.setString( i, info.getWeeklyIncrease() );
            ++i;

            st.setString( i, info.getMonthlyIncrease() );
            ++i;

            st.setString( i, info.getYearlyIncrease() );
            ++i;
            // holdNum，持有量
            st.setInt( i, info.getholdNum() );

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
    public int Update( FundInfo info, String name ){

        int count = -1; // 受影响的记录条数
        conn = DbConn.GetConnection();// 获得连接

        try{
            StringBuffer sBuffer = new StringBuffer();
            sBuffer.append( "update fund " )
                    .append( " set name = ? "
                            + ", value = ? "
                            + ", size = ? "
                            + ", time = ? "
                            + ", DailyIncrease = ? "
                            + ", WeeklyIncrease = ? "
                            + ", MonthlyIncrease = ? "
                            + ", YearlyIncrease = ? "
                            + ", holdNum = ? "
                            + " where name = ? " );
            PreparedStatement st = conn.prepareStatement(  sBuffer.toString() );
            int i = 1;

            // name， 名称
            st.setString( i, info.getname() );
            ++i;
            // value, 净值
            st.setString( i, info.getvalue() );
            ++i;
            // size, 规模
            st.setString( i, info.getsize() );
            ++i;
            // time, 周期
            st.setString( i, info.gettime() );
            ++i;


            // DailyIncrease,日增长
            st.setString( i, info.getDailyIncrease() );
            ++i;
            st.setString( i, info.getWeeklyIncrease() );
            ++i;
            st.setString( i, info.getMonthlyIncrease() );
            ++i;
            st.setString( i, info.getYearlyIncrease() );
            ++i;


            // holdNum，持有量
            st.setInt( i, info.getholdNum() );
            ++i;
            // where
            st.setString(i, info.getname());

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


    //删除一条记录
    public int Delete( String name ){

        int count = -1; // 受影响的记录条数
        conn = DbConn.GetConnection();// 获得连接

        try{
            StringBuffer sBuffer = new StringBuffer();
            sBuffer.append( "delete from fund " )
                    .append( " where name = ? " );
            PreparedStatement st = conn.prepareStatement(  sBuffer.toString() );
            int i = 1;

            // where
            st.setString(i, name);

            count = st.executeUpdate( );// 执行语句
            System.out.printf( "删除%d条记录", count );
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
     * 读取全部
     * return: ArrayList< FundInfo >, 书籍列表
     ***/
    public ArrayList< FundInfo > GetAll( ){

        conn = DbConn.GetConnection();// 取得连接
        System.out.println(conn);

        ArrayList< FundInfo > list = new ArrayList< FundInfo >();
        try{

            StringBuffer sBuffer = new StringBuffer();
            sBuffer.append( " select id, name, value, size, time, DailyIncrease, WeeklyIncrease, MonthlyIncrease, YearlyIncrease, holdNum " )
                    .append( " from fund " );
            PreparedStatement st = conn.prepareStatement(  sBuffer.toString() );


            ResultSet rs = st.executeQuery(  );// 执行查询语句

            while( rs.next() ){
                FundInfo info  = GetDataFromResultSet( rs );
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
     *return: FundInfo, 封装了基金表的字段
     ***/
    private FundInfo GetDataFromResultSet( ResultSet rs ){

        FundInfo info = null;
        try{

            // id int
            int id = rs.getInt( "id" );
            // name，基金名称
            String name = rs.getString( "name" );
            // value, 净值
            String value = rs.getString("value");
            // size，规模
            String size = rs.getString( "size" );
            String time = rs.getString( "time" );
            //DailyIncrease, 日增长
            String DailyIncrease = rs.getString( "DailyIncrease" );
            String WeeklyIncrease = rs.getString( "WeeklyIncrease" );
            String MonthlyIncrease = rs.getString( "MonthlyIncrease" );
            String YearlyIncrease = rs.getString( "YearlyIncrease" );
            //holdNum, 持有量
            int holdNum = rs.getInt( "holdNum" );

            info = new FundInfo( id, name, value, size, time, DailyIncrease, WeeklyIncrease, MonthlyIncrease, YearlyIncrease, holdNum );


        }catch( SQLException e ){
            System.out.printf( "数据库查询失败\n" + e.getMessage()  );
            e.printStackTrace();
        }

        return info;

    }
}
