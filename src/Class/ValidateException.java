package Class;

import java.math.BigDecimal;

class ValidateException extends Exception {
    public int ChangeMonthException(){
        System.out.println("Превышен максимальный срок, новый срок 36 месяцев");
        return 36;
    }
    public BigDecimal ChangeSumException(){
        System.out.println("Превышена максимальная сумма, новая сумма 200,000");
        return new BigDecimal("200000.0");
    }

}