import jrest.Tech;
import jrest.dbUtil;

import java.util.List;

/**
 * Created by lexx on 16.09.2017.
 */
public class dbTests {

    java.sql.Connection con;
    public jrest.dbUtil dbUtil = new dbUtil();

    public void dbTests(String[] args) {

        con = dbUtil.dbConnect();
        if(con == null){
            return;
        }

        List dates= dbUtil.selectAllDates(con);
        System.out.println(dates.toString());
    }

}
