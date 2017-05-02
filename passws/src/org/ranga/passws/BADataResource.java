package org.ranga.passws;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.ranga.passws.model.BAData;
import org.ranga.passws.model.BioData;
import org.ranga.passws.model.RequestData;
import org.ranga.passws.service.BADataService;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("")
public class BADataResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
	
	@GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getBookingas() {
    	
        return "Hello World" ;
    }
	
	
	@GET
    @Path("/booking")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RequestData> getBooking() {
    	
        return new BADataService().getBooking();
    }
    
	
	@GET
    @Path("/booking/performba/{deviceId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RequestData> setPerformBA(@PathParam("deviceId") String deviceId) {
    	
        return new BADataService().setPerformBA(deviceId);
    }
    
	
	
	
    @GET
    @Path("/{deviceId}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public List<RequestData> getRequest(@PathParam("deviceId") String deviceId) {
    	
        return new BADataService().getBARequest(deviceId);
    }
    
    
    
    
    @GET
    @Path("/badata/{deviceId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<BAData> getBAData(@PathParam("deviceId") String deviceId) {
    	System.out.println("ID : " + deviceId);
        return new BADataService().getBAData(deviceId);
    }
    
    
    @POST
    @Path("/badata")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public List<BAData> putBAData(@PathParam("deviceId") String deviceId, @FormParam("result") String res) {
    	//System.out.println("ID : " + deviceId);
    	//System.out.println("Result : " + res);
        return new BADataService().putBAData(res);
    }
    
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public List<BAData> authenticate(@PathParam("uername") String deviceId, @FormParam("password") String res) {
    	//System.out.println("ID : " + deviceId);
    	//System.out.println("Result : " + res);
        return new BADataService().putBAData(res);
    }
    
    
    
    // MEDICAL RELATED FUNCTIONS
    
    
    @GET
    @Path("/biodata/{empId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<BioData> getBioData(@PathParam("empId") String empId) {
    	System.out.println("ID : " + empId);
    	
    	BADataService bds = new BADataService();
        return bds.getBioData(empId);
    }
    
    
    
    @GET
    @Path("/getuser/{uid}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<BioData> getUserInfo(@PathParam("uid") String uid) {
    	System.out.println("ID : " + uid);
    	
    	BADataService bds = new BADataService();
        return bds.getBioData(uid);
    }
    
    
    
    @POST
    @Path("/savebiodata/{empid}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public List<BAData> savePersonalBiodata(@FormParam("firstname") String firstname, @FormParam("lastname") String lastname,
    										@FormParam("fathersname") String fathersname,@FormParam("mothersname") String mothersname,
											@FormParam("dob") String dob, @FormParam("pob") String pob,@FormParam("nationality") String nationality) {
    	//System.out.println("ID : " + deviceId);
    	//System.out.println("Result : " + res);
    	String empid="P-101";
        return new BADataService().savePersonalBiodata(empid,firstname,lastname,fathersname,mothersname,dob,pob,nationality);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
