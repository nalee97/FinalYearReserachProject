//import javafx.application.Application;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.geometry.Side;
//import javafx.scene.Group;
//import javafx.scene.Scene;
//import javafx.scene.chart.PieChart;
//import javafx.stage.Stage;
//
//public class PieChartExample extends Research {
//
//    @Override
//    public void start(Stage stage) {
//        // Data
//        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
//                new PieChart.Data("Meaningful %", matchedIdentifiers.size()),
//                new PieChart.Data("Meaningless %", unactualOutput.size()));
//
//        // Create chart
//        PieChart chart = new PieChart(pieChartData);
//        chart.setTitle("Summary of This Source Code");
//        chart.setLegendSide(Side.LEFT);
//
//        // Create scene
//        Scene scene = new Scene(new Group(chart), 600, 400);
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
