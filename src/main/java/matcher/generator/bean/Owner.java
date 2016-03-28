package matcher.generator.bean;

import com.google.gson.annotations.Expose;
import ru.yandex.qatools.processors.matcher.gen.annotations.GenerateMatcher;

/**
 * Created by Funker on 28.03.2016.
 */

public class Owner {

    @GenerateMatcher
    private String email;

    @Expose
    private String uid;

    private String name;

    public String getEmail() {
        return email;
    }

    public String getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

}
