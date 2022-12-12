public class BookedTrain {
    int train_no;
    String train_name;
    String source;
    String destination;
    String arrivalTime;
    String destinationTime;
    int cost;

    BookedTrain(int train_no,String train_name,String source,String destination,String arrivalTime,String destinationTime,int cost){
        this.train_no=train_no;
        this.train_name=train_name;
        this.source=source;
        this.destination=destination;
        this.arrivalTime=arrivalTime;
        this.destinationTime=destinationTime;
        this.cost=cost;
    }
}