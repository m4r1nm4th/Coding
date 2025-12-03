import numpy as np
from sympy import O, expand, fps, lambdify,poly, div, pprint, series, simplify, symbols

def my_lgs():

    fee = 2
    capital = 2

    n = 10
    m = 10 

    z = symbols('z')

    coeff = 1/2
    power = z**1

    mz = 0
    for _ in range(m):
        mz += coeff*power
        coeff *= 1/2
        power *= power

    mz *= z**(-fee)

    simplify(mz)

    res = z**capital  
    for _ in range(n):
        res += mz**n

    res += O(z**(capital))
    res = expand(res).removeO()
    res = res.subs(z,1/z)
    res += O(z)
    res = expand(res).removeO().subs(z,1/z)
    print(res)

    res2 = z**(fee + capital) / (z**fee - mz)
    pprint(series(res2,z,0,3))


    

def __main__():
    my_lgs()

if __name__=="__main__":
    __main__()


                