import java.util.ArrayList;
import java.util.List;

interface IObserver {
    void update(String stockSymbol, double price);
}


interface ISubject {
    void registerObserver(IObserver observer, String stockSymbol);
    void removeObserver(IObserver observer, String stockSymbol);
    void notifyObservers(String stockSymbol, double price);
}



class StockExchange implements ISubject {
    private class ObserverEntry {
        IObserver observer;
        String stockSymbol;

        ObserverEntry(IObserver observer, String stockSymbol) {
            this.observer = observer;
            this.stockSymbol = stockSymbol;
        }
    }

    private List<ObserverEntry> observers = new ArrayList<>();

    @Override
    public void registerObserver(IObserver observer, String stockSymbol) {
        observers.add(new ObserverEntry(observer, stockSymbol));
        System.out.println("Наблюдатель " + observer.getClass().getSimpleName() + " подписан на акцию " + stockSymbol);
    }


    @Override
    public void removeObserver(IObserver observer, String stockSymbol) {
        observers.removeIf(entry -> entry.observer.equals(observer) && entry.stockSymbol.equals(stockSymbol));
        System.out.println("Наблюдатель " + observer.getClass().getSimpleName() + " отменил подписку на акцию " + stockSymbol);
    }

    @Override
    public void notifyObservers(String stockSymbol, double price) {
        for (ObserverEntry entry : observers) {
            if (entry.stockSymbol.equals(stockSymbol)) {
                entry.observer.update(stockSymbol, price);
            }
        }
    }



    public void changeStockPrice(String stockSymbol, double newPrice) {
        System.out.println("Изменение цены акции " + stockSymbol + ": новая цена " + newPrice);
        notifyObservers(stockSymbol, newPrice);
    }
}





class Trader implements IObserver {
    private final String name;

    public Trader(String name) {
        this.name = name;
    }

    @Override
    public void update(String stockSymbol, double price) {
        System.out.println("Трейдер " + name + " получил обновление: акция " + stockSymbol + " стоит " + price);
    }
}


class TradingRobot implements IObserver {
    private final String name;
    private final double buyThreshold;
    private final double sellThreshold;

    public TradingRobot(String name, double buyThreshold, double sellThreshold) {
        this.name = name;
        this.buyThreshold = buyThreshold;
        this.sellThreshold = sellThreshold;
    }

    @Override
    public void update(String stockSymbol, double price) {
        if (price <= buyThreshold) {
            System.out.println("Робот " + name + " покупает акцию " + stockSymbol + " по цене " + price);
        } else if (price >= sellThreshold) {
            System.out.println("Робот " + name + " продает акцию " + stockSymbol + " по цене " + price);
        } else {
            System.out.println("Робот " + name + " игнорирует акцию " + stockSymbol + ", текущая цена " + price);
        }
    }
}





public class Observer {
    public static void main(String[] args) {
        StockExchange stockExchange = new StockExchange();


        Trader trader1 = new Trader("Shyngys");
        Trader trader2 = new Trader("Almaz");
        TradingRobot robot1 = new TradingRobot("Робот1", 100, 150);
        TradingRobot robot2 = new TradingRobot("Робот2", 80, 120);


        stockExchange.registerObserver(trader1, "AAPL");
        stockExchange.registerObserver(trader2, "AAPL");
        stockExchange.registerObserver(robot1, "AAPL");
        stockExchange.registerObserver(robot2, "AAPL");


        stockExchange.changeStockPrice("AAPL", 95);
        stockExchange.changeStockPrice("AAPL", 155);
        stockExchange.changeStockPrice("AAPL", 110);


        stockExchange.removeObserver(trader1, "AAPL");

        stockExchange.changeStockPrice("AAPL", 90);



    }
}
