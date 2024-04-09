package yesko.project.OnlineShop;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import static yesko.project.OnlineShop.utils.Constants.formatter;
import static yesko.project.OnlineShop.utils.Constants.nowUtcPlusOne;

public class Main {
    public static void main(String[] args) {
        Timestamp modified = new Timestamp(System.currentTimeMillis());
        System.out.println(formatter.format(modified));
    }
}
