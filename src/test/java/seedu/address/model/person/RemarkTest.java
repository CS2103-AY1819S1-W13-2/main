package seedu.address.model.person;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class RemarkTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Remark(null));
    }

    @Test
    public void equals() {

        // same object
        Remark remark = new Remark("test remark");
        Remark sameRemark = remark;
        assertEquals(remark, sameRemark);
        assertEquals(remark, remark);

        // different object, same value
        remark = new Remark("test remark");
        Remark sameValueRemark = new Remark("test remark");
        assertEquals(remark, sameValueRemark);

        // different object, different value
        remark = new Remark("test remark");
        Remark diffRemark = new Remark("not the same value");
        assertNotEquals(remark, diffRemark);

    }
}
