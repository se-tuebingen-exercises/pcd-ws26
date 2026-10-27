
def mean_coo [k] (rows: [k]i64) (columns: [k]i64) (values: [k]f64) (n: i64) (m: i64) : [n]f64 =
  assert false (replicate n 0.0) -- TODO

entry main : [3]f64 =
  let rows = [0, 0, 1, 2, 2]
  let columns = [0, 2, 1, 0, 2]
  let values = [1.0, 3.0, 4.0, 2.0, 5.0]
  in mean_coo rows columns values 3 3

