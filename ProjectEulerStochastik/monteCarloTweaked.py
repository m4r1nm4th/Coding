import pandas as pd
import numpy as np


class Lottery:

    initial_wealth = 2.0
    fee = 2.0

    def next_round(self, samples, rng):
        df = self.df
        df2 = self.df2
        df['geom']=rng.geometric(p=0.5,size=samples)
        df['virtual_wealth'] = df['wealth'] + np.exp2(df['geom'] - 1) - self.fee
        df.loc[df['wealth'] >= self.fee,["wealth"]] = df["virtual_wealth"]
        count = df.loc[df['wealth'] < self.fee,['birth','wealth']].groupby(['birth']).count()
        count = count.rename(columns={'wealth':'died_this_turn'})
        if 'died_this_turn' in df2.columns:
            df2 = df2.drop(columns='died_this_turn')
        df2 = df2.join(count, on='birth',validate='1:1')
        df2['dead'] += df2['died_this_turn']
        df2['alive'] -= df2['died_this_turn']
        df.loc[df['wealth'] < self.fee,['birth']] = self.round
        df.loc[df['wealth'] < self.fee,['wealth']] = self.initial_wealth
        c = count['died_this_turn'].sum()
        df2.loc[self.round] = {'birth':self.round, 'dead': 0, 'alive': c}
        self.df2 = df2

    def simulate(self, samples = 10000000):
        w = np.array([self.initial_wealth]*samples)
        self.df = pd.DataFrame({'wealth': w, 'birth':[0]*samples})
        self.df2 = pd.DataFrame({'birth' : [0], 'dead': [0], 'alive': [samples]})
        rng = np.random.default_rng()
        rounds =100
        self.round = 0
        for i in range(rounds):
            self.round = i + 1
            self.next_round(samples, rng)
        self.df2 = self.df2[:-1]
        self.df2['p'] = self.df2['dead'] / (self.df2['dead'] + self.df2['alive'])
        df_cleaned = self.df2['p'][:-rounds//2].dropna()
        mean_p = df_cleaned.mean()
        return 1 - mean_p


def __main__():
    l = Lottery()
    print(l.simulate())


if __name__=="__main__":
    __main__()

    