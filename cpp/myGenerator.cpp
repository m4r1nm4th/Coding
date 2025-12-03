#include <cstdint>
#include <iostream>
using namespace std;

struct MyRBG
{
    using result_type = uint32_t;

    static constexpr result_type min() noexcept { return 0; }
    static constexpr result_type max() noexcept { return UINT32_MAX; }

    result_type operator()() noexcept
    {
        state ^= state << 13;
        state ^= state >> 17;
        state ^= state << 5;
        return state;
    }

    explicit MyRBG(result_type seed = 1) : state(seed) {}

private:
    result_type state;
};

int main()
{
    int seed;
    cin >> seed;
    MyRBG testRBG(seed);
    auto dist = uniform_int_distribution<int>(0, 1);
    for (int i = 0; i < 5; i++)
    {
        cout << dist(testRBG);
    }
    cout << "\n";
}