package metrics;

import java.util.List;

public class TaxiCabMetric implements iMetric<Double> {
    @Override
    public double getDistance(List<Double>  a, List<Double>  b) {
        double distance = 0;

        for(int i = 0; i < a.size(); i++ ){
            double diff = a.get(i) - b.get(i);
            distance += Math.abs(diff);
        }
        
        return distance;
    }
}
