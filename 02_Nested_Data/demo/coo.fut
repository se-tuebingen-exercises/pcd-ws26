
def multiply_coo [k] [m] (rows: [k]i64) (columns: [k]i64) (values: [k]f64) (vector: [m]f64) (n: i64) : [n]f64 =
  let products = map2 (\c v -> v * vector[c]) columns values
  in reduce_by_index (replicate n 0.0) (+) 0.0 rows products

def main : []f64 =
  let rows = [0, 0, 1, 2, 2, 3]
  let columns = [0, 2, 1, 0, 2, 3]
  let values = [1.0, 2.0, 3.0, 4.0, 5.0, 6.0]
  let vector = [1.0, 2.0, 3.0, 4.0]
  in multiply_coo rows columns values vector 4

