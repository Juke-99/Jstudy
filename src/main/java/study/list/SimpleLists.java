package study.list;

import java.util.Collections;
import java.util.List;

public class SimpleLists {
    public <T> List<T> createUnmodifiableList(List<T> list) {
        return Collections.unmodifiableList(list);
    }
}
