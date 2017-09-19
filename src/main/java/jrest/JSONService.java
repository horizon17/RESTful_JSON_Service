package jrest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/json/hhstat")
public class JSONService {

    java.sql.Connection con;
    public dbUtil dbUtil = new dbUtil();

    @GET
    @Path("/get/{lang}")
    @Produces(MediaType.APPLICATION_JSON)
    public Hhstat getTrackInJSON(@PathParam("lang") String lang) {

        Hhstat hhstat= new Hhstat();

        con = dbUtil.dbConnect();
        if(con == null){
            hhstat.setLang("dbConnect_1 = null");
            return hhstat;
        }

        hhstat.setLang(lang);
        List<Tech> techList= dbUtil.selectData(con,lang);
        hhstat.setTechs(techList);
        //track.setTitle("Enter Sandman");
        //track.setSinger("Metallica_"+lang);

        return hhstat;

    }

    @GET
    @Path("/getstats/{lang}")
    @Produces(MediaType.APPLICATION_JSON)
    public Hhstat getStatsByDates(@PathParam("lang") String lang) {

        //HhstatByDate hhstatByDate= new HhstatByDate();
        Hhstat hhstat= new Hhstat();

        con = dbUtil.dbConnect();
        if(con == null){
            hhstat.setLang("dbConnect_1 = null");
            return hhstat;
        }

        hhstat.setLang(lang);
        //Map<Date,Tech> techList= dbUtil.selectDataAllDates(con,lang);
        List<Tech> techList= dbUtil.selectDataAllDates(con,lang);
        hhstat.setTechs(techList);

        return hhstat;

    }

    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTrackInJSON(Resume resume) {

        String result = "Resume saved : " + resume;
        return Response.status(201).entity(result).build();

    }

}
