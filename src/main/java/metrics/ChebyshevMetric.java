package metrics;

import java.util.List;

public class ChebyshevMetric implements iMetric<Double>{
    @Override
    public double getDistance(List<Double> a, List<Double> b) {
        double maxDistance = 0;
        for(int i = 0; i < a.size(); i++ ){
            double distance = Math.abs(a.get(i) - b.get(i));
            if(distance > maxDistance)
                maxDistance = distance;
        }
        return maxDistance;
    }
}
