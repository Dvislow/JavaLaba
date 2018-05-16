package Class;

import java.math.BigDecimal;
import java.util.Date;

public  class Payment  {

    private Date paymentDate;
    private BigDecimal amount;
    public Payment(){}
    public Payment(Date date, BigDecimal pay) {
        paymentDate=date;
        amount=pay;
    }

    public BigDecimal getAmount() {
        return amount;
    }
    public Date getPaymentDate() {
        return paymentDate;
    }
}