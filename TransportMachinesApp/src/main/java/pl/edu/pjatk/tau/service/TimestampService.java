package pl.edu.pjatk.tau.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.TreeMap;

import pl.edu.pjatk.tau.domain.Car;
import pl.edu.pjatk.tau.domain.CarTimestamp;

public class TimestampService extends CarService implements ITimestampService {
	
	//CarTimestamp database
	private TreeMap<Integer, CarTimestamp> carsT = new TreeMap<Integer, CarTimestamp>();
		
	public TreeMap<Integer, CarTimestamp> getCarsTime() {
		
//		//get Cars TreeMap object
//		TreeMap<Integer, Car> cars = super.getCars();
//		
//		//copy Car object to CarTimestamp object
//		for(Map.Entry<Integer, Car> entry : cars.entrySet()) {
//			
//			//get Car
//			int key = entry.getKey();
//			Car car = cars.get(key);
//			
//			//cast Car to CarTimestamp
//			CarTimestamp carT = carCasting(car);
//			
//			//add CarTimestamp to TreeMap obj
//			carsT.put(key, carT);
//			} 
		
		return carsT;
	}
	
	public int create(Car car, LocalDateTime timestamp) {
		
		//add Car into super class object and get operation return code
		int ret = super.create(car);
		
		//get cars from super class object TreeMap database
		TreeMap<Integer, Car> cars = super.getCars();
		
		//clear cars with timestamps database to prepare for loop
		carsT.clear();
		
		//clone Car object form superclass database to this database TreeMap
		for(Map.Entry<Integer, Car> entry : cars.entrySet()) {
			//get car from superclass and cast from Car object to CarTimestamp object 
			CarTimestamp carT = carCasting(entry.getValue());
			//    bug
			//add timestamp to CarTimestamp object
			carT.setWriteTimestamp(timestamp);
			//read car id from superclass
			int id = entry.getKey();
			//add CarTimestamp into this TreeMap database
			carsT.put(id, carT);
		}
		
		return ret;
	}
	
//	@Override
//	public int create(Car car) {
//		//casting Car object for Car with timestamps object 
//		CarTimestamp carT = carCasting(car);
//		
//		//create timestamp for written car
//		LocalDateTime now = LocalDateTime.now();
//		carT.setWriteTimestamp(now);
//		
//		//add extended by timestamps Car object into db
//		int result = super.create(carT);
//		
//		//filter super class Car for find id 
//		int idx = -1;
//		Car carLoop = (Car) carT;
//		for (Map.Entry<Integer, Car> entry : super.getCars().entrySet()) {
//			//if it's the same Car object then return it id
//			if(carLoop.equals(entry.getValue())) {
//				idx = entry.getKey();
//			}
//		}
//		
//		//put Car with original id and set of timestamps
//		carsT.put(idx, carT);
//		return result;
//	}
	
	public CarTimestamp carCasting(Car car) {
		CarTimestamp result = new CarTimestamp();
		result.setId(car.getId());
		result.setMark(car.getMark());
		result.setModel(car.getModel());
		result.setProducentCountry(car.getProducentCountry());
		result.setYearOfProduction(car.getYearOfProduction());
		result.setColor(car.getColor());
		result.setEngineType(car.getEngineType());
		result.setGearboxType(car.getGearboxType());
		result.setMaxSpeed(car.getMaxSpeed());
		result.setSegmentType(car.getSegmentType());
		result.setProductVersion(car.getProductVersion());
		result.setPrice(car.getPrice());
		return result;		
	}
	
	public LocalDateTime actualTime() {
		return LocalDateTime.now();
	}
	
	public String dateFormatter(LocalDateTime now) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String result = now.format(formatter);
		return result;
	}
}

