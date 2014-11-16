package my.company.steps;

import ru.yandex.qatools.allure.events.StepFinishedEvent;
import ru.yandex.qatools.allure.events.StepStartedEvent;
import ru.yandex.qatools.allure.experimental.LifecycleListener;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Dmitry Baev charlie@yandex-team.ru
 *         Date: 03.10.14
 */
public class EachStepLogger extends LifecycleListener {

    private Deque<String> names = new LinkedList<>();

    @Override
    public void fire(StepStartedEvent event) {
        names.push(event.getName());
        System.out.println(getOffset() + ">>> step  started " + names.getFirst());
    }

    @Override
    public void fire(StepFinishedEvent event) {
        System.out.println(getOffset() + "<<<    step finished " + names.poll());
    }

    private String getOffset() {
        return new String(new char[names.size() == 0 ? 0 : names.size() - 1]).replaceAll("\0", " ");
    }
}
