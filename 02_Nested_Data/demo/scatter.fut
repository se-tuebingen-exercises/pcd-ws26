def scatter_add (is: []i64) (vs: []f64) : []f64 =
  reduce_by_index (replicate 4 0) (+) 0 is vs

entry main (is: [4]i64) (vs: [4]f64) : [4]f64 =
  scatter_add is vs
