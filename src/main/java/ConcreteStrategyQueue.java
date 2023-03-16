import java.util.List;

public class ConcreteStrategyQueue implements Strategy{
    @Override
    public void addTask(List<Server> servers, Task t) {

        int min1 = Integer.MAX_VALUE;
        int pos = 0;

        for(int i = 0; i < servers.size(); i ++){
            if(min1 > servers.get(i).getTasks().size()){
                pos = i;
                min1 = servers.get(i).getTasks().size();
            }
        }

        servers.get(pos).addTask(t);
    }
}
