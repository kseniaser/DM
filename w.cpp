#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

typedef long long ll;

int const maxn = int(1e5) + 4;
int n, q, s[30][4 * maxn];
vector <int> graph[maxn];
vector <int> used, pos(maxn), old(maxn), lg(4 * maxn);


void dfs(int v) {
	pos[v] = used.size();
	old[pos[v]] = v;
	used.push_back(pos[v]);
	for (int i = 0; i < graph[v].size(); i++) {
		int to = graph[v][i];
		dfs(to);
		used.push_back(pos[v]);
	}
}

int lca(int u, int v) {
	int l = pos[u], r = pos[v];
	if (l > r)
		swap(l, r);
	int k = lg[r - l + 1];
	return old[min(s[k][l], s[k][r - (1 << k) + 1])];
}

int main() {
	freopen("lca_rmq.in", "r", stdin);
	freopen("lca_rmq.out", "w", stdout);
	cin >> n >> q;
	for (int i = 1; i < n; i++) {
		int x;
		cin >> x;
		graph[x].push_back(i);
	}
	dfs(0);
	int m = used.size();
    for (int i = 2; i <= m; i++)
        lg[i] = lg[i / 2] + 1;
    for (int i = 0; i < m; i++)
        s[0][i] = used[i];
    for (int k = 1; k <= lg[n]; k++)
        for (int i = 0; i < m; i++)
            s[k][i] = min(s[k - 1][i], s[k - 1][i + (1 << (k - 1))]);
	int a0, a1, a2, a3, x, y, z, last = 0;
	ll ans = 0;
	cin >> a0 >> a1 >> x >> y >> z;
	for (int i = 1; i <= q; i++) {
		last = lca((a0 + last) % n, a1);
		ans += last;
		last %= n;
		a2 = (x * 1ll * a0 + y * 1ll * a1 + z) % n;
		a3 = (x * 1ll * a1 + y * 1ll * a2 + z) % n;
		a0 = a2, a1 = a3;
	}
	cout << ans << endl;
	return 0;
}