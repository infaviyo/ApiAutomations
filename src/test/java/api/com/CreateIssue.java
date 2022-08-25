package api.com;

import static io.restassured.RestAssured.given;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

public class CreateIssue {
public static void main(String[] args) {
	RestAssured.baseURI ="http://localhost:8080/";
	
	SessionFilter session =new SessionFilter();
	
			//session
	given().log().all().header("Content-Type","application/json")
	.body(""+"{ \"username\": \"infaviyo\", \"password\": \"viyola66\" }").filter(session)
			.when().post("rest/auth/1/session")
			.then().assertThat().statusCode(200);
		//create	
	String issue =given().log().all().header("Content-Type","application/json")
	.body("{\r\n"
					+ "    \"fields\": {\r\n"
					+ "       \"project\":\r\n"
					+ "       {\r\n"
					+ "          \"key\": \"RES\"\r\n"
					+ "       },\r\n"
					+ "       \"summary\": \"  forth botten not working.\",\r\n"
					+ "       \"description\": \"Creating of an issue using ids for projects and issue types using the REST API\",\r\n"
					+ "       \"issuetype\": {\r\n"
					+ "          \"name\": \"Bug\"\r\n"
					+ "       }\r\n"
					+ "   }\r\n"
					+ "}").filter(session)
			.when().post("rest/api/2/issue")
			.then().assertThat().statusCode(201).extract().response().asString();
	
	JsonPath js= new JsonPath(issue);
	String id=js.get("id");
	System.out.println(issue);
	
	//add comment
	String comment=given().log().all().pathParam("id",id).header("Content-Type","application/json")
	.body("{\r\n"
			+ "    \"body\": \" this my forth comment.\",\r\n"
			+ "    \"visibility\": {\r\n"
			+ "        \"type\": \"role\",\r\n"
			+ "        \"value\": \"Administrators\"\r\n"
			+ "    }\r\n"
			+ "}").filter(session)
			.when().post("rest/api/2/issue/{id}/comment")
			.then().assertThat().statusCode(201).extract().response().asString();
	//add attachement
	given().log().all().pathParam("id",id).header("X-Atlassian-Token","no-check")
.header("Content-Type"," multipart/form-data")
	.multiPart("file",new File("./jira.txt")).filter(session)
		.when().post("rest/api/2/issue/{id}/attachments")
		.then().assertThat().statusCode(200);
//get comments



}
}
