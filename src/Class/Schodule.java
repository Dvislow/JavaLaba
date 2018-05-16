package Class;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Schodule implements ScheduleInterface {


    private List<Payment> list=new ArrayList <Payment>();
    private int month;
    private BigDecimal amount;
    private BigDecimal percent;
    private Date dateStart;
    public Schodule(int _months, String _Amount, String _Percent, String _Date){

        try{
            if(_months>36) throw new ValidateException();
            month=_months;
        }
        catch(ValidateException error){
            month=error.ChangeMonthException();

        }

        try{
            if(new BigDecimal(_Amount).compareTo(new BigDecimal("200000"))==1) throw new ValidateException();
            amount=new BigDecimal(_Amount);
        }
        catch(ValidateException error){
            amount=error.ChangeSumException();
        }
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        try {
            dateStart = format.parse(_Date);
        } catch (ParseException error) {
            error.printStackTrace();
        }
        percent=new BigDecimal(_Percent).divide(new BigDecimal("100"));
    }

    public Comparator<Payment> order()
    {
        return new Comparator<Payment>(){

            @Override
            public int compare(Payment arg1, Payment arg2) {
                final Date date1 = arg1.getPaymentDate();
                final Date date2 = arg2.getPaymentDate();
                return date1.compareTo(date2);
            }
        };
    }
    public Comparator<Payment> revertOrder()
    {
        return new Comparator<Payment>() {
            @Override
            public int compare(Payment arg1, Payment arg2) {
                final Date date1 = arg1.getPaymentDate();
                final Date date2 = arg2.getPaymentDate();
                return date2.compareTo(date1);
            }
        };
    }

    //public Schodule(){}


    public void calculate(){
        //аннуиментный платёж одной формулой
        BigDecimal monthPerCent= percent.divide(new BigDecimal("12"), 4, BigDecimal.ROUND_HALF_UP);
        BigDecimal anPayMent=amount.multiply(monthPerCent.add(monthPerCent.divide(monthPerCent.add(new BigDecimal("1.0")).pow(month).subtract(new BigDecimal("1.0")),4, BigDecimal.ROUND_HALF_UP)));
        // округление
        anPayMent=anPayMent.setScale(2,  BigDecimal.ROUND_HALF_UP);
        Calendar tmp= new GregorianCalendar();
        tmp.setTime(dateStart);
        for(int i=0;i<month;i++){

            Date Localtmp= tmp.getTime();
            Payment a=new Payment(Localtmp, anPayMent);
            list.add(a);
            tmp.add(GregorianCalendar.MONTH, 1);
        }

    }

    public List<Payment> getList() {
        return list;
    }
}
