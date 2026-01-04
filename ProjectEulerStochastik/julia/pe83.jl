using DelimitedFiles

M = readdlm("./resources/0083_matrix.txt", ',', Int)

function dijkstra(m)
    sz = size(m)
    short_dist = fill(Inf,sz)
    locked = fill(false, sz)
    short_dist[1,1] = m[1,1]
    locked[1,1] = true
    pointer = CartesianIndex{2}((1,1))
    while any(.!locked)
        neigh = [a + pointer for a in CartesianIndices((-1:1,-1:1)) if abs(a[1]+a[2]) == 1]
        filter!(n -> sz[2]>= n[1] >= 1,neigh)
        filter!(n -> sz[2]>= n[2] >= 1,neigh)
        filter!(n -> !locked[n],neigh)
        short_dist[neigh] .= min.(short_dist[pointer] .+ m[neigh],short_dist[neigh])
        pointer = argmin(short_dist .+ Inf .* locked)
        locked[pointer] = true
    end
    return short_dist
end

show(dijkstra(M))
