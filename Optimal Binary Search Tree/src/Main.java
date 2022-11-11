import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static double[] p;
    static double[][] a;
    static int[][] r;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("n을 입력하세요: ");
        int n = Integer.parseInt(br.readLine());

        p = new double[n + 1];

        System.out.print("p 배열(n 칸)을 입력하세요(space 로 구분): ");

        String[] temp = br.readLine().trim().split(" ");
        for (int i = 1; i <= n; i++) {
            p[i] = Double.parseDouble(temp[i - 1]);
        }

        r = new int[n + 2][n + 1];
        optsearchtree(n);
        System.out.println("최소 평균 탐색 횟수: " + a[1][n]);
    }

    static void optsearchtree(int n) {
        int i, j, k, diagonal;
        a = new double[n + 2][n + 1];
        for (i = 1; i <= n; i++) {
            a[i][i - 1] = 0;
            a[i][i] = p[i];
            r[i][i] = i;
            r[i][i - 1] = 0;
        }

        a[n + 1][n] = 0;
        r[n + 1][n] = 0;
        double sum;

        for (diagonal = 1; diagonal <= n - 1; diagonal++) {
            for (i = 1; i <= n - diagonal; i++) {
                j = i + diagonal;
                sum = 0;
                for (k = i; k <= j; k++) {
                    sum += p[k];
                    if (r[i][j] == 0 || a[i][k - 1] + a[k + 1][j] < a[i][j]) {
                        a[i][j] = a[i][k - 1] + a[k + 1][j];
                        r[i][j] = k;
                    }
                }
                a[i][j] += sum;
            }
        }
    }

}