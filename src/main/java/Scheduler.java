import java.util.*;

public class Scheduler {
    private List<Server> servers;
    private int nrMaxNoServers;
    private int maxTasksPerServer;
    private Strategy strategy;

    public Scheduler(int maxNoServers, int maxTasksPerServer){
        this.nrMaxNoServers = maxNoServers;
        this.maxTasksPerServer = maxTasksPerServer;
        this.servers = new ArrayList<Server>();

        for(int i = 0; i < maxNoServers; i ++){
            Server s = new Server(maxTasksPerServer);
            this.servers.add(s);
            Thread t = new Thread(s);
            t.start();
        }
    }

    public void changeStrategy(SelectionPolicy policy) {
        if (policy == SelectionPolicy.SHORTEST_QUEUE) {
            strategy = new ConcreteStrategyQueue();
        }
        if (policy == SelectionPolicy.SHORTEST_TIME) {
            strategy = new ConcreteStrategyTime();
        }
    }

   public void dispatchTask(Task t){
        strategy.addTask(servers, t);
   }

    public ArrayList<Server> getServers() {
        return (ArrayList<Server>) servers;
    }
}
