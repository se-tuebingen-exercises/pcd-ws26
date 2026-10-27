
def gather (xs: []f64) (is: []i64) : []f64 =
  map (\i -> xs[i]) is

entry main (xs: [4]f64) (is: [4]i64) : [4]f64 =
  gather xs is


