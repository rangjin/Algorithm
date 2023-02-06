import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    record Pair(int x, int y) {
    }
    static int[][] graph, u;
    static ArrayList<Pair> f;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("정점의 개수 n을 입력하세요: ");
        int n = Integer.parseInt(br.readLine());
        u = new int[n + 1][2];

        System.out.print("간선의 개수 m을 입력하세요: ");
        int m = Integer.parseInt(br.readLine());

        graph = new int[m][3];

        System.out.println("간선이 있는 두 노드와 가중치를 입력하세요(노드의 번호 2개와 가중치를 space로 구분하여 m줄 입력)");
        String[] temp;
        for (int k = 0; k < m; k++) {
            temp = br.readLine().trim().split(" ");
            graph[k][0] = Integer.parseInt(temp[0]);
            graph[k][1] = Integer.parseInt(temp[1]);
            graph[k][2] = Integer.parseInt(temp[2]);
        }

        f = new ArrayList<>();

        Arrays.sort(graph, Comparator.comparingInt(a -> a[2]));

        for (int i = 1; i <= n; i++) {
            u[i][0] = i;
            u[i][1] = 0;
        }

        kruskal(n);

        System.out.print("F = { ");
        for (int i = 0; i < n - 1; i++) {
            System.out.print("(" + f.get(i).x + ", " + f.get(i).y + ") ");
        }
        System.out.println("}");
    }

    static void kruskal(int n) {
        int k = 0, i, j, p, q;
        while(f.size() < (n - 1)) {
            i = graph[k][0];
            j = graph[k][1];

            p = find(i);
            q = find(j);

            if (p != q) {
                merge(p, q);
                f.add(new Pair(i, j));
            }

            k++;
        }
    }

    static void merge(int a, int b) {
        if (u[a][1] < u[b][1]) {
            u[a][0] = b;
            u[a][1] = -1;
        } else if (u[a][1] == u[b][1]) {
            u[b][0] = a;
            u[a][1]++;
            u[b][1] = -1;
        } else {
            u[b][0] = a;
            u[b][1] = -1;
        }
    }

    static int find(int x) {
        if (u[x][0] == x) {
            return x;
        } else {
            return find(u[x][0]);
        }
    }

}