//package metrics;
//
//import java.util.List;
//
//public class BoostMetric {
//    @Override
//    public double getDistance(List<Double> a, List<Double> b) {
//        int boostIdx = a.size()+1;
//        double dist = 0;
//
//        for(int i = 0; i < a.size(); i++ ){
//            double distance = Math.abs(a.get(i) - b.get(i));
//
//            if(distance < .3){
//                boostIdx--;
//            }
//        }
//        for(int i = 0; i < a.size(); i++ ){
//            double distance = Math.abs(a.get(i) - b.get(i));
//
//            dist +=  Math.pow(distance, boostIdx);
//        }
//
//        return dist / (double)a.size();
//    }
//}
//
///*
//\frac{\sum(x{_{ai}}-x{_{bi}})^{(n+1)-|\left \{ x{_{ai}} \epsilon X1,x{_{bi}} \epsilon X2 : (x{_{ai}}-x{_{bi}}) > 0.2 \right \}|})}{n}
// */