valid = zeros(Int8,14000)
product_length = zeros(Int,14000)

function update(divisors)
    l = length(divisors) + prod(divisors) - sum(divisors)
    if product_length[l] == 0
        product_length[l] = prod(divisors)
    else
        product_length[l] = min(product_length[l], prod(divisors))
    end
end

using Primes

function multiplicative_partitions(n::Int; minfactor::Int = 2)
    result = Vector{Vector{Int}}()
    for d in divisors(n)
        if d < minfactor
            continue
        end

        if d == n
            push!(result, [n])
        else
            for tail in multiplicative_partitions(n ÷ d; minfactor = d)
                push!(result, [d; tail])
            end
        end
    end

    return result
end


for n in 2:14000
    for k in 2*n:n:14000
        valid[k] = 1
    end    
    if valid[n] == 0
        continue
    end
    mps = multiplicative_partitions(n)
    pop!(mps)
    for mp in mps
        update(mp)
    end
end

show(product_length[2:12000])
sum(product_length)
sum(Set(product_length[2:12000]))