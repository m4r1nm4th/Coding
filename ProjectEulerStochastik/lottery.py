import numpy as np
import scipy.signal as sig

class Lottery:

    def initialize_transition(self):
        for i in range(0, np.floor(np.log2(self.size)).astype(int)):
            self.transition[2**i,0] = 1/2**(i+1)

    def next_iteration(self,iteration):
        self.transition[:,iteration + 1] = np.round(sig.fftconvolve(self.transition[:,0], self.transition[:,iteration],mode='full')[:self.size],16)

    def big_f(self,k):
        return self.fee * k - self.wealth

    def calc_numerator(self):
        result = 0
        for k in range(0,self.iterations):
            result += self.transition[:self.big_f(k),k].sum()
        return result

    def calc_denominator(self):
        result = 0
        for k in range(0, self.iterations):
            result += self.transition[0::self.fee*(k + 1),k].sum()
        return result

    def __init__(self, size=1024, iterations=100, fee=2, wealth=5):
        self.size = size
        self.iterations = iterations # size > big_F(iterations)
        self.fee = fee
        self.wealth = wealth
        self.transition = np.zeros((self.size,self.iterations))
        self.initialize_transition()
        for i in range(0,self.iterations - 1):
            self.next_iteration(i)
        self.calc_prob = self.calc_numerator()/self.calc_denominator()





def __main__():
    iteration = 10000
    size = 40000
    print(f"Iteration: {iteration}, Size: {size}, Probability: {Lottery(size=size, iterations=iteration).calc_prob}")


if __name__=="__main__":
    __main__()

