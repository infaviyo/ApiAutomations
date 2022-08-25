package api.com;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

public class Getcomment {
	public static void main(String[] args) {
		RestAssured.baseURI ="http://localhost:8080/";
		
		SessionFilter session =new SessionFilter();
		
				//session
	given().log().all().header("Content-Type","application/json")
	.body(""+"{ \"username\": \"infaviyo\", \"password\": \"viyola66\" }").filter(session)
	.when().post("rest/auth/1/session")
	.then().assertThat().statusCode(200);
	//add comment
		String comment=given().log().all().pathParam("id","10304").header("Content-Type","application/json")
		.body("{\r\n"
				+ "    \"body\": \" this my fifth comment.\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}").filter(session)
				.when().post("rest/api/2/issue/{id}/comment")
				.then().assertThat().statusCode(201).extract().response().asString();
//get comment
	String comments=given().log().all().pathParam("id","10304").header("Content-Type","application/json")
	.filter(session)
	.when().get("rest/api/2/issue/{id}/comment")
	.then().assertThat().statusCode(200).extract().response().asString();
	System.out.println(comments);

		JsonPath js= new JsonPath(comments);
		int count=js.get("comments.size()");
		System.out.println(count);
		
		for (int i = 0; i < count; i++) {
		String ids = js.get("comments["+i+"].id");
		System.out.println(ids);
		if (ids.equals(10306)) {
			String msg= js.get("comments["+i+"].body");
			System.out.println(msg);
		}
		}
	
	}}
