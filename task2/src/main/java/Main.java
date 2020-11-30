import java.util.Scanner;
import java.util.Vector;


public class Main {

    static Vector<Vector<Double>> matrix;
    static Vector<Double> f;

    private static void readMatrix() {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        matrix = new Vector<>(num);
        matrix.setSize(num);
        matrix.set(0, new Vector<>(3));
        matrix.get(0).setSize(3);
        matrix.get(0).set(0, 0.0);
        matrix.get(0).set(1, in.nextDouble());
        matrix.get(0).set(2, in.nextDouble());
        for (int i = 1; i < num - 1; ++i) {
            Vector<Double> new_row = new Vector<>(3);
            new_row.setSize(3);
            for (int j = 0; j < 3; ++j) {
                new_row.set(j, in.nextDouble());
            }
            matrix.set(i, new_row);
        }

        matrix.set(num - 1, new Vector<>(3));
        matrix.get(num - 1).setSize(3);
        matrix.get(num - 1).set(2, 0.0);
        matrix.get(num - 1).set(0, in.nextDouble());
        matrix.get(num - 1).set(1, in.nextDouble());

        f = new Vector<>(num);
        f.setSize(num);
        for(int i = 0; i < num; ++i) {
            f.set(i, in.nextDouble());
        }
    }

    private static void printEquation(Vector<Vector<Double>> matrix, Vector <Double> f){
        for (int i = 0; i < matrix.size(); ++i) {
            var row = matrix.get(i);
            for (int j = 0; j < matrix.size(); ++j) {
                if (i == j)
                    System.out.print(row.get(1).toString() + "\t");
                else if (i == j + 1)
                    System.out.print(row.get(0).toString() + "\t");
                else if (i == j - 1)
                    System.out.print(row.get(2).toString() + "\t");
                else
                    System.out.print(0 + "\t");
            }
            System.out.print("| " + f.get(i) + "\n");
        }
    }

    public static void main(String[] args) {
        readMatrix();
        printEquation(matrix, f);

        StraightRun strun = new StraightRun(matrix, f);
        ReverseRun rvrun = new ReverseRun(matrix, f);
        int num = matrix.size();
        int p = num / 2;
        strun.setRange(0, p);
        rvrun.setRange(p, num - 1);
        strun.run();
        rvrun.run();


        try {
            strun.join();
            rvrun.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println();
        Vector<Double> alpha = strun.getAlpha();
        Vector<Double> beta = strun.getBeta();
        Vector<Double> ksi = rvrun.getKsi();
        Vector<Double> theta = rvrun.getTheta();
        Double x_p = (alpha.get(p + 1) * theta.get(0) + beta.get(p + 1)) / (1 - alpha.get(p + 1) * ksi.get(0));
        FinderStraight finderStraight = new FinderStraight(alpha, beta, x_p);
        FinderReverse finderReverse = new FinderReverse(ksi, theta, x_p);

        finderStraight.start();
        finderReverse.start();

        try {
            finderReverse.join();
            finderStraight.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
        for (Double i: finderStraight.getX()) System.out.print(i + " ");
        for (Double i: finderReverse.getX()) System.out.print(i + " ");
    }
}