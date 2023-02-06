import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int n, W, maxprofit, numbest;
    static int[] w, p;
    static boolean[] bestset, include;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("물건의 개수 n을 입력하세요: ");
        n = Integer.parseInt(br.readLine());

        System.out.print("가방의 용량 w을 입력하세요: ");
        W = Integer.parseInt(br.readLine());

        maxprofit = 0;
        numbest = 0;

        p = new int[n + 1];
        System.out.print("물건의 가치들을 입력하세요(space로 구분): ");
        String[] temp = br.readLine().trim().split(" ");;
        for (int k = 0; k < n; k++) {
            p[k + 1] = Integer.parseInt(temp[k]);
        }

        w = new int[n + 1];
        System.out.print("물건의 무게들을 입력하세요(space로 구분): ");
        temp = br.readLine().trim().split(" ");
        for (int k = 0; k < n; k++) {
            w[k + 1] = Integer.parseInt(temp[k]);
        }

        bestset = new boolean[n + 1];
        include = new boolean[n + 1];

        knapsack(0, 0, 0);

        System.out.print("Best Set: { ");
        for (int i = 1; i <= n; i++) {
            if (bestset[i] && i <= numbest) {
                System.out.print(i + " ");
            }
        }
        System.out.println("}");
    }

    static void knapsack(int i, int profit, int weight) {
        if (weight <= W && profit > maxprofit) {
            maxprofit = profit;
            numbest = i;
            if (n >= 0) System.arraycopy(include, 1, bestset, 1, n);
        }

        if (promising(i, profit, weight)) {
            include[i + 1] = true;
            knapsack(i + 1, profit + p[i + 1], weight + w[i + 1]);
            include[i + 1] = false;
            knapsack(i + 1, profit, weight);
        }
    }

    static boolean promising(int i, int profit, int weight) {
        if (weight >= W)
            return false;
        else {
            int j = i + 1;
            int bound = profit;
            int totweight = weight;

            while((j <= n) && (totweight + w[j] <= W)) {
                totweight = totweight + w[j];
                bound = bound + p[j];
                j++;
            }

            int k = j;
            if (k <= n) {
                bound = bound + (W - totweight) * (p[k] / w[k]);
            }

            return bound > maxprofit;
        }
    }

}
