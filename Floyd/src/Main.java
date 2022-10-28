import java.io.*;

public class Main {

    static int[][] w, d, p;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("n을 입력하세요: ");
        int n = Integer.parseInt(br.readLine());
        w = new int[n + 1][n + 1];
        d = new int[n + 1][n + 1];
        p = new int[n + 1][n + 1];

        System.out.println("n*n 배열 형태로 가중치를 입력하세요(열은 space로, 행은 enter로 구분)");
        String[] temp;
        for (int i = 1; i <= n; i++) {
            temp = br.readLine().trim().split(" ");
            for (int j = 1; j <= n; j++) {
                w[i][j] = Integer.parseInt(temp[j - 1]);
            }
        }

        floyd2(n);
        System.out.println("\n배열 D");
        for (int i = 1; i <= n; i++) {
            for (int j  = 1; j <= n; j++) {
                System.out.printf("%4d ", d[i][j]);
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("배열 P");
        for (int i = 1; i <= n; i++) {
            for (int j  = 1; j <= n; j++) {
                System.out.printf("%4d ", p[i][j]);
            }
            System.out.println();
        }
        System.out.println();

        System.out.print("구하고자 하는 경로의 출발점, 도착점을 입력하세요(space로 구분): ");
        temp = br.readLine().trim().split(" ");
        int q = Integer.parseInt(temp[0]), r = Integer.parseInt(temp[1]);


        System.out.println(q + "부터 " + r + "까지의 최단거리: " + d[q][r]);
        System.out.print(q + "부터 " + r + "까지의 최단거리 경로: " + q + " -> ");
        path(q, r);
        System.out.println(r);
    }

    static void floyd2(int n) {
        int i, j, k;
        for (i = 1; i <= n; i++) {
            for (j = 1; j <= n; j++) {
                p[i][j] = 0;
            }
        }

        d = w;

        for (k = 1; k <= n; k++) {
            for (i = 1; i <= n; i++) {
                for (j = 1; j <= n; j++) {
                    // -1은 inf 로 취급
                    if ((d[i][k] != -1 && d[k][j] != -1) && (d[i][j] == -1 || d[i][k] + d[k][j] < d[i][j])) {
                        p[i][j] = k;
                        d[i][j] = d[i][k] + d[k][j];
                    }
                }
            }
        }
    }

    static void path(int q, int r) {
        if (p[q][r] != 0) {
            path(q, p[q][r]);
            System.out.print(p[q][r] + " -> ");
            path(p[q][r], r);
        }
    }

}