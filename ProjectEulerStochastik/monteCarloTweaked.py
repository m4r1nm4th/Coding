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
        count = df.loc[df['wealth'] < self.fee,['birth']].groupby(['birth']).count();
        self.df2 = self.df2.join(count.set_index('birth'), on='birth',validate='1:1',rsuffix='count');
        self.df2['dead'] += self.df2['birthcount'];
        self.df2['alive'] -= self.df2['birthcount'];
        df.loc[df['wealth'] < self.fee,['birth']] = self.round;
        df.loc[df['wealth'] < self.fee,['wealth']] = self.initial_wealth;
        c = count.count()
        self.df2.loc[self.round + 1] = {'birth':self.round + 1, 'dead': 0, 'alive': c, 'p': 0};

    def simulate(self, samples = 100000):
        X = np.array([self.initial_wealth]*samples);
        self.df = pd.DataFrame({'wealth': X, 'birth':[0]*samples});
        self.df2 = pd.DataFrame({'birth' : [0], 'dead': [0], 'alive': [samples], 'p': [0], 'birthcount': [0]})
        rng = np.random.default_rng();
        rounds =10;
        self.round = 0;
        for i in range(rounds):
            self.round = i;
            self.nextRound(samples,rng);
        return self.df2


def __main__():
    L = Lottery();
    print(L.simulate());

if __name__=="__main__":
    __main__();

    