package matcher.generator.test;

import matcher.generator.bean.MobMatchers;
import org.hamcrest.Matcher;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class MobTest {

    @DataProvider
    public static Object[][] matchers() {
        return new Object[][]{
                {"withValByte"},
                {"withValueByte"},
                {"withValChar"},
                {"withValueCharacter"},
                {"withValInt"},
                {"withValueInteger"},
                {"withValLong"},
                {"withValueLong"},
                {"withValFloat"},
                {"withValueFloat"},
                {"withValDouble"},
                {"withValueDouble"},
                {"withValBoolean"},
                {"withValueBoolean"}
        };
    }

    @Test(dataProvider = "matchers")
    public void shouldFindMatcher(String matcherName) throws Exception {
        assertThat(MobMatchers.class.getDeclaredMethod(matcherName, Matcher.class), notNullValue());
    }
}
