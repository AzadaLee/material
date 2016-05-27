package person.txm.test.thread.future;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureDemo {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        Student s1 = new Student("zs", 25);
        Student s2 = new Student("ls", 24);
        Student s3 = new Student("ww", 23);
        Student s4 = new Student("zl", 22);
        List<Student> students = new ArrayList<Student>();
        List<Student> students2 = new ArrayList<Student>();
        students.add(s1);
        students.add(s2);
        students2.add(s3);
        students2.add(s4);
        CallableDemo task = new CallableDemo(students);
        CallableDemo task2 = new CallableDemo(students);
        List<CallableDemo> list = new ArrayList<CallableDemo>();
        list.add(task);
        list.add(task2);
        List<Future<Map<String, Object>>> f =  pool.invokeAll(list);
        for(Future<Map<String, Object>> future : f){
        	Map<String,Object> map = future.get();
            Set<String> key = map.keySet();
            Iterator<String> iterator = key.iterator();
            String k = "";
            while(iterator.hasNext()){
            	k = iterator.next();
            	System.out.println(k+""+map.get(k));
            }
        }
        
//        Set<Map.Entry<String,Object>> sets = map.entrySet();
//        sets.
        pool.shutdownNow();
    }
}
