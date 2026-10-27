
def gather2d [n] [m] [k] (matrix: [n][m]f64) (indices: [k](i32,i32)) : [k]f64 =
  map (\(r,c) -> matrix[r,c]) indices

entry main (xs: [3][3]f64) (rs: [3]i32) (cs: [3]i32) : [3]f64 =
  gather2d xs (zip rs cs)

