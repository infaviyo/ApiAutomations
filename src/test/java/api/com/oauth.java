package api.com;

import static io.restassured.RestAssured.given;

import io.restassured.path.json.JsonPath;

public class oauth {
public static void main(String[] args) {
	String url ="https://rahulshettyacademy.com/getCourse.php?code=4%2F0AdQt8qjpLP5ZZrKl6gBzEPm30Ea2AG7LTNPUNb3ti8qaDSDceH7TSlsXVOmVMHGTQPs9Nw&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none";
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
	String course=given().log().all().queryParam("access_token",token).header("Content-Type","application/json")
			.urlEncodingEnabled(false)
.when().get("https://rahulshettyacademy.com/getCourse.php").asString();

System.out.println(course);}
}
