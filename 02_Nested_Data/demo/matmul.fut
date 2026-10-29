def dot [n] (xs: [n]f64) (ys: [n]f64) : f64 =
  reduce (+) 0.0 (map2 (*) xs ys)

def multiply [n] [m] [p] (a: [n][m]f64) (b: [m][p]f64) : [n][p]f64 =
  map (\row -> map (\column -> dot row column) (transpose b)) a

-- ==
-- compiled random input { [1000][1000]f64 [1000][1000]f64 }
entry main [n] [m] [p] (a: [n][m]f64) (b: [m][p]f64) : [n][p]f64 =
  multiply a b
