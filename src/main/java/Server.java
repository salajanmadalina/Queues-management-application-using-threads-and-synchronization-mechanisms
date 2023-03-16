import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;

    public Server(int maxTasksPerServer){
        this.tasks = new ArrayBlockingQueue<Task>(maxTasksPerServer);
        this.waitingPeriod = new AtomicInteger();

    }

    public void deleteTask(){
        for(int i = 0; i < tasks.size(); i ++)
            if(tasks.peek().getServiceTime() == 0){
                tasks.remove();
            }
    }

    public void addTask(Task newTask){
        tasks.add(newTask);
        waitingPeriod.addAndGet(newTask.getServiceTime());
    }

    public void run(){
        Task t = new Task(0, 0);
        while(SimulationManager.runVariable) {
            if (!tasks.isEmpty()) {
                t = tasks.peek();
                if (t != null) {
                    try {
                        Thread.sleep(t.getServiceTime() * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                this.waitingPeriod.addAndGet(-t.getServiceTime());
            }
        }
    }

    public BlockingQueue<Task> getTasks() {
        return tasks;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }
}
