#include <iostream>
#include <vector>
using namespace std;

template <typename T>
struct Node {
    T value;
    Node* children;
    int numberChildren;
    Node* root;
};

vector<int> usedValues(Node<int*> &tree, vector<int> vals = {}) {
    vals.push_back(*tree.value);
    vals.push_back(*(tree.value + 1));
    vals.push_back(*(tree.value + 2));
    if (tree.root) {
        return usedValues(*tree.root,vals);
    } else {
        return vals;
    }
}

int main() {

    Node<int*> tree;
    int bins[3] = {2,3,1};
    tree.value = bins;
    Node<int*> tree2;
    tree.children = &tree2;
    int childBins[3] = {4,6,5};
    tree2.value = childBins;
    for(int a: usedValues(tree2)) {
        cout << a;
    }
    return 0;
}

Node<int*> generateTree(int n) {
    Node<int*> res;
    int emptyBins[3] = {};
    res.value = emptyBins;
    return res;
}



void fillTree(int n, Node<int*> &tree) {


}
