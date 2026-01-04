function totientquotient(prime_factors)
    prod([1-1/p for p in prime_factors])
end

using Primes


function isPerm(a,b)
    sort(a) == sort(b)
end

max_tq = 0
max_n = 0
for n in 2:10000000
    if isPerm(digits(totient(n)),digits(n))
        fs = factor(n)
        tq = totientquotient(keys(fs))
        if tq > max_tq
            max_tq = tq
            max_n = n
            println("Number: ",n," Totientenquotient: ", tq)
            println(fs)
        end
    end
end

