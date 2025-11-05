import java.util.*;

class InvalidDiscountCodeException extends Exception
{
    public InvalidDiscountCodeException(String message) {
        super(message);
    }
}

class Coupon
{
    private String validCode[] = {"SAVE10", "WELCOME20", "FESTIVE30"};

    public void apply(String code) throws InvalidDiscountCodeException
    {
        boolean isValid = false;
        for (String valid : validCode)
        {
            if (valid.equals(code))
            {
                isValid = true;
                break;
            }
        }
        if (!isValid)
        {
            throw new InvalidDiscountCodeException("The provided discount code is invalid.");
        }
        else
        {
            System.out.println("Coupon Applied Successfully");
        }
    }
}

public class InvalidDiscountCode
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        Coupon coupon = new Coupon();
        System.out.println("Enter Discount Code:");
        String code = sc.nextLine();
        try
        {
            coupon.apply(code);
        }
        catch (InvalidDiscountCodeException e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            sc.close();
        }
    }
}