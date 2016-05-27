package person.txm.test.thread.future;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class CallableDemo implements Callable<Map<String, Object>>{
	private volatile List<Student> students = null;
	public CallableDemo(final List<Student> students){
		this.students = students;
	}

	@Override
	public Map<String, Object> call(){
		System.out.println(Thread.currentThread().getName());
		Map<String,Object> result = new HashMap<String, Object>();
		doSomething(result, students);
		return result;
	}
	
	public void doSomething(Map<String,Object> params,List<Student> students){
		for(Student stu : students){
			params.put(stu.getName(), stu.getAge());
		}
	}
}
