import com.profitsoft.entity.Car;
import com.profitsoft.utils.JsonParser;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonParserTest {

    @Test
    public void testParseFileExistsAndCanBeRead() {
        JsonParser parser = new JsonParser();
        try {
            List<?> result = parser.parse("src/main/resources/assets/car.json", Car.class);
            assertNotNull(result);

        } catch (IOException e) {
            fail("Exception not expected for an existing file.");
        }
    }

    @Test
    public void testParseFileDoesNotExist() {
        JsonParser parser = new JsonParser();
        assertThrows(NoSuchFileException.class, () -> parser.parse("src/main/resources/assets/photos34.json", Car.class));
    }


    @Test
    public void testParseNullClass() {
        JsonParser parser = new JsonParser();
        assertThrows(NullPointerException.class, () -> parser.parse("src/main/resources/assets/photos.json", null));
    }

    @Test
    public void testParseNullPath() {
        JsonParser parser = new JsonParser();
        assertThrows(IllegalArgumentException.class, () -> parser.parse("", Car.class));
    }

}
