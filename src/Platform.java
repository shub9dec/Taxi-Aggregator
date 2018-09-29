
import java.util.*;
import iiitb.ess201a7.a7base.*;

public class Platform {

// all the methods in this class need to be implemented
	private ArrayList<Fleet> fleetList;
	public Platform() {
		fleetList = new ArrayList<Fleet>();
	}

	public void addFleet(Fleet f) {
		fleetList.add(f);
	}

	// for a request defined as a Trip, find the best car by checking each of its fleets
	// and assigns the car to this trip
	public Car assignCar(Trip trip) {
		boolean flag = false;
		Car minDistanceCar = null;
		int minDistance = Integer.MAX_VALUE;
		for(int i = 0 ; i < fleetList.size() ; i++) {
			Car tempCar = fleetList.get(i).findNearestCar(trip.getStart());
			//If none of the cars of a fleet are idle, the fleet returns a null pointer.
			//This is the reason why we use exception handling here to catch the NULLPOINTEREXCEPTION
			try {
				int tempDistance = tempCar.distSqrd(trip.getStart());
				flag = true;
				if(tempDistance < minDistance) {
					minDistance = tempDistance;
					minDistanceCar = tempCar;
				}
			}
			catch(NullPointerException e) {
				
			}
		}
		if(flag == true) {
			minDistanceCar.assignTrip(trip);
		}
		return minDistanceCar;
	}

	// returns list of all cars (in all the fleets) managed by this platform
	public ArrayList<Car> findCars() {
		ArrayList<Car> allCarsList = new ArrayList<Car>();
		for(Fleet i : fleetList) {
			allCarsList.addAll(i.getCars());
		}
		return allCarsList;
	}
}