public class challenge
{
    public static void main(String[] args)
    {
        boolean isValidCard = validateCard(1234567890123456L);

        System.out.println("Is 1234567890123452 a valid card number? It turns out: " + isValidCard);

        System.out.println("Is 1234567890123452 a valid card number? " + validateCard(1234567890123452L));
    }

    public static boolean validateCard(Long cardNumber)
    {
        /*
            5. Subtract the last digit of the sum from 10; this should be equal to the check digit from step 1
        */

        StringBuffer cardString = new StringBuffer(cardNumber.toString());

        if ((cardString.length()<14)||(cardString.length()>19))
        {
            System.out.println("Invalid card number;card number not within bounds");
            return false;
        }
        System.out.println("====BEGIN CARD CHECK====");
        System.out.println("Card number is: " + cardString);

        //LUHN TEST:
        //Store and remove the check digit
        int checkDigit = Integer.parseInt(cardString.substring(cardString.length()-1,cardString.length()));
        cardString.replace(cardString.length()-1,cardString.length(),"");
        System.out.println("Check digit is: " + checkDigit);
        System.out.println("Card number is now: " + cardString);

        //Reverse the number
        cardString = cardString.reverse();
        System.out.println("Reversed number is " + cardString);

        //Double numbers in "odd" positions
        for (int x=0;x<cardString.length();x++)
        {
            //Only bother if the position is "odd"
            if(((x+1)%2)==1)
            {
                //get the number from this index of the string
                Integer newNum = Integer.parseInt(cardString.substring(x,x+1));

                //double it
                newNum = newNum*2;

                //If double digit, add together; otherwise, leave it
                String newAsString = newNum.toString();
                if (newAsString.length()>1)
                {
                    newNum = Integer.parseInt(newAsString.substring(0,1)) + Integer.parseInt(newAsString.substring(1,2));
                }

                //Put the new number back in where we got it
                cardString.replace(x,x+1,newNum.toString());
            }
        }
        System.out.println("Card string after selective doubling is: " + cardString);

        //Add all of the digits
        Integer sum = 0;
        for (int x=0;x<cardString.length();x++)
        {
            sum += Integer.parseInt(cardString.substring(x,x+1));
        }

        String sumLastDigit = sum.toString();
        sumLastDigit = sumLastDigit.substring(sumLastDigit.length()-1,sumLastDigit.length());
        int lastDigitAsInt = Integer.parseInt(sumLastDigit);

        System.out.println("Check digit is: " + checkDigit);
        System.out.println("Last digit is " + lastDigitAsInt);
        System.out.println("10 minus last digit is: 10 - " + lastDigitAsInt + " = " + (10-lastDigitAsInt));

        if ((10-lastDigitAsInt)==checkDigit)
            return true;
        else
            return false;
    }
}
