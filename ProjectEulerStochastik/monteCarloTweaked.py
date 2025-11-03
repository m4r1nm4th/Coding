import pandas as pd;
import numpy as np;

class Lottery:

    initial_wealth = 2.0;
    fee = 2.0;

    def nextRound(self,samples,rng):
        df = self.df;
        df['geom']=rng.geometric(p=0.5,size=samples);
        df['virtual_wealth'] = df['wealth'] + np.exp2(df['geom'] - 1) - self.fee;
        df.loc[df['wealth'] >= self.fee,["wealth"]] = df["virtual_wealth"];
        count = df.loc[df['wealth'] < self.fee,['wealth']].count()['wealth'];
        df.loc[df['wealth'] < self.fee,['wealth']] = self.initial_wealth;
        return count;

    def simulate(self, samples = 100000):
        X = np.array([self.initial_wealth]*samples);
        self.df = pd.DataFrame({'wealth': X});
        self.df2 = pd.DataFrame({'birth' : [0], 'dead': [0], 'alive': [samples], 'p': [0]})
        rng = np.random.default_rng();
        rounds =100000;
        for i in range(rounds):
            count2 += self.nextRound(samples, rng);
            count += count2;
            p = count2/count;
            print(p)


def __main__():
    L = Lottery();
    print(L.simulate());

if __name__=="__main__":
    __main__();

    