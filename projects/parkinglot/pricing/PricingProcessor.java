package projects.parkinglot.pricing;

import java.util.HashMap;
import java.util.Map;
import projects.parkinglot.parking.Ticket;

public class PricingProcessor {
    private final Map<PricingStrategyEnum, IPricingStrategy> objects = new HashMap<>();
    
    private static class PricingProcessorHolder {
        private final static PricingProcessor INSTANCE = new PricingProcessor();
    }

    public static PricingProcessor getPricingInstance() {
        return PricingProcessorHolder.INSTANCE;
    }

    private IPricingStrategy getInstance(PricingStrategyEnum strategy){
        if(!objects.containsKey(strategy)){
            objects.put(strategy, getNewInstance(strategy));
        }
        return objects.get(strategy);
    }

    private IPricingStrategy getNewInstance(PricingStrategyEnum strategy){
        switch (strategy) {
            case TIME_BASED -> {
                return new TimeBasedPayment();
            }
            default -> throw new AssertionError();
        }
    }

    public double calculateAmount(PricingStrategyEnum type, Ticket ticket) {
        IPricingStrategy strategy = this.getInstance(type);
        return strategy.calculateAmount(ticket);
    }
}
