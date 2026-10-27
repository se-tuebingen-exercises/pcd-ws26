
def scatter (n: i64) (is: [256]i64) (vs: [256]i64) : [n]i64 =
  reduce_by_index (replicate n 0) (+) 0 is vs

def histogram [n] (xs: [n]i64): [256]i64 =
  reduce_by_index (replicate 256 0) (+) 0 xs (replicate n 1)

def sort [n] (xs: [n]i64) : [n]i64 =
  let counts = histogram xs
  let offsets = scan (+) 0 counts
  let markers = scatter n offsets (replicate 256 1)
  in scan (+) 0 markers

entry main (xs: [4]i64) : [4]i64 =
  sort xs

