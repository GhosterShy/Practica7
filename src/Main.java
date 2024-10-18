interface  IcostCalculate
{
    void Calculate(int passengers,int typeClass, boolean bag,boolean child,boolean pensioner);
}

class Plane implements  IcostCalculate{

    @Override
    public void Calculate(int passengers, int typeClass, boolean bag,boolean child,boolean pensioner) {
        if (typeClass == 1) {
            if (bag = false) {
                if(pensioner == true || child == true)
                {
                    System.out.println(200 * passengers + 600 * 0.25);
                }
                else {
                    System.out.println(200 * passengers + 900);
                }
            } else {
                System.out.println(200 * passengers + 600);
            }

        }
    }
}



class Bus implements IcostCalculate
{


    @Override
    public void Calculate(int passengers, int typeClass, boolean bag,boolean child,boolean pensioner) {
        if (typeClass == 1) {
            if (bag = false) {
                if(pensioner == true || child == true)
                {
                    System.out.println(50 * passengers + 600 * 0.5);
                }
                else {
                    System.out.println(50 * passengers + 600);
                }
            } else {
                System.out.println(50 * passengers  + 400);
            }
        }

    }
}


class Poesd implements IcostCalculate
{


    @Override
    public void Calculate(int passengers, int typeClass, boolean bag,boolean child,boolean pensioner) {
        if (typeClass == 1) {
            if (bag = false) {
                if(pensioner == true || child == true)
                {
                    System.out.println(100 * passengers + 200 * 0.65);
                }
                else {
                    System.out.println(200 * passengers + 200);
                }
            } else {
                System.out.println(100 * passengers  + 200);
            }
        }

    }
}


class TravelBookingContext{
    private  IcostCalculate icostCalculate;
    public void  setCostCalculation(IcostCalculate calculation)
    {
        this.icostCalculate = calculation;
    }

    public  void getCost(int passengers, int TypeClass, boolean bag,boolean child,boolean pensioner)
    {
        if(icostCalculate == null)
        {
            System.out.println("Error");
        }
        icostCalculate.Calculate(passengers,TypeClass,bag,child,pensioner);
    }

}


public class Main {
    public static void main(String[] args) {

        TravelBookingContext travelBookingContext = new TravelBookingContext();
        travelBookingContext.setCostCalculation(new Plane());
        travelBookingContext.getCost(1,1,true,true,false);



    }
}


