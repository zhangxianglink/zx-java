package style.code7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author nuc
 */
public class Example1 {

    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        List<Object> objects = Collections.unmodifiableList(list);
        System.out.println(objects);
        objects.add(4);
    }
}
