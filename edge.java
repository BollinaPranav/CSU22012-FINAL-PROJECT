
public class edge {
	public int trip_id;
	public String arrival_time;
	public String departure_time;
	public int Fromstop_id;
    public int Tostop_id;
    public int stop_sequence;
    public String stop_headsign;
    public int pickup_type;
    public int drop_off_type;
    public double shape_dist_traveled;
    public double cost;

	public edge(int trip_id,String arrival_time,String departure_time,int Fromstop_id, int Tostop_id, int stop_sequence,String stop_headsign, int pickup_type, int drop_off_type, double shape_dist_traveled, double cost) {
		
		
		this.trip_id = trip_id;
		this.arrival_time = arrival_time;
		this.departure_time = departure_time;
		this.Fromstop_id = Fromstop_id;
		this.Tostop_id = Tostop_id;
		this.stop_sequence = stop_sequence;
		this.stop_headsign = stop_headsign;
		this.pickup_type = pickup_type;
		this.drop_off_type = drop_off_type;
		this.shape_dist_traveled = shape_dist_traveled;
		this.cost = cost;

}
}
