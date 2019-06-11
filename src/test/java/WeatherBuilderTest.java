import org.junit.Assert;
import org.junit.Test;
import ru.ariona.WeatherBuilder;

public class WeatherBuilderTest {
    @Test
    public void getWeather() {
        Assert.assertEquals("Город не найден", WeatherBuilder.getWeather("hhh"));
    }
}
