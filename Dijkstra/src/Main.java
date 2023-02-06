import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    record Pair(int x, int y) {
    }

    static ArrayList<Pair> f;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("n을 입력하세요: ");
        int n = Integer.parseInt(br.readLine());
        int[][] w = new int[n + 1][n + 1];

        System.out.println("n*n 배열 형태로 가중치를 입력하세요(열은 space로, 행은 enter로 구분)");
        String[] temp;
        for (int i = 1; i <= n; i++) {
            temp = br.readLine().trim().split(" ");
            for (int j = 1; j <= n; j++) {
                w[i][j] = Integer.parseInt(temp[j - 1]);
            }
        }

        dijkstra(n, w);

        System.out.print("F의 edge: ");
        for (Pair pair : f) {
            System.out.print("(" + pair.x + ", " + pair.y + ") ");
        }
    }

    static void dijkstra(int n, int[][] w) {
        int i, vnear = 0;
        Pair e;
        int[] touch = new int[n + 1];
        int[] length = new int[n + 1];

        f = new ArrayList<>();

        for (i = 2; i <= n; i++) {
            touch[i] = 1;
            length[i] = w[1][i];
        }

        for (int k = 0; k < n - 1; k++) {
            int min = -1;
            for (i = 2; i <= n; i++) {
                if (length[i] >= 0 && (length[i] < min || min  == -1)) {
                    min = length[i];
                    vnear = i;
                }
            }

            e = new Pair(touch[vnear], vnear);
            f.add(e);

            for (i = 2; i <= n; i++) {
                if (length[i] != -2 && (w[vnear][i] != -1 && length[vnear] != -1) &&
                        (length[i] == -1 || length[vnear] + w[vnear][i] < length[i])) {
                    length[i] = length[vnear] + w[vnear][i];
                    touch[i] = vnear;
                }
            }
            length[vnear] = -2;
        }

    }

}