import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by Eugene on 23.01.2017.
 */
public class Task implements Callable<Integer>{
	String name;
	
	TaskStatus status;
	
	public Task(String name) {
		this.name = name;
		status = TaskStatus.Pending;
	}
	public String getName() {
		return name;
	}
	
	public TaskStatus getStatus() {
		return status;
	}
	
	@Override
	public Integer call() throws Exception {
		status = TaskStatus.Running;
		int i = new Random().nextInt();
		Thread.sleep(3000 + Math.abs(i%7000));
		if (i%5==0)
			status = TaskStatus.Failed;
		else
			status = TaskStatus.Done;
		return i;
	}
	
	
	
}
