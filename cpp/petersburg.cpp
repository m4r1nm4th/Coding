#include <iostream>
#include <functional>
#include <cstdlib>
#include <random>
#include <execution>
#include "pcg-cpp-0.98/include/pcg_random.hpp"
using namespace std;

struct Gameparameter
{
    int initial_capital;
    int fee;
    int rounds;
} gamePara;

struct MonteCarloSimulation
{
    int size;
} simPara;

pcg32 rng(chrono::system_clock::now().time_since_epoch().count());

bool runGame()
{
    int money = gamePara.initial_capital;
    int round = 0;
    do
    {
        round++;
        money -= gamePara.fee;
        int numHeads = 0;
        while (bernoulli_distribution(0.5)(rng) == 1)
        {
            numHeads += 1;
        }
        int win = 1;
        for (int i = 0; i < numHeads; i++)
        {
            win *= 2;
        }
        money += win;
    } while (money >= gamePara.fee && round < gamePara.rounds);
    return money > gamePara.fee;
}

float gameSim()
{
#pragma opm parallel for default(none)
    vector<bool> alive(simPara.size, true);
    for (int i = 0; i < simPara.size; i++)
    {
        alive[i] = runGame();
    }
    return (count(alive.begin(), alive.end(), true) + 0.0) / simPara.size;
}

int main2()
{
    gamePara.fee = 2;
    gamePara.initial_capital = 2;
    gamePara.rounds = 100;

    cout << "Samplesize: ";
    cin >> simPara.size;

    cout << "Monte Carlo simulation of alive players: " << gameSim() << "%" << "\n";
    return 0;
}

int main(int argc, char *argv[])
{
    if (argc < 5)
    {
        return main2();
    }
    else
    {
        gamePara.fee = stoi(argv[1]);
        gamePara.initial_capital = stoi(argv[2]);
        gamePara.rounds = stoi(argv[3]);
        simPara.size = stoi(argv[4]);

        auto start = chrono::high_resolution_clock::now();
        cout << "Monte Carlo simulation of alive players: " << gameSim() * 100 << "%" << "\n";
        auto end = chrono::high_resolution_clock::now();
        chrono::duration<double> duration = end - start;
        cout << "Exectution time: " << duration.count() << " seconds" << "\n";
        return 0;
    }
}