import java.util.List;

public class ConcreteStrategyTime implements Strategy{
    @Override
    public void addTask(List<Server> servers, Task t) {

        int min1 = Integer.MAX_VALUE;
        int pos = 0;
       // int size = Integer.MAX_VALUE;

        for(int i = 0; i < servers.size(); i ++){
            if(servers.get(i).getWaitingPeriod().get() < min1){
                pos = i;
                min1 = servers.get(i).getWaitingPeriod().get();
            }
        }

       // t.setServiceTime(min1 + t.getServiceTime());
        servers.get(pos).addTask(t);
    }
}
