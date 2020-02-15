package schedule.util;

import org.junit.Test;
import org.ruqinhu.schedule.util.ClassUtil;

public class ClassUtilTest {

    @Test
    public void testGetClasses() {
        System.out.println(ClassUtil.getClasses(ClassUtil.class.getPackage().getName()));
    }

}
