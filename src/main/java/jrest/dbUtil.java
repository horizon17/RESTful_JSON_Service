package jrest;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;
import java.io.*;
import java.util.Date;

/**
 * Created by lexx on 08.07.2017.
 */
public class dbUtil {

    public Connection dbConnect(){

        Connection con;
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hh_parser?serverTimezone=UTC","rootpwd", "123");
        } catch(SQLException e){
            System.out.println("SQL exception occured: " + e);
            con=null;
        }
        return con;
    }

    public List<Tech> selectData(Connection con,String lang){

        String query ="";

        List<Tech> techList=new ArrayList<Tech>();

        try {
            Statement stmt = con.createStatement();
            query = "SELECT name, sum(count) as count, lang FROM hh_parser.tech WHERE lang='"+lang+"' Group by name Order by sum(count) Desc LIMIT 10";

            System.out.println(query);
            stmt.executeQuery(query);
            ResultSet resultSet = stmt.getResultSet();
            while (resultSet.next()){
                Tech tech = new Tech();
                tech.setTech(resultSet.getString("name"));
                tech.setCount(resultSet.getInt("count"));
                techList.add(tech);
            }
        } catch (SQLException e) {
            System.out.println("Error " + e + " in query=" + query);
        }
        return techList;

    }

    public Map<Date,Tech> selectDataByDate(Connection con,String lang){

        String query ="";

        Map<Date,Tech> techList=new HashMap<Date, Tech>();

        List<Date> dates=selectAllDates(con);

        int i=0;
        for (Date date : dates) {
            Date date1=getStartOfDay(date);
            Date date2=getEndOfDay(date);
            i++;
            try {
                Statement stmt = con.createStatement();

                query = "SELECT name, sum(count) as count, lang FROM hh_parser.tech WHERE lang='" + lang + "' AND date>'"+date1+"' AND date<'"+date2+"' Group by name Order by sum(count) Desc LIMIT 10";
                System.out.println(query);
                System.out.println(date1+" / "+date2);
                stmt.executeQuery(query);
                ResultSet resultSet = stmt.getResultSet();
                while (resultSet.next()) {
                    Tech tech = new Tech();
                    tech.setTech(resultSet.getString("name"));
                    tech.setCount(resultSet.getInt("count"));

                    techList.put(date, tech);
                }

            } catch (SQLException e) {
                System.out.println("Error " + e + " in query=" + query);
            }
            if (i>10) break;
        }
        return techList;

    }

    public List<Tech> selectDataAllDates(Connection con,String lang){

        String query ="";

        //Map<Date,Tech> techList=new HashMap<Date, Tech>();
        List<Tech> techList=new ArrayList<Tech>();

        int i=0;

        i++;
        try {
            Statement stmt = con.createStatement();

            query = "select date,name,count, r from ( select @r:=CASE WHEN @date=date THEN @r + 1  ELSE 1 END AS r, @date:=date" +
                    "  date, name, count from " +
                    "  (SELECT DATE(date) as date, name,sum(count) as count from tech where lang='java' " +
                    "  group by DATE(date), name " +
                    "  order by DATE(date), count desc, name) AS t) tn where r<=10 order by date, count desc, name";

            System.out.println(query);
            //System.out.println(date1 + " / " + date2);
            stmt.executeQuery(query);
            ResultSet resultSet = stmt.getResultSet();
            while (resultSet.next()) {
                Tech tech = new Tech();
                tech.setCount(resultSet.getInt("count"));
                tech.setTech(resultSet.getString("name"));
                tech.setDate(resultSet.getDate("date"));
                System.out.println("-------> "+tech.getDate()+"   "+tech.getTech()+"="+tech.getCount());
                //techList.put(tech.getDate(), tech);
                techList.add(tech);
                if (i > 200) break;
            }

        } catch (SQLException e) {
            System.out.println("Error " + e + " in query=" + query);
        }

        return techList;

    }

    public Date getEndOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public Date getStartOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public List<Date> selectAllDates(Connection con){

        String query ="";

        List<Date> dates=new ArrayList();

        try {
            Statement stmt = con.createStatement();

            query = "SELECT date FROM hh_parser.tech Group by date Order by date ";

            stmt.executeQuery(query);
            ResultSet resultSet = stmt.getResultSet();
            while (resultSet.next()){
                dates.add(resultSet.getDate("date"));
            }
        } catch (SQLException e) {
            System.out.println("Error " + e + " in query=" + query);
        }
        return dates;

    }


}
