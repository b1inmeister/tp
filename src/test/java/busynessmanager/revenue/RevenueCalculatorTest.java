//@@author LEESY02
package busynessmanager.revenue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

import busynessmanager.managers.InventoryManager;
import busynessmanager.managers.SalesManager;


public class RevenueCalculatorTest {
    InventoryManager im;
    SalesManager sm;
    RevenueCalculator rc;

    @BeforeEach
    public void setUp() throws Exception {
        this.im = new InventoryManager();
        this.sm = new SalesManager(im);
        this.rc = new RevenueCalculator(sm);
    }

    @Test
    public void computeProductRevenue_individualRevenueTest_success() {
        im.addProduct("bean", 100, 0.6);
        im.addProduct("donkey", 6000, 900);
        im.addProduct("bingbangbong", 1, 10000);
        sm.recordSale("ID_0010", 50);
        sm.recordSale("ID_0011", 10);
        sm.recordSale("ID_0012", 1);

        im.printProducts();

        assertEquals(
            30,
            rc.computeProductRevenue("ID_0010")
        );

        assertEquals(
            9000,
            rc.computeProductRevenue("ID_0011")
        );

        assertEquals(
            10000,
            rc.computeProductRevenue("ID_0012")
        );
    }

    @Test
    public void computeTotalRevenue_totalRevenueTest_success() {
        im.addProduct("bean", 100, 0.6); // ID_0010
        im.addProduct("donkey", 6000, 900);
        im.addProduct("bingbangbong", 1, 10000);
        sm.recordSale("ID_0013", 50);
        sm.recordSale("ID_0014", 10);
        sm.recordSale("ID_0015", 1);

        assertEquals(
            19030,
            rc.computeTotalRevenue()
        );
    }
}
