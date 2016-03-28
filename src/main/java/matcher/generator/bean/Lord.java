package matcher.generator.bean;

import ru.yandex.qatools.processors.matcher.gen.annotations.GenerateMatcher;

/**
 * Created by Funker on 28.03.2016.
 */
@GenerateMatcher
public class Lord {

    private String name;
    private Integer slavesCount;


    public String getName() {
        return name;
    }

    public Integer getSlavesCount() {
        return slavesCount;
    }
}
