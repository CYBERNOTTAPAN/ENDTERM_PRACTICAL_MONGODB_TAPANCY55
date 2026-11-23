import com.mongodb.client.*;
import org.bson.Document;
import java.util.*;
public class EmployeeDirectory {
 public static void main(String[] args) {
 try (MongoClient client =
 MongoClients.create("mongodb://localhost:27017")) {
 MongoDatabase db = client.getDatabase("hr");
 MongoCollection<Document> e = db.getCollection("employees");
 e.drop();
 
 e.insertMany(Arrays.asList(
 new Document("empId",1).append("name","Anita").append("dept","HR").append("salary",42000).append("city","Chennai"),
 new Document("empId",2).append("name","Bala").append("dept","IT").append("salary",65000).append("city","Bengaluru"),
 new Document("empId",3).append("name","Charan").append("dept","IT").append("salary",58000).append("city","Chennai"),
 new Document("empId",4).append("name","Deepa").append("dept","Finance").append("salary",50000).append("city","Mumbai"),
 new Document("empId",5).append("name","Elan").append("dept","IT").append("salary",72000).append("city","Chennai"),
 new Document("empId",6).append("name","Farah").append("dept","HR").append("salary",45000).append("city","Delhi"),
 new Document("empId",7).append("name","Gopal").append("dept","Finance").append("salary",61000).append("city","Chennai"),
 new Document("empId",8).append("name","Hema").append("dept","Sales").append("salary",38000).append("city","Hyderabad")
 ));
 
 e.find(new Document("salary",new Document("$gt",60000))).forEach(System.out::println);
 
 e.find(new Document("salary",new Document("$gte",45000).append("$lte",60000)))
 .forEach(System.out::println);

 e.find(new Document("dept",new Document("$in",Arrays.asList("IT","Finance"))))
 .forEach(System.out::println);
 
 e.find(new Document("$and",Arrays.asList(
 new Document("city","Chennai"),
 new Document("salary",new Document("$gt",50000)))))
 .forEach(System.out::println);
 
 e.find(new Document("$or",Arrays.asList(
 new Document("dept","HR"),
 new Document("salary",new Document("$gt",70000)))))
 .forEach(System.out::println);
 
 e.find(new Document("city",new Document("$nin",Arrays.asList("Chennai","Bengaluru"))))
 .forEach(System.out::println);
 
 e.find(new Document("name",new Document("$regex","^[ABC]")))
 .forEach(System.out::println);
 
 e.find()
 .projection(new Document("_id",0).append("name",1).append("salary",1))
 .sort(new Document("salary",-1)).skip(2).limit(3)
 .forEach(System.out::println);
 }
 }
}