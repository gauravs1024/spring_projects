package student_mapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import Student.Student;


public class Student_mapper  implements RowMapper<Student> {

	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		Student std=new Student();
		std.setRollno(rs.getString("std_roll"));
		std.setName(rs.getString("std_name"));
		std.setMarks(rs.getString("std_marks"));

		
		return std;
	}

	

	

}
