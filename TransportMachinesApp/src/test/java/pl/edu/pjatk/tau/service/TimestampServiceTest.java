package pl.edu.pjatk.tau.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import pl.edu.pjatk.tau.domain.Car;
import pl.edu.pjatk.tau.domain.CarTimestamp;
import pl.edu.pjatk.tau.domain.factory.BMW.BMWFactory;
import pl.edu.pjatk.tau.domain.factory.Renault.RenaultFactory;

@RunWith(PowerMockRunner.class)
@PrepareForTest({CarTimestamp.class, LocalDateTime.class, TimestampService.class})
public class TimestampServiceTest {
		
	public CarTimestamp cars;
	public TimestampService service;
	
	public Car mockCar = PowerMockito.mock(Car.class);
	public LocalDateTime mockTime = PowerMockito.mock(LocalDateTime.class);
	
//	//prepare Mock
//	@Before
//	public void setUp() {
//		cars = new CarTimestamp();
//		service = new TimestampService();
//		bmw = new Car(1, new BMWFactory());
//		renault = new Car(1, new RenaultFactory());
//		
//		PowerMockito.mockStatic(LocalDateTime.class);
//		
//		  readTime = PowerMockito.mock(LocalDateTime.class);
//		  retReadT = PowerMockito.mock(LocalDateTime.class);
//		  writeTime = PowerMockito.mock(LocalDateTime.class);
//		  //retWriteT = PowerMockito.mock(LocalDateTime.class);
//		  updateTime = PowerMockito.mock(LocalDateTime.class);
//		  retUpdateT = PowerMockito.mock(LocalDateTime.class);
//		
//		PowerMockito.when(retReadT.getHour()).thenReturn(1);
//		PowerMockito.when(readTime.now()).thenReturn(retReadT);
//		
//		PowerMockito.when(writeTime.getHour()).thenReturn(2);
//		//PowerMockito.when(writeTime.now()).thenReturn(retWriteT);
//		
//		PowerMockito.when(retUpdateT.getHour()).thenReturn(3);
//		PowerMockito.when(updateTime.now()).thenReturn(retUpdateT);
//	}
	
	
	@Test
	public void shouldPass() {
		assertTrue(true);
	}
	
	
	@Test
	public void shouldInitializeAllTimestampsDirectlyAddedIntoDatabase() {
		cars = new CarTimestamp();
		cars.setReadTimestamp(mockTime);
		assertNotNull(cars.getReadTimestamp());
		
		cars.setWriteTimestamp(mockTime);
		assertNotNull(cars.getWriteTimestamp());
		
		cars.setUpdateTimestamp(LocalDateTime.now());
		assertNotNull(cars.getUpdateTimestamp());
	}
	
	@Test
	public void shouldReturnCarWithTimestampAfterCallCreateMethod() {
		
		service = new TimestampService();
		PowerMockito.when(mockCar.getId()).thenReturn(1);
		service.create(mockCar, mockTime);
		assertNotNull(service.readById(1));
		assertNotNull(service.getCarsTime().get(1));
		assertNotNull(service.getCarsTime().get(1).getWriteTimestamp());
		assertNull(service.getCarsTime().get(1).getReadTimestamp());

	}
	
	@Ignore
	@Test
	public void shouldReturnCorrectTimestampAfterCallCreateMethod() {
		renault = new Car(0, new RenaultFactory());
		//assertEquals(1, service.create(renault, writeTime));
		//assertNotNull(service.getCarsTime().get(0));
		//assertEquals(service.getCars().get(0));
	}
}
