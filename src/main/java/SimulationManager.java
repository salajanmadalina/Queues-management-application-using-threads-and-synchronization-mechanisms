import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class SimulationManager implements Runnable {
    private int timeLimit;
    private int maxProcessingTime;
    private int minProcessingTime;
    private int maxArrivalTime;
    private int minArrivalTime;
    private int numberOfServers;
    private int numberOfClients;
    private SelectionPolicy selectionPolicy;
    private Scheduler scheduler;
    private List<Task> generatedTasks;
    private float averageWaitingTime;
    private int peekHour;
    private float averageServiceTime;
    public static boolean runVariable = true;

    public SimulationManager(int maxArrivalTime, int minArrivalTime, int maxProcessingTime, int minProcessingTime, int numberOfClients, int numberOfServers, int timeLimit, String selectedStrategy) {
        super();
        this.maxArrivalTime = maxArrivalTime;
        this.minArrivalTime = minArrivalTime;
        this.maxProcessingTime = maxProcessingTime;
        this.minProcessingTime = minProcessingTime;
        this.numberOfServers = numberOfServers;
        this.numberOfClients = numberOfClients;
        this.timeLimit = timeLimit;
        this.averageWaitingTime = 0;
        this.peekHour = 0;
        this.averageServiceTime = 0;
        this.selectionPolicy = SelectionPolicy.valueOf(selectedStrategy);

        this.scheduler = new Scheduler(numberOfServers, numberOfClients);
        this.scheduler.changeStrategy(selectionPolicy);
        this.generateNRandomTasks();
    }

    private void generateNRandomTasks() {
        Random r = new Random();
        this.generatedTasks = new ArrayList<Task>();

        for (int i = 0; i < numberOfClients; i++) {
            generatedTasks.add(new Task(r.nextInt(maxArrivalTime - minArrivalTime) + minArrivalTime, r.nextInt(maxProcessingTime - minProcessingTime) + minProcessingTime));
            averageWaitingTime += generatedTasks.get(i).getServiceTime();
        }

        Collections.sort(generatedTasks);
        averageServiceTime = averageWaitingTime / numberOfClients;
        averageWaitingTime = averageWaitingTime / (numberOfClients * numberOfServers);
    }

    @Override
    public void run() {
        int currentTime = 0;
        int max1 = Integer.MIN_VALUE;
        try {
            FileWriter myWriter = new FileWriter("projectFile.txt");
            PrintWriter printWriter = new PrintWriter(myWriter);
            printWriter.print(generatedTasks.toString() + "\n");
            SimulationFrame.getTextArea().append(generatedTasks.toString() + "\n");

            while (currentTime < timeLimit) {

                for (int i = 0; i < generatedTasks.size(); i++) {
                    if (generatedTasks.get(i).getArrivalTime() == currentTime) {
                        scheduler.dispatchTask(generatedTasks.get(i));
                        generatedTasks.remove(i--);
                    }
                }

                SimulationFrame.getTextArea().append("Timp simulare " + currentTime + "\n");
                printWriter.print("Timp simulare " + currentTime + "\n");

                for (int i = 0; i < scheduler.getServers().size(); i++) {
                    String print = new String();
                    SimulationFrame.getTextArea().append("Coada " + (i + 1) + ": ");
                    printWriter.print("Coada " + (i + 1) + ": ");
                    print = scheduler.getServers().get(i).getTasks().toString();
                    printWriter.write(print + "\n");
                    SimulationFrame.getTextArea().append(print + "\n");
                }

                int ok = 0;
                int sum = 0;

                for(int i = 0; i < scheduler.getServers().size(); i ++){
                    sum += scheduler.getServers().get(i).getTasks().size();
                }

                if(sum > max1){
                    max1 = sum;
                    peekHour = currentTime;
                }

                for (int i = 0; i < scheduler.getServers().size(); i++) {
                    if (scheduler.getServers().get(i).getTasks().size() != 0) {
                        int time = scheduler.getServers().get(i).getTasks().peek().getServiceTime();
                        if (time != 0) {
                            scheduler.getServers().get(i).getTasks().peek().setServiceTime(time - 1);
                        } else {
                            scheduler.getServers().get(i).deleteTask();
                        }
                    } else {
                        ok++;
                    }
                }

                if (ok == numberOfServers && generatedTasks.size() == 0){
                    runVariable = true;
                    break;
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                currentTime++;
            }
            printWriter.write("Peek hour is " + peekHour + ".\n");
            printWriter.write("Average waiting time is " + averageWaitingTime + ".\n");
            printWriter.write("Average service time is " + averageServiceTime + ".\n");
            printWriter.close();

            SimulationFrame.getTextArea().append("Peek hour is " + peekHour + ".\n");
            SimulationFrame.getTextArea().append("Average waiting time is " + averageWaitingTime + ".\n");
            SimulationFrame.getTextArea().append("Average service time is " + averageServiceTime + ".\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



