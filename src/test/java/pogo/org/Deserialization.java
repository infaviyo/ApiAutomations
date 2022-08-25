package pogo.org;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Deserialization {
	public static void main(String[] args) {
		
	
pogo p= new pogo();
p.setInstructor("");
p.setServices("");
p.setUrl("");
p.setLinkedIn("");

courses c =new courses();
webAutomation wa= new webAutomation();
wa.setCourseTitle("java");
wa.setPrice("500");

webAutomation wb= new webAutomation();
wa.setCourseTitle("java");
wa.setPrice("500");

List<webAutomation> lw= new ArrayList <webAutomation>();
lw.add(wa);
lw.add(wb);

p.setCourses(c);


RestAssured.baseURI ="https://petstore.swagger.io/v2";
String response =given().log().all().header("Content-Type","application/json")
.body(c).when().post("/pet").then().log().all().assertThat().statusCode(200).body("id",equalTo(155)).extract().response().asString();
System.out.println(response);
JsonPath js =new JsonPath(response);
int id = js.get("id");
System.out.println(id);
int id1 = js.get("category.id");
System.out.println(id1);
int id2 =js.get("tags[0].id");
System.out.println(id2);
String getResponse = given().log().all().pathParam("id",id).headers("Content-Type","application/json")
.when().get("/pet/{id}")
.then().assertThat().statusCode(200).extract().response().asString();
JsonPath js1 =new JsonPath(getResponse);
int Newid = js.get("id");
System.out.println(Newid);
Assert.assertEquals(id, Newid);
	}
}
