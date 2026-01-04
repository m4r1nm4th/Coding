open("resources/0089_roman.txt") do file
    diff_length = 0
    for line in eachline(file)
        rv = roman_value(line)
        mm = minimal_roman(rv)
        diff_length += length(line) - length(mm)
        printstyled("Fileline: ", line, " Computed Value: ", rv, " Minimal Computed Value: ", mm;
            color = mm == line ? :green : :red)
        println()
    end
    println("Unnecessary chars: ",diff_length)
end

function minimal_roman(n)
    if n - 1000 >= 0
        return string('M',minimal_roman(n - 1000))
    end
    if n - 900 >= 0
        return string("CM",minimal_roman(n - 900))
    end
    if n - 500 >= 0
        return string('D',minimal_roman(n - 500))
    end
    if n - 400 >= 0
        return string("CD",minimal_roman(n - 400))
    end
    if n - 100 >= 0
        return string('C',minimal_roman(n - 100))
    end
    if n - 90 >= 0
        return string("XC",minimal_roman(n-90))
    end
    if n - 50 >= 0
        return string('L',minimal_roman(n-50))
    end
    if n - 40 >= 0
        return string("XL",minimal_roman(n-40))
    end
    if n - 10 >= 0
        return string('X',minimal_roman(n-10))
    end
    if n == 9
        return "IX"
    end
    if n - 5 >= 0
        return string('V',minimal_roman(n - 5))
    end
    if n == 4
        return "IV"
    end
    if n <= 0
        return ""
    end
    return string('I', minimal_roman(n - 1))
end

function roman_value(roman)
    function help(remain,s)
        if length(remain) == 0
            return s
        end
        a_val = Dict('M'=>1000,'D'=>500,'C'=>100,'L'=>50,'X'=>10, 'V'=>5,'I'=>1)
        a, remain = remain[1], remain[2:end]
        value_of_a = get(a_val,a,0)
        if length(remain) >= 1
            if get(a_val,remain[1],0) > value_of_a
                return help(remain,s-value_of_a)
            end
        end
        return help(remain,s+value_of_a)
    end
    help(roman,0)
end

for n in 44:60
    show(minimal_roman(n))
end