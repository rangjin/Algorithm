import java.io.*;

public class Main {

    static int[] d;
    static int[][] p;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("n을 입력하세요: ");
        int n = Integer.parseInt(br.readLine());

        d = new int[n + 1];
        p = new int[n + 1][n + 1];

        System.out.print("d 배열(n + 1 칸)을 입력하세요(space 로 구분): ");

        String[] temp = br.readLine().trim().split(" ");
        for (int i = 0; i <= n; i++) {
            d[i] = Integer.parseInt(temp[i]);
        }

        System.out.println("행렬들의 곱셈에 필요한 최소 곱셈 횟수: " + minmult(n));
        System.out.print("최소 곱셈 횟수를 가지는 곱셈 순서: ");
        order(1, n);
    }

    static int minmult(int n) {
        int i, j, k, diagonal;
        int[][] m  =  new int[n + 1][n + 1];
        for (i = 1; i <= n; i++) {
            m[i][i] = 0;
        }

        for (diagonal = 1; diagonal <= n - 1; diagonal++) {
            for (i = 1; i <= n - diagonal; i++) {
                j = i + diagonal;
                for (k = i; k <= j - 1; k++) {
                    if (p[i][j] == 0 || m[i][k] + m[k + 1][j] + d[i - 1] * d[k] * d[j] < m[i][j]) {
                        m[i][j] = m[i][k] + m[k + 1][j] + d[i - 1] * d[k] * d[j];
                        p[i][j] = k;
                    }
                }
            }
        }

        return m[1][n];
    }

    static void order(int i, int j) {
        if (i == j) {
            System.out.print("A" + i);
        } else {
            int k = p[i][j];
            System.out.print("(");
            order(i, k);
            order(k + 1, j);
            System.out.print(")");
        }
    }

}