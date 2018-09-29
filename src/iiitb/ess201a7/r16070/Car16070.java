package iiitb.ess201a7.r16070;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import iiitb.ess201a7.a7base.*;

class Car16070 extends Car {
	private Location carLocation;
	private int status;
	private Trip trip;
	public Car16070(int carId, int speed) {
		super(carId,speed);
		carLocation = new Location(0,0);
		setStatus(Idle);
	}

	@Override
	public void setLocation(Location l) {
		// TODO Auto-generated method stub
		carLocation.set(l.getX(), l.getY());
	}

	@Override
	public Location getLocation() {
		// TODO Auto-generated method stub
		return carLocation;
	}

	@Override
	public void setStatus(int s) {
		// TODO Auto-generated method stub
		status = s;
	}

	@Override
	public int getStatus() {
		// TODO Auto-generated method stub
		return status;
	}

	@Override
	public void assignTrip(Trip trip) {
		// TODO Auto-generated method stub
		this.trip = trip;
		status = Booked;
	}

	@Override
	public Location getStart() {
		// TODO Auto-generated method stub
		return trip.getStart();
	}

	@Override
	public Location getDest() {
		// TODO Auto-generated method stub
		return trip.getDest();
	}
	@Override
	public Trip getTrip() {
		return this.trip;
	}
	@Override
	public void updateLocation(double deltaT) {
		// TODO Auto-generated method stub
		if(status == Idle) {
			return;
		}
		else if(status == Booked) {
			if(distSqrd(trip.getStart()) == 0) {
				setStatus(OnTrip);
				return;
			}

			double den = sqrt(distSqrd(trip.getStart()));
			double cos = (trip.getStart().getX() - carLocation.getX())/den;
			double sin = (trip.getStart().getY() - carLocation.getY())/den;
			
			if(deltaT*maxSpeed < den) {
				carLocation.set((int)(deltaT*maxSpeed*cos) + carLocation.getX(), (int)(deltaT*maxSpeed*sin) + carLocation.getY());
				return;
			}
			
			else {
				carLocation.set(trip.getStart().getX(), trip.getStart().getY());
				setStatus(OnTrip);
				return;
			}
			//Go towards the customer
			//Once you've reached the customer, change the status to OnTrip, because the customer will 
			//have jumped into the car, before you know it.
		}
		else {//This case corresponds to status==OnTrip
			//Once you've reached the desired destination, update the car's status to Idle.
			if(distSqrd(trip.getDest()) == 0) {
				setStatus(Idle);
				return;
			}
			
			double den = sqrt(distSqrd(trip.getDest()));
			double cos = (trip.getDest().getX() - carLocation.getX())/den;
			double sin = (trip.getDest().getY() - carLocation.getY())/den;
			
			if(deltaT*maxSpeed < den) {
				carLocation.set((int)(deltaT*maxSpeed*cos) + carLocation.getX(), (int)(deltaT*maxSpeed*sin) + carLocation.getY());
				return;
			}
			else {
				carLocation.set(trip.getDest().getX(), trip.getDest().getY());
				setStatus(Idle);
				return;
			}
		}
	}
	public int distSqrd(Location loc) {
		return (int)(pow(( loc.getX()-carLocation.getX() ), 2)+pow(( loc.getY()-carLocation.getY() ), 2));
	}
	

}

