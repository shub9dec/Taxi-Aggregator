package iiitb.ess201a7.r16070;


import java.util.ArrayList;

import iiitb.ess201a7.a7base.*;

public class Fleet16070 extends Fleet {

	private ArrayList<Car16070> carList;
	static private int carId = 1;
	
	public Fleet16070(String colour) {
		super(16070,colour);
		carList = new ArrayList<Car16070>();
	}

	@Override
	public void addCar(int speed) {
		String carIdString = Integer.toString(carId);
		int newCarId = Integer.parseInt("16070"+carIdString);
		carList.add(new Car16070(newCarId, speed));
		carId+=1;
	}

	@Override
	public Car findNearestCar(Location loc) {
		int nearestCarIndex = 0;
		int nearestDistance = Integer.MAX_VALUE;
		boolean flag = false;
		for(int i = 0 ; i < carList.size() ; i++){
			if(carList.get(i).getStatus() == Car.Idle) {
				int tempDistance = carList.get(i).distSqrd(loc);
				if(tempDistance < nearestDistance){
					nearestCarIndex = i;
					nearestDistance = tempDistance;
					flag = true;
				}
			}

		}
		if(flag == true)
			return carList.get(nearestCarIndex);
		return null;
	}


	public ArrayList<? extends Car> getCars(){
		return carList;
	}
}


