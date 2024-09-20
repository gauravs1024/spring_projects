package maven_projects.spring_jdbc_crud_opr;

import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.instrument.classloading.jboss.JBossLoadTimeWeaver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import Student.Student;
import resources.spring_config_file;
import student_mapper.Student_mapper;

public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext context= new AnnotationConfigApplicationContext(spring_config_file.class);
    	JdbcTemplate jdbcTemplate=context.getBean(JdbcTemplate.class);
    	
    	
    	//----------------INSERT OPERATION----------------------
    	String std_rollno="503";
    	String std_name="GAurav";
    	String marks="99919";
    	String insert_sql_query="INSERT INTO student VALUES(?,?,?)";
    	int count=jdbcTemplate.update(insert_sql_query,std_rollno,std_name,marks);
    	if(count>0) {
    		System.out.println("data inserted successfully");
    	}
    	else {
    		System.out.println("data insertion failed");

    	}
    	
    	
    	//---------------UPDATE OPERATION------------------------
    	String update_sql_query="UPDATE student SET std_marks=? WHERE std_roll=503";
    	String new_marks="99";
    	int up_count=jdbcTemplate.update(update_sql_query,new_marks);
    	if(up_count>0) {
    		System.out.println("data updated successfully");
    	}
    	else {
    		System.out.println("data updation failed");

    	}
    	
    	//--------------DELETE OPERATION-------------------------
    	String delete_sql_query="DELETE FROM student  WHERE std_roll=233";

     	int del_count=jdbcTemplate.update(delete_sql_query);
    	if(del_count>0) {
    		System.out.println("data deleted successfully");
    	}
    	else {
    		System.out.println("data deletion failed");

    	}
    	
    	//--------------SELECT OPERATION---------------------------
    	
    	String select_sql_query="SELECT * FROM student ";
    	List<Student> std_list=jdbcTemplate.query(select_sql_query, new Student_mapper());
    	for(Student std :std_list) {
    		System.out.println("rolno :"+std.getRollno());
    		System.out.println("name :"+std.getName());
    		System.out.println("marks :"+std.getMarks());
    		System.out.println("----------------");
    	}
    	
    	//--------------SELECT OPERATION-2-------------------------
    	String roll="503";
    	String select_sql_query2="SELECT * FROM student WHERE std_roll=?";
    	List<Student> std_list2=jdbcTemplate.query(select_sql_query2, new Student_mapper(),roll);
    	for(Student std1 :std_list2) {
    		System.out.println("rolno :"+std1.getRollno());
    		System.out.println("name :"+std1.getName());
    		System.out.println("marks :"+std1.getMarks());
    		System.out.println("----------------");
    	}
    	
    	//------------------SELECT OPERATION 3(ACCESSING AS A OBJECT)------------
    	String roll2="101"; 		// IF MORE THAN ONE OBJECTS COMING FROM SQL DATA BASE MEANS THERE EXIST MORE THAN ONE NUMBER OF DATA OF SAME ROLL NUMBER THEN IT THROUGHS EXCEPTION
    	String select_sql_query3="SELECT * FROM student WHERE std_roll=?";
    	Student std_list3=jdbcTemplate.queryForObject(select_sql_query3, new Student_mapper(),roll2);
    	
    		System.out.println("rolno :"+std_list3.getRollno());
    		System.out.println("name :"+std_list3.getName());
    		System.out.println("marks :"+std_list3.getMarks());
    		System.out.println("----------------");
    	
    		App obj=new App();
    		obj.namedParameterJdbcFunc();
    }
    
    
    public void namedParameterJdbcFunc() {
    	
    	ApplicationContext new_context= new AnnotationConfigApplicationContext(spring_config_file.class);
    	NamedParameterJdbcTemplate npjdbcTemplate=new_context.getBean(NamedParameterJdbcTemplate.class);
    	Map<String,String> map=new HashMap<String,String>();
    	map.put("std_rollno","104");
    	map.put("std_name","Aman Kumar ");
    	map.put("std_marks","0.0009");
    	String insert_sql_query="INSERT INTO student VALUES(:std_rollno,:std_name,:std_marks) ";
    	int count4=npjdbcTemplate.update(insert_sql_query, map);
    	if(count4>0) {
    		System.out.println("data inserted successfully");
    	}
    	else {
    		System.out.println("data insertion failed");

    	}

    	
    	 
    	
    }
}
