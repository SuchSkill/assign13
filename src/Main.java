


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Eugene on 23.01.2017.
 */
public class Main {
	public static void main(String[] args) throws Exception {
		
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		List<Task> tasks = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			tasks.add(new Task("Task " + i));
		}
		List<Future<Integer>> results = new ArrayList<>();
		List<String> names = new ArrayList<>();
		for (Task task : tasks) {
			results.add(executorService.submit(task));
			names.add(task.getName());
		}
		
		
		while (true) {
			Thread.sleep(3000);
			System.out.println("id\t\t\t\tstatus\t\t\tresult");
			for (int i = 0; i < results.size(); i++) {
				String name = names.get(i);
				TaskStatus status = tasks.get(i).status;
				Integer integer = null;
				if (status == TaskStatus.Done)
					integer = results.get(i).get();
				System.out.println(name + "\t\t\t" + status + "\t\t\t" + integer);
			}
		}
//		executorService.shutdown();
	}
	
	
}
