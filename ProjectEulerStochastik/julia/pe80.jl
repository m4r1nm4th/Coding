setprecision(340)

function square_root_digit_sum(n)
    s = sqrt(BigFloat(n))
    decimals = [parse(Int,a) for a in string(s)[1:101] if isdigit(a)]
    sum(decimals)
end

sol = 0
nums = [a for a in 1:100]

nums = setdiff(nums,nums.*nums)

for a in nums
    sol += square_root_digit_sum(a)
end
sol

