package reflection.factory;

import org.junit.Assert;
import org.junit.Test;
import org.ruqinhu.reflection.factory.DefaultObjectFactory;

public class ReflectorTest {

  @Test
  public void testGetSetterType() {
    DefaultObjectFactory defaultObjectFactory = new DefaultObjectFactory();
    Entity entity = defaultObjectFactory.create(Section.class);
    entity.setId("zhaorunze");
    Assert.assertEquals("zhaorunze", entity.getId());
    Assert.assertTrue(Entity.class.isAssignableFrom(entity.getClass()));
  }

  interface Entity<T> {
    T getId();
    void setId(T id);
  }

  static abstract class AbstractEntity implements Entity<String> {

    private String id;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }
  }

  static class Section extends AbstractEntity implements Entity<String> {
  }

}
