import numpy as np;
import pandas as pd;

class Lottery:

    initial_wealth = 2.0;
    fee = 2.0;

    def nextRound(self,samples,rng):
        df = self.df;
        df['time'] += 1;
        df['geom']=rng.geometric(p=0.5,size=samples);
        df['virtual_wealth'] = df['wealth'] + np.exp2(df['geom'] - 1) - self.fee;
        df.loc[df['wealth'] >= self.fee,["wealth"]] = df["virtual_wealth"];
        df['min'] = df[['stopping_time','time']].min(axis=1); 
        df.loc[df['wealth'] < self.fee,['stopping_time']] = df['min'];

    def simulate(self, samples = 1000000):
        X = np.array([self.initial_wealth]*samples);
        t = np.array([0]*samples);
        stopping_time = np.array([np.inf]*samples);
        self.df = pd.DataFrame({'wealth': X, 'time': t, 'stopping_time': stopping_time});
        rng = np.random.default_rng();
        p = 1;
        rounds = 250;
        for i in range(rounds):
            self.nextRound(samples, rng);
            p = self.df.loc[self.df['stopping_time'] == np.inf,['stopping_time']].count()/samples;
            print(p)
            #self.df['laplace'] = s * np.exp((-1)* s * self.df['stopping_time']);



def __main__():
    L = Lottery();
    print(L.simulate());

if __name__=="__main__":
    __main__();

    