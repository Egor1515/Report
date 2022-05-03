
import com.github.javafaker.Faker;
import com.github.javafaker.PhoneNumber;
import io.netty.channel.local.LocalAddress;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@UtilityClass
public class DataGenerator {

    @UtilityClass
    public static class Registration {
        public static RegistrationInfo generateInfo(String locale) {
            Faker faker = new Faker(new Locale(locale));
            var user = new RegistrationInfo(faker.name().fullName(),
                    faker.numerify("+7##########"),
                    faker.address().city());
            return user;
        }

        public static String generateDate(int days) {
            return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }

    }
}
