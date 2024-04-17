import com.profitsoft.entity.Car;
import com.profitsoft.utils.CalculationStatistics;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class CalculationStatisticTest {
    private CalculationStatistics calcStatistics;
    private ArrayList<Car> cars;
    private ArrayList<Car> emptyCars;

    @Before
    public void setUp() {
        calcStatistics = new CalculationStatistics();
        cars = new ArrayList<>(Arrays.asList(
                new Car("Toyota", "Camry", 2018),
                new Car("Honda", "Civic", 2019),
                new Car("Ford", "Fusion", 2017),
                new Car("Chevrolet", "Malibu", 2020),
                new Car("Nissan", "Altima", 2016),
                new Car("Hyundai", "Sonata", 2019),
                new Car("Kia", "Optima", 2018),
                new Car("Volkswagen", "Jetta", 2021),
                new Car("BMW", "3 Series", 2020),
                new Car("Mercedes-Benz", "C-Class", 2017),
                new Car("Toyota", "Camry", 2018),
                new Car("Honda", "Civic", 2019),
                new Car("Ford", "Fusion", 2017),
                new Car("Chevrolet", "Malibu", 2020),
                new Car("Nissan", "Altima", 2016),
                new Car("Hyundai", "Sonata", 2019),
                new Car("Kia", "Optima", 2018),
                new Car("Volkswagen", "Jetta", 2021),
                new Car("BMW", "3 Series", 2020),
                new Car("Mercedes-Benz", "C-Class", 2017)
        ));
        emptyCars = new ArrayList<>();
    }

    @Test
    public void testEmptyList() {
        assertThrows(IllegalArgumentException.class, () -> calcStatistics.getAttributeCount(emptyCars, "brand"));
    }

    @Test
    public void testEmptyAttribute() {
        assertThrows(IllegalArgumentException.class, () -> calcStatistics.getAttributeCount(cars, ""));
    }

    @Test
    public void testNonNullAttributeValues() {
        HashMap<String, Integer> result = calcStatistics.getAttributeCount(cars, "brand");
        assertEquals(10, result.size());
        assertEquals(2, (int) result.get("Toyota"));
        assertEquals(2, (int) result.get("Honda"));
        assertEquals(2, (int) result.get("Ford"));
        assertEquals(2, (int) result.get("Chevrolet"));
        assertEquals(2, (int) result.get("Nissan"));
        assertEquals(2, (int) result.get("Hyundai"));
        assertEquals(2, (int) result.get("Kia"));
        assertEquals(2, (int) result.get("Volkswagen"));
        assertEquals(2, (int) result.get("BMW"));
        assertEquals(2, (int) result.get("Mercedes-Benz"));
    }


}
