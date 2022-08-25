package api.com;

import static io.restassured.RestAssured.given;

import java.util.List;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import pogo.org.Api;
import pogo.org.courses;
import pogo.org.mobile;
import pogo.org.pogo;
import pogo.org.webAutomation;

public class serialization {
public static void main(String[] args) {
	String url ="rahulshettyacademy.com/maps/api/place/add/json&key =qaclick123"
			+ "	String[] s = url.split(\"code=\");\r\n"
			+ "	String[]s1=s[1].split(\"&scope=\");\r\n"
			+ "	String code = s1[0];\r\n"
			+ "	\r\n"
			+ "	//assess token\r\n"
			+ "String Acesstoken=	given().log().all().queryParams(\"code\",code)\r\n"
			+ "	.queryParams(\"scope\",\"https://www.googleapis.com/auth/userinfo.email\")\r\n"
			+ "	.queryParams(\"client_id\",\"692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com\")\r\n"
			+ "	.queryParams(\"client_secret\",\"erZOWM9g3UtwNRj340YYaK_W\")\r\n"
			+ "	.queryParams(\"redirect_uri\",\"https://rahulshettyacademy.com/getCourse.php\")\r\n"
			+ "	.queryParams(\"grant_type\",\"authorization_code\").header(\"Content-Type\",\"application/json\")\r\n"
			+ "	.urlEncodingEnabled(false)\r\n"
			+ "	.when().post(\"https://www.googleapis.com/oauth2/v4/token\")\r\n"
			+ "	.then().assertThat().statusCode(200).extract().response().asString();\r\n"
			+ "	\r\n"
			+ "\r\n"
			+ "\r\n"
			+ "JsonPath js= new JsonPath(Acesstoken);\r\n"
			+ "String token=js.get(\"access_token\");\r\n"
			+ "System.out.println(\"token is:\"+token);\r\n"
			+ "	//course details\r\n"
			+ "	String course=given().log().all().queryParam(\"access_token\",token).header(\"Content-Type\",\"application/json\")\r\n"
			+ "			.urlEncodingEnabled(false)\r\n"
			+ ".when().get(\"https://rahulshettyacademy.com/getCourse.php\").asString();\r\n"
			+ "\r\n"
			+ "System.out.println(course);}&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none";
	String[] s = url.split("code=");
	String[]s1=s[1].split("&scope=");
	String code = s1[0];
	
	//assess token
String Acesstoken=	given().log().all().queryParams("code",code)
	.queryParams("scope","https://www.googleapis.com/auth/userinfo.email")
	.queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
	.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
	.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
	.queryParams("grant_type","authorization_code").header("Content-Type","application/json")
	.urlEncodingEnabled(false)
	.when().post("https://www.googleapis.com/oauth2/v4/token")
	.then().assertThat().statusCode(200).extract().response().asString();
	


JsonPath js= new JsonPath(Acesstoken);
String token=js.get("access_token");
System.out.println("token is:"+token);
	//course details
pogo course=given().log().all()
.queryParam("access_token",token)
.header("Content-Type","application/json").urlEncodingEnabled(false)
.expect().defaultParser(Parser.JSON)
			
.when().get("https://rahulshettyacademy.com/getCourse.php").as(pogo.class);

System.out.println(course.getInstructor());
System.out.println(course.getExpertise());
System.out.println(course.getLinkedIn());
System.out.println(course.getServices());
System.out.println(course.getUrl());
List<webAutomation> webAutomation = course.getCourses().getWebAutomation();
for (int i = 0; i < webAutomation.size(); i++) {
	System.out.println(webAutomation.get(i).getCourseTitle());
System.out.println(webAutomation.get(i).getPrice());}
List<mobile> mobile = course.getCourses().getMobile();
for (int j = 0; j < mobile.size(); j++) {
	System.out.println(mobile.get(j).getCourseTitle());
System.out.println(mobile.get(j).getPrice());
}
List<Api> api = course.getCourses().getApi();
for (int i = 0; i < api.size(); i++) {
	System.out.println(api.get(i).getCourseTitle());
	System.out.println(api.get(i).getPrice());
	
}
}
}

