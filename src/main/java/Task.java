public class Task implements Comparable{
    private int arrivalTime;
    private int serviceTime;
    private int ID;
    private static int nr = 0;

    public Task(int arrivalTime, int serviceTime){
        super();
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        nr++;
        this.ID = nr;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    @Override
    public int compareTo(Object o) {
        Task t = (Task)o;

        if(t.getArrivalTime() > arrivalTime)
            return -1;
        else
            return 1;
    }

    @Override
    public String toString() {
        return "(" + ID +
                " " + arrivalTime +
                " " + serviceTime + ")";
    }
}
