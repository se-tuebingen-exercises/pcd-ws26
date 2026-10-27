
def histogram [n] (xs: [n]i64): [256]i32 =
  reduce_by_index (replicate 256 0) (+) 0 xs (replicate n 1)

entry main (xs: [6]i64): [256]i32 =
  histogram xs
